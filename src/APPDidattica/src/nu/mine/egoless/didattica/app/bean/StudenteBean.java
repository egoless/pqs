/*
 * Nome file:StudenteBean.java
 * Data creazione:6 marzo 2007, 9.26
 * Info svn: $Id: StudenteBean.java 919 2007-04-18 14:51:01Z eric $
 */


package nu.mine.egoless.didattica.app.bean;

import nu.mine.egoless.didattica.ws.personaclient.Studente;
import nu.mine.egoless.didattica.ws.contattoclient.Contatto;
import nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaStudente;
import nu.mine.egoless.didattica.ws.personaclient.WSPersonaService;
import nu.mine.egoless.didattica.ws.personaclient.WSPersona;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;
import nu.mine.egoless.didattica.ws.personaclient.Date;
import java.lang.UnsupportedOperationException;
import java.util.List;
import nu.mine.egoless.supporto.DateTimeFacade;
/**
 *
 * @author STEFANO
 * Questa classe si interfacciera' alla parte di GUI per la gestione delle informazioni
 * relative allo studente
 */
public class StudenteBean extends PersonaBean {
   
   /**
    * Costanti usate dai metodo fireChangeProperty perche' le classi che gestiscono
    * l'intefaccia grafica riescano a discriminare quali proprieta' della classe sono
    * cambiate
    */      
   
   public static final String DATA_ISCRIZIONE="dataIscrizione";
   
   public static final String STUDENTE="studente";
   
   public static final String MATRICOLA="matricola";
   
   public static final String ID_CLASSE="idClasse";
   
   private Studente studente;
   
   private int idClasse;
   
   /** La classe verra' costruita passando l'identificativo */
   
   public StudenteBean(int id) {
      studente=new Studente();
      studente.setId(id);
      persona=studente;
      idClasse=0;
   }
   
   /** Costruttore senza parametri */
   public StudenteBean() {
      //creo un nuovo studente
      studente=new Studente();
      //imposto il valore id di default
      studente.setId(Costanti.ID_NUOVA_PERSONA);
      
      /*l'oggetto persona ereditato fara' riferimento all'oggetto studente:
       *serve nel caso in cui io debba riferirmi solo alle proprieta' ereditate
       *da persona evitando Cast pericolosi.
       */
      persona=studente;
   }

 
   
   /**
    * Getter per la proprietà matricola.
    * @return Valore della proprietà matricola.
    */
   public String getMatricola() {
      String t = this.studente.getMatricola();
      if( t == null ) t="";
      return t;          
   }

   /**
    * Setter per la proprietà matricola.
    * @param matricola Nuovo valore della proprietà matricola.
    */
   public void setMatricola(String matricola) {
      String oldMatricola = this.studente.getMatricola();
      if(oldMatricola==null && matricola==null) return;
      if((oldMatricola==null && matricola!=null) || !oldMatricola.equals(matricola)){
         this.studente.setMatricola(matricola);
         propertyChangeSupport.firePropertyChange (MATRICOLA, oldMatricola, matricola);
      }
   }   
   
   /**
    * Getter per la proprietà idClasse.
    * @return Valore della proprietà idClasse.
    */
   public int getIdClasse() {
         return idClasse;
   }

   /**
    * Setter per la proprietà dataIscrizione.
    * @param dataIscrizione Nuovo valore della proprietà dataIscrizione.
    */
   public void setIdClasse(int nuovoIdClasse) {
      if(this.idClasse!=nuovoIdClasse){
         int oldIdClasse=idClasse;
         idClasse=nuovoIdClasse;
         propertyChangeSupport.firePropertyChange(ID_CLASSE, oldIdClasse, nuovoIdClasse);
      }
   }
   
   public java.util.Date getDataIscrizione() {
      if (this.studente.getDataIscrizione()==null) return new java.util.Date();
      return DateTimeFacade.String2Date(this.studente.getDataIscrizione().getDate());
   }

   /**
    * Setta la proprietà dataIscrizione.
    * @param dataNascita Nuovo valore della proprietà dataIscrizione.
    */
   public void setDataIscrizione(java.util.Date nuovaDataIscrizione) {
     //recupero la vecchia data in formato Swell
      Date oldDataIscrizione = this.studente.getDataIscrizione();
      //la converto in java.util.Date
      java.util.Date vecchiaDataIscrizione=new java.util.Date();
      if (oldDataIscrizione!=null) 
         vecchiaDataIscrizione=DateTimeFacade.String2Date(oldDataIscrizione.getDate());
      else
         vecchiaDataIscrizione=null;         
      
      // se sono entrambe null ritorno
      if(vecchiaDataIscrizione==null && nuovaDataIscrizione==null) return;
      
      //creo una nuova data da inserire in formato Swell
      Date newDataIscrizione=new Date();
      if (nuovaDataIscrizione!=null) 
         newDataIscrizione.setDate(DateTimeFacade.Date2String(nuovaDataIscrizione));
      else
         newDataIscrizione=null;
      
      // verifico il cambiamento e salvo se necessario
      if((vecchiaDataIscrizione==null && nuovaDataIscrizione!=null) || !vecchiaDataIscrizione.equals(nuovaDataIscrizione)){
         this.studente.setDataIscrizione(newDataIscrizione);
         propertyChangeSupport.firePropertyChange(DATA_ISCRIZIONE,vecchiaDataIscrizione, nuovaDataIscrizione);
      }
   }


   /** Salva lo Studente sul Web Service  
    */
   public void salvaSuWS() throws WSDidatticaException_Exception{
      if(studente.getId()!=Costanti.ID_NUOVA_PERSONA){
         WSPersonaService service = new WSPersonaService();
         WSPersona port = service.getWSPersonaPort();
         
         port.modificaStudente(this.studente);
         persona=studente;
         salvaIdClasse();
      }      
      else{
          WSPersonaService service = new WSPersonaService();
          WSPersona port = service.getWSPersonaPort();
          //da notare che la richiesta dell'id e' comune a Studente,Genitore,Insegnante
          //long nuovoId=port.ottieniProssimoIdPersona();
          /*perche' l'oggetto e' stato creato con l'id di default. Al momento del
           *salvataggio, in modo del tutto trasparente all'utente gli viene conferito
           *un valore consistente.
           */
          
          int nuovoId=port.aggiungiStudente(studente);
          studente.setId(nuovoId);
          persona=studente;
          salvaIdClasse();
       }
       System.out.println ("QUI1: "+idClasse);
   }

   private void salvaIdClasse() {
      try{
         nu.mine.egoless.didattica.ws.personaclient.WSPersonaService service = new nu.mine.egoless.didattica.ws.personaclient.WSPersonaService();
         nu.mine.egoless.didattica.ws.personaclient.WSPersona port = service.getWSPersonaPort();
         port.setClasse(studente.getId (), idClasse);
      }
      catch (Exception ex){
         ex.printStackTrace();
      }     
   }
   
   private void caricaIdClasse() {
      try { // Call Web Service Operation
         nu.mine.egoless.didattica.ws.personaclient.WSPersonaService service = new nu.mine.egoless.didattica.ws.personaclient.WSPersonaService ();
         nu.mine.egoless.didattica.ws.personaclient.WSPersona port = service.getWSPersonaPort ();
            
         int result = port.getClasse (studente.getId ());
         System.out.println ("Result = "+result);
         idClasse=result;
     } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
         
  }
         
   
   
   /** Carica lo Studente corrispondente all'id dal Web Service  
    * @param id Id del contatto da caricare
    */
   public void caricaDaWS(int id) throws UnsupportedOperationException,WSDidatticaException_Exception{
      if(id!=Costanti.ID_NUOVA_PERSONA){
         
            //Chiamata ad una operazione del web service
            WSPersonaService service = new WSPersonaService();
            WSPersona port = service.getWSPersonaPort();
            // TODO initialize WS operation arguments here
            ParametriRicercaStudente insiemeParametri = new ParametriRicercaStudente();
            insiemeParametri.setId(id);
            // TODO process result here
            List<Studente> result = port.cercaStudente(insiemeParametri);
            if(!result.isEmpty())
            {
               studente=result.get(0);
               persona= studente;
            }
            
            caricaIdClasse();
            System.out.println ("QUI2: "+idClasse);   
           
           
      }
      /* viene lanciata questa eccezione per indicare un comportamento
       * che il programmatore non dovrebbe avere.
       * Non ha senso infatti caricare un oggetto che ha come id quello di default!
       */
      else throw new UnsupportedOperationException();
   }
   /** Cancella lo Studente corrispondente all'idStudente passato come parametro
    * @param idStudente Valore dell'id dello studente da cancellare 
    */
    public void cancellaStudente()throws WSDidatticaException_Exception{
      
         WSPersonaService service = new WSPersonaService();
         WSPersona port = service.getWSPersonaPort();
         // TODO initialize WS operation arguments here
         port.cancellaStudente(this.studente.getId());
      
    }
   
}
