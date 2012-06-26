/*
 * ElementoTipoVoto.java
 *
 * Created on 22 marzo 2007, 0.44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni.elementi;

import nu.mine.egoless.didattica.app.bean.Costanti;
import nu.mine.egoless.didattica.app.bean.TipoVotoBean;
import nu.mine.egoless.didattica.ws.tipovotoclient.TipoVoto;
import nu.mine.egoless.didattica.app.gui.componentiComuni.Listable;

/**
 *
 * @author Alberto Meneghello
 */
public class ElementoTipoVoto extends TipoVotoBean implements Listable<ElementoTipoVoto> {
   
   
   public ElementoTipoVoto(int idTipoVoto){
      super(idTipoVoto);
   }
   
   public ElementoTipoVoto(int idTipoVoto, String stringa){
       super(idTipoVoto);
       this.setValore ( stringa );
   }
   
   public String toString(){
      return getValore();
   }
   
   public ElementoTipoVoto fromString(String stringa) {
       return new ElementoTipoVoto(Costanti.ID_NUOVO_TIPO_VOTO, stringa);
   }
   
   public boolean equals(Object o) {
      return ( o instanceof TipoVotoBean && ((TipoVotoBean)o).getId () == this.getId () );
   }
}
