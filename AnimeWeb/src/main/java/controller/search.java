package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.ListMovie;
import model.Movie;

@WebServlet("/anime-main/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String value = request.getParameter("searchBox");

		String id = ";jsessionid=" + request.getParameter("id");
		
		try {
			ListMovie listmv = (ListMovie) getServletContext().getAttribute("listMovie");
			ArrayList<Movie> listMovieResult = listmv.findMovie(value);
			PrintWriter print = response.getWriter();
			if (value != "") {
				for (Movie mv : listMovieResult) {
					
					print.println("<tr class=\"rendersearch\" ><td class=\"renderResult\">\r\n"
							+"<img class=\"rsImg\" src=\"/AnimeWeb/anime-main/storage/avatarMovie/"+mv.getAvatar()+"\"></td>"
						+ "<td>\r\n"
							+ "<h2>\r\n" + "<a  class=\"resultLink\" href=\"anime_details" + id + "?idMv=" + mv.getIdMovie() + "\">"
							+ mv.getNameMovie() + "</a>\r\n" + "</h2>\r\n" + "</td></tr>\r\n");
				}
			} else {
				print.print("");
			}

		} catch (ClassNotFoundException | SQLException e) {

			response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
