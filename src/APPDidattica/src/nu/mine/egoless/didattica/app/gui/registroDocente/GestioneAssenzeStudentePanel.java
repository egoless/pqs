/*
 * GestioneAssenzeStudentePanel.java
 *
 * Created on 6 marzo 2007, 23.43
 *
 * $Id$
 */


package nu.mine.egoless.didattica.app.gui.registroDocente;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.PortUnreachableException;
import java.util.Date;
import javax.swing.event.AncestorEvent;
import nu.mine.egoless.didattica.app.bean.AssenzaBean;
import nu.mine.egoless.didattica.app.bean.AssenzeBean;
import nu.mine.egoless.didattica.app.bean.Costanti;
import nu.mine.egoless.didattica.app.bean.StudenteBean;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.app.bean.TipoAssenzaBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.DataPanel;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import nu.mine.egoless.didattica.app.gui.componentiComuni.elementi.ElementiTipiAssenze;
import nu.mine.egoless.didattica.ws.assenzaclient.ParametriRicercaAssenza;
import nu.mine.egoless.didattica.ws.assenzaclient.WSDidatticaException_Exception;
import nu.mine.egoless.supporto.DateTimeFacade;






/**
 * Questo panello consente di inserire un assenza di uno studente,
 * corredata da giustificazione.
 *
 * @author  Alberto Meneghello
 */
public class GestioneAssenzeStudentePanel extends javax.swing.JPanel {
  
   
   /** 
    * Crea un nuovo form GestioneAssenzeStudentePanel, associandolo
    * ad una specifica assenza.
    * L'assenza deve essere pre-caricata e già associata allo studente.
    * @param ancestor Pannello in cui e' congtenuto questo componente.
    * @param assenza Assenza associata al pannello.
    */
   public GestioneAssenzeStudentePanel (RegistroInsegnantePanel ancestor, RegistroPanel registro, StudenteBean studente, AssenzaBean assenza, Date data) {
      
      System.out.println ("Assenza: iuhuu "+assenza);
      this.assenza = assenza;
      this.data = data;
      this.studente = studente;
      this.ancestor = ancestor;
      this.registro = registro;
      
      inizializzaComponenti ();
      inizializzaCampi();
   }  
   
   
   /**
    * Associa i PropertyChangeListeners ai componenti.
    */
   private void associaPropertyChangeListeners() {      
      
        assenza.addPropertyChangeListener( assenza.DATA_INIZIO, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("Associato PCE a Data Inizio ");
                    if(event.getNewValue()!=null) dataPanelDa.setData( (Date) event.getNewValue() );
                    else dataPanelDa.setData( UtilitaGUI.getDataCorrente () );
                }
            }              
        );
        
        assenza.addPropertyChangeListener( assenza.DATA_FINE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("Associato PCE a Data Fine ");
                    if(event.getNewValue()!=null) dataPanelA.setData( (Date) event.getNewValue() );
                    else dataPanelA.setData( UtilitaGUI.getDataCorrente () );
                }
            }              
        );
        
        assenza.addPropertyChangeListener( assenza.ID_TIPO_ASSENZA, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("Associato PCE a Tipo Assenza ");
                    if(event.getNewValue()!=null) jcmbTipoGiustificazione.setElementoSelezionatoPerId ( ((Integer)event.getNewValue()).intValue () );
                    else jcmbTipoGiustificazione.setElementoSelezionato( 0 );
                }
            }              
        );
        
        assenza.addPropertyChangeListener( assenza.GIUSTIFICAZIONE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("Associato PCE a Note ");
                    jtaGiustificazioneAssenzaStudente.setText( (String) event.getNewValue() );
                }
            }              
        );
        
   }
   
   
   /**
    * Inizializza i componenti del pannello.
    */
   private void inizializzaComponenti(){
      
      initComponents();
      
      dataPanelDa = new DataPanel();
      jpnlContenitoreDataDa.setLayout( new javax.swing.BoxLayout(jpnlContenitoreDataDa, javax.swing.BoxLayout.X_AXIS) );
      jpnlContenitoreDataDa.add(dataPanelDa);
      
      dataPanelA = new DataPanel();
      jpnlContenitoreDataA. setLayout( new javax.swing.BoxLayout(jpnlContenitoreDataA, javax.swing.BoxLayout.X_AXIS) );
      jpnlContenitoreDataA.add(dataPanelA);
      
      tipiAssenze = ancestor.getContenitoreDati ().getTipiAssenze ();
      
      jcmbTipoGiustificazione = new ElementiTipiAssenze(tipiAssenze, false);
      jpnlContenitoreTipoAssenza. setLayout( new javax.swing.BoxLayout(jpnlContenitoreTipoAssenza, javax.swing.BoxLayout.X_AXIS) );
      jpnlContenitoreTipoAssenza.add(jcmbTipoGiustificazione);
      
      
      dataPanelDa.addPropertyChangeListener( new PropertyChangeListener(){
             public void propertyChange(PropertyChangeEvent e){
                 assenza.setDataInizio ( dataPanelDa.getDataSelezionata () );
             }
         }
      );
        
      dataPanelA.addPropertyChangeListener( new PropertyChangeListener(){
             public void propertyChange(PropertyChangeEvent e){
                 assenza.setDataFine ( dataPanelA.getDataSelezionata () );
             }
         }
      );
      
      jbPulisciAssenza.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbCancellaGiustificazioneActionPerformed(evt);
         }
      });
      
   }
   
   /**
    * Inizializza i campi dell'interfaccia grafica con 
    * i dati dell'assenza.
    */
   private void inizializzaCampi(){
      boolean enabled = (assenza!=null);
      setAssenzaEnabled( enabled );
      if( enabled ) {
         jchbAssente.setSelected (true);
         dataPanelDa.setData ( assenza.getDataInizio() );
         dataPanelA.setData ( assenza.getDataFine() );
         jcmbTipoGiustificazione.setElementoSelezionatoPerId ( (int)assenza.getIdTipoAssenza () );
         jtaGiustificazioneAssenzaStudente.setText ( assenza.getGiustificazione () );
         associaPropertyChangeListeners();
      }
      
   }
   
   
   
   /**
    * Permette di gestire la giustificazione solo in caso
    * di assenza dello studente.
    */
   private void setAssenzaEnabled(boolean assenza){
      dataPanelA.setEnabled(assenza);           // Abilita/disabilita tutti i campi sensibili
      dataPanelDa.setEnabled(assenza);          // per la giustificazione
      jcmbTipoGiustificazione.setEnabled(assenza);
      jtaGiustificazioneAssenzaStudente.setEnabled (assenza);
      jbCreaAssenza.setEnabled (assenza);
      jbEliminaAssenza.setEnabled (assenza);
      jbPulisciAssenza.setEnabled (assenza);
   }
   
   
   
   /**
    * Cancella il testo inserito come nota di giustificazione.
    */
   private void jbCancellaGiustificazioneActionPerformed(java.awt.event.ActionEvent evt){
      assenza.setGiustificazione (null);
   }
   
   
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
   private void initComponents() {
      jlblAssenzaA = new javax.swing.JLabel();
      jlblGiustificazioneAssenza = new javax.swing.JLabel();
      jlblAssenzaDa = new javax.swing.JLabel();
      jscpAssenzaStudente = new javax.swing.JScrollPane();
      jtaGiustificazioneAssenzaStudente = new javax.swing.JTextArea();
      jbCreaAssenza = new javax.swing.JButton();
      jbPulisciAssenza = new javax.swing.JButton();
      jchbAssente = new javax.swing.JCheckBox();
      jlblAssente = new javax.swing.JLabel();
      jpnlContenitoreDataDa = new javax.swing.JPanel();
      jpnlContenitoreDataA = new javax.swing.JPanel();
      jpnlContenitoreTipoAssenza = new javax.swing.JPanel();
      jbEliminaAssenza = new javax.swing.JButton();
      jbVisualizzaTutteAssenze = new javax.swing.JButton();

      setBackground(new java.awt.Color(204, 204, 255));
      setBorder(javax.swing.BorderFactory.createTitledBorder("Assenza"));
      jlblAssenzaA.setDisplayedMnemonic('a');
      jlblAssenzaA.setText("A:");

      jlblGiustificazioneAssenza.setDisplayedMnemonic('i');
      jlblGiustificazioneAssenza.setLabelFor(jtaGiustificazioneAssenzaStudente);
      jlblGiustificazioneAssenza.setText("Giustificazione:");

      jlblAssenzaDa.setDisplayedMnemonic('d');
      jlblAssenzaDa.setText("Da:");

      jtaGiustificazioneAssenzaStudente.setFont(new java.awt.Font("Tahoma", 0, 12));
      jtaGiustificazioneAssenzaStudente.setLineWrap(true);
      jtaGiustificazioneAssenzaStudente.setWrapStyleWord(true);
      jtaGiustificazioneAssenzaStudente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      jtaGiustificazioneAssenzaStudente.addKeyListener(new java.awt.event.KeyAdapter() {
         public void keyReleased(java.awt.event.KeyEvent evt) {
            jtaGiustificazioneAssenzaStudenteKeyReleased(evt);
         }
      });

      jscpAssenzaStudente.setViewportView(jtaGiustificazioneAssenzaStudente);

      jbCreaAssenza.setMnemonic('g');
      jbCreaAssenza.setText("Conferma");
      jbCreaAssenza.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbCreaAssenzaActionPerformed(evt);
         }
      });

      jbPulisciAssenza.setText("Pulisci");

      jchbAssente.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
      jchbAssente.setMargin(new java.awt.Insets(0, 0, 0, 0));
      jchbAssente.setOpaque(false);
      jchbAssente.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jchbAssenteActionPerformed(evt);
         }
      });

      jlblAssente.setText("Assente:");

      jpnlContenitoreDataDa.setOpaque(false);
      jpnlContenitoreDataDa.setPreferredSize(new java.awt.Dimension(19, 19));
      org.jdesktop.layout.GroupLayout jpnlContenitoreDataDaLayout = new org.jdesktop.layout.GroupLayout(jpnlContenitoreDataDa);
      jpnlContenitoreDataDa.setLayout(jpnlContenitoreDataDaLayout);
      jpnlContenitoreDataDaLayout.setHorizontalGroup(
         jpnlContenitoreDataDaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 184, Short.MAX_VALUE)
      );
      jpnlContenitoreDataDaLayout.setVerticalGroup(
         jpnlContenitoreDataDaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 19, Short.MAX_VALUE)
      );

      jpnlContenitoreDataA.setOpaque(false);
      jpnlContenitoreDataA.setPreferredSize(new java.awt.Dimension(19, 19));
      org.jdesktop.layout.GroupLayout jpnlContenitoreDataALayout = new org.jdesktop.layout.GroupLayout(jpnlContenitoreDataA);
      jpnlContenitoreDataA.setLayout(jpnlContenitoreDataALayout);
      jpnlContenitoreDataALayout.setHorizontalGroup(
         jpnlContenitoreDataALayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 184, Short.MAX_VALUE)
      );
      jpnlContenitoreDataALayout.setVerticalGroup(
         jpnlContenitoreDataALayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 18, Short.MAX_VALUE)
      );

      jpnlContenitoreTipoAssenza.setOpaque(false);
      jpnlContenitoreTipoAssenza.setPreferredSize(new java.awt.Dimension(19, 19));
      org.jdesktop.layout.GroupLayout jpnlContenitoreTipoAssenzaLayout = new org.jdesktop.layout.GroupLayout(jpnlContenitoreTipoAssenza);
      jpnlContenitoreTipoAssenza.setLayout(jpnlContenitoreTipoAssenzaLayout);
      jpnlContenitoreTipoAssenzaLayout.setHorizontalGroup(
         jpnlContenitoreTipoAssenzaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 184, Short.MAX_VALUE)
      );
      jpnlContenitoreTipoAssenzaLayout.setVerticalGroup(
         jpnlContenitoreTipoAssenzaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 18, Short.MAX_VALUE)
      );

      jbEliminaAssenza.setText("Elimina");
      jbEliminaAssenza.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbEliminaAssenzaActionPerformed(evt);
         }
      });

      jbVisualizzaTutteAssenze.setText("Visualizza Tutte");
      jbVisualizzaTutteAssenze.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbVisualizzaTutteAssenzeActionPerformed(evt);
         }
      });

      org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jbVisualizzaTutteAssenze, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
               .add(jscpAssenzaStudente, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
               .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                  .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                     .add(org.jdesktop.layout.GroupLayout.TRAILING, jlblAssenzaA, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                     .add(jlblAssenzaDa, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                     .add(jlblAssente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jlblGiustificazioneAssenza, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                  .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                     .add(jchbAssente)
                     .add(jpnlContenitoreDataDa, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                     .add(layout.createSequentialGroup()
                        .add(jpnlContenitoreDataA, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                     .add(layout.createSequentialGroup()
                        .add(jpnlContenitoreTipoAssenza, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))))
               .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                  .add(jbCreaAssenza, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                  .add(jbEliminaAssenza, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                  .add(jbPulisciAssenza, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(jlblAssente)
               .add(jchbAssente))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
               .add(jlblAssenzaDa)
               .add(jpnlContenitoreDataDa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
               .add(jlblAssenzaA)
               .add(jpnlContenitoreDataA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
               .add(jlblGiustificazioneAssenza)
               .add(jpnlContenitoreTipoAssenza, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(17, 17, 17)
            .add(jscpAssenzaStudente, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(jbPulisciAssenza)
               .add(jbEliminaAssenza)
               .add(jbCreaAssenza, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jbVisualizzaTutteAssenze))
      );
   }// </editor-fold>//GEN-END:initComponents

   private void jbVisualizzaTutteAssenzeActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizzaTutteAssenzeActionPerformed
      String nomeStudente = studente.getCognome() + " " + studente.getNome ();
      new ListaAssenzeStudenteDialog( nomeStudente, studente.getId (), ancestor.getContenitoreDati ().getTipiAssenze () ).setVisible (true);
   }//GEN-LAST:event_jbVisualizzaTutteAssenzeActionPerformed

   
   
   
   
   
   
   
   
   
   private void jbEliminaAssenzaActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEliminaAssenzaActionPerformed
      (new javax.swing.SwingWorker<Object, Object>(){
         public Object doInBackground(){
            try { 
               assenza.cancellaAssenza (); 
               //setAssenzaEnabled(false);
               //jchbAssente.setEnabled(false);
               System.out.println("ELIMINATA");
               jchbAssente.setSelected (false);
               assenza = null;
               inizializzaCampi();
               if(registro!=null) registro.aggiornaModifica (studente,assenza);
            } 
            catch (WSDidatticaException_Exception ex) {
               new javax.swing.JOptionPane("Impossibile completare l'operazione.",javax.swing.JOptionPane.WARNING_MESSAGE ).setVisible(true);
               ex.printStackTrace();
            }
            return null;
         }
      }).execute();
   }//GEN-LAST:event_jbEliminaAssenzaActionPerformed

   
   
   
   
   private void jtaGiustificazioneAssenzaStudenteKeyReleased (java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaGiustificazioneAssenzaStudenteKeyReleased
      assenza.setGiustificazione ( jtaGiustificazioneAssenzaStudente.getText () );
   }//GEN-LAST:event_jtaGiustificazioneAssenzaStudenteKeyReleased

   
   
   
   
   private void jchbAssenteActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchbAssenteActionPerformed
      boolean enabled = jchbAssente.isSelected ();
      setAssenzaEnabled( enabled );
      if( enabled && assenza == null ) creaNuovaAssenza();
   }//GEN-LAST:event_jchbAssenteActionPerformed
   
   
   /**
    * Crea una nuova assenza per lo studente.
    * @return Ritorna l'assenza creata (questo metodo puo' 
    * essere invocato dal RegistroPanel).
    */
   public AssenzaBean creaNuovaAssenza() {
      
      // Controlla se l'assenza continua da piu' giorni.
      ParametriRicercaAssenza paramAssenza = new ParametriRicercaAssenza();
      paramAssenza.setIdStudente (  studente.getId () );
      //paramAssenza.setDataFine (  (getGiornoPrima(data)) );
      AssenzeBean assenzaGiornoPrima = new AssenzeBean();
      try { assenzaGiornoPrima.caricaAssenze ( paramAssenza ); }
      catch (WSDidatticaException_Exception e) {}
      catch (PortUnreachableException e) {}
      
      // Se no allora l'assenza crea una nuova assenza.
      if (assenzaGiornoPrima.ritornaNumeroDiAssenze () == 0 ) { 
         assenza = new AssenzaBean(); assenza.setDataInizio ( UtilitaGUI.getDataCorrente () );
      }
      // Se si' allora l'assenza continua dai giorni precedenti.
      else try {
         if(assenza==null) assenza = new AssenzaBean();
         assenza.caricaDaWS ( assenzaGiornoPrima.getAssenzaAt (0).getId () );
      } catch (WSDidatticaException_Exception e) {}
      
      assenza.setDataFine ( UtilitaGUI.getDataCorrente () );
      
      inizializzaCampi();
      if(registro!=null) registro.aggiornaModifica (studente,assenza);
      return assenza;
   }
   
   
   public Date getGiornoPrima(Date data) {
      return new Date( data.getYear (), data.getMonth (), data.getDate ()-1 );
   }
   
   
   /**
    * Inserisce l'assenza dello studente.
    * Per motivi impleentativi, ciò richiede di inserire 
    * anche la giustificazione, e l'operazione non e'
    * reversibile.
    */
   private void jbCreaAssenzaActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCreaAssenzaActionPerformed
     
      (new javax.swing.SwingWorker<Object, Object>(){
         public Object doInBackground(){
            try { 
               System.out.println("DA SALVARE");
               TipoAssenzaBean tipoAssenza = ((TipoAssenzaBean)jcmbTipoGiustificazione.getElementoSelezionato ());
               if(tipoAssenza!=null) assenza.setIdTipoAssenza ( tipoAssenza.getId () );
               assenza.setIdPersona (studente.getId ());
               assenza.setIdEvento(Costanti.ID_EVENTO_DUMMY);
               assenza.salvaSuWS(); 
               //setAssenzaEnabled(false);
               //jchbAssente.setEnabled(false);
               System.out.println("SALVATO");
            } 
            catch (Exception ex) {
               System.out.println("GesAssenzeStudPanel Ecc1 :"+ex.getClass().toString());
               new javax.swing.JOptionPane("Impossibile completare l'operazione.",javax.swing.JOptionPane.WARNING_MESSAGE ).setVisible(true);
               ex.printStackTrace();
            }
            return null;
         }
      }).execute();
      
      System.out.println (  "ASSENZA\nDa: " + UtilitaGUI.dateToString ( assenza.getDataInizio () ) + 
                          "\t    A: " + UtilitaGUI.dateToString ( assenza.getDataFine () ) +
                          "\t    Di tipo: " + assenza.getIdTipoAssenza () +
                          "\t    Note: :"  + assenza.getGiustificazione ()
                  );  // Debug code 
      
   }//GEN-LAST:event_jbCreaAssenzaActionPerformed
   
   
   
   
   
   
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jbCreaAssenza;
   private javax.swing.JButton jbEliminaAssenza;
   private javax.swing.JButton jbPulisciAssenza;
   private javax.swing.JButton jbVisualizzaTutteAssenze;
   private javax.swing.JCheckBox jchbAssente;
   private javax.swing.JLabel jlblAssente;
   private javax.swing.JLabel jlblAssenzaA;
   private javax.swing.JLabel jlblAssenzaDa;
   private javax.swing.JLabel jlblGiustificazioneAssenza;
   private javax.swing.JPanel jpnlContenitoreDataA;
   private javax.swing.JPanel jpnlContenitoreDataDa;
   private javax.swing.JPanel jpnlContenitoreTipoAssenza;
   private javax.swing.JScrollPane jscpAssenzaStudente;
   private javax.swing.JTextArea jtaGiustificazioneAssenzaStudente;
   // End of variables declaration//GEN-END:variables
   
   private RegistroInsegnantePanel ancestor;
   
   private ElementiTipiAssenze jcmbTipoGiustificazione;
   private DataPanel dataPanelDa;
   private DataPanel dataPanelA;
   
   private StudenteBean studente;
   private AssenzaBean assenza;
   private TipiAssenzeBean tipiAssenze;
   private Date data;
   private RegistroPanel registro;
   
   
   
   /**
    * Avvia un anteprima dell'interfaccia grafica.
    */
   public static void main(String[] args) {
        String str = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	try { javax.swing.UIManager.setLookAndFeel(str); }
	catch (Exception ex) { System.out.println("Failed loading Look And Feel:\n"+ex+'\n'); }
        javax.swing.JFrame temp=new javax.swing.JFrame();
        temp.setLayout(new java.awt.BorderLayout());
        
        AssenzaBean assenza = new AssenzaBean();
        assenza.setDataInizio ( UtilitaGUI.getDataCorrente () );
        assenza.setDataFine ( UtilitaGUI.getDataCorrente () );
        assenza.setIdTipoAssenza (2);
        assenza.setGiustificazione ("Ciao ciao");
        
        
        
        System.out.println( //DateTimeFacade.String2Date(
           DateTimeFacade.Date2String( new Date(85,6,8)) ); // );
        
        temp.add(new GestioneAssenzeStudentePanel( null, null, new StudenteBean(), assenza, UtilitaGUI.getDataCorrente () )); //assenza ));
        temp.setSize(380,400);
        temp.setVisible(true);
    }
   
}
