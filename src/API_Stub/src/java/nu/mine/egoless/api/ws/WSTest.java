/*
 * Nome file: WSTest.java
 * Data creazione: March 14, 2007, 3:25 PM
 * Info svn: $Id: WSTest.java 830 2007-03-26 20:23:21Z eric $
 */

package nu.mine.egoless.api.ws;

import com.swellsys.pqs.ws.api.Classe;
import com.swellsys.pqs.ws.api.Contatto;
import com.swellsys.pqs.ws.api.Docente;
import com.swellsys.pqs.ws.api.MateriaInsegnamento;
import com.swellsys.pqs.ws.api.Studente;
import com.swellsys.pqs.ws.modulo.registro.Assenza;
import com.swellsys.pqs.ws.modulo.registro.TipoAssenza;
import com.swellsys.pqs.ws.modulo.registro.TipoProva;
import com.swellsys.pqs.ws.modulo.registro.TipoVoto;
import com.swellsys.pqs.ws.modulo.registro.Voto;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import javax.xml.bind.JAXBContext;

/**
 *
 * @author eric
 */

@Stateless()
@WebService()
public class WSTest {
   
   @PersistenceContext
   private EntityManager em;
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void recuperaNazioniBehavior(@WebParam(name = "returnNull") boolean returnNull) {
      
      Configuration conf=em.find(Configuration.class, 1L);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(1L);
      }
      
      conf.setRecuperaNazioniNull(returnNull);
      em.persist(conf);
      em.flush();
      
      //JAXBContext jc = JAXBContext.newInstance( "nu.mine.egoless.api.ws" );
      
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   @Oneway
   public void recuperaReligioniBehavior(@WebParam(name = "returnNull") boolean returnNull) {
      
      Configuration conf=em.find(Configuration.class, 1L);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setId(1L);
      }
      
      conf.setRecuperaReligioniNull(returnNull);
      em.persist(conf);
      em.flush();
      
   }
   
   /**
    * Web service operation
    */
   @WebMethod
   public boolean opern() {
      em.flush();
      //EntityManager em2=emf.createEntityManager();
      Configuration conf=em.find(Configuration.class, 1L);
      //conf.flush();
//fare FLUSH
      
      // TODO implement operation
      
      return conf.isRecuperaNazioniNull();
      
   }
   //*** TIPO VOTO ***
   @WebMethod
   public TipoVoto caricaTipoVoto(int idTipoVoto){
      em.flush();
      TipoVoto tp=em.find(TipoVoto.class, idTipoVoto);
      
      if(tp!=null) {
         em.refresh(tp);
      }
      
      return tp;
   }
   
   @WebMethod
   public int salvaTipoVoto(TipoVoto tipoVoto){
      em.persist(tipoVoto);
      em.flush();
      return tipoVoto.getId();
   }
   
   @WebMethod
   public void eliminaTipoVoto(int idTipoVoto){
      TipoVoto tipoVoto=em.find(TipoVoto.class,idTipoVoto);
      
      if (tipoVoto!=null) {
         em.remove(tipoVoto);
         em.flush();
      }
   }
   
   //*** TIPO PROVA ***
   @WebMethod
   public TipoProva caricaTipoProva(int idTipoProva){
      em.flush();
      TipoProva tp=em.find(TipoProva.class, idTipoProva);
      
      if(tp!=null) {
         em.refresh(tp);
      }
      
      return tp;
   }
   
   @WebMethod
   public int salvaTipoProva(TipoProva tipoProva){
      em.persist(tipoProva);
      em.flush();
      return tipoProva.getId();
   }
   
   @WebMethod
   public void eliminaTipoProva(int idTipoProva){
      TipoProva tipoProva=em.find(TipoProva.class,idTipoProva);
      
      if (tipoProva!=null) {
         em.remove(tipoProva);
         em.flush();
      }
   }
   
   //*** ASSENZA ***
   @WebMethod
   public Assenza caricaAssenza(int idAssenza){
      em.flush();
      return em.find(Assenza.class, idAssenza);
   }
   
   @WebMethod
   public int salvaAssenza(Assenza assenza){
      em.persist(assenza);
      em.flush();
      return assenza.getId();
   }
   
   @WebMethod
   public void eliminaAssenza(int idAssenza){
      Assenza assenza=em.find(Assenza.class,idAssenza);
      
      if (assenza!=null) {
         em.remove(assenza);
         em.flush();
      }
   }
   
   //*** CONTATTO ***
   @WebMethod
   public Contatto caricaContatto(int idContatto){
      em.flush();
      return em.find(Contatto.class, idContatto);
   }
   
   @WebMethod
   public int salvaContatto(Contatto contatto){
      em.persist(contatto);
      em.flush();
      return contatto.getId();
   }
   
   @WebMethod
   public void eliminaContatto(int idContatto){
      Contatto contatto=em.find(Contatto.class,idContatto);
      
      if (contatto!=null) {
         em.remove(contatto);
         em.flush();
      }
   }
   
   //*** VOTO ***
   @WebMethod
   public Voto caricaVoto(int idVoto){
      em.flush();
      return em.find(Voto.class, idVoto);
   }
   
   @WebMethod
   public int salvaVoto(Voto voto){
      em.persist(voto);
      em.flush();
      return voto.getId();
   }
   
   @WebMethod
   public void eliminaVoto(int idVoto){
      Voto voto=em.find(Voto.class,idVoto);
      
      if (voto!=null) {
         em.remove(voto);
         em.flush();
      }
   }
   
   //*** STUDENTE ***
   @WebMethod
   public Studente caricaStudente(int idStudente){
      em.flush();
      return em.find(Studente.class, idStudente);
   }
   
   @WebMethod
   public int salvaStudente(Studente studente){
      em.persist(studente);
      em.flush();
      return studente.getId();
   }
   
   @WebMethod
   public void eliminaStudente(int idStudente){
      Studente studente=em.find(Studente.class,idStudente);
      
      if (studente!=null) {
         em.remove(studente);
         em.flush();
      }
   }
   
   //*** INSEGNANTE ***
   @WebMethod
   public Docente caricaInsegnante(int idInsegnante){
      em.flush();
      return em.find(Docente.class, idInsegnante);
   }
   
   @WebMethod
   public int salvaInsegnante(Docente insegnante){
      em.persist(insegnante);
      em.flush();
      return insegnante.getId();
   }
   
   @WebMethod
   public void eliminaInsegnante(int idInsegnante){
      Docente insegnante=em.find(Docente.class,idInsegnante);
      
      if (insegnante!=null) {
         em.remove(insegnante);
         em.flush();
      }
   }
   
   //*** TIPO ASSENZA ***
   @WebMethod
   public TipoAssenza caricaTipoAssenza(int idTipoAssenza){
      em.flush();
      return em.find(TipoAssenza.class, idTipoAssenza);
   }
   
   @WebMethod
   public int salvaTipoAssenza(TipoAssenza tipoAssenza){
      em.persist(tipoAssenza);
      em.flush();
      return tipoAssenza.getId();
   }
   
   @WebMethod
   public void eliminaTipoAssenza(int idTipoAssenza){
      TipoAssenza tipoAssenza=em.find(TipoAssenza.class,idTipoAssenza);
      
      if (tipoAssenza!=null) {
         em.remove(tipoAssenza);
         em.flush();
      }
   }
   
   //*** CLASSE ***
   @WebMethod
   public Classe caricaClasse(int idClasse){
      em.flush();
      return em.find(Classe.class, idClasse);
   }
   
   @WebMethod
   public int salvaClasse(Classe classe){
      em.persist(classe);
      em.flush();
      return classe.getId();
   }
   
   @WebMethod
   public void eliminaClasse(int idClasse){
      Classe classe=em.find(Classe.class,idClasse);
      
      if (classe!=null) {
         em.remove(classe);
         em.flush();
      }
   }
   
   //*** MATERIA ***
   @WebMethod
   public MateriaInsegnamento caricaMateria(int idMateriaInsegnamento){
      em.flush();
      return em.find(MateriaInsegnamento.class, idMateriaInsegnamento);
   }
   
   @WebMethod
   public int salvaMateria(MateriaInsegnamento materiaInsegnamento){
      em.persist(materiaInsegnamento);
      em.flush();
      return materiaInsegnamento.getId();
   }
   
   @WebMethod
   public void eliminaMateria(int idMateriaInsegnamento){
      MateriaInsegnamento materiaInsegnamento=em.find(MateriaInsegnamento.class,idMateriaInsegnamento);
      
      if (materiaInsegnamento!=null) {
         em.remove(materiaInsegnamento);
         em.flush();
      }
   }
}

