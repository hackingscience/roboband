/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robobandbackend;

import netP5.NetAddress;
import oscP5.OscEventListener;
import oscP5.OscMessage;
import oscP5.OscP5;
import oscP5.OscStatus;

/**
 *
 * @author toddbodnar
 */
public class waitAndRespond {
    public static void main(String args[])
    {
        OscP5 oscP5 = new OscP5(new test_oscP5(), 10000);
        NetAddress remote = new NetAddress("127.0.0.1", 57120);
        oscP5.addListener(new OscEventListener() {

            @Override
            public void oscEvent(OscMessage om) {
                System.out.println(om.typetag());
                System.out.println(om.addrPattern());
                System.out.println(om.toString());
                System.out.println(om.arguments()[0]);
                System.exit(0);
            }

            @Override
            public void oscStatus(OscStatus os) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }
}
