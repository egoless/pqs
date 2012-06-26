/*
 * Nome file: AssenzaWS.java
 * Data creazione: 24 marzo 2007, 17.20
 * Info svn: $Id: AssenzaWS.java 830 2007-03-26 20:23:21Z eric $
 */

package com.swellsys.pqs.ws.modulo.registro;

import com.swellsys.pqs.ws.api.OperazioneNonValida;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import nu.mine.egoless.api.ws.ParametriRicercaAssenza;

/**
 * Class description
 */
@Stateless
@WebService
public class AssenzaWS {
   
   @PersistenceContext
   private EntityManager em;
   
   /** Creates a new instance of AssenzaWS */
   public AssenzaWS() {
   }
   
   @WebMethod
   public int aggiungiAssenza(@WebParam(name="nuovaAssenza") Assenza nuovaAssenza) throws OperazioneNonValida {
      em.persist(nuovaAssenza);
      em.flush();
      return nuovaAssenza.getId();
   }
   
   @WebMethod
   public void modificaAssenza(@WebParam(name="assenza") Assenza assenza) throws OperazioneNonValida {
      em.persist(assenza);
      em.flush();
   }
   
   @WebMethod
   public void cancellaAssenza(@WebParam(name="idAssenza") int idAssenza) {
      Assenza a=em.find(Assenza.class,idAssenza);
      em.remove(a);
      em.flush();
   }
   
   @WebMethod
   public Assenza caricaAssenza(@WebParam(name="idAssenza") int idAssenza) throws OperazioneNonValida {
      if(idAssenza==1) {
         Assenza n=new Assenza();
         n.setGiustificazione("UnAssenza");
         n.setId(1);
         return n;
      } else {
         return null;
      }
   }
   
   @WebMethod
   public List<Integer> cercaAssenza(@WebParam(name="parametri") ParametriRicercaAssenza parametri) {
      ArrayList<Integer> al=new ArrayList<Integer>();
      
      al.add(1);
      
      return al;
   }
   
}
