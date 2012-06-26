/*
 * Nome file: DisplayEntry.java
 * Data creazione: 24 marzo 2007, 10.26
 * Info svn: $Id: DisplayEntry.java 637 2007-03-24 10:43:19Z eric $
 */

package com.swellsys.pqs.ws.api;

/**
 * Classe che contiene i dati essenziali di un oggetto.
 */
public class DisplayEntry {

   private int id;
   private String string;
   
   /** Creates a new instance of DisplayEntry */
   public DisplayEntry() {
   }
   
   /**
    * Getter for property id.
    * @return Value of property id.
    */
   public int getId() {
      return this.id;
   }

   /**
    * Setter for property id.
    * @param id New value of property id.
    */
   public void setId(int id) {
      this.id = id;
   }

   /**
    * Getter for property string.
    * @return Value of property string.
    */
   public String getString() {
      return this.string;
   }

   /**
    * Setter for property string.
    * @param string New value of property string.
    */
   public void setString(String string) {
      this.string = string;
   }
   
}
