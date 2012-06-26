/*
 * InsegnanteBeanTest.java
 * JUnit based test
 *
 * Created on 23 marzo 2007, 21.39
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.LinkedList;
import junit.framework.*;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStub;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStubService;
import nu.mine.egoless.didattica.ws.personaclient.Docente;
import nu.mine.egoless.didattica.ws.contattoclient.Contatto;
import nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaDocente;
import nu.mine.egoless.didattica.ws.personaclient.WSPersonaService;
import nu.mine.egoless.didattica.ws.personaclient.WSPersona;
import java.util.Date;
import java.lang.UnsupportedOperationException;
import java.util.List;
import nu.mine.egoless.supporto.DateTimeFacade;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;
import nu.mine.egoless.didattica.ws.personaclient.Docente;
import nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaDocente;
//import nu.mine.egoless.didattica.ws.personaclient.Date;

/**
 *
 * @author Roberto
 */
public class InsegnanteBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public InsegnanteBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of getDataAssunzione method, of class nu.mine.egoless.didattica.app.bean.InsegnanteBean.
    */
   public void testGetDataAssunzione() {
      System.out.println("getDataAssunzione");
      
      InsegnanteBean instance = new InsegnanteBean();
      
      Date expResult=Calendar.getInstance().getTime();
      instance.setDataAssunzione(expResult);
      Date result = instance.getDataAssunzione();
      assertEquals(expResult, result);
   }

   /**
    * Test of setDataAssunzione method, of class nu.mine.egoless.didattica.app.bean.InsegnanteBean.
    */
   public void testSetDataAssunzione() {
      System.out.println("setDataAssunzione");
      
      eventFired=false;
      
      //Passaggio da due valori
      final Date data=DateTimeFacade.String2Date("2002-10-10T17:00:00.000+02:00");
      final Date nuovaData=DateTimeFacade.String2Date("2002-11-10T17:00:00.000+02:00");
                 
      InsegnanteBean instance = new InsegnanteBean();
      instance.setDataAssunzione(data);
     
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
      instance.addPropertyChangeListener(InsegnanteBean.DATA_ASSUNZIONE, l);
     
      instance.setDataAssunzione(nuovaData);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(InsegnanteBean.DATA_ASSUNZIONE, l);
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
      instance.setDataAssunzione(null);
      instance.addPropertyChangeListener(InsegnanteBean.DATA_ASSUNZIONE, l);
      instance.setDataAssunzione(nuovaData);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(InsegnanteBean.DATA_ASSUNZIONE, l);
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
      instance.setDataAssunzione(data);
      instance.addPropertyChangeListener(InsegnanteBean.DATA_ASSUNZIONE, l);
      instance.setDataAssunzione(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(InsegnanteBean.DATA_ASSUNZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDataAssunzione(data);
      instance.addPropertyChangeListener(InsegnanteBean.DATA_ASSUNZIONE, l);
      instance.setDataAssunzione(data);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(InsegnanteBean.DATA_ASSUNZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDataAssunzione(null);
      instance.addPropertyChangeListener(InsegnanteBean.DATA_ASSUNZIONE, l);
      instance.setDataAssunzione(null);
      
      assertEquals(false, eventFired);
   }


   //TODO Cancellare commento: setIdMateria non esiste più
//   /**
//    * Test of setIdMateria method, of class nu.mine.egoless.didattica.app.bean.InsegnanteBean.
//    */
//   public void testSetIdMateria() {
//      System.out.println("setIdMateria");
//      
//      //Passaggio da un valore a un altro
//      
//      final int id=1;
//      final int nuovoId=4;
//      
//      InsegnanteBean instance = new InsegnanteBean();
//      instance.setIdMateria(id);
//      
//      PropertyChangeListener l = new PropertyChangeListener() {
//         public void propertyChange(PropertyChangeEvent evt) {
//            if (evt.getOldValue().equals(id) &&
//                    evt.getNewValue().equals(nuovoId)) {
//               
//               eventFired=true;
//            } else {
//               eventFired=false;
//            }
//         }
//      };
//      instance.addPropertyChangeListener(InsegnanteBean.MATERIA_INSEGNAMENTO, l);
//      
//      instance.setIdMateria(nuovoId);
//      assertEquals(true, eventFired);
//      
//           
//      //Impostazione dello stesso valore
//      eventFired=false;
//      instance.removePropertyChangeListener(InsegnanteBean.MATERIA_INSEGNAMENTO, l);
//      l = new PropertyChangeListener() {
//         public void propertyChange(PropertyChangeEvent evt) {
//            eventFired=true;
//         }
//      };
//      instance.setIdMateria(1);
//      instance.addPropertyChangeListener(InsegnanteBean.MATERIA_INSEGNAMENTO, l);
//      instance.setIdMateria(1);
//      
//      assertEquals(false, eventFired);
//   }


   
         /**
    * Funzione di aiuto per determinare se esiste un insegnante con un determinato id.
    * @param id id dell'occorrenza da trovare.
    * @return {@code true} se l'occorrenza esiste, {@code false}.
    * @throws Exception Vengono rilanciate tutte le eccezioni che si verificano.
    **/
   private static boolean esisteInsegnante(int id) throws Exception {
      cercaInsegnanteBehavior(1);
      
      java.util.List<nu.mine.egoless.didattica.ws.personaclient.Docente> result=null;
      try { // Call Web Service Operation
         nu.mine.egoless.didattica.ws.personaclient.WSPersonaService service = new nu.mine.egoless.didattica.ws.personaclient.WSPersonaService();
         nu.mine.egoless.didattica.ws.personaclient.WSPersona port = service.getWSPersonaPort();
         // TODO initialize WS operation arguments here
         nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaDocente insiemeParametri = new nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaDocente();
         
         insiemeParametri.setId(id);
         result = port.cercaDocente(insiemeParametri);
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      if (result==null || result.isEmpty()) return false;
      if (result.get(0).getId()==id) return true;
      else return false;      
     
   }
   
      /**
    * Funzione di aiuto per impostare il comportamente del Web Service.
    * @param value {@true} se {@code recuperaTipiInsegnante} deve restituire {@code null};
    *              {@false} altrimenti.
    */
   public static void cercaInsegnanteBehavior(int value) {
      try { // Call Web Service Operation
         WSDidaStubService service = new WSDidaStubService();
         WSDidaStub port = service.getWSDidaStubPort();
         
         port.cercaInsegnanteBehavior(value);
      } catch (Exception ex) {
         //Non gestiamo le eccezioni: lasciamo che il test continui
      }
   }

   /**
    * Test of caricaDaWS method, of class nu.mine.egoless.didattica.app.bean.InsegnanteBean.
    */
   public void testCaricaDaWS() throws Exception {
      System.out.println("caricaDaWS");
     // provo a caricare un elemento presente
      cercaInsegnanteBehavior(2);
      InsegnanteBean instance = new InsegnanteBean();
      
      // carico l'elemento con id=1
      instance.caricaDaWS(1);
      assertEquals(1, instance.getId());
      
      // provo a caricare un elemento non presente
      cercaInsegnanteBehavior(0);
      instance = new InsegnanteBean();
      instance.caricaDaWS(1);
      assertEquals(new InsegnanteBean().getId(), instance.getId());
      
      // provo a richiedere l'id di default
      instance = new InsegnanteBean();
      // dovrebbe lanciare un'eccezione
      boolean thrown=false;
      try{instance.caricaDaWS(Costanti.ID_NUOVA_PERSONA);}
      catch(UnsupportedOperationException ex){
         thrown=true;
      }
      assertTrue(thrown);
   }

   /**
    * Test of salvaSuWS method, of class nu.mine.egoless.didattica.app.bean.InsegnanteBean.
    */
   public void testSalvaSuWS() throws Exception {
      System.out.println("salvaSuWS");
      
      InsegnanteBean instance = new InsegnanteBean();
      assertEquals(instance.getId(), Costanti.ID_NUOVA_PERSONA);
      
      instance.setNome("IlNomeDelProf");
      instance.salvaSuWS();
      assertFalse(instance.getId()==Costanti.ID_NUOVA_PERSONA);
      assertTrue(esisteInsegnante(instance.getId()));
   }

   /**
    * Test of cancellaInsegnante method, of class nu.mine.egoless.didattica.app.bean.InsegnanteBean.
    */
   public void testCancellaInsegnante() throws Exception {
      System.out.println("cancellaInsegnante");
      
      InsegnanteBean instance = new InsegnanteBean();
   
      instance.salvaSuWS();
      //verifico comunque il caricamento
      assertTrue(esisteInsegnante(instance.getId()));
      instance.cancellaInsegnante();
      // verifico la cancellazione
      assertEquals(false, esisteInsegnante(instance.getId()));
   }

   /**
    * Test of getIdMaterie method, of class nu.mine.egoless.didattica.app.bean.InsegnanteBean.
    */
   public void testGetIdMaterie() {
      System.out.println("getIdMaterie");
      
      InsegnanteBean instance = new InsegnanteBean();
      
      int[] expResult = new int[] {1};
      instance.setIdMaterie(expResult);
      int result = instance.getIdMaterie()[0];
      assertEquals(1, result);
      
   }

   /**
    * Test of setIdMaterie method, of class nu.mine.egoless.didattica.app.bean.InsegnanteBean.
    */
   public void testSetIdMaterie() {
      System.out.println("setIdMaterie");
      
      eventFired=false;
      
      //Passaggio da due valori
      final int[] lista=new int[] {1};
      final int[] nuovaLista=new int[] {2};
                       
      InsegnanteBean instance = new InsegnanteBean();
      instance.setIdMaterie(lista);
     
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (((int[])evt.getOldValue())[0]==(lista[0]) &&
                   ((int[])evt.getNewValue())[0]==(nuovaLista[0])) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(InsegnanteBean.ID_MATERIE_INSEGNAMENTO, l);
     
      instance.setIdMaterie(nuovaLista);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(InsegnanteBean.ID_MATERIE_INSEGNAMENTO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals(nuovaLista)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setIdMaterie(null);
      instance.addPropertyChangeListener(InsegnanteBean.ID_MATERIE_INSEGNAMENTO, l);
      instance.setIdMaterie(nuovaLista);
      
      //assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(InsegnanteBean.ID_MATERIE_INSEGNAMENTO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(lista) &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setIdMaterie(lista);
      instance.addPropertyChangeListener(InsegnanteBean.ID_MATERIE_INSEGNAMENTO, l);
      instance.setIdMaterie(null);
      
      //assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(InsegnanteBean.ID_MATERIE_INSEGNAMENTO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdMaterie(lista);
      instance.addPropertyChangeListener(InsegnanteBean.ID_MATERIE_INSEGNAMENTO, l);
      instance.setIdMaterie(lista);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(InsegnanteBean.ID_MATERIE_INSEGNAMENTO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdMaterie(null);
      instance.addPropertyChangeListener(InsegnanteBean.ID_MATERIE_INSEGNAMENTO, l);
      instance.setIdMaterie(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getMatricola method, of class nu.mine.egoless.didattica.app.bean.InsegnanteBean.
    */
   public void testGetMatricola() {
      System.out.println("getMatricola");
      
      InsegnanteBean instance = new InsegnanteBean();
      
      String expResult = "12345";
      instance.setMatricola(expResult);
      String result = instance.getMatricola();
      assertEquals(expResult, result);
   }

   /**
    * Test of setMatricola method, of class nu.mine.egoless.didattica.app.bean.InsegnanteBean.
    */
   public void testSetMatricola() {
      System.out.println("setMatricola");
      
      eventFired=false;
      
      //Passaggio da due valori
      String matricola="unMatricola";
      String nuovoMatricola="unNuovoMatricola";
      InsegnanteBean instance = new InsegnanteBean();
      instance.setMatricola(matricola);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unMatricola") &&
                    evt.getNewValue().equals("unNuovoMatricola")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(InsegnanteBean.MATRICOLA, l);
      
      instance.setMatricola(nuovoMatricola);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(InsegnanteBean.MATRICOLA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unNuovoMatricola")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setMatricola(null);
      instance.addPropertyChangeListener(InsegnanteBean.MATRICOLA, l);
      instance.setMatricola(nuovoMatricola);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(InsegnanteBean.MATRICOLA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unMatricola") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setMatricola(matricola);
      instance.addPropertyChangeListener(InsegnanteBean.MATRICOLA, l);
      instance.setMatricola(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(InsegnanteBean.MATRICOLA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setMatricola(matricola);
      instance.addPropertyChangeListener(InsegnanteBean.MATRICOLA, l);
      instance.setMatricola(matricola);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(InsegnanteBean.MATRICOLA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setMatricola(null);
      instance.addPropertyChangeListener(InsegnanteBean.MATRICOLA, l);
      instance.setMatricola(null);
      
      assertEquals(false, eventFired);
   }
   
}
