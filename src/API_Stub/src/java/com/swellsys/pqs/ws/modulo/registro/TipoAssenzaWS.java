/*
 * Nome file: TipoAssenzaWS.java
 * Data creazione: 24 marzo 2007, 12.44
 * Info svn: $Id: TipoAssenzaWS.java 738 2007-03-25 21:23:32Z eric $
 */

package com.swellsys.pqs.ws.modulo.registro;

import com.swellsys.pqs.ws.api.OperazioneNonValida;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class description
 */
@Stateless
@WebService
public class TipoAssenzaWS {
   
   @PersistenceContext
   private EntityManager em;
   
   @WebMethod
   public int aggiungiTipoAssenza(@WebParam(name="nuovoTipoAssenza") TipoAssenza nuovoTipoAssenza) throws OperazioneNonValida {
      em.persist(nuovoTipoAssenza);
      em.flush();
      return nuovoTipoAssenza.getId();
   }
   
   @WebMethod
   public void modificaTipoAssenza(@WebParam(name="tipoAssenza") TipoAssenza tipoAssenza) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public void cancellaTipoAssenza(@WebParam(name="idTipoAssenza") int idTipoAssenza) throws OperazioneNonValida {
      TipoAssenza t=em.find(TipoAssenza.class, idTipoAssenza);
      em.remove(t);
      em.flush();
   }
   
   @WebMethod
   public TipoAssenza caricaTipoAssenza(@WebParam(name="idTipoAssenza") int idTipoAssenza) throws OperazioneNonValida {
      if(idTipoAssenza==1) {
         TipoAssenza n=new TipoAssenza();
         n.setDescrizione("UnTipoDiAssenza");
         n.setId(1);
         return n;
      } else {
         return null;
      }
   }
   
   @WebMethod
   public List<Integer> getTipiAssenza() {
      ArrayList<Integer> al=new ArrayList<Integer>();
      
      al.add(1);
      
      return al;
   }
}
