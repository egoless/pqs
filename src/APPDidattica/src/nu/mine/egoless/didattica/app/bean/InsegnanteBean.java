/*
 * Nome file:InsegnanteBean.java
 * Data creazione:22 febbraio 2007, 12.09
 * Info svn: $Id: InsegnanteBean.java 917 2007-04-18 12:36:06Z eric $
 */


package nu.mine.egoless.didattica.app.bean;

import java.util.LinkedList;
import nu.mine.egoless.didattica.ws.personaclient.Date;
import nu.mine.egoless.didattica.ws.personaclient.Docente;

import nu.mine.egoless.didattica.ws.contattoclient.Contatto;
import nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaDocente;

import nu.mine.egoless.didattica.ws.personaclient.WSPersonaService;
import nu.mine.egoless.didattica.ws.personaclient.WSPersona;

import java.lang.UnsupportedOperationException;
import java.util.List;
import nu.mine.egoless.supporto.DateTimeFacade;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;

/** Questa classe si interfaccierà alla parte di GUI per la gestione delle informazioni
 * relative all'Insegnante
 */

public class InsegnanteBean extends PersonaBean {
   /**
    * Costanti usate dai metodo fireChangeProperty perche' le classi che gestiscono
    * l'intefaccia grafica riescano a discriminare quali proprieta' della classe sono
    * cambiate
    */
   
   public static final String DATA_ASSUNZIONE="dataAssunzione";
   
   public static final String INSEGNANTE="insegnante";
   
   public static final String MATERIA_INSEGNAMENTO="materiaInsegnamento";
   
   public static final String ID_MATERIE_INSEGNAMENTO="idMaterieInsegnamento";
   
   public static final String MATRICOLA="matricola";
   
   /**Oggetto interno di tipo insegnante*/
   private Docente insegnante;
   
   private java.util.List<java.lang.Integer> listaIdMaterie = new LinkedList<Integer>();
   
   /**
    * Crea una nuova istanza di InsegnanteBean
    */
   public InsegnanteBean() {
      insegnante=new Docente();
      insegnante.setId(Costanti.ID_NUOVA_PERSONA);
      listaIdMaterie=new java.util.LinkedList<java.lang.Integer>();
      persona=insegnante;
   }
   
   /**
    * Crea una nuova istanza di InsegnanteBean dato l'id
    * @param id Id dell'insegnante
    */
   public InsegnanteBean(int id) {
      insegnante=new Docente();
      insegnante.setId(id);
      persona=insegnante;
   }
   
   
   /**
    * Ritorna la proprieta' dataAssunzione.
    * @return Valore della proprieta' dataAssunzione.
    */
   public java.util.Date getDataAssunzione() {
      if (this.insegnante.getDataAssunzione()==null) return new java.util.Date();
      return DateTimeFacade.String2Date(this.insegnante.getDataAssunzione().getDate());
   }
   
   /**
    * Setter per la proprietà dataAssunzione.
    * @param dataAssunzione Nuovo valore della proprietà dataAssunzione.
    */
   public void setDataAssunzione(java.util.Date nuovaDataAssunzione) {
      
      //recupero la vecchia data in formato Swell
      Date oldDataAssunzione = this.insegnante.getDataAssunzione();
      //la converto in java.util.Date
      java.util.Date vecchiaDataAssunzione=new java.util.Date();
      if (oldDataAssunzione!=null) 
         vecchiaDataAssunzione=DateTimeFacade.String2Date(oldDataAssunzione.getDate());
      else
         vecchiaDataAssunzione=null;         
      
      // se sono entrambe null ritorno
      if(vecchiaDataAssunzione==null && nuovaDataAssunzione==null) return;
      
      //creo una nuova data da inserire in formato Swell
      Date newDataAssunzione=new Date();
      if (nuovaDataAssunzione!=null) 
         newDataAssunzione.setDate(DateTimeFacade.Date2String(nuovaDataAssunzione));
      else
         newDataAssunzione=null;
      
      // verifico il cambiamento e salvo se necessario
      if((vecchiaDataAssunzione==null && nuovaDataAssunzione!=null) || !vecchiaDataAssunzione.equals(nuovaDataAssunzione)){
         this.insegnante.setDataAssunzione(newDataAssunzione);
         propertyChangeSupport.firePropertyChange(DATA_ASSUNZIONE,vecchiaDataAssunzione, nuovaDataAssunzione);
      }
   }

   /**
    * Ritorna la proprietà matricola.
    * @ritorna il valore della proprietà matricola.
    */
   public String getMatricola() {
      String t = this.insegnante.getMatricola();
      if( t == null ) {
         t="";
      }
      
      return t;            
   }
   
   /**
    * Setta la proprietà matricola.
    * @param matricola Nuovo valore della proprietà matricola.
    */
   public void setMatricola(String matricola) {
      String oldMatricola = this.insegnante.getMatricola();
      if(oldMatricola==null && matricola==null) return;
      if ((oldMatricola==null && matricola!=null) || !oldMatricola.equals(matricola)){
         this.insegnante.setMatricola(matricola);
         propertyChangeSupport.firePropertyChange(MATRICOLA, oldMatricola, matricola);
      }
   }
   
   /**
    * Getter per la proprietà idMateria.
    * @return Valore della proprietà idMateria
    */
  public int[] getIdMaterie(){
      if(listaIdMaterie == null) return new int[0];
      //System.out.println("YES5");
      int[] result=new int[listaIdMaterie.size()];
      for (int i=0; i<listaIdMaterie.size(); i++){
         result[i]=listaIdMaterie.get(i);
      }
      return result;
   }
   
   /**
    * Sostituisce la lista di id delle amterie
    */
   public void setIdMaterie(int[] elencoIdMaterie){
      //System.out.println("YES1");
      if(elencoIdMaterie == null) return;
      //System.out.println("YES2");
      //crea un array con le vccchie materie
      java.util.List<java.lang.Integer> temp = this.listaIdMaterie;
      int[] vecchiaLista;
      
      if(temp == null) 
          vecchiaLista = new int[0];
      else{
          vecchiaLista = new int[temp.size()];
          for( int i=0; i<temp.size(); i++)
              vecchiaLista[i] = (temp.get(i)).intValue();
      }
      //test di uguaaglianza
      boolean uguali = true;
      if(vecchiaLista.length == elencoIdMaterie.length){
         for(int i=0; i<vecchiaLista.length && uguali; i++)
            if(vecchiaLista[i]!=elencoIdMaterie[i]) uguali = false;
      }
      else uguali = false;
   
      if(uguali) return;
      
      java.util.List<Integer> nuovaListaIdMaterie=new LinkedList<Integer>();
      if (elencoIdMaterie!=null)
         for (int i=0; i<elencoIdMaterie.length; i++){
           //System.out.println("YES4");
            nuovaListaIdMaterie.add(elencoIdMaterie[i]);
         }
      //assegna la nuova lista
      listaIdMaterie = nuovaListaIdMaterie;
      
      propertyChangeSupport.firePropertyChange(ID_MATERIE_INSEGNAMENTO, vecchiaLista, elencoIdMaterie);         
   }
   
   
   /** Carica l'Oggetto corrispondente all'id dal Web Service
    * @param id Id dell'oggetto da caricare
    */
   public void caricaDaWS(int id) throws UnsupportedOperationException,WSDidatticaException_Exception{
      if(id!=Costanti.ID_NUOVA_PERSONA){
         
         //Chiamata ad una operazione del web service
         WSPersonaService service = new WSPersonaService();
         WSPersona port = service.getWSPersonaPort();
         // TODO initialize WS operation arguments here
         ParametriRicercaDocente insiemeParametri = new ParametriRicercaDocente();
         insiemeParametri.setId(id);
         // TODO process result here
         List<Docente> result = port.cercaDocente(insiemeParametri);
         if(!result.isEmpty()) {
            insegnante=result.get(0);
            persona= insegnante;
         }
        
        //carico la lista delle materie
        service = new nu.mine.egoless.didattica.ws.personaclient.WSPersonaService();
        port = service.getWSPersonaPort();
        // TODO process result here
        listaIdMaterie = port.getMaterie(insegnante.getId());
        
         
         
         
         
         
      }
      /* viene lanciata questa eccezione per indicare un comportamento
       * che il programmatore non dovrebbe avere.
       * Non ha senso infatti caricare un oggetto che ha come id quello di default!
       */
      else throw new UnsupportedOperationException();
      
   }
   
   /** Salva l'oggetto sul Web Service
    */
   public void salvaSuWS()throws WSDidatticaException_Exception{
      
      if(insegnante.getId()!=Costanti.ID_NUOVA_PERSONA){
         
         WSPersonaService service = new WSPersonaService();
         WSPersona port = service.getWSPersonaPort();
         // TODO initialize WS operation arguments here
         port.modificaDocente(this.insegnante);
         persona=insegnante;
         port.setMaterie(getId(), listaIdMaterie);
         // TODO handle custom exceptions here
      } else{
         WSPersonaService service = new WSPersonaService();
         WSPersona port = service.getWSPersonaPort();
         //long nuovoId=port.ottieniProssimoIdPersona();
          /*perche' l'oggetto e' stato creato con l'id di default. Al momento del
           *salvataggio, in modo del tutto trasparente all'utente gli viene conferito
           *un valore consistente.
           */
         
         int nuovoId=port.aggiungiDocente(insegnante);
         persona.setId(nuovoId);
         persona=insegnante;
         port.setMaterie(getId(), listaIdMaterie);
         // TODO handle custom exceptions here
      }
      
      
   }
   
   /** Metodo che cancella l'oggetto a partire dal suo id
    * @param idInsegnante Valore dell'id dell'oggetto da cancellare
    */
   
   public void cancellaInsegnante()throws WSDidatticaException_Exception {
      
      WSPersonaService service = new WSPersonaService();
      WSPersona port = service.getWSPersonaPort();
      // TODO initialize WS operation arguments here
      port.cancellaDocente(this.insegnante.getId());
      
   }
}
