/*
 * Nome file: Nazione.java
 * Data creazione: March 23, 2007, 10:39 AM
 * Info svn: $Id: Nazione.java 693 2007-03-25 11:48:47Z eric $
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
public class Nazione extends Elemento
        implements Serializable{
   private String nome;
   private String codice;
   
   /** Creates a new instance of Nazione */
   public Nazione() {
   }
   
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
   
   /**
    * Getter for property codice.
    * @return Value of property codice.
    */
   public String getCodice() {
      return this.codice;
   }

   /**
    * Setter for property codice.
    * @param codice New value of property codice.
    */
   public void setCodice(String codice) {
      this.codice = codice;
   }
}
