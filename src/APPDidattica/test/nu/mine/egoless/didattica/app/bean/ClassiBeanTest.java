/*
 * ClassiBeanTest.java
 * JUnit based test
 *
 * Created on 25 marzo 2007, 8.47
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.net.PortUnreachableException;
import nu.mine.egoless.didattica.ws.classesupport.Classe;
import nu.mine.egoless.didattica.ws.classesupport.WSClasse;
import nu.mine.egoless.didattica.ws.classesupport.WSClasseService;
import nu.mine.egoless.didattica.ws.classesupport.WSDidatticaException_Exception;

/**
 *
 * @author Roberto
 */
public class ClassiBeanTest extends TestCase {
   
   private boolean eventFired;
      
   public ClassiBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
   }

   protected void tearDown() throws Exception {
   }
   
   /**
    * Test of caricaClassi method, of class nu.mine.egoless.didattica.app.bean.ClassiBean.
    */
   public void testCaricaClassi() throws Exception {
      System.out.println("caricaClassi");
      
      ClassiBean instance = new ClassiBean();
      
      eventFired=false;
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      
      ClasseBeanTest.recuperaClassiBehavior(2);
      instance.addPropertyChangeListener(l);
      instance.caricaClassi();
      assertEquals(true, eventFired);
   }

   /**
    * Test of ritornaNumeroDiClassi method, of class nu.mine.egoless.didattica.app.bean.ClassiBean.
    */
   public void testRitornaNumeroDiClassi() throws Exception {
      System.out.println("ritornaNumeroDiClassi");
      
      ClassiBean instance = new ClassiBean();
      
      int expResult;
      
      //Uso del bean appena creato
      expResult=0;
      int result = instance.ritornaNumeroDiClassi();
      assertEquals(expResult, result);
      
      //Ci vengono restituite 3 classi
      ClasseBeanTest.recuperaClassiBehavior(2);
      //expResult = 3;
      instance.caricaClassi();
      result = instance.ritornaNumeroDiClassi();
      assertTrue(result>0);
      
      //Non ci viene restituita alcuna classe
      ClasseBeanTest.recuperaClassiBehavior(0);
      expResult = 0;
      instance.caricaClassi();
      result = instance.ritornaNumeroDiClassi();
      assertEquals(expResult, result);
   
   }

   /**
    * Test of getClasseAt method, of class nu.mine.egoless.didattica.app.bean.ClassiBean.
    */
   public void testGetClasseAt() throws Exception {
      System.out.println("getClasseAt");
      
      int posizione = 0;
      ClassiBean instance = new ClassiBean();
          
      Classe result;
      
      //Uso senza caricare nulla
      posizione=0;
      result = instance.getClasseAt(posizione);
      assertEquals(null, result);
      
      //Non ci viene restituita alcuna classe
      ClasseBeanTest.recuperaClassiBehavior(0);
      posizione=0;
      instance.caricaClassi();
      result = instance.getClasseAt(posizione);
      assertEquals(null, result);
      
      //Ci vengono restituite 3 classi
      ClasseBeanTest.recuperaClassiBehavior(2);
      instance.caricaClassi();
      posizione=0;
      result = instance.getClasseAt(posizione);
      assertNotNull(result);
      
      posizione=instance.ritornaNumeroDiClassi();
      result = instance.getClasseAt(posizione);
      assertNull(result);
      
      posizione=-1;
      result = instance.getClasseAt(posizione);
      assertNull(result);
      
      posizione=instance.ritornaNumeroDiClassi()+2;
      result = instance.getClasseAt(posizione);
      assertNull(result);
      
      posizione=1;
      result = instance.getClasseAt(posizione);
      assertNotNull(result);
      
       
   }
   
}
