/*
 * Nome file:ParametriRicercaDocente.java
 * Data creazione:1 marzo 2007, 17.16
 * Info svn: $Id: ParametriRicercaDocente.java 830 2007-03-26 20:23:21Z eric $
 */

package nu.mine.egoless.api.ws;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author STEFANO
 */
public class ParametriRicercaDocente 
extends ParametriRicerca implements Serializable{
   
   /**
    * Creates a new instance of ParametriRicercaDocente
    */
   public ParametriRicercaDocente() {
   }
   
   /**
    * Holds value of property idMateria.
    */
   private long idMateria;
   
   /**
    * Getter for property idMateria.
    * @return Value of property idMateria.
    */
   public long getIdMateria() {
      return this.idMateria;
   }
   
   /**
    * Setter for property idMateria.
    * @param idMateria New value of property idMateria.
    */
   public void setIdMateria(long idMateria) {
      this.idMateria = idMateria;
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

   /**
    * Holds value of property cognome.
    */
   private String cognome;

   /**
    * Getter for property cognome.
    * @return Value of property cognome.
    */
   public String getCognome() {
      return this.cognome;
   }

   /**
    * Setter for property cognome.
    * @param cognome New value of property cognome.
    */
   public void setCognome(String cognome) {
      this.cognome = cognome;
   }
   
   @Override
   public boolean equals(Object anObject){
      
      if (anObject instanceof ParametriRicercaDocente){
         ParametriRicercaDocente parametriRicercaInsegnante=(ParametriRicercaDocente)anObject;
         return (super.equals(parametriRicercaInsegnante) &&
                 parametriRicercaInsegnante.id==id &&
                 parametriRicercaInsegnante.idMateria==idMateria);
      }
      return false;
   }
   
}
