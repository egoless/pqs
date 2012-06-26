/*
 * Nome file:TipiAssenzeBean.java
 * Data creazione:8 marzo 2007, 22.20
 * Info svn: $Id$
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.TipoAssenza;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.WSTipoAssenzaService;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.WSTipoAssenza;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 * Questa classe dovra' essere utilizzata da oggetti della GUI che hanno bisogno di
 * fare riferimento ad una lista di oggetti di tipo TipoAssenza.
 * Da tale lista potranno costruire il proprio Bean sul quale compiere modifiche e
 * renderle effettive.
 */
public class TipiAssenzeBean {
   
   /**
    * Costanti che verranno utilizzate per comunicare a coloro che sono in ascolto
    * cosa e' stato fatto. A questi messaggi sara' corredato anche l'indice di dove
    * e' avvenuta la modifica
    */
   public static final String RIMOZIONE="rimozione";
   
   public static final String MODIFICA="modifica";
   
   public static final String CARICAMENTO_AVVENUTO="caricamentoAvvenuto";
   
   private List<TipoAssenza> elencoTipiAssenze=null;
   
   /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /** Creates a new instance of TipiAssenzeBean */
   public TipiAssenzeBean() {
   }
   
   /**
    * Aggiunge un {@link PropertyChangeListener} alla lista dei listener che ascoltano
    * i cambiamenti di una data proprieta'.
    * @param nomeProprieta Proprieta' di cui il listener vuole sapere i cambiamenti.
    * @param l Il listener da aggiungere.
    */
   public void addPropertyChangeListener(String nomeProprieta, PropertyChangeListener l) {
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
    * Permette di caricare una lista di TipiAssenze
    * @param insiemeParametri Lista di parametri per la ricerca
    */
   public void caricaTipiAssenze()throws WSDidatticaException_Exception, PortUnreachableException {
      // Call Web Service Operation
      WSTipoAssenzaService service = new WSTipoAssenzaService();
      WSTipoAssenza port = service.getWSTipoAssenzaPort();
      
      if(port!=null) {
         elencoTipiAssenze = port.recuperaTipiAssenza();
         propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
      } else {
         throw new PortUnreachableException();
      }
   }
   
   /**
    * Ritorna quanti TipiAssenze sono stati caricati
    * @return Valore dei numero dei tipi voti caricati
    */
   public int ritornaNumeroDiTipiAssenze(){
      if(elencoTipiAssenze!=null) {
         return elencoTipiAssenze.size();
      } else {
         return 0;
      }
   }
   
   /**
    * Ritorna il tipo voto presente nella posizione indicata
    * @param posizione indica la posizione dell'tipo voto da ritornare
    */
   
   public TipoAssenza getTipoAssenzaAt(int posizione){
      if(elencoTipiAssenze!=null && posizione>=0 && posizione<elencoTipiAssenze.size()) {
         return elencoTipiAssenze.get(posizione);
      } else {
         return null;
      }
   }
   
   /** Rimuove un oggetto dalla lista
    * @param posizione Posizione dell'oggetto da togliere
    */
   public void rimuoviTipoAssenzaAt(int posizione) {
      if(elencoTipiAssenze!=null && posizione>=0 && posizione<elencoTipiAssenze.size()) {
         elencoTipiAssenze.remove(posizione);
         propertyChangeSupport.firePropertyChange(RIMOZIONE, null, null);
      }
   }
   
   
   /** Sostituisce un oggetto dalla lista con quello modificato
    * @param posizione Posizione dell'oggetto da sostituire
    * @param tipo voto Nuovo valore del tipo voto
    */
   
   public void sostituisciTipoAssenzaAt(int posizione,TipoAssenza tipoassenza) {
      if(elencoTipiAssenze!=null && posizione>=0 && posizione<elencoTipiAssenze.size() && tipoassenza!=null) {
         elencoTipiAssenze.remove(posizione);
         elencoTipiAssenze.add(posizione,tipoassenza);
         propertyChangeSupport.firePropertyChange(MODIFICA, null, null);
      }
   }
   
   /*
    NOTA 1 BENE VOLENDO SI PUO FARE UN METODO CHE RESTITUISCE GIA IL BEAN CORRISPONDENTE
    */
   
   
}

