/*
 * ElementiTipiProve.java
 *
 * Created on 27 marzo 2007, 8.22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni.elementi;

import java.util.Vector;
import nu.mine.egoless.didattica.app.bean.TipiProveBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ElementiComboBox;

/**
 *
 * @author Admin
 */
public class ElementiTipiProve extends ElementiComboBox {
   
   TipiProveBean tipiProve;
   
   public ElementiTipiProve( TipiProveBean tipiProve, boolean modifica){
      super(new Vector(), modifica);
      this.tipiProve = tipiProve;
      inizializzaLista();
   }
   
   
   private void inizializzaLista() {
      for(int i=0; i<tipiProve.ritornaNumeroDiTipiProve (); i++ ) {
         lista.add (  (ElementoTipoProva) new ElementoTipoProva( tipiProve.getTipoProvaAt(i).getId (), 
                                                                     tipiProve.getTipoProvaAt(i).getNome () ) );
      }
   }
   
}   
