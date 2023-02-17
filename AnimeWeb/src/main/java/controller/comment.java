package controller;

import java.io.IOException;
import java.sql.SQLException;
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
import model.Comment;
import model.ListMovie;
import model.Movie;

/**
 *
 * Servlet implementation class comment
 */
@WebServlet("/anime-main/comment")
public class comment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int idFilm = Integer.valueOf(request.getParameter("saveID"));
		Account account = (Account) session.getAttribute("user");
		String mess = request.getParameter("message");
	
		ListMovie listMovie = (ListMovie) getServletContext().getAttribute("listMovie");
		if (account != null && mess != "") {
			String userName = account.getUserName();
			Movie movie = listMovie.findMovie(idFilm);
			
			int idMovie = movie.getIdMovie();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = new Date();
			String fm = format.format(date);
			Comment cm = new Comment(userName, mess, idMovie, fm);
			database.comment cmt = new database.comment();
			try {
				cmt.addComment(cm);
				listMovie = new movie().getMovie();
				
				getServletContext().setAttribute("listMovie", listMovie);
				String id = ";jsessionid=" + session.getId();
				response.sendRedirect(
						getServletContext().getContextPath() + "/anime-main/anime_details" + id + "?idMv=" + idMovie);
			} catch (ClassNotFoundException | SQLException e) {

				response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
			}
		}
		if (account == null) {
			getServletContext().getRequestDispatcher("/anime-main/login.jsp").forward(request, response);
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
