/*
 * UserTest.java
 * JUnit based test
 *
 * Created on March 31, 2006, 6:47 PM
 */

package beans;

import beans.user.User;
import junit.framework.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import utils.Database;
import utils.Field;

/**
 *
 * @author superwtk
 */
public class UserTest extends TestCase {
	
	public UserTest(String testName) {
		super(testName);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite(UserTest.class);
		
		return suite;
	}
	
	public void testLoad() throws Exception {
		int n = 2;
		
		User[] user = new User[n];
	/*	
		for(int i=0; i<n; i++) {
			user[i] = new User(i+1);
			user[i].load();
			
			System.out.println(user[i].getName());
			System.out.println(user[i].getPassword());
		}*/
	}
	
	public void testQuery() throws SQLException, Exception {
		User user = new User(4);
		user.setName("user3");
		user.setPassword("123456");
		user.setGivenName("James");
		user.setTimezone(-7);
		user.setSignature("An RA of Cochise Residence Hall");
		user.setAttributes(1235);
	//	user.insert();
		user.update(User.USE_USER_NAME);
	//	user.delete();
	}
	
}
