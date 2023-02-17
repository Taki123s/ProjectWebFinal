package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import database.movie;
import model.ListMovie;
import model.Movie;

@MultipartConfig
@WebServlet("/admin/ChapterAdmin")
public class ChapterAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String idMovie = request.getParameter("idMovie");
			String index =request.getParameter("index");
			String update = request.getParameter("update");
			String delete = request.getParameter("delete");
			String confirm = request.getParameter("confirm");
			String type=request.getParameter("type");
			movie DAOmv = new movie();
			String appPath = request.getServletContext().getRealPath("");
			String savePath = appPath + "anime-main"+File.separator+"storage"+File.separator+"chapter";
			ListMovie listMv = (ListMovie)getServletContext().getAttribute("listMovie");
			Movie mv = listMv.findMovie(Integer.parseInt(idMovie));
			switch (type) {
			case "setting": {
					if(delete!=null) {
						
						try {
							boolean success=DAOmv.removeChapter(mv,index,savePath);
						if(success) {
							getServletContext().setAttribute("listMovie", DAOmv.getMovie());
							
							request.getRequestDispatcher("/admin/movieManagerment.jsp").forward(request, response);
						}else {
							response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
						}
						} catch (ClassNotFoundException | SQLException e) {
							
							response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
						}
						
					}
					if(update!=null) {
						request.setAttribute("movieBeUpdate", mv);
						
						request.setAttribute("index", index);
						request.getRequestDispatcher("/admin/updateChapter.jsp").forward(request, response);
					}
					if(confirm!=null) {
						try {
							Part file = request.getPart("video");
							String opening = request.getParameter("opening");
							boolean success = DAOmv.updateChaper(mv,index,savePath,file,opening);
							if(success) {
								getServletContext().setAttribute("listMovie", DAOmv.getMovie());
								
								request.getRequestDispatcher("/admin/movieManagerment.jsp").forward(request, response);
							}else {
								response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
							}
						}catch (Exception e) {
							response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
						}
					}
				break;
			}
			case "add":{
				try {
					Part file = request.getPart("video");
					String opening = request.getParameter("opening");
					boolean success = DAOmv.addChapter(mv,index,savePath,file,opening);
					if(success) {
						getServletContext().setAttribute("listMovie", DAOmv.getMovie());
						request.getRequestDispatcher("/admin/movieManagerment.jsp").forward(request, response);
					}else {
						response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
					}
				}catch (Exception e) {
					response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + type);
			}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
