/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robobandbackend;

import javax.swing.JButton;
import netP5.NetAddress;
import oscP5.OscEventListener;
import oscP5.OscMessage;
import oscP5.OscP5;
import oscP5.OscStatus;

/**
 *
 * @author toddbodnar
 */
public class RoboGrader extends javax.swing.JFrame {

    /**
     * Creates new form RoboGrader
     */
    public RoboGrader() {
        initComponents();
        OscP5 oscP5 = new OscP5(new test_oscP5(), 10000);
        NetAddress remote = new NetAddress("127.0.0.1", 57120);
        populations = new SoundGene[keytypes][popsize];
        scores = new int[keytypes][popsize];
        pointers = new int[keytypes];
        
        for(int i=0;i<keytypes;i++)
            for(int j=0;j<popsize;j++)
            {
                populations[i][j] = new SoundGene(oscP5,remote);
                scores[i][j] = -1;
                pointers[i] = 0;
            }
        
        oscP5.addListener(new OscEventListener() {

            @Override
            public void oscEvent(OscMessage om) {
                process(om);
            }

            @Override
            public void oscStatus(OscStatus os) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    long time=0;
    
    public void process(OscMessage om)
    {
        if(om.checkAddrPattern("/note_message"))
        {
            if(System.currentTimeMillis() - time < 750)
                return;
            time = System.currentTimeMillis();
            System.out.println(om.arguments()[0]);
            float inFreq = (Float)(om.arguments()[0]);
            
            int type = -1;
            
            if (inFreq > 80 && inFreq < 100) 
                type = 1;
            else
                if(inFreq <120)
                    type = 2;
                else if(inFreq < 135)
                    type = 3;
                else if(inFreq < 160)
                    type = 4;

            if(type < 0)
                return;
            
            
            
            mostRecentKey = type;
            mostRecentPoint = pointers[type];
            populations[type][pointers[type]].play();
            pointers[type] = (pointers[type]+1)%popsize;
            
            jLabel1.setText("Last Response: "+type+","+mostRecentPoint);
           // jLabel1.set
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setText("Last Response: ");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jLabel1))
                    .add(layout.createSequentialGroup()
                        .add(jButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton5)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton2)
                    .add(jButton3)
                    .add(jButton4)
                    .add(jButton5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int value = Integer.parseInt(((JButton)(evt.getSource())).getText());
        System.out.println(value);
        if (mostRecentKey == -1 || mostRecentPoint == -1)
            return;
        
        scores[mostRecentKey][mostRecentPoint] = value;
        
        for(int sc : scores[mostRecentKey])
            if(sc < 0)
                return;
        
        
        //else, entire sub-pop has been graded;
        System.out.println("New generation!");
        SoundGene popnew[] = new SoundGene[popsize];
            
            for(int ct=0;ct<popsize;ct++)
            {
                SoundGene parents[] = new SoundGene[2];
                for(int c=0;c<2;c++)
                {
                    int r1 = (int) (Math.random()*popsize);
                    int r2 = (int) (Math.random()*popsize);
                    
                    if(scores[mostRecentKey][r1]<scores[mostRecentKey][r2])
                        parents[c] = populations[mostRecentKey][r2];
                    else
                        parents[c] = populations[mostRecentKey][r1];
                }
                
                popnew[ct] = new SoundGene(parents);
            }
            
            populations[mostRecentKey] = popnew;
            
         for(int ct=0;ct<popsize;ct++)
             scores[mostRecentKey][ct] = -1;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton2ActionPerformed(evt);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jButton2ActionPerformed(evt);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jButton2ActionPerformed(evt);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jButton2ActionPerformed(evt);
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RoboGrader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoboGrader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoboGrader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoboGrader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoboGrader().setVisible(true);
            }
        });
    }
    public static int popsize = 5, keytypes = 10;
    public SoundGene populations[][]; //the populations of responses
    public int scores[][];//the scores for the responses
    public int pointers[]; //which response for each keytype will be play next?
    public int mostRecentKey = -1, mostRecentPoint = -1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}