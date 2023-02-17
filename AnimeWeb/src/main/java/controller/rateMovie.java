package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Rate;
import model.Account;


@WebServlet("/anime-main/rateMovie")
public class rateMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account user = (Account) session.getAttribute("user");
		Integer score =Integer.valueOf(request.getParameter("scoreMovie"));
		Integer idMovie = Integer.valueOf(request.getParameter("id"));
		String sessionId = ";jsessionid=" + session.getId();
		if(user==null) {
			response.sendRedirect(getServletContext().getContextPath() +"/anime-main/login.jsp");
		}else {
			Rate rate = new Rate();
			try {
				rate.voteRate(user.getUserName(),idMovie,score);
			} catch (ClassNotFoundException | SQLException e) {
				response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
			}
			response.sendRedirect(getServletContext().getContextPath()+"/anime-main/anime_details"+sessionId+"?idMv="+idMovie);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
