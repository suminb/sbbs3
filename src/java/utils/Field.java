/*
 * Field.java
 *
 * Created on March 18, 2006, 5:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package utils;

/**
 *
 * @author superwtk
 */
public final class Field<E> {
	
	private String name;
	private int type; // java.sql.Types
	private E data;
	
	/** Creates a new instance of Field */
	public Field() {
	}
	
	public Field(String name, int type) {
		setName(name);
		setType(type);
	}
	
	public Field(String name, int type, E data) {
		setName(name);
		setType(type);
		setData(data);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
	
}
