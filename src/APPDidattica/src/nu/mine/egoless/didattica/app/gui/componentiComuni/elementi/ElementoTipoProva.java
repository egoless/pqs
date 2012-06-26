/*
 * ElementoTipoProva.java
 *
 * Created on 27 marzo 2007, 8.22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni.elementi;

import nu.mine.egoless.didattica.app.bean.Costanti;
import nu.mine.egoless.didattica.app.bean.TipoProvaBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.Listable;

/**
 *
 * @author Admin
 */
public class ElementoTipoProva extends TipoProvaBean implements Listable<ElementoTipoProva> {
   
   
   public ElementoTipoProva(int idTipoVoto){
      super(idTipoVoto);
   }
   
   public ElementoTipoProva(int idTipoVoto, String stringa){
       super(idTipoVoto);
       this.setNome ( stringa );
   }
   
   public String toString(){
      return getNome ();
   }
   
   public ElementoTipoProva fromString(String stringa) {
       return new ElementoTipoProva(Costanti.ID_NUOVO_TIPO_PROVA, stringa);
   }
    
   public boolean equals(Object o) {
      return ( o instanceof TipoProvaBean && ((TipoProvaBean)o).getId () == this.getId () );
   }
}
