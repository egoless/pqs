/*
 * PersonaBeanTest.java
 * JUnit based test
 *
 * Created on 24 marzo 2007, 12.07
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import java.util.Calendar;
import junit.framework.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import nu.mine.egoless.didattica.ws.personaclient.Persona;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;
import nu.mine.egoless.didattica.ws.contattoclient.Contatto;
import nu.mine.egoless.didattica.ws.religioneclient.Religione;
import nu.mine.egoless.supporto.DateTimeFacade;

/**
 *
 * @author Roberto
 */
public class PersonaBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public PersonaBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of getCodiceFiscale method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testGetCodiceFiscale() {
      System.out.println("getCodiceFiscale");
      
      PersonaBean instance = new StudenteBean();
      
      String expResult = "RFETRE32F13F422P";
      instance.setCodiceFiscale(expResult);
      String result = instance.getCodiceFiscale();
      assertEquals(expResult, result);

   }

   /**
    * Test of setCodiceFiscale method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testSetCodiceFiscale() {
      System.out.println("setCodiceFiscale");
      eventFired=false;
      
      //Passaggio da due valori
      String codiceFiscale="unCodiceFiscale";
      String nuovoCodiceFiscale="unNuovoCodiceFiscale";
      PersonaBean instance = new StudenteBean();
      instance.setCodiceFiscale(codiceFiscale);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unCodiceFiscale") &&
                    evt.getNewValue().equals("unNuovoCodiceFiscale")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(PersonaBean.CODICE_FISCALE, l);
      
      instance.setCodiceFiscale(nuovoCodiceFiscale);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.CODICE_FISCALE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unNuovoCodiceFiscale")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setCodiceFiscale(null);
      instance.addPropertyChangeListener(PersonaBean.CODICE_FISCALE, l);
      instance.setCodiceFiscale(nuovoCodiceFiscale);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.CODICE_FISCALE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unCodiceFiscale") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setCodiceFiscale(codiceFiscale);
      instance.addPropertyChangeListener(PersonaBean.CODICE_FISCALE, l);
      instance.setCodiceFiscale(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.CODICE_FISCALE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setCodiceFiscale(codiceFiscale);
      instance.addPropertyChangeListener(PersonaBean.CODICE_FISCALE, l);
      instance.setCodiceFiscale(codiceFiscale);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.CODICE_FISCALE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setCodiceFiscale(null);
      instance.addPropertyChangeListener(PersonaBean.CODICE_FISCALE, l);
      instance.setCodiceFiscale(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getCognome method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testGetCognome() {
      System.out.println("getCognome");
      
      PersonaBean instance = new StudenteBean();
      
      String expResult = "unCognome";
      instance.setCognome(expResult);
      String result = instance.getCognome();
      assertEquals(expResult, result);

   }

   /**
    * Test of setCognome method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testSetCognome() {
      System.out.println("setCognome");
       eventFired=false;
      
      //Passaggio da due valori
      String cognome="unCognome";
      String nuovoCognome="unNuovoCognome";
      PersonaBean instance = new StudenteBean();
      instance.setCognome(cognome);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unCognome") &&
                    evt.getNewValue().equals("unNuovoCognome")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(PersonaBean.COGNOME, l);
      
      instance.setCognome(nuovoCognome);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.COGNOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unNuovoCognome")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setCognome(null);
      instance.addPropertyChangeListener(PersonaBean.COGNOME, l);
      instance.setCognome(nuovoCognome);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.COGNOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unCognome") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setCognome(cognome);
      instance.addPropertyChangeListener(PersonaBean.COGNOME, l);
      instance.setCognome(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.COGNOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setCognome(cognome);
      instance.addPropertyChangeListener(PersonaBean.COGNOME, l);
      instance.setCognome(cognome);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.COGNOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setCognome(null);
      instance.addPropertyChangeListener(PersonaBean.COGNOME, l);
      instance.setCognome(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getDataNascita method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testGetDataNascita() {
      System.out.println("getDataNascita");
      
      PersonaBean instance = new StudenteBean();
      
      Date expResult=Calendar.getInstance().getTime();
      instance.setDataNascita(expResult);
      Date result = instance.getDataNascita();
      assertEquals(expResult, result);
   }

   /**
    * Test of setDataNascita method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testSetDataNascita() {
      System.out.println("setDataNascita");
      
      eventFired=false;
      
      //Passaggio da due valori
      final Date data=DateTimeFacade.String2Date("2002-10-10T17:00:00.000+02:00");
      final Date nuovaData=DateTimeFacade.String2Date("2002-11-10T17:00:00.000+02:00");
      
      PersonaBean instance = new StudenteBean();
      instance.setDataNascita(data);
      
      
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
      instance.addPropertyChangeListener(PersonaBean.DATA_NASCITA, l);
      
      instance.setDataNascita(nuovaData);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.DATA_NASCITA, l);
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
      instance.setDataNascita(null);
      instance.addPropertyChangeListener(PersonaBean.DATA_NASCITA, l);
      instance.setDataNascita(nuovaData);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.DATA_NASCITA, l);
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
      instance.setDataNascita(data);
      instance.addPropertyChangeListener(PersonaBean.DATA_NASCITA, l);
      instance.setDataNascita(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.DATA_NASCITA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDataNascita(data);
      instance.addPropertyChangeListener(PersonaBean.DATA_NASCITA, l);
      instance.setDataNascita(data);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.DATA_NASCITA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDataNascita(null);
      instance.addPropertyChangeListener(PersonaBean.DATA_NASCITA, l);
      instance.setDataNascita(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getId method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testGetId() {
      System.out.println("getId");
      
      PersonaBean instance = new StudenteBean();
      
      int expResult = 0;
      int result = instance.getId();
      assertEquals(expResult, result);

   }

   /**
    * Test of getIdIndirizzoResidenza method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testGetIdIndirizzoResidenza() {
      System.out.println("getIdIndirizzoResidenza");
      
      PersonaBean instance = new StudenteBean();
      
      int expResult = 1;
      instance.setIdIndirizzoResidenza(expResult);
      int result = instance.getIdIndirizzoResidenza();
      assertEquals(expResult, result);
   }

   /**
    * Test of setIdIndirizzoResidenza method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testSetIdIndirizzoResidenza() {
      System.out.println("setIdIndirizzoResidenza");
      
       //Passaggio da un valore a un altro
      
      final int id=1;
      final int nuovoId=4;
      
      PersonaBean instance = new StudenteBean();
      instance.setIdIndirizzoResidenza(id);
      
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
      instance.addPropertyChangeListener(PersonaBean.INDIRIZZO_RESIDENZA, l);
      
      instance.setIdIndirizzoResidenza(nuovoId);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.INDIRIZZO_RESIDENZA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdIndirizzoResidenza(1);
      instance.addPropertyChangeListener(PersonaBean.INDIRIZZO_RESIDENZA, l);
      instance.setIdIndirizzoResidenza(1);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getNome method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testGetNome() {
      System.out.println("getNome");
      
      PersonaBean instance = new StudenteBean();
      
      String expResult = "unNome";
      instance.setNome(expResult);
      String result = instance.getNome();
      assertEquals(expResult, result);
   }

   /**
    * Test of setNome method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testSetNome() {
      System.out.println("setNome");
      
     eventFired=false;
      
      //Passaggio da due valori
      String nome="unNome";
      String nuovoNome="unNuovoNome";
      PersonaBean instance = new StudenteBean();
      instance.setNome(nome);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unNome") &&
                    evt.getNewValue().equals("unNuovoNome")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(PersonaBean.NOME, l);
      
      instance.setNome(nuovoNome);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.NOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unNuovoNome")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setNome(null);
      instance.addPropertyChangeListener(PersonaBean.NOME, l);
      instance.setNome(nuovoNome);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.NOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unNome") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setNome(nome);
      instance.addPropertyChangeListener(PersonaBean.NOME, l);
      instance.setNome(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.NOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setNome(nome);
      instance.addPropertyChangeListener(PersonaBean.NOME, l);
      instance.setNome(nome);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.NOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setNome(null);
      instance.addPropertyChangeListener(PersonaBean.NOME, l);
      instance.setNome(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of isPortatoreHandicap method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testIsPortatoreHandicap() {
      System.out.println("isPortatoreHandicap");
      
      PersonaBean instance = new StudenteBean();
      
      boolean expResult = true;
      instance.setPortatoreHandicap(true);
      boolean result = instance.isPortatoreHandicap();
      assertEquals(expResult, result);
      
      expResult = false;
      instance.setPortatoreHandicap(false);
      result = instance.isPortatoreHandicap();
      assertEquals(expResult, result);
   }

   /**
    * Test of setPortatoreHandicap method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testSetPortatoreHandicap() {
      System.out.println("setPortatoreHandicap");
      
      //Passaggio da true a false
      PersonaBean instance = new StudenteBean();
      instance.setPortatoreHandicap(true);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(true) &&
                    evt.getNewValue().equals(false)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(PersonaBean.PORTATORE_HANDICAP, l);
      
      instance.setPortatoreHandicap(false);
      assertEquals(true, eventFired);
      
      //Passaggio da false a true
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.PORTATORE_HANDICAP, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(false) &&
                    evt.getNewValue().equals(true)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setPortatoreHandicap(false);
      instance.addPropertyChangeListener(PersonaBean.PORTATORE_HANDICAP, l);
      instance.setPortatoreHandicap(true);
      
      assertEquals(true, eventFired);
      
      //Mantenimento di true
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.PORTATORE_HANDICAP, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setPortatoreHandicap(true);
      instance.addPropertyChangeListener(PersonaBean.PORTATORE_HANDICAP, l);
      instance.setPortatoreHandicap(true);
      
      assertEquals(false, eventFired);
      
      //Mantenimento di false
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.PORTATORE_HANDICAP, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setPortatoreHandicap(false);
      instance.addPropertyChangeListener(PersonaBean.PORTATORE_HANDICAP, l);
      instance.setPortatoreHandicap(false);
      
      assertEquals(false, eventFired);
      
   }

   /**
    * Test of setIdNazionalita method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testSetIdNazionalita() {
      System.out.println("setIdNazionalita");
      
      //Passaggio da un valore a un altro
      
      final int id=1;
      final int nuovoId=4;
      
      PersonaBean instance = new StudenteBean();
      instance.setIdNazionalita(id);
      
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
      instance.addPropertyChangeListener(PersonaBean.NAZIONALITA, l);
      
      instance.setIdNazionalita(nuovoId);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.NAZIONALITA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdNazionalita(1);
      instance.addPropertyChangeListener(PersonaBean.NAZIONALITA, l);
      instance.setIdNazionalita(1);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getIdNazionalita method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testGetIdNazionalita() {
      System.out.println("getIdNazionalita");
      
      PersonaBean instance = new StudenteBean();
      
      int expResult = 1;
      instance.setIdNazionalita(expResult);
      int result = instance.getIdNazionalita();
      assertEquals(expResult, result);
   }

   /**
    * Test of setIdReligione method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testSetIdReligione() {
      System.out.println("setIdReligione");
      
      //Passaggio da un valore a un altro
      
      final int id=1;
      final int nuovoId=4;
      
      PersonaBean instance = new StudenteBean();
      instance.setIdReligione(id);
      
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
      instance.addPropertyChangeListener(PersonaBean.RELIGIONE, l);
      
      instance.setIdReligione(nuovoId);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.RELIGIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdReligione(1);
      instance.addPropertyChangeListener(PersonaBean.RELIGIONE, l);
      instance.setIdReligione(1);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getIdReligione method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testGetIdReligione() {
      System.out.println("getIdReligione");
      
      PersonaBean instance = new StudenteBean();
      
      int expResult = 1;
      instance.setIdReligione(expResult);
      int result = instance.getIdReligione();
      assertEquals(expResult, result);
   }



   /**
    * Test of getIdIstituto method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testGetIdIstituto() {
      System.out.println("getIdIstituto");
      
      PersonaBean instance = new StudenteBean();
      
      int expResult = 1;
      instance.setIdIstituto(expResult);
      int result = instance.getIdIstituto();
      assertEquals(expResult, result);
      

   }

   /**
    * Test of setIdIstituto method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testSetIdIstituto() {
      System.out.println("setIdIstituto");
      
      //Passaggio da un valore a un altro
      
      final int id=1;
      final int nuovoId=4;
      
      PersonaBean instance = new StudenteBean();
      instance.setIdIstituto(id);
      
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
      instance.addPropertyChangeListener(PersonaBean.ISTITUTO, l);
      
      instance.setIdIstituto(nuovoId);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.ISTITUTO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdIstituto(1);
      instance.addPropertyChangeListener(PersonaBean.ISTITUTO, l);
      instance.setIdIstituto(1);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getSesso method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testGetSesso() {
      System.out.println("getSesso");
      
      PersonaBean instance = new StudenteBean();
      
      int expResult = 2;
      instance.setSesso(expResult);
      int result = instance.getSesso();
      assertEquals(expResult, result);
      
  
   }

   /**
    * Test of setSesso method, of class nu.mine.egoless.didattica.app.bean.PersonaBean.
    */
   public void testSetSesso() {
      
      System.out.println("setSesso");
      //Passaggio da un valore a un altro
      
      final int sesso=1;
      final int nuovoSesso=4;
      
      PersonaBean instance = new StudenteBean();
      instance.setSesso(sesso);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(sesso) &&
                    evt.getNewValue().equals(nuovoSesso)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(PersonaBean.SESSO, l);
      
      instance.setSesso(nuovoSesso);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(PersonaBean.SESSO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setSesso(1);
      instance.addPropertyChangeListener(PersonaBean.SESSO, l);
      instance.setSesso(1);
      
      assertEquals(false, eventFired);
   }
   
}
