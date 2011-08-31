/*
 * Entry.java
 *
 * Created on March 17, 2006, 10:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author superwtk
 */
public abstract class Entry<E> /* implements Storable */ {
	
	///////////////////////////////////////////////////////
	// DB fields
	//
	protected int id;

	///////////////////////////////////////////////////////
	// non-DB fields
	//
	protected boolean loaded;
	
	/** Creates a new instance of Entry */
	public Entry() {
	}
	
	///////////////////////////////////////////////////////
	// getters & setters
	//
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}		

	///////////////////////////////////////////////////////
	// Implements of Storable
	//
	protected Connection connection;
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public boolean isLoaded() {
		return loaded;
	}
	
	protected void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	/*
	public abstract E load() throws Exception;
	
	public abstract void save() throws Exception;
	
	public abstract void insert() throws Exception;
	
	public abstract void update() throws Exception;

	public abstract void delete() throws Exception;
	*/
}
