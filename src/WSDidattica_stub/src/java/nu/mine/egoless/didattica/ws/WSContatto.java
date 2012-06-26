/*
 * Nome file: WSContatto.java
 * Data creazione: March 13, 2007, 8:54 AM
 * Info svn: $Id: WSContatto.java 778 2007-03-26 08:36:12Z roberto $
 */

package nu.mine.egoless.didattica.ws;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import nu.mine.egoless.ws.contattoclient.Contatto;
import nu.mine.egoless.ws.contattoclient.ParametriRicercaContatto;
import ws.didattica.stub.Configuration;
import ws.didattica.stub.Storage;

/**
 * Web Service per gestire i contatti.
 */
@Stateless()
@WebService()
public class WSContatto {
   
   @PersistenceContext
   private EntityManager em;
   
   
   /**
    * Determina se i campi del contatto hanno valori
    * validi.
    * @param contattoAttuale Oggetto {@link Contatto} da validare.
    * @throws WSDidatticaException quando si verificano errori di validazione.
    */
   private void validaContatto(Contatto contattoAttuale) throws WSDidatticaException {
      if (contattoAttuale==null) {
         throw new WSDidatticaException("Non e' stato passatto alcun contatto.");
      }
   }
   
   /**
    * Aggiunge un nuovo contatto.
    * @param nuovoConatto Contatto da aggiungere.
    * @return Id assegnato al contatto creato.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passato nessun contatto;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public int aggiungiContatto(@WebParam(name = "nuovoContatto") Contatto nuovoContatto) throws WSDidatticaException{
      validaContatto(nuovoContatto);
      return Storage.persist(em, Contatto.class, nuovoContatto, Storage.CONTATTO);
      
   }
   
   /**
    * Modifica i dati di un contatto esistente.
    * @param contatto Contatto con i dati modificati.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passato nessun contatto;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public void modificaContatto(@WebParam(name = "contatto") Contatto contatto) throws WSDidatticaException{
      validaContatto(contatto);
      Storage.persistExisting(em, Contatto.class, contatto, Storage.CONTATTO, contatto.getId());
   }
   
   /**
    * Cancella un contatto.
    * @param idContatto Identificativo del contatto da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaContatto(@WebParam(name = "idContatto") int idContatto) throws WSDidatticaException {
      Storage.delete(em, idContatto);
   }
   
   /**
    * Permette di ottenere una serie di contatti che soddisfano a dei criteri.
    * @param insiemeParametri Parametri di ricerca.
    * @return Insieme di contatti che soddisfano i criteri di ricerca.
    * @throws WSDidatticaException Segnala un generico errore del WS.
    */
   @WebMethod
   public Contatto[] cercaContatto(@WebParam(name = "insiemeParametri") ParametriRicercaContatto insiemeParametri) throws WSDidatticaException {
      if (insiemeParametri==null) {
         throw new WSDidatticaException("Non sono stati passati i parametri di ricerca.");
      }      else {
         Configuration conf=em.find(Configuration.class, 1L);
         
         if (conf==null) {
            conf=new Configuration();
            conf.setCercaContattoBehavior(1);
         } else {
             em.refresh(conf);
         }
         
         if (conf==null || conf.getCercaContattoBehavior()==0) {
            return null;
         } else {
            List<Contatto> result=new ArrayList<Contatto>();
            JAXBContext jc;
            JAXBElement<Contatto> je;
            
            if (conf.getCercaContattoBehavior()==1) {
               List<Storage> l=Storage.retrieve(em, Storage.CONTATTO);
               try {
                  jc = JAXBContext.newInstance(Contatto.class);
                  
                  Unmarshaller u = jc.createUnmarshaller();
                  for(Storage st : l) {
                     if (insiemeParametri.getId()==0 || st.getId()==insiemeParametri.getId()) {
                        je=u.unmarshal(new StreamSource(new StringReader(st.getContent())), Contatto.class);
                        Contatto tv =  je.getValue();
                        tv.setId(st.getId());
                        result.add(tv);
                     }
                  }
               } catch (JAXBException ex) {
                  ex.printStackTrace();
               }
            } else {
               //SRestituiamo dei dati al volo
               Contatto obj=new Contatto();
               obj.setId(1);
               //obj.setPersonaId(1);
               obj.setNazioneId(1);
               obj.setVia("Via Belzoni");
               obj.setCivico("5");
               obj.setCap("40040");
               obj.setCitta("Padova");
               obj.setProvincia("PD");
               obj.setTelefonoPrincipale("0000544");
               result.add(obj);
               
               obj=new Contatto();
               obj.setId(2);
               //obj.setIdPersona(2);
               obj.setNazioneId(1);
               obj.setVia("Via Morgagni");
               obj.setCivico("7A");
               //obj.setCAP("40040");
               obj.setCitta("Padova");
               obj.setProvincia("PD");
               obj.setTelefonoPrincipale("0110544");
               result.add(obj);
               
               obj=new Contatto();
               obj.setId(3);
               //obj.setIdPersona(3);
               obj.setNazioneId(3);
               obj.setVia("Humphrey Street");
               obj.setCivico("5");
               //obj.setCAP("4845");
               obj.setCitta("Manchester");
               obj.setProvincia("Manchester");
               obj.setTelefonoPrincipale("995665");
               result.add(obj);
            }
            
            return result.toArray(new Contatto[] {});
         }
      }
   }
}
