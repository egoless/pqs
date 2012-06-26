/*
 * Nome file: VotoWS.java
 * Data creazione: 24 marzo 2007, 15.24
 * Info svn: $Id: VotoWS.java 794 2007-03-26 12:44:44Z eric $
 */

package com.swellsys.pqs.ws.modulo.registro;

import com.swellsys.pqs.ws.api.OperazioneNonValida;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nu.mine.egoless.api.ws.ParametriRicercaVoto;

/**
 * Class description
 */
@Stateless
@WebService
public class VotoWS {
   
   @PersistenceContext
   private EntityManager em;
   
   /** Creates a new instance of VotoWS */
   public VotoWS() {
   }
   
   @WebMethod
   public int aggiungiVoto(@WebParam(name="nuovoVoto") Voto nuovoVoto) {
      em.persist(nuovoVoto);
      em.flush();
      return nuovoVoto.getId();
   }
   
   @WebMethod
   public void modificaVoto(@WebParam(name="voto") Voto voto) {
      em.persist(voto);
      em.flush();
   }
   
   @WebMethod
   public void cancellaVoto(@WebParam(name="idVoto") int idVoto) throws OperazioneNonValida {
      Voto v=em.find(Voto.class,idVoto);
      em.remove(v);
      em.flush();
   }
   
   @WebMethod
   public Voto caricaVoto(@WebParam(name="idVoto") int idVoto) throws OperazioneNonValida {
      if (idVoto == 1) {
         Voto v=new Voto();
         v.setId(1);
         v.setClasseId(5);
         v.setDescrizione("pippo");
         
         return v;
      } else {
         return null;
      }
   }
   
   @WebMethod
   public List<Integer> cercaVoto(@WebParam(name="parametri") ParametriRicercaVoto parametri) {
      ArrayList<Integer> al=new ArrayList<Integer>();
      
      al.add(1);
      
      return al;
   }
   
}
