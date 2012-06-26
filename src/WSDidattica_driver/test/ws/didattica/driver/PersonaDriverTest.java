/*
 * PersonaDriverTest.java
 * JUnit based test
 *
 * Created on 20 marzo 2007, 19.01
 */

package ws.didattica.driver;

import junit.framework.*;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import ws.didattica.driver.personaclient.Docente;
import ws.didattica.driver.personaclient.ParametriRicercaDocente;
import ws.didattica.driver.personaclient.ParametriRicercaStudente;
import ws.didattica.driver.personaclient.Studente;
import ws.didattica.driver.personaclient.WSDidatticaException;
import ws.didattica.driver.personaclient.WSDidatticaException_Exception;
import ws.didattica.driver.personaclient.WSPersona;
import ws.didattica.driver.personaclient.WSPersonaService;

/**
 *
 * @author Roberto
 */
public class PersonaDriverTest extends TestCase {
   
   ws.didattica.driver.personaclient.Studente studenteDaAggiungere=new ws.didattica.driver.personaclient.Studente();
   ws.didattica.driver.testclient.Studente studenteDaCancellare=new ws.didattica.driver.testclient.Studente();
   ws.didattica.driver.personaclient.Docente docenteDaAggiungere=new ws.didattica.driver.personaclient.Docente();
   ws.didattica.driver.testclient.Docente docenteDaCancellare=new ws.didattica.driver.testclient.Docente();
   
   
   public PersonaDriverTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
      ws.didattica.driver.personaclient.Date dt;
      studenteDaAggiungere.setCodiceFiscale("ABCDEF90A60I392U");
      studenteDaAggiungere.setCognome("CognomeIns");
      
      dt=new ws.didattica.driver.personaclient.Date();
      dt.setDate("2002-09-01T17:00:00.000+02:00");
      studenteDaAggiungere.setDataIscrizione(dt);
      
      dt=new ws.didattica.driver.personaclient.Date();
      dt.setDate("1990-01-20T17:00:00.000+02:00");
      studenteDaAggiungere.setDataNascita(dt);
      
      studenteDaAggiungere.setId(1);
      studenteDaAggiungere.setContattoId(10);
      studenteDaAggiungere.setNazioneId(20);
      studenteDaAggiungere.setReligioneId(30);
      studenteDaAggiungere.setMatricola("MatricolaIns");
      studenteDaAggiungere.setNome("NomeIns");
      studenteDaAggiungere.setPortatoreHandicap(false);
      
      ws.didattica.driver.testclient.Date dt2;
      studenteDaCancellare.setCodiceFiscale("ABCDEF92A10I392U");
      studenteDaCancellare.setCognome("CognomeCanc");
      
      dt2=new ws.didattica.driver.testclient.Date();
      dt2.setDate("2002-01-10T17:00:00.000+02:00");
      studenteDaCancellare.setDataIscrizione(dt2);
      
      dt2=new ws.didattica.driver.testclient.Date();
      dt2.setDate("1992-01-10T17:00:00.000+02:00");
      studenteDaCancellare.setDataNascita(dt2);
      
      studenteDaCancellare.setId(99);
      studenteDaCancellare.setContattoId(90);
      studenteDaCancellare.setNazioneId(80);
      studenteDaCancellare.setReligioneId(70);
      studenteDaCancellare.setMatricola("MatricolaCanc");
      studenteDaCancellare.setNome("NomeCanc");
      studenteDaCancellare.setPortatoreHandicap(true);
      
      docenteDaAggiungere.setCodiceFiscale("ZXCVBN40D14A266P");
      docenteDaAggiungere.setCognome("CognomeIns");
      
      dt=new ws.didattica.driver.personaclient.Date();
      dt.setDate("1980-01-10T17:00:00.000+02:00");
      docenteDaAggiungere.setDataAssunzione(dt);
      
      dt=new ws.didattica.driver.personaclient.Date();
      dt.setDate("1940-04-14T17:00:00.000+02:00");
      docenteDaAggiungere.setDataNascita(dt);
      
      docenteDaAggiungere.setId(2);
      docenteDaAggiungere.setContattoId(10);
      docenteDaAggiungere.setNazioneId(30);
      docenteDaAggiungere.setReligioneId(40);
      docenteDaAggiungere.setNome("NomeIns");
      docenteDaAggiungere.setPortatoreHandicap(true);
      
      docenteDaCancellare.setCodiceFiscale("QWERTY20D14A266P");
      docenteDaCancellare.setCognome("CognomeCanc");
      
      dt2=new ws.didattica.driver.testclient.Date();
      dt2.setDate("1940-01-10T17:00:00.000+02:00");
      docenteDaCancellare.setDataAssunzione(dt2);
      
      dt2=new ws.didattica.driver.testclient.Date();
      dt2.setDate("1920-04-14T17:00:00.000+02:00");
      docenteDaCancellare.setDataNascita(dt2);
      
      docenteDaCancellare.setId(98);
      docenteDaCancellare.setContattoId(90);
      docenteDaCancellare.setNazioneId(70);
      docenteDaCancellare.setReligioneId(60);
      docenteDaCancellare.setNome("NomeCanc");
      docenteDaCancellare.setPortatoreHandicap(false);
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         long result = port.salvaStudente(studenteDaCancellare);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         int result = port.salvaInsegnante(docenteDaCancellare);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      
   }
   
   protected void tearDown() throws Exception {
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         port.eliminaStudente(studenteDaAggiungere.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         port.eliminaStudente(studenteDaCancellare.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         port.eliminaInsegnante(docenteDaAggiungere.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         port.eliminaInsegnante(docenteDaCancellare.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      
   }
   
   /**
    * Test of driverAggiungiStudente method, of class ws.didattica.driver.PersonaDriver.
    */
   public void testDriverAggiungiStudente() {
      System.out.println("driverAggiungiStudente");
      
      ws.didattica.driver.personaclient.Studente studente = null;
      PersonaDriver instance = new PersonaDriver();
      
      long expResult = studenteDaAggiungere.getId();
      long result = instance.driverAggiungiStudente(studenteDaAggiungere);
      assertEquals(expResult, result);
      
      ws.didattica.driver.testclient.Studente loaded=null;
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         loaded = port.caricaStudente(studenteDaAggiungere.getId());
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      assertEquals(studenteDaAggiungere.getCodiceFiscale(),loaded.getCodiceFiscale());
      assertEquals(studenteDaAggiungere.getCognome(),loaded.getCognome());
      assertEquals(studenteDaAggiungere.getDataIscrizione().getDate(),loaded.getDataIscrizione().getDate());
      assertEquals(studenteDaAggiungere.getDataNascita().getDate(),loaded.getDataNascita().getDate());
      assertEquals(studenteDaAggiungere.getId(),loaded.getId());
      assertEquals(studenteDaAggiungere.getContattoId(),loaded.getContattoId());
      assertEquals(studenteDaAggiungere.getNazioneId(),loaded.getNazioneId());
      assertEquals(studenteDaAggiungere.getReligioneId(),loaded.getReligioneId());
      assertEquals(studenteDaAggiungere.getMatricola(),loaded.getMatricola());
      assertEquals(studenteDaAggiungere.getNome(),loaded.getNome());
      
      //caso2: provo ad aggiungere null
      result=instance.driverAggiungiStudente(null);
      // dovrebbe acchiappare l'eccezione e ritornare -2
      assertEquals(-2, result);
      
      //caso3: metto codice fiscale non valido
      studenteDaAggiungere.setCodiceFiscale("CIE28D20H38HH577");
      result=instance.driverAggiungiStudente(studenteDaAggiungere);
      // dovrebbe acchiappare l'eccezione e ritornare -3
      assertEquals(-3, result);
      
      studenteDaAggiungere.setCodiceFiscale("ABCDEF90A60I392U");
      //caso4: metto data di nascita successiva a quella attuale
      ws.didattica.driver.personaclient.Date dt= new ws.didattica.driver.personaclient.Date();
      dt.setDate("2007-12-20T17:00:00.000+02:00");
      studenteDaAggiungere.setDataNascita(dt);
      result=instance.driverAggiungiStudente(studenteDaAggiungere);
      // dovrebbe acchiappare l'eccezione e ritornare -4
      assertEquals(-4, result);
      
      dt= new ws.didattica.driver.personaclient.Date();
      dt.setDate("1990-01-20T17:00:00.000+02:00");
      studenteDaAggiungere.setDataNascita(dt);
      
      //caso5: metto data di iscrizione successiva a quella attuale
      dt= new ws.didattica.driver.personaclient.Date();
      dt.setDate("2007-12-20T17:00:00.000+02:00");
      studenteDaAggiungere.setDataIscrizione(dt);
      result=instance.driverAggiungiStudente(studenteDaAggiungere);
      // dovrebbe acchiappare l'eccezione e ritornare -5
      assertEquals(-5, result);
   }
   
   /**
    * Test of driverModificaStudente method, of class ws.didattica.driver.PersonaDriver.
    */
   public void testDriverModificaStudente() {
      System.out.println("driverModificaStudente");
      
      ws.didattica.driver.personaclient.Studente studente = null;
      PersonaDriver instance = new PersonaDriver();
      
      studenteDaAggiungere.setCognome("CognomeModificato");
      instance.driverModificaStudente(studenteDaAggiungere);
      
      ws.didattica.driver.testclient.Studente loaded=new ws.didattica.driver.testclient.Studente();
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         loaded = port.caricaStudente(studenteDaAggiungere.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      assertEquals(studenteDaAggiungere.getCodiceFiscale(),loaded.getCodiceFiscale());
      assertEquals(studenteDaAggiungere.getCognome(),loaded.getCognome());
      assertEquals(studenteDaAggiungere.getDataIscrizione().getDate(),loaded.getDataIscrizione().getDate());
      assertEquals(studenteDaAggiungere.getDataNascita().getDate(),loaded.getDataNascita().getDate());
      assertEquals(studenteDaAggiungere.getId(),loaded.getId());
      assertEquals(studenteDaAggiungere.getContattoId(),loaded.getContattoId());
      assertEquals(studenteDaAggiungere.getNazioneId(),loaded.getNazioneId());
      assertEquals(studenteDaAggiungere.getReligioneId(),loaded.getReligioneId());
      assertEquals(studenteDaAggiungere.getMatricola(),loaded.getMatricola());
      assertEquals(studenteDaAggiungere.getNome(),loaded.getNome());
   }
   
   /**
    * Test of driverCancellaStudente method, of class ws.didattica.driver.PersonaDriver.
    */
   public void testDriverCancellaStudente() {
      System.out.println("driverCancellaStudente");
      
      PersonaDriver instance = new PersonaDriver();
      
      instance.driverCancellaStudente(studenteDaCancellare.getId());
      
      // verra' sostituito da null se lo studente e' cancellato correttamente
      ws.didattica.driver.testclient.Studente result=new ws.didattica.driver.testclient.Studente();
      
      // provo a caricare lo studente che ho cancellato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         // se e' stato cancellato dovrebbe ritornare null
         result = port.caricaStudente(studenteDaCancellare.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      // verifico l'eliminazione
      assertEquals(null,result);
   }
   
   /**
    * Test of driverCercaStudente method, of class ws.didattica.driver.PersonaDriver.
    */
   public void testDriverCercaStudente() throws Exception {
      System.out.println("driverCercaStudente");
      
      boolean exceptionThrown=false;
      
      ParametriRicercaStudente insiemeParametri = null;
      PersonaDriver instance = new PersonaDriver();
      
      try {
         java.util.List<Studente> result = instance.driverCercaStudente(insiemeParametri);
      } catch(WSDidatticaException_Exception ex){
         exceptionThrown=true;
      }
      
      assertEquals(true, exceptionThrown);
      
      insiemeParametri=new ParametriRicercaStudente();
      java.util.List<Studente> result = instance.driverCercaStudente(insiemeParametri);
      
      assertNotNull(result);
      Studente v=result.get(0);
      assertNotNull(v);
      assertEquals(1, v.getId());
      assertEquals("Pippo",   v.getNome());
   }
   
   /**
    * Test of driverAggiungiDocente method, of class ws.didattica.driver.PersonaDriver.
    */
   public void testDriverAggiungiDocente() {
      System.out.println("driverAggiungiDocente");
      
      ws.didattica.driver.personaclient.Docente docente = null;
      PersonaDriver instance = new PersonaDriver();
      
      long expResult = docenteDaAggiungere.getId();
      long result = instance.driverAggiungiDocente(docenteDaAggiungere);
      assertEquals(expResult, result);
      
      ws.didattica.driver.testclient.Docente loaded=null;
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         loaded = port.caricaInsegnante(docenteDaAggiungere.getId());
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      
      assertEquals(docenteDaAggiungere.getCodiceFiscale(),loaded.getCodiceFiscale());
      assertEquals(docenteDaAggiungere.getCognome(),loaded.getCognome());
      assertEquals(docenteDaAggiungere.getDataAssunzione().getDate(),loaded.getDataAssunzione().getDate());
      assertEquals(docenteDaAggiungere.getDataNascita().getDate(),loaded.getDataNascita().getDate());
      assertEquals(docenteDaAggiungere.getId(),loaded.getId());
      assertEquals(docenteDaAggiungere.getContattoId(),loaded.getContattoId());
      assertEquals(docenteDaAggiungere.getNazioneId(),loaded.getNazioneId());
      assertEquals(docenteDaAggiungere.getReligioneId(),loaded.getReligioneId());
      assertEquals(docenteDaAggiungere.getNome(),loaded.getNome());
      
      //caso2: provo ad aggiungere null
      result=instance.driverAggiungiDocente(null);
      // dovrebbe acchiappare l'eccezione e ritornare -2
      assertEquals(-2, result);
      
      //caso5: metto data di assunzione successiva a quella attuale
      ws.didattica.driver.personaclient.Date dt= new ws.didattica.driver.personaclient.Date();
      dt.setDate("2007-12-20T17:00:00.000+02:00");
      docenteDaAggiungere.setDataAssunzione(dt);
      result=instance.driverAggiungiDocente(docenteDaAggiungere);
      //dovrebbe acchiappare l'eccezione e ritornare -3
      assertEquals(-3, result);
   }
   
   /**
    * Test of driverModificaDocente method, of class ws.didattica.driver.PersonaDriver.
    */
   public void testDriverModificaDocente() {
      System.out.println("driverModificaDocente");
      
      
      ws.didattica.driver.personaclient.Docente docente = null;
      PersonaDriver instance = new PersonaDriver();
      
      docenteDaAggiungere.setCognome("CognomeModificato");
      instance.driverModificaDocente(docenteDaAggiungere);
      
      ws.didattica.driver.testclient.Docente loaded=new ws.didattica.driver.testclient.Docente();
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         loaded = port.caricaInsegnante(docenteDaAggiungere.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      assertEquals(docenteDaAggiungere.getCodiceFiscale(),loaded.getCodiceFiscale());
      assertEquals(docenteDaAggiungere.getCognome(),loaded.getCognome());
      assertEquals(docenteDaAggiungere.getDataAssunzione().getDate(),loaded.getDataAssunzione().getDate());
      assertEquals(docenteDaAggiungere.getDataNascita().getDate(),loaded.getDataNascita().getDate());
      assertEquals(docenteDaAggiungere.getId(),loaded.getId());
      assertEquals(docenteDaAggiungere.getContattoId(),loaded.getContattoId());
      assertEquals(docenteDaAggiungere.getNazioneId(),loaded.getNazioneId());
      assertEquals(docenteDaAggiungere.getReligioneId(),loaded.getReligioneId());
      assertEquals(docenteDaAggiungere.getNome(),loaded.getNome());
   }
   
   /**
    * Test of driverCancellaDocente method, of class ws.didattica.driver.PersonaDriver.
    */
   public void testDriverCancellaDocente() {
      System.out.println("driverCancellaDocente");
      
      
      PersonaDriver instance = new PersonaDriver();
      
      instance.driverCancellaDocente(docenteDaCancellare.getId());
      
      // verra' sostituito da null se l'docente e' cancellato correttamente
      ws.didattica.driver.testclient.Docente result=new ws.didattica.driver.testclient.Docente();
      
      // provo a caricare l'docente che ho cancellato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         // se e' stato cancellato dovrebbe ritornare null
         result = port.caricaInsegnante(docenteDaCancellare.getId());
         
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      // verifico l'eliminazione
      assertEquals(null,result);
   }
   
   /**
    * Test of driverCercaDocente method, of class ws.didattica.driver.PersonaDriver.
    */
   public void testDriverCercaDocente() throws Exception {
      System.out.println("driverCercaDocente");
      
      boolean exceptionThrown=false;
      
      ParametriRicercaDocente insiemeParametri = null;
      PersonaDriver instance = new PersonaDriver();
      
      try {
         java.util.List<Docente> result = instance.driverCercaDocente(insiemeParametri);
      } catch(WSDidatticaException_Exception ex){
         exceptionThrown=true;
      }
      
      assertEquals(true, exceptionThrown);
      
      insiemeParametri=new ParametriRicercaDocente();
      java.util.List<Docente> result = instance.driverCercaDocente(insiemeParametri);
      
      assertNotNull(result);
      Docente v=result.get(0);
      assertNotNull(v);
      assertEquals(1, v.getId());
      assertEquals("John",   v.getNome());
      assertEquals("Cena",   v.getCognome());
   }
   
   /**
    * Test of setMaterie method, of class ws.didattica.driver.PersonaDriver.
    */
   public void testSetMaterie() throws Exception {
      System.out.println("setMaterie");
      
      boolean notYet=false;
      
      int idDocente = 0;
      List<Integer> idMaterie = null;
      PersonaDriver instance = new PersonaDriver();
      
      //Testiamo semplicemente se la chiamata avviene correttamente
      try {
         instance.setMaterie(idDocente, idMaterie);
      } catch(Exception ex) {
         if(ex.getMessage().equals("Not yet implemented")) {
            notYet=true;
         }
      }
      
      assertEquals(true, notYet);
   }
   
   /**
    * Test of getClasse method, of class ws.didattica.driver.PersonaDriver.
    */
   public void testGetClasse() throws Exception {
      System.out.println("getClasse");
      
      int idStudente = 5;
      PersonaDriver instance = new PersonaDriver();
      
      int expResult = 5;
      int result = instance.getClasse(idStudente);
      assertEquals(expResult, result);
   }
   
   /**
    * Test of setClasse method, of class ws.didattica.driver.PersonaDriver.
    */
   public void testSetClasse() throws Exception {
      System.out.println("setClasse");
      
      boolean notYet=false;
      
      int idStudente = 0;
      int idClasse = 0;
      PersonaDriver instance = new PersonaDriver();
      
      //Testiamo semplicemente se la chiamata avviene correttamente
      try {
         instance.setClasse(idStudente, idClasse);
      } catch(Exception ex) {
         if(ex.getMessage().equals("Not yet implemented")) {
            notYet=true;
         }
      }
      
      assertEquals(true, notYet);
   }
   
   /**
    * Test of getMaterie method, of class ws.didattica.driver.PersonaDriver.
    */
   public void testGetMaterie() throws Exception {
      System.out.println("getMaterie");
      
      PersonaDriver instance = new PersonaDriver();
      
      List<Integer> result = instance.getMaterie(1);
      assertNotNull(result);
      assertEquals(true, result.contains(5));
      assertEquals(true, result.contains(6));
      assertEquals(true, result.contains(7));
   }
   
}
