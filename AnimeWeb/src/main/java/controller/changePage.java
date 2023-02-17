package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class changePage
 */
@WebServlet("/anime-main/changePage")
public class changePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String PageNumber = request.getParameter("number");
		if("index".equalsIgnoreCase(type)) {
			int offset = Integer.parseInt(PageNumber);
			request.setAttribute("offset",offset);
			request.getRequestDispatcher("/anime-main/index.jsp").forward(request, response);
		}
		if("categories".equalsIgnoreCase(type)) {

			int offset = Integer.parseInt(PageNumber);
			String rqGenre = request.getParameter("rqGenre");
			request.setAttribute("rqGenre", rqGenre);
			request.setAttribute("offset",offset);
			request.getRequestDispatcher("/anime-main/categories.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
