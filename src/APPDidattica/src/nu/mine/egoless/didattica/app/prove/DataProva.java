/*
 * DataProva.java
 *
 * Created on 16 marzo 2007, 22.18
 */

package nu.mine.egoless.didattica.app.prove;

import nu.mine.egoless.didattica.app.gui.componentiComuni.DataPanel;
import java.util.Date;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
/**
 *
 * @author  Administrator
 */
public class DataProva extends javax.swing.JPanel {
    
    /** Creates new form DataProva */
    public DataProva() {
        initComponents();
        jpnlData.addPropertyChangeListener( new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent e){
                    System.out.println("YEAHHHHHHHHH");
                }
            }       
        );
    }
    
   public static void main (String[] args) {
    javax.swing.SwingUtilities.invokeLater( new Runnable(){public void run(){  
      String str = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";	// Windows
      //String str = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";		// Grey
      //String str = "javax.swing.plaf.metal.MetalLookAndFeel";   			// Java Metal
      try { javax.swing.UIManager.setLookAndFeel (str); } catch (Exception ex) { System.out.println ("Failed loading Look And Feel:\n"+ex+'\n'); }
      javax.swing.JFrame temp=new javax.swing.JFrame ();
      temp.setLayout (new java.awt.BorderLayout ());
      DataProva prova = new DataProva();
      temp.add (prova);
      temp.setSize (200,56);
      temp.setVisible (true);
    }});
   }  
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jpnlData = new nu.mine.egoless.didattica.app.gui.componentiComuni.DataPanel();
        jButton1 = new javax.swing.JButton();

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnlData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jpnlData.setData(new Date(70,1,1));
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private nu.mine.egoless.didattica.app.gui.componentiComuni.DataPanel jpnlData;
    // End of variables declaration//GEN-END:variables
    
}
