/*
 * GestioneVotiStudentePanel.java
 *
 * Created on 7 marzo 2007, 3.07
 *
 * $Id$
 */

package nu.mine.egoless.didattica.app.gui.registroDocente;

import java.util.Date;
import nu.mine.egoless.didattica.app.bean.Costanti;
import nu.mine.egoless.didattica.app.bean.MateriaInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.StudenteBean;
import nu.mine.egoless.didattica.app.bean.TipoProvaBean;
import nu.mine.egoless.didattica.app.bean.TipoVotoBean;
import nu.mine.egoless.didattica.app.bean.VotoBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import nu.mine.egoless.didattica.app.gui.componentiComuni.elementi.ElementiTipiProve;
import nu.mine.egoless.didattica.app.gui.componentiComuni.elementi.ElementiTipiVoti;
import nu.mine.egoless.didattica.app.gui.componentiComuni.elementi.ElementoTipoProva;
import nu.mine.egoless.didattica.app.gui.componentiComuni.elementi.ElementoTipoVoto;
import nu.mine.egoless.didattica.ws.votoclient.WSDidatticaException_Exception;

/**
 * Questo pannello consente di assegnare un voto ad un certo studente
 * in una specifica materia, in una precisa data.
 *
 * @author  Alberto Meneghello
 */
public class GestioneVotiStudentePanel extends javax.swing.JPanel {
   
   /**
    * Costruisce un nuovo form GestioneVotiStudentePanel
    * @param ancestor Pannello che contiene il nuovo pannello.
    * @param studente Studente cui si riferisce il pannello.
    * @param voto Voto cui il pannello si riferisce.
    * @param materia materia cui il pannelo si riferisce.
    */
   public GestioneVotiStudentePanel (RegistroInsegnantePanel ancestor, RegistroPanel registro, StudenteBean studente, VotoBean voto, MateriaInsegnamentoBean materia, Date data) {
      this.ancestor = ancestor;
      this.registro = registro;
      this.dataVoto = data;
      this.materia = materia;
      this.studente = studente;
      this.voto = voto;
      inizializzaComponenti();
      inizializzaValori();
   }
   
   
   /**
    * Inizializza le componenti grafiche della GUI.
    */
   private void  inizializzaComponenti() {
      initComponents ();
      
      jcmbTipoVoto = new ElementiTipiVoti( ancestor.getContenitoreDati ().getTipiVoto () , false);
      jpnlAssegnaVotoStudente.setLayout(new javax.swing.BoxLayout(jpnlAssegnaVotoStudente,javax.swing.BoxLayout.X_AXIS) );
      jpnlAssegnaVotoStudente.add (jcmbTipoVoto);
      
      jcmbTipoProva = new ElementiTipiProve( ancestor.getContenitoreDati ().getTipiProve () , false );
      jpnlTipoVotoStudente.setLayout(new javax.swing.BoxLayout(jpnlTipoVotoStudente,javax.swing.BoxLayout.X_AXIS) );
      jpnlTipoVotoStudente.add (jcmbTipoProva);
   }
   
   
   public void inizializzaValori() {
      if(voto!=null) {
         System.out.println ("Inizializzazione del voto "+voto.getIdTipoVoto () );
         jcmbTipoVoto.setElementoSelezionato ( new ElementoTipoVoto( voto.getIdTipoVoto () ) );
         jcmbTipoProva.setElementoSelezionato( new ElementoTipoProva(voto.getIdTipoProva () ) );
      }
   }
   
   
   
   /** 
    * This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
   private void initComponents() {
      jbAssegnaVotoStudente = new javax.swing.JButton();
      jbCancellaVotoStudente = new javax.swing.JButton();
      jbVisualizzaTuttiVoti = new javax.swing.JButton();
      jpnlAssegnaVotoStudente = new javax.swing.JPanel();
      jpnlTipoVotoStudente = new javax.swing.JPanel();

      setBackground(new java.awt.Color(204, 204, 255));
      setBorder(javax.swing.BorderFactory.createTitledBorder("Voto"));
      setAutoscrolls(true);
      setPreferredSize(new java.awt.Dimension(200, 120));
      jbAssegnaVotoStudente.setText("Assegna");
      jbAssegnaVotoStudente.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbAssegnaVotoStudenteActionPerformed(evt);
         }
      });

      jbCancellaVotoStudente.setText("Cancella");
      jbCancellaVotoStudente.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbCancellaVotoStudenteActionPerformed(evt);
         }
      });

      jbVisualizzaTuttiVoti.setText("Visualizza tutti i Voti");
      jbVisualizzaTuttiVoti.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbVisualizzaTuttiVotiActionPerformed(evt);
         }
      });

      jpnlAssegnaVotoStudente.setOpaque(false);
      jpnlAssegnaVotoStudente.setPreferredSize(new java.awt.Dimension(100, 22));
      org.jdesktop.layout.GroupLayout jpnlAssegnaVotoStudenteLayout = new org.jdesktop.layout.GroupLayout(jpnlAssegnaVotoStudente);
      jpnlAssegnaVotoStudente.setLayout(jpnlAssegnaVotoStudenteLayout);
      jpnlAssegnaVotoStudenteLayout.setHorizontalGroup(
         jpnlAssegnaVotoStudenteLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 107, Short.MAX_VALUE)
      );
      jpnlAssegnaVotoStudenteLayout.setVerticalGroup(
         jpnlAssegnaVotoStudenteLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 22, Short.MAX_VALUE)
      );

      jpnlTipoVotoStudente.setOpaque(false);
      jpnlTipoVotoStudente.setPreferredSize(new java.awt.Dimension(100, 22));
      org.jdesktop.layout.GroupLayout jpnlTipoVotoStudenteLayout = new org.jdesktop.layout.GroupLayout(jpnlTipoVotoStudente);
      jpnlTipoVotoStudente.setLayout(jpnlTipoVotoStudenteLayout);
      jpnlTipoVotoStudenteLayout.setHorizontalGroup(
         jpnlTipoVotoStudenteLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 109, Short.MAX_VALUE)
      );
      jpnlTipoVotoStudenteLayout.setVerticalGroup(
         jpnlTipoVotoStudenteLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 22, Short.MAX_VALUE)
      );

      org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
               .add(jbVisualizzaTuttiVoti, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
               .add(layout.createSequentialGroup()
                  .add(jpnlAssegnaVotoStudente, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                  .add(jpnlTipoVotoStudente, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
               .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                  .add(jbAssegnaVotoStudente, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                  .add(jbCancellaVotoStudente, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
               .add(jpnlTipoVotoStudente, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .add(jpnlAssegnaVotoStudente, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(jbCancellaVotoStudente)
               .add(jbAssegnaVotoStudente))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jbVisualizzaTuttiVoti, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
   }// </editor-fold>//GEN-END:initComponents
   
   
   
   
   
   private void jbVisualizzaTuttiVotiActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizzaTuttiVotiActionPerformed
      new ListaVotiStudenteDialog( studente.getId (), materia.getId(),  ancestor.getContenitoreDati ()).setVisible(true);
   }//GEN-LAST:event_jbVisualizzaTuttiVotiActionPerformed

   
   
   
   private void jbCancellaVotoStudenteActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancellaVotoStudenteActionPerformed
          
      (new javax.swing.SwingWorker<Object, Object>(){
         public Object doInBackground(){
            if( voto!= null ) try {
               ancestor.setValoreUpload (-1);
               System.out.println ("Start Cancellazione "+voto.getIdTipoVoto ()+" "+ voto.getIdTipoProva () );
               voto.cancellaVoto ();
               System.out.println ("End  Cancellazione");
               ancestor.setValoreUpload (0);
               if(registro!=null) registro.aggiornaModifica (studente,voto);
               ancestor.showMessage ("Voto cancellato!");
               voto = null;
            } 
            catch (Exception ex) {
               ancestor.showMessage ("Impossibile cancellare il voto.");
               ex.printStackTrace();
            }
            return null;
         }
      }).execute();     
   }//GEN-LAST:event_jbCancellaVotoStudenteActionPerformed

   
   
   private void jbAssegnaVotoStudenteActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAssegnaVotoStudenteActionPerformed
      if(materia==null || studente==null) return;
      if (voto == null) voto = new VotoBean(); 
      
      TipoVotoBean tipoVoto = (TipoVotoBean) jcmbTipoVoto.getElementoSelezionato ();   
      TipoProvaBean tipoProva = (TipoProvaBean) jcmbTipoProva.getElementoSelezionato ();
      System.out.println ( "Param VOto: - "+tipoVoto+ "  "+tipoProva );
      if( tipoVoto == null || tipoProva == null ) { ancestor.showMessage("Selezionare i parametri del voto."); return; }
      
      voto.setIdTipoVoto ( tipoVoto.getId() );
      voto.setIdTipoProva ( tipoProva.getId () );
      voto.setIdMateria( materia.getId () );
      voto.setIdStudente( studente.getId () );
      voto.setData (dataVoto);
      voto.setIdClasse(studente.getIdClasse());
      voto.setDescrizione(Costanti.DESCRIZIONE_DUMMY);
      voto.setIdInsegnante(Costanti.ID_DOCENTE_DUMMY);
      
      //N.B Descrizione dummy da sistemare
      voto.setDescrizione(Costanti.DESCRIZIONE_DUMMY);
      
      (new javax.swing.SwingWorker<Object, Object>(){
         public Object doInBackground(){
            try { 
               ancestor.setValoreDownload (-1);
               voto.salvaSuWS();
               ancestor.showMessage ("Voto Assegnato!!");
               ancestor.setValoreDownload (0);
               if(registro!=null) registro.aggiornaModifica (studente,voto);
            } 
            catch (WSDidatticaException_Exception ex) {
               //ancestor.showMessage("Impossibile salvare il voto.");
               ancestor.showMessage(ex.getMessage());
               ex.printStackTrace();
            }
            return null;
         }
      }).execute();
      
   }//GEN-LAST:event_jbAssegnaVotoStudenteActionPerformed
   
   
   
   
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jbAssegnaVotoStudente;
   private javax.swing.JButton jbCancellaVotoStudente;
   private javax.swing.JButton jbVisualizzaTuttiVoti;
   private javax.swing.JPanel jpnlAssegnaVotoStudente;
   private javax.swing.JPanel jpnlTipoVotoStudente;
   // End of variables declaration//GEN-END:variables
   
   private ElementiTipiVoti jcmbTipoVoto;
   private ElementiTipiProve jcmbTipoProva;
   //private javax.swing.JComboBox jcmbTipoProva;
   private RegistroInsegnantePanel ancestor;
   
   /** Voto associato al panello */
   private VotoBean voto;
   
   private StudenteBean studente;
   private MateriaInsegnamentoBean materia;
   private Date dataVoto;
   private RegistroPanel registro;
   
   
   
   
   
    public static void main(String[] args) {
        String str = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";	// Windows
	//String str = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";		// Grey
	//String str = "javax.swing.plaf.metal.MetalLookAndFeel";   			// Java Metal
	try { javax.swing.UIManager.setLookAndFeel(str); }
	catch (Exception ex) { System.out.println("Failed loading Look And Feel:\n"+ex+'\n'); }
        javax.swing.JFrame temp=new javax.swing.JFrame();
        temp.setLayout(new java.awt.BorderLayout());
        
            
         StudenteBean studente = new StudenteBean ();
         try { studente.caricaDaWS (1); } 
         catch (UnsupportedOperationException ex) { ex.printStackTrace(); } 
         catch (nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception ex) { ex.printStackTrace(); }
         
         VotoBean voto = new VotoBean ();

         voto.setData (new Date (107,2,17) );
         voto.setIdTipoVoto (3);
 
         MateriaInsegnamentoBean materia = new MateriaInsegnamentoBean();
         materia.setNome ("Latino");
        
        temp.add(new GestioneVotiStudentePanel(null, null, studente, voto, materia, UtilitaGUI.getDataCorrente () ));
        temp.setSize(380,180);
        temp.setVisible(true);
    }
   
   
}
