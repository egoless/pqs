/*
 * ElementiClasse.java
 *
 * Created on 18 marzo 2007, 0.17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni.elementi;

import java.util.Vector;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ElementiComboBox;





/**
 *
 * @author Admin
 */
public class ElementiClasse extends ElementiComboBox {
   
   ClassiBean classi;
   
   public ElementiClasse(ClassiBean classi, boolean modifica){
      super(new Vector(), modifica);
      this.classi = classi;
      inizializzaLista();
   }
   
   
   private void inizializzaLista() {
      for(int i=0; i<classi.ritornaNumeroDiClassi(); i++ )
         lista.add (  (ElementoClasse) new ElementoClasse( classi.getClasseAt(i) ) );
   }
   
}   
