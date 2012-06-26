/*
 * Nome file:ParametriRicercaVoto.java
 * Data creazione:1 marzo 2007, 17.16
 * Info svn: $Id: ParametriRicercaStudente.java 830 2007-03-26 20:23:21Z eric $
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
public class ParametriRicercaStudente 
extends ParametriRicerca implements Serializable{
   
   /** Creates a new instance of ParametriRicercaStudente */
   public ParametriRicercaStudente() {
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

   /**
    * Holds value of property matricola.
    */
   private String matricola;

   /**
    * Getter for property matricola.
    * @return Value of property matricola.
    */
   public String getMatricola() {
      return this.matricola;
   }

   /**
    * Setter for property matricola.
    * @param matricola New value of property matricola.
    */
   public void setMatricola(String matricola) {
      this.matricola = matricola;
   }

   /**
    * Holds value of property annoCorso.
    */
   private int annoCorso;

   /**
    * Getter for property annoCorso.
    * @return Value of property annoCorso.
    */
   public int getAnnoCorso() {
      return this.annoCorso;
   }

   /**
    * Setter for property annoCorso.
    * @param annoCorso New value of property annoCorso.
    */
   public void setAnnoCorso(int annoCorso) {
      this.annoCorso = annoCorso;
   }

   /**
    * Holds value of property sezione.
    */
   private char sezione;

   /**
    * Getter for property sezione.
    * @return Value of property sezione.
    */
   public char getSezione() {
      return this.sezione;
   }

   /**
    * Setter for property sezione.
    * @param sezione New value of property sezione.
    */
   public void setSezione(char sezione) {
      this.sezione = sezione;
   }

   @Override
   public boolean equals(Object anObject){
      
      if (anObject instanceof ParametriRicercaStudente){
         ParametriRicercaStudente parametriRicercaStudente=(ParametriRicercaStudente)anObject;
         return (super.equals(parametriRicercaStudente) &&
                 parametriRicercaStudente.annoCorso==annoCorso &&
                 parametriRicercaStudente.cognome.equals(cognome) &&
                 parametriRicercaStudente.id==id &&
                 parametriRicercaStudente.matricola.equals(matricola) &&
                 parametriRicercaStudente.nome.equals(nome) &&
                 parametriRicercaStudente.sezione==sezione);
      }
      return false;
   }
   
}
