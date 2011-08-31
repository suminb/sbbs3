/*
 * Inheritable.java
 *
 * Created on March 17, 2006, 11:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans;

import java.sql.SQLException;

/**
 *
 * @author superwtk
 */
public interface Inheritable<E extends Entry> {
	
	public boolean hasParent();
	
	public boolean hasChildren();

	public E getParent() throws SQLException, Exception;
	
	public E[] getChildren() throws SQLException, Exception;
	
	public int getDepth();
	
	public void setDepth(int depth);

}
