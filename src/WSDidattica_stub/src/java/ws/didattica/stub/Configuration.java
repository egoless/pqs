/*
 * Nome file: Configuration.java
 * Data creazione: March 14, 2007, 5:30 PM
 * Info svn: $Id: Configuration.java 684 2007-03-25 09:59:00Z eric $
 */

package ws.didattica.stub;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class Configuration
 * Class desccription
 */
@Entity
public class Configuration implements Serializable {

   @Id
  // @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   
   /** Creates a new instance of Configuration */
   public Configuration() {
   }

   /**
    * Gets the id of this Configuration.
    * @return the id
    */
   public Long getId() {
      return this.id;
   }

   /**
    * Sets the id of this Configuration to the specified value.
    * @param id the new id
    */
   public void setId(Long id) {
      this.id = id;
   }

   /**
    * Returns a hash code value for the object.  This implementation computes 
    * a hash code value based on the id fields in this object.
    * @return a hash code value for this object.
    */
   @Override
   public int hashCode() {
      int hash = 0;
      hash += (this.id != null ? this.id.hashCode() : 0);
      return hash;
   }

   /**
    * Determines whether another object is equal to this Configuration.  The result is 
    * <code>true</code> if and only if the argument is not null and is a Configuration object that 
    * has the same id field values as this object.
    * @param object the reference object with which to compare
    * @return <code>true</code> if this object is the same as the argument;
    * <code>false</code> otherwise.
    */
   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof Configuration)) {
         return false;
      }
      Configuration other = (Configuration)object;
      if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) return false;
      return true;
   }

   /**
    * Returns a string representation of the object.  This implementation constructs 
    * that representation based on the id fields.
    * @return a string representation of the object.
    */
   @Override
   public String toString() {
      return "ws.didattica.stub.Configuration[id=" + id + "]";
   }

   /**
    * Holds value of property recuperaNazioniReturnsNull.
    */
   private boolean recuperaNazioniReturnsNull;

   /**
    * Getter for property recuperaNazioniReturnsNull.
    * @return Value of property recuperaNazioniReturnsNull.
    */
   public boolean isRecuperaNazioniReturnsNull() {
      return this.recuperaNazioniReturnsNull;
   }

   /**
    * Setter for property recuperaNazioniReturnsNull.
    * @param recuperaNazioniReturnsNull New value of property recuperaNazioniReturnsNull.
    */
   public void setRecuperaNazioniReturnsNull(boolean recuperaNazioniReturnsNull) {
      this.recuperaNazioniReturnsNull = recuperaNazioniReturnsNull;
   }

   /**
    * Holds value of property recuperaReligioniReturnsNull.
    */
   private boolean recuperaReligioniReturnsNull;

   /**
    * Getter for property recuperaReligioniReturnsNull.
    * @return Value of property recuperaReligioniReturnsNull.
    */
   public boolean isRecuperaReligioniReturnsNull() {
      return this.recuperaReligioniReturnsNull;
   }

   /**
    * Setter for property recuperaReligioniReturnsNull.
    * @param recuperaReligioniReturnsNull New value of property recuperaReligioniReturnsNull.
    */
   public void setRecuperaReligioniReturnsNull(boolean recuperaReligioniReturnsNull) {
      this.recuperaReligioniReturnsNull = recuperaReligioniReturnsNull;
   }

   /**
    * Holds value of property recuperaTipiVotoBehavior.
    */
   private int recuperaTipiVotoBehavior;

   /**
    * Getter for property recuperaTipiVotoReturnsNull.
    * @return Value of property recuperaTipiVotoReturnsNull.
    */
   public int getRecuperaTipiVotoBehavior() {
      return this.recuperaTipiVotoBehavior;
   }

   /**
    * Setter for property recuperaTipiVotoReturnsNull.
    * @param recuperaTipiVotoReturnsNull New value of property recuperaTipiVotoReturnsNull.
    */
   public void setRecuperaTipiVotoBehavior(int recuperaTipiVotoBehavior) {
      this.recuperaTipiVotoBehavior = recuperaTipiVotoBehavior;
   }

   /**
    * Holds value of property recuperaTipiAssenzaBehavior.
    */
   private int recuperaTipiAssenzaBehavior;

   /**
    * Getter for property recuperaTipiAssenzaReturnsNull.
    * @return Value of property recuperaTipiAssenzaReturnsNull.
    */
   public int getRecuperaTipiAssenzaBehavior() {
      return this.recuperaTipiAssenzaBehavior;
   }

   /**
    * Setter for property recuperaTipiAssenzaReturnsNull.
    * @param recuperaTipiAssenzaReturnsNull New value of property recuperaTipiAssenzaReturnsNull.
    */
   public void setRecuperaTipiAssenzaBehavior(int recuperaTipiAssenzaBehavior) {
      this.recuperaTipiAssenzaBehavior = recuperaTipiAssenzaBehavior;
   }

   /**
    * Holds value of property recuperaMaterieBehavior.
    */
   private int recuperaMaterieBehavior;

   /**
    * Getter for property recuperaMaterieReturnsNull.
    * @return Value of property recuperaMaterieReturnsNull.
    */
   public int getRecuperaMaterieBehavior() {
      return this.recuperaMaterieBehavior;
   }

   /**
    * Setter for property recuperaMaterieReturnsNull.
    * @param recuperaMaterieReturnsNull New value of property recuperaMaterieReturnsNull.
    */
   public void setRecuperaMaterieBehavior(int recuperaMaterieBehavior) {
      this.recuperaMaterieBehavior = recuperaMaterieBehavior;
   }

   /**
    * Holds value of property cercaContattoBehavior.
    */
   private int cercaContattoBehavior;

   /**
    * Getter for property cercaContattoReturnsNull.
    * @return Value of property cercaContattoReturnsNull.
    */
   public int getCercaContattoBehavior() {
      return this.cercaContattoBehavior;
   }

   /**
    * Setter for property cercaContattoReturnsNull.
    * @param cercaContattoReturnsNull New value of property cercaContattoReturnsNull.
    */
   public void setCercaContattoBehavior(int cercaContattoBehavior) {
      this.cercaContattoBehavior = cercaContattoBehavior;
   }

   /**
    * Holds value of property recuperaClassiBehavior.
    */
   private int recuperaClassiBehavior;

   /**
    * Getter for property recuperaClassiReturnsNull.
    * @return Value of property recuperaClassiReturnsNull.
    */
   public int getRecuperaClassiBehavior() {
      return this.recuperaClassiBehavior;
   }

   /**
    * Setter for property recuperaClassiReturnsNull.
    * @param recuperaClassiReturnsNull New value of property recuperaClassiReturnsNull.
    */
   public void setRecuperaClassiBehavior(int recuperaClassiBehavior) {
      this.recuperaClassiBehavior = recuperaClassiBehavior;
   }

   /**
    * Holds value of property cercaStudenteBehavior.
    */
   private int cercaStudenteBehavior;

   /**
    * Getter for property cercaStudenteReturnsNull.
    * @return Value of property cercaStudenteReturnsNull.
    */
   public int getCercaStudenteBehavior() {
      return this.cercaStudenteBehavior;
   }

   /**
    * Setter for property cercaStudenteReturnsNull.
    * @param cercaStudenteReturnsNull New value of property cercaStudenteReturnsNull.
    */
   public void setCercaStudenteBehavior(int cercaStudenteBehavior) {
      this.cercaStudenteBehavior = cercaStudenteBehavior;
   }

   /**
    * Holds value of property cercaInsegnanteBehavior.
    */
   private int cercaInsegnanteBehavior;

   /**
    * Getter for property cercaInsegnanteReturnsNull.
    * @return Value of property cercaInsegnanteReturnsNull.
    */
   public int getCercaInsegnanteBehavior() {
      return this.cercaInsegnanteBehavior;
   }

   /**
    * Setter for property cercaInsegnanteReturnsNull.
    * @param cercaInsegnanteReturnsNull New value of property cercaInsegnanteReturnsNull.
    */
   public void setCercaInsegnanteBehavior(int cercaInsegnanteBehavior) {
      this.cercaInsegnanteBehavior = cercaInsegnanteBehavior;
   }

   /**
    * Holds value of property cercaAssenzaBehavior.
    */
   private int cercaAssenzaBehavior;

   /**
    * Getter for property cercaAssenzaReturnsNull.
    * @return Value of property cercaAssenzaReturnsNull.
    */
   public int getCercaAssenzaBehavior() {
      return this.cercaAssenzaBehavior;
   }

   /**
    * Setter for property cercaAssenzaReturnsNull.
    * @param cercaAssenzaReturnsNull New value of property cercaAssenzaReturnsNull.
    */
   public void setCercaAssenzaBehavior(int cercaAssenzaBehavior) {
      this.cercaAssenzaBehavior = cercaAssenzaBehavior;
   }

   /**
    * Holds value of property cercaVotoBehavior.
    */
   private int cercaVotoBehavior;

   /**
    * Getter for property cercaVotoReturnsNull.
    * @return Value of property cercaVotoReturnsNull.
    */
   public int getCercaVotoBehavior() {
      return this.cercaVotoBehavior;
   }

   /**
    * Setter for property cercaVotoReturnsNull.
    * @param cercaVotoReturnsNull New value of property cercaVotoReturnsNull.
    */
   public void setCercaVotoBehavior(int cercaVotoBehavior) {
      this.cercaVotoBehavior = cercaVotoBehavior;
   }

   /**
    * Holds value of property recuperaTipiProvaBehavior.
    */
   private int recuperaTipiProvaBehavior;

   /**
    * Getter for property recuperaTipiProvaBehavior.
    * @return Value of property recuperaTipiProvaBehavior.
    */
   public int getRecuperaTipiProvaBehavior() {
      return this.recuperaTipiProvaBehavior;
   }

   /**
    * Setter for property recuperaTipiProvaBehavior.
    * @param recuperaTipiProvaBehavior New value of property recuperaTipiProvaBehavior.
    */
   public void setRecuperaTipiProvaBehavior(int recuperaTipiProvaBehavior) {
      this.recuperaTipiProvaBehavior = recuperaTipiProvaBehavior;
   }
   
}
