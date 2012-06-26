/*
 * ElementoTipoAssenza.java
 *
 * Created on 18 marzo 2007, 21.52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni.elementi;

import nu.mine.egoless.didattica.app.bean.Costanti;
import nu.mine.egoless.didattica.app.bean.TipoAssenzaBean;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.TipoAssenza;
import nu.mine.egoless.didattica.app.gui.componentiComuni.Listable;



/**
 *
 * @author Alberto
 */
public class ElementoTipoAssenza  extends TipoAssenzaBean implements Listable<ElementoTipoAssenza> {
   
   public ElementoTipoAssenza(int id){
       super(id);
   }
   
   public ElementoTipoAssenza(int id, String stringa){
       super(id);
       setDescrizione (stringa );
   }
   
   public String toString(){
      return getDescrizione();
   }
   
   public ElementoTipoAssenza fromString(String stringa) {
       return new ElementoTipoAssenza(Costanti.ID_NUOVO_TIPO_ASSENZA, stringa);
   }
   
}