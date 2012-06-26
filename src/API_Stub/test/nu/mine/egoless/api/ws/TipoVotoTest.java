/*
 * TipoVotoTest.java
 * JUnit based test
 *
 * Created on 18 marzo 2007, 16.07
 */

package nu.mine.egoless.api.ws;

import com.swellsys.pqs.ws.modulo.registro.TipoVoto;
import com.swellsys.pqs.ws.modulo.registro.Voto;
import junit.framework.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Roberto
 */
public class TipoVotoTest extends TestCase {
   
   public TipoVotoTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of getId method, of class nu.mine.egoless.api.ws.TipoVoto.
    */
   public void testGetId() {
      System.out.println("getId");
      
      TipoVoto instance = new TipoVoto();
      
      long expResult = 0L;
      long result = instance.getId();
      assertEquals(expResult, result);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setId method, of class nu.mine.egoless.api.ws.TipoVoto.
    */
   public void testSetId() {
      System.out.println("setId");
      
      long id = 0L;
      TipoVoto instance = new TipoVoto();
      
      instance.setId(id);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getDescrizione method, of class nu.mine.egoless.api.ws.TipoVoto.
    */
   public void testGetDescrizione() {
      System.out.println("getDescrizione");
      
      TipoVoto instance = new TipoVoto();
      
      String expResult = "";
      String result = instance.getDescrizione();
      assertEquals(expResult, result);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setDescrizione method, of class nu.mine.egoless.api.ws.TipoVoto.
    */
   public void testSetDescrizione() {
      System.out.println("setDescrizione");
      
      String descrizione = "";
      TipoVoto instance = new TipoVoto();
      
      instance.setDescrizione(descrizione);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of getVoti method, of class nu.mine.egoless.api.ws.TipoVoto.
    */
   public void testGetVoti() {
      System.out.println("getVoti");
      
      TipoVoto instance = new TipoVoto();
      
      Voto[] expResult = null;
      Voto[] result = instance.getVoti();
      assertEquals(expResult, result);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of setVoti method, of class nu.mine.egoless.api.ws.TipoVoto.
    */
   public void testSetVoti() {
      System.out.println("setVoti");
      
      Voto[] voti = null;
      TipoVoto instance = new TipoVoto();
      
      instance.setVoti(voti);
      
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of equals method, of class nu.mine.egoless.api.ws.TipoVoto.
    */
   public void testEquals() {
      System.out.println("equals");
      
      TipoVoto i1=new TipoVoto();
      //i1.setId(1);
      i1.setDescrizione("D!");
      i1.setVoti(new Voto[] {});
      TipoVoto instance = new TipoVoto();
      //instance.setId(1);
      instance.setDescrizione("D!");
      instance.setVoti(new Voto[] {});
      
      boolean expResult = true;
      boolean result = instance.equals(i1);
      assertEquals(expResult, result);
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }
   
}
