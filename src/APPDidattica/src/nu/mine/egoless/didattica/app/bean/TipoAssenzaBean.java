/*
 * Nome file:TipoAssenzaBean.java
 * Data creazione:22 febbraio 2007, 12.02
 * Info svn: $Id: TipoAssenzaBean.java 777 2007-03-26 08:28:40Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.TipoAssenza;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.WSTipoAssenzaService;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.WSTipoAssenza;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.WSDidatticaException_Exception;

/**
 * Classe che incapsula un oggetto di tipo {@link TipoAssenza} per renderlo
 * maggiormente fruibile ad una GUI.
 */
public class TipoAssenzaBean {
   
   /**
    * Costanti usate dai metodo fireChangeProperty perche' le classi che gestiscono
    * l'intefaccia grafica riescano a discriminare quali proprieta' della classe sono
    * cambiate
    */
   public static final String DESCRIZIONE="descrizione";
   
   /**
    * Tiene il valore della proprietà tipoAssenza.
    */
   private TipoAssenza tipoAssenza;
   
   /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /**
    * Crea una nuova istanza di TipoAssenzaBean
    */
   public TipoAssenzaBean() {
      this.tipoAssenza=new TipoAssenza();
      tipoAssenza.setId(Costanti.ID_NUOVO_TIPO_ASSENZA);
   }
   
   /**
    * Crea una nuova istanza di TipoAssenzaBean con un dato id.
    * @param idIn Id da assegnare al tipo assenza.
    */
   public TipoAssenzaBean(int idIn) {
      this.tipoAssenza=new TipoAssenza();
      tipoAssenza.setId(idIn);   
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
    * Getter per la proprieta' id.
    * @return Valore della proprieta' id.
    */
   public int getId() {
      return this.tipoAssenza.getId();
   }
   
   /**
    * Getter per la proprietà descrizione.
    * @return Valore della proprietà descrizione.
    */
   public String getDescrizione() {
      return this.tipoAssenza.getDescrizione();
   }
   
   /**
    * Setter per la proprietà descrizione.
    * @param descrizione Nuovo valore della proprietà descrizione.
    */
   public void setDescrizione(String descrizione) {
      String oldDescrizione = this.tipoAssenza.getDescrizione();
      
      //oldDescrizione==null || !oldDescrizione.equals(descrizione)
      if((oldDescrizione==null && descrizione !=null) ||
              (oldDescrizione!=null && !oldDescrizione.equals(descrizione))){
         
         this.tipoAssenza.setDescrizione(descrizione);
         propertyChangeSupport.firePropertyChange(DESCRIZIONE, oldDescrizione, descrizione);
      }
   }

   /**
    * Salva l'oggetto sul Web Service
    * @throws WSDidatticaException_Exception Lanciata quando si verifica un
    *         errore nel WS sottostante.
    */
   public void salvaSuWS()throws WSDidatticaException_Exception {
      WSTipoAssenzaService service = new WSTipoAssenzaService();
      WSTipoAssenza port = service.getWSTipoAssenzaPort();
      
      int nuovoId=port.aggiungiTipoAssenza(this.tipoAssenza);
      tipoAssenza.setId(nuovoId);
   }
   
   /**
    * Metodo che cancella l'oggetto nel Web Service.
    * @throws WSDidatticaException_Exception Lanciata quando si verifica un
    *         errore nel WS sottostante.
    */
   public void cancellaTipoAssenza()throws WSDidatticaException_Exception {
      WSTipoAssenzaService service = new WSTipoAssenzaService();
      WSTipoAssenza port = service.getWSTipoAssenzaPort();
      
      port.cancellaTipoAssenza(this.tipoAssenza.getId()); 
   }
}
