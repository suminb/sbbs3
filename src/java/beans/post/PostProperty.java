/*
 * PostExtendedElement.java
 *
 * Created on March 18, 2006, 6:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans.post;

import beans.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.Query;

/**
 *
 * @author superwtk
 */
public final class PostProperty extends EntryProperty implements Storable {
	
	private int postId;
	
	public static final String TABLE_NAME = "sbbs_post_extended";
	public static final String FIELD_POSTX_ID = "postx_id";
	public static final String FIELD_POST_ID = "post_id";
	public static final String FIELD_POSTX_KEY = "postx_key";
	public static final String FIELD_POSTX_VALUE = "postx_value";
	public static final String FIELD_POSTX_ATTRIBUTES = "postx_attributes";
	
	
	/** Creates a new instance of PostExtendedElement */
	public PostProperty() {
	}
	
	public PostProperty(String key, String value) {
		super(key, value);
	}
	
	///////////////////////////////////////////////////////
	// getters & setters
	//
	public int getPostId() {
		return postId;
	}
	
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	///////////////////////////////////////////////////////
	// Implementations of Storable
	//
	private Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public boolean isLoaded() {
		return loaded;
	}
	
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	
	
	public PostProperty load() throws SQLException {
		Query query = new Query(Query.SELECT, TABLE_NAME);
		query.setWhere(String.format("%s=%d AND %s='%s'", FIELD_POST_ID, postId, FIELD_POSTX_KEY, key));
		query.setLimit(1);
		
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query.toString());
		
		if(rs.next()) {
			setPostId(rs.getInt(FIELD_POST_ID));
			setKey(rs.getString(FIELD_POSTX_KEY));
			setValue(rs.getString(FIELD_POSTX_VALUE));
			setAttributes(rs.getInt(FIELD_POSTX_ATTRIBUTES));
			
			setLoaded(true);
			
			rs.close();
			stmt.close();
		} else {
			// throw some Exception
		}
		
		return this;
	}
	
	public void load(Connection connection) throws SQLException {
		setConnection(connection);
		load();
	}
	
	public void save() throws SQLException {
		if(isLoaded()) {
			if(value == null) {
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
		
		query.add(FIELD_POST_ID, getPostId());
		query.add(FIELD_POSTX_KEY, getKey());
		query.add(FIELD_POSTX_VALUE, getValue());
		query.add(FIELD_POSTX_ATTRIBUTES, getAttributes());
		
		Statement stmt = connection.createStatement();
		stmt.execute(query.toString());
		stmt.close();
	}
	
	public void update() throws SQLException {
		Query query = new Query(Query.UPDATE, TABLE_NAME);
		
		query.add(FIELD_POST_ID, getPostId());
		query.add(FIELD_POSTX_KEY, getKey());
		query.add(FIELD_POSTX_VALUE, getValue());
		query.add(FIELD_POSTX_ATTRIBUTES, getAttributes());
		
		query.setWhere(String.format("%s=%d AND %s='%s'", FIELD_POST_ID, postId, FIELD_POSTX_KEY, key));
		query.setLimit(1);
		
		Statement stmt = connection.createStatement();
		stmt.execute(query.toString());
		stmt.close();
	}
	
	public void delete() throws SQLException {
		Query query = new Query(Query.DELETE, TABLE_NAME);
		
		query.setWhere(String.format("%s=%d AND %s='%s'", FIELD_POST_ID, postId, FIELD_POSTX_KEY, key));
		query.setLimit(1);
		
		Statement stmt = connection.createStatement();
		stmt.execute(query.toString());
		stmt.close();
	}
	
	
	
}
