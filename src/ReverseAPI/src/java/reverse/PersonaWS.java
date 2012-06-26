/*
 * Nome file: PersonaWS.java
 * Data creazione: 26 marzo 2007, 21.31
 * Info svn: $Id$
 */

package reverse;

import com.swellsys.pqs.ws.api.Carica;
import com.swellsys.pqs.ws.api.Docente;
import com.swellsys.pqs.ws.api.Genitore;
import com.swellsys.pqs.ws.api.ParametriRicercaDocente;
import com.swellsys.pqs.ws.api.ParametriRicercaStudente;
import com.swellsys.pqs.ws.api.Persona;
import com.swellsys.pqs.ws.api.PersonaleATA;
import com.swellsys.pqs.ws.api.Studente;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * Class description
 */
@Stateless
@WebService(serviceName = "PersonaWSService", portName = "PersonaWSPort", endpointInterface = "com.swellsys.pqs.ws.api.PersonaWS", targetNamespace = "http://api.ws.pqs.swellsys.com/", wsdlLocation = "META-INF/wsdl/PersonaWS/PersonaWSService.wsdl")
public class PersonaWS implements com.swellsys.pqs.ws.api.PersonaWS {
   
   /** Creates a new instance of PersonaWS */
   public PersonaWS() {
   }

   public java.util.List<com.swellsys.pqs.ws.api.DisplayEntry> getIncarichi(Persona persona) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public java.util.List<com.swellsys.pqs.ws.api.DisplayEntry> getUtenti(Persona persona) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public java.util.List<com.swellsys.pqs.ws.api.DisplayEntry> getAssenze(Persona persona) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public void delCarica(Persona persona, Carica carica) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public void addCarica(Persona persona, Carica carica) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public int aggiungiStudente(Studente nuovoStudente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return 0;
   }

   public void modificaStudente(Studente studente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public void cancellaStudente(int idStudente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public Studente caricaStudente(int idStudente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public java.util.List<java.lang.Integer> cercaStudente(ParametriRicercaStudente parametri) {
      return null;
   }

   public void addGenitore(Studente studente, Genitore genitore) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public void delGenitore(Studente studente, Genitore genitore) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public java.util.List<com.swellsys.pqs.ws.api.DisplayEntry> getGenitori(Studente studente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public int getClasse(int idStudente, int idAnnoScolastico) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return 0;
   }

   public java.util.List<java.lang.Integer> getClassi(int idStudente) {
      return null;
   }

   public void addclasse(int idStudente, int idClasse) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public void delClasse(int idStudente, int idClasse) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public int aggiungiDocente(Docente nuovoDocente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return 0;
   }

   public void modificaDocente(Docente docente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public void cancellaDocente(int idDocente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public Docente caricaDocente(int idDocente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public java.util.List<java.lang.Integer> cercaDocente(ParametriRicercaDocente parametri) {
      return null;
   }

   public java.util.List<com.swellsys.pqs.ws.api.DisplayEntry> getCoordinatoreDi(Docente docente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public java.util.List<com.swellsys.pqs.ws.api.DisplayEntry> getClassiDocente(Docente docente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public java.util.List<com.swellsys.pqs.ws.api.DisplayEntry> getOreLezione(Docente docente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public java.util.List<com.swellsys.pqs.ws.api.DisplayEntry> getResponsabileDi(Docente docente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public java.util.List<java.lang.Integer> getMaterie(int idDocente) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public void setMaterie(int idDocente, java.util.List<java.lang.Integer> idMaterie) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public int aggiungiGenitore(Genitore nuovoGenitore) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return 0;
   }

   public void modificaGenitore(Genitore genitore) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public void cancellaGenitore(int idGenitore) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public Genitore caricaGenitore(int idGenitore) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public void addFiglio(Genitore genitore, Studente figlio) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public void delFiglio(Genitore genitore, Studente figlio) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public java.util.List<com.swellsys.pqs.ws.api.DisplayEntry> getFigli(Genitore genitore) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }

   public int aggiungiPersonaleATA(PersonaleATA nuovoPersonaleATA) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return 0;
   }

   public void modificaPersonaleATA(PersonaleATA personaleATA) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public void cancellaPersonaleATA(int idPersonaleATA) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public PersonaleATA caricaPersonaleATA(int idPersonaleATA) throws com.swellsys.pqs.ws.api.OperazioneNonValida_Exception {
      return null;
   }
   
}
