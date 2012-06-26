/*
 * ClasseBeanTest.java
 * JUnit based test
 *
 * Created on 23 marzo 2007, 15.02
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.classesupport.Classe;
import nu.mine.egoless.didattica.ws.classesupport.WSClasse;
import nu.mine.egoless.didattica.ws.classesupport.WSClasseService;
import nu.mine.egoless.didattica.ws.classesupport.WSDidatticaException_Exception;
import java.lang.UnsupportedOperationException;
import java.util.List;
import java.util.Date;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStub;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStubService;

/**
 *
 * @author Roberto
 */
public class ClasseBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public ClasseBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
   }

   protected void tearDown() throws Exception {
   }


   /**
    * Test of getId method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testGetId() {
      System.out.println("getId");
      
      ClasseBean instance = new ClasseBean();
      
      int expResult = 0;
      int result = instance.getId();
      assertEquals(expResult, result);
      
      
   }

   /**
    * Test of getAnnoCorso method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testGetAnnoCorso() {
      System.out.println("getAnnoCorso");
      
      ClasseBean instance = new ClasseBean();
      
      int expResult = 1990;
      instance.setAnnoCorso(expResult);
      int result = instance.getAnnoCorso();
      assertEquals(expResult, result);
    
   }

   /**
    * Test of setAnnoCorso method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testSetAnnoCorso() {
      System.out.println("setAnnoCorso");
           
      //Passaggio da un valore a un altro
      
      final int anno=1;
      final int nuovoAnno=2;
      
      ClasseBean instance = new ClasseBean();
      instance.setAnnoCorso(anno);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(anno) &&
                    evt.getNewValue().equals(nuovoAnno)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ClasseBean.ANNO_CORSO, l);
      
      instance.setAnnoCorso(nuovoAnno);
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ClasseBean.ANNO_CORSO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setAnnoCorso(3);
      instance.addPropertyChangeListener(ClasseBean.ANNO_CORSO, l);
      instance.setAnnoCorso(3);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of setIndirizzoStudi method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testSetIndirizzoStudi() {
      System.out.println("setIndirizzoStudi");
            
      //Passaggio da un valore a un altro
      
      final int id=5;
      final int nuovoId=6;
      
      ClasseBean instance = new ClasseBean();
      instance.setIndirizzoStudi(id);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(id) &&
                    evt.getNewValue().equals(nuovoId)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ClasseBean.INDIRIZZO_STUDI, l);
      
      instance.setIndirizzoStudi(nuovoId);
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ClasseBean.INDIRIZZO_STUDI, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIndirizzoStudi(2);
      instance.addPropertyChangeListener(ClasseBean.INDIRIZZO_STUDI, l);
      instance.setIndirizzoStudi(2);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getIndirizzoStudi method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testGetIndirizzoStudi() {
      System.out.println("getIndirizzoStudi");
      
      ClasseBean instance = new ClasseBean();
      
      int expResult = 3;
      instance.setIndirizzoStudi(expResult);
      int result = instance.getIndirizzoStudi();
      assertEquals(expResult, result);
   }

   /**
    * Test of getSezione method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testGetSezione() {
      System.out.println("getSezione");
      
      ClasseBean instance = new ClasseBean();
      
      char expResult = 'A';
      instance.setSezione(expResult);
      char result = instance.getSezione();
      assertEquals(expResult, result);
   }

   /**
    * Test of setSezione method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testSetSezione() {
      System.out.println("setSezione");
      
      //Passaggio da un valore a un altro
      
      final char sezione='A';
      final char nuovaSezione='B';
      
      ClasseBean instance = new ClasseBean();
      instance.setSezione(sezione);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(65) &&
                    evt.getNewValue().equals(66)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ClasseBean.SEZIONE, l);
      
      instance.setSezione(nuovaSezione);
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ClasseBean.SEZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setSezione(sezione);
      instance.addPropertyChangeListener(ClasseBean.SEZIONE, l);
      instance.setSezione(sezione);
      
      assertEquals(false, eventFired);

   }

 
   
   /**
    * Funzione di aiuto per determinare se esiste una classe con un determinato id.
    * @param id id dell'occorrenza da trovare.
    * @return {@code true} se l'occorrenza esiste, {@code false}.
    * @throws Exception Vengono rilanciate tutte le eccezioni che si verificano.
    **/
   private static boolean esisteClasse(int id) throws Exception {
      recuperaClassiBehavior(1);
      
      java.util.List<nu.mine.egoless.didattica.ws.classesupport.Classe> result=null;
      try { // Call Web Service Operation
         nu.mine.egoless.didattica.ws.classesupport.WSClasseService service = new nu.mine.egoless.didattica.ws.classesupport.WSClasseService();
         nu.mine.egoless.didattica.ws.classesupport.WSClasse port = service.getWSClassePort();
         // TODO process result here
          result = port.recuperaClassi();
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
  
      if (result==null || result.isEmpty()) return false;
      boolean found=false;
      int i=0;
      while(!found && i<result.size()) {
         if(result.get(i).getId()==id) {
            found=true;
         } else {
            i++;
         }
      }
      
      return found;
      
     
   }
   
   /**
    * Funzione di aiuto per impostare il comportamente del Web Service.
   
    */
   public static void recuperaClassiBehavior(int value) {
      try { // Call Web Service Operation
         WSDidaStubService service = new WSDidaStubService();
         WSDidaStub port = service.getWSDidaStubPort();
         
         port.recuperaClassiBehavior(value);
         
      } catch (Exception ex) {
         //Non gestiamo le eccezioni: lasciamo che il test continui
      }
   }

   /**
    * Test of salvaSuWS method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testSalvaSuWS() throws Exception {
      System.out.println("salvaSuWS");
      
      ClasseBean instance = new ClasseBean();
      assertEquals(instance.getId(), Costanti.ID_NUOVA_CLASSE);

      
      instance.setSezione('D');
      instance.salvaSuWS();
      assertFalse(instance.getId()==Costanti.ID_NUOVA_CLASSE);
      assertEquals(true, esisteClasse(instance.getId()));
   }

   /**
    * Test of cancellaClasse method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testCancellaClasse() throws Exception {
      System.out.println("cancellaClasse");
      
      ClasseBean instance = new ClasseBean();
      
      
      instance.salvaSuWS();
      //verifico comunque il caricamento
      assertTrue(esisteClasse(instance.getId()));
      instance.cancellaClasse();
      // verifico la cancellazione
      assertEquals(false, esisteClasse(instance.getId()));
   }

   /**
    * Test of setDocente method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testSetDocente() {
      System.out.println("setDocente");
      
     //Passaggio da un valore a un altro
      
      final int id=5;
      final int nuovoId=6;
      
      ClasseBean instance = new ClasseBean();
      instance.setDocente(id);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(id) &&
                    evt.getNewValue().equals(nuovoId)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ClasseBean.DOCENTE, l);
      
      instance.setDocente(nuovoId);
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ClasseBean.DOCENTE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDocente(2);
      instance.addPropertyChangeListener(ClasseBean.DOCENTE, l);
      instance.setDocente(2);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getDocente method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testGetDocente() {
      System.out.println("getDocente");
      
      ClasseBean instance = new ClasseBean();
      
      int expResult = 1990;
      instance.setDocente(expResult);
      int result = instance.getDocente();
      assertEquals(expResult, result);
   }

   /**
    * Test of setAnnoScolastico method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testSetAnnoScolastico() {
      System.out.println("setAnnoScolastico");
      
      //Passaggio da un valore a un altro
      
      final int id=5;
      final int nuovoId=6;
      
      ClasseBean instance = new ClasseBean();
      instance.setAnnoScolastico(id);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(id) &&
                    evt.getNewValue().equals(nuovoId)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ClasseBean.ANNO_SCOLASTICO, l);
      
      instance.setAnnoScolastico(nuovoId);
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ClasseBean.ANNO_SCOLASTICO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setAnnoScolastico(2);
      instance.addPropertyChangeListener(ClasseBean.ANNO_SCOLASTICO, l);
      instance.setAnnoScolastico(2);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getAnnoScolastico method, of class nu.mine.egoless.didattica.app.bean.ClasseBean.
    */
   public void testGetAnnoScolastico() {
      System.out.println("getAnnoScolastico");
      
      ClasseBean instance = new ClasseBean();
      
      int expResult = 1990;
      instance.setAnnoScolastico(expResult);
      int result = instance.getAnnoScolastico();
      assertEquals(expResult, result);
   }
   
}
