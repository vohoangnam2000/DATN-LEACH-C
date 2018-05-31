/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package basestation;


/**
 *
 * @author advanticsys
 */
public class Main {

    public static int assignment_no = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Thread listen = new Thread(new PacketReceiver());
        listen.start();
    }
}
