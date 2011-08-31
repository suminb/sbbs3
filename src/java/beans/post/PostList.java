/*
 * PostList.java
 *
 * Created on April 9, 2006, 1:21 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans.post;

import beans.EntryList;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author superwtk
 */
public final class PostList extends EntryList {
	
	private List<Post> posts;
	
	/** Creates a new instance of PostList */
	public PostList() {
	}
	
	public PostList(List<Integer> postIds) throws PostNotFoundException, SQLException {
		for(int id : postIds) {
			posts.add(new Post(id).load());
		}
	}
}
