/*
 * Nome file:Religione.java
 * Data creazione:27 febbraio 2007, 20.06
 * Info svn: $Id: Religione.java 693 2007-03-25 11:48:47Z eric $
 */

package com.swellsys.pqs.ws.api;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author STEFANO
 */
@Entity
public class Religione extends Elemento
        implements Serializable {
   
   /** Creates a new instance of Religione */
   public Religione() {
   }

   /**
    * Holds value of property nome.
    */
   private String nome;

   /**
    * Getter for property nome.
    * @return Value of property nome.
    */
   public String getNome() {
      return this.nome;
   }

   /**
    * Setter for property nome.
    * @param nome New value of property nome.
    */
   public void setNome(String nome) {
      this.nome = nome;
   }   
}
