
/*
 * Nome file:VotoBean.java
 * Data creazione:22 febbraio 2007, 12.05
 * Info svn: $Id: VotoBean.java 881 2007-03-27 18:25:01Z alberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.votoclient.Date;
import nu.mine.egoless.didattica.ws.personaclient.Docente;
import nu.mine.egoless.didattica.ws.personaclient.Studente;
import nu.mine.egoless.didattica.ws.tipovotoclient.TipoVoto;
import nu.mine.egoless.didattica.ws.votoclient.Voto;
import java.lang.UnsupportedOperationException;
import java.util.List;
import nu.mine.egoless.didattica.ws.votoclient.ParametriRicercaVoto;
import nu.mine.egoless.didattica.ws.votoclient.WSVotoService;
import nu.mine.egoless.didattica.ws.votoclient.WSVoto;
import nu.mine.egoless.didattica.ws.votoclient.WSDidatticaException_Exception;
import nu.mine.egoless.supporto.DateTimeFacade;
/**
 *
 * @author marghe
 */

/** Questa classe si interfaccierà alla parte di GUI per la gestione delle informazioni
 * relative all'oggetto Voto
 */

public class VotoBean {
   
   /**
    * Costanti usate dai metodo fireChangeProperty perche' le classi che gestiscono
    * l'intefaccia grafica riescano a discriminare quali proprieta' della classe sono
    * cambiate
    */
   public static final String DATA="data";
   
   public static final String EXTRA="extra";
   
   public static final String ID="id";
   
   public static final String VALORE="valore";
   
   public static final String TIPO_VOTO="tipoVoto";
   
   public static final String INSEGNANTE="insegnante";
   
   public static final String STUDENTE="studente";
   
   public static final String MATERIA_INSEGNAMENTO="materiaInsegnamento";
   
   public static final String CLASSE="classe";
   
   public static final String TIPO_PROVA="tipoProva";
   
   public static final String DESCRIZIONE="descrizione";
   
   /**
    * Crea una nuova istanza di VotoBean
    */
   public VotoBean() {
      voto=new Voto();
      voto.setId(Costanti.ID_NUOVO_VOTO);
   }
   /**
    * Crea una nuova istanza di VotoBean a partire dall'id
    * @param id Valore dell'id del nuovo oggetto
    */
   public VotoBean(int id){
      voto=new Voto();
      voto.setId(id);
   }
   
   public VotoBean(Voto v){
      voto=v;
   }
   
   /**
    * Tiene il valore della proprietà voto
    */
   private Voto voto;
   
   
   /**
    * Campo utilizzato da proprietà di tipo Bound.
    */
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /**
    * Aggiunge un propertyChangeListener alla lista dei listener
    * @param l Il listener da aggiungere.
    */
   public void addPropertyChangeListener(String nomeProprieta,PropertyChangeListener l) {
      propertyChangeSupport.addPropertyChangeListener(nomeProprieta,l);
   }
   
   /**
    * Rimuove un propertyChangeListener alla lista dei listener
    * @param l Il listener da rimuovere.
    */
   public void removePropertyChangeListener(String nomeProprieta,PropertyChangeListener l) {
      propertyChangeSupport.removePropertyChangeListener(nomeProprieta,l);
   }
   
   /**
    * Getter per la proprieta' id.
    * @return Valore della proprieta' id.
    */
   public int getId() {
      return this.voto.getId();
   }
   
   /**
    * Getter for property data.
    * @return Value della proprietà data.
    */
   public java.util.Date getData() {
      if (this.voto.getData()==null) return new java.util.Date();
      return DateTimeFacade.String2Date(this.voto.getData().getDate());
   }
   
   /**
    * Setter per la proprietà data.
    * @param data New Valore della proprietà data.
    */
   public void setData(java.util.Date nuovaData) {
      //recupero la vecchia data in formato Swell
      Date oldData = this.voto.getData();
      //la converto in java.util.Date
      java.util.Date vecchiaData=new java.util.Date();
      if (oldData!=null) 
         vecchiaData=DateTimeFacade.String2Date(oldData.getDate());
      else
         vecchiaData=null;         
      
      // se sono entrambe null ritorno
      if(vecchiaData==null && nuovaData==null) return;
      
      //creo una nuova data da inserire in formato Swell
      Date newData=new Date();
      if (nuovaData!=null) 
         newData.setDate(DateTimeFacade.Date2String(nuovaData));
      else
         newData=null;
      
      // verifico il cambiamento e salvo se necessario
      if((vecchiaData==null && nuovaData!=null) || !vecchiaData.equals(nuovaData)){
         this.voto.setData(newData);
         propertyChangeSupport.firePropertyChange(DATA,vecchiaData, nuovaData);
      }
   }
   
   /**
    * Tiene il valore della proprietà valore.
    */
   //private int valore;
   
 
 
    /*
    public void setInsegnante(Insegnante insegnante){
      Insegnante oldInsegnante= this.voto.getInsegnante();
      this.voto.setInsegnante(insegnante);
      propertyChangeSupport.firePropertyChange (INSEGNANTE, oldInsegnante, insegnante);
    }
     
    public Insegnante getInsegnante(){
      return this.voto.getInsegnante();
    }
     
     
     
    public void setMateria(MateriaInsegnamento materia){
      MateriaInsegnamento oldMateria= this.voto.getMateriaInsegnamento();
      if(!oldMateria.equals(materia)){
         this.voto.setMateriaInsegnamento(materia);
         propertyChangeSupport.firePropertyChange (MATERIA_INSEGNAMENTO, oldMateria, materia);
      }
    }
     
    public MateriaInsegnamento getMateria(){
      return this.voto.getMateriaInsegnamento();
    }
     
     
    public void setStudente(int idStudente){
      int oldIdStudente= this.voto.getIdStudente();
      if( oldIdStudente!=idStudente){
         this.voto.setIdStudente(idStudente);
         propertyChangeSupport.firePropertyChange (STUDENTE, oldIdStudente, idStudente);
      }
    }
     
    public int getStudente(){
      return this.voto.getStudente();
    }
     
     
    public void setTipoVoto(TipoVoto tipoV){
       TipoVoto oldTipoVoto= this.voto.getTipoVoto();
       this.voto.setTipoVoto(tipoV);
       propertyChangeSupport.firePropertyChange (TIPO_VOTO, oldTipoVoto, tipoV);
    }
     
    public TipoVoto getTipoVoto(){
      return this.voto.getTipoVoto();
    }
     */
   
   /**
    * Setter per la proprietà idInsegnante.
    * @param idInsegnante Nuovo valore della proprietà idInsegnante.
    */
   public void setIdInsegnante(int idInsegnante){
      int oldIdInsegnante= this.voto.getDocenteId();
      this.voto.setDocenteId(idInsegnante);
      propertyChangeSupport.firePropertyChange(INSEGNANTE, oldIdInsegnante, idInsegnante);
   }
   /**
    * Getter per la proprietà idInsegnante.
    * @return Valore della proprietà idInsegnante.
    */
   public int getIdInsegnante(){
      return this.voto.getDocenteId();
   }
   
   
   /**
    * Setter per la proprietà idMateria.
    * @param idMateria Nuovo valore della proprietà idMateria.
    */
   public void setIdMateria(int idMateria){
      int oldIdMateria= this.voto.getMateriaId();
      if(oldIdMateria!=idMateria){
         this.voto.setMateriaId(idMateria);
         propertyChangeSupport.firePropertyChange(MATERIA_INSEGNAMENTO, oldIdMateria, idMateria);
      }
   }
   /**
    * Getter per la proprietà idMateria.
    * @return Valore della proprietà idMateria.
    */
   public int getIdMateria(){
      return this.voto.getMateriaId();
   }
   
     /**
    * Setter per la proprietà idClasse.
    * @param idClasse Nuovo valore della proprietà idClasse.
    */
   public void setIdClasse(int idClasse){
      int oldIdClasse= this.voto.getClasseId();
      if(oldIdClasse!=idClasse){
         this.voto.setClasseId(idClasse);
         propertyChangeSupport.firePropertyChange(CLASSE, oldIdClasse, idClasse);
      }
   }
   /**
    * Getter per la proprietà idClasse.
    * @return Valore della proprietà idClasse.
    */
   public int getIdClasse(){
      return this.voto.getClasseId();
   }
   
   /**
    * Setter per la proprietà idStudente.
    * @param idStudente Nuovo valore della proprietà idStudente.
    */
   public void setIdStudente(int idStudente){
      int oldIdStudente= this.voto.getStudenteId();
      if( oldIdStudente!=idStudente){
         this.voto.setStudenteId(idStudente);
         propertyChangeSupport.firePropertyChange(STUDENTE, oldIdStudente, idStudente);
      }
   }
   /**
    * Getter per la proprietà idStudente.
    * @return Valore della proprietà idStudente.
    */
   public int getIdStudente(){
      return this.voto.getStudenteId();
   }
   
   /**
    * Setter per la proprietà idTipoV.
    * @param idTipoV Nuovo valore della proprietà idTipoV.
    */
   public void setIdTipoVoto(int idTipoV){
      int oldTipoVoto= this.voto.getTipoVotoId();
      this.voto.setTipoVotoId(idTipoV);
      propertyChangeSupport.firePropertyChange(TIPO_VOTO, oldTipoVoto, idTipoV);
   }
   /**
    * Getter per la proprietà idTipoVoto.
    * @return Valore della proprietà idTipoVoto.
    */
   public int getIdTipoVoto(){
      return this.voto.getTipoVotoId();
   }
      
      /**
    * Setter per la proprietà idTipoP.
    * @param idTipoP Nuovo valore della proprietà idTipoP.
    */
   public void setIdTipoProva(int idTipoP){
      int oldTipoProva= this.voto.getTipoProvaId();
      this.voto.setTipoProvaId(idTipoP);
      propertyChangeSupport.firePropertyChange(TIPO_PROVA, oldTipoProva, idTipoP);
   }
   /**
    * Getter per la proprietà idTipoProva.
    * @return Valore della proprietà idTipoProva.
    */
   public int getIdTipoProva(){
      return this.voto.getTipoProvaId();
   }
   
      /**
    * Setter per la proprietà descrizione.
    * @param descrizione Nuovo valore della proprietà descrizione.
    */
   public void setDescrizione(String descrizione){
      String oldDescrizione = this.voto.getDescrizione();
      if(oldDescrizione==null && descrizione==null) return;
      if((oldDescrizione==null && descrizione!=null) 
            || !oldDescrizione.equals(descrizione)){
         this.voto.setDescrizione(descrizione);
         propertyChangeSupport.firePropertyChange(DESCRIZIONE, oldDescrizione, descrizione);
      }
         
   }
   /**
    * Getter per la proprietà descrizione.
    * @return Valore della proprietà descrizione.
    */
   public String getDescrizione(){
      return this.voto.getDescrizione();
   }
   
   /** Carica l'Oggetto corrispondente all'id dal Web Service
    * @param id Id dell'oggetto da caricare
    */
   
   public void caricaDaWS(int id)  throws UnsupportedOperationException,WSDidatticaException_Exception{
      if(id!=Costanti.ID_NUOVO_VOTO){
         
         //Chiamata ad una operazione del web service
         WSVotoService service = new WSVotoService();
         WSVoto port = service.getWSVotoPort();
         // TODO initialize WS operation arguments here
         ParametriRicercaVoto insiemeParametri = new ParametriRicercaVoto();
         insiemeParametri.setId(id);
         // TODO process result here
         List<Voto> result = port.cercaVoto(insiemeParametri);
         if(!result.isEmpty()) {
            voto=result.get(0);
            
         }
         
         
      }
      /* viene lanciata questa eccezione per indicare un comportamento
       * che il programmatore non dovrebbe avere.
       * Non ha senso infatti caricare un oggetto che ha come id quello di default!
       */
      else throw new UnsupportedOperationException();
   }
   
   /** Salva l'oggetto sul Web Service  */
   
   public void salvaSuWS() throws WSDidatticaException_Exception{
      
      if(voto.getId()!=Costanti.ID_NUOVO_VOTO){
         
         WSVotoService service = new WSVotoService();
         WSVoto port = service.getWSVotoPort();
         // TODO initialize WS operation arguments here
         port.modificaVoto(this.voto);
         // TODO handle custom exceptions here
      } else{
         WSVotoService service = new WSVotoService();
         WSVoto port = service.getWSVotoPort();
         
//          int nuovoId=port.ottieniProssimoIdVoto();
          /*perche' l'oggetto e' stato creato con l'id di default. Al momento del
           *salvataggio, in modo del tutto trasparente all'utente gli viene conferito
           *un valore consistente.
           */
         int nuovoId=port.aggiungiVoto(voto);
         voto.setId(nuovoId);
         // TODO handle custom exceptions here
      }
      
   }
   
   

   public void cancellaVoto()throws WSDidatticaException_Exception {
      
      WSVotoService service = new WSVotoService();
      WSVoto port = service.getWSVotoPort();
      // TODO initialize WS operation arguments here
      port.cancellaVoto(this.voto.getId());
      
      // TODO handle custom exceptions here
      
   }
   
}
