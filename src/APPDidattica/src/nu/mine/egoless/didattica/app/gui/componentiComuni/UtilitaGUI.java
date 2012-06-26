/*
 * UtilitaGUI.java
 *
 * Created on 13 marzo 2007, 16.36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni;


import nu.mine.egoless.didattica.app.bean.PersonaBean;
import nu.mine.egoless.didattica.app.bean.ReligioniBean;
import nu.mine.egoless.didattica.app.bean.NazioniBean;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.app.bean.TipiProveBean;
import nu.mine.egoless.didattica.app.bean.TipiVotiBean;
import nu.mine.egoless.didattica.ws.religioneclient.Religione;
import nu.mine.egoless.didattica.ws.nazioneclient.Nazione;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean;
import nu.mine.egoless.didattica.ws.classesupport.Classe;
import nu.mine.egoless.didattica.ws.materiaclient.MateriaInsegnamento;
import javax.swing.JOptionPane;
import nu.mine.egoless.didattica.ws.nazioneclient.Nazione;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.TipoAssenza;
import nu.mine.egoless.didattica.ws.tipoprovaclient.TipoProva;
import nu.mine.egoless.didattica.ws.tipovotoclient.TipoVoto;


/**
 *
 * @author  Lorenzo Daniele
 * @author  Alberto Meneghello
 *
 * Classe ove vengono raccolti metodi di utilità per l'implementazione della GUI
 */
public class UtilitaGUI {
    
    /** Creates a new instance of UtilitaGUI */
    public UtilitaGUI() {
    }

    /**
     * associa al pannello passato come parametro un bottone che ne permette la chiusura
     */
    public static void initTabComponent(javax.swing.JTabbedPane jtabp, javax.swing.JPanel jpnl, String title){
        int i = jtabp.indexOfComponent(jpnl); 
        if(i<0) return;
        jtabp.setTabComponentAt(i, new ButtonTabComponent(title, jtabp));
    }    
    
    /* 
     *mostra un messaggio tramite un dialog
     */
    public static void messaggioWSNonRaggiungibile(java.awt.Component parentComponent){
        JOptionPane.showMessageDialog(parentComponent, "Il Server non è raggiungibile!");
    }
    
      /** 
       * Dato un id ritorna la relativa Religione dal ReligioniBean, null se non esiste 
       *  Il primo argomento non deve essere a null 
       */
    public static Religione getReligione(ReligioniBean religioniBean, int id ){
        if(religioniBean==null) { throw new IllegalArgumentException(); }
        for(int i=0; i<religioniBean.ritornaNumeroDiReligioni(); i++){
            Religione religione = religioniBean.getReligioneAt(i);
            if(religione.getId()==id)
                return religione;
        }
        return null;
    }
    
    /** 
     * Data una string ritorna la relativa Religione dal ReligioniBean, null se non esiste 
     * Il primo argomento non deve essere a null 
     */
    public static Religione getReligione(ReligioniBean religioniBean, String religione ){  
        if(religioniBean==null) { throw new IllegalArgumentException(); }       
        //if(religioniBean==null) System.out.println("ERRORE");
        for(int i=0; i<religioniBean.ritornaNumeroDiReligioni(); i++){
            Religione r = religioniBean.getReligioneAt(i);
            if(r.getNome().equals(religione))
                return r;
        }
        return null;      
    }

    /** 
     * Dato un id ritorna la relativa Nazione dal NazioniBean, null se non esiste 
     * Il primo argomento non deve essere a null 
     */
    public static Nazione getNazione(NazioniBean nazioniBean, int id ){
        if(nazioniBean==null) { throw new IllegalArgumentException(); }      
        for(int i=0; i<nazioniBean.ritornaNumeroDiNazioni(); i++){
            Nazione nazione = nazioniBean.getNazioneAt(i);
            if(nazione.getId()==id)
                return nazione;
        }
        return null;
    }    
    
    /** 
     * Data una string ritorna la relativa Nazione dal NazioniBean, null se non esiste 
     * Il primo argomento non deve essere a null 
     */
    public static Nazione getNazione(NazioniBean nazioniBean, String nazione ){  
        if(nazioniBean==null) { throw new IllegalArgumentException(); }      
        //if(religioniBean==null) System.out.println("ERRORE");
        for(int i=0; i<nazioniBean.ritornaNumeroDiNazioni(); i++){
            Nazione n = nazioniBean.getNazioneAt(i);
            if(n.getNome().equals(nazione))
                return n;
        }
        return null;      
    }    

    /** Dato un id ritorna la relativa Classe dal ClassiBean, null se non esiste 
     *  Il primo argomento non deve essere a null 
     */
    public static Classe getClasse(ClassiBean classiBean, int id ){
        if(classiBean==null) { throw new IllegalArgumentException(); }      
        for(int i=0; i<classiBean.ritornaNumeroDiClassi(); i++){
            Classe classe = classiBean.getClasseAt(i);
            if(classe.getId()==id)
                return classe;
        }
        return null;
    }    
    
    /** 
     * Dato anno e sezione ritorna la relativa Classe dal ClassiBean, null se non esiste 
     * Il primo argomento non deve essere a null 
     */
    public static Classe getClasse(ClassiBean classiBean, int anno, char sezione ){  
        if(classiBean==null) { throw new IllegalArgumentException(); }      
        //if(religioniBean==null) System.out.println("ERRORE");
        for(int i=0; i<classiBean.ritornaNumeroDiClassi(); i++){
            Classe c = classiBean.getClasseAt(i);
            if(c.getAnnoCorso() == anno && c.getSezione() == sezione)
                return c;
        }
        return null;      
    }        

    /** 
     * Dato un id ritorna la relativa MateriaInsegnamento dal MaterieInsegnamentoBean, null se non esiste 
     * Il primo argomento non deve essere a null 
     */
    public static MateriaInsegnamento getMateriaInsegnamento(MaterieInsegnamentoBean materieBean, int id ){
        if(materieBean==null) { throw new IllegalArgumentException(); }      
        for(int i=0; i<materieBean.ritornaNumeroDiMaterie(); i++){
            MateriaInsegnamento materia = materieBean.getMateriaAt(i);
            if(materia.getId()==id)
                return materia;
        }
        return null;
    }    
    
    /** 
     * Data una string ritorna la relativa MateriaInsegnamento dal MaterieInsegnamentoBean, null se non esiste 
     *  Il primo argomento non deve essere a null 
     */
    public static MateriaInsegnamento getMateriaInsegnamento(MaterieInsegnamentoBean materieBean, String materia ){  
        if(materieBean==null) { throw new IllegalArgumentException(); }      
        //if(religioniBean==null) System.out.println("ERRORE");
        for(int i=0; i<materieBean.ritornaNumeroDiMaterie(); i++){
            MateriaInsegnamento m = materieBean.getMateriaAt(i);
            if(m.getNome().equals(materia))
                return m;
        }
        return null;      
    } 
    
    
    
      /** 
       * Dato un id ritorna la relativa {@link TipoAssenza} dal {@link TipiAssenzeBean}, 
       * null se non esiste.
       * @param tipiAssenzeBean Contenitore dei tipi di assenze. Non deve essere a null.
       * @param id id del tipo dell'assenza desiderata.
       */
    public static TipoAssenza getTipoAssenza (TipiAssenzeBean tipiAssenzeBean, int id ) {
        if(tipiAssenzeBean==null) { throw new IllegalArgumentException(); }
        for(int i=0; i<tipiAssenzeBean.ritornaNumeroDiTipiAssenze (); i++){
            TipoAssenza tipiAssenza = tipiAssenzeBean.getTipoAssenzaAt (i);
            if(tipiAssenza.getId()==id)
                return tipiAssenza;
        }
        return null;
    }
    
    
      /** 
       * Dato un id ritorna il relativo {@link TipoVoto} dal {@link TipiVotiBean}, 
       * null se non esiste.
       * @param tipiVotiBean Contenitore dei tipi di voto. Non deve essere a null.
       * @param id id del tipo del voto desiderato.
       */
    public static TipoVoto getTipoVoto (TipiVotiBean tipiVotiBean, int id ) {
        if(tipiVotiBean==null) { throw new IllegalArgumentException(); }
        for(int i=0; i<tipiVotiBean.ritornaNumeroDiTipiVoti (); i++){
            TipoVoto tipiVoti = tipiVotiBean.getTipoVotoAt (i);
            if(tipiVoti.getId()==id)
                return tipiVoti;
        }
        return null;
    }
    
    
      /** 
       * Dato un id ritorna la relativa {@link TipoProva} dal {@link TipiProveBean}, 
       * null se non esiste.
       * @param tipiProveBean Contenitore dei tipi di prove. Non deve essere a null.
       * @param id id del tipo della prova desiderata.
       */
    public static TipoProva getTipoProva (TipiProveBean tipiProveBean, int id ) {
        if(tipiProveBean==null) { throw new IllegalArgumentException(); }
        for(int i=0; i<tipiProveBean.ritornaNumeroDiTipiProve (); i++){
            TipoProva tipiProve = tipiProveBean.getTipoProvaAt (i);
            if(tipiProve.getId()==id)
                return tipiProve;
        }
        return null;
    }
    
    
    
    
    /**
     * Ritorna un oggetto Date che rappresenta la data odierna.
     */
    public static java.util.Date getDataCorrente() {
      java.util.Calendar c = java.util.Calendar.getInstance();
      return new java.util.Date(
                c.get(java.util.Calendar.YEAR)-1900,
		c.get(java.util.Calendar.MONTH),
		c.get(java.util.Calendar.DATE) );
    }
    
    
    /**
     * Ritorna una stringa rappresentante una specifica data.
     * @param data Data da rappresentare.
     */
    public static String dateToString (java.util.Date data){
       if( data == null ) return new String("no date");
       String[] MESI = new String[] { "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre" };
       return new String(data.getDate() +" "+ MESI[ data.getMonth() ] +" "+  (data.getYear()+1900) );
    }
    
    /**
     * Dato un oggetto Classe ritorna il nome della classe secondo un pattern standard
     */
    public static String getNomeClasse(Classe classe){
        return classe.getAnnoCorso() + " "+((char)classe.getSezione());    
    }
    
    /**
     * Data una stringa ritorna la relativa Classe dal ClassiBean
     * null se tale classe non esiste
     * Il primo parametro no deve essere a null
     * il formato deve essere quello di UtilitaGUI.getNomeCorso
     */
    public static Classe getClasse(ClassiBean classiBean, String classe){  
        if(classe==null) { throw new IllegalArgumentException(); }  
        int anno = (Integer.decode(classe.substring(0,1))).intValue();
        char sezione = classe.charAt(2);
        //if(religioniBean==null) System.out.println("ERRORE");
        for(int i=0; i<classiBean.ritornaNumeroDiClassi(); i++){
            Classe c = classiBean.getClasseAt(i);
            if(c.getAnnoCorso() == anno && c.getSezione() == sezione)
                return c;
        }
        return null;    
    }
    
    /**
     * Ritorna una stringa rappresentativa del valore booleano passato
     */
    public static String valoreBoolean(boolean b){
        if(b) return new String("si");
        else return new String("no");
    }  
 
    /**
     * Aggiunge una riga alla table
     * La riga viene aggiunta in fondo
     * Il modello della table deve essere DefaultTableModel
     */
    public static void aggiungiRiga(javax.swing.JTable jtbl){
        int colNum = jtbl.getColumnCount();
        ( (javax.swing.table.DefaultTableModel)jtbl.getModel() ).addRow(new Object[colNum]);        
    }
    
    /**
     * Aggiunge una riga alla posizione voluta (nel caso 
     * crea le righe mancanti) e vi inserisce i valori passati 
     * (è necessario che la dimensione dell'array 
     * sia uguale al numero di colonne)
     **/
    public static void inserisciRiga(javax.swing.JTable jtbl, int row, Object[] values){
        if(jtbl == null || values.length != jtbl.getColumnCount()) throw new IllegalArgumentException();
        if(jtbl.getRowCount() <= row){ //aggiunge le righe mancanti
            for(int i=0; i <= row - (jtbl.getRowCount()); i++){
                aggiungiRiga(jtbl);
            }
        }
        for(int j=0; j < values.length; j++){
            jtbl.setValueAt(values[j], row, j);
        }
    }
    
    /**
     * Rimuove tutte le righe della tabella
     */
    public static void rimuoviRighe(javax.swing.JTable jtbl){
        if(jtbl == null) throw new IllegalArgumentException();
        int rows = jtbl.getRowCount();
        for(int i=0; i<rows; i++){
            ( (javax.swing.table.DefaultTableModel)jtbl.getModel() ).removeRow(0);         
        }
    }
    
    /**
     * Rimuove la riga i
     */
    public static void rimuoviRiga(javax.swing.JTable jtbl, int i){
        if(jtbl == null) throw new IllegalArgumentException();
        ( (javax.swing.table.DefaultTableModel)jtbl.getModel() ).removeRow(i);         
    }
    
    /**
     * Ritorna una stringa identificativa significativa per un personaBean
     */
    public static String getStringaIdentificativa(PersonaBean personaBean){
        return personaBean.getNome()+" "+personaBean.getCognome();
    }
    
    /**
     * Ritorna una stringa identificativa per il sesso
     */
    public static String getStringaSesso(Integer i){
        String sesso ="maschio";
        if( i != null && i == FEMMINA ) sesso = "femmina";
        return sesso;
    }
    
    /**
     * Ritorna una stringa con i nomi delle materie aventi gli id passati
     * ponendo i nomi in ordine ed uno per riga
     * Il contenitoreDati non deve essere a null;
     */
    public static String getStringaElencoMaterie(int[] ids, ContenitoreDati contenitoreDati){
        String materie = "";
        if(ids == null) return materie;
        if(contenitoreDati == null) throw new IllegalArgumentException();
        for( int i=0; i<ids.length; i++ ){
            MateriaInsegnamento materia = UtilitaGUI.getMateriaInsegnamento( contenitoreDati.getMaterieInsegnamento(), ids[i] );
            if(materia!=null) materie+=materia.getNome()+"\n";
        }   
        return materie;
    }
    
    public static int MASCHIO = 0;
    public static int FEMMINA = 1;
}
