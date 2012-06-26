/*
 * RegistroPanel.java
 *
 * Created on 7 marzo 2007, 23.41
 *
 * $Id$
 */

package nu.mine.egoless.didattica.app.gui.registroDocente;



import java.net.PortUnreachableException;
import java.util.Date;
import java.util.Vector;
import javax.swing.SwingUtilities;
import nu.mine.egoless.didattica.app.bean.AssenzeBean;
import nu.mine.egoless.didattica.app.bean.ClasseBean;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.bean.MateriaInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.StudenteBean;
import nu.mine.egoless.didattica.app.bean.StudentiBean;
import nu.mine.egoless.didattica.app.bean.VotiBean;
import nu.mine.egoless.didattica.ws.assenzaclient.Assenza;
import nu.mine.egoless.didattica.ws.assenzaclient.ParametriRicercaAssenza;
import nu.mine.egoless.didattica.ws.personaclient.Studente;
import nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaStudente;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;
import nu.mine.egoless.didattica.ws.tipovotoclient.TipoVoto;
import nu.mine.egoless.didattica.ws.votoclient.ParametriRicercaVoto;
import nu.mine.egoless.didattica.ws.votoclient.Voto;
import nu.mine.egoless.supporto.DateTimeFacade;



/**
 * Pannello che simula una pagina di registro del docente.
 * Per motivi di eficienza derivati dai Web Services,
 * si e' limitato l'arco temporale ad un singolo giorno.
 *
 * @author  Alberto Meneghello
 */
public class RegistroPanel extends javax.swing.JPanel {
   
   
   /**
    * Crea un nuovo form RegistroPanel.
    * @param ancestor Pannello che contiene il RegistroPanel.
    * @param classe Classe associata al registro.
    * @param materia Materia associata al registro.
    * @param data  Data della pagina di registro.
    */
   public RegistroPanel (RegistroInsegnantePanel ancestor, ClasseBean classe, MateriaInsegnamentoBean materia, Date data) {
      
      dataRegistro = data;
      this.classe = classe;
      this.materia = materia;
      
      registro = new Vector< Pair<Studente,Object> >();
      
      if(ancestor==null) caricaDati();  // Per il test del file
      
      this.ancestor = ancestor;
      inizializzaComponenti ();
      inizializzaValori ();
   }
   
   
   private void inizializzaComponenti () {
      initComponents ();
      jtblRegistro.setAutoCreateRowSorter (true);
      
      String stringClasse = "Null", stringMateria = "Null", stringData = UtilitaGUI.dateToString ( dataRegistro );
      if(classe!=null) stringClasse = "Classe "+ classe.getAnnoCorso() + classe.getSezione ();
      if(materia!=null) stringMateria = materia.getNome();
      
      jlblClasse.setText ( stringClasse );
      jlblMateria.setText ( stringMateria );
      jlblData.setText ( stringData );
   }
   
   
   
   /**
    * Inizializza la tabella registro della GUI con i valori 
    * appropriati, caricandoli dal database.
    */
   private void inizializzaValori () {   
      
      new javax.swing.SwingWorker<Object, Object>(){
        public Object doInBackground (){
            String ecc = null;
            
            try{
               ancestor.setTesto ("Ricerca in corso...");
               ancestor.setValoreDownload (-1);
               
               //carica gli studenti secondo i parametri di ricerca immessi
               System.out.println ("Caricamento dei dati in corso"); //DEBUG CODE
               effettuaRicerca ();
               
               //Visualizza i risultati nella tabella
               System.out.println ("Visualizza risultati"); //DEBUG CODE
               SwingUtilities.invokeLater ( new Runnable(){
                  public void run(){
                     visualizzaRisultati();
                  }
               });    
            }
            catch(WSDidatticaException_Exception e) { ecc = "Errore: Connessione al server non riuscita."; }
            catch(PortUnreachableException e) { ecc = "Errore: Porta non raggiungibile."; }
            catch(Exception e){ System.out.println (e.getClass ()); ecc = "Errore: Eccezione generica nel caricare i dati."; }
            finally{
               if(ecc != null) javax.swing.JOptionPane.showMessageDialog (registroPanel,ecc);
               else ecc = "Ricerca terminata con sucesso.";
               ancestor.setValoreDownload (0);
               ancestor.setTesto (ecc);
               System.out.println (ecc);
               return null;
            }
         } //doInBackground
      }.execute (); //Swing Worker  */
   }
   
   
   
   /**
    * Effettua una ricerca per prelevare la lista di studenti
    * della classe selezionata.
    * Al termine della funzione studentiBean contiene tutti
    * gli studenti (Studente) della classe.
    */
   private void effettuaRicerca () throws PortUnreachableException,nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception {
      synchronized(classe){ //evita problemi se l'utente schiaccia nuovamente ricerca prima che quella precedente sia finita
         registro = new Vector< Pair<Studente,Object> >();
         
         // Ricerca Studenti
         //  seleziona tuti gli studenti appartenenti ad una specifica classe
         ParametriRicercaStudente parametriRicercaStudente = new ParametriRicercaStudente ();
         parametriRicercaStudente.setIdClasse ( classe.getId () );
         //  carica gli studenti
         StudentiBean studentiBean = new StudentiBean ();
         studentiBean.caricaStudenti (parametriRicercaStudente);
         int num = studentiBean.ritornaNumeroDiStudenti ();
         
         // Ricerca Assenze e Voti
         //  seleziona i voti in base ai criteri
         ParametriRicercaVoto parametriVoto = new ParametriRicercaVoto ();
         parametriVoto.setIdMateria ( materia.getId () );
         //parametriVoto.setDataOra(dataRegistro);
         //  seleziona le assenze in base ai criteri
         ParametriRicercaAssenza parametriAssenza = new ParametriRicercaAssenza ();
         
         nu.mine.egoless.didattica.ws.assenzaclient.Date dataRegistroWS=new nu.mine.egoless.didattica.ws.assenzaclient.Date();
         dataRegistroWS.setDate (DateTimeFacade.Date2String (dataRegistro));
         parametriAssenza.setData (dataRegistroWS);
         
         // per ogni studente associa voto o assenza.
         for(int i=0; i< num; i++) {
            ancestor.setValoreDownload ( primoCaricamento+(100-primoCaricamento)+i/num );
            
            Object dato = null;
            
            Studente studente = studentiBean.getStudenteAt (i);
            parametriVoto.setIdStudente ( studente.getId () );
            VotiBean voti = new VotiBean ();
            try { voti.caricaVoti ( parametriVoto ); System.out.println ("Voti caricati: "); }
            catch (PortUnreachableException ex) { System.out.println ("Ecc. Carica Voti: Port "+ex.getMessage ()); }
            //catch (WSDidatticaException_Exception ex) { System.out.println ("Ecc. Carica Voti WS: "+ex.getMessage ()); }
            catch(Exception e) { System.out.println ("Ecc. Carica Voti: Altro "+e.getClass ()); }
            System.out.println ("...saranno stati caricati i voti? ");
            
            if( voti.ritornaNumeroDiVoti ()>0 )
                dato = voti.getVotoAt (0);
            
            if( dato == null )  // Puo' esserci un assenza solo se non c'e' gia' un voto
            {
               parametriAssenza.setIdStudente ( studente.getId () );
               AssenzeBean assenze = new AssenzeBean ();
               
               try {  
                  assenze.caricaAssenze ( parametriAssenza );  
               } catch(Exception e) { 
                  System.out.println ("Ecc Carica Assenze "); 
               }
               
               if( assenze.ritornaNumeroDiAssenze ()>0 ) {
                   dato = assenze.getAssenzaAt (0);
               }
            }
            
            registro.add( new Pair<Studente, Object>( studentiBean.getStudenteAt (i), dato ) );
         }  
      }
   }
   
   
   /**
    * Riempie la lista.
    */
   private void visualizzaRisultati () { 
      synchronized(ancestor){
         ancestor.setValoreDownload ( -1 );
       
         for(int i=0; i< registro.size(); i++) {
            Object[] riga = new Object[4];

            Studente studente = registro.elementAt (i).getPrimo ();
            riga[0] = studente.getMatricola ();
            riga[1] = studente.getCognome ();
            riga[2] = studente.getNome ();
            
            String etichetta;
            Object dato = registro.elementAt (i).getSecondo ();
            
            if(dato instanceof Voto) {
               TipoVoto voto = UtilitaGUI.getTipoVoto (ancestor.getContenitoreDati ().getTipiVoto(), ((Voto)dato).getTipoVotoId () );
               etichetta = voto.getNome ()+" "+voto.getValore ();
            }
            else 
               if(dato instanceof Assenza) etichetta = ETICHETTA_ASSENZA;
            else 
               etichetta = "-";
            
            riga[3] = etichetta;
            
            UtilitaGUI.inserisciRiga (jtblRegistro,i,riga);
         }
         
         ancestor.setValoreDownload ( 0 );
         ancestor.setTesto ("Registro visualizzato"); 
      }
   }
   
   
   /**
    * Funzione richiamata quando si aggiornano i valori (dato) del 
    * registro (assenze o voti).
    */
   public void aggiornaModifica(StudenteBean studente, Object dato) {
      /*for(int i=0; i<registro.size(); i++)
         if( ((Studente)((Pair)(this.registro.elementAt (i))).getPrimo ()).getId() == studente.getId()) {
            System.out.println ("Aggiornato il dato registro!!! ");
         }
      visualizzaRisultati(); */
   }
   
   
   /**
    * This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
   private void initComponents() {
      jscpRegistro = new javax.swing.JScrollPane();
      jtblRegistro = new javax.swing.JTable();
      jbVisualizzaDatiStudente = new javax.swing.JButton();
      jbChiudiRegistro = new javax.swing.JButton();
      jpnlTitoloRegistro = new javax.swing.JPanel();
      jlblClasse = new javax.swing.JLabel();
      jlblData = new javax.swing.JLabel();
      jlblMateria = new javax.swing.JLabel();

      setBackground(new java.awt.Color(204, 204, 255));
      setBorder(javax.swing.BorderFactory.createTitledBorder("Registro"));
      jscpRegistro.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());
      jtblRegistro.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {

         },
         new String [] {
            "Matricola", "Cognome", "Nome", "Voto/Assenza"
         }
      ) {
         Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
      jscpRegistro.setViewportView(jtblRegistro);

      jbVisualizzaDatiStudente.setMnemonic('V');
      jbVisualizzaDatiStudente.setText("Visualizza Dati");
      jbVisualizzaDatiStudente.setPreferredSize(new java.awt.Dimension(100, 26));
      jbVisualizzaDatiStudente.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbVisualizzaDatiStudenteActionPerformed(evt);
         }
      });

      jbChiudiRegistro.setText("Chiudi Registro");
      jbChiudiRegistro.setPreferredSize(new java.awt.Dimension(100, 26));
      jbChiudiRegistro.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbChiudiRegistroActionPerformed(evt);
         }
      });

      jpnlTitoloRegistro.setBackground(new java.awt.Color(102, 153, 255));
      jpnlTitoloRegistro.setPreferredSize(new java.awt.Dimension(30, 30));
      jlblClasse.setFont(new java.awt.Font("Tahoma", 0, 18));
      jlblClasse.setForeground(new java.awt.Color(255, 255, 255));
      jlblClasse.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
      jlblClasse.setText("Classe 5 B");
      jlblClasse.setMaximumSize(new java.awt.Dimension(10000, 28));
      jlblClasse.setMinimumSize(new java.awt.Dimension(28, 28));

      jlblData.setFont(new java.awt.Font("Tahoma", 0, 18));
      jlblData.setForeground(new java.awt.Color(255, 255, 255));
      jlblData.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
      jlblData.setText("22 Marzo 2007");
      jlblData.setMaximumSize(new java.awt.Dimension(10000, 28));
      jlblData.setMinimumSize(new java.awt.Dimension(28, 28));

      jlblMateria.setFont(new java.awt.Font("Tahoma", 0, 18));
      jlblMateria.setForeground(new java.awt.Color(255, 255, 255));
      jlblMateria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      jlblMateria.setText("Matematica");
      jlblMateria.setMaximumSize(new java.awt.Dimension(10000, 28));
      jlblMateria.setMinimumSize(new java.awt.Dimension(28, 28));

      org.jdesktop.layout.GroupLayout jpnlTitoloRegistroLayout = new org.jdesktop.layout.GroupLayout(jpnlTitoloRegistro);
      jpnlTitoloRegistro.setLayout(jpnlTitoloRegistroLayout);
      jpnlTitoloRegistroLayout.setHorizontalGroup(
         jpnlTitoloRegistroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jpnlTitoloRegistroLayout.createSequentialGroup()
            .addContainerGap()
            .add(jlblClasse, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 114, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(24, 24, 24)
            .add(jlblMateria, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jlblData, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
      );
      jpnlTitoloRegistroLayout.setVerticalGroup(
         jpnlTitoloRegistroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jpnlTitoloRegistroLayout.createSequentialGroup()
            .add(jpnlTitoloRegistroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(jlblMateria, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(jlblData, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(jlblClasse, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
            .addContainerGap())
      );

      org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
               .add(org.jdesktop.layout.GroupLayout.LEADING, jscpRegistro, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
               .add(org.jdesktop.layout.GroupLayout.LEADING, jpnlTitoloRegistro, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
               .add(layout.createSequentialGroup()
                  .add(jbVisualizzaDatiStudente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                  .add(jbChiudiRegistro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .add(jpnlTitoloRegistro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jscpRegistro, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(jbChiudiRegistro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(jbVisualizzaDatiStudente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
      );
   }// </editor-fold>//GEN-END:initComponents
   
   
   
   /**/
   private void jbVisualizzaDatiStudenteActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizzaDatiStudenteActionPerformed
      int[] rows = jtblRegistro.getSelectedRows ();
      for(int i=0; i<rows.length; i++) {
         Studente studente = (Studente) registro.elementAt (rows[i]).getPrimo ();
         Object dato = registro.elementAt (rows[i]).getSecondo ();
         System.out.println ("->->"+i+" "+rows[i]+" ECCO : "+studente+"  "+studente.getId () );
         ancestor.apriNuovoGestioneDatiStudente (this, materia,studente,dato,dataRegistro);
      }
   }//GEN-LAST:event_jbVisualizzaDatiStudenteActionPerformed
   
   
   
   private void jbChiudiRegistroActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChiudiRegistroActionPerformed
      if (ancestor != null )
         ancestor.chiudiRegistro (this);
   }//GEN-LAST:event_jbChiudiRegistroActionPerformed
   
   
   
   
   public boolean corrispondeA (ClasseBean classe, MateriaInsegnamentoBean materia, Date data) {
      //return this.classe.getId() == classe.getId()  &&
         //this.materia.getId() == materia.getId() &&
         //this.dataRegistro.equals (data);
      return  this.classe.equals (classe) &&
         this.materia.equals (materia) &&
         this.dataRegistro.equals (data);
   }
   
   
   
   
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jbChiudiRegistro;
   private javax.swing.JButton jbVisualizzaDatiStudente;
   private javax.swing.JLabel jlblClasse;
   private javax.swing.JLabel jlblData;
   private javax.swing.JLabel jlblMateria;
   private javax.swing.JPanel jpnlTitoloRegistro;
   private javax.swing.JScrollPane jscpRegistro;
   private javax.swing.JTable jtblRegistro;
   // End of variables declaration//GEN-END:variables
   
   private RegistroInsegnantePanel ancestor;
   private RegistroPanel registroPanel;
   
   // Costanti
   private static final int primoCaricamento = 15;
   private static final String ETICHETTA_ASSENZA = "A";
   
   // Parametri del registro
   private Date dataRegistro;
   private MateriaInsegnamentoBean materia;
   private ClasseBean classe;
   
   // Struttura dati del registro
   private Vector <Pair <Studente, Object> > registro;
   
   
   
   
   
   private void caricaDati() {
      ClassiBean classi = new ClassiBean();
      try {
         classi.caricaClassi ();
      } catch (PortUnreachableException ex) {
         ex.printStackTrace();
      } catch (nu.mine.egoless.didattica.ws.classesupport.WSDidatticaException_Exception ex) {
         ex.printStackTrace();
      }
      classe = new ClasseBean ( classi.getClasseAt (0) );
      
      materia = new MateriaInsegnamentoBean(0);
      materia.setNome ("Matematica");
   }
   
   
   
   /**
    * Avvia un'anteprima dell'interfaccia grafica.
    */
   public static void main (String[] args) {
      String str = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";	// Windows
      //String str = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";		// Grey
      //String str = "javax.swing.plaf.metal.MetalLookAndFeel";   			// Java Metal
      try { javax.swing.UIManager.setLookAndFeel (str); } catch (Exception ex) { System.out.println ("Failed loading Look And Feel:\n"+ex+'\n'); }
      javax.swing.JFrame temp=new javax.swing.JFrame ();
      temp.setLayout (new java.awt.BorderLayout ());
      
      temp.add (new RegistroPanel (null,null,null,UtilitaGUI.getDataCorrente ()));
      temp.setSize (600,400);
      temp.setVisible (true);
   }
   
   
}
   




   
class Pair<Tipo1, Tipo2>
{
   private Tipo1 primo;
   private Tipo2 secondo;
   
   public Pair(Tipo1 primo, Tipo2 secondo)
   {
      this.primo = primo;
      this.secondo = secondo;
   }
   
   public void setPrimo(Tipo1 primo) { this.primo = primo; }
   public Tipo1 getPrimo() { return primo; }
   
   public void setSecondo(Tipo2 secondo) { this.secondo = secondo; }
   public Tipo2 getSecondo() { return secondo; }
   
   public boolean equals(Object o) {
      return ( o instanceof Pair && ((Pair)o).getPrimo ().equals(this.getPrimo()) );
   }
   
}
   

