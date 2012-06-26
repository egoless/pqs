/*
 * Nome file:TipoAssenza.java
 * Data creazione:27 febbraio 2007, 20.06
 * Info svn: $Id: TipoAssenza.java 694 2007-03-25 12:02:13Z eric $
 */

package com.swellsys.pqs.ws.modulo.registro;

import com.swellsys.pqs.ws.api.Elemento;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Rappresenta un tipo di assenza.
 */
@Entity
public class TipoAssenza extends Elemento
        implements Serializable {
   
   /** Creates a new instance of TipoAssenza */
   public TipoAssenza() {
   }
   
   /**
    * Holds value of property descrizione.
    */
   private String descrizione;
   
   /**
    * Getter for property descrizione.
    * @return Value of property descrizione.
    */
   public String getDescrizione() {
      return this.descrizione;
   }
   
   /**
    * Setter for property descrizione.
    * @param descrizione New value of property descrizione.
    */
   public void setDescrizione(String descrizione) {
      this.descrizione = descrizione;
   }
   
   public String toString(){
      return this.getDescrizione();
   }   
}
