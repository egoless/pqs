/*
 * Nome file: ClasseWS.java
 * Data creazione: 24 marzo 2007, 13.04
 * Info svn: $Id: ClasseWS.java 811 2007-03-26 15:45:49Z eric $
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

/**
 * Class description
 */
@Stateless
@WebService
public class ClasseWS {
   
   @PersistenceContext
   private EntityManager em;
   
   /** Creates a new instance of ClasseWS */
   public ClasseWS() {
   }
   
   @WebMethod
   public List<DisplayEntry> getDocenti(@WebParam(name="classe") Classe classe) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public int aggiungiClasse(@WebParam(name="nuovaClasse") Classe nuovaClasse) throws OperazioneNonValida {
      em.persist(nuovaClasse);
      em.flush();
      return nuovaClasse.getId();
   }
   
   @WebMethod
   public void modificaClasse(@WebParam(name="classe") Classe classe) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public void cancellaClasse(@WebParam(name="idClasse") int idClasse) throws OperazioneNonValida {
      Classe c=em.find(Classe.class,idClasse);
      em.remove(c);
      em.flush();
   }
   
   @WebMethod
   public Classe caricaClasse(@WebParam(name="idClasse")int idClasse) throws OperazioneNonValida {
      if (idClasse==5) {
         Classe c=new Classe();
         
         c.setId(5);
         c.setSezione('A');
         c.setAnnoCorso(5);
     
         return c;
      } else {
         return null;
      }
   }
   
   @WebMethod
   public List<Integer> getClassi() {
      ArrayList<Integer> al=new ArrayList<Integer>();
      
      al.add(5);
      
      return al;
   }
   
   @WebMethod
   public List<DisplayEntry> getCariche(@WebParam(name="classe")Classe classe, @WebParam(name="ruoloCarica") RuoloCarica ruoloCarica) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public List<DisplayEntry> getAule(@WebParam(name="classe") Classe classe) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public List<DisplayEntry> getConsigliClasse(@WebParam(name="classe") Classe classe) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public List<DisplayEntry> getMaterie(@WebParam(name="classe") Classe classe) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public String getNomeCompleto(@WebParam(name="classe") Classe classe) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public List<Integer> getOreLzione(@WebParam(name="classe") Classe classe) throws OperazioneNonValida {
      return null;
   }
   
}
