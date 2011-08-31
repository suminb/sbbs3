/*
 * QueryTest.java
 * JUnit based test
 *
 * Created on April 1, 2006, 12:20 PM
 */

package utils;

import junit.framework.*;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author superwtk
 */
public class QueryTest extends TestCase {
	
	public QueryTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite(QueryTest.class);
		
		return suite;
	}
	
	public void test() {
		Query q = new Query(Query.SELECT);
		q.setTableName("sbbs_users");
	//	q.add("user_name", "superwtk");
	//	q.add("user_signature", "Just like a challenge!");
		q.setWhere("user_id=5");
		q.setLimit(1);
		
		System.out.println(q);
	}

	public void testSetBehavior() {
	}

	public void testAddField() {
	}

	public void testAddValue() {
	}

	public void testToString() {
	}
	
}
