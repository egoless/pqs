/*
 * VisualizzaDatiStudentePanel.java
 *
 * Created on 9 marzo 2007, 3.19
 *
 * $Id$
 */

package nu.mine.egoless.didattica.app.gui.registroDocente;


import java.util.Date;
import nu.mine.egoless.didattica.app.bean.ContattoBean;
import nu.mine.egoless.didattica.app.bean.StudenteBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import nu.mine.egoless.didattica.ws.nazioneclient.Nazione;
import nu.mine.egoless.didattica.ws.religioneclient.Religione;





/**
 * Pannello che visualizza nell'interfaccia degli insegnanti
 * tuti i dati di uno specifico studente.
 * @author  Admin
 */
public class VisualizzaDatiStudentePanel extends javax.swing.JPanel {
   
   
   /**
    * Crea un nuovo pannello che visualizza i dati di uno
    * specifico studente.
    * @param Studente di cui visualizzare i dati.
    */
   public VisualizzaDatiStudentePanel (RegistroInsegnantePanel ancestor, StudenteBean studente) {
      this.ancestor = ancestor;
      this.studente = studente;
      initComponents ();
      setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS) );
      update();
   }
   
   
   /**
    * Aggiorna l'interfaccia grafica con la visualizzazione dei 
    * dati dello studente.
    */
   public void update(){

      ContattoBean contattoBean = new ContattoBean();
      try { contattoBean.caricaDaWS ( studente.getIdIndirizzoResidenza () ); }
      catch(Exception e) { System.out.println ("Ecezione nel caricare il contatto"); }

      String dataNascita = "-", nomeReligione="-", nomeNazione="-";
      if(studente.getDataNascita() != null) dataNascita =  UtilitaGUI.dateToString ( studente.getDataNascita() );
      Religione religione = UtilitaGUI.getReligione( ancestor.getContenitoreDati().getReligioni(), studente.getIdReligione () );
      Nazione nazione = UtilitaGUI.getNazione( ancestor.getContenitoreDati().getNazioni(), studente.getIdNazionalita() );
      if(religione != null) nomeReligione = religione.getNome();
      if(nazione != null) nomeNazione = nazione.getNome();
      //aggiorna la stringa da immettere nella text area
      String anteprima = new String(
         "Matricola: " +studente.getMatricola () +"\n"+
         "Cognome: " +studente.getCognome()+"\n"+
         "Nome: " +studente.getNome()+"\n"+
         "Data di Nascita: " +dataNascita+"\n"+
         "Codice Fiscale: " +studente.getCodiceFiscale()+"\n\n"+
         "Portatore di Handicap: " +UtilitaGUI.valoreBoolean((new Boolean(studente.isPortatoreHandicap())))+"\n\n"+
         "Religione: " +nomeReligione+"\n"+
         "Nazionalita: " +nomeNazione+"\n\n"+
         "Via: " +contattoBean.getVia()+"\n"+
         "Civico: " +contattoBean.getCivico()+"\n"+
         "C.A.P.: " +contattoBean.getCAP()+"\n"+
         "Provincia: " +contattoBean.getProvincia()+"\n"+
         "Telefono Principale: " +contattoBean.getTelefonoPrincipale()+"\n"+
         "Telefono Secondario: " +contattoBean.getTelefonoSecondario()+"\n"+
         "Fax: " +contattoBean.getFax()+"\n"
      );
      
      jtaDatiStudente.append(anteprima); 
   }
   
   
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
   private void initComponents() {
      jscpDatiStudente = new javax.swing.JScrollPane();
      jtaDatiStudente = new javax.swing.JTextArea();

      setBackground(new java.awt.Color(204, 204, 255));
      setBorder(javax.swing.BorderFactory.createTitledBorder("Dati dello Studente"));
      jtaDatiStudente.setColumns(20);
      jtaDatiStudente.setEditable(false);
      jtaDatiStudente.setFont(new java.awt.Font("Monotype Corsiva", 0, 16));
      jtaDatiStudente.setForeground(new java.awt.Color(0, 0, 102));
      jtaDatiStudente.setRows(5);
      jtaDatiStudente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      jtaDatiStudente.setMargin(new java.awt.Insets(5, 5, 5, 5));
      jscpDatiStudente.setViewportView(jtaDatiStudente);

      org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(jscpDatiStudente, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .add(jscpDatiStudente, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
            .addContainerGap())
      );
   }// </editor-fold>//GEN-END:initComponents
   
   
   
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JScrollPane jscpDatiStudente;
   private javax.swing.JTextArea jtaDatiStudente;
   // End of variables declaration//GEN-END:variables
   
   private RegistroInsegnantePanel ancestor;
   
   /** Studente a cui si riferiscono i dati da visualizzare */
   private StudenteBean studente;
   
   
    /**
     * Avvia un'anteprima dell'interfaccia grafica.
     */
    public static void main(String[] args) {
        String str = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";	// Windows
	//String str = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";		// Grey
	//String str = "javax.swing.plaf.metal.MetalLookAndFeel";   			// Java Metal
	try { javax.swing.UIManager.setLookAndFeel(str); }
	catch (Exception ex) { System.out.println("Failed loading Look And Feel:\n"+ex+'\n'); }
        javax.swing.JFrame temp=new javax.swing.JFrame();
        temp.setLayout(new java.awt.BorderLayout());
        
        StudenteBean studente = new StudenteBean();
        studente.setMatricola ("518667");
        studente.setNome ("Alberto");
        studente.setCognome ("Meneghello");
        studente.setCodiceFiscale ("MNGLRT85L08L585S");
        studente.setDataNascita (new Date(85,6,8));
        
        temp.add(new VisualizzaDatiStudentePanel(null, studente));
        temp.setSize(300,400);
        temp.setVisible(true);
    }
   
   
}