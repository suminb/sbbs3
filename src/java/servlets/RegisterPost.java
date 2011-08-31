/*
 * RegisterPost.java
 *
 * Created on April 15, 2006, 4:31 PM
 */

package servlets;

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
public class RegisterPost extends HttpServlet {
	
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
		DataSource dataSource = null;
		
		Post post = null;
		
		try {
			initCtx = new InitialContext();
			envCtx = (Context)initCtx.lookup("java:comp/env");
			dataSource = (DataSource)envCtx.lookup("jdbc/Database");
			
			post = new Post();
			post.setConnection(dataSource.getConnection());
			
			post.setTitle(request.getParameter("post_title"));
			post.setContent(request.getParameter("post_content"));
			
			post.save();
			
		} catch(NamingException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		/* TODO output your page here */
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet RegisterPost</title>");
		out.println("</head>");
		out.printf("params: %s, %s\n", request.getParameter("post_title"), request.getParameter("post_content"));
		out.println("<body>");
		out.println("<h1>Servlet RegisterPost at " + request.getContextPath () + "</h1>");
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
