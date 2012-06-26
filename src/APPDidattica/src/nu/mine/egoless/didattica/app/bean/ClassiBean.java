
/*
 * Nome file:ClassiBean.java
 * Data creazione:18 marzo 2007, 13.37
 * Info svn: $Id$
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.net.PortUnreachableException;
import nu.mine.egoless.didattica.ws.classesupport.Classe;
import nu.mine.egoless.didattica.ws.classesupport.WSClasse;
import nu.mine.egoless.didattica.ws.classesupport.WSClasseService;
import nu.mine.egoless.didattica.ws.classesupport.WSDidatticaException_Exception;

public class ClassiBean {
   
   /** Costante per comunicare l'avvenuto caricamento */

   public static final String CARICAMENTO_AVVENUTO="caricamentoAvvenuto";
   
   private List<Classe> elencoClassi=null;
   
   /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /**
    * Creata una nuova istanza di ClassiBean
    */
   public ClassiBean() {
   }
   
   /**
    * Aggiunge un {@link PropertyChangeListener} alla lista dei listener che ascoltano
    * i cambiamenti di una data proprieta'.
    * @param nomeProprieta Proprieta' di cui il listener vuole sapere i cambiamenti.
    * @param l Il listener da aggiungere.
   */
   public void addPropertyChangeListener(PropertyChangeListener l) {
      propertyChangeSupport.addPropertyChangeListener(l);
   }
   
   /**
    * Rimuove un propertyChangeListener alla lista dei listener
    * @param l Il listener da rimuovere.
    */
   public void removePropertyChangeListener(PropertyChangeListener l) {
      propertyChangeSupport.removePropertyChangeListener(l);
   }
   

   /**
    * Permette di caricare una lista di classi
    * @param insiemeParametri Lista di parametri per la ricerca
    */
   
   public void caricaClassi() throws WSDidatticaException_Exception,PortUnreachableException  {
      
         // Chiamata all'operazione Web Service 
         WSClasseService service = new WSClasseService();
         WSClasse port = service.getWSClassePort();
         // TODO process result here
         if(port!= null){
            elencoClassi = port.recuperaClassi();
            propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
         }
         else
            throw new PortUnreachableException();
         
         propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
   }
   
   /**
    * Ritorna quante classi sono state caricate
    */   
   public int ritornaNumeroDiClassi(){
      if(elencoClassi!=null) {
         return elencoClassi.size();
      }
      else { 
         return 0;
      }
   }
   
   /**
    * Ritorna l'classe presente nella posizione indicata
    * @param posizione indica la posizione dell'classe da ritornare
    */
   
   public Classe getClasseAt(int posizione){
      if(elencoClassi!=null && posizione>=0 && posizione<elencoClassi.size()) {
       return elencoClassi.get(posizione);
      } else {
         return null;
      }
   }
   
}