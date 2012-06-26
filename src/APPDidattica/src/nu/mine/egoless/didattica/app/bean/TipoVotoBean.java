/*
 * Nome file:TipoVotoBean.java
 * Data creazione:22 febbraio 2007, 12.08
 * Info svn: $Id: TipoVotoBean.java 777 2007-03-26 08:28:40Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.tipovotoclient.TipoVoto;
import nu.mine.egoless.didattica.ws.tipovotoclient.WSDidatticaException_Exception;
import nu.mine.egoless.didattica.ws.tipovotoclient.WSTipoVotoService;
import nu.mine.egoless.didattica.ws.tipovotoclient.WSTipoVoto;

/**
 * Questa classe si interfaccia alla parte di GUI per la gestione delle informazioni
 * relative all'oggetto TipoVoto contenuto in questa classe.
 */
public class TipoVotoBean {
   
   /**
    * Costanti usate dai metodo fireChangeProperty perche' le classi che gestiscono
    * l'intefaccia grafica riescano a discriminare quali proprieta' della classe sono
    * cambiate
    */
   public static final String NOME="nome";
   
   public static final String ORDINE="ordine";
   
   public static final String VALORE="valore";
   
   /** Campo utilizzato da proprieta' di tipo Bound*/
   private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
   
   /**
    * Tiene il valore della proprieta' tipoVoto.
    */
   private TipoVoto tipoVoto;
   
   /**
    * Crea una nuova istanza di TipoVotoBean.
    */
   public TipoVotoBean() {
      tipoVoto=new TipoVoto();
      tipoVoto.setId(Costanti.ID_NUOVO_TIPO_VOTO);
   }
   
   public TipoVotoBean(int idIn){
      tipoVoto=new TipoVoto();
      tipoVoto.setId(idIn);
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
      return this.tipoVoto.getId();
   }
   
   /**
    * Getter per la proprieta' nome.
    * @return Valore della proprieta' nome.
    */
   public String getNome() {
      return this.tipoVoto.getNome();
   }
   
   /**
    * Setter per la proprieta' nome.
    * @param nome Nuovo valore della proprieta' nome.
    */
   public void setNome(String nome) {
      String oldNome = this.tipoVoto.getNome();
      
      if((oldNome==null && nome !=null) ||
              (oldNome!=null && !oldNome.equals(nome))){
         
         this.tipoVoto.setNome(nome);
         propertyChangeSupport.firePropertyChange(NOME, oldNome, nome);
      }
   }
   
     /**
    * Getter per la proprieta' valore.
    * @return Valore della proprieta' valore.
    */
   public String getValore() {
      return this.tipoVoto.getValore();
   }
   
   /**
    * Setter per la proprieta' valore.
    * @param valore Nuovo valore della proprieta' valore.
    */
   public void setValore(String valore) {
      String oldValore = this.tipoVoto.getValore();
      
      if((oldValore==null && valore !=null) ||
              (oldValore!=null && !oldValore.equals(valore))){
         
         this.tipoVoto.setValore(valore);
         propertyChangeSupport.firePropertyChange(VALORE, oldValore, valore);
      }
   }
   
   
     /**
    * Getter per la proprieta' ordine.
    * @return Valore della proprieta' ordine.
    */
   public Integer getOrdine() {
      return this.tipoVoto.getOrdine();
   }
   
   /**
    * Setter per la proprieta' ordine.
    * @param ordine Nuovo valore della proprieta' ordine.
    */
   public void setOrdine(Integer ordine) {
      Integer oldOrdine = this.tipoVoto.getOrdine();
      
      if((oldOrdine==null && ordine !=null) ||
              (oldOrdine!=null && !oldOrdine.equals(ordine))){
         
         this.tipoVoto.setOrdine(ordine);
         propertyChangeSupport.firePropertyChange(ORDINE, oldOrdine, ordine);
      }
   }
   
   /**
    * Salva l'oggetto sul Web Service
    * @throws WSDidatticaException_Exception Lanciata quando si verifica un errore
    *         nella chiamata al Web Service.
    */
   public void salvaSuWS()throws WSDidatticaException_Exception {
      WSTipoVotoService service = new WSTipoVotoService();
      WSTipoVoto port = service.getWSTipoVotoPort();
      
//    L'oggetto e' stato creato con l'id di default. Al momento del
//    salvataggio, in modo del tutto trasparente all'utente, gli viene conferito
//    un valore consistente dato dal WS
      int nuovoId=port.aggiungiTipoVoto(this.tipoVoto);
      tipoVoto.setId(nuovoId);
   }
   
   /**
    * Metodo che cancella l'oggetto nel Web Service.
    * @throws WSDidatticaException_Exception Lanciata quando si verifica un errore
    *         nella chiamata al Web Service.
    */
   public void cancellaTipoVoto() throws WSDidatticaException_Exception {
      WSTipoVotoService service = new WSTipoVotoService();
      WSTipoVoto port = service.getWSTipoVotoPort();
      port.cancellaTipoVoto(this.tipoVoto.getId());
   }
}





