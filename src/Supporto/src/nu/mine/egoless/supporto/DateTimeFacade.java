/*
 * Nome File: DateTimeFacade.java
 * Data creazione: 11 marzo 2007, 19.32
 * Info svn: $Id: DateTimeFacade.java 285 2007-03-14 15:50:27Z roberto $
 */

package nu.mine.egoless.supporto;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Classe con funzioni per convertire oggetti
 * che rappresentano tempi in una stringa che rappresenta
 * un tempo in formato {@code xsd:dateTime} e viceversa.
 *
 * Questa classe e' necessaria in quanto JAX-WS/JAXB non
 * riescono a gestire in maniera sensata la traduzione verso/da
 * XML dei tipi che trattano date e tempi.
 *
 * E' un facade verso le classi {@code DatatypeFactory} e
 * {@code XmlGregorianCalendar}
 */
public class DateTimeFacade {
   
   /** Creates a new instance of {@code DateTimeFacade} */
   private DateTimeFacade() {
   }
   
   /**
    * Converte un oggetto di tipo {@code Date} in una stringa.
    * @param dataDaConvertire oggetto da convertire
    * @return rappresentazione dell'oggetto {@code Date} come stringa.
    */
   public static String Date2String(Date dataDaConvertire) {
      if (dataDaConvertire==null) {
         return null;
      } else {
         GregorianCalendar calendario;
         
         calendario=new GregorianCalendar();
         calendario.setTime(dataDaConvertire);
         
         return GregorianCalendar2String(calendario);
      }
   }
   
   /**
    * Converte un oggetto di tipo {@code GregorianCalendar} in una stringa.
    * @param calendarioDaConvertire oggetto da convertire
    * @return rappresentazione del calendario come stringa.
    */
   public static String GregorianCalendar2String(GregorianCalendar calendarioDaConvertire) {
      if (calendarioDaConvertire==null) {
         return null;
      } else {
         DatatypeFactory factory;
         XMLGregorianCalendar calendarioXml;
         
         try {
            factory=DatatypeFactory.newInstance();
            calendarioXml=factory.newXMLGregorianCalendar(calendarioDaConvertire);
            return calendarioXml.toString();
         } catch (DatatypeConfigurationException ex) {
            //Il verificarsi di questa eccezione indica, citando la documentazione,
            //un grave errore di configurazione. Restituiamo quindi null per
            //segnalare l'impossibilita' di operare la conversione.
            return null;
         }
      }
   }
   
   /**
    * Restituisce un oggetto {@code Date} corrispondente ad una rappresentazione
    * su stringa.
    * @param stringaDaConvertire stringa che rappresenta un tempo XML.
    * @return Oggetto {@code Date} corrispondente alla stringa.
    */
   public static Date String2Date(String stringaDaConvertire) {
      if (stringaDaConvertire==null) {
         return null;
      } else {
         GregorianCalendar calendario;
         
         calendario=String2GregorianCalendar(stringaDaConvertire);
         
         if (calendario==null) {
            return null;
         } else {
            return calendario.getTime();
         }
      }
   }
   
   /**
    * Restituisce un oggetto {@code GregorianCalendar} corrispondente ad una
    * rappresentazione su stringa.
    * @param stringaDaConvertire stringa che rappresenta un tempo XML.
    * @return Oggetto {@code GregorianCalendar} corrispondente alla stringa.
    */
   public static GregorianCalendar String2GregorianCalendar(String stringaDaConvertire) {
      if (stringaDaConvertire==null) {
         return null;
      } else {
         DatatypeFactory factory;
         XMLGregorianCalendar calendarioXml;
         
         try {
            factory=DatatypeFactory.newInstance();
            calendarioXml=factory.newXMLGregorianCalendar(stringaDaConvertire);
            return calendarioXml.toGregorianCalendar();
         } catch (DatatypeConfigurationException ex) {
            //Il verificarsi di questa eccezione indica, citando la documentazione,
            //un grave errore di configurazione. Restituiamo quindi null per
            //segnalare l'impossibilita' di operare la conversione.
            return null;
         } catch (IllegalArgumentException ex) {
            //La stringa data non e' valida.
            return null;
         }
      }
   }
}

