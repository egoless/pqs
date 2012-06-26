/*
 * Nome file: TipoProvaWS.java
 * Data creazione: 25 marzo 2007, 12.57
 * Info svn: $Id: TipoProvaWS.java 738 2007-03-25 21:23:32Z eric $
 */

package com.swellsys.pqs.ws.modulo.registro;

import com.swellsys.pqs.ws.api.OperazioneNonValida;
import java.util.List;
import java.util.ArrayList;
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
public class TipoProvaWS {
   
   @PersistenceContext
   private EntityManager em;
   
   /** Creates a new instance of TipoProvaWS */
   public TipoProvaWS() {
   }
   
   @WebMethod
   public int aggiungiTipoProva(@WebParam(name="nuovoTipoProva") TipoProva nuovoTipoProva) throws OperazioneNonValida {
      em.persist(nuovoTipoProva);
      em.flush();
      return nuovoTipoProva.getId();
   }
   
   @WebMethod
   public void modificaTipoProva(@WebParam(name="tipoProva") TipoProva tipoProva) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public void cancellaTipoProva(@WebParam(name="idTipoProva") int idTipoProva) throws OperazioneNonValida {
      TipoProva t=em.find(TipoProva.class,idTipoProva);
      em.remove(t);
      em.flush();
   }
   
   @WebMethod
   public TipoProva caricaTipoProva(@WebParam(name="idTipoProva") int idTipoProva) throws OperazioneNonValida {
     if (idTipoProva==1) {
        TipoProva obj=new TipoProva();
        
        obj.setId(1);
        obj.setNome("UnTipoDiProva");
        return obj;
     } else {
        return null;
     }
   }
   
   @WebMethod
   public List<Integer> getTipiProva() {
      ArrayList<Integer> al=new ArrayList<Integer>();
      
      al.add(1);
      
      return al;
   }
   
}
