/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package basestation;

import java.io.IOException;
import net.tinyos.packet.BuildSource;
import net.tinyos.packet.PacketSource;
import net.tinyos.util.Dump;
import net.tinyos.util.PrintStreamMessenger;

/**
 *
 * @author advanticsys
 */
public class PacketSend {
    public static void sendAssignment(String msg) throws IOException {
        byte[] assignment = new byte[msg.split(" ").length];
        int i = 0;
        for(String str : msg.split(" ")) {
            assignment[i] = (byte)Integer.parseInt(str, 16);
            i++;
        }
        sendAssignment(assignment);
    }
    
    private static void sendAssignment(byte[] assignment) throws IOException {
        PacketSource sfw = BuildSource.makePacketSource();
	sfw.open(PrintStreamMessenger.err);

	try {
	    sfw.writePacket(assignment);
	}
	catch (IOException e) {
            System.out.println("Damn!");
	    System.exit(2);
	}
        Dump.printPacket(System.out, assignment);
	System.out.println();
	// A close would be nice, but javax.comm's close is deathly slow
	sfw.close();
    }
}
