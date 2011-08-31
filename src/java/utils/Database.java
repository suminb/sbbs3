/*
 * Database.java
 *
 * Created on March 17, 2006, 11:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package utils;

import beans.Entry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author superwtk
 */
public final class Database {
	
	private Connection conn = null;
	
	
	/** Creates a new instance of Database */
	public Database() {
	}
	
	public Connection connect() throws SQLException, Exception {
		Class.forName("org.gjt.mm.mysql.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://61.250.92.162/superwtk-sbbs",
				"remote_user",
				"4321qwerasdf");
	/*
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/sbbs",
				"root",
				"qwerasdf");
	 */
		return conn;
	}
	
	public void disconnect() throws SQLException {
		if(conn != null)
			conn.close();
	}
	
	public Connection getConnection() {
		return conn;
	}
	
}
