/*
 * Win.java
 *
 * Created on 2 marzo 2007, 17.36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.prove;


import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class Win extends JWindow{
    
    /** Creates a new instance of Win */
    public Win() {
        add(new JTextField("ciaociao"));
	this.setSize(100,100);
    }
    public static void main(String args[]){
	new Win().setVisible(true);
    }    
}

