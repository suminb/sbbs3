/*
 * Category.java
 *
 * Created on March 17, 2006, 11:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans.category;

import beans.*;
import utils.Field;

/**
 *
 * @author superwtk
 */
public final class Category
		extends Entry<Category>
		implements Inheritable<Category> {
	
	///////////////////////////////////////////////////////
	// DB fields
	//
	private int boardId;
	private String name;
	private String description;
	private int options;
	
	///////////////////////////////////////////////////////
	// non-DB fields
	//
	
	/** Creates a new instance of Category */
	public Category() {
	}
	
	public Category getParent() {
		return null;
	}
	
	public Category[] getChildren() {
		return null;
	}

	public int getDepth() {
		return 0;
	}
	
	public void setDepth(int depth) {
	}
	
	public boolean hasParent() {
		return false;
	}
	
	public boolean hasChildren() {
		return false;
	}
	
	///////////////////////////////////////////////////////
	// implementations of Storable
	//
	public Category load() {
		return this;
	}
	
	public void save() {
		
	}
	
	public void insert() {
	}
	
	public void update() {
	}
	
	public void delete() {
	}
	
}
