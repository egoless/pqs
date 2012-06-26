/*
 * AssenzaBeanTest.java
 * JUnit based test
 *
 * Created on 22 marzo 2007, 9.30
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import java.util.Calendar;
import junit.framework.*;
import java.util.List;
import nu.mine.egoless.didattica.ws.assenzaclient.Assenza;
import nu.mine.egoless.didattica.ws.assenzaclient.ParametriRicercaAssenza;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.assenzaclient.WSAssenzaService;
import nu.mine.egoless.didattica.ws.assenzaclient.WSAssenza;
import nu.mine.egoless.didattica.ws.assenzaclient.WSDidatticaException_Exception;
import java.util.Date;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStub;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStubService;
import nu.mine.egoless.supporto.DateTimeFacade;

/**
 *
 * @author Roberto
 */
public class AssenzaBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public AssenzaBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of getId method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testGetId() {
      System.out.println("getId");
      
      AssenzaBean instance = new AssenzaBean();
      
      int expResult = 0;
      int result = instance.getId();
      assertEquals(expResult, result);
      
     
   }

   /**
    * Test of getDataInizio method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testGetDataInizio() {
      System.out.println("getDataInizio");

      AssenzaBean instance = new AssenzaBean();
      
      Date expResult=Calendar.getInstance().getTime();
      instance.setDataInizio(expResult);
      Date result = instance.getDataInizio();
      assertEquals(expResult, result);
   }

   /**
    * Test of setDataInizio method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testSetDataInizio() {
      System.out.println("setDataInizio");
      
      eventFired=false;
      
      //Passaggio da due valori
      final Date data=DateTimeFacade.String2Date("2002-10-10T17:00:00.000+02:00");
      final Date nuovaData=DateTimeFacade.String2Date("2002-11-10T17:00:00.000+02:00");
      
      AssenzaBean instance = new AssenzaBean();
      instance.setDataInizio(data);
      
      
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
      instance.addPropertyChangeListener(AssenzaBean.DATA_INIZIO, l);
      
      instance.setDataInizio(nuovaData);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.DATA_INIZIO, l);
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
      instance.setDataInizio(null);
      instance.addPropertyChangeListener(AssenzaBean.DATA_INIZIO, l);
      instance.setDataInizio(nuovaData);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.DATA_INIZIO, l);
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
      instance.setDataInizio(data);
      instance.addPropertyChangeListener(AssenzaBean.DATA_INIZIO, l);
      instance.setDataInizio(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.DATA_INIZIO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDataInizio(data);
      instance.addPropertyChangeListener(AssenzaBean.DATA_INIZIO, l);
      instance.setDataInizio(data);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.DATA_INIZIO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDataInizio(null);
      instance.addPropertyChangeListener(AssenzaBean.DATA_INIZIO, l);
      instance.setDataInizio(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getDataFine method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testGetDataFine() {
      System.out.println("getDataFine");

      AssenzaBean instance = new AssenzaBean();
      
      Date expResult=Calendar.getInstance().getTime();
      instance.setDataFine(expResult);
      Date result = instance.getDataFine();
      assertEquals(expResult, result);
   }

   /**
    * Test of setDataFine method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testSetDataFine() {
      System.out.println("setDataFine");
      
      eventFired=false;
      
      //Passaggio da due valori
      final Date data=DateTimeFacade.String2Date("2002-10-10T17:00:00.000+02:00");
      final Date nuovaData=DateTimeFacade.String2Date("2002-11-10T17:00:00.000+02:00");
                 
      AssenzaBean instance = new AssenzaBean();
      instance.setDataFine(data);
     
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
      instance.addPropertyChangeListener(AssenzaBean.DATA_FINE, l);
     
      instance.setDataFine(nuovaData);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.DATA_FINE, l);
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
      instance.setDataFine(null);
      instance.addPropertyChangeListener(AssenzaBean.DATA_FINE, l);
      instance.setDataFine(nuovaData);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.DATA_FINE, l);
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
      instance.setDataFine(data);
      instance.addPropertyChangeListener(AssenzaBean.DATA_FINE, l);
      instance.setDataFine(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.DATA_FINE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDataFine(data);
      instance.addPropertyChangeListener(AssenzaBean.DATA_FINE, l);
      instance.setDataFine(data);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.DATA_FINE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDataFine(null);
      instance.addPropertyChangeListener(AssenzaBean.DATA_FINE, l);
      instance.setDataFine(null);
      
      assertEquals(false, eventFired);
   }

 

  

   /**
    * Test of getIdTipoAssenza method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testGetIdTipoAssenza() {
      System.out.println("getIdTipoAssenza");
      
      AssenzaBean instance = new AssenzaBean();
      
      int expResult = 1;
      instance.setIdTipoAssenza(expResult);
      int result = instance.getIdTipoAssenza();
      assertEquals(expResult, result);
      
      
   }

   /**
    * Test of setIdTipoAssenza method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testSetIdTipoAssenza() {
      System.out.println("setIdTipoAssenza");
      
      eventFired=false;
      
      //Passaggio da due valori
      final int id=1;
      final int nuovoId=4;
      
      AssenzaBean instance = new AssenzaBean();
      instance.setIdTipoAssenza(id);
      
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
      instance.addPropertyChangeListener(AssenzaBean.ID_TIPO_ASSENZA, l);
      
      instance.setIdTipoAssenza(nuovoId);
      assertEquals(true, eventFired);
      
      //Mantenimento del valore
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.ID_TIPO_ASSENZA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdTipoAssenza(id);
      instance.addPropertyChangeListener(AssenzaBean.ID_TIPO_ASSENZA, l);
      instance.setIdTipoAssenza(id);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getNote method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testGetGiustificazione() {
      System.out.println("getGiustificazione");
      
      AssenzaBean instance = new AssenzaBean();
      
      String expResult = "unaGiustificazione";
      instance.setGiustificazione(expResult);
      String result = instance.getGiustificazione();
      assertEquals(expResult, result);
      
 
   }

   /**
    * Test of setGiustificazione method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testSetGiustificazione() {
      System.out.println("setGiustificazione");
      eventFired=false;
      
      //Passaggio da due valori
      String giustificazione="unaGiustificazione";
      String nuovaGiustificazione="unaNuovaGiustificazione";
      AssenzaBean instance = new AssenzaBean();
      instance.setGiustificazione(giustificazione);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unaGiustificazione") &&
                    evt.getNewValue().equals("unaNuovaGiustificazione")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(AssenzaBean.GIUSTIFICAZIONE, l);
      
      instance.setGiustificazione(nuovaGiustificazione);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.GIUSTIFICAZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unaNuovaGiustificazione")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setGiustificazione(null);
      instance.addPropertyChangeListener(AssenzaBean.GIUSTIFICAZIONE, l);
      instance.setGiustificazione(nuovaGiustificazione);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.GIUSTIFICAZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unaGiustificazione") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setGiustificazione(giustificazione);
      instance.addPropertyChangeListener(AssenzaBean.GIUSTIFICAZIONE, l);
      instance.setGiustificazione(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.GIUSTIFICAZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setGiustificazione(giustificazione);
      instance.addPropertyChangeListener(AssenzaBean.GIUSTIFICAZIONE, l);
      instance.setGiustificazione(giustificazione);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.GIUSTIFICAZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setGiustificazione(null);
      instance.addPropertyChangeListener(AssenzaBean.GIUSTIFICAZIONE, l);
      instance.setGiustificazione(null);
      
      assertEquals(false, eventFired);
   }




   /**
    * Funzione di aiuto per determinare se esiste un'assenza con un determinato id.
    * @param id id dell'occorrenza da trovare.
    * @return {@code true} se l'occorrenza esiste, {@code false}.
    * @throws Exception Vengono rilanciate tutte le eccezioni che si verificano.
    **/
   private static boolean esisteAssenza(int id) throws Exception {
      cercaAssenzaBehavior(1);
      java.util.List<nu.mine.egoless.didattica.ws.assenzaclient.Assenza> result=null;
      
      try { // Call Web Service Operation
         nu.mine.egoless.didattica.ws.assenzaclient.WSAssenzaService service = new nu.mine.egoless.didattica.ws.assenzaclient.WSAssenzaService();
         nu.mine.egoless.didattica.ws.assenzaclient.WSAssenza port = service.getWSAssenzaPort();
         // TODO initialize WS operation arguments here
         nu.mine.egoless.didattica.ws.assenzaclient.ParametriRicercaAssenza insiemeParametri = new nu.mine.egoless.didattica.ws.assenzaclient.ParametriRicercaAssenza();
         insiemeParametri.setId(id);
         // TODO process result here
          result= port.cercaAssenza(insiemeParametri);
         
         //System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      if (result==null || result.isEmpty()) return false;
      if (result.get(0).getId()==id) return true;
      else return false;
      
      //if (assenza.getId()==id) return true;
      //else return false;
   }
   
      /**
    * Funzione di aiuto per impostare il comportamente del Web Service.
    * @param value {@true} se {@code recuperaTipiAssenza} deve restituire {@code null};
    *              {@false} altrimenti.
    */
   public static void cercaAssenzaBehavior(int value) {
      try { // Call Web Service Operation
         WSDidaStubService service = new WSDidaStubService();
         WSDidaStub port = service.getWSDidaStubPort();
         
         port.cercaAssenzaBehavior(value);
      } catch (Exception ex) {
         //Non gestiamo le eccezioni: lasciamo che il test continui
      }
   }
   
   /**
    * Test of salvaSuWS method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testSalvaSuWS() throws Exception {
      System.out.println("salvaSuWS");
      
      AssenzaBean instance = new AssenzaBean();
      assertEquals(instance.getId(), Costanti.ID_NUOVA_ASSENZA);
      
      instance.setGiustificazione("GiustificazioneProva");
      instance.salvaSuWS();
      assertFalse(instance.getId()==Costanti.ID_NUOVA_ASSENZA);
      assertEquals(true,esisteAssenza(instance.getId()));
      
   }

   /**
    * Test of caricaDaWS method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testCaricaDaWS() throws Exception {
      System.out.println("caricaDaWS");
      
      // provo a caricare un elemento presente
      cercaAssenzaBehavior(2);
      AssenzaBean instance = new AssenzaBean();
      
      // carico l'elemento con id=1
      instance.caricaDaWS(1);
      assertEquals(1, instance.getId());
      
      // provo a caricare un elemento non presente
      cercaAssenzaBehavior(0);
      instance = new AssenzaBean();
      instance.caricaDaWS(1);
      assertEquals(new AssenzaBean().getId(), instance.getId());
      
      // provo a richiedere l'id di default
      instance = new AssenzaBean();
      // AssenzaBean dovrebbe rilanciare l'eccezione
      String messaggioEx=null;
      try {instance.caricaDaWS(Costanti.ID_NUOVA_ASSENZA);}
      catch (UnsupportedOperationException ex){ 
         messaggioEx=ex.getMessage();
      }
      assertEquals("Non si puo' caricare un oggetto appena creato e non salvato",messaggioEx);
      
      
   }

   /**
    * Test of cancellaAssenza method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testCancellaAssenza() throws Exception {
      System.out.println("cancellaAssenza");
      
      AssenzaBean instance = new AssenzaBean();
            
      instance.salvaSuWS();
      //verifico comunque il caricamento
      assertTrue(esisteAssenza(instance.getId()));
      instance.cancellaAssenza();
      // verifico la cancellazione
      assertEquals(false, esisteAssenza(instance.getId()));
      
      
   }


 
   /**
    * Test of getIdPersona method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testGetIdPersona() {
      System.out.println("getIdPersona");
      
      AssenzaBean instance = new AssenzaBean();
      
      int expResult = 1;
      instance.setIdPersona(expResult);
      int result = instance.getIdPersona();
      assertEquals(expResult, result);
   }
   /**
    * Test of setIdPersona method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testSetIdPersona() {
      System.out.println("setIdPersona");
      
      eventFired=false;
      
      //Passaggio da due valori
      final int id=1;
      final int nuovoId=4;
      
      AssenzaBean instance = new AssenzaBean();
      instance.setIdPersona(id);
      
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
      instance.addPropertyChangeListener(AssenzaBean.ID_PERSONA, l);
      
      instance.setIdPersona(nuovoId);
      assertEquals(true, eventFired);
      
      //Mantenimento del valore
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.ID_PERSONA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdPersona(id);
      instance.addPropertyChangeListener(AssenzaBean.ID_PERSONA, l);
      instance.setIdPersona(id);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getIdEvento method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testGetIdEvento() {
      System.out.println("getIdEvento");
      
      AssenzaBean instance = new AssenzaBean();
      
      int expResult = 1;
      instance.setIdEvento(expResult);
      int result = instance.getIdEvento();
      assertEquals(expResult, result);
   }

   /**
    * Test of setIdEvento method, of class nu.mine.egoless.didattica.app.bean.AssenzaBean.
    */
   public void testSetIdEvento() {
      System.out.println("setIdEvento");
      
      eventFired=false;
      
      //Passaggio da due valori
      final int id=1;
      final int nuovoId=4;
      
      AssenzaBean instance = new AssenzaBean();
      instance.setIdEvento(id);
      
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
      instance.addPropertyChangeListener(AssenzaBean.ID_EVENTO, l);
      
      instance.setIdEvento(nuovoId);
      assertEquals(true, eventFired);
      
      //Mantenimento del valore
      eventFired=false;
      instance.removePropertyChangeListener(AssenzaBean.ID_EVENTO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdEvento(id);
      instance.addPropertyChangeListener(AssenzaBean.ID_EVENTO, l);
      instance.setIdEvento(id);
      
      assertEquals(false, eventFired);
   }
   
}
