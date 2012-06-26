/*
 * Nome file: WSMateria.java
 * Data creazione: March 13, 2007, 3:06 PM
 * Info svn: $Id: WSMateria.java 800 2007-03-26 13:57:14Z eric $
 */

package nu.mine.egoless.didattica.ws;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import nu.mine.egoless.ws.materiaclient.MateriaInsegnamentoWSService;
import nu.mine.egoless.ws.materiaclient.MateriaInsegnamentoWS;
import nu.mine.egoless.ws.materiaclient.MateriaInsegnamento;
import nu.mine.egoless.ws.materiaclient.DisplayEntry;
import nu.mine.egoless.ws.materiaclient.OperazioneNonValida_Exception;

/**
 * Web Service che gestisce le materie.
 */
@Stateless()
@WebService()
public class WSMateria {
   
   @WebServiceRef(wsdlLocation = "conf/xml-resources/web-service-references/MateriaInsegnamentoWSService/wsdl/MateriaInsegnamentoWSService.wsdl")
   private MateriaInsegnamentoWSService service;
   
   
   /**
    * Aggiunge una nuova materia.
    * @param nuovaMateria Materia da aggiungere.
    * @return Id assegnato alla materia creata.
    * @throws WSDidatticaException Lanciata quando non e' stato passato
    *                              alcun tipo.
    */
   @WebMethod
   public int aggiungiMateria(@WebParam(name = "nuovaMateria") MateriaInsegnamento nuovaMateria) throws WSDidatticaException{
      if (nuovaMateria==null) {
         throw new WSDidatticaException("Non e' stato passato alcuna materia.");
      } else {
         try {
            MateriaInsegnamentoWS port = service.getMateriaInsegnamentoWSPort();
            return port.aggiungiMateriaInsegnamento(nuovaMateria);
         } catch (OperazioneNonValida_Exception ex) {
            //Mascheriamo l'eccezione
            throw new WSDidatticaException(ex.getMessage());
         }
      }
   }
   
   /**
    * Cancella una materia.
    * @param idMateria Identificativo della materia da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaMateria(@WebParam(name = "idMateria") int idMateria) throws WSDidatticaException{
      try {
         MateriaInsegnamentoWS port = service.getMateriaInsegnamentoWSPort();
         port.cancellaMateriaInsegnamento(idMateria);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Ottiene tutte le materie.
    * @return Insieme di tutte le materie.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public MateriaInsegnamento[] recuperaMaterie() throws WSDidatticaException{
      try {
         MateriaInsegnamentoWS port = service.getMateriaInsegnamentoWSPort();
         List<Integer> result = port.getMaterie();
         List<MateriaInsegnamento> materie=new ArrayList<MateriaInsegnamento>();
         
         if (result==null) {
            return null;
         } else {
            //Cicliamo sui risultati e recuperiamo le materie
            for(Integer i: result) {
               MateriaInsegnamento obj=port.caricaMateriaInsegnamento(i);
               materie.add(obj);
            }
            
            return materie.toArray(new MateriaInsegnamento[] {});
         }
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
}
