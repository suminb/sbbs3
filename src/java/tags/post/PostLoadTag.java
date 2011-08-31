/*
 * PostTag.java
 *
 * Created on March 13, 2006, 4:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tags.post;

import beans.post.Post;
import java.sql.SQLException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.sql.DataSource;

/**
 *
 * @author superwtk
 */
public class PostLoadTag extends TagSupport {
	
	private String id;
	private String var;
	private String scope;
	private DataSource dataSource;
	
	/** Creates a new instance of PostTag */
	public PostLoadTag() {
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}
	
	public int doEndTag() throws JspException {
		Post post = new Post(Integer.valueOf(id));
		
		try {
			post.setConnection(dataSource.getConnection());
			post.load();

		} catch(SQLException e) {
			post = null;
			e.printStackTrace();
		} catch(Exception e) {
			post = null;
			e.printStackTrace();
		}
		
		pageContext.setAttribute(var, post);
		return EVAL_PAGE;
	}
}
