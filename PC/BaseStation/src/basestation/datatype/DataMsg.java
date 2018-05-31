/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package basestation.datatype;

/**
 *
 * @author advanticsys
 */
public class DataMsg extends WSNPacket {
    private long owner_addr;
    private int temperature_raw;
    private int humidity_raw;
    private int light_raw;
    private int voltage_raw;
    private int infared_raw;
    
}
