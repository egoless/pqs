/*
 * ElementiTipiVoti.java
 *
 * Created on 22 marzo 2007, 0.44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni.elementi;

import java.util.Vector;
import nu.mine.egoless.didattica.app.bean.TipiVotiBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ElementiComboBox;

/**
 *
 * @author Admin
 */
public class ElementiTipiVoti extends ElementiComboBox {
   
   TipiVotiBean tipiVoti;
   
   public ElementiTipiVoti(TipiVotiBean tipiVoti, boolean modifica){
      super(new Vector(), modifica);
      this.tipiVoti = tipiVoti;
      inizializzaLista();
   }
   
   
   private void inizializzaLista() {
      for(int i=0; i<tipiVoti.ritornaNumeroDiTipiVoti(); i++ ) {
         lista.add (  (ElementoTipoVoto) new ElementoTipoVoto( tipiVoti.getTipoVotoAt (i).getId (), 
                                                               tipiVoti.getTipoVotoAt(i).getValore () ) );
      }
   }
   
}

