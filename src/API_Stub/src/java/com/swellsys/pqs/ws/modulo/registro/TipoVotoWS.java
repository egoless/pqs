/*
 * Nome file: TipoVotoWS.java
 * Data creazione: 24 marzo 2007, 14.15
 * Info svn: $Id: TipoVotoWS.java 784 2007-03-26 10:23:45Z eric $
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
public class TipoVotoWS {
   
   @PersistenceContext
   private EntityManager em;
   
   /** Creates a new instance of TipoVotoWS */
   public TipoVotoWS() {
   }
   
   @WebMethod
   public int aggiungiTipoVoto(@WebParam(name="nuovoTipoVoto") TipoVoto nuovoTipoVoto) throws OperazioneNonValida {
      em.persist(nuovoTipoVoto);
      em.flush();
      return nuovoTipoVoto.getId();
   }
   
   @WebMethod
   public void modificaTipoVoto(@WebParam(name="tipoVoto") TipoVoto tipoVoto) throws OperazioneNonValida {
      throw new UnsupportedOperationException("Not yet implemented");
   }
   
   @WebMethod
   public void cancellaTipoVoto(@WebParam(name="idTipoVoto") int idTipoVoto) throws OperazioneNonValida {
      TipoVoto t=em.find(TipoVoto.class,idTipoVoto);
      
      if (t!=null) {
         em.remove(t);
         em.flush();
      }
   }
   
   @WebMethod
   public TipoVoto caricaTipoVoto(@WebParam(name="idTipoVoto") int idTipoVoto) throws OperazioneNonValida {
      if (idTipoVoto == 1) {
         TipoVoto t=new TipoVoto();
         t.setNome("UnTipoDiVoto");
         t.setValore("5");
         t.setId(1);
         return t;
      } else {
         return null;
      }
   }
   
   @WebMethod
   public List<Integer> getTipivoto() {
      ArrayList<Integer> al=new ArrayList<Integer>();
      
      al.add(1);
      
      return al;
   }
}
