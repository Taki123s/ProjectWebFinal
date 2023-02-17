package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import database.blog;
import model.Account;
import model.ListBlog;


@WebServlet("/anime-main/commentBlog")
public class commentBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String type = request.getParameter("type");
			Account user = (Account) session.getAttribute("user");
			
			if(user!=null) {
				int idBlog = Integer.parseInt(request.getParameter("idblog"));
				blog DBAOBlog = new blog();
				String url ="gotoblog?"+"idBlog="+idBlog;
				switch (type) {
				case "repcmt": {
				
				
					int idCmt = Integer.parseInt(request.getParameter("idCmt"));
					String userCmt = request.getParameter("userCmt");
					String userReply = user.getUserName();
					boolean checkMess = request.getParameter("message")==""?false:true;
					if(checkMess) {
						try {
							DBAOBlog.replyCommentBlog(idBlog, idCmt, userCmt, userReply, request.getParameter("message"));
							getServletContext().setAttribute("listBlog", new ListBlog(DBAOBlog.getlistBlog()));
							request.getRequestDispatcher(url).forward(request, response);
						} catch (ClassNotFoundException | SQLException e) {
							//response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
							e.printStackTrace();
						}
					}else {
						response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
					}
					break;
				}
				case "bigcmt":{
				
					String userName = user.getUserName();
					boolean checkMess = request.getParameter("message")==""?false:true;
					
					if(checkMess) {
						try {
							DBAOBlog.commentBlog(idBlog, userName, request.getParameter("message"));
							//chuyen lai ve trang blog
							getServletContext().setAttribute("listBlog", new ListBlog(DBAOBlog.getlistBlog()));
							
							request.getRequestDispatcher(url).forward(request, response);
						} catch (ClassNotFoundException | SQLException e) {
							response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
						}
					}else {
						response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
					}
					
					break;
				}
				default:
					response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
				}
			}else {
				request.getRequestDispatcher("/anime-main/login.jsp").forward(request, response);
			}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
