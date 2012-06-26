/*
 * Nome file:TipoVoto.java
 * Data creazione:27 febbraio 2007, 20.06
 * Info svn: $Id: TipoVoto.java 784 2007-03-26 10:23:45Z eric $
 */

package com.swellsys.pqs.ws.modulo.registro;

import com.swellsys.pqs.ws.api.Elemento;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author STEFANO
 */
@Entity
@XmlType(name="TipoVoto")
public class TipoVoto 
        extends Elemento implements Serializable {
   
   private String nome;
   private Integer ordine;  
   private String valore;
   
   /** Creates a new instance of TipoVoto */
   public TipoVoto() {
   }

   /**
    * Getter for property descrizione.
    * @return Value of property descrizione.
    */
   public String getNome() {
      return this.nome;
   }
   
   /**
    * Setter for property descrizione.
    * @param descrizione New value of property descrizione.
    */
   public void setNome(String nome) {
      this.nome = nome;
   }

   /**
    * Getter for property ordine.
    * @return Value of property ordine.
    */
   public Integer getOrdine() {
      return this.ordine;
   }

   /**
    * Setter for property ordine.
    * @param ordine New value of property ordine.
    */
   public void setOrdine(Integer ordine) {
      this.ordine = ordine;
   }

   /**
    * Getter for property valore.
    * @return Value of property valore.
    */
   public String getValore() {
      return this.valore;
   }

   /**
    * Setter for property valore.
    * @param valore New value of property valore.
    */
   public void setValore(String valore) {
      this.valore = valore;
   }
}
