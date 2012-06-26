/*
 * ExtProva.java
 *
 * Created on 15 marzo 2007, 20.55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.prove;

/**
 *
 * @author Administrator
 */
public class ExtProva {
    
    /** Creates a new instance of ExtProva */
    public ExtProva() {
    }
    
    public javax.swing.JPanel get(){ System.out.println("ExtProva"); return new javax.swing.JPanel(); }
    
    public static void main(String args[]){
        (new ExtProva()).get();
    }
}
