/*
 * Nome file:NazioniBean.java
 * Data creazione:14 marzo 2007, 14.53
 * Info svn: $Id$
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.nazioneclient.Nazione;
import nu.mine.egoless.didattica.ws.nazioneclient.RecuperaNazioni;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.nazioneclient.WSNazione;
import nu.mine.egoless.didattica.ws.nazioneclient.WSNazioneService;
import nu.mine.egoless.didattica.ws.nazioneclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 * Questa classe dovra' essere utilizzata da oggetti della GUI che hanno bisogno di
 * fare riferimento ad una lista di oggetti di tipo Nazione.
 * Da tale lista potranno costruire il proprio Bean sul quale compiere modifiche e
 * renderle effettive.
 */
public class NazioniBean {
   
   /** Costante per comunicare l'avvenuto caricamento */
   
   public static final String CARICAMENTO_AVVENUTO="caricamentoAvvenuto";
   
   private List<Nazione> elencoNazioni=null;
   
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /** Creata una nuova istanza di NazioniBean */
   public NazioniBean() {
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
    * Permette di caricare una lista di nazioni
    * @param insiemeParametri Lista di parametri per la ricerca
    */
   public void caricaNazioni() throws WSDidatticaException_Exception,PortUnreachableException  {
      // Chiamata all'operazione Web Service
      WSNazioneService service = new WSNazioneService();
      WSNazione port = service.getWSNazionePort();
      
      if(port!= null) {
         elencoNazioni = port.recuperaNazioni();
         propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
      } else
         throw new PortUnreachableException();
   }
   
   /**
    * Ritorna quante nazioni sono state caricate
    */
   public int ritornaNumeroDiNazioni(){
      if(elencoNazioni!=null) {
         return elencoNazioni.size();
      } else {
         return 0;
      }
   }
   
   /**
    * Ritorna l'nazione presente nella posizione indicata
    * @param posizione indica la posizione dell'nazione da ritornare
    */
   public Nazione getNazioneAt(int posizione){
      if(elencoNazioni!=null && posizione>=0 && posizione<elencoNazioni.size()) {
         return elencoNazioni.get(posizione);
      } else {
         return null;
      }
   }
   
}

