
/*
 * Nome file:MateriaInsegnamentoBean.java
 * Data creazione:22 febbraio 2007, 12.19
 * Info svn: $Id: MateriaInsegnamentoBean.java 874 2007-03-27 14:18:23Z alberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.materiaclient.MateriaInsegnamento;
import nu.mine.egoless.didattica.ws.materiaclient.WSMateria;
import nu.mine.egoless.didattica.ws.materiaclient.WSMateriaService;
import nu.mine.egoless.didattica.ws.materiaclient.WSDidatticaException_Exception;
import java.net.PortUnreachableException;


/** Classe che contiene l'oggetto MateriaInsegnamento */
public class MateriaInsegnamentoBean {
   
   /**
    * Costanti usate dai metodo fireChangeProperty perche' le classi che gestiscono
    * l'intefaccia grafica riescano a discriminare quali proprieta' della classe sono
    * cambiate
    */
   public static final String EXTRA="extra";
   
   public static final String NOME="nome";
   
   /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /**
    * Tiene il valore della proprietà materiaInsegnamento.
    */
   private MateriaInsegnamento materiaInsegnamento;
   
   
   /**
    * Crea una nuova istanza di MateriaInsegnamentoBean
    */
   public MateriaInsegnamentoBean() {
      materiaInsegnamento=new MateriaInsegnamento();
      materiaInsegnamento.setId(Costanti.ID_NUOVA_MATERIA_INSEGNAMENTO);
   }
   
   public MateriaInsegnamentoBean(int idIn) {
      materiaInsegnamento=new MateriaInsegnamento();
      materiaInsegnamento.setId(idIn);
   }
   
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
      return this.materiaInsegnamento.getId();
   }
   
   /**
    * Getter per la proprietà nome.
    * @return Valore della proprietà nome.
    */
   public String getNome() {
      String t = this.materiaInsegnamento.getNome();
      if( t == null ) t="";
      return t;      
   }
   
   /**
    * Setter per la proprietà nome.
    * @param nome Nuovo valore della proprietà nome.
    */
   public void setNome(String nome) {
      String oldNome = this.materiaInsegnamento.getNome();
      if(oldNome==null && nome==null) return;
      if((oldNome==null && nome!=null) || !oldNome.equals(nome)){
         this.materiaInsegnamento.setNome(nome);
         propertyChangeSupport.firePropertyChange(NOME, oldNome, nome);
      }
   }
   
   
   
  /*
   
   N.B NON C'E' IL METODO DI CARICA DA WS:
   NON SERVE PERCHE' RECUPERO TUTTE LE MATERIE E POI MI CREO IL BEAN DA QUELLE
   
   */
   /** Salva l'oggetto sul Web Service  */
   
   public void salvaSuWS()throws WSDidatticaException_Exception {
      
      WSMateriaService service = new WSMateriaService();
      WSMateria port = service.getWSMateriaPort();
      int nuovoId=port.aggiungiMateria(materiaInsegnamento);
      materiaInsegnamento.setId(nuovoId);
      
   }
   
   
   /**
    * Metodo che cancella l'oggetto nel Web Service.
    * @throws WSDidatticaException_Exception Lanciata quando si verifica un errore
    *         nella chiamata al Web Service.
    */
   public void cancellaMateriaInsegnamento() throws WSDidatticaException_Exception {
      WSMateriaService service = new WSMateriaService();
      WSMateria port = service.getWSMateriaPort();
      port.cancellaMateria(this.materiaInsegnamento.getId());
   }
   
   
   
   
}
