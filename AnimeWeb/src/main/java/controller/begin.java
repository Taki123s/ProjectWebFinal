package controller;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import database.Login;
import database.blog;
import database.movie;
import model.ListAccount;
import model.ListBlog;
import model.ListMovie;


@WebServlet("/anime-main/begin")
public class begin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
			movie daoMovie = new movie();
			blog daoBlog = new blog();   
			ListMovie listMovie;
			ListBlog listBlog;
			ListAccount listAccount;
			
			try {
				listMovie = daoMovie.getMovie();
				listBlog = new ListBlog(daoBlog.getlistBlog());
				listAccount = new ListAccount(new Login().getConnection());
				getServletContext().setAttribute("listMovie", listMovie);
				getServletContext().setAttribute("listBlog", listBlog);
				getServletContext().setAttribute("listUser", listAccount);
			
				request.getRequestDispatcher("/anime-main/index.jsp").forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
				
			}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
