/*
 * ContenitoreDati.java
 *
 * Created on 17 marzo 2007, 3.13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni;

import nu.mine.egoless.didattica.app.bean.NazioniBean;
import nu.mine.egoless.didattica.app.bean.ReligioniBean;
import nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.app.bean.TipiProveBean;
import nu.mine.egoless.didattica.app.bean.TipiVotiBean;
import nu.mine.egoless.didattica.ws.classesupport.Classe;
import java.util.TreeSet;

/**
 *
 * @author Lorenzo Daniele
 *
 * Classe che contiene tutti i dati caricati inzialmente dall'applicazione
 * e da passare alle varie classi
 */
public class ContenitoreDati {
    
    /** 
     * Creates a new instance of ContenitoreDati 
     */
    public ContenitoreDati(ReligioniBean religioni, NazioniBean nazioni, ClassiBean classi, MaterieInsegnamentoBean materie, TipiVotiBean tipiVoti, TipiAssenzeBean tipiAssenze,  TipiProveBean tipiProve) {
       if(nazioni==null || religioni==null || classi==null || materie==null || tipiVoti == null || tipiAssenze == null) throw new IllegalArgumentException();
       religioniBean = religioni;
       nazioniBean = nazioni;
       classiBean = classi;
       materieBean = materie;
       tipiVotiBean = tipiVoti;
       tipiAssenzeBean = tipiAssenze;
       this.tipiProve = tipiProve;
       //System.out.println ("RELCAR: "+religioni.ritornaNumeroDiReligioni());
       //inizializza la lista delle sezioni possibili
       TreeSet<Character> s = new TreeSet<Character>();
       for(int i=0; i<classiBean.ritornaNumeroDiClassi(); i++){
           char c= (char) classiBean.getClasseAt(i).getSezione();
           s.add(new Character(c));
       }
       Character[] temp = s.toArray(new Character[0]);
       sezioni = new char[temp.length];
       for(int j=0; j<temp.length; j++)
          sezioni[j] = temp[j].charValue();
    }    
    
    /**
     * @return l'oggetto passato come parametro al costruttore
     */
    public ReligioniBean getReligioni(){ return religioniBean; }
    
    /**
     * @return l'oggetto passato come parametro al costruttore
     */    
    public NazioniBean getNazioni(){ return nazioniBean; }
    
    /**
     * @return l'oggetto passato come parametro al costruttore
     */    
    public ClassiBean getClassi(){ return classiBean; }
    
    /**
     * @return l'oggetto passato come parametro al costruttore
     */    
    public MaterieInsegnamentoBean getMaterieInsegnamento(){ return materieBean; }
    
    /**
     * @return l'oggetto passato come parametro al costruttore
     */    
    public TipiAssenzeBean getTipiAssenze (){ return tipiAssenzeBean; }
    
    /**
     * @return l'oggetto passato come parametro al costruttore
     */    
    public TipiVotiBean getTipiVoto(){ return tipiVotiBean; }
    
    /**
     * @return l'oggetto passato come parametro al costruttore
     */    
    public TipiProveBean getTipiProve(){ return tipiProve; }
    
    /**
     * @return la lista degli anni di corso possibili
     */    
    public int[] getAnniCorso(){ return anniCorso; }
    
    /**
     * @return la lista delle sezioni possibili prese dal parametro del costruttore di tipo ClassiBean
     */    
    public char[] getSezioni(){ return sezioni; }

    
    
    
    
    /*variabili locali*/
    private ReligioniBean religioniBean = null;
    private NazioniBean nazioniBean = null;
    private ClassiBean classiBean = null;
    private MaterieInsegnamentoBean materieBean = null;
    private TipiAssenzeBean tipiAssenzeBean = null;
    private TipiVotiBean tipiVotiBean = null;
    private final int[] anniCorso = {1,2,3,4,5}; 
    private char[] sezioni = null;
    private TipiProveBean tipiProve = null;


}
