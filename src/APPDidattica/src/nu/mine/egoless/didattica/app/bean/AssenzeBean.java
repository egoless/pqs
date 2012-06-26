/*
 * Nome file:AssenzaBean.java
 * Data creazione:8 marzo 2007, 22.20
 * Info svn: $Id: AssenzeBean.java 702 2007-03-25 14:32:37Z roberto $
 */


package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.assenzaclient.Assenza;
import nu.mine.egoless.didattica.ws.assenzaclient.ParametriRicercaAssenza;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.assenzaclient.WSAssenzaService;
import nu.mine.egoless.didattica.ws.assenzaclient.WSAssenza;
import nu.mine.egoless.didattica.ws.assenzaclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;


/**
 * Questa classe dovra' essere utilizzata da oggetti della GUI che hanno bisogno di
 * fare riferimento ad una lista di oggetti di tipoAssenza.
 * Da tale lista potranno costruire il proprio Bean sul quale compiere modifiche e
 * renderle effettive.
 */
public class AssenzeBean {
   
   /**
    * Costanti che verranno utilizzate per comunicare cosa e' stato fatto
    * a coloro che sono in ascolto.
    * A questi messaggi sara' allegato anche l'indice di dove
    * e' avvenuta la modifica
    */
   public static final String RIMOZIONE="rimozione";
   
   public static final String MODIFICA="modifica";
   
   public static final String CARICAMENTO_AVVENUTO="caricamentoAvvenuto";
   
   private List<Assenza> elencoAssenze=null;
  
  /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /** Creata una nuova istanza di AssenzeBean */
   public AssenzeBean() {
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
    * Permette di caricare una lista di assenze
    * @param insiemeParametri Lista di parametri per la ricerca
    */
   
   public void caricaAssenze(ParametriRicercaAssenza insiemeParametri) throws WSDidatticaException_Exception,PortUnreachableException {
      
      // Chiamata all'operazione Web Service
      WSAssenzaService service = new WSAssenzaService();
      WSAssenza port = service.getWSAssenzaPort();
      // TODO process result here
      if(port!=null){
         elencoAssenze = port.cercaAssenza(insiemeParametri);
         propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
      }
      else
         throw new PortUnreachableException();
      
   }
   
   /**
    * Ritorna quante assenze sono state caricate
    */
   public int ritornaNumeroDiAssenze(){
      if(elencoAssenze!=null) {
         return elencoAssenze.size();
      } else return 0;
   }
   
   /**
    * Ritorna l'assenza presente nella posizione indicata
    * @param posizione indica la posizione dell'assenza da ritornare
    */
   
   public Assenza getAssenzaAt(int posizione){
      if(elencoAssenze!=null && posizione>=0 && posizione<elencoAssenze.size())
         return elencoAssenze.get(posizione);
      else
         return null;
   }
   
   /** Rimuove un oggetto dalla lista
    * @param posizione Posizione dell'oggetto da togliere
    */
   public void rimuoviAssenzaAt(int posizione){
      if(elencoAssenze!=null && posizione>=0 && posizione<elencoAssenze.size()) {
         elencoAssenze.remove(posizione);
         propertyChangeSupport.firePropertyChange(RIMOZIONE, null, null);
      }
   }
   
   
   /** Sostituisce un oggetto dalla lista con quello modificato
    * @param posizione Posizione dell'oggetto da sostituire
    */
   
   public void sostituisciAssenzaAt(int posizione,Assenza assenza){
      if(elencoAssenze!=null && posizione>=0 && posizione<elencoAssenze.size() && assenza!=null) {
         elencoAssenze.remove(posizione);
         elencoAssenze.add(posizione,assenza);
         propertyChangeSupport.firePropertyChange(MODIFICA, null, null);
      }
   }
   
   
}

