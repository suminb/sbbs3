/*
 * EntryList.java
 *
 * Created on April 8, 2006, 6:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans;

/**
 *
 * @author superwtk
 */
public class EntryList<E extends Entry> implements Enumeratable {
	
	Object[] entries;
	
	/** Creates a new instance of EntryList */
	public EntryList() {
	//	entries = new Object[1];
	}

}
