package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import database.Login;
import model.Account;
import model.Encode;
import model.ListAccount;

@MultipartConfig
@WebServlet("/anime-main/profile")
public class profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String sessionId = ";jsessionid=" + session.getId();
		Account user = (Account) session.getAttribute("user");
		String pass = request.getParameter("pass");
		Encode encrypt = new Encode();
		String passw = encrypt.toSHA1(pass);
		String email = request.getParameter("email");
		Part file = request.getPart("files");
		boolean error = false;
		
		Path nameFile = Path.of(file.getSubmittedFileName().toString());
		if("".equalsIgnoreCase(pass)) {
			passw=user.getPassWord();
			pass=user.getPassWord();
		}
		if (user == null || user.getUserName().length() > 25 || pass.length() < 6
				|| pass.length() > 500 ) {
			error = true;
		}
		
		if (error == false) {
			boolean checkSave=false;
			String appPath = request.getServletContext().getRealPath("");
			String path = appPath + "anime-main\\storage\\avatarUser";
			String savefile = null;
			String img = null;
			String filename = nameFile.toString();
	
			if(file.getSize()>0) {
				if((filename.endsWith("jpg") || filename.endsWith("png"))) {
					 img= "avatar." + filename.subSequence(filename.length() - 3, filename.length());
						savefile = path + "\\" + user.getUserName() + img;
						checkSave=true;
				}else {
				
					response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
				}

				
			}
			
		
	
			Login login = new Login();
		
		
		
			try {
				if(checkSave) {
					login.changeProfile(user.getUserName(), passw, email, user.getUserName() + img);
					File f = new File(path + "\\" + user.getAvatar());
					if (f.exists()) {
						f.delete();
					}
					file.write(savefile);
				}else {
					login.changeProfile(user.getUserName(), passw, email, user.getAvatar());
				}
				
				
		
				ListAccount listAc = new ListAccount(new Login().getConnection());
				getServletContext().setAttribute("listUser",listAc);
			
				session.setAttribute("user", listAc.findByUserName(user.getUserName()));
			
				response.sendRedirect(getServletContext().getContextPath() + "/anime-main/index.jsp" + sessionId);
			} catch (ClassNotFoundException | SQLException e) {
				response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
			}

			

		} else {
			response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
			}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
