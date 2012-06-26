/*
 * Nome File: DateTimeFacadeTest.java
 * Data creazione: 11 marzo 2007, 20.27
 * Info svn: $Id: DateTimeFacadeTest.java 285 2007-03-14 15:50:27Z roberto $
 */

package nu.mine.egoless.supporto;

import java.util.Calendar;
import junit.framework.*;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Test per la classe {@link DateTimeFacade}
 */
public class DateTimeFacadeTest extends TestCase {
   private Date dataDaConvertire;
   private String stringaDaConvertire;
   private GregorianCalendar c;
       
   public DateTimeFacadeTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
       c=new GregorianCalendar();
       //dataDaConvertire=new Date();
       
       //dataDaConvertire=c.getInstance();
       c.set(Calendar.YEAR, 2002);
       c.set(Calendar.MONTH, Calendar.OCTOBER);
       c.set(Calendar.DAY_OF_MONTH, 10);
       c.set(Calendar.HOUR_OF_DAY, 17);
       c.set(Calendar.MINUTE, 0);
       c.set(Calendar.SECOND, 0);
       c.set(Calendar.MILLISECOND, 0);
       dataDaConvertire=c.getTime();
       
       stringaDaConvertire=new String("2002-10-10T17:00:00.000+02:00");
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of Date2String method, of class nu.mine.egoless.supporto.DateTimeFacade.
    */
   public void testDate2String() {
      System.out.println("Date2String");
      
       
      String expResult = "2002-10-10T17:00:00.000+02:00";
      String result = DateTimeFacade.Date2String(dataDaConvertire);
      assertEquals(expResult, result);
      assertEquals(null,DateTimeFacade.Date2String(null));
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }

   /**
    * Test of GregorianCalendar2String method, of class nu.mine.egoless.supporto.DateTimeFacade.
    */
   public void testGregorianCalendar2String() {
      System.out.println("GregorianCalendar2String");
      
            
      String expResult = stringaDaConvertire;
      String result = DateTimeFacade.GregorianCalendar2String(c);
      assertEquals(expResult, result);
      assertEquals(null,DateTimeFacade.GregorianCalendar2String(null));
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }

   /**
    * Test of String2Date method, of class nu.mine.egoless.supporto.DateTimeFacade.
    */
   public void testString2Date() {
      System.out.println("String2Date");
      
            
      Date expResult = dataDaConvertire;
      Date result = DateTimeFacade.String2Date(stringaDaConvertire);
      assertEquals(expResult, result);
      assertEquals(null,DateTimeFacade.String2Date(null));
      assertEquals(null,DateTimeFacade.String2Date("pippo"));
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }

   /**
    * Test of String2GregorianCalendar method, of class nu.mine.egoless.supporto.DateTimeFacade.
    */
   public void testString2GregorianCalendar() {
      System.out.println("String2GregorianCalendar");
      

      
      GregorianCalendar expResult = c;
      GregorianCalendar result = DateTimeFacade.String2GregorianCalendar(stringaDaConvertire);
      
      
      //assertEquals(expResult, result);
      //stiamo confrontando solo il valore temporale (dist in millisec dall epoch)
      assertEquals(expResult.compareTo(result),0);
      assertEquals(DateTimeFacade.String2GregorianCalendar(null),null);
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }
   
}

