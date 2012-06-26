/*
 * Nome file: ContattoWS.java
 * Data creazione: 24 marzo 2007, 17.12
 * Info svn: $Id: ContattoWS.java 818 2007-03-26 16:37:37Z eric $
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
import nu.mine.egoless.api.ws.ParametriRicercaContatto;

/**
 * Class description
 */
@Stateless
@WebService
public class ContattoWS {
   
   @PersistenceContext
   private EntityManager em;
   
   /** Creates a new instance of ContattoWS */
   public ContattoWS() {
   }
   
   @WebMethod
   public int aggiungiContatto(@WebParam(name="nuovoContatto") Contatto nuovoContatto) throws OperazioneNonValida {
      em.persist(nuovoContatto);
      em.flush();
      return nuovoContatto.getId();
   }
   
   @WebMethod
   public void modificaContatto(@WebParam(name="contatto") Contatto contatto) throws OperazioneNonValida {
      em.persist(contatto);
      em.flush();
   }
   
   @WebMethod
   public void cancellaContatto(@WebParam(name="idContatto") int idContatto) throws OperazioneNonValida {
      Contatto c=em.find(Contatto.class,idContatto);
      em.remove(c);
      em.flush();
   }
   
   @WebMethod
   public Contatto caricaContatto(@WebParam(name="idContatto") int idContatto) throws OperazioneNonValida {
      if (idContatto==5) {
         Contatto c=new Contatto();
         
         c.setId(5);
         c.setVia("Belzoni");
         c.setCivico("5");
     
         return c;
      } else {
         return null;
      }
   }

   @WebMethod
   public List<Integer> cercaContatto(@WebParam(name="parametri") ParametriRicercaContatto parametri) {
      ArrayList<Integer> al=new ArrayList<Integer>();
      
      al.add(5);
      
      return al;
   }  
}
