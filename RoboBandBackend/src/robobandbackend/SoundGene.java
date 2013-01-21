/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robobandbackend;

import java.util.logging.Level;
import java.util.logging.Logger;
import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;

/**
 *
 * @author toddbodnar
 */
public class SoundGene {
    public SoundGene(OscP5 oscP5, NetAddress remote)
    {
        this.oscP5 = oscP5;
        this.remote = remote;
        
        notes = new int[NOTES];
        for(int ct=0;ct<NOTES;ct++)
        {
            if(Math.random()<.05)
                notes[ct] = -1;
            else
                notes[ct] = (int) (Math.random()*(highest-lowest)+lowest);
        }
    }
    
    public void play()
    {
        for(int note:notes)
        {
            if(note>0)
            {
                OscMessage msg = new OscMessage("/newnote");
                msg.setArguments(new Integer[]{note});
                oscP5.send(msg,remote);
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(SoundGene.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String args[])
    {
        OscP5 oscP5 = new OscP5(new test_oscP5(), 10000);
        NetAddress remote = new NetAddress("127.0.0.1", 57120);
        
        for(int ct=0;ct<10;ct++)
            new SoundGene(oscP5,remote).play();
    }
    
    int notes[];
    static int lowest = 45, highest = 120, NOTES = 4;
    double rest_prob = .05;
    OscP5 oscP5;
    NetAddress remote;
}
