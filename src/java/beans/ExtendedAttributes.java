/*
 * ExtendedAttributes.java
 *
 * Created on May 20, 2006, 4:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans;

import java.util.TreeMap;

/**
 *
 * @author superwtk
 */
public abstract class ExtendedAttributes extends TreeMap<String, String> {
	
	/** Creates a new instance of ExtendedAttributes */
	public ExtendedAttributes() {
	}
	
	public abstract void loadAll() throws Exception;
	
	public abstract void saveAll() throws Exception;
	
}
