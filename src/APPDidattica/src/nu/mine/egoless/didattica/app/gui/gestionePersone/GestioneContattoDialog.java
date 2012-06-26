/*
 * GestioneContattoDialog.java
 *
 * Created on 2 marzo 2007, 17.52
 *
 */

package nu.mine.egoless.didattica.app.gui.gestionePersone;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import nu.mine.egoless.didattica.app.bean.ContattoBean;
import nu.mine.egoless.didattica.app.bean.Costanti;

/**
 *
 * @author  Lorenzo Daniele
 *
 * Semplice estensione di un JDialog che permette di gestire tutti i dati relativi ad un contatto.
 *
 */
public class GestioneContattoDialog extends javax.swing.JDialog {
   
   
   /**
    * Creates new form GestioneContattoDialog
    * @param parent il Frame che eventualmente detiene tale dialog
    * @param modal rende il Dialog modale
    * @param contatto identifica il contatto che si vuole manipolare (solitamente ritornato da
    *         un PersonaBean)
    */
   public GestioneContattoDialog (java.awt.Frame parent, boolean modal, ContattoBean contatto) {//aggiungere ID
      super (parent, modal);
      if(contatto==null) throw new IllegalArgumentException ();
      this.contattoBean = contatto;
      initComponents (); //inizializza le componenti grafiche (controller di notifica dell' MVC)'
      inizializzaForm ();
      //Associazione dei PropertyChangeListener al ContattoBean
      aggiungiPropertyListener ();
   }
   
   /**
    * rende agibili o inagibili le componenti del dialog
    */
   public void setEnabled (boolean b){
      jtfVia.setEnabled (b);
      jtfCivico.setEnabled (b);
      jtfProvincia.setEnabled (b);
      jtfTelefonoPrincipale.setEnabled (b);
      jtfTelefonoSecondario.setEnabled (b);
      jtfFax.setEnabled (b);
   }
   
   /**
    * main di prova
    * @param args the command line arguments
    */
   public static void main (String args[]) {
      java.awt.EventQueue.invokeLater (new Runnable () {
         public void run () {
            new GestioneContattoDialog (new javax.swing.JFrame (), true, new ContattoBean ()).setVisible (true);
         }
      });
   }
   
   /**
    * Aggiunge i listener per i cambiamenti di stato sul personaBean
    */
   private void aggiungiPropertyListener (){
      contattoBean.addPropertyChangeListener ( contattoBean.VIA, new PropertyChangeListener (){
         public void propertyChange (PropertyChangeEvent event){
            System.out.println ("PCE ");
            if(event.getNewValue ()!=null) jtfVia.setText ( (String) event.getNewValue () );
            else jtfVia.setText ("");
         }
      }
      );
      contattoBean.addPropertyChangeListener ( contattoBean.CIVICO, new PropertyChangeListener (){
         public void propertyChange (PropertyChangeEvent event){
            System.out.println ("PCE ");
            if(event.getNewValue ()!=null) jtfCivico.setText ( (String) event.getNewValue () );
            else jtfCivico.setText ("");
         }
      }
      );
      contattoBean.addPropertyChangeListener ( contattoBean.CAP, new PropertyChangeListener (){
         public void propertyChange (PropertyChangeEvent event){
            System.out.println ("PCE ");
            System.out.println ((String) event.getNewValue ());
            System.out.println ((String) contattoBean.getCAP ());
            if(event.getNewValue ()!=null) {
               if(!jtfCAP.getText ().equals ((String) event.getNewValue ())) {
                  jtfCAP.setText ( (String) event.getNewValue () );
               }
            }  else {
               jtfCAP.setText ("");
            }
         }
      }
      );
      contattoBean.addPropertyChangeListener ( contattoBean.PROVINCIA, new PropertyChangeListener (){
         public void propertyChange (PropertyChangeEvent event){
            System.out.println ("PCE ");
            //if(event.getNewValue ()!=null) jtfProvincia.setText ( (String) event.getNewValue () );
            //else jtfProvincia.setText ("");
            
            if(event.getNewValue ()!=null) {
               if(!jtfProvincia.getText ().equals ((String) event.getNewValue ())) {
                  jtfProvincia.setText ( (String) event.getNewValue () );
               }
            }  else {
               jtfProvincia.setText ("");
            }
            
         }
      }
      );
      contattoBean.addPropertyChangeListener ( contattoBean.TELEFONO_PRINCIPALE, new PropertyChangeListener (){
         public void propertyChange (PropertyChangeEvent event){
            System.out.println ("PCE ");
            if(event.getNewValue ()!=null) jtfTelefonoPrincipale.setText ( (String) event.getNewValue () );
            else jtfTelefonoPrincipale.setText ("");
         }
      }
      );
      contattoBean.addPropertyChangeListener ( contattoBean.TELEFONO_SECONDARIO, new PropertyChangeListener (){
         public void propertyChange (PropertyChangeEvent event){
            System.out.println ("PCE ");
            if(event.getNewValue ()!=null) jtfTelefonoSecondario.setText ( (String) event.getNewValue () );
            else jtfTelefonoSecondario.setText ("");
         }
      }
      );
      contattoBean.addPropertyChangeListener ( contattoBean.FAX, new PropertyChangeListener (){
         public void propertyChange (PropertyChangeEvent event){
            System.out.println ("PCE ");
            if(event.getNewValue ()!=null) jtfFax.setText ( (String) event.getNewValue () );
            else jtfFax.setText ("");
         }
      }
      );
   }
   
    /*
     * inizializza il form con i valori delle variabili locali (contattoBean)
     */
   private void inizializzaForm (){
      if(contattoBean.getVia ()!=null) jtfVia.setText (contattoBean.getVia ());
      if(contattoBean.getCivico ()!=null) jtfCivico.setText (contattoBean.getCivico ());
      if(contattoBean.getCAP ()!=null) jtfCAP.setText (contattoBean.getCAP ());
      if(contattoBean.getProvincia ()!=null) jtfProvincia.setText (contattoBean.getProvincia ());
      if(contattoBean.getTelefonoPrincipale ()!=null) jtfTelefonoPrincipale.setText (contattoBean.getTelefonoPrincipale ());
      if(contattoBean.getTelefonoSecondario ()!=null) jtfTelefonoSecondario.setText (contattoBean.getTelefonoSecondario ());
      if(contattoBean.getFax ()!=null) jtfFax.setText (contattoBean.getFax ());
      
        /* Nella form non esiste una text field da cui inserire il valore della citta e della nazione
         quindi sono stati inseriti brutalmente*/
      contattoBean.setCitta (Costanti.CITTA_DUMMY);
      contattoBean.setIdNazione (Costanti.ID_NAZIONE_DUMMY);
   }
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jpnlBottoni = new javax.swing.JPanel();
        jbChiudi = new javax.swing.JButton();
        jpnlRecapitoTelefonico = new javax.swing.JPanel();
        jlblSecondario = new javax.swing.JLabel();
        jlblPrincipale = new javax.swing.JLabel();
        jtfTelefonoPrincipale = new javax.swing.JTextField();
        jtfTelefonoSecondario = new javax.swing.JTextField();
        jlblFax = new javax.swing.JLabel();
        jtfFax = new javax.swing.JTextField();
        jpnlDomicilio = new javax.swing.JPanel();
        jlblVia = new javax.swing.JLabel();
        jlblCivico = new javax.swing.JLabel();
        jlblCAP = new javax.swing.JLabel();
        jlblProvincia = new javax.swing.JLabel();
        jtfVia = new javax.swing.JTextField();
        jtfCivico = new javax.swing.JTextField();
        jtfCAP = new javax.swing.JTextField();
        jtfProvincia = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Contatto  - Gestione Docenti");
        setAlwaysOnTop(true);
        setForeground(java.awt.Color.gray);
        setModal(true);
        setResizable(false);
        jbChiudi.setMnemonic('C');
        jbChiudi.setText("Chiudi");
        jbChiudi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbChiudiActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jpnlBottoniLayout = new org.jdesktop.layout.GroupLayout(jpnlBottoni);
        jpnlBottoni.setLayout(jpnlBottoniLayout);
        jpnlBottoniLayout.setHorizontalGroup(
            jpnlBottoniLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jpnlBottoniLayout.createSequentialGroup()
                .add(216, 216, 216)
                .add(jbChiudi, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnlBottoniLayout.setVerticalGroup(
            jpnlBottoniLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlBottoniLayout.createSequentialGroup()
                .addContainerGap()
                .add(jbChiudi)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnlRecapitoTelefonico.setBorder(javax.swing.BorderFactory.createTitledBorder("Recapito Telefonico"));
        jlblSecondario.setDisplayedMnemonic('S');
        jlblSecondario.setLabelFor(jtfTelefonoSecondario);
        jlblSecondario.setText("Secondario: ");

        jlblPrincipale.setDisplayedMnemonic('N');
        jlblPrincipale.setLabelFor(jtfTelefonoPrincipale);
        jlblPrincipale.setText("Principale: ");

        jtfTelefonoPrincipale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfTelefonoPrincipaleKeyReleased(evt);
            }
        });

        jtfTelefonoSecondario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfTelefonoSecondarioKeyReleased(evt);
            }
        });

        jlblFax.setDisplayedMnemonic('F');
        jlblFax.setLabelFor(jtfFax);
        jlblFax.setText("Fax: ");

        jtfFax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfFaxKeyReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jpnlRecapitoTelefonicoLayout = new org.jdesktop.layout.GroupLayout(jpnlRecapitoTelefonico);
        jpnlRecapitoTelefonico.setLayout(jpnlRecapitoTelefonicoLayout);
        jpnlRecapitoTelefonicoLayout.setHorizontalGroup(
            jpnlRecapitoTelefonicoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlRecapitoTelefonicoLayout.createSequentialGroup()
                .addContainerGap()
                .add(jpnlRecapitoTelefonicoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlblFax)
                    .add(jlblPrincipale)
                    .add(jlblSecondario))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpnlRecapitoTelefonicoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jtfTelefonoPrincipale, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .add(jtfFax, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jpnlRecapitoTelefonicoLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtfTelefonoSecondario, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnlRecapitoTelefonicoLayout.setVerticalGroup(
            jpnlRecapitoTelefonicoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jpnlRecapitoTelefonicoLayout.createSequentialGroup()
                .add(jpnlRecapitoTelefonicoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlblPrincipale)
                    .add(jtfTelefonoPrincipale, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpnlRecapitoTelefonicoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlblSecondario)
                    .add(jtfTelefonoSecondario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpnlRecapitoTelefonicoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlblFax)
                    .add(jtfFax, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnlDomicilio.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio"));
        jlblVia.setDisplayedMnemonic('V');
        jlblVia.setLabelFor(jlblVia);
        jlblVia.setText("Via: ");

        jlblCivico.setDisplayedMnemonic('V');
        jlblCivico.setLabelFor(jtfCivico);
        jlblCivico.setText("Civico: ");

        jlblCAP.setDisplayedMnemonic('A');
        jlblCAP.setLabelFor(jtfCAP);
        jlblCAP.setText("C.A.P.: ");

        jlblProvincia.setDisplayedMnemonic('R');
        jlblProvincia.setLabelFor(jtfProvincia);
        jlblProvincia.setText("Provincia: ");

        jtfVia.setToolTipText("");
        jtfVia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfViaKeyReleased(evt);
            }
        });

        jtfCivico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfCivicoKeyReleased(evt);
            }
        });

        jtfCAP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfCAPKeyReleased(evt);
            }
        });

        jtfProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfProvinciaKeyReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jpnlDomicilioLayout = new org.jdesktop.layout.GroupLayout(jpnlDomicilio);
        jpnlDomicilio.setLayout(jpnlDomicilioLayout);
        jpnlDomicilioLayout.setHorizontalGroup(
            jpnlDomicilioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlDomicilioLayout.createSequentialGroup()
                .addContainerGap()
                .add(jpnlDomicilioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlblCivico)
                    .add(jlblVia)
                    .add(jlblProvincia))
                .add(10, 10, 10)
                .add(jpnlDomicilioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jtfVia, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .add(jtfProvincia, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .add(jpnlDomicilioLayout.createSequentialGroup()
                        .add(jtfCivico, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .add(13, 13, 13)
                        .add(jlblCAP)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtfCAP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpnlDomicilioLayout.setVerticalGroup(
            jpnlDomicilioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlDomicilioLayout.createSequentialGroup()
                .add(jpnlDomicilioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlblVia)
                    .add(jtfVia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpnlDomicilioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlblCivico)
                    .add(jtfCAP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlblCAP)
                    .add(jtfCivico, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpnlDomicilioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlblProvincia)
                    .add(jtfProvincia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlDomicilio, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jpnlRecapitoTelefonico, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jpnlBottoni, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jpnlDomicilio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpnlRecapitoTelefonico, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jpnlBottoni, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Metodi per implementare il controller (MVC) che ascolta gli input utente
     * Vengono richiamati dai listener associati alle varie componenti
     */
    private void jtfFaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfFaxKeyReleased
       contattoBean.setFax ( jtfFax.getText () );
    }//GEN-LAST:event_jtfFaxKeyReleased
    
    private void jtfTelefonoSecondarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTelefonoSecondarioKeyReleased
       contattoBean.setTelefonoSecondario ( jtfTelefonoSecondario.getText () );
    }//GEN-LAST:event_jtfTelefonoSecondarioKeyReleased
    
    private void jtfTelefonoPrincipaleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfTelefonoPrincipaleKeyReleased
       contattoBean.setTelefonoPrincipale ( jtfTelefonoPrincipale.getText () );
    }//GEN-LAST:event_jtfTelefonoPrincipaleKeyReleased
    
    private void jtfProvinciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfProvinciaKeyReleased
       String text = jtfProvincia.getText ();
       if( text.length () >2 ) {
          text = text.substring (0,2);
          jtfProvincia.setText (text);
       }
       contattoBean.setProvincia ( jtfProvincia.getText () );
       
       
    }//GEN-LAST:event_jtfProvinciaKeyReleased
    
    private void jtfCAPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCAPKeyReleased
       String text = jtfCAP.getText ();
       if( text.length () >5 ) {
          text = text.substring (0,5);
          jtfCAP.setText (text);
       }
       contattoBean.setCAP ( text );
    }//GEN-LAST:event_jtfCAPKeyReleased
    
    private void jtfCivicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCivicoKeyReleased
       contattoBean.setCivico ( jtfCivico.getText () );
    }//GEN-LAST:event_jtfCivicoKeyReleased
    
    private void jtfViaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfViaKeyReleased
       contattoBean.setVia ( jtfVia.getText () );
    }//GEN-LAST:event_jtfViaKeyReleased
    
    private void jbChiudiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChiudiActionPerformed
       System.out.println ("ContattoBean:\n "+contattoBean.getVia ()+"\n "+contattoBean.getCivico ()+"\n "+contattoBean.getCAP ()+"\n "+
          contattoBean.getProvincia ()+"\n "+contattoBean.getTelefonoPrincipale ()+"\n "+contattoBean.getTelefonoSecondario ()+"\n "+
          contattoBean.getFax ()+"\n ");  //DEBUG CODE
       this.setVisible (false);
    }//GEN-LAST:event_jbChiudiActionPerformed
    
    /**variabili locali*/
    private ContattoBean contattoBean = null; //Bean associato
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbChiudi;
    private javax.swing.JLabel jlblCAP;
    private javax.swing.JLabel jlblCivico;
    private javax.swing.JLabel jlblFax;
    private javax.swing.JLabel jlblPrincipale;
    private javax.swing.JLabel jlblProvincia;
    private javax.swing.JLabel jlblSecondario;
    private javax.swing.JLabel jlblVia;
    private javax.swing.JPanel jpnlBottoni;
    private javax.swing.JPanel jpnlDomicilio;
    private javax.swing.JPanel jpnlRecapitoTelefonico;
    private javax.swing.JTextField jtfCAP;
    private javax.swing.JTextField jtfCivico;
    private javax.swing.JTextField jtfFax;
    private javax.swing.JTextField jtfProvincia;
    private javax.swing.JTextField jtfTelefonoPrincipale;
    private javax.swing.JTextField jtfTelefonoSecondario;
    private javax.swing.JTextField jtfVia;
    // End of variables declaration//GEN-END:variables
    
}
