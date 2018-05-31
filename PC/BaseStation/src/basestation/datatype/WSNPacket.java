/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package basestation.datatype;

/**
 *
 * @author advanticsys
 */
public class WSNPacket {
    // Sample packet
    // U: unknown + useless
    // S: source addr
    // D: destination addr
    // L: length of payload
    // G: group id
    // R: Radio type
    // A: data
    // 00 00 05 00 00 0A 00 CF 02 01 01 00 01 00 00 00 04 00 00 00
    // UU DD DD SS SS LL GG RR AA AA AA AA AA AA AA AA AA AA AA AA
    
    private static final int is_am_msg_flag_position = 0;
    private static final int dest_addr_position = 1;
    private static final int source_addr_position = 3;
    private static final int data_length_position = 5;
    private static final int group_id_position = 6;
    private static final int radio_type_position = 7;
    private static final int unstructured_data_position = 8;
    
    public Byte is_am_msg_flag;
    public String dest_addr;
    public String source_addr;
    public Integer data_length;
    public String group_id;
    public String radio_type;
    public byte[] unstructured_data;

    
    public static WSNPacket parseRawData(byte[] raw_data) {
        WSNPacket packet = new WSNPacket();
        packet.is_am_msg_flag = raw_data[is_am_msg_flag_position];
        packet.dest_addr = String.format("%02x%02x", raw_data[dest_addr_position], raw_data[dest_addr_position + 1]);
        packet.source_addr = String.format("%02x%02x", raw_data[source_addr_position], raw_data[source_addr_position + 1]);
        packet.data_length = (new Byte(raw_data[data_length_position])).intValue();
        packet.group_id = String.format("%02x", raw_data[group_id_position]);
        packet.radio_type = String.format("%02x", raw_data[radio_type_position]);
        packet.unstructured_data = new byte[packet.data_length];
        for(int i = unstructured_data_position; i < raw_data.length; i++) {
            packet.unstructured_data[i-unstructured_data_position] = raw_data[i];
        }
        return packet;
    }
    
    protected WSNPacket() {
        
    }
}
