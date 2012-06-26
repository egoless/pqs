/*
 * ElementoClasse.java
 *
 * Created on 17 marzo 2007, 21.34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni.elementi;


import java.util.Vector;
import nu.mine.egoless.didattica.app.bean.ClasseBean;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.bean.Costanti;
import nu.mine.egoless.didattica.app.gui.componentiComuni.Listable;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ElementiComboBox;
import nu.mine.egoless.didattica.ws.classesupport.Classe;



public class ElementoClasse extends ClasseBean implements Listable<ElementoClasse> {
   
   
   public ElementoClasse(Classe classe){
      super(classe);
   }
   
   public ElementoClasse(int id, String stringa){
       super();
       setAnnoCorso( new Integer(stringa.charAt(0)-'0').intValue () );
       setSezione(stringa.charAt(1) );
       //classe.setIndirizzoStudi( stringa.substring(3) );
       
       System.out.println(getAnnoCorso() + 10);
   }
   
   public String toString(){
      Integer anno = new Integer(this.getAnnoCorso ());
      String sezione = new String(""+(char)(this.getSezione ()) ).toUpperCase ();
      return anno.toString () + sezione; //+ " "+ this.getIndirizzoStudi ();
   }
   
   public ElementoClasse fromString(String stringa) {
       return new ElementoClasse(Costanti.ID_NUOVA_CLASSE, stringa);
   }
   
   
   public boolean equals(Object elem) {
      return (elem instanceof ElementoClasse && 
               this.getSezione() == ((ElementoClasse)elem).getSezione() &&
               this.getAnnoCorso() == ((ElementoClasse)elem).getAnnoCorso()  &&
               this.getIndirizzoStudi()==((ElementoClasse)elem).getIndirizzoStudi()
               );
   }
}

