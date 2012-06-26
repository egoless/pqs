/*
 * Nome file: WSNazione.java
 * Data creazione: March 23, 2007, 6:56 PM
 * Info svn: $Id: WSNazione.java 825 2007-03-26 19:33:46Z eric $
 */

package nu.mine.egoless.didattica.ws;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nu.mine.egoless.ws.nazioneclient.Nazione;
import ws.didattica.stub.Configuration;

/**
 * Web Service che gestisce le nazioni.
 */
@Stateless()
@WebService()
public class WSNazione {
   
   @PersistenceContext
   private EntityManager em;
   
   /**
    * Ottiene tutte le nazioni/nazionalita' gestite dal sistema.
    * @return Insieme di tutte le nazioni.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public Nazione[] recuperaNazioni() throws WSDidatticaException{
      Configuration conf=em.find(Configuration.class, 1L);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setRecuperaNazioniReturnsNull(false);
      } else {
         em.refresh(conf);
      }
      
      if(conf.isRecuperaNazioniReturnsNull()) {
         return null;
      } else {
         Nazione n1=new Nazione();
         n1.setId(1);
         n1.setNome("USA");
         
         Nazione n2=new Nazione();
         n2.setId(2);
         n2.setNome("Albania");
         return new Nazione[] {n1, n2};
      }
      
   }
}
