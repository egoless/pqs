/*
 * Nome file: PersonaWS.java
 * Data creazione: 25 marzo 2007, 14.02
 * Info svn: $Id: PersonaWS.java 889 2007-03-27 21:23:54Z eric $
 */

package com.swellsys.pqs.ws.api;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nu.mine.egoless.api.ws.ParametriRicercaDocente;
import nu.mine.egoless.api.ws.ParametriRicercaStudente;

/**
 * Class description
 */
@Stateless
@WebService
public class PersonaWS {
   
   @PersistenceContext
   private EntityManager em;
   
   /** Creates a new instance of PersonaWS */
   public PersonaWS() {
   }
   
   @WebMethod
   public List<DisplayEntry> getOreLezione(@WebParam(name="docente") Docente docente) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public List<DisplayEntry> getIncarichi(@WebParam(name="persona") Persona persona) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public List<Integer> cercaStudente(@WebParam(name="parametri") ParametriRicercaStudente parametri) {
      ArrayList<Integer> al=new ArrayList<Integer>();
      
      al.add(1);
      
      return al;
   }
   
   @WebMethod
   public List<DisplayEntry> getClassi(@WebParam(name="docente") Docente docente) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public List<DisplayEntry> getUtenti(@WebParam(name="persona") Persona persona) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public List<DisplayEntry> getAssenze(@WebParam(name="persona") Persona persona) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public void delCarica(@WebParam(name="persona") Persona persona, @WebParam(name="carica") Carica carica) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public void addCarica(@WebParam(name="persona") Persona persona, @WebParam(name="carica") Carica carica) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public int aggiungiStudente(@WebParam(name="nuovoStudente") Studente nuovoStudente) throws OperazioneNonValida {
      em.persist(nuovoStudente);
      em.flush();
      return nuovoStudente.getId();
   }
   
   @WebMethod
   public void modificaStudente(@WebParam(name="studente") Studente studente) throws OperazioneNonValida {
      em.persist(studente);
      em.flush();
   }
   
   @WebMethod
   public void cancellaStudente(@WebParam(name="idStudente") int idStudente) throws OperazioneNonValida {
      Studente s=em.find(Studente.class, idStudente);
      em.remove(s);
      em.flush();
   }
   
   @WebMethod
   public Studente caricaStudente(@WebParam(name="idStudente") int idStudente) throws OperazioneNonValida {
      if (idStudente==1) {
         Studente st=new Studente();
         st.setId(1);
         st.setNome("Pippo");
         
         return st;
      } else {
         return null;
      }
   }
   
   @WebMethod
   public void addGenitore(@WebParam(name="studente") Studente studente, @WebParam(name="genitore") Genitore genitore) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public void delGenitore(@WebParam(name="studente") Studente studente, @WebParam(name="genitore") Genitore genitore) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public List<DisplayEntry> getGenitori(@WebParam(name="studente") Studente studente) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public int aggiungiDocente(@WebParam(name="nuovoDocente") Docente nuovoDocente) throws OperazioneNonValida {
      em.persist(nuovoDocente);
      em.flush();
      return nuovoDocente.getId();
   }
   
   @WebMethod
   public void modificaDocente(@WebParam(name="docente") Docente docente) throws OperazioneNonValida {
      em.persist(docente);
      em.flush();
   }
   
   @WebMethod
   public void cancellaDocente(@WebParam(name="idDocente") int idDocente) throws OperazioneNonValida {
      Docente i=em.find(Docente.class, idDocente);
      em.remove(i);
      em.flush();
   }
   
   @WebMethod
   public Docente caricaDocente(@WebParam(name="idDocente") int idDocente) throws OperazioneNonValida {
      if (idDocente==1) {
         Docente d=new Docente();
         d.setId(1);
         d.setNome("John");
         d.setCognome("Cena");
         
         return d;
      } else {
         return null;
      }
   }
   
   @WebMethod
   public List<Integer> cercaDocente(@WebParam(name="parametri") ParametriRicercaDocente parametri) {
      ArrayList<Integer> al=new ArrayList<Integer>();
      
      al.add(1);
      
      return al;
   }
   
   @WebMethod
   public List<DisplayEntry> getCoordinatoreDi(@WebParam(name="docente") Docente docente) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public List<DisplayEntry> getResponsabileDi(@WebParam(name="docente") Docente docente) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public int aggiungiGenitore(@WebParam(name="nuovoGenitore") Genitore nuovoGenitore) throws OperazioneNonValida {
      return 0;
   }
   
   @WebMethod
   public void modificaGenitore(@WebParam(name="genitore") Genitore genitore) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public void cancellaGenitore(@WebParam(name="idGenitore") int idGenitore) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public Genitore caricaGenitore(@WebParam(name="idGenitore") int idGenitore) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public void addFiglio(@WebParam(name="genitore") Genitore genitore, @WebParam(name="figlio") Studente figlio) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public void delFiglio(@WebParam(name="genitore") Genitore genitore, @WebParam(name="figlio") Studente figlio) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public List<DisplayEntry> getFigli(@WebParam(name="genitore") Genitore genitore) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public int aggiungiPersonaleATA(@WebParam(name="nuovoPersonaleATA") PersonaleATA nuovoPersonaleATA) throws OperazioneNonValida {
      return 0;
   }
   
   @WebMethod
   public void modificaPersonaleATA(@WebParam(name="personaleATA") PersonaleATA personaleATA) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public void cancellaPersonaleATA(@WebParam(name="idPersonaleATA") int idPersonaleATA) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public PersonaleATA caricaPersonaleATA(@WebParam(name="idPersonaleATA") int idPersonaleATA) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public List<Integer> getMaterieDocente(@WebParam(name="idDocente") int idDocente) throws OperazioneNonValida {
      List<Integer> l=new ArrayList<Integer>();
      
      l.add(5);
      l.add(6);
      l.add(7);
      
      return l;
   }
   
   @WebMethod
   public void setMaterie(@WebParam(name="idDocente") int idDocente, @WebParam(name="idMaterie") int[] idMaterie) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public int getClasse(@WebParam(name="idStudente") int idStudente, @WebParam(name="idAnnoScolastico") int idAnnoScolastico) throws OperazioneNonValida {
      return 5;
   }
   
   @WebMethod
   public void addclasse(@WebParam(name="idStudente") int idStudente, @WebParam(name="idClasse") int idClasse) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
}
