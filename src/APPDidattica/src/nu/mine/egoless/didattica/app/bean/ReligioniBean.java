/*
 * Nome file:ReligioniBean.java
 * Data creazione:14 marzo 2007, 14.59
 * Info svn: $Id$
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.religioneclient.Religione;
import nu.mine.egoless.didattica.ws.religioneclient.RecuperaReligioni;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.religioneclient.WSReligione;
import nu.mine.egoless.didattica.ws.religioneclient.WSReligioneService;
import nu.mine.egoless.didattica.ws.religioneclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 * Questa classe dovra' essere utilizzata da oggetti della GUI che hanno bisogno di
 * fare riferimento ad una lista di oggetti di tipoReligione.
 * Da tale lista potranno costruire il proprio Bean sul quale compiere modifiche e
 * renderle effettive.
 */
public class ReligioniBean {
   
   /** Costante per comunicare l'avvenuto caricamento */
   
   public static final String CARICAMENTO_AVVENUTO="caricamentoAvvenuto";
   
   private List<Religione> elencoReligioni=null;
   
   /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /** Creata una nuova istanza di ReligioniBean */
   public ReligioniBean() {
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
    * Permette di caricare una lista di religioni
    * @param insiemeParametri Lista di parametri per la ricerca
    */
   public void caricaReligioni() throws WSDidatticaException_Exception,PortUnreachableException {
      // Chiamata all'operazione Web Service
      WSReligioneService service = new WSReligioneService();
      WSReligione port = service.getWSReligionePort();
      
      if(port!=null) {
         //Possiamo comunicare con il WS: recuperiamo le religioni
         // e segnaliamo il loro caricamento.
         elencoReligioni = port.recuperaReligioni();
         propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
      } else {
         throw new PortUnreachableException();
      }
      
   }
   
   /**
    * Ritorna quante religioni sono state caricate.
    * @return Numero di religioni caricate.
    */
   public int ritornaNumeroDiReligioni(){
      if(elencoReligioni!=null) {
         return elencoReligioni.size();
      } else {
         return 0;
      }
   }
   
   /**
    * Ritorna la religione presente nella posizione indicata
    * @param posizione indica la posizione dell'religione da ritornare.
    * @return La religione voluta oppure {@code null}.
    */
   public Religione getReligioneAt(int posizione){
      if(elencoReligioni!=null &&posizione>=0 && posizione<elencoReligioni.size()) {
         return elencoReligioni.get(posizione);
      } else {
         return null;
      }
   }
   
}

