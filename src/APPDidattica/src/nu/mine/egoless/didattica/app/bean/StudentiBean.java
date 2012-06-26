/*
 * Nome file:StudentiBean.java
 * Data creazione:8 marzo 2007, 22.19
 * Info svn: $Id: StudentiBean.java 702 2007-03-25 14:32:37Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.personaclient.Studente;
import nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaStudente;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.personaclient.WSPersona;
import nu.mine.egoless.didattica.ws.personaclient.WSPersonaService;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 * Questa classe dovra' essere utilizzata da oggetti della GUI che hanno bisogno di
 * fare riferimento ad una lista di oggetti di tipoStudente.
 * Da tale lista potranno costruire il proprio Bean sul quale compiere modifiche e
 * renderle effettive.
 */
public class StudentiBean {
   
   /**
    * Costanti che verranno utilizzate per comunicare a coloro che sono in ascolto
    * cosa e' stato fatto. A questi messaggi sara' corredato anche l'indice di dove
    * e' avvenuta la modifica
    */
   public static final String RIMOZIONE="rimozione";
   
   public static final String MODIFICA="modifica";
   
   public static final String CARICAMENTO_AVVENUTO="caricamentoAvvenuto";
   
   private List<Studente> elencoStudenti=null;
   
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   /** Crea una nuova istanza di StudentiBean */
   public StudentiBean() {
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
    * Permette di caricare una lista di Studenti
    * @param insiemeParametri Lista di parametri per la ricerca
    */
   
   public void caricaStudenti(ParametriRicercaStudente insiemeParametri)throws WSDidatticaException_Exception,PortUnreachableException {
      
         // Call Web Service Operation
         WSPersonaService service = new WSPersonaService();
         WSPersona port = service.getWSPersonaPort();
         // TODO process result here
         if(port!=null){
            elencoStudenti = port.cercaStudente(insiemeParametri);
            propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
         }
         else
             throw new PortUnreachableException();
      
   }
   
   /**
    * Ritorna quanti Studenti sono stati caricati
    */   
   public int ritornaNumeroDiStudenti(){
      if(elencoStudenti!=null)
      {
         return elencoStudenti.size();
      }
      else return 0;
   }
   
   /**
    * Ritorna l'studente presente nella posizione indicata
    * @param posizione indica la posizione dell'studente da ritornare
    */
   
   public Studente getStudenteAt(int posizione){
      if(elencoStudenti!=null && posizione>=0 && posizione<elencoStudenti.size())
       return elencoStudenti.get(posizione);
      else
         return null;
   }
   
   /** Rimuove un oggetto dalla lista
    * @param posizione Posizione dell'oggetto da togliere
    */
   public void rimuoviStudenteAt(int posizione){
      if(elencoStudenti!=null && posizione>=0 && posizione<elencoStudenti.size()) {
         elencoStudenti.remove(posizione);
         propertyChangeSupport.firePropertyChange(RIMOZIONE, null, null);
      }
   }
  
   
   /** Sostituisce un oggetto dalla lista con quello modificato
    * @param posizione Posizione dell'oggetto da sostituire
    */
   
   public void sostituisciStudenteAt(int posizione,Studente studente){
      if(elencoStudenti!=null && posizione>=0 && posizione<elencoStudenti.size() && studente!=null) {
         elencoStudenti.remove(posizione);
         elencoStudenti.add(posizione,studente);
         propertyChangeSupport.firePropertyChange(MODIFICA, null, null);
      }
   }
   
   /*
    
    NOTA 1 BENE VOLENDO SI PUO FARE UN METODO CHE RESTITUISCE GIA IL BEAN CORRISPONDENTE
    
    NOTA 2: NON CI SARA UN METODO DI SALVATAGGIO PERCHE IL SALVATTAGGIO AVVIENE PER MEZZO
    *       DI ASSENZA BEAN
    */
   
   
}
   
