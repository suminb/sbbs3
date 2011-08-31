/*
 * ExtendedElement.java
 *
 * Created on March 18, 2006, 6:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans;

import java.sql.Connection;

/**
 *
 * @author superwtk
 */
public abstract class EntryProperty extends Entry<EntryProperty> {
	
	///////////////////////////////////////////////////////
	// DB fields
	//
	protected int id;
	protected String key;
	protected String value;
	protected int attributes;
	
	///////////////////////////////////////////////////////
	// non-DB fields
	//
	
	
	/** Creates a new instance of ExtendedElement */
	public EntryProperty() {
	}
	
	public EntryProperty(String key, String value) {
		setKey(key);
		setValue(value);
	}
	
	///////////////////////////////////////////////////////
	// getters & setters
	//
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}	

	public int getAttributes() {
		return attributes;
	}

	public void setAttributes(int attributes) {
		this.attributes = attributes;
	}

	
}
