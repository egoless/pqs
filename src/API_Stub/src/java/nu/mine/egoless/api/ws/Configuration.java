/*
 * Nome file: Configuration.java
 * Data creazione: March 14, 2007, 3:16 PM
 * Info svn: $Id: Configuration.java 565 2007-03-21 14:13:32Z roberto $
 */

package nu.mine.egoless.api.ws;

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
   @GeneratedValue(strategy = GenerationType.AUTO)
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
      return "nu.mine.egoless.api.ws.Configuration[id=" + id + "]";
   }

   /**
    * Holds value of property recuperaNazioniNull.
    */
   private boolean recuperaNazioniNull;

   /**
    * Getter for property recuperaNazioniNull.
    * @return Value of property recuperaNazioniNull.
    */
   public boolean isRecuperaNazioniNull() {
      return recuperaNazioniNull;
   }

   /**
    * Setter for property recuperaNazioniNull.
    * @param recuperaNazioniNull New value of property recuperaNazioniNull.
    */
   public void setRecuperaNazioniNull(boolean pRecuperaNazioniNull) {
      recuperaNazioniNull = pRecuperaNazioniNull;
   }
   
   /**
    * Holds value of property recuperaReligioniNull.
    */
   private boolean recuperaReligioniNull;

   /**
    * Getter for property recuperaReligioniNull.
    * @return Value of property recuperaReligioniNull.
    */
   public boolean isRecuperaReligioniNull() {
      return recuperaReligioniNull;
   }

   /**
    * Setter for property recuperaReligioniNull.
    * @param recuperaNazioniNull New value of property recuperaReligioniNull.
    */
   public void setRecuperaReligioniNull(boolean pRecuperaReligioniNull) {
      recuperaReligioniNull = pRecuperaReligioniNull;
   }
   
  
   
   
}
