/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robobandbackend;

import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;

/**
 * Simple test
 * @author toddbodnar
 */
public class test_oscP5 {
    public static void main(String args[]) throws InterruptedException
    {
        OscP5 oscP5 = new OscP5(new test_oscP5(), 10000);
        NetAddress remote = new NetAddress("127.0.0.1", 57120);
        
        for(int i =65; i<100;i+=1){
        OscMessage msg = new OscMessage("/newnote");
        msg.setArguments(new Integer[]{i});
        //msg.set(1, i);
        oscP5.send(msg,remote);
        
        Thread.sleep(1000/25);
        }
        System.exit(0);
    }
}
