/*
 * Nome file:ClasseBean.java
 * Data creazione:22 febbraio 2007, 11.04
 * Info svn: $Id: ClasseBean.java 783 2007-03-26 10:07:00Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.classesupport.Classe;
import nu.mine.egoless.didattica.ws.classesupport.WSClasse;
import nu.mine.egoless.didattica.ws.classesupport.WSClasseService;
import nu.mine.egoless.didattica.ws.classesupport.WSDidatticaException_Exception;
import java.lang.UnsupportedOperationException;
import java.util.List;
import java.util.Date;


public class ClasseBean {
   
   public static final String ANNO_CORSO="annoCorso";
   
   public static final String ANNO_SCOLASTICO="idAnnoScolastico";
   
   public static final String DOCENTE="idDocente";
   
   public static final String SEZIONE="sezione";
   
   public static final String INDIRIZZO_STUDI="indirizzoStudi";
   
   /**
    * Tiene il valore della proprieta' classe.
    */
   private Classe classe;
   
   
   /** Crea una nuova istanza di ClassBean */
   public ClasseBean() {
      classe=new Classe();
   }
   
   /**
    * Crea una nuova istanza di ClassBean partendo da un oggetto di tipo classe
    * @param classeIn oggetto di tipo classe in ingresso
    */
   public ClasseBean(Classe classeIn){
      classe=classeIn;
   }
   
   /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /**
    * Aggiunge un {@link PropertyChangeListener} alla lista dei listener che ascoltano
    * i cambiamenti di una data proprieta'.
    * @param nomeProprieta Proprieta' di cui il listener vuole sapere i cambiamenti.
    * @param l Il listener da aggiungere.
    */
   public void addPropertyChangeListener(String nomeProprieta,PropertyChangeListener l) {
      propertyChangeSupport.addPropertyChangeListener(nomeProprieta, l);
   }
   
   /**
    * Rimuove un {@link PropertyChangeListener} alla lista dei listener che ascoltano i cambiamenti
    * di una data proprieta'
    * @param nomeProprieta La proprieta' a cui il listener e' agganciato.
    * @param l Il listener da rimuovere.
    */
   public void removePropertyChangeListener(String nomeProprieta, PropertyChangeListener l) {
      propertyChangeSupport.removePropertyChangeListener(nomeProprieta, l);
   }
   
   /**
    * Getter per la proprieta' id.
    * @return Valore della proprieta' id.
    */
   public int getId() {
      return this.classe.getId();
   }
   
   /**
    * Getter per la proprieta' annoCorso.
    * @return Valore della proprieta' annoCorso.
    */
   public int getAnnoCorso() {
      return this.classe.getAnnoCorso().intValue();
   }
   
   /**
    * Setter per la proprieta' annoCorso.
    * @param annoCorso Nuovo valore della proprieta' annoCorso.
    */
   public void setAnnoCorso(int annoCorso) {
      int oldAnnoCorso=0;
      if (this.classe.getAnnoCorso()!=null) oldAnnoCorso = this.classe.getAnnoCorso().intValue();
      if(annoCorso!=oldAnnoCorso){
         this.classe.setAnnoCorso(annoCorso);
         propertyChangeSupport.firePropertyChange(ANNO_CORSO, new Integer(oldAnnoCorso), new Integer(annoCorso));
      }
   }
   
   /**
    *Setter per la proprieta idIndirizzoStudi
    *@param idIndirizzoStudi Nuovo valore per la proprieta idIndirizzoStudi
    */
   public void setIndirizzoStudi(int idIndirizzoStudi){
      int oldIndirizzoStudi=this.classe.getIndirizzoStudiId();
      if(oldIndirizzoStudi!=idIndirizzoStudi){
         this.classe.setIndirizzoStudiId(idIndirizzoStudi);
         propertyChangeSupport.firePropertyChange(INDIRIZZO_STUDI, oldIndirizzoStudi , idIndirizzoStudi);
      }
   }
   
   public int getIndirizzoStudi(){
      return this.classe.getIndirizzoStudiId();
   }
   
      /**
    *Setter per la proprieta idDocente
    *@param idDocente Nuovo valore per la proprieta idDocente
    */
   public void setDocente(int idDocente){
      int oldDocente=this.classe.getDocenteId();
      if(oldDocente!=idDocente){
         this.classe.setDocenteId(idDocente);
         propertyChangeSupport.firePropertyChange(DOCENTE, oldDocente , idDocente);
      }
   }
   
   public int getDocente(){
      return this.classe.getDocenteId();
   }
   
      /**
    *Setter per la proprieta idAnnoScolastico
    *@param idAnnoScolastico Nuovo valore per la proprieta idAnnoScolastico
    */
   public void setAnnoScolastico(int idAnnoScolastico){
      int oldAnnoScolastico=this.classe.getAnnoScolasticoId();
      if(oldAnnoScolastico!=idAnnoScolastico){
         this.classe.setAnnoScolasticoId(idAnnoScolastico);
         propertyChangeSupport.firePropertyChange(ANNO_SCOLASTICO, oldAnnoScolastico , idAnnoScolastico);
      }
   }
   
   public int getAnnoScolastico(){
      return this.classe.getAnnoScolasticoId();
   }
   
   /**
    * Getter per la proprieta' sezione.
    * @return Valore della proprieta' sezione.
    */
   public char getSezione() {
      return (char) this.classe.getSezione();
   }
   
   /**
    * Setter per la proprieta' sezione.
    * @param sezione Nuovo valore della proprieta' sezione.
    */
   public void setSezione(char sezione) {
      char oldSezione = (char) this.classe.getSezione();
      if(sezione!=oldSezione){
         this.classe.setSezione(sezione);
         propertyChangeSupport.firePropertyChange(SEZIONE, oldSezione, sezione);
      }
   }
   
   

   /** Salva l'oggetto sul Web Service  */
   public void salvaSuWS()throws WSDidatticaException_Exception{
      WSClasseService service = new WSClasseService();
      WSClasse port = service.getWSClassePort();
      // TODO initialize WS operation arguments here
      int nuovoId=port.aggiungiClasse(classe);
      classe.setId(nuovoId);
      // TODO handle custom exceptions here
   }
   
   public void cancellaClasse() throws WSDidatticaException_Exception{
      
      WSClasseService service = new WSClasseService();
      WSClasse port = service.getWSClassePort();
      // TODO initialize WS operation arguments here
      port.cancellaClasse(this.classe.getId());
      
   }
   
   
}
