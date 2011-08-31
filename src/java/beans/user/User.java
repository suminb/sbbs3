/*
 * User.java
 *
 * Created on March 17, 2006, 11:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans.user;

import beans.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import utils.Database;
import utils.Query;

/**
 *
 * @author superwtk
 */
public final class User
		extends Entry<User>
		implements Accessible, Extendable {
	
	///////////////////////////////////////////////////////
	// DB fields
	//
	private String name;
	private String password;
	private int groupCount;
	
	private String email;
	private String familyName;
	private String givenName;
	private String middleName;
	private String nickName;
	private int timezone;
	private String signature;
	
	private int attributes;
	
	public static final int USE_USER_ID =				0x00000001;
	public static final int USE_USER_NAME =				0x00000002;
	public static final int USE_USER_PASSWORD =			0x00000004;
	public static final int USE_USER_FAMILY_NAME =		0x00000010;
	public static final int USE_USER_GIVEN_NAME =		0x00000020;
	public static final int USE_USER_MIDDLE_NAME =		0x00000040;
	public static final int USE_USER_NICK_NAME =		0x00000080;
	public static final int USE_USER_EMAIL =			0x00000100;
	public static final int USE_USER_TIMEZONE =			0x00000200;
	public static final int USE_USER_SIGNATURE =		0x00000400;
	public static final int USE_USER_ATTRIBUTES =		0x80000000;
	
	private static final String TABLE_NAME = "sbbs_users";
	public static final String FIELD_USER_ID = "user_id";
	public static final String FIELD_USER_NAME = "user_name";
	public static final String FIELD_USER_PASSWORD = "user_password";
	public static final String FIELD_USER_FAMILY_NAME = "user_family_name";
	public static final String FIELD_USER_GIVEN_NAME = "user_given_name";
	public static final String FIELD_USER_MIDDLE_NAME = "user_middle_name";
	public static final String FIELD_USER_NICK_NAME = "user_nick_name";
	public static final String FIELD_USER_EMAIL = "user_email";
	public static final String FIELD_USER_TIMEZONE = "user_timezone";
	public static final String FIELD_USER_SIGNATURE = "user_signature";
	public static final String FIELD_USER_ATTRIBUTES = "user_attributes";
	
	///////////////////////////////////////////////////////
	// non-DB fields
	//
	private Connection conn;
	private List<UserProperty> extendedElements;
	
	/**
	 * Creates a new instance of User
	 */
	public User() {
		extendedElements = new Vector<UserProperty>();
	}
	
	public User(int id) {
		this();
		setId(id);
	}
	
	///////////////////////////////////////////////////////
	// getters & setters
	//
	/**
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String username) {
		this.name = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getGroupCount() {
		return groupCount;
	}
	
	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getGivenName() {
		return givenName;
	}
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public int getTimezone() {
		return timezone;
	}
	
	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public int getAttributes() {
		return attributes;
	}
	
	public void setAttributes(int attributes) {
		this.attributes = attributes;
	}
	
	///////////////////////////////////////////////////////
	// Implements Extendable
	//
	public ExtendedAttributes extended() {
		return null;
	}
	
	///////////////////////////////////////////////////////
	// Implementations of Storable
	//

	/**
	 * Loads the specified user
	 * @throws java.sql.SQLException
	 * @throws java.lang.Exception
	 */
	public User load() throws SQLException, Exception {
		
		Query query = new Query(Query.SELECT, TABLE_NAME);
		query.setWhere(String.format("%s=%d", FIELD_USER_ID, getId()));
		query.setLimit(1);
		
		Statement stmt = getConnection().createStatement();		
		ResultSet rs = stmt.executeQuery(query.toString());
		
		if(rs.next()) {
			setName(rs.getString(FIELD_USER_NAME));
			setPassword(rs.getString(FIELD_USER_PASSWORD));
			setEmail(rs.getString(FIELD_USER_EMAIL));
			
			setFamilyName(rs.getString(FIELD_USER_FAMILY_NAME));
			setGivenName(rs.getString(FIELD_USER_GIVEN_NAME));
			setMiddleName(rs.getString(FIELD_USER_MIDDLE_NAME));
			setNickName(rs.getString(FIELD_USER_NICK_NAME));
			setSignature(rs.getString(FIELD_USER_SIGNATURE));
			
			setAttributes(rs.getInt(FIELD_USER_ATTRIBUTES));
			
			setLoaded(true);
			rs.close();
			stmt.close();
		} else {
			throw new Exception("There is no such a user");
		}
		
		return this;
	}
	
	/**
	 *
	 * @param behavior
	 * @throws java.sql.SQLException
	 * @throws java.lang.Exception
	 */
	public void save() throws SQLException, Exception {
		if(isLoaded()) {
			if(getName() == null) {
				delete();
			} else {
				update();
			}
		} else {
			insert();
		}
	}
	
	/**
	 *
	 * @throws java.sql.SQLException
	 * @throws java.lang.Exception
	 */
	public void insert() throws SQLException, Exception {
		insert(0xFFFFFFFF);
	}
	
	/**
	 *
	 * @param field
	 * @throws java.sql.SQLException
	 * @throws java.lang.Exception
	 */
	public void insert(int fields) throws SQLException, Exception {
		
		if(fields == 0)
			throw new Exception("Nothing to insert");
		
		Query query = new Query(Query.INSERT, TABLE_NAME);
		
		if((fields & USE_USER_ID) == USE_USER_ID) {
			query.add(FIELD_USER_ID, getId());
		}
		if((fields & USE_USER_NAME) == USE_USER_NAME) {
			query.add(FIELD_USER_NAME, getName());
		}
		if((fields & USE_USER_PASSWORD) == USE_USER_PASSWORD) {
			query.add(FIELD_USER_PASSWORD, getPassword());
		}
		if((fields & USE_USER_FAMILY_NAME) == USE_USER_FAMILY_NAME) {
			query.add(FIELD_USER_FAMILY_NAME, getFamilyName());
		}
		if((fields & USE_USER_GIVEN_NAME) == USE_USER_GIVEN_NAME) {
			query.add(FIELD_USER_GIVEN_NAME, getGivenName());
		}
		if((fields & USE_USER_MIDDLE_NAME) == USE_USER_MIDDLE_NAME) {
			query.add(FIELD_USER_MIDDLE_NAME, getMiddleName());
		}
		if((fields & USE_USER_NICK_NAME) == USE_USER_NICK_NAME) {
			query.add(FIELD_USER_NICK_NAME, getNickName());
		}
		if((fields & USE_USER_EMAIL) == USE_USER_EMAIL) {
			query.add(FIELD_USER_EMAIL, getEmail());
		}
		if((fields & USE_USER_TIMEZONE) == USE_USER_TIMEZONE) {
			query.add(FIELD_USER_TIMEZONE, getTimezone());
		}
		if((fields & USE_USER_SIGNATURE) == USE_USER_SIGNATURE) {
			query.add(FIELD_USER_SIGNATURE, getSignature());
		}
		if((fields & USE_USER_ATTRIBUTES) == USE_USER_ATTRIBUTES) {
			query.add(FIELD_USER_ATTRIBUTES, getAttributes());
		}
		
		Statement stmt = getConnection().createStatement();
		
		// DEBUG
		System.out.println(query);
		
		stmt.execute(query.toString());
		stmt.close();
	}
	
	/**
	 * Updates all fields
	 * @throws java.sql.SQLException
	 * @throws java.lang.Exception
	 */
	public void update() throws SQLException, Exception {
		update(0xFFFFFFFF);
	}
	
	/**
	 * Updates the specified fields
	 * @param field
	 * @throws java.sql.SQLException
	 * @throws java.lang.Exception
	 */
	public void update(int fields) throws SQLException, Exception {
		
		if(fields == 0)
			throw new Exception("Nothing to update");
		
		Query query = new Query(Query.UPDATE, TABLE_NAME);
		
		if((fields & USE_USER_ID) == USE_USER_ID) {
			query.add(FIELD_USER_ID, getId());
		}
		if((fields & USE_USER_NAME) == USE_USER_NAME) {
			query.add(FIELD_USER_NAME, getName());
		}
		if((fields & USE_USER_PASSWORD) == USE_USER_PASSWORD) {
			query.add(FIELD_USER_PASSWORD, getPassword());
		}
		if((fields & USE_USER_FAMILY_NAME) == USE_USER_FAMILY_NAME) {
			query.add(FIELD_USER_FAMILY_NAME, getFamilyName());
		}
		if((fields & USE_USER_GIVEN_NAME) == USE_USER_GIVEN_NAME) {
			query.add(FIELD_USER_GIVEN_NAME, getGivenName());
		}
		if((fields & USE_USER_MIDDLE_NAME) == USE_USER_MIDDLE_NAME) {
			query.add(FIELD_USER_MIDDLE_NAME, getMiddleName());
		}
		if((fields & USE_USER_NICK_NAME) == USE_USER_NICK_NAME) {
			query.add(FIELD_USER_NICK_NAME, getNickName());
		}
		if((fields & USE_USER_EMAIL) == USE_USER_EMAIL) {
			query.add(FIELD_USER_EMAIL, getEmail());
		}
		if((fields & USE_USER_TIMEZONE) == USE_USER_TIMEZONE) {
			query.add(FIELD_USER_TIMEZONE, getTimezone());
		}
		if((fields & USE_USER_SIGNATURE) == USE_USER_SIGNATURE) {
			query.add(FIELD_USER_SIGNATURE, getSignature());
		}
		if((fields & USE_USER_ATTRIBUTES) == USE_USER_ATTRIBUTES) {
			query.add(FIELD_USER_ATTRIBUTES, getAttributes());
		}
		
		query.setWhere(String.format("%s=%d", FIELD_USER_ID, id));
		query.setLimit(1);
		
		Statement stmt = getConnection().createStatement();
		
		// DEBUG
		System.out.println(query);
		
		stmt.execute(query.toString());
		stmt.close();
	}
	
	/**
	 *
	 * @throws java.sql.SQLException
	 * @throws java.lang.Exception
	 */
	public void delete() throws SQLException, Exception {
	
		Query query = new Query(Query.DELETE, TABLE_NAME);
		query.setWhere(String.format("%s=%d", FIELD_USER_ID, id));
		query.setLimit(1);
		
		Statement stmt = getConnection().createStatement();		
		stmt.execute(query.toString());
		stmt.close();
	}
}
