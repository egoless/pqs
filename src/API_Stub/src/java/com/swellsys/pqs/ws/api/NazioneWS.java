/*
 * Nome file: NazioneWS.java
 * Data creazione: March 23, 2007, 10:22 AM
 * Info svn: $Id: NazioneWS.java 738 2007-03-25 21:23:32Z eric $
 */

package com.swellsys.pqs.ws.api;

import java.util.List;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nu.mine.egoless.api.ws.Configuration;

/**
 * Class desccription
 */
@Stateless
@WebService
public class NazioneWS {
   
   @PersistenceContext
   private EntityManager em;
   
   /** Creates a new instance of NazioneWS */
   public NazioneWS() {
   }
   
   @WebMethod
   public int aggiungiNazione(@WebParam(name="nuovaNazione") Nazione nuovaNazione) throws OperazioneNonValida {
      return 0;
   }
   
   @WebMethod
   public void modificaNazione(@WebParam(name="nazione") Nazione nazione) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public void cancellaNazione(@WebParam(name="idNazione") int idNazione) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public Nazione caricaNazione(@WebParam(name="idNazione") int idNazione) throws OperazioneNonValida {
      if (idNazione==95) {
         Nazione obj=new Nazione();
         obj.setId(95);
         obj.setNome("Pippo");
         return obj;
      } else {
         return null;
      }
   }
   
   @WebMethod
   public List<Integer> getNazioni() {
      Configuration conf=em.find(Configuration.class, 1L);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(1L);
      }
      System.out.println("SERVER:"+conf.isRecuperaNazioniNull());
      if (conf.isRecuperaNazioniNull()) {
         return null;
      } else {
         ArrayList<Integer> al=new ArrayList<Integer>();
         
         al.add(95);
         
         return al;
      }
   }
}
