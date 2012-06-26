/*
 * PersonaDriver.java
 *
 * Created on 20 marzo 2007, 18.56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ws.didattica.driver;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import ws.didattica.driver.personaclient.WSDidatticaException;
import ws.didattica.driver.personaclient.WSPersona;
import ws.didattica.driver.personaclient.WSPersonaService;

/**
 *
 * @author Roberto
 */
public class PersonaDriver {
   
   /** Creates a new instance of PersonaDriver */
   public PersonaDriver() {
   }
   public long driverAggiungiStudente(ws.didattica.driver.personaclient.Studente studente){
      
      long result=-1;
      
      try { // Call Web Service Operation
         ws.didattica.driver.personaclient.WSPersonaService service = new ws.didattica.driver.personaclient.WSPersonaService();
         ws.didattica.driver.personaclient.WSPersona port = service.getWSPersonaPort();
         // TODO initialize WS operation arguments here
         
         // TODO process result here
         result = port.aggiungiStudente(studente);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         if (ex.getMessage().equals("Non e' stato passato nessun studente")) return -2;
         if (ex.getMessage().equals("Il codice fiscale indicato non e' valido.")) return -3;
         if (ex.getMessage().equals("La data di nascita e' successiva al giorno attuale.")) return -4;
         if (ex.getMessage().equals("La data di iscrizione e' successiva al giorno attuale.")) return -5;
         ex.printStackTrace();
      }
      return result;
      
   }
   
   public void driverModificaStudente(ws.didattica.driver.personaclient.Studente studente){
      try { // Call Web Service Operation
         ws.didattica.driver.personaclient.WSPersonaService service = new ws.didattica.driver.personaclient.WSPersonaService();
         ws.didattica.driver.personaclient.WSPersona port = service.getWSPersonaPort();
         // TODO initialize WS operation arguments here
         port.modificaStudente(studente);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
   }
   
   public void driverCancellaStudente(int idStudente){
      try { // Call Web Service Operation
         ws.didattica.driver.personaclient.WSPersonaService service = new ws.didattica.driver.personaclient.WSPersonaService();
         ws.didattica.driver.personaclient.WSPersona port = service.getWSPersonaPort();
         // TODO initialize WS operation arguments here
         
         port.cancellaStudente(idStudente);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
   }
   
   public java.util.List<ws.didattica.driver.personaclient.Studente> driverCercaStudente(ws.didattica.driver.personaclient.ParametriRicercaStudente insiemeParametri) throws Exception {
      
      java.util.List<ws.didattica.driver.personaclient.Studente> result=null;
      
      
      ws.didattica.driver.personaclient.WSPersonaService service = new ws.didattica.driver.personaclient.WSPersonaService();
      ws.didattica.driver.personaclient.WSPersona port = service.getWSPersonaPort();
      
      // TODO process result here
      result = port.cercaStudente(insiemeParametri);
      System.out.println("Result = "+result);
      
      return result;
   }
   
   public long driverAggiungiDocente(ws.didattica.driver.personaclient.Docente insegnante){
      
      long result=-1;
      
      try { // Call Web Service Operation
         ws.didattica.driver.personaclient.WSPersonaService service = new ws.didattica.driver.personaclient.WSPersonaService();
         ws.didattica.driver.personaclient.WSPersona port = service.getWSPersonaPort();
         // TODO initialize WS operation arguments here
         
         // TODO process result here
         result = port.aggiungiDocente(insegnante);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         if (ex.getMessage().equals("Non e' stato passato alcun insegnante.")) return -2;
         if (ex.getMessage().equals("La data di assunzione e' successiva al giorno attuale.")) return -3;
         ex.printStackTrace();
      }
      return result;
      
   }
   
   public void driverModificaDocente(ws.didattica.driver.personaclient.Docente insegnante){
      try { // Call Web Service Operation
         ws.didattica.driver.personaclient.WSPersonaService service = new ws.didattica.driver.personaclient.WSPersonaService();
         ws.didattica.driver.personaclient.WSPersona port = service.getWSPersonaPort();
         // TODO initialize WS operation arguments here
         port.modificaDocente(insegnante);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
   }
   
   public void driverCancellaDocente(int idInsegnante){
      try { // Call Web Service Operation
         ws.didattica.driver.personaclient.WSPersonaService service = new ws.didattica.driver.personaclient.WSPersonaService();
         ws.didattica.driver.personaclient.WSPersona port = service.getWSPersonaPort();
         // TODO initialize WS operation arguments here
         
         port.cancellaDocente(idInsegnante);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
   }
   
   public java.util.List<ws.didattica.driver.personaclient.Docente> driverCercaDocente(ws.didattica.driver.personaclient.ParametriRicercaDocente insiemeParametri) throws Exception{
      
      java.util.List<ws.didattica.driver.personaclient.Docente> result=null;
      
      
      ws.didattica.driver.personaclient.WSPersonaService service = new ws.didattica.driver.personaclient.WSPersonaService();
      ws.didattica.driver.personaclient.WSPersona port = service.getWSPersonaPort();
      
      // TODO process result here
      result = port.cercaDocente(insiemeParametri);
      
      
      return result;
   }
   
   public List<Integer> getMaterie(int idDocente) throws Exception {
      WSPersonaService service = new WSPersonaService();
      WSPersona port = service.getWSPersonaPort();
      
      return port.getMaterie(idDocente);
   }
   
   public void setMaterie(int idDocente, List<Integer> idMaterie) throws Exception {
      WSPersonaService service = new WSPersonaService();
      WSPersona port = service.getWSPersonaPort();
      
      port.setMaterie(idDocente, idMaterie);
   }
   
   
   public int getClasse(int idStudente) throws Exception {
      WSPersonaService service = new WSPersonaService();
      WSPersona port = service.getWSPersonaPort();
      
      return port.getClasse(idStudente);
   }
   
   public void setClasse(int idStudente, int idClasse) throws Exception {
      WSPersonaService service = new WSPersonaService();
      WSPersona port = service.getWSPersonaPort();
      
      port.setClasse(idStudente, idClasse);
   }
}
