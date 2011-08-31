/*
 * Storable.java
 *
 * Created on March 17, 2006, 10:54 PM
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
public interface Storable<E extends Entry> {
	
	public Connection getConnection();
	
	public void setConnection(Connection connection);
	
	public boolean isLoaded();
	
	public E load() throws Exception;
	
	public void save() throws Exception;
	
	/**
	 * Inserts the specified entry to a specific location, for example, a database.
	 * @param entry an entry to be stored.
	 */
	public void insert() throws Exception;
	
	/**
	 * Updates the specified entry if there is such an entry.
	 * @param entry an entry to be updated.
	 */
	public void update() throws Exception;
	
	/**
	 * Deletes the specified entry if there is such an entry.
	 * @param entry an entry to be removed.
	 */
	public void delete() throws Exception;
}
