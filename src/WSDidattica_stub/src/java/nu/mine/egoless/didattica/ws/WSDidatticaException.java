/*
 * Nome file:WSDidatticaException.java
 * Data creazione: 08 marzo 2007, 10.54
 * Info svn: $Id: WSDidatticaException.java 294 2007-03-14 19:43:34Z eric $
 */

package nu.mine.egoless.didattica.ws;

import java.lang.Exception;

/**
 * Eccezione creata ad hoc per segnalare all'APP-DIDATTICA quali problemi sono
 * occorsi a livello del WSDidattica 
 */
public class WSDidatticaException extends Exception {
   
   /**
    * Crea una nuova istanza dell'Eccezione 
    */
   public WSDidatticaException() {
   }
   
   /**
    * Constructs an instance of <code>WSDidatticaException</code> with 
    * the specified detail message.
    * @param msg the detail message.
    */
   public WSDidatticaException(String msg) {
      super(msg);
   }
}
