package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.ListMovie;
import model.Movie;

/**
 * Servlet implementation class watching
 */
@WebServlet("/anime-main/watching")
public class watching extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int idMovie = Integer.parseInt(request.getParameter("idM"));
		int chapter = Integer.parseInt(request.getParameter("chapter"));
		try {
			
			ListMovie list =(ListMovie) getServletContext().getAttribute("listMovie");
			Movie m = list.findMovie(idMovie);
			request.setAttribute("viewFilm", m);
			request.setAttribute("linkChap", idMovie+"_"+chapter+".mp4");
			request.setAttribute("chapter", m.getListchapter().get(chapter-1));
			request.setAttribute("cr", new Date().toString());
			request.getRequestDispatcher("/anime-main/anime-watching.jsp").forward(request, response);
		} catch (Exception e) {
			response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
