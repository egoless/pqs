/*
 * Nome file:VotiBean.java
 * Data creazione:8 marzo 2007, 22.20
 * Info svn: $Id: VotiBean.java 702 2007-03-25 14:32:37Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.votoclient.Voto;
import nu.mine.egoless.didattica.ws.votoclient.ParametriRicercaVoto;
import java.beans.PropertyChangeListener;
//import nu.mine.egoless.didattica.ws.personaclient.WSPersona;
import nu.mine.egoless.didattica.ws.votoclient.WSVotoService;
import nu.mine.egoless.didattica.ws.votoclient.WSVoto;
import nu.mine.egoless.didattica.ws.votoclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 * Questa classe dovra' essere utilizzata da oggetti della GUI che hanno bisogno di
 * fare riferimento ad una lista di oggetti di tipoVoto.
 * Da tale lista potranno costruire il proprio Bean sul quale compiere modifiche e
 * renderle effettive.
 */
public class VotiBean {
   
   /**
    * Costanti che verranno utilizzate per comunicare a coloro che sono in ascolto
    * cosa e' stato fatto. A questi messaggi sara' corredato anche l'indice di dove
    * e' avvenuta la modifica
    */
   public static final String RIMOZIONE="rimozione";
   
   public static final String MODIFICA="modifica";
   
   public static final String CARICAMENTO_AVVENUTO="caricamentoAvvenuto";
   
   private List<Voto> elencoVoti=null;
   
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   /** Creates a new instance of VotiBean */
   public VotiBean() {
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
    * Rimuove un propertyChangeListener alla lista dei listener
    * @param l Il listener da rimuovere.
    */
   public void removePropertyChangeListener(PropertyChangeListener l) {
      propertyChangeSupport.removePropertyChangeListener(l);
   }
   

   /**
    * Permette di caricare una lista di Voti
    * @param insiemeParametri Lista di parametri per la ricerca
    */
   
   public void caricaVoti(ParametriRicercaVoto insiemeParametri)throws WSDidatticaException_Exception,PortUnreachableException {
      
         // Call Web Service Operation
         WSVotoService service = new WSVotoService();
         WSVoto port = service.getWSVotoPort();
         if(port!=null){
            elencoVoti = port.cercaVoto(insiemeParametri);
            propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
         }
         else
             throw new PortUnreachableException();
      
   }
   
   /**
    * Ritorna quanti Voti sono stati caricati
    * @return Valore dei numero dei voti caricati
    */   
   public int ritornaNumeroDiVoti(){
      if(elencoVoti!=null)
      {
         return elencoVoti.size();
      }
      else return 0;
   }
   
   /**
    * Ritorna il voto presente nella posizione indicata
    * @param posizione indica la posizione dell'voto da ritornare
    */
   
   public Voto getVotoAt(int posizione){
      if(elencoVoti!=null && posizione>=0 && posizione<elencoVoti.size())
       return elencoVoti.get(posizione);
      else
         return null;
   }
   
   /** Rimuove un oggetto dalla lista
    * @param posizione Posizione dell'oggetto da togliere
    */
   public void rimuoviVotoAt(int posizione){
      if(elencoVoti!=null && posizione>=0 && posizione<elencoVoti.size()) {
         elencoVoti.remove(posizione);
         propertyChangeSupport.firePropertyChange(RIMOZIONE, null, null);
      }
   }
  
   
   /** Sostituisce un oggetto dalla lista con quello modificato
    * @param posizione Posizione dell'oggetto da sostituire
    * @param voto Nuovo valore del voto
    */
   
   public void sostituisciVotoAt(int posizione,Voto voto){
      if(elencoVoti!=null && posizione>=0 && posizione<elencoVoti.size() && voto!=null) {
         elencoVoti.remove(posizione);
         elencoVoti.add(posizione,voto);
         propertyChangeSupport.firePropertyChange(MODIFICA, null, null);
      }
   }
   
   /*
    
    NOTA 1 BENE VOLENDO SI PUO FARE UN METODO CHE RESTITUISCE GIA IL BEAN CORRISPONDENTE
    
    NOTA 2: NON CI SARA UN METODO DI SALVATAGGIO PERCHE IL SALVATTAGGIO AVVIENE PER MEZZO
    *       DI ASSENZA BEAN
    */
   
   
}
   
