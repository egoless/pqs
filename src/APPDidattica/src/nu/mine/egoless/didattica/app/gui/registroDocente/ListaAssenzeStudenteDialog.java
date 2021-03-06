/*
 * ListaAssenzeStudenteDialog.java
 *
 * Created on 25 marzo 2007, 20.17
 */

package nu.mine.egoless.didattica.app.gui.registroDocente;

import java.net.PortUnreachableException;
import nu.mine.egoless.didattica.app.bean.AssenzeBean;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import nu.mine.egoless.didattica.ws.assenzaclient.Assenza;
import nu.mine.egoless.didattica.ws.assenzaclient.ParametriRicercaAssenza;
import nu.mine.egoless.didattica.ws.assenzaclient.WSDidatticaException_Exception;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.TipoAssenza;
import nu.mine.egoless.supporto.DateTimeFacade;

/**
 * Visualizza la lista di tutte le assenza di uno 
 * specifico studente.
 *
 * @author  Alberto Meneghello
 */
public class ListaAssenzeStudenteDialog extends javax.swing.JDialog {
      
   
   /**
    * Costruisce un nuovo form ListaAssenzeStudenteDialog.
    * @param titolo Titolo del Dialog
    * @param idStudente ID dello studente di cui visualizzare le assenze.
    * @param tipiAsssenze Lista {@link TipiAssenzeBean} contentente tutti i tipi di assenze.
    */
   public ListaAssenzeStudenteDialog (String titolo, int idStudente, TipiAssenzeBean tipiAssenze ) {
      super (new javax.swing.JFrame(), true);
      this.setTitle (titolo);
      this.tipiAssenze = tipiAssenze;
      caricaDati(idStudente);
      initComponents ();
      update();
      this.setSize (400,300);
   }
   
   /** 
    * This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
   private void initComponents() {
      jscpListaAssenzeStudente = new javax.swing.JScrollPane();
      jlstListaAssenzeStudente = new javax.swing.JTable();
      jbChiudi = new javax.swing.JButton();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      jlstListaAssenzeStudente.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
         },
         new String [] {
            "Da", "A", "Motivo", "Note"
         }
      ) {
         Class[] types = new Class [] {
            java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
         };
         boolean[] canEdit = new boolean [] {
            false, false, false, false
         };

         public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
         }

         public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
         }
      });
      jscpListaAssenzeStudente.setViewportView(jlstListaAssenzeStudente);

      jbChiudi.setText("Chiudi");
      jbChiudi.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbChiudiActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(247, Short.MAX_VALUE)
            .addComponent(jbChiudi, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jscpListaAssenzeStudente, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jscpListaAssenzeStudente, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jbChiudi)
            .addContainerGap())
      );
      pack();
   }// </editor-fold>//GEN-END:initComponents

   
   
   
   
   private void jbChiudiActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChiudiActionPerformed
      setVisible(false);
   }//GEN-LAST:event_jbChiudiActionPerformed
   
   /**
    * Carica tutte le assenze di uno specifico studente.
    * @param idStudente Studente di cui caricare le assenze.
    */
   private void caricaDati(int idStudente) {
      assenzeStudente = new AssenzeBean ();
      ParametriRicercaAssenza parametriAssenza = new ParametriRicercaAssenza();
      parametriAssenza.setIdStudente (idStudente);
      try { 
         assenzeStudente.caricaAssenze (parametriAssenza); 
      } catch( WSDidatticaException_Exception e ) { 
         System.out.println ("Eccezione nel caricare le assenze!" ); 
      } catch( PortUnreachableException e ) {
         System.out.println ("Eccezione nel caricare le assenze!" ); 
      }
   }
   
   /**
    * Aggiorna la lista delle assenze.
    */
   public void update () {
      
      Assenza iteratoreAssenza;
      int numeroAssenze = assenzeStudente.ritornaNumeroDiAssenze ();
      
      Object[][] listaAssenze = new Object[numeroAssenze][];
      
      for(int i=0; i<numeroAssenze; i++ )
         listaAssenze[i] = costruisciEntry ( assenzeStudente.getAssenzaAt (i) );
      
      jlstListaAssenzeStudente.setModel (new javax.swing.table.DefaultTableModel ( listaAssenze, TITOLO ));
   }
   
   
   /**
    * Crea una riga della tabella a partire dalla struttura dati originale.
    */
   private Object[] costruisciEntry (Assenza assenza) {
      
      TipoAssenza tipo = UtilitaGUI.getTipoAssenza( tipiAssenze, assenza.getTipoAssenzaId () );
      String str = "Non giustificata";
      if( tipo!=null) str = tipo.getDescrizione ();
      return new Object[] { (assenza.getDataOraInizio ().getDate () ),
                            (assenza.getDataOraFine ().getDate () ),
                            str,
                            assenza.getGiustificazione ()
      };
   }
   
   
   
   
   
   
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jbChiudi;
   private javax.swing.JTable jlstListaAssenzeStudente;
   private javax.swing.JScrollPane jscpListaAssenzeStudente;
   // End of variables declaration//GEN-END:variables
   
   
   private String[] TITOLO = new String [] { "Da", "A", "Motivo", "Note" };
   private AssenzeBean assenzeStudente;
   private TipiAssenzeBean tipiAssenze;
   
   
   
   
   /**
    * Avvia un'anteprima dell'interfaccia grafica.
    */
   public static void main (String[] args) {
      String str = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";	// Windows
      try { javax.swing.UIManager.setLookAndFeel (str); } catch (Exception ex) { System.out.println ("Failed loading Look And Feel:\n"+ex+'\n'); }
      
      java.awt.EventQueue.invokeLater (new Runnable () {
         public void run () {
            new ListaAssenzeStudenteDialog ("Alberto Meneghello",0,new TipiAssenzeBean() ).setVisible (true);
         }
      });
   }

   
   
   
}
