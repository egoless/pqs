/*
 * Nome file:PersonaBean.java
 * Data creazione:10 marzo 2007, 9.21
 * Info svn: $Id: PersonaBean.java 908 2007-04-13 13:02:28Z stefano $
 */


package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.personaclient.Date;
import nu.mine.egoless.didattica.ws.personaclient.Persona;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;
import nu.mine.egoless.didattica.ws.contattoclient.Contatto;
import nu.mine.egoless.didattica.ws.religioneclient.Religione;
import nu.mine.egoless.supporto.DateTimeFacade;

/**
 *
 * @author STEFANO
 */
public abstract class PersonaBean {
   
   
   /**
    * Costanti usate dai metodo fireChangeProperty perche' le classi che gestiscono
    * l'intefaccia grafica riescano a discriminare quali proprieta' della classe sono
    * cambiate
    */
   public static final String CODICE_FISCALE = "codiceFiscale";
   
   public static final String COGNOME = "cognome";
   
   public static final String DATA_NASCITA = "dataNascita";
   
   public static final String DATA_ISCRIZIONE = "dataIscrizione";
   
   public static final String EXTRA = "extra";
   
   public static final String ID = "id";
   
   public static final String INDIRIZZO_RESIDENZA = "indirizzoResidenza";
   
   public static final String NOME = "nome";
   
   public static final String PORTATORE_HANDICAP = "portatoreHandicap";
   
   public static final String RELIGIONE= "religione";
   
   public static final String NAZIONALITA= "nazionalita";
   
   public static final String ISTITUTO= "istituto";
   
   public static final String SESSO= "sesso";
  
   
  /** Campo utilizzato da proprieta' di tipo Bound*/
   protected PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /**
    *Contiene il valore dell'oggetto Persona
    */
   protected Persona persona;
   
   /**
    * Crea una nuova istanza di PersonaBean
    */
   public PersonaBean() {
   /*   persona= new Persona();
    * creare un oggetto di tipo persona non serve in quanto
    * il reference all'oggetto di tipo persona verr� inizializzato
    * nelle sottoclassi che ereditano persona
    */
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
    * Ritorna la propriet� codiceFiscale.
    * @ritorna il valore della propriet� codiceFiscale.
    */
   public String getCodiceFiscale() {
      String t = this.persona.getCodiceFiscale();
      if( t == null ) t="";
      return t;            
   }
   
   /**
    * Setta la propriet� codiceFiscale.
    * @param codiceFiscale Nuovo valore della propriet� codiceFiscale.
    */
   public void setCodiceFiscale(String codiceFiscale) {
      String oldCodiceFiscale = this.persona.getCodiceFiscale();
      if(oldCodiceFiscale==null && codiceFiscale==null) return;
      if ((oldCodiceFiscale==null && codiceFiscale!=null) || !oldCodiceFiscale.equals(codiceFiscale)){
         this.persona.setCodiceFiscale(codiceFiscale);
         propertyChangeSupport.firePropertyChange(CODICE_FISCALE, oldCodiceFiscale, codiceFiscale);
      }
   }
   
   /**
    * Ritorna la propriet� cognome.
    * @ritorna il valore della propriet� cognome.
    */
   public String getCognome() {
      String t = this.persona.getCognome();
      if( t == null ) t="";
      return t;        
   }
   
   /**
    * Setta la propriet� cognome.
    * @param cognome Nuovo valore della propriet� cognome.
    */
   public void setCognome(String cognome) {
      String oldCognome = this.persona.getCognome();
      if(oldCognome==null && cognome==null) return;
      if ((oldCognome==null && cognome!=null) || !oldCognome.equals(cognome)){
         this.persona.setCognome(cognome);
         propertyChangeSupport.firePropertyChange(COGNOME, oldCognome, cognome);
      }
   }
   
   /**
    * Ritorna la propriet� dataNascita.
    * @return Valore della propriet� dataNascita.
    */
   public java.util.Date getDataNascita() {
      if (this.persona.getDataNascita()==null) return new java.util.Date();
      return DateTimeFacade.String2Date(this.persona.getDataNascita().getDate());
   }
   
   /**
    * Setta la propriet� dataNascita.
    * @param dataNascita Nuovo valore della propriet� dataNascita.
    */
   public void setDataNascita(java.util.Date nuovaDataNascita) {
      //recupero la vecchia data in formato Swell
      Date oldDataNascita = this.persona.getDataNascita();
      //la converto in java.util.Date
      java.util.Date vecchiaDataNascita=new java.util.Date();
      if (oldDataNascita!=null) 
         vecchiaDataNascita=DateTimeFacade.String2Date(oldDataNascita.getDate());
      else
         vecchiaDataNascita=null;         
      
      // se sono entrambe null ritorno
      if(vecchiaDataNascita==null && nuovaDataNascita==null) return;
      
      //creo una nuova data da inserire in formato Swell
      Date newDataNascita=new Date();
      if (nuovaDataNascita!=null) 
         newDataNascita.setDate(DateTimeFacade.Date2String(nuovaDataNascita));
      else
         newDataNascita=null;
      
      // verifico il cambiamento e salvo se necessario
      if((vecchiaDataNascita==null && nuovaDataNascita!=null) || !vecchiaDataNascita.equals(nuovaDataNascita)){
         this.persona.setDataNascita(newDataNascita);
         propertyChangeSupport.firePropertyChange(DATA_NASCITA,vecchiaDataNascita, nuovaDataNascita);
      }
   }
   
   
   
   /**
    * Ritorna la propriet� id.
    * @return Valore della propriet� id.
    */
   public int getId() {
      return this.persona.getId();
   }
   
   
   /**
    * Ritorna la propriet� indirizzoResidenza.
    * @return Valore della propriet� indirizzoResidenza.
    */
   public int getIdIndirizzoResidenza() {
      return this.persona.getContattoId();
   }
   

   /**
    * Setta la propriet� contatto.
    * @param contatto Nuovo valore della propriet� contatto.
    */
   public void setIdIndirizzoResidenza(int idIndirizzoResidenza) {
      int oldIdContatto = this.persona.getContattoId();
      if (oldIdContatto!=idIndirizzoResidenza){
         this.persona.setContattoId(idIndirizzoResidenza);
         propertyChangeSupport.firePropertyChange(INDIRIZZO_RESIDENZA, oldIdContatto, idIndirizzoResidenza);
      }
   }
   
      /**
    * Ritorna la propriet� indirizzoResidenza.
    * @return Valore della propriet� indirizzoResidenza.
    */
   public int getIdIstituto() {
      return this.persona.getIstitutoId();
   }
   

   /**
    * Setta la propriet� istituto.
    * @param istituto Nuovo valore della propriet� istituto.
    */
   public void setIdIstituto(int idIstituto) {
      int oldIdIstituto = this.persona.getIstitutoId();
      if (oldIdIstituto!=idIstituto){
         this.persona.setIstitutoId(idIstituto);
         propertyChangeSupport.firePropertyChange(ISTITUTO, oldIdIstituto, idIstituto);
      }
   }
   
   /**
    * Ritorna la propriet� nome.
    * @return Valore della propriet� nome.
    */
   public String getNome() {
      String t = this.persona.getNome();
      if( t == null ) t="";
      return t;              
   }
   
   /**
    * Setta la propriet� nome.
    * @param nome Nuovo valore della propriet� nome.
    */
   public void setNome(String nome) {
      String oldNome = this.persona.getNome();
      if(oldNome==null && nome==null) return;
      if ((oldNome==null && nome!=null) || !oldNome.equals(nome)){
         this.persona.setNome(nome);
         propertyChangeSupport.firePropertyChange(NOME, oldNome, nome);
      }
   }
   
      /**
    * Ritorna la propriet� sesso. Se non � stato impostato ritorna -1
    * @return Valore della propriet� sesso.
    */
   public int getSesso() {
      if (this.persona.getSesso()!=null)
         return this.persona.getSesso().intValue();
      else 
         return -1;
   }
   
   /**
    * Setta la propriet� sesso.
    * @param sesso Nuovo valore della propriet� sesso.
    */
   public void setSesso(int sesso) {
      Integer oldSesso = this.persona.getSesso();
      if (oldSesso==null) oldSesso=new Integer(-1);
      
          
      if (!oldSesso.equals(sesso)){
         this.persona.setSesso(sesso);
         propertyChangeSupport.firePropertyChange(SESSO, oldSesso.intValue(), sesso);
      }
   }
   
   /**
    * Ritorna la propriet� portatoreHandicap.
    * @return Valore della propriet� portatoreHandicap.
    */
   public boolean isPortatoreHandicap() {
      if (persona.isPortatoreHandicap()==null) return false;
      return this.persona.isPortatoreHandicap().booleanValue();
   }
   
   /**
    * Setter per la propriet� portatoreHandicap.
    * @param portatoreHandicap Nuovo valore della propriet� portatoreHandicap.
    */
   public void setPortatoreHandicap(boolean portatoreHandicap) {
      boolean oldPortatoreHandicap;
      if (persona.isPortatoreHandicap()==null) oldPortatoreHandicap=false;
      else oldPortatoreHandicap = this.persona.isPortatoreHandicap().booleanValue();
      if (portatoreHandicap!=oldPortatoreHandicap){
         this.persona.setPortatoreHandicap(portatoreHandicap);
         propertyChangeSupport.firePropertyChange(PORTATORE_HANDICAP, new Boolean(oldPortatoreHandicap), new Boolean(portatoreHandicap));
      }
   }
   
   /**
    * Setter per la propriet� idNazionalita.
    * @param idNazionalita Nuovo valore della propriet� idNazionalita.
    */
   public void setIdNazionalita(int idNazionalita) {
      int oldIdNazionalita = this.persona.getNazioneId();
      if(oldIdNazionalita!=idNazionalita){
         this.persona.setNazioneId(idNazionalita);
         propertyChangeSupport.firePropertyChange(NAZIONALITA, oldIdNazionalita, idNazionalita);
      }
   }
   
   /**
    * Ritorna la propriet� idNazionalita.
    * @return Valore della propriet� idNazionalita.
    */
   public int getIdNazionalita(){
      return this.persona.getNazioneId();
      //  return 0;
   }
   
   /**
    * Setter per la propriet� idReligione.
    * @param idReligione Nuovo valore della propriet� idReligione.
    */
   public void setIdReligione(int idReligione) {
      int oldIdReligione = this.persona.getReligioneId();
      if (oldIdReligione!=idReligione){
         this.persona.setReligioneId(idReligione);
         propertyChangeSupport.firePropertyChange(RELIGIONE, oldIdReligione, idReligione);
      }
   }
   /**
    * Ritorna la propriet� idReligione.
    * @return Valore della propriet� idReligione.
    */
   public int getIdReligione(){
      return this.persona.getReligioneId();
      //  return 0;
   }
   
   /** Carica l'Oggetto corrispondente all'id dal Web Service
    * @param id Id dell'oggetto da caricare
    */
   
   public abstract void caricaDaWS(int id) throws UnsupportedOperationException,WSDidatticaException_Exception;
   
   /** Salva l'oggetto sul Web Service  */
   public abstract void salvaSuWS()throws WSDidatticaException_Exception;
   
   
   
}