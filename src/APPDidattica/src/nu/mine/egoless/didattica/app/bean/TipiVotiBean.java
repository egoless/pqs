/*
 * Nome file: TipiVotiBean.java
 * Data creazione: 8 marzo 2007, 22.20
 * Info svn: $Id$
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.tipovotoclient.TipoVoto;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.tipovotoclient.WSTipoVotoService;
import nu.mine.egoless.didattica.ws.tipovotoclient.WSTipoVoto;
import nu.mine.egoless.didattica.ws.tipovotoclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 * Questa classe dovra' essere utilizzata da oggetti della GUI che hanno bisogno di
 * fare riferimento ad una lista di oggetti di tipo TipoVoto.
 * Da tale lista potranno costruire il proprio Bean sul quale compiere modifiche e
 * renderle effettive.
 */
public class TipiVotiBean {
   
   /**
    * Costanti che verranno utilizzate per comunicare a coloro che sono in ascolto
    * cosa e' stato fatto. A questi messaggi sara' corredato anche l'indice di dove
    * e' avvenuta la modifica
    */
   public static final String RIMOZIONE="rimozione";
   
   public static final String MODIFICA="modifica";
   
   public static final String CARICAMENTO_AVVENUTO="caricamentoAvvenuto";
   
   private List<TipoVoto> elencoTipiVoti=null;
   
   /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /** Creates a new instance of TipiVotiBean */
   public TipiVotiBean() {
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
    * Permette di caricare una lista di TipiVoti
    * @throws WSDidatticaException_Exception Quando si verifica un errore nel WS
    * @throws PortUnreachableException Quando il web service non e' raggiungibile.
    */
   public void caricaTipiVoti()throws WSDidatticaException_Exception,PortUnreachableException {
      
      // Call Web Service Operation
      WSTipoVotoService service = new WSTipoVotoService();
      WSTipoVoto port = service.getWSTipoVotoPort();
      if(port!=null) {
         elencoTipiVoti = port.recuperaTipiVoto();
         propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
      } else
         throw new PortUnreachableException();
      
   }
   
   /**
    * Ritorna quanti TipiVoti sono stati caricati
    * @return Valore dei numero dei tipi voti caricati
    */
   public int ritornaNumeroDiTipiVoti(){
      if(elencoTipiVoti!=null) {
         return elencoTipiVoti.size();
      } else return 0;
   }
   
   /**
    * Ritorna il tipo voto presente nella posizione indicata
    * @param posizione indica la posizione dell'tipo voto da ritornare
    */
   
   public TipoVoto getTipoVotoAt(int posizione){
      if(elencoTipiVoti!=null && posizione>=0 && posizione<elencoTipiVoti.size())
         return elencoTipiVoti.get(posizione);
      else
         return null;
   }
   
   /**
    * Rimuove un oggetto dalla lista
    * @param posizione Posizione dell'oggetto da togliere
    */
   public void rimuoviTipoVotoAt(int posizione){
      if(elencoTipiVoti!=null && posizione>=0 && posizione<elencoTipiVoti.size()) {
         elencoTipiVoti.remove(posizione);
         propertyChangeSupport.firePropertyChange(RIMOZIONE, null, null);
      }
   }
   
   /** Sostituisce un oggetto dalla lista con quello modificato
    * @param posizione Posizione dell'oggetto da sostituire
    * @param tipoVoto Nuovo valore del tipo voto
    */
   public void sostituisciTipoVotoAt(int posizione, TipoVoto tipoVoto){
      if(elencoTipiVoti!=null && posizione>=0 && posizione<elencoTipiVoti.size() && tipoVoto!=null) {
         elencoTipiVoti.remove(posizione);
         elencoTipiVoti.add(posizione, tipoVoto);
         propertyChangeSupport.firePropertyChange(MODIFICA, null, null);
      }
   }
}

