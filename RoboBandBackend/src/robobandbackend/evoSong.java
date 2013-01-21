/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robobandbackend;

import javax.swing.JOptionPane;
import netP5.NetAddress;
import oscP5.OscP5;

/**
 *
 * @author toddbodnar
 */
public class evoSong {
    public static void main(String args[])
    {
        int popSize = 5;
        int generations = 10;
        
        SoundGene.NOTES = 12;
        
        OscP5 oscP5 = new OscP5(new test_oscP5(), 10000);
        NetAddress remote = new NetAddress("127.0.0.1", 57120);
        
        SoundGene population[] = new SoundGene[popSize];
        for(int ct=0;ct<popSize;ct++)
            population[ct] = new SoundGene(oscP5,remote);
        
        for(int g = 0;g<generations;g++)
        {
            int scores[] = new int[popSize];
            
            for(int ct=0;ct<popSize;ct++)
            {
                JOptionPane.showMessageDialog(null, "Now song "+ct+" of gen "+g+".");
                population[ct].play();
                scores[ct] = Integer.parseInt(JOptionPane.showInputDialog("Score?"));
            }
            
            SoundGene popnew[] = new SoundGene[popSize];
            
            for(int ct=0;ct<popSize;ct++)
            {
                SoundGene parents[] = new SoundGene[2];
                for(int c=0;c<2;c++)
                {
                    int r1 = (int) (Math.random()*popSize);
                    int r2 = (int) (Math.random()*popSize);
                    
                    if(scores[r1]<scores[r2])
                        parents[c] = population[r2];
                    else
                        parents[c] = population[r1];
                }
                
                popnew[ct] = new SoundGene(parents);
            }
            
            population = popnew;
        }
        
        System.exit(0);
    }
}
