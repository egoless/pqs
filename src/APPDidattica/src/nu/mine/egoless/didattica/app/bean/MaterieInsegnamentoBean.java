/*
 * Nome file:MaterieInsegnamentoBean.java
 * Data creazione:18 marzo 2007, 13.37
 * Info svn: $Id$
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.net.PortUnreachableException;
import nu.mine.egoless.didattica.ws.materiaclient.MateriaInsegnamento;
import nu.mine.egoless.didattica.ws.materiaclient.WSMateria;
import nu.mine.egoless.didattica.ws.materiaclient.WSMateriaService;
import nu.mine.egoless.didattica.ws.materiaclient.WSDidatticaException_Exception;

public class MaterieInsegnamentoBean {
   
   /** Costante per comunicare l'avvenuto caricamento */

   public static final String RIMOZIONE="rimozione";
   
   public static final String MODIFICA="modifica";
   
   public static final String CARICAMENTO_AVVENUTO="caricamentoAvvenuto";
   
   private List<MateriaInsegnamento> elencoMaterie=null;
   
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /**
    * Creata una nuova istanza di MaterieInsegnamentoBean
    */
   public MaterieInsegnamentoBean() {
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
    * Permette di caricare una lista di materie
    * @param insiemeParametri Lista di parametri per la ricerca
    */
   
   public void caricaMaterie() throws WSDidatticaException_Exception,PortUnreachableException  {
      
         // Chiamata all'operazione Web Service 
         WSMateriaService service = new WSMateriaService();
         WSMateria port = service.getWSMateriaPort();
         // TODO process result here
         if(port!= null){
            elencoMaterie = port.recuperaMaterie();
            propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
         }
         else
            throw new PortUnreachableException();
         
         propertyChangeSupport.firePropertyChange(CARICAMENTO_AVVENUTO, null, null);
   }
   
   /**
    * Ritorna quante materie sono state caricate
    */   
   public int ritornaNumeroDiMaterie(){
      if(elencoMaterie!=null) {
         return elencoMaterie.size();
      }
      else { 
         return 0;
      }
   }
   
   /**
    * Rimuove un oggetto dalla lista
    * @param posizione Posizione dell'oggetto da togliere
    */
   public void rimuoviMateriaAt(int posizione){
      if(elencoMaterie!=null && posizione>=0 && posizione<elencoMaterie.size()) {
         elencoMaterie.remove(posizione);
         propertyChangeSupport.firePropertyChange(RIMOZIONE, null, null);
      }
   }
   
   /** Sostituisce un oggetto dalla lista con quello modificato
    * @param posizione Posizione dell'oggetto da sostituire
    * @param materia Nuovo valore della materia 
    */
   public void sostituisciMateriaAt(int posizione, MateriaInsegnamento materia){
      if(elencoMaterie!=null && posizione>=0 && posizione<elencoMaterie.size() && materia!=null) {
         elencoMaterie.remove(posizione);
         elencoMaterie.add(posizione, materia);
         propertyChangeSupport.firePropertyChange(MODIFICA, null, null);
      }
   }
   
   public MateriaInsegnamento getMateriaAt(int posizione){
      if(elencoMaterie!=null && posizione>=0 && posizione<elencoMaterie.size()) {
         return elencoMaterie.get(posizione);
      } else {
         return null;
      }
   }   
   
}
   