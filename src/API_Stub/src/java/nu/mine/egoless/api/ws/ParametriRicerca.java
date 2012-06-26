/*
 * Nome file: ParametriRicerca.java
 * Data creazione: March 12, 2007, 4:44 PM
 * Info svn: $Id: ParametriRicerca.java 830 2007-03-26 20:23:21Z eric $
 */

package nu.mine.egoless.api.ws;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


       
/**
 * Classe base astratta per le classi con i parametri di ricerca.
 */
public abstract class ParametriRicerca 
        implements Serializable {
   
   /**
    * Holds value of property datiParziali.
    */
   Boolean datiParziali;
   
   /** Creates a new instance of ParametriRicerca */
   public ParametriRicerca() {
   }

   /**
    * Getter for property datiParziali.
    * @return Value of property datiParziali.
    */
   public Boolean getDatiParziali() {
      return this.datiParziali;
   }

   /**
    * Setter for property datiParziali.
    * @param datiParziali New value of property datiParziali.
    */
   public void setDatiParziali(Boolean datiParziali) {
      this.datiParziali = datiParziali;
   }

   protected int id;

   
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
   
}
