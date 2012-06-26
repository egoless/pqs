/*
 * VotoDriverTest.java
 * JUnit based test
 *
 * Created on 19 marzo 2007, 10.06
 */

package ws.didattica.driver;

import junit.framework.*;
import ws.didattica.driver.votoclient.Date;
import ws.didattica.driver.votoclient.Voto;
import ws.didattica.driver.votoclient.WSDidatticaException_Exception;

/**
 *
 * @author Roberto
 */
public class VotoDriverTest extends TestCase {
   
   ws.didattica.driver.votoclient.Voto votoDaAggiungere=new ws.didattica.driver.votoclient.Voto();
   ws.didattica.driver.testclient.Voto votoDaCancellare=new ws.didattica.driver.testclient.Voto();
   
   public VotoDriverTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
      Date dt=new Date();
      dt.setDate("2007-01-09T17:00:00.000+02:00");
      votoDaAggiungere.setData(dt);
      votoDaAggiungere.setId(1);
      votoDaAggiungere.setDocenteId(10);
      votoDaAggiungere.setMateriaId(20);
      votoDaAggiungere.setStudenteId(30);
      votoDaAggiungere.setTipoVotoId(40);
      
      ws.didattica.driver.testclient.Date dt2=new ws.didattica.driver.testclient.Date();
      dt2.setDate("2007-02-19T14:00:00.000+02:00");
      votoDaCancellare.setData(dt2);
      votoDaCancellare.setId(99);
      votoDaCancellare.setDocenteId(90);
      votoDaCancellare.setMateriaId(80);
      votoDaCancellare.setStudenteId(70);
      votoDaCancellare.setTipoVotoId(60);
      
      //salvo un voto da cancellare
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         long result = port.salvaVoto(votoDaCancellare);
         System.out.println("Result = "+result);
      } catch (Exception ex) {

      }
   }
   
   protected void tearDown() throws Exception {
      //elimino il voto inserito
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         port.eliminaVoto(votoDaAggiungere.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      // elimino quello da cancellare (caso in cui il test fallisca)
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         port.eliminaVoto(votoDaCancellare.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
   }
   
   /**
    * Test of driverAggiungiVoto method, of class ws.didattica.driver.VotoDriver.
    */
   public void testDriverAggiungiVoto() {
      System.out.println("driverAggiungiVoto");
      
      
      VotoDriver instance = new VotoDriver();
      
      long expResult = votoDaAggiungere.getId();
      long result = instance.driverAggiungiVoto(votoDaAggiungere);
      assertEquals(expResult, result);
      
      ws.didattica.driver.testclient.Voto loaded=null;
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         loaded = port.caricaVoto(votoDaAggiungere.getId());
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      assertEquals(votoDaAggiungere.getData().getDate(),loaded.getData().getDate());
      assertEquals(votoDaAggiungere.getId(),loaded.getId());
      assertEquals(votoDaAggiungere.getDocenteId(),loaded.getDocenteId());
      assertEquals(votoDaAggiungere.getMateriaId(),loaded.getMateriaId());
      assertEquals(votoDaAggiungere.getStudenteId(),loaded.getStudenteId());
      assertEquals(votoDaAggiungere.getTipoVotoId(),loaded.getTipoVotoId());
      
      //caso2: provo ad aggiungere null
      result=instance.driverAggiungiVoto(null);
      // dovrebbe acchiappare l'eccezione e ritornare -2
      assertEquals(-2, result);
      

      //caso4: inserisco il voto in una data posteriore a quella attuale
      Date dt=new Date();
      dt.setDate("2007-12-09T17:00:00.000+02:00");
      votoDaAggiungere.setData(dt);
      result=instance.driverAggiungiVoto(votoDaAggiungere);
      // dovrebbe acchiappare l'eccezione e ritornare -4
      assertEquals(-4, result);
      
   }
   
   /**
    * Test of driverModificaVoto method, of class ws.didattica.driver.VotoDriver.
    */
   public void testDriverModificaVoto() {
      System.out.println("driverModificaVoto");
      
      VotoDriver instance = new VotoDriver();
      
      //votoDaAggiungere.setValore(9);
      votoDaAggiungere.setTipoVotoId(35);
      votoDaAggiungere.setDocenteId(5);
      
      instance.driverModificaVoto(votoDaAggiungere);
      
      ws.didattica.driver.testclient.Voto loaded=new ws.didattica.driver.testclient.Voto();
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         loaded = port.caricaVoto(votoDaAggiungere.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      assertEquals(votoDaAggiungere.getData().getDate(),loaded.getData().getDate());
      assertEquals(votoDaAggiungere.getId(),loaded.getId());
      assertEquals(votoDaAggiungere.getDocenteId(),loaded.getDocenteId());
      assertEquals(votoDaAggiungere.getMateriaId(),loaded.getMateriaId());
      assertEquals(votoDaAggiungere.getStudenteId(),loaded.getStudenteId());
      assertEquals(votoDaAggiungere.getTipoVotoId(),loaded.getTipoVotoId());
   }
   
   /**
    * Test of driverCancellaVoto method, of class ws.didattica.driver.VotoDriver.
    */
   public void testDriverCancellaVoto() {
      System.out.println("driverCancellaVoto");
      
      
      VotoDriver instance = new VotoDriver();
      
      instance.driverCancellaVoto(votoDaCancellare.getId());
      
      // verra' sostituito da null se il voto e' cancellato correttamente
      ws.didattica.driver.testclient.Voto result=new ws.didattica.driver.testclient.Voto();
      
      // provo a caricare il voto che ho cancellato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         // se e' stato cancellato dovrebbe ritornare null
         result = port.caricaVoto(votoDaCancellare.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      // verifico l'eliminazione
      assertEquals(null,result);
   }
   
   /**
    * Test of driverCercaVoto method, of class ws.didattica.driver.VotoDriver.
    */
   public void testDriverCercaVoto() throws Exception {
      System.out.println("driverCercaVoto");
      
      boolean exceptionThrown=false;
      
      ws.didattica.driver.votoclient.ParametriRicercaVoto insiemeParametri = null;
      VotoDriver instance = new VotoDriver();
      
      try {
         java.util.List<ws.didattica.driver.votoclient.Voto> result = instance.driverCercaVoto(insiemeParametri);
      } catch(WSDidatticaException_Exception ex){
         exceptionThrown=true;
      }
      
      assertEquals(true, exceptionThrown);
      
      insiemeParametri=new ws.didattica.driver.votoclient.ParametriRicercaVoto();
      java.util.List<ws.didattica.driver.votoclient.Voto> result = instance.driverCercaVoto(insiemeParametri);
      
      assertNotNull(result);
      Voto v=result.get(0);
      assertNotNull(v);
      assertEquals(1, v.getId());
      assertEquals(5,   v.getClasseId());
      assertEquals("pippo", v.getDescrizione());
   }
   
}
