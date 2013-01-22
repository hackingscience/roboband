/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robobandbackend;

/**
 *
 * @author toddbodnar
 */
public class SoundGene {
<<<<<<< HEAD
    public SoundGene()
=======
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
    
    public SoundGene(SoundGene parents[])
    {
        this.oscP5 = parents[0].oscP5;
        this.remote = parents[0].remote;
        
        notes = new int[NOTES];
        int p = 0;
        for(int ct=0;ct<NOTES;ct++)
        {
            if(Math.random() < cross_prob)
                p++;
            
            notes[ct] = parents[p%parents.length].notes[ct];
        }
    }
    
    public void play()
    {
        for(int note:notes)
        {
            System.out.println(note);
            if(note>0)
            {
                OscMessage msg = new OscMessage("/newnote");
                msg.setArguments(new Integer[]{note});
                oscP5.send(msg,remote);
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(SoundGene.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String args[])
>>>>>>> Final Java code
    {
        
<<<<<<< HEAD
    }
    
=======
        
        
        for(int ct=0;ct<1;ct++)
            new SoundGene(oscP5,remote).play();
    }
    
    int notes[];
    static int lowest = 45, highest = 75, NOTES = 4;
    double rest_prob = .05, cross_prob = .1;
    OscP5 oscP5;
    NetAddress remote;
>>>>>>> Final Java code
}
