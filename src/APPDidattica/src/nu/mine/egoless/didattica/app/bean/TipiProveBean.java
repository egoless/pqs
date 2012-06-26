/*
 * Nome file: TipiProveBean.java
 * Data creazione: 26 marzo 2007, 8.20
 * Info svn: $Id: TipiProveBean.java 771 2007-03-26 08:03:20Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.tipoprovaclient.TipoProva;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.tipoprovaclient.WSTipoProvaService;
import nu.mine.egoless.didattica.ws.tipoprovaclient.WSTipoProva;
import nu.mine.egoless.didattica.ws.tipoprovaclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 * Questa classe dovra' essere utilizzata da oggetti della GUI che hanno bisogno di
 * fare riferimento ad una lista di oggetti di tipo TipoProva.
 * Da tale lista potranno costruire il proprio Bean sul quale compiere modifiche e
 * renderle effettive.
 */
public class TipiProveBean {
   
   /**
    * Costanti che verranno utilizzate per comunicare a coloro che sono in ascolto
    * cosa e' stato fatto. A questi messaggi sara' corredato anche l'indice di dove
    * e' avvenuta la modifica
    */
   public static final String RIMOZIONE="rimozione";
   
   public static final String MODIFICA="modifica";
   
   public static final String CARICAMENTO_AVVENUTO="caricamentoAvvenuto";
   
   private List<TipoProva> elencoTipiProve=null;
   
   /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /** Creates a new instance of TipiProveBean */
   public TipiProveBean() {
   }
   
   /**
    * Aggiunge un {@link PropertyChangeListener} alla lista dei listener che ascoltano
    * i cambiamenti di una data proprieta'.
    * @param nomeProprieta Proprieta' di cui il listener vuole sapere i cambiamenti.
    * @param l Il listener da aggiungere.
    */
   public void addPropertyChangeListener(String nomeProprieta,PropertyChangeListener l) {
      propertyChangeSupport.addPropertyChangeListener(nomeProprieta, l);
   }
   
   /**
    * Rimuove un {@link PropertyChangeListener} alla lista dei listener che ascoltano i cambiamenti
    * di una data proprieta'
    * @param nomeProprieta La proprieta' a cui il listener e' agganciato.
    * @param l Il listener da rimuovere.
    */
   public void removePropertyChangeListener(String nomeProprieta, PropertyChangeListener l) {
      propertyChangeSupport.removePropertyChangeListener(nomeProprieta, l);
   }
   
   /**
    * Permette di caricare una lista di TipiProve
    * @throws WSDidatticaException_Exception Quando si verifica un errore nel WS
    * @throws PortUnreachableException Quando il web service non e' raggiungibile.
    */
   public void caricaTipiProve()throws WSDidatticaException_Exception,PortUnreachableException {
      
      // Call Web Service Operation
      WSTipoProvaService service = new WSTipoProvaService();
      WSTipoProva port = service.getWSTipoProvaPort();
      if(port!=null) {
         elencoTipiProve = port.recuperaTipiProva();
         propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
      } else
         throw new PortUnreachableException();
      
   }
   
   /**
    * Ritorna quanti TipiProve sono stati caricati
    * @return Valore dei numero dei tipi prove caricati
    */
   public int ritornaNumeroDiTipiProve(){
      if(elencoTipiProve!=null) {
         return elencoTipiProve.size();
      } else return 0;
   }
   
   /**
    * Ritorna il tipo prova presente nella posizione indicata
    * @param posizione indica la posizione del tipo prova da ritornare
    */
   
   public TipoProva getTipoProvaAt(int posizione){
      if(elencoTipiProve!=null && posizione>=0 && posizione<elencoTipiProve.size())
         return elencoTipiProve.get(posizione);
      else
         return null;
   }
   
   /**
    * Rimuove un oggetto dalla lista
    * @param posizione Posizione dell'oggetto da togliere
    */
   public void rimuoviTipoProvaAt(int posizione){
      if(elencoTipiProve!=null && posizione>=0 && posizione<elencoTipiProve.size()) {
         elencoTipiProve.remove(posizione);
         propertyChangeSupport.firePropertyChange(RIMOZIONE, null, null);
      }
   }
   
   /** Sostituisce un oggetto dalla lista con quello modificato
    * @param posizione Posizione dell'oggetto da sostituire
    * @param tipoProva Nuovo valore del tipo prova
    */
   public void sostituisciTipoProvaAt(int posizione, TipoProva tipoProva){
      if(elencoTipiProve!=null && posizione>=0 && posizione<elencoTipiProve.size() && tipoProva!=null) {
         elencoTipiProve.remove(posizione);
         elencoTipiProve.add(posizione, tipoProva);
         propertyChangeSupport.firePropertyChange(MODIFICA, null, null);
      }
   }
}

