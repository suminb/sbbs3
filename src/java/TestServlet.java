/*
 * TestServlet.java
 *
 * Created on April 8, 2006, 11:02 PM
 */

import beans.post.Post;
import java.io.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

/**
 *
 * @author superwtk
 * @version
 */
public class TestServlet extends HttpServlet {
	
	/** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Context initCtx = null;
		Context envCtx = null;
		
		DataSource ds = null;
		
		Post post = null;
		
		try {
			// Obtain our environment naming context
			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup("java:comp/env");
		
			// Look up our data source
			ds = (DataSource)envCtx.lookup("jdbc/Database");
			
			out.println("DataSource:");
			out.println(ds);
		/*
			post = new Post();
			post.setConnection(ds.getConnection());
			post.setTitle("TEST");
			post.setContent("TEST_TEST");
			post.insert(Post.USE_POST_TITLE | Post.USE_POST_CONTENT);
		*/
			
			post = new Post(2);
			post.setConnection(ds.getConnection());
			post.load();
		} catch(NamingException ne) {
			ne.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		/* TODO output your page here*/
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet TestServlet (6)</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
		out.println(post.getTitle());
		out.println(post.getContent());
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
	
	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/** Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/** Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/** Returns a short description of the servlet.
	 */
	public String getServletInfo() {
		return "Short description";
	}
	// </editor-fold>
}
