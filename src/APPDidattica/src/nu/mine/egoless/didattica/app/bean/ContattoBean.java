/*
 * Nome file:ContattoBean.java
 * Data creazione:22 febbraio 2007, 12.24
 * Info svn: $Id: ContattoBean.java 874 2007-03-27 14:18:23Z alberto $
 */


package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import nu.mine.egoless.didattica.ws.contattoclient.Contatto;
import nu.mine.egoless.didattica.ws.contattoclient.ParametriRicercaContatto;
import nu.mine.egoless.didattica.ws.personaclient.Persona;
import nu.mine.egoless.didattica.ws.contattoclient.WSContattoService;
import nu.mine.egoless.didattica.ws.contattoclient.WSContatto;
import nu.mine.egoless.didattica.ws.contattoclient.WSDidatticaException_Exception;
import java.util.List;

/** Questa classe si interfaccierà alla parte di GUI per la gestione delle informazioni
 * relative al Contatto
 */

public class ContattoBean {
   
   /**
    * Costanti usate dai metodo fireChangeProperty perche' le classi che gestiscono
    * l'intefaccia grafica riescano a discriminare quali proprieta' della classe sono
    * cambiate
    */
   
   public static final String CAP="cap";
   
   public static final String CITTA="citta";
   
   public static final String CIVICO="civico";
   
   public static final String FAX="fax";
   
   public static final String ID_PERSONA="idPersona";
   
   public static final String ID_NAZIONE="idNazione";
   
   public static final String PROVINCIA="provincia";
   
   public static final String TELEFONO_PRINCIPALE="telefonoPrincipale";
   
   public static final String TELEFONO_SECONDARIO="telefonoSecondario";
   
   public static final String VIA="via";
   
  /** Campo utilizzato da proprieta' di tipo Bound*/
   protected PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /**
    * Tiene il valore della proprieta' contatto.
    */
   private Contatto contatto;
   
   /**
    * Crea una nuova istanza ContattoBean
    */
   public ContattoBean() {
      contatto=new Contatto();
   }
   /**
    * Crea una nuova istanza ContattoBean partendo da contattoIn
    *     @param contattoIn Oggetto in ingresso
    */
   
   public ContattoBean(Contatto contattoIn){
      contatto=contattoIn;
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
    * Getter per la proprieta' id.
    * @return Valore della proprieta' id.
    */
   public int getId() {
      return this.contatto.getId();
   }
   
   /**
    * Getter per la proprieta' via.
    * @return Valore della proprieta' via.
    */
   public String getVia() {
      String t = this.contatto.getVia();
      if( t == null ) t="";
      return t;
   }
   
   /**
    * Setter per la proprieta' via.
    * @param via Nuovo valore della proprieta' via.
    */
   public void setVia(String via) {
      String oldVia = this.contatto.getVia();
      if (oldVia==null && via==null) return;
      if((oldVia==null && via!=null) || !oldVia.equals(via)){
         this.contatto.setVia(via);
         propertyChangeSupport.firePropertyChange(VIA, oldVia, via);
      }
   }
   
   /**
    * Getter per la proprieta' civico.
    * @return Valore della proprieta' civico.
    */
   public String getCivico() {
      String t = this.contatto.getCivico();
      if( t == null ) t="";
      return t;
   }
   
   /**
    * Setter per la proprieta' civico.
    * @param civico Nuovo valore della proprieta' civico.
    */
   public void setCivico(String civico) {
      String oldCivico = this.contatto.getCivico();
      if (oldCivico==null && civico==null) return;
      if((oldCivico==null && civico!=null) || !oldCivico.equals(civico)){
         this.contatto.setCivico(civico);
         propertyChangeSupport.firePropertyChange(CIVICO, oldCivico, civico);
      }
   }
   
   
   /**
    * Getter per la proprieta' CAP.
    * @return Valore della proprieta' CAP.
    */
   public String getCAP() {
      String t = this.contatto.getCap();
      if( t == null ) t="";
      return t;
   }
   
   /**
    * Setter per la proprieta' CAP.
    * @param cap Nuovo valore della proprieta' CAP.
    */
   public void setCAP(String cap) {
      String oldCAP = this.contatto.getCap();
      if (oldCAP==null && cap==null) return;
      if((oldCAP==null && cap!=null) || !oldCAP.equals(cap)){
         this.contatto.setCap(cap);
         propertyChangeSupport.firePropertyChange(CAP, oldCAP, cap);
      }
   }
   
   
   /**
    * Getter per la proprieta' citta.
    * @return Valore della proprieta' citta.
    */
   public String getCitta() {
      String t = this.contatto.getCitta();
      if( t == null ) t="";
      return t;
   }
   
   /**
    * Setter per la proprieta' citta.
    * @param citta' Nuovo valore della proprieta' citta.
    */
   public void setCitta(String citta) {
      String oldCitta = this.contatto.getCitta();
      if (oldCitta==null && citta==null) return;
      if((oldCitta==null && citta!=null) || !oldCitta.equals(citta)){
         this.contatto.setCitta(citta);
         propertyChangeSupport.firePropertyChange(CITTA, oldCitta, citta);
      }
   }
   
   /**
    * Getter per la proprieta' provincia.
    * @return Valore della proprieta' provincia.
    */
   public String getProvincia() {
      String t = this.contatto.getProvincia();
      if( t == null ) t="";
      return t;
   }
   
   /**
    * Setter per la proprieta' provincia.
    * @param provincia Nuovo valore della proprieta' provincia.
    */
   public void setProvincia(String provincia) {
      String oldProvincia = this.contatto.getProvincia();
      if (oldProvincia==null && provincia==null) return;
      if((oldProvincia==null && provincia!=null) || !oldProvincia.equals(provincia)){
         this.contatto.setProvincia(provincia);
         propertyChangeSupport.firePropertyChange(PROVINCIA, oldProvincia, provincia);
      }
   }
   
   
   /**
    * Getter per la proprieta' telefonoPrincipale.
    * @return Valore della proprieta' telefonoPrincipale.
    */
   public String getTelefonoPrincipale(){
      String t = this.contatto.getTelefonoPrincipale();
      if( t == null ) t="";
      return t;   
   }
   
   /**
    * Setter per la proprieta' telefonoPrincipale.
    * @param telefonoPrincipale Nuovo valore della proprieta' telefonoPrincipale.
    */
   public void setTelefonoPrincipale(String telefonoPrincipale) {
      String oldTelefonoPrincipale = this.contatto.getTelefonoPrincipale();
      if (oldTelefonoPrincipale==null && telefonoPrincipale==null) return;
      if((oldTelefonoPrincipale==null && telefonoPrincipale!=null) || !oldTelefonoPrincipale.equals(telefonoPrincipale)){
         this.contatto.setTelefonoPrincipale(telefonoPrincipale);
         propertyChangeSupport.firePropertyChange(TELEFONO_PRINCIPALE, oldTelefonoPrincipale, telefonoPrincipale);
      }
   }
   
   /**
    * Getter per la proprieta' telefonoSecondario.
    * @return Valore della proprieta' telefonoSecondario.
    */
   public String getTelefonoSecondario() {
      String t = this.contatto.getTelefonoSecondario();
      if( t == null ) t="";
      return t;   
   }
   
   /**
    * Setter per la proprieta' telefonoSecondario.
    * @param telefonoSecondario Nuovo valore della proprieta' telefonoSecondario.
    */
   public void setTelefonoSecondario(String telefonoSecondario) {
      String oldTelefonoSecondario = this.contatto.getTelefonoSecondario();
      if (oldTelefonoSecondario==null && telefonoSecondario==null) return;
      if((oldTelefonoSecondario==null && telefonoSecondario!=null) || !oldTelefonoSecondario.equals(telefonoSecondario)){
         this.contatto.setTelefonoSecondario(telefonoSecondario);
         propertyChangeSupport.firePropertyChange(TELEFONO_SECONDARIO, oldTelefonoSecondario, telefonoSecondario);
      }
   }
   
   
   /**
    * Getter per la proprieta' fax.
    * @return Valore della proprieta' fax.
    */
   public String getFax() {
      String t = this.contatto.getFax();
      if( t == null ) t="";
      return t;
   }
   
   /**
    * Setter per la proprieta' fax.
    * @param fax Nuovo valore della proprieta' fax.
    */
   public void setFax(String fax) {
      String oldFax = this.contatto.getFax();
      if (oldFax==null && fax==null) return;
      if((oldFax==null && fax!=null) || !oldFax.equals(fax)){
         this.contatto.setFax(fax);
         propertyChangeSupport.firePropertyChange(FAX, oldFax, fax);
      }
   }
   
     /**
    * Getter per la proprieta' idNazione.
    * @return Valore della proprieta' idNazione.
    */
   public int getIdNazione() {
      return this.contatto.getNazioneId();
   }
   
   /**
    * Setter per la proprieta' idNazione.
    * @param idNazione Nuovo valore della proprieta' idNazione.
    */
   public void setIdNazione(int idNazione) {
      int oldId = this.contatto.getNazioneId();
      if(oldId!=idNazione){
         this.contatto.setNazioneId(idNazione);
         propertyChangeSupport.firePropertyChange(ID_NAZIONE, oldId, idNazione);
      }
   }


   /** Salva il Contatto corrispondente sul Web Service
    */
   
   public void salvaSuWS() throws WSDidatticaException_Exception{
      
      if(contatto.getId()!=Costanti.ID_NUOVO_CONTATTO){
         
         WSContattoService service = new WSContattoService();
         WSContatto port = service.getWSContattoPort();
         // TODO initialize WS operation arguments here
         port.modificaContatto(this.contatto);
      } else{
         WSContattoService service = new WSContattoService();
         WSContatto port = service.getWSContattoPort();
         
         //int nuovoId=port.ottieniProssimoIdContatto();
          /*perche' l'oggetto e' stato creato con l'id di default. Al momento del
           *salvataggio, in modo del tutto trasparente all'utente gli viene conferito
           *un valore consistente.
           */
         
         int nuovoId=port.aggiungiContatto(this.contatto);
         contatto.setId(nuovoId);
         
         // TODO handle custom exceptions here
      }
      
   }
   /** Carica il Contatto corrispondente all'id dal Web Service
    * @param id Id del contatto da caricare
    */
   
   public void caricaDaWS(int id)throws WSDidatticaException_Exception{
      if(id!=Costanti.ID_NUOVO_CONTATTO){
         
         //Chiamata ad una operazione del web service
         WSContattoService service = new WSContattoService();
         WSContatto port = service.getWSContattoPort();
         // TODO initialize WS operation arguments here
         ParametriRicercaContatto insiemeParametri = new ParametriRicercaContatto();
         insiemeParametri.setId(id);
         // TODO process result here
         List<Contatto> result = port.cercaContatto(insiemeParametri);
         if(!result.isEmpty()) {
            contatto=result.get(0);
         }
         
      }
   }
   /** Metodo che cancella il contatto a partire dal suo id
    * @param idContatto Valore dell'id del contatto da cancellare
    */
   
   public void cancellaContatto()throws WSDidatticaException_Exception {
      
      WSContattoService service = new WSContattoService();
      WSContatto port = service.getWSContattoPort();
      // TODO initialize WS operation arguments here
      port.cancellaContatto(this.contatto.getId());
      
   }
   
}



