/*
 * Nome file: WSAssenza.java
 * Data creazione: March 13, 2007, 9:07 AM
 * Info svn: $Id: WSDidaStub.java 684 2007-03-25 09:59:00Z eric $
 */

package ws.didattica.stub;

import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Web Service per manipolare lo stato dello stub
 * per testare i Bean.
 */
@Stateless()
@WebService()
public class WSDidaStub {
   private static long CONFIGURATION_ID=1L;
   @PersistenceContext
   private EntityManager em;
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void recuperaNazioniReturnsNull(boolean value) {
      Configuration conf=em.find(Configuration.class, CONFIGURATION_ID);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(CONFIGURATION_ID);
      }
      
      conf.setRecuperaNazioniReturnsNull(value);
      em.persist(conf);
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void recuperaReligioniReturnsNull(@WebParam(name = "value") boolean value) {
      Configuration conf=em.find(Configuration.class, CONFIGURATION_ID);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(CONFIGURATION_ID);
      }
      
      conf.setRecuperaReligioniReturnsNull(value);
      em.persist(conf);
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void recuperaTipiVotoBehavior(@WebParam(name = "value") int value) {
      Configuration conf=em.find(Configuration.class, CONFIGURATION_ID);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(CONFIGURATION_ID);
      }
      
      conf.setRecuperaTipiVotoBehavior(value);
      em.persist(conf);
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void recuperaTipiAssenzaBehavior(@WebParam(name = "value") int value) {
      Configuration conf=em.find(Configuration.class, CONFIGURATION_ID);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(CONFIGURATION_ID);
      }
      
      conf.setRecuperaTipiAssenzaBehavior(value);
      em.persist(conf);
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void recuperaMaterieBehavior(@WebParam(name = "value") int value) {
      Configuration conf=em.find(Configuration.class, CONFIGURATION_ID);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(CONFIGURATION_ID);
      }
      
      conf.setRecuperaMaterieBehavior(value);
      em.persist(conf);
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void recuperaClassiBehavior(@WebParam(name = "value") int value) {
      Configuration conf=em.find(Configuration.class, CONFIGURATION_ID);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(CONFIGURATION_ID);
      }
      
      conf.setRecuperaClassiBehavior(value);
      em.persist(conf);
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void cercaContattoBehavior(@WebParam(name = "value") int value) {
      Configuration conf=em.find(Configuration.class, CONFIGURATION_ID);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(CONFIGURATION_ID);
      }
      
      conf.setCercaContattoBehavior(value);
      em.persist(conf);
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void cercaStudenteBehavior(@WebParam(name = "value") int value) {
      Configuration conf=em.find(Configuration.class, CONFIGURATION_ID);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(CONFIGURATION_ID);
      }
      
      conf.setCercaStudenteBehavior(value);
      em.persist(conf);
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void cercaInsegnanteBehavior(@WebParam(name = "value") int value) {
      Configuration conf=em.find(Configuration.class, CONFIGURATION_ID);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(CONFIGURATION_ID);
      }
      
      conf.setCercaInsegnanteBehavior(value);
      em.persist(conf);
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void cercaVotoBehavior(@WebParam(name = "value") int value) {
      Configuration conf=em.find(Configuration.class, CONFIGURATION_ID);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(CONFIGURATION_ID);
      }
      
      conf.setCercaVotoBehavior(value);
      em.persist(conf);
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void cercaAssenzaBehavior(@WebParam(name = "value") int value) {
      Configuration conf=em.find(Configuration.class, CONFIGURATION_ID);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(CONFIGURATION_ID);
      }
      
      conf.setCercaAssenzaBehavior(value);
      em.persist(conf);
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void recuperaTipiProvaBehavior(@WebParam(name = "value") int value) {
      Configuration conf=em.find(Configuration.class, CONFIGURATION_ID);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(CONFIGURATION_ID);
      }
      
      conf.setRecuperaTipiProvaBehavior(value);
      em.persist(conf);
   }
}
