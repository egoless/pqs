/*
 * ElementoMateriaInsegnamento.java
 *
 * Created on 17 marzo 2007, 23.08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni.elementi;



import nu.mine.egoless.didattica.app.bean.Costanti;
import nu.mine.egoless.didattica.app.bean.MateriaInsegnamentoBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.Listable;
import nu.mine.egoless.didattica.ws.materiaclient.MateriaInsegnamento;




public class ElementoMateriaInsegnamento extends MateriaInsegnamentoBean implements Listable<ElementoMateriaInsegnamento> {
   
   /*
   public ElementoMateriaInsegnamento(MateriaInsegnamento materiaInsegnamento){
      super(materiaInsegnamento);
   }*/
   
   public ElementoMateriaInsegnamento(int id, String stringa) {
       super(id);
       this.setNome (stringa);
   }
   
   public String toString() {
      return getNome();
   }
   
   public ElementoMateriaInsegnamento fromString(String stringa) {
       return new ElementoMateriaInsegnamento(Costanti.ID_NUOVA_MATERIA_INSEGNAMENTO, stringa);
   }
   
}

