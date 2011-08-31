/*
 * ExtendedElements.java
 *
 * Created on March 15, 2006, 12:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author superwtk
 * @deprecated
 */
public class ExtendedElements extends DBConnection {

	protected String tableName;
	

	/**
	 * @param key
	 * @throws java.sql.SQLException 
	 * @throws java.lang.Exception 
	 * @return
	 */
	public String getElement(String key) throws SQLException, Exception {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM `");
		query.append(tableName);
		query.append("` WHERE `key`=? LIMIT 1");
		
		PreparedStatement stmt = conn.prepareStatement(new String(query));
		stmt.setString(1, key);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next() == false) {
			throw new Exception("There is no such element");
		}
		else {
			return rs.getString("value");
		}
	}
	
	/**
	 * 
	 * @param key 
	 * @param value 
	 */
	public void setElement(String key, String value) {
		
	}
}
