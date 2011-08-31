/*
 * EntryTest.java
 * JUnit based test
 *
 * Created on March 18, 2006, 1:41 AM
 */

package beans;

import junit.framework.*;
import utils.Database;

/**
 *
 * @author superwtk
 */
public class EntryTest extends TestCase {
	
	public EntryTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite(EntryTest.class);
		
		return suite;
	}

	public void testOverall() {
		try {
			Database db = new Database();

			/*
			Config config = new Config();
			config.setConnection(db.connect());
			config.setKey("evaluation");
			config.load();
			
			System.out.printf("%s: %s", config.getKey(), config.getValue());
			System.out.println();
			
			config.setValue(null);
			config.save();
			*/
			
			Post post = new Post();
			post.setConnection(db.connect());
			post.setTitle("This is a post");
			post.setContent("Just like a challenge!");
			post.insert(Post.USE_POST_TITLE | Post.USE_POST_CONTENT);
		
			db.disconnect();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
		}
	}
	
}
