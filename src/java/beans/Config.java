/*
 * Config.java
 *
 * Created on April 9, 2006, 1:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.Query;

/**
 *
 * @author superwtk
 */
public final class Config extends Entry<Config> implements Storable {
	
	private String key;
	private String value;
	
	private static final String TABLE_NAME = "sbbs_config";
	public static final String FIELD_CONFIG_KEY = "config_key";
	public static final String FIELD_CONFIG_VALUE = "config_value";
	
	private Connection connection;
	private boolean loaded;
	
	/** Creates a new instance of Config */
	public Config() {
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
	
	///////////////////////////////////////////////////////
	// Implementations of Storable
	//
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public boolean isLoaded() {
		return loaded;
	}
	
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	
	public Config load() throws SQLException {
		Query query = new Query(Query.SELECT, TABLE_NAME);
		query.setWhere(String.format("%s='%s'", FIELD_CONFIG_KEY, getKey()));
		query.setLimit(1);
		
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query.toString());
		
		if(rs.next()) {
			setKey(rs.getString(FIELD_CONFIG_KEY));
			setValue(rs.getString(FIELD_CONFIG_VALUE));
			
			setLoaded(true);
			
			rs.close();
			stmt.close();
		} else {
			
		}
		
		return this;
	}
	
	public void save() throws SQLException {
		if(isLoaded()) {
			if(getValue() == null) {
				delete();
			} else {
				update();
			}
		} else {
			insert();
		}
	}
	
	public void insert() throws SQLException {
		Query query = new Query(Query.INSERT, TABLE_NAME);
		query.add(FIELD_CONFIG_KEY, getKey());
		query.add(FIELD_CONFIG_VALUE, getValue());
		
		Statement stmt = getConnection().createStatement();
		stmt.execute(query.toString());
		stmt.close();
	}
	
	public void update() throws SQLException {
		Query query = new Query(Query.UPDATE, TABLE_NAME);
		query.add(FIELD_CONFIG_VALUE, getValue());
		query.setWhere(String.format("%s='%s'", FIELD_CONFIG_KEY, getKey()));
		query.setLimit(1);
		
		Statement stmt = getConnection().createStatement();
		stmt.execute(query.toString());
		stmt.close();
	}
	
	public void delete() throws SQLException {
		Query query = new Query(Query.DELETE, TABLE_NAME);
		query.setWhere(String.format("%s='%s'", FIELD_CONFIG_KEY, getKey()));
		query.setLimit(1);
		
		Statement stmt = getConnection().createStatement();
		stmt.execute(query.toString());
		stmt.close();
	}
	
	
}
