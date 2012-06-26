/*
 * Nome file:TipoProvaBean.java
 * Data creazione:26 marzo 2007, 8.08
 * Info svn: $Id: TipoProvaBean.java 777 2007-03-26 08:28:40Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.tipoprovaclient.TipoProva;
import nu.mine.egoless.didattica.ws.tipoprovaclient.WSDidatticaException_Exception;
import nu.mine.egoless.didattica.ws.tipoprovaclient.WSTipoProva;
import nu.mine.egoless.didattica.ws.tipoprovaclient.WSTipoProvaService;

/**
 *
 * @author Roberto
 */
public class TipoProvaBean {
   
   /**
    * Costanti usate dai metodo fireChangeProperty perche' le classi che gestiscono
    * l'intefaccia grafica riescano a discriminare quali proprieta' della classe sono
    * cambiate
    */
   public static final String NOME="nome";
   

   /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /**
    * Tiene il valore della proprieta' tipoProva.
    */
   private TipoProva tipoProva;
   
   /**
    * Crea una nuova istanza di TipoProvaBean.
    */
   public TipoProvaBean() {
      tipoProva=new TipoProva();
      tipoProva.setId(Costanti.ID_NUOVO_TIPO_PROVA);
   }
   
   public TipoProvaBean(int idIn){
      tipoProva=new TipoProva();
      tipoProva.setId(idIn);
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
      return this.tipoProva.getId();
   }
   
   /**
    * Getter per la proprieta' nome.
    * @return Valore della proprieta' nome.
    */
   public String getNome() {
      return this.tipoProva.getNome();
   }
   
   /**
    * Setter per la proprieta' nome.
    * @param nome Nuovo valore della proprieta' nome.
    */
   public void setNome(String nome) {
      String oldNome = this.tipoProva.getNome();
      
      if((oldNome==null && nome !=null) ||
              (oldNome!=null && !oldNome.equals(nome))){
         
         this.tipoProva.setNome(nome);
         propertyChangeSupport.firePropertyChange(NOME, oldNome, nome);
      }
   }
   

   /**
    * Salva l'oggetto sul Web Service
    * @throws WSDidatticaException_Exception Lanciata quando si verifica un errore
    *         nella chiamata al Web Service.
    */
   public void salvaSuWS()throws WSDidatticaException_Exception {
      WSTipoProvaService service = new WSTipoProvaService();
      WSTipoProva port = service.getWSTipoProvaPort();
      
//    L'oggetto e' stato creato con l'id di default. Al momento del
//    salvataggio, in modo del tutto trasparente all'utente, gli viene conferito
//    un valore consistente dato dal WS
      int nuovoId=port.aggiungiTipoProva(this.tipoProva);
      tipoProva.setId(nuovoId);
   }
   
   /**
    * Metodo che cancella l'oggetto nel Web Service.
    * @throws WSDidatticaException_Exception Lanciata quando si verifica un errore
    *         nella chiamata al Web Service.
    */
   public void cancellaTipoProva() throws WSDidatticaException_Exception {
      WSTipoProvaService service = new WSTipoProvaService();
      WSTipoProva port = service.getWSTipoProvaPort();
      port.cancellaTipoProva(this.tipoProva.getId());
   }
}

