/*
 * Nome file: ReligioneWS.java
 * Data creazione: March 23, 2007, 1:41 PM
 * Info svn: $Id: ReligioneWS.java 738 2007-03-25 21:23:32Z eric $
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
import nu.mine.egoless.api.ws.Configuration;

/**
 * Class desccription
 */
@Stateless
@WebService
public class ReligioneWS {
   
   @PersistenceContext
   private EntityManager em;
   
   @WebMethod
   public int aggiungiReligione(@WebParam(name="nuovaReligione") Religione nuovaReligione) throws OperazioneNonValida {
      return 0;
   }
   
   @WebMethod
   public void modificaReligione(@WebParam(name="religione") Religione religione) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public void cancellaReligione(@WebParam(name="idReligione") int idReligione) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public Religione caricaReligione(@WebParam(name="idReligione") int idReligione) throws OperazioneNonValida {
      if (idReligione==12) {
         Religione obj=new Religione();
         obj.setId(12);
         obj.setNome("Mormone");
         
         return obj;
      } else {
         return null;
      }
   }
   
   @WebMethod
   public List<Integer> getReligioni() {
      Configuration conf=em.find(Configuration.class, 1L);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(1L);
      }
      
      System.out.println("SERVER:"+conf.isRecuperaReligioniNull());
      if (conf.isRecuperaReligioniNull()) {
         return null;
      } else {
         ArrayList<Integer> al=new ArrayList<Integer>();
         
         al.add(12);
         
         return al;
      }
   } 
}
