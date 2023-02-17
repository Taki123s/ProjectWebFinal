package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.movie;
import model.Account;
import model.ListMovie;
import model.Movie;

@WebServlet("/anime-main/follow")
public class follow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		String sessionId = ";jsessionid=" + session.getId();
		Account user = (Account) session.getAttribute("user");
		if (user == null) {
			request.getRequestDispatcher("/anime-main/login.jsp").forward(request, response);
		} else {
			if(type==null) {
				int idMovie = Integer.parseInt(request.getParameter("idMv"));
				boolean checkFl = false;
				ListMovie list = null;
				try {
					checkFl = new movie().follow(user.getUserName(), idMovie);

					list = new movie().getMovie();
				} catch (ClassNotFoundException | SQLException e) {

					response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
				}

				request.setAttribute("viewFilm", list.findMovie(idMovie));
				request.setAttribute("checkFl", checkFl);
				response.sendRedirect(getServletContext().getContextPath() + "/anime-main/anime_details" + sessionId + "?idMv=" + idMovie);
			}else {
				try {
					ArrayList<Movie> listFollow = new movie().getFollowMovie(user.getUserName());
					request.setAttribute("listFollow", listFollow);
					request.getRequestDispatcher("/anime-main/follow.jsp").forward(request, response);
				} catch (ClassNotFoundException | SQLException e) {
			
					response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
