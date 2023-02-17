package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Account;


@WebServlet("/admin/featureAdmin")
public class featureAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account ac = (Account) session.getAttribute("user");
	

	
		
			if(ac==null) {
				getServletContext().getRequestDispatcher("/admin/login.jsp").forward(request, response);
			}
			if(ac.getIsAdmin()==0) {
				response.sendRedirect("/admin/login.jsp");
			}
			if(ac.getIsAdmin()==1) {
			
				getServletContext().getRequestDispatcher("/admin/movieManagerment.jsp").forward(request, response);

			}
			
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
