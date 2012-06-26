/*
 * Nome file:AssenzaBean.java
 * Data creazione:22 febbraio 2007, 11.36
 * Info svn: $Id: AssenzaBean.java 881 2007-03-27 18:25:01Z alberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.util.List;
import nu.mine.egoless.didattica.ws.assenzaclient.Assenza;
import nu.mine.egoless.didattica.ws.assenzaclient.ParametriRicercaAssenza;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.assenzaclient.WSAssenzaService;
import nu.mine.egoless.didattica.ws.assenzaclient.WSAssenza;
import nu.mine.egoless.didattica.ws.assenzaclient.WSDidatticaException_Exception;
import nu.mine.egoless.didattica.ws.assenzaclient.Date;
import nu.mine.egoless.supporto.DateTimeFacade;


/** La classe AssenzaBean contiene l'oggetto di tipo Assenza*/

public class AssenzaBean {
   /**
    * Costanti usate dai metodo fireChangeProperty perche' le classi che gestiscono
    * l'intefaccia grafica riescano a discriminare quali proprieta' della classe sono
    * cambiate.
    */
   
   public static final String DATA_FINE="dataFine";
   
   public static final String DATA_INIZIO="dataInizio";
   
   public static final String GIUSTIFICAZIONE="giustificazione";
   
   public static final String ID="id";
   
   public static final String ID_PERSONA="idPersona";
   
   public static final String ID_TIPO_ASSENZA="idTipoAssenza";
   
   public static final String ID_EVENTO="idEvento";
      
   /**
    * Tiene il valore della proprieta' assenza.
    */
   private Assenza assenza;
   
   
   /**
    * Crea una nuova istanza di AssenzaBean partendo da un id
    * @param id identificativo dell'assenza
    */
   public AssenzaBean(int id) {
      assenza= new Assenza();
      assenza.setId(id);
   }
   
   /**
    * Crea una nuova istanza di AssenzaBean
    */
   
   public AssenzaBean() {
      assenza= new Assenza();
      assenza.setId(Costanti.ID_NUOVA_ASSENZA);
   }
   
   /**
    *Crea una nuova istanza di AssenzaBean partendo da un'assenza esistente
    *@param assenzaIn riceve un oggetto di tipo assenza
    */
   
   public AssenzaBean(Assenza assenzaIn){
      this.assenza=assenzaIn;
   }
   
   
  /** Campo utilizzato da proprieta' di tipo Bound*/
   private java.beans.PropertyChangeSupport propertyChangeSupport =  new java.beans.PropertyChangeSupport(this);
   
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
    * Getter per la proprieta' id.
    * @return Valore della proprieta' id.
    */
   public int getId() {
      return this.assenza.getId();
   }
   
   /**
    * Getter per la proprieta' dataInizio.
    * @return Valore della proprieta' dataInizio.
    */
   public java.util.Date getDataInizio() {
      if (this.assenza.getDataOraInizio()==null) return new java.util.Date();;
      return DateTimeFacade.String2Date((this.assenza.getDataOraInizio()).getDate());
   }
   
   /**
    * Setter della proprieta' dataInizio.
    * @param dataInizio Nuovo valore della proprieta' dataInizio.
    */
   public void setDataInizio(java.util.Date nuovaDataOraInizio) {
      //recupero la vecchia data in formato Swell
      Date oldDataOraInizio = this.assenza.getDataOraInizio();
      //la converto in java.util.Date
      java.util.Date vecchiaDataOraInizio=new java.util.Date();
      if (oldDataOraInizio!=null) 
         vecchiaDataOraInizio=DateTimeFacade.String2Date(oldDataOraInizio.getDate());
      else
         vecchiaDataOraInizio=null;         
      
      // se sono entrambe null ritorno
      if(vecchiaDataOraInizio==null && nuovaDataOraInizio==null) return;
      
      //creo una nuova data da inserire in formato Swell
      Date newDataOraInizio=new Date();
      if (nuovaDataOraInizio!=null) 
         newDataOraInizio.setDate(DateTimeFacade.Date2String(nuovaDataOraInizio));
      else
         newDataOraInizio=null;
      
      // verifico il cambiamento e salvo se necessario
      if((vecchiaDataOraInizio==null && nuovaDataOraInizio!=null) || !vecchiaDataOraInizio.equals(nuovaDataOraInizio)){
         this.assenza.setDataOraInizio(newDataOraInizio);
         propertyChangeSupport.firePropertyChange(DATA_INIZIO,vecchiaDataOraInizio, nuovaDataOraInizio);
      }
   }
   
   
   /**
    * Getter della proprieta' dataFine.
    * @return Il valore della proprieta' dataFine.
    */
   public java.util.Date getDataFine() {
      if (this.assenza.getDataOraFine()==null) return new java.util.Date();;
      return DateTimeFacade.String2Date((this.assenza.getDataOraFine()).getDate());
   }
   
   /**
    * Setter della proprieta' dataFine.
    * @param dataFine uovo valore della proprieta' dataFine.
    */
   public void setDataFine(java.util.Date nuovaDataOraFine) {
      //recupero la vecchia data in formato Swell
      Date oldDataOraFine = this.assenza.getDataOraFine();
      //la converto in java.util.Date
      java.util.Date vecchiaDataOraFine=new java.util.Date();
      if (oldDataOraFine!=null) 
         vecchiaDataOraFine=DateTimeFacade.String2Date(oldDataOraFine.getDate());
      else
         vecchiaDataOraFine=null;         
      
      // se sono entrambe null ritorno
      if(vecchiaDataOraFine==null && nuovaDataOraFine==null) return;
      
      //creo una nuova data da inserire in formato Swell
      Date newDataOraFine=new Date();
      if (nuovaDataOraFine!=null) 
         newDataOraFine.setDate(DateTimeFacade.Date2String(nuovaDataOraFine));
      else
         newDataOraFine=null;
      
      // verifico il cambiamento e salvo se necessario
      if((vecchiaDataOraFine==null && nuovaDataOraFine!=null) || !vecchiaDataOraFine.equals(nuovaDataOraFine)){
         this.assenza.setDataOraFine(newDataOraFine);
         propertyChangeSupport.firePropertyChange(DATA_FINE,vecchiaDataOraFine, nuovaDataOraFine);
      }
   }
   
   
   /**
    * Getter della proprieta' giustificata.
    * @return Valore della proprieta' giustificata.
    */
   public String getGiustificazione() {
      return this.assenza.getGiustificazione();
   }
   
   /**
    * Setter della proprieta' giustificata.
    * @param giustificata Nuovo valore della proprieta' giustificata.
    */
   public void setGiustificazione(String giustificazione) {
      String oldGiustificazione = this.assenza.getGiustificazione();
      if(oldGiustificazione==null && giustificazione==null) return;
      if((oldGiustificazione==null && giustificazione!=null) || 
              !oldGiustificazione.equals(giustificazione)){
         this.assenza.setGiustificazione(giustificazione);
         propertyChangeSupport.firePropertyChange(GIUSTIFICAZIONE, oldGiustificazione, giustificazione);
      }
   }
   
   /**
    * Getter della proprieta' idPersona.
    * @return Valore della proprieta' idPersona.
    */
   public int getIdPersona(){
      return this.assenza.getPersonaId();
   }
   
   
   /**
    * Setter della proprieta' idPersona.
    * @param idStudente Nuovo valore della proprieta' giustificata.
    */
   public void setIdPersona(int idPersona){
      int oldId= this.assenza.getPersonaId();
      if(oldId!=idPersona){
         this.assenza.setPersonaId(idPersona);
         propertyChangeSupport.firePropertyChange(ID_PERSONA, oldId, idPersona);
      }
   }
   
   /**
    * Getter della proprieta' idTipoAssenza.
    * @return Valore della proprieta' idTipoAssenza.
    */
   public int getIdTipoAssenza(){
      return this.assenza.getTipoAssenzaId();
   }
   
   /**
    * Setter della proprieta' idTipoAssenza.
    * @param idTipoAssenza Nuovo valore della proprieta' idTipoAssenza.
    */
   public void setIdTipoAssenza(int idTipoAssenza){
      int oldId= this.assenza.getTipoAssenzaId();
      if(oldId!=idTipoAssenza){
         this.assenza.setTipoAssenzaId(idTipoAssenza);
         propertyChangeSupport.firePropertyChange(ID_TIPO_ASSENZA, oldId, idTipoAssenza);
      }
   }
   
      /**
    * Getter della proprieta' idEvento.
    * @return Valore della proprieta' idEvento.
    */
   public int getIdEvento(){
      return this.assenza.getEventoId();
   }
   
   /**
    * Setter della proprieta' idEvento.
    * @param idEvento Nuovo valore della proprieta' idEvento.
    */
   public void setIdEvento(int idEvento){
      int oldId= this.assenza.getEventoId();
      if(oldId!=idEvento){
         this.assenza.setEventoId(idEvento);
         propertyChangeSupport.firePropertyChange(ID_EVENTO, oldId, idEvento);
      }
   }
   

   public void salvaSuWS() throws WSDidatticaException_Exception{
      
      if(assenza.getId()!=Costanti.ID_NUOVA_ASSENZA){
         
         WSAssenzaService service = new WSAssenzaService();
         WSAssenza port = service.getWSAssenzaPort();
         // TODO initialize WS operation arguments here
         port.modificaAssenza(this.assenza);
      } else{
         WSAssenzaService service = new WSAssenzaService();
         WSAssenza port = service.getWSAssenzaPort();
         int nuovoId=port.aggiungiAssenza(assenza);
         assenza.setId(nuovoId);
         // TODO handle custom exceptions here
      }
      
      
   }
   
   
   /**Carica un Assenza dal Web Service
    *@param id Id relativo all'assenza che si vuole caricare
    */
   
   public void caricaDaWS(int id) throws UnsupportedOperationException,WSDidatticaException_Exception {
      if(id!=Costanti.ID_NUOVA_ASSENZA){
         
         //Chiamata ad una operazione del web service
         WSAssenzaService service = new WSAssenzaService();
         WSAssenza port = service.getWSAssenzaPort();
         // TODO initialize WS operation arguments here
         ParametriRicercaAssenza insiemeParametri = new ParametriRicercaAssenza();
         insiemeParametri.setId(id);
         // TODO process result here
         List<Assenza> result = port.cercaAssenza(insiemeParametri);
         if(!result.isEmpty()) {
            assenza=result.get(0);
         }
         
      }
      /* viene lanciata questa eccezione per indicare un comportamento
       * che il programmatore non dovrebbe tenere.
       * Non ha senso infatti caricare un oggetto che ha come id quello di default!
       */
      else throw new UnsupportedOperationException("Non si puo' caricare un oggetto appena creato e non salvato");
      
   }
   
   public void cancellaAssenza() throws WSDidatticaException_Exception {
      
      WSAssenzaService service = new WSAssenzaService();
      WSAssenza port = service.getWSAssenzaPort();
      // TODO initialize WS operation arguments here
      port.cancellaAssenza(this.assenza.getId());
      
      
   }
   
   
}
