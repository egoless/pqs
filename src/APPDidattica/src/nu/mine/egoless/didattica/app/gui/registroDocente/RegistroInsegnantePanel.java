/*
 * RegistroInsegnantePanel.java
 *
 * Created on 8 marzo 2007, 23.36
 *
 * $Id$
 */

package nu.mine.egoless.didattica.app.gui.registroDocente;



import java.util.Date;
import nu.mine.egoless.didattica.app.bean.ClasseBean;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.bean.MateriaInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.NazioniBean;
import nu.mine.egoless.didattica.app.bean.ReligioniBean;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.app.bean.TipiVotiBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.BarraDiStatoPanel;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ContenitoreDati;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import nu.mine.egoless.didattica.ws.personaclient.Studente;





/**
 * Interfaccia grafica da cui un docente pu� gestire una lezione.
 *
 * @author  Alberto Meneghello
 */
public class RegistroInsegnantePanel extends javax.swing.JPanel {
   
   
   /**
    * Crea un  nuovo form RegistroInsegnantePanel
    * @param contenitoreDati Oggetto da cui preleva i dati semplici
    * fissi, precaricati al caricamento dell'applicazione.
    */
   public RegistroInsegnantePanel (ContenitoreDati contenitoreDati) {
      this.contenitoreDati = contenitoreDati;
      if(contenitoreDati==null) caricaDati();
      inizializzaComponenti ();
   }
   
   
   /**
    * Inizializza le componenti della GUI.
    */
   private void inizializzaComponenti () {
      initComponents();
      add (new SelezionaRegistroPanel(this) , "North" );
      barraStato = new BarraDiStatoPanel();
      add( barraStato, "South");
   }
   
   
   /** 
    * This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
   private void initComponents() {
      jsplRegistroInsegnante = new javax.swing.JSplitPane();
      jtbpDatiStudente = new javax.swing.JTabbedPane();
      jtbpRegistri = new javax.swing.JTabbedPane();

      setLayout(new java.awt.BorderLayout());

      setBackground(new java.awt.Color(153, 153, 255));
      jsplRegistroInsegnante.setBackground(new java.awt.Color(153, 153, 255));
      jsplRegistroInsegnante.setResizeWeight(0.7);
      jtbpDatiStudente.setBackground(new java.awt.Color(204, 204, 255));
      jtbpDatiStudente.setOpaque(true);
      jsplRegistroInsegnante.setRightComponent(jtbpDatiStudente);

      jtbpRegistri.setBackground(new java.awt.Color(213, 213, 252));
      jtbpRegistri.setOpaque(true);
      jsplRegistroInsegnante.setLeftComponent(jtbpRegistri);

      add(jsplRegistroInsegnante, java.awt.BorderLayout.CENTER);

   }// </editor-fold>//GEN-END:initComponents
   
 
   
   
   
   
   
   
    /**
     * Apre nel tab sinistro una nuova pagina che visualizza il registro docente
     * corrispondente ai parametri selezionati.
     * @param classe Oggetto ClasseBean che modella la classe a cui si riferisce il registro.
     * @param materia Materia a cui si riferisce il registro.
     * @param data Data della pagina del registro.
     */
    public void apriNuovoRegistro( ClasseBean classe, MateriaInsegnamentoBean materia, Date data ) {
       if( !isApertoRegistro ( classe, materia, data) ) {
          RegistroPanel registro = new RegistroPanel(this, classe, materia, data);
          jtbpRegistri.addTab ("Registro", registro );
          UtilitaGUI.initTabComponent (jtbpRegistri, registro , "Registro "+materia.getNome () );
          jtbpRegistri.setSelectedComponent (registro);
       }
    }
    
      
    /**
     * Controlla se una pagina di registro e' gia' aperta.
     * @param classe Identifica la pagina da controlllare.
     * @param materia Identifica la pagina da controlllare.
     * @param data Identifica la pagina da controlllare.
     * @returns true Ritorna true se e solo se e' gia aperta
     * una pagina per i parametri selezionati.
     */
    public boolean isApertoRegistro( ClasseBean classe, MateriaInsegnamentoBean materia, Date data ){
        for(int i=0; i<jtbpRegistri.getTabCount(); i++){
            javax.swing.JPanel pannello = (javax.swing.JPanel) jtbpRegistri.getComponentAt(i);
            if( pannello instanceof RegistroPanel && 
               ((RegistroPanel)pannello).corrispondeA(classe, materia, data) ) {
                  jtbpRegistri.setSelectedIndex(i);
                  return true;
            }
        }
        return false;
    }
    
    
    /**
     * Chiude la pagina di registro corrente.
     * @param registro Pagina registro da chiudere.
     */
    public void chiudiRegistro(RegistroPanel registro) //ParametriRegistro param )
    {
       jtbpRegistri.remove(registro);
    }
   
   
    
    
    
    /**
     * Apre una nuova pagina nel tab destro, contentente i dati dello studente.
     * @param materia Materia del registro.
     * @param studente Studente di cui visualizzare i dati.
     * @param dato Dato didattico associato allo studente (assenza, voto o null).
     * @param data Data del giorno cui il registro si riferisce.
     */
    public void apriNuovoGestioneDatiStudente( RegistroPanel registro, MateriaInsegnamentoBean materia, Studente studente, Object dato, Date data) {
       if( studente!=null && !isApertoGestioneDatiStudente(studente) ) {
          String nomeStudente = studente.getCognome () +" "+studente.getNome ();
          GestioneDatiStudentePanel gestioneStudente = new GestioneDatiStudentePanel(this, registro, materia, studente, dato, data);
          jtbpDatiStudente.addTab (nomeStudente, gestioneStudente ); // this, param) );
          UtilitaGUI.initTabComponent (jtbpDatiStudente, gestioneStudente , "" );
          jtbpDatiStudente.setSelectedComponent (gestioneStudente);
       }
    }
    
    
    /**
     * Controlla se una pagina dedicata da uno studente e' gia' aperta.
     * @param studente Lo studente da controllare.
     * @returns true Ritorna true se e solo se e' gia aperta
     * una pagina dedicata allo studente
     */
    public boolean isApertoGestioneDatiStudente(Studente studente) {
        for(int i=0; i<jtbpDatiStudente.getTabCount(); i++){
            javax.swing.JPanel pannello = (javax.swing.JPanel) jtbpDatiStudente.getComponentAt(i);
            if( pannello instanceof GestioneDatiStudentePanel && 
               ((GestioneDatiStudentePanel)pannello).corrispondeA(studente) ){
                  jtbpDatiStudente.setSelectedIndex(i);
                  return true;
            }
        }
        return false;
    }
 
    
    /**
     * Chiude una pagina dei dati dello studente (quella del tab destro).
     * @param pannello Pannello da chiudere.
     */
    public void chiudiGestioneDatiStudente(GestioneDatiStudentePanel pannello) {
       jtbpDatiStudente.remove(pannello);
    }
    

    
      
    
    
    
    /**
     * Setta il testo della Status Bar.
     * @param testo Stringa di testo da visualizzare.
     */
    public void setTesto(String testo) {
       barraStato.setTesto (testo);
    }
    
    
    /**
     * Setta il valore della barra di Download ad un certo valore.
     * @param n Il valora a cui settare la barra.
     */
    public void setValoreDownload(int n){
       if (n<0) barraStato.setDownloadIndeterminato(true);
       else { barraStato.setDownloadIndeterminato(false); barraStato.setValoreDownload(n); }
    }
    
    
    /**
     * Setta il valore della barra di Upload ad un certo valore.
     * @param n Il valora a cui settare la barra.
     */
    public void setValoreUpload(int n){
       if (n<0) barraStato.setUploadIndeterminato(true);
       else { barraStato.setUploadIndeterminato(false); barraStato.setValoreUpload(n); }
    }
    

    
    
    /**
     * Visualizza un mesaggio di warning riportante un messaggio.
     * @param string Testo del messaggio da visualizzare;
     * se null viene impostato di default il messaggio
     * "Impossibile completare l'operazione.".
     */
    public void showMessage(String string) {
       if(string == null) string = "Impossibile completare l'operazione.";
       javax.swing.JOptionPane.showMessageDialog(this,string);
    }
   
    
    
    
    /**
     * Ritorna un contenitore di dati semplici,
     * precaricati all'inizio dell'applicazione.
     */
    public ContenitoreDati getContenitoreDati() {
       return contenitoreDati;
    }
   
   
   
   
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JSplitPane jsplRegistroInsegnante;
   private javax.swing.JTabbedPane jtbpDatiStudente;
   private javax.swing.JTabbedPane jtbpRegistri;
   // End of variables declaration//GEN-END:variables
   
   private BarraDiStatoPanel barraStato;
   private ContenitoreDati contenitoreDati;
   
    
    /**
     * Avvia un'anteprima dell'interfaccia grafica.
     */
    public static void main(String[] args) {
        String str = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	try { javax.swing.UIManager.setLookAndFeel(str); }
	catch (Exception ex) { System.out.println("Failed loading Look And Feel:\n"+ex+'\n'); }
        javax.swing.JFrame temp=new javax.swing.JFrame();
        temp.setLayout(new java.awt.BorderLayout());
        temp.add(new RegistroInsegnantePanel( null ));
        temp.setSize(800,600);
        temp.setVisible(true);
    }
    
    
    
    
    private boolean caricaDati() {
       
        NazioniBean nazioniBean = null;
        ReligioniBean religioniBean = null;   
        ClassiBean classiBean = null;
        MaterieInsegnamentoBean materieBean = null; 
        TipiVotiBean tipiVotiBean = null;
        TipiAssenzeBean tipiAssenzeBean = null;
       
        //inizializza le variabili locali
        religioniBean = new ReligioniBean();        
        nazioniBean = new NazioniBean();      
        classiBean = new ClassiBean();
        materieBean = new MaterieInsegnamentoBean();        
        tipiVotiBean = new TipiVotiBean();
        tipiAssenzeBean = new TipiAssenzeBean();
       
       
       
         boolean ecc = true; //traccia il fallimento nel caricamento di qualche dato
         int step = 100/6; //passo di aumento della barra di progresso
         String missed = ""; //traccia i dati che non si � riusciti a caricare
         System.out.println("GuiFrame: Caricamento Dati Iniziali") ;
         try{
             System.out.println("Inizio del caricamento della lista delle religioni");
             religioniBean.caricaReligioni();
         }catch(Exception e){missed += "religioni; "; System.out.println("Fallito il caricamento della lista delle religioni"); ecc=false;}
         try{
            System.out.println("Inizio del caricamento della lista dellle nazioni");
            nazioniBean.caricaNazioni();
         }catch(Exception e){missed += "nazioni; "; System.out.println("Fallito il caricamento della lista dellle nazioni"); ecc=false;}

         try{
            System.out.println("Inizio del caricamento della lista classi");
            classiBean.caricaClassi();
         }catch(Exception e){missed += "classi; "; System.out.println("Fallito il caricamento della lista classi"); ecc=false;} 
         try{
            System.out.println("Inizio del caricamento della lista materie");
            materieBean.caricaMaterie();
         }catch(Exception e){missed += "materie; "; System.out.println("Fallito il caricamento della lista delle materie"); ecc=false;}
         try{
            System.out.println("Inizio del caricamento della lista dei tipi di voto");
            tipiVotiBean.caricaTipiVoti();
         }catch(Exception e){missed += "tipi di voto; "; System.out.println("Fallito il caricamento della lista dei tipi di voto"); ecc=false;}
         try{
            System.out.println("Inizio del caricamento della lista dei tipi di assenza");
            tipiAssenzeBean.caricaTipiAssenze();
         }catch(Exception e){missed += "tipi di assenze; "; System.out.println("Fallito il caricamento della lista dei tipi di assenza"); ecc=false;}         
         if(ecc) System.out.println("Caricamento completato con successo");            
         else { //se � fallito i caricamento di qualche dato
             System.out.println("GUIFrame: Errore di Caricamento: "+missed);
         }
         
         
         contenitoreDati  = new ContenitoreDati(religioniBean, nazioniBean, classiBean, materieBean, tipiVotiBean, tipiAssenzeBean, null);
         
         return ecc;
    }        
    
    
    
   
}
