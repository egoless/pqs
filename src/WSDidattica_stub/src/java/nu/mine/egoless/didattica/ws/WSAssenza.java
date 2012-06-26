/*
 * Nome file: WSAssenza.java
 * Data creazione: March 13, 2007, 9:07 AM
 * Info svn: $Id: WSAssenza.java 778 2007-03-26 08:36:12Z roberto $
 */

package nu.mine.egoless.didattica.ws;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
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
import nu.mine.egoless.ws.assenzaclient.Assenza;
import nu.mine.egoless.ws.assenzaclient.ParametriRicercaAssenza;
import nu.mine.egoless.supporto.DateTimeFacade;
import ws.didattica.stub.Configuration;
import ws.didattica.stub.Storage;

/**
 * Web Service per gestire le assenze.
 */
@Stateless()
@WebService()
public class WSAssenza {
   
   @PersistenceContext
   private EntityManager em;
   
   
   /**
    * Determina se i campi dell'assenza hanno valori
    * validi.
    * @param assenzaAttuale Oggetto {@link Assenza} da validare.
    * @throws WSDidatticaException quando si verificano errori di validazione.
    */
   private void validaAssenza(Assenza assenzaAttuale) throws WSDidatticaException {
      if (assenzaAttuale==null) {
         throw new WSDidatticaException("Non e' stata passata alcuna assenza");
      }
   }
   
   /**
    * Aggiunge una nuova assenza..
    * @param nuovoAssenza Assenza da aggiungere.
    * @return Id assegnato all'assenza creata.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passato nessuna assenza;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public int aggiungiAssenza(@WebParam(name = "nuovaAssenza") Assenza nuovaAssenza) throws WSDidatticaException{
      validaAssenza(nuovaAssenza);
      return Storage.persist(em, Assenza.class, nuovaAssenza, Storage.ASSENZA);
   }
   
   /**
    * Modifica un'assenza esistente.
    * @param assenza Assenza con i dati modificati.
    * @return Id assegnato all'assenza creata.:
    * <ul>
    *   <li>non e' stato passato nessuna assenza;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public void modificaAssenza(@WebParam(name = "assenza") Assenza assenza) throws WSDidatticaException{
      validaAssenza(assenza);
      Storage.persistExisting(em, Assenza.class, assenza, Storage.ASSENZA, assenza.getId());
   }
   
   /**
    * Cancella un'assenza.
    * @param idAssenza Identificativo dell'assenza da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaAssenza(@WebParam(name = "idAssenza") int idAssenza) throws WSDidatticaException{
      Storage.delete(em, idAssenza);
   }
   
   /**
    * Permette di ottenere una serie di assenze che soddisfano a dei criteri.
    * @param insiemeParametri Parametri di ricerca.
    * @return Insieme di assenze che soddisfano i criteri di ricerca.
    * @throws WSDidatticaException Segnala un generico errore del WS.
    */
   @WebMethod
   public Assenza[] cercaAssenza(@WebParam(name = "insiemeParametri") ParametriRicercaAssenza insiemeParametri) throws WSDidatticaException{
      if (insiemeParametri==null) {
         throw new WSDidatticaException("Non sono stati passati i parametri di ricerca.");
      }      else {
         Configuration conf=em.find(Configuration.class, 1L);
         
         if (conf==null) {
            conf=new Configuration();
            conf.setCercaAssenzaBehavior(1);
         } else {
             em.refresh(conf);
         }
         
         if (conf==null || conf.getCercaAssenzaBehavior()==0) {
            return null;
         } else {
            List<Assenza> result=new ArrayList<Assenza>();
            JAXBContext jc;
            JAXBElement<Assenza> je;
            
            if (conf.getCercaAssenzaBehavior()==1) {
               List<Storage> l=Storage.retrieve(em, Storage.ASSENZA);
               try {
                  jc = JAXBContext.newInstance(Assenza.class);
                  
                  Unmarshaller u = jc.createUnmarshaller();
                  for(Storage st : l) {
                     if (insiemeParametri.getId()==0 || st.getId()==insiemeParametri.getId()) {
                        je=u.unmarshal(new StreamSource(new StringReader(st.getContent())), Assenza.class);
                        Assenza tv =  je.getValue();
                        tv.setId(st.getId());
                        result.add(tv);
                     }
                  }
               } catch (JAXBException ex) {
                  ex.printStackTrace();
               }
            } else {
               //Restituiamo dei dati al volo
               Assenza a=new Assenza();
               a.setId(1);
               //a.setGiustificata(false);
               a.setPersonaId(5);
               a.setTipoAssenzaId(7);
               a.setGiustificazione("Aveva l'influenza");
               //a.setDataInizio(DateTimeFacade.Date2String(Calendar.getInstance().getTime()));
               //a.setDataFine(DateTimeFacade.Date2String(Calendar.getInstance().getTime()));
               result.add(a);
               
               a=new Assenza();
               a.setId(2);
              // a.setGiustificata(true);
               a.setPersonaId(7);
               a.setTipoAssenzaId(1);
               //a.setDataInizio(DateTimeFacade.Date2String(Calendar.getInstance().getTime()));
               //a.setDataFine(DateTimeFacade.Date2String(Calendar.getInstance().getTime()));
               result.add(a);
               
               a=new Assenza();
               a.setId(3);
               //a.setGiustificata(false);
               a.setPersonaId(5);
               a.setTipoAssenzaId(3);
               //a.setDataInizio(DateTimeFacade.Date2String(Calendar.getInstance().getTime()));
               //a.setDataFine(DateTimeFacade.Date2String(Calendar.getInstance().getTime()));
               result.add(a);
            }
            
            return result.toArray(new Assenza[] {});
         }
      }
   }
}
