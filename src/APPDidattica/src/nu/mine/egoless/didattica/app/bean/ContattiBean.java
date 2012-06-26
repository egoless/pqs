/*
 * Nome file:ContattiBean.java
 * Data creazione:13 marzo 2007, 22.20
 * Info svn: $Id$
 */


package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.contattoclient.Contatto;
import nu.mine.egoless.didattica.ws.contattoclient.ParametriRicercaContatto;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.contattoclient.WSContatto;
import nu.mine.egoless.didattica.ws.contattoclient.WSContattoService;
import nu.mine.egoless.didattica.ws.contattoclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;


/**
 * Questa classe dovra' essere utilizzata da oggetti della GUI che hanno bisogno di
 * fare riferimento ad una lista di oggetti di tipoContatto.
 * Da tale lista potranno costruire il proprio Bean sul quale compiere modifiche e
 * renderle effettive.
 */
public class ContattiBean {
   
   /**
    * Costanti che verranno utilizzate per comunicare cosa e' stato fatto 
    * a coloro che sono in ascolto. 
    * A questi messaggi sara' corredato anche l'indice di dove
    * e' avvenuta la modifica.
    */
   public static final String RIMOZIONE="rimozione";
   
   public static final String MODIFICA="modifica";
   
   public static final String CARICAMENTO_AVVENUTO="caricamentoAvvenuto";
 
   private List<Contatto> elencoContatti=null;
   
   /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   /** Creata una nuova istanza di ContattiBean */
   public ContattiBean() {
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
   
   public void caricaContatti(ParametriRicercaContatto insiemeParametri)throws WSDidatticaException_Exception,PortUnreachableException {
      
         // Call Web Service Operation
         WSContattoService service = new WSContattoService();
         WSContatto port = service.getWSContattoPort();
         // TODO process result here
        if(port!=null){
            elencoContatti = port.cercaContatto(insiemeParametri);
            propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
        }
         else
             throw new PortUnreachableException();
   }
         
   /**
    * @return quanti Insegnanti sono stati caricati
    */   
   public int ritornaNumeroDiContatti(){
      if(elencoContatti!=null)
      {
         return elencoContatti.size();
      }
      else return 0;
   }
   
   /**
    * Ritorna l'Insegnante presente nella posizione indicata
    * @param posizione indica la posizione dell'Insegnante da ritornare
    */
   
   public Contatto getContattoAt(int posizione){
      if(elencoContatti!=null && posizione>=0 && posizione<elencoContatti.size())
       return elencoContatti.get(posizione);
      else
         return null;
   }
   
   /** Rimuove un oggetto dalla lista
    * @param posizione Posizione dell'oggetto da togliere
    */
   public void rimuoviContattoAt(int posizione){
      if(elencoContatti!=null && posizione>=0 && posizione<elencoContatti.size()) {
         elencoContatti.remove(posizione);
         propertyChangeSupport.firePropertyChange(RIMOZIONE, null, null);
      }
   }
  
   
   /** Sostituisce un oggetto dalla lista con quello modificato
    * @param posizione Posizione dell'oggetto da sostituire
    * @param contatto Oggetto modificato
    */
   
   public void sostituisciContattoAt(int posizione,Contatto contatto){
      if(elencoContatti!=null && posizione>=0 && posizione<elencoContatti.size() && contatto!=null) {
         elencoContatti.remove(posizione);
         elencoContatti.add(posizione,contatto);
         propertyChangeSupport.firePropertyChange(MODIFICA, null, null);
      }
   }
   
   /*
    
    NOTA 1 BENE VOLENDO SI PUO FARE UN METODO CHE RESTITUISCE GIA IL BEAN CORRISPONDENTE
    
    NOTA 2: NON CI SARA UN METODO DI SALVATAGGIO PERCHE IL SALVATTAGGIO AVVIENE PER MEZZO
    *       DI ASSENZA BEAN
    */
   
   
}
   
