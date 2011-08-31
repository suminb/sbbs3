/*
 * PostNotFoundException.java
 *
 * Created on April 9, 2006, 1:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans.post;

/**
 *
 * @author superwtk
 */
public class PostNotFoundException extends java.lang.Exception {
	
	/**
	 * Creates a new instance of <code>PostNotFoundException</code> without detail message.
	 */
	public PostNotFoundException() {
	}
	
	
	/**
	 * Constructs an instance of <code>PostNotFoundException</code> with the specified detail message.
	 * @param msg the detail message.
	 */
	public PostNotFoundException(String msg) {
		super(msg);
	}
}
