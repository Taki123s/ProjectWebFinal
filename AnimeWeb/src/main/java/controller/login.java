package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Login;
import database.Register;
import model.Account;
import model.Encode;
import model.ListAccount;

@WebServlet("/anime-main/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Encode encrypt = new Encode();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ListAccount listAccount = (ListAccount) getServletContext().getAttribute("listUser");
		String userName = request.getParameter("loginName");
		String passWord = request.getParameter("loginPassword");
		HttpSession session = request.getSession();
		String pass = encrypt.toSHA1(passWord);
		String sessionId = ";jsessionid=" + session.getId();
		String query = request.getParameter("accountBtn");
		Account account = null;
		String url = null;
		if ("login".equals(query)) {
			boolean error = false;
			System.out.println(pass);
			account = listAccount.checkLogin(userName, pass);
			if (account != null) {
				session.setAttribute("user", account);
				url = getServletContext().getContextPath() + "/anime-main/index.jsp" + sessionId;

			} else {
				error = true;

		
				url = getServletContext().getContextPath() + "/anime-main/login.jsp";
			}
			if (error == true) {
				session.setAttribute("errorLogin", "Tên tài khoản hoặc mật khẩu sai");
			}

		}
		if ("signup".equals(query)) {
			String email = request.getParameter("email");
			// validation
			boolean error = false;
			if (userName.length() > 25 || userName.length() < 6 || passWord.length() < 6 || passWord.length() > 100) {
				error = true;
			}

			if (error == false) {
				Register register = new Register();
				try {
					boolean check = listAccount.checkRegister(userName);
					if (check) {
						session.setAttribute("errorSignup", "");
						session.setAttribute("mailOld", "");
						session.setAttribute("nameOld", "");
						session.setAttribute("passOld", "");
						register.createAccount(userName, pass, email);
						getServletContext().setAttribute("listUser", new ListAccount(new Login().getConnection()));
						url = getServletContext().getContextPath() + "/anime-main/index.jsp";
					} else {
						session.setAttribute("errorSignup", "Tên tài khoản đã tồn tại");
						session.setAttribute("mailOld", email);
						session.setAttribute("nameOld", userName);
						session.setAttribute("passOld", passWord);
						url = getServletContext().getContextPath() + "/anime-main/signup.jsp" + sessionId;
					}

				} catch (ClassNotFoundException | SQLException e) {

					response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
				}
			} else {
				session.setAttribute("errorSignup", "Đăng ký không thành công do lỗi dữ liệu");
				session.setAttribute("mailOld", email);
				session.setAttribute("nameOld", userName);
				session.setAttribute("passOld", passWord);
				url = getServletContext().getContextPath() + "/anime-main/signup.jsp" + sessionId;
			}

		}
		if (query == null) {
			session.invalidate();
			url = getServletContext().getContextPath() + "/anime-main/index.jsp";
		}

		response.sendRedirect(url);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
