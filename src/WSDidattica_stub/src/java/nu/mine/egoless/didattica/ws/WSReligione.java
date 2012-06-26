/*
 * Nome file: WSReligione.java
 * Data creazione: March 23, 2007, 6:52 PM
 * Info svn: $Id: WSReligione.java 825 2007-03-26 19:33:46Z eric $
 */

package nu.mine.egoless.didattica.ws;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nu.mine.egoless.ws.religioneclient.Religione;
import ws.didattica.stub.Configuration;

/**
 * Web Service per la gestione delle religioni.
 */
@Stateless()
@WebService()
public class WSReligione {
   
   @PersistenceContext
   private EntityManager em;
   
   /**
    * Ottiene tutte le religioni gestite dal sistema.
    * @return Insieme di tutte le religioni.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public Religione[] recuperaReligioni() throws WSDidatticaException{
      Configuration conf=em.find(Configuration.class, 1L);
      
      
      if (conf==null) {
         conf=new Configuration();
         conf.setRecuperaReligioniReturnsNull(false);
      } else {
         em.refresh(conf);
      }
      
      if(conf.isRecuperaReligioniReturnsNull()) {
         return null;
      } else {
         Religione n1=new Religione();
         n1.setId(1);
         n1.setNome("Scientology");
         
         Religione n2=new Religione();
         n2.setId(2);
         n2.setNome("Cristiana");
         
         Religione n3=new Religione();
         n2.setId(3);
         n2.setNome("Musulmana");
         
         return new Religione[] {n1, n2, n3};
      }
      
   }
}
