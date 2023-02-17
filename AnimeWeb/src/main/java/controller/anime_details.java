package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

/**
 * Servlet implementation class anime_details
 */
@WebServlet("/anime-main/anime_details")
public class anime_details extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.addHeader("Pragma", "No-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.addDateHeader("Expires", 1);
		String idM = request.getParameter("idf");
		HttpSession session = request.getSession(false);
		Account user = (Account) session.getAttribute("user");
		ListMovie list = null;
		try {
			list = new movie().getMovie();
		} catch (ClassNotFoundException | SQLException e1) {

			response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
		}

		if (idM == null && user != null) {
			String query = request.getParameter("idMv");
			int idMovie = Integer.valueOf(query);
			Movie m = list.findMovie(idMovie);
			if (m != null) {
				request.setAttribute("viewFilm", m);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date date = new Date();
				long nowTime = 0;
				boolean checkFl = false;
				try {
					String fm = format.format(date);
					Date d1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(fm);
					nowTime = d1.getTime();
					session.setAttribute("nowTime", nowTime);

				} catch (ParseException e) {

					response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
				}
				try {
					checkFl = new movie().isFollow(user.getUserName(), idMovie);
				} catch (ClassNotFoundException | SQLException e) {

					response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
				}
				request.setAttribute("checkFl", checkFl);
				request.getRequestDispatcher("/anime-main/anime-details.jsp").forward(request, response);
			} else {
				response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
			}
		}
		if (idM == null && user == null) {
			String query = request.getParameter("idMv");
			int idMovie = Integer.valueOf(query);
			Movie m = list.findMovie(idMovie);
			if (m != null) {
				request.setAttribute("viewFilm", m);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date date = new Date();
				long nowTime = 0;
				boolean checkFl = false;
				try {
					String fm = format.format(date);
					Date d1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(fm);
					nowTime = d1.getTime();
					session.setAttribute("nowTime", nowTime);

				} catch (ParseException e) {

					response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
				}
				request.setAttribute("checkFl", checkFl);
				request.getRequestDispatcher("/anime-main/anime-details.jsp").forward(request, response);
			} else {
				response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
			}
		}
		if (idM != null) {

			int idMovie = Integer.valueOf(idM);
			try {
				new movie().updateView(idMovie);
				list = new movie().getMovie();
			} catch (ClassNotFoundException | SQLException e) {

				response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
			}
		
			Movie m = list.findMovie(idMovie);
			request.setAttribute("viewFilm", m);
		
		
			getServletContext().setAttribute("listMovie", list);
			request.getRequestDispatcher("/anime-main/anime-watching.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
