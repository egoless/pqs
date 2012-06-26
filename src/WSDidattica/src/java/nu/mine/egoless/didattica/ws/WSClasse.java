/*
 * Nome file: WSClasse.java
 * Data creazione: March 13, 2007, 1:48 PM
 * Info svn: $Id: WSClasse.java 727 2007-03-25 18:33:13Z eric $
 */

package nu.mine.egoless.didattica.ws;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import nu.mine.egoless.ws.classeclient.ClasseWSService;
import nu.mine.egoless.ws.classeclient.ClasseWS;
import nu.mine.egoless.ws.classeclient.Classe;
import nu.mine.egoless.ws.classeclient.DisplayEntry;
import nu.mine.egoless.ws.classeclient.OperazioneNonValida_Exception;

/**
 * Web Service per gestire le classi.
 */
@Stateless()
@WebService()
public class WSClasse {
   
   @WebServiceRef(wsdlLocation = "conf/xml-resources/web-service-references/ClasseWSService/wsdl/ClasseWSService.wsdl")
   private ClasseWSService service;
   
   /**
    * Determina se i dati di una classe sono validi.
    * @param classeAttuale Classe da validare.
    * @throws WSDidatticaException se ci sono errori di validazione.
    */
   private void validaClasse(Classe classeAttuale) throws WSDidatticaException {
      if (classeAttuale==null) {
         throw new WSDidatticaException("Non e' stato passato alcuna classe.");
      }
      
      //Validazione numero classe
      if (classeAttuale.getAnnoCorso()<1 || classeAttuale.getAnnoCorso()>5) {
         throw new WSDidatticaException("L'anno del corso non e' compreso tra 1 e 5.");
      }
   }
   
   /**
    * Aggiunge una nuova classe.
    * @param nuovaClasse Classe da aggiungere.
    * @return Id assegnato alla classe creata.
    * @throws WSDidatticaException Lanciata quando non e' stato passato
    *                              alcun tipo.
    */
   @WebMethod
   public int aggiungiClasse(@WebParam(name = "nuovaClasse") Classe nuovaClasse) throws WSDidatticaException {
      try {
         ClasseWS port;
         
         validaClasse(nuovaClasse);
         port = service.getClasseWSPort();
         return port.aggiungiClasse(nuovaClasse);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Cancella una classe.
    * @param idClasse Identificativo della classe da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaClasse(@WebParam(name = "idClasse") int idClasse) throws WSDidatticaException {
      try {
         ClasseWS port = service.getClasseWSPort();
         port.cancellaClasse(idClasse);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Ottiene tutte le classi.
    * @return Insieme di tutte le classi.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public Classe[] recuperaClassi() throws WSDidatticaException {
      try {
         ClasseWS port = service.getClasseWSPort();
         List<Integer> result = port.getClassi();
         List<Classe> tipiAssenza=new ArrayList<Classe>();
         
         if (result==null) {
            return null;
         } else {
            //Cicliamo sui risultati e recuperiamo le classi
            for(Integer i: result) {
               Classe obj=port.caricaClasse(i);
               tipiAssenza.add(obj);
            }
            
            return tipiAssenza.toArray(new Classe[] {});
         }
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
}
