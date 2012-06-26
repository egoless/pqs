/*
 * ListaVotiStudenteDialog.java
 *
 * Created on 27 marzo 2007, 4.27
 */

package nu.mine.egoless.didattica.app.gui.registroDocente;

import java.net.PortUnreachableException;
import java.util.HashMap;
import java.util.Vector;
import nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.VotiBean;
import nu.mine.egoless.didattica.app.bean.VotoBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ContenitoreDati;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import nu.mine.egoless.didattica.ws.votoclient.ParametriRicercaVoto;
import nu.mine.egoless.didattica.ws.votoclient.WSDidatticaException_Exception;

/**
 * Questa finestra visualizza tutti i voti associati
 * ad uno specifico studente.
 *
 * @author  Alberto Meneghello
 */
public class ListaVotiStudenteDialog extends javax.swing.JDialog {
   
   /** 
    * Crea un nuovo form ListaVotiStudenteDialog.
    * @param idStudente id dello studente di cui visualizzare i voti.
    * @param idMateria id della materia di cui visualizzare i voti;
    * se negativo si visualizzano tutte le materie.
    * @param dati contenitore dei tipi semplici.
    */
   public ListaVotiStudenteDialog (int idStudente, int idMateria, ContenitoreDati dati ) {//  , int idMateria) {
      super(new javax.swing.JFrame(),true);
      this.idStudente = idStudente;
      this.dati = dati;
      initComponents ();
      this.setBarraDownload (-1);
      try {
         if(idMateria<0) caricaDatiTutteMaterie();
         else caricaDatiMateria(idMateria);
      }
      catch (PortUnreachableException ex) { ex.printStackTrace(); } 
      catch (WSDidatticaException_Exception ex) { ex.printStackTrace(); }      
      this.setBarraDownload (0);
   }
   
   
   
   /**
    * Carica i voti di tutte le materie.
    */
   private void caricaDatiTutteMaterie () throws WSDidatticaException_Exception, PortUnreachableException {
      MaterieInsegnamentoBean materie = dati.getMaterieInsegnamento ();
      int numMaterie = materie.ritornaNumeroDiMaterie ();
      
      ParametriRicercaVoto paramVoto = new ParametriRicercaVoto();
      paramVoto.setIdStudente (idStudente);
      VotiBean voti = new VotiBean();
      voti.caricaVoti ( paramVoto );
      int numVoti = voti.ritornaNumeroDiVoti ();
     
      HashMap< Integer, Vector<VotoBean> > votiPerMateria = new HashMap< Integer, Vector<VotoBean> >();
      for(int i=0; i < numMaterie; i++ ) {
         votiPerMateria.put( materie.getMateriaAt (i).getId() , new Vector<VotoBean>() );
      }  

      /*java.awt.EventQueue.invokeLater (new Runnable () {
         public void run () {      */
            for(int i=0; i<numVoti; i++) {
               this.setBarraDownload (i*100/numVoti);
               VotoBean voto = new VotoBean();
               voto.caricaDaWS (voti.getVotoAt (i).getId () );
               ( votiPerMateria.get( new Integer(voto.getIdMateria ()) ) ).add(voto);
            }
         /*}
      });*/
       
      for(int i=0; i < numMaterie; i++ )
         jtbpVotiMaterie.add(  materie.getMateriaAt (i).getNome (),
            new ListaVotiStudentePanel( 
            votiPerMateria.get( 
            materie.getMateriaAt (i).getId() ),
                                                          materie.getMateriaAt (i).getNome (), 
            dati ) );
         
   }
   
   
   /**
    * Carica i voti di una singola materia.
    */
   private void caricaDatiMateria (int idMateria) throws WSDidatticaException_Exception, PortUnreachableException {
      MaterieInsegnamentoBean materie = dati.getMaterieInsegnamento ();
      String nomeMateria = UtilitaGUI.getMateriaInsegnamento (materie, idMateria).getNome () ;
      
      ParametriRicercaVoto paramVoto = new ParametriRicercaVoto();
      paramVoto.setIdStudente (idStudente);
      paramVoto.setIdMateria (idMateria);
      VotiBean voti = new VotiBean();
      voti.caricaVoti ( paramVoto );
      
      Vector<VotoBean> listaVoti = new Vector<VotoBean>();
      int numVoti = voti.ritornaNumeroDiVoti ();

      /*java.awt.EventQueue.invokeLater (new Runnable () {
         public void run () {      */
            for(int i=0; i<numVoti; i++){
               this.setBarraDownload (i*100/numVoti);
               VotoBean voto = new VotoBean();
               voto.caricaDaWS (voti.getVotoAt (i).getId () );
               listaVoti.add(voto);
            }
         /*}
      });*/      
      jtbpVotiMaterie.add( nomeMateria, new ListaVotiStudentePanel( listaVoti, nomeMateria, dati ) );
   }
   
   
   
   
   /**
    * Setta la barra di caricamento al valore n [0-100].
    */
   public void setBarraDownload(int n) {
      if(n<0) jpgbBarraDiCaricamento.setIndeterminate (true);
      else if(n<=100) { jpgbBarraDiCaricamento.setIndeterminate (false); jpgbBarraDiCaricamento.setValue (n); }
   }
   
   
   
   
   /** 
    * This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
   private void initComponents() {
      jtbpVotiMaterie = new javax.swing.JTabbedPane();
      jbChiudi = new javax.swing.JButton();
      jpgbBarraDiCaricamento = new javax.swing.JProgressBar();
      jlblCaricamento = new javax.swing.JLabel();
      jsepListaVoti = new javax.swing.JSeparator();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      jtbpVotiMaterie.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

      jbChiudi.setText("Chiudi");
      jbChiudi.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbChiudiActionPerformed(evt);
         }
      });

      jlblCaricamento.setText("Caricamento:");

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(jsepListaVoti, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jtbpVotiMaterie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(jlblCaricamento)
                  .addGap(13, 13, 13)
                  .addComponent(jpgbBarraDiCaricamento, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                  .addComponent(jbChiudi, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jtbpVotiMaterie, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jsepListaVoti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(jbChiudi)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jpgbBarraDiCaricamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jlblCaricamento)))
            .addContainerGap())
      );
      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void jbChiudiActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChiudiActionPerformed
      this.setVisible(false);
   }//GEN-LAST:event_jbChiudiActionPerformed
   
   /**
    * @param args the command line arguments
    */
   public static void main (String args[]) {
      java.awt.EventQueue.invokeLater (new Runnable () {
         public void run () {
            new ListaVotiStudenteDialog (0,0, null).setVisible (true);
         }
      });
   }
   
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jbChiudi;
   private javax.swing.JLabel jlblCaricamento;
   private javax.swing.JProgressBar jpgbBarraDiCaricamento;
   private javax.swing.JSeparator jsepListaVoti;
   private javax.swing.JTabbedPane jtbpVotiMaterie;
   // End of variables declaration//GEN-END:variables

   private ContenitoreDati dati;
   private int idStudente;
   
}
