/*
 * BeanProxy.java
 *
 * Created on 8 marzo 2007, 12.09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.prove;

/***
 *
 * @author Admin
 */
public class BeanProxy {
   
   /** Creates a new instance of BeanProxy */
   public BeanProxy (Object bean) {
         beanCorrente = beanPrecedente = bean;
   }

   private Object beanCorrente = null;

   private Object beanPrecedente = null;

   public Object getBean () {
         return beanCorrente;
   }

   public void ricaricaBean () {
         beanCorrente = beanPrecedente;
   }
   
   
   
   
   
}
