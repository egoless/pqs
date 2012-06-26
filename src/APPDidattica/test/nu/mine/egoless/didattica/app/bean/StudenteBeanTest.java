/*
 * StudenteBeanTest.java
 * JUnit based test
 *
 * Created on 24 marzo 2007, 10.32
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import junit.framework.*;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStub;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStubService;
import nu.mine.egoless.didattica.ws.personaclient.Studente;
import nu.mine.egoless.didattica.ws.contattoclient.Contatto;
import nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaStudente;
import nu.mine.egoless.didattica.ws.personaclient.WSPersonaService;
import nu.mine.egoless.didattica.ws.personaclient.WSPersona;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;
import java.util.Date;
import java.lang.UnsupportedOperationException;
import java.util.List;
import nu.mine.egoless.supporto.DateTimeFacade;

/**
 *
 * @author Roberto
 */
public class StudenteBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public StudenteBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of getMatricola method, of class nu.mine.egoless.didattica.app.bean.StudenteBean.
    */
   public void testGetMatricola() {
      System.out.println("getMatricola");
      
      StudenteBean instance = new StudenteBean();
            
      String expResult = "unaMatricola";
      instance.setMatricola(expResult);
      String result = instance.getMatricola();
      assertEquals(expResult, result);
   }

   /**
    * Test of setMatricola method, of class nu.mine.egoless.didattica.app.bean.StudenteBean.
    */
   public void testSetMatricola() {
      System.out.println("setMatricola");
         eventFired=false;
      
      //Passaggio da due valori
      String matricola="unaMatricola";
      String nuovaMatricola="unaNuovaMatricola";
      StudenteBean instance = new StudenteBean();
      instance.setMatricola(matricola);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unaMatricola") &&
                    evt.getNewValue().equals("unaNuovaMatricola")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(StudenteBean.MATRICOLA, l);
      
      instance.setMatricola(nuovaMatricola);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(StudenteBean.MATRICOLA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unaNuovaMatricola")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setMatricola(null);
      instance.addPropertyChangeListener(StudenteBean.MATRICOLA, l);
      instance.setMatricola(nuovaMatricola);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(StudenteBean.MATRICOLA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unaMatricola") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setMatricola(matricola);
      instance.addPropertyChangeListener(StudenteBean.MATRICOLA, l);
      instance.setMatricola(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(StudenteBean.MATRICOLA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setMatricola(matricola);
      instance.addPropertyChangeListener(StudenteBean.MATRICOLA, l);
      instance.setMatricola(matricola);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(StudenteBean.MATRICOLA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setMatricola(null);
      instance.addPropertyChangeListener(StudenteBean.MATRICOLA, l);
      instance.setMatricola(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getIdClasse method, of class nu.mine.egoless.didattica.app.bean.StudenteBean.
    */
   public void testGetIdClasse() {
      System.out.println("getIdClasse");
      
      StudenteBean instance = new StudenteBean();
    
      int expResult = 1;
      instance.setIdClasse(expResult);
      int result = instance.getIdClasse();
      assertEquals(expResult, result);
   }

   /**
    * Test of setIdClasse method, of class nu.mine.egoless.didattica.app.bean.StudenteBean.
    */
   public void testSetIdClasse() {
      System.out.println("setIdClasse");
      
     //Passaggio da un valore a un altro
      
      final int id=1;
      final int nuovoId=4;
      
      StudenteBean instance = new StudenteBean();
      instance.setIdClasse(id);
      
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
      instance.addPropertyChangeListener(StudenteBean.ID_CLASSE, l);
      
      instance.setIdClasse(nuovoId);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(StudenteBean.ID_CLASSE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdClasse(1);
      instance.addPropertyChangeListener(StudenteBean.ID_CLASSE, l);
      instance.setIdClasse(1);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getDataIscrizione method, of class nu.mine.egoless.didattica.app.bean.StudenteBean.
    */
   public void testGetDataIscrizione() {
      System.out.println("getDataIscrizione");
      
      StudenteBean instance = new StudenteBean();
      Date expResult=Calendar.getInstance().getTime();
      instance.setDataIscrizione(expResult);
      Date result = instance.getDataIscrizione();
      assertEquals(expResult, result);
   }

   /**
    * Test of setDataIscrizione method, of class nu.mine.egoless.didattica.app.bean.StudenteBean.
    */
   public void testSetDataIscrizione() {
      System.out.println("setDataIscrizione");
      
      eventFired=false;
      
      //Passaggio da due valori
      final Date data=DateTimeFacade.String2Date("2002-10-10T17:00:00.000+02:00");
      final Date nuovaData=DateTimeFacade.String2Date("2002-11-10T17:00:00.000+02:00");
                 
      StudenteBean instance = new StudenteBean();
      instance.setDataIscrizione(data);
     
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(data) &&
                    evt.getNewValue().equals(nuovaData)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(StudenteBean.DATA_ISCRIZIONE, l);
     
      instance.setDataIscrizione(nuovaData);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(StudenteBean.DATA_ISCRIZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals(nuovaData)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setDataIscrizione(null);
      instance.addPropertyChangeListener(StudenteBean.DATA_ISCRIZIONE, l);
      instance.setDataIscrizione(nuovaData);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(StudenteBean.DATA_ISCRIZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(data) &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setDataIscrizione(data);
      instance.addPropertyChangeListener(StudenteBean.DATA_ISCRIZIONE, l);
      instance.setDataIscrizione(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(StudenteBean.DATA_ISCRIZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDataIscrizione(data);
      instance.addPropertyChangeListener(StudenteBean.DATA_ISCRIZIONE, l);
      instance.setDataIscrizione(data);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(StudenteBean.DATA_ISCRIZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDataIscrizione(null);
      instance.addPropertyChangeListener(StudenteBean.DATA_ISCRIZIONE, l);
      instance.setDataIscrizione(null);
      
      assertEquals(false, eventFired);
   }

            /**
    * Funzione di aiuto per determinare se esiste un insegnante con un determinato id.
    * @param id id dell'occorrenza da trovare.
    * @return {@code true} se l'occorrenza esiste, {@code false}.
    * @throws Exception Vengono rilanciate tutte le eccezioni che si verificano.
    **/
   private static boolean esisteStudente(int id) throws Exception {
      cercaStudenteBehavior(1);
      
      java.util.List<nu.mine.egoless.didattica.ws.personaclient.Studente> result=null;
      try { // Call Web Service Operation
         nu.mine.egoless.didattica.ws.personaclient.WSPersonaService service = new nu.mine.egoless.didattica.ws.personaclient.WSPersonaService();
         nu.mine.egoless.didattica.ws.personaclient.WSPersona port = service.getWSPersonaPort();
         // TODO initialize WS operation arguments here
         nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaStudente insiemeParametri = new nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaStudente();
         // TODO process result here
         insiemeParametri.setId(id);
         result = port.cercaStudente(insiemeParametri);
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      if (result==null || result.isEmpty()) return false;
      if (result.get(0).getId()==id) return true;
      else return false;      
     
   }
   
      /**
    * Funzione di aiuto per impostare il comportamente del Web Service.
    * @param value {@true} se {@code recuperaTipiStudente} deve restituire {@code null};
    *              {@false} altrimenti.
    */
   public static void cercaStudenteBehavior(int value) {
      try { // Call Web Service Operation
         WSDidaStubService service = new WSDidaStubService();
         WSDidaStub port = service.getWSDidaStubPort();
         
         port.cercaStudenteBehavior(value);
      } catch (Exception ex) {
         //Non gestiamo le eccezioni: lasciamo che il test continui
      }
   }
   
   /**
    * Test of salvaSuWS method, of class nu.mine.egoless.didattica.app.bean.StudenteBean.
    */
   public void testSalvaSuWS() throws Exception {
      System.out.println("salvaSuWS");
      
      StudenteBean instance = new StudenteBean();
      assertEquals(instance.getId(), Costanti.ID_NUOVA_PERSONA);
      
      instance.setMatricola("matricolaDiProva");
      instance.salvaSuWS();
      assertFalse(instance.getId()==Costanti.ID_NUOVA_PERSONA);
      assertTrue(esisteStudente(instance.getId()));
   }

   /**
    * Test of caricaDaWS method, of class nu.mine.egoless.didattica.app.bean.StudenteBean.
    */
   public void testCaricaDaWS() throws Exception {
      System.out.println("caricaDaWS");
      
     // provo a caricare un elemento presente
      cercaStudenteBehavior(2);
      StudenteBean instance = new StudenteBean();
      
      // carico l'elemento con id=1
      instance.caricaDaWS(1);
      assertEquals(1, instance.getId());
      
      // provo a caricare un elemento non presente
      cercaStudenteBehavior(0);
      instance = new StudenteBean();
      instance.caricaDaWS(1);
      assertEquals(new StudenteBean().getId(), instance.getId());
      
      // provo a richiedere l'id di default
      instance = new StudenteBean();
      // dovrebbe lanciare un'eccezione
      boolean thrown=false;
      try{instance.caricaDaWS(Costanti.ID_NUOVA_PERSONA);}
      catch(UnsupportedOperationException ex){
         thrown=true;
      }
      assertTrue(thrown);
   }

   /**
    * Test of cancellaStudente method, of class nu.mine.egoless.didattica.app.bean.StudenteBean.
    */
   public void testCancellaStudente() throws Exception {
      System.out.println("cancellaStudente");
      
      StudenteBean instance = new StudenteBean();
      instance.salvaSuWS();
      //verifico comunque il caricamento
      assertTrue(esisteStudente(instance.getId()));
      instance.cancellaStudente();
      // verifico la cancellazione
      assertEquals(false, esisteStudente(instance.getId()));
   }
   
}
