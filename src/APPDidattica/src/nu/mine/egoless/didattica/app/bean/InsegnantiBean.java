/*
 * Nome file:InsegnantiBean.java
 * Data creazione:13 marzo 2007, 22.20
 * Info svn: $Id: InsegnantiBean.java 739 2007-03-25 21:28:51Z roberto $
 */


package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.personaclient.Docente;
import nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaDocente;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.personaclient.WSPersona;
import nu.mine.egoless.didattica.ws.personaclient.WSPersonaService;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 * Questa classe dovra' essere utilizzata da oggetti della GUI che hanno bisogno di
 * fare riferimento ad una lista di oggetti di tipoInsegnante.
 * Da tale lista potranno costruire il proprio Bean sul quale compiere modifiche e
 * renderle effettive.
 */
public class InsegnantiBean {
   
   /**
    * Costanti che verranno utilizzate per comunicare a coloro che sono in ascolto
    * cosa e' stato fatto. A questi messaggi sara' corredato anche l'indice di dove
    * e' avvenuta la modifica
    */
   public static final String RIMOZIONE="rimozione";
   
   public static final String MODIFICA="modifica";
   
   public static final String CARICAMENTO_AVVENUTO="caricamentoAvvenuto";
   
   private List<Docente> elencoInsegnanti=null;
   
   /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /** Crea una nuova istanza di InsegnantiBean */
   public InsegnantiBean() {
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
    * Permette di caricare una lista di Insegnanti
    * @param insiemeParametri Lista di parametri per la ricerca
    */
   
   public void caricaInsegnanti(ParametriRicercaDocente insiemeParametri)throws WSDidatticaException_Exception,PortUnreachableException{
      
         // Call Web Service Operation
         WSPersonaService service = new WSPersonaService();
         WSPersona port = service.getWSPersonaPort();
         // TODO process result here
         if(port!=null){
            elencoInsegnanti = port.cercaDocente(insiemeParametri);
            propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
         }
         else
             throw new PortUnreachableException();
      
   }
   
   /**
    * Ritorna quanti Insegnanti sono stati caricati
    */   
   public int ritornaNumeroDiInsegnanti(){
      if(elencoInsegnanti!=null)
      {
         return elencoInsegnanti.size();
      }
      else return 0;
   }
   
   /**
    * Ritorna l'insegnante presente nella posizione indicata
    * @param posizione indica la posizione dell'insegnante da ritornare
    * @return Insegnante Lista degli Insegnanti
    */
   
   public Docente getInsegnanteAt(int posizione){
      if(elencoInsegnanti!=null && posizione>=0 && posizione<elencoInsegnanti.size())
       return elencoInsegnanti.get(posizione);
      else
         return null;
   }
   
   /** Rimuove un oggetto dalla lista
    * @param posizione Posizione dell'oggetto da togliere
    */
   public void rimuoviInsegnanteAt(int posizione){
      if(elencoInsegnanti!=null && posizione>=0 && posizione<elencoInsegnanti.size()) {
         elencoInsegnanti.remove(posizione);
         propertyChangeSupport.firePropertyChange(RIMOZIONE, null, null);
      }
   }
  
   
   /** Sostituisce un oggetto dalla lista con quello modificato
    * @param posizione Posizione dell'oggetto da sostituire
    */
   
   public void sostituisciInsegnanteAt(int posizione,Docente insegnante){
      if(elencoInsegnanti!=null && posizione>=0 && posizione<elencoInsegnanti.size()) {
         elencoInsegnanti.remove(posizione);
         elencoInsegnanti.add(posizione,insegnante);
         propertyChangeSupport.firePropertyChange(MODIFICA, null, null);
      }
   }
   
   /*
    
    NOTA 1 BENE VOLENDO SI PUO FARE UN METODO CHE RESTITUISCE GIA IL BEAN CORRISPONDENTE
    
    NOTA 2: NON CI SARA UN METODO DI SALVATAGGIO PERCHE IL SALVATTAGGIO AVVIENE PER MEZZO
    *       DI ASSENZA BEAN
    */
   
   
}
   
