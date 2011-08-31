/*
 * Board.java
 *
 * Created on March 17, 2006, 11:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans.board;

import beans.*;
import java.sql.Types;
import utils.Field;

/**
 *
 * @author superwtk
 */
public class Board
		extends Entry<Board>
		implements Accessible, Extendable
{
	
	///////////////////////////////////////////////////////
	// DB fields
	//
	private String name;
	private String description;
	private String templateName;
	private int postCount;
	private int options;
	
	///////////////////////////////////////////////////////
	// non-DB fields
	//
	
	/** Creates a new instance of Board */
	public Board() {
	}
	
	///////////////////////////////////////////////////////
	// Implementations of Extendable
	//
	public ExtendedAttributes extended() {
		return null;
	}
	
	///////////////////////////////////////////////////////
	// implementations Storable
	//
	public Board load() throws Exception {
		return this;
	}

	public void save() throws Exception {
	}

	public void insert() throws Exception {
	}

	public void update() throws Exception {
	}

	public void delete() throws Exception {
	}
	
}
