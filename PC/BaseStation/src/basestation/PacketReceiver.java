/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package basestation;

import basestation.datatype.WSNPacket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import net.tinyos.packet.BuildSource;
import net.tinyos.packet.PacketSource;
import net.tinyos.util.Dump;
import net.tinyos.util.PrintStreamMessenger;

/**
 *
 * @author advanticsys
 */
public class PacketReceiver implements Runnable {

    @Override
    public void run() {
        readMsg();
    }
    
    private void readMsg() {
        PacketSource reader;
        
        reader = BuildSource.makePacketSource();
        if (reader == null) {
            System.err.println("Invalid packet source (check your MOTECOM environment variable)");
            System.exit(2);
        }

        try {
            reader.open(PrintStreamMessenger.err);
            for (;;) {
                byte[] packet = reader.readPacket();
                System.out.println("Receive packet");
                Dump.printPacket(System.out, packet);
                System.out.println();
                analyzeMsg(packet);
            }
        } catch (IOException e) {
            System.err.println("Error on " + reader.getName() + ": " + e);
        }
    }
    int assignment_no = 0;
    private void analyzeMsg(byte[] packet) {
        WSNPacket pkt = WSNPacket.parseRawData(packet);
        System.out.println("Source addr: " + pkt.source_addr);
        System.out.println("Dest addr: " + pkt.dest_addr);
        System.out.println("Data length: " + pkt.data_length);
        System.out.println("Group id: " + pkt.group_id);
        System.out.println("Radio type: " + pkt.radio_type);
        System.out.println("Unstructured data:");
        Dump.printPacket(System.out, pkt.unstructured_data);
        System.out.println();
        if(assignment_no >= 32) {
            assignment_no = 0;
            return;
        }
        if(pkt.radio_type.equalsIgnoreCase(String.format("%02x", 205))) {
            assignment_no = 1;
            try {
                PacketSend.sendAssignment("00 00 00 00 00 0C 00 CF 02 01 01 00 01 00 00 00 04 00 00 00");
            } catch (IOException ex) {
                Logger.getLogger(PacketReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(pkt.radio_type.equalsIgnoreCase(String.format("%02x", 206))) {
            assignment_no++;
            try {
                PacketSend.sendAssignment("00 00 00 00 00 0C 00 CF 02 01 01 00 01 00 00 00 04 00 00 00");
            } catch (IOException ex) {
                Logger.getLogger(PacketReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
