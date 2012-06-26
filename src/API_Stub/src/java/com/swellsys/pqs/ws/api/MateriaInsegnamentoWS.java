/*
 * Nome file: MateriaInsegnamentoWS.java
 * Data creazione: 24 marzo 2007, 11.11
 * Info svn: $Id: MateriaInsegnamentoWS.java 738 2007-03-25 21:23:32Z eric $
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
 * WS per gestire le materie.
 */
@Stateless
@WebService
public class MateriaInsegnamentoWS {
   
   @PersistenceContext
   private EntityManager em;
   
   /** Creates a new instance of MateriaInsegnamentoWS */
   public MateriaInsegnamentoWS() {
   }
   
   @WebMethod
   public List<Integer> getOreLezione(@WebParam(name="materiaInsegnamento") MateriaInsegnamento materiaInsegnamento) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public List<DisplayEntry> getDocenti(@WebParam(name="materiaInsegnamento") MateriaInsegnamento materiaInsegnamento) throws OperazioneNonValida {
      return null;
   }
   
   @WebMethod
   public List<Integer> getMaterie() {
      ArrayList<Integer> al=new ArrayList<Integer>();
      
      al.add(5);
      
      return al;
   }
   
   @WebMethod
   public int aggiungiMateriaInsegnamento(@WebParam(name="nuovaMateriaInsegnamento") MateriaInsegnamento nuovaMateriaInsegnamento) throws OperazioneNonValida {
      em.persist(nuovaMateriaInsegnamento);
      em.flush();
      return nuovaMateriaInsegnamento.getId();
   }
   
   @WebMethod
   public void modificaMateriaInsegnamento(@WebParam(name="materiaInsegnamento") MateriaInsegnamento materiaInsegnamento) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public void cancellaMateriaInsegnamento(@WebParam(name="idMateriaInsegnamento") int idMateriaInsegnamento) throws OperazioneNonValida {
      MateriaInsegnamento m=em.find(MateriaInsegnamento.class, idMateriaInsegnamento);
      em.remove(m);
      em.flush();
   }
   
   @WebMethod
   public MateriaInsegnamento caricaMateriaInsegnamento(@WebParam(name="idMateriaInsegnamento") int idMateriaInsegnamento) throws OperazioneNonValida {
      if (idMateriaInsegnamento==5) {
         MateriaInsegnamento m=new MateriaInsegnamento();
         m.setId(5);
         m.setNome("UnaMateria");
         return m;
      } else {
         return null;
      }
   }
   
}
