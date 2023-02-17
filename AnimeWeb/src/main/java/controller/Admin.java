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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import database.Login;
import database.blog;
import database.movie;
import model.Account;
import model.Blog;
import model.Encode;
import model.ListAccount;
import model.ListBlog;
import model.ListMovie;
import model.Movie;

@MultipartConfig
@WebServlet("/admin/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String sessionId = ";jsessionid=" + session.getId();

		Account user = (Account) session.getAttribute("user");

		String query = request.getParameter("type");
		if (user != null && user.getIsAdmin() == 1) {
			switch (query) {
			case "upBlog": {
				String title = request.getParameter("title");
		
				String dayDebut = request.getParameter("dayDebut");
				Part file = request.getPart("files");
				Part avt = request.getPart("avt");

				String appPath = request.getServletContext().getRealPath("");

				String savePath = appPath + "anime-main\\storage\\blogSave";

				if (file.getSubmittedFileName().endsWith(".docx")||file.getSubmittedFileName().endsWith(".doc")||file.getSubmittedFileName().endsWith(".odt")) {

					try {
						blog blogDao = new blog();
						blogDao.insertBlog(savePath, file, title, avt, dayDebut);
						getServletContext().setAttribute("listBlog", new ListBlog(new blog().getlistBlog()));
						response.sendRedirect(
								getServletContext().getContextPath() + "/admin/blogManagerment.jsp" + sessionId);
					} catch (Exception e) {
						e.printStackTrace();
						//response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
					}
				}

				break;
			}
			case "settingBlog": {
				String update = request.getParameter("update");
				String delete = request.getParameter("delete");
				String confirm = request.getParameter("confirm");

				if (confirm != null) {

					String title = request.getParameter("title");
					String dayDebut = request.getParameter("dayDebut");
					Part file = request.getPart("files");
					Part avt = request.getPart("avt");
					String appPath = request.getServletContext().getRealPath("");

					blog blogDao = new blog();
					int idblog = Integer.parseInt(request.getParameter("idBlog"));
					Blog blog = null;
					try {
						blog = new ListBlog(new blog().getlistBlog()).findBlogById(idblog);
					} catch (ClassNotFoundException | SQLException e1) {
						
						response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
					}
					String savePath = appPath + "anime-main\\storage\\blogSave\\" + blog.getFolder();
					try {
						boolean check = blogDao.updateBlog(savePath, file, title, avt, dayDebut, idblog);
						if (check) {
							getServletContext().setAttribute("listBlog", new ListBlog(new blog().getlistBlog()));
							request.getRequestDispatcher("/admin/blogManagerment.jsp").forward(request, response);
						}else {
							response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
						}
					} catch (Exception e) {
		
						response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
					}

				}
				if (update != null) {
					int idblog = Integer.parseInt(request.getParameter("idBlog"));
					Blog blog = null;
					try {
						blog = new ListBlog(new blog().getlistBlog()).findBlogById(idblog);
					} catch (ClassNotFoundException | SQLException e1) {
						
						response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
					}
					if (blog != null) {
						request.setAttribute("blogBeUpdate", blog);
						request.getRequestDispatcher("/admin/blogsetting.jsp").forward(request, response);
					} else {
						response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
					}

				}

				if (delete != null) {
					int idblog = Integer.parseInt(request.getParameter("idBlog"));
					try {
						boolean bl = new blog().deleteBlog(idblog);
						if (bl) {
							String appPath = request.getServletContext().getRealPath("");
							ListBlog listblog = (ListBlog) getServletContext().getAttribute("listBlog");
							String folder = listblog.findBlogById(idblog).getFolder();
							String savePath = appPath + "anime-main\\storage\\blogSave" + "\\" + folder;
							File src = new File(savePath);
							if (src.exists()) {
								for (File f : src.listFiles()) {
									f.delete();
								}
								src.delete();
							}
							getServletContext().setAttribute("listBlog", new ListBlog(new blog().getlistBlog()));

							response.sendRedirect(
									getServletContext().getContextPath() + "/admin/blogManagerment.jsp" + sessionId);
						}
					} catch (ClassNotFoundException | SQLException e) {
						
						response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
					}
				}
				break;
			}
			case "settingMovie": {
				int idMovie = Integer.parseInt(request.getParameter("idMovie"));
				String update = request.getParameter("update");
				String delete = request.getParameter("delete");
				String confirm = request.getParameter("confirm");
				String settingChapter = request.getParameter("settingChapter");
				ListMovie mvList = (ListMovie) getServletContext().getAttribute("listMovie");
				Movie mv = mvList.findMovie(idMovie);
				movie DAOMV = new movie();
				boolean check = mv == null ? false : true;
				String appPath = request.getServletContext().getRealPath("");
				String savePath = appPath + "anime-main\\storage\\";
				if (check) {
					if (update != null) {
						request.setAttribute("movieBeUpdate", mv);
						request.getRequestDispatcher("/admin/movieSetting.jsp").forward(request, response);
					}
					if (delete != null) {
						try {
							DAOMV.removeMovie(mv, savePath);
							getServletContext().setAttribute("listMovie", DAOMV.getMovie());
							request.getRequestDispatcher("/admin/movieManagerment.jsp").forward(request, response);
						} catch (ClassNotFoundException | SQLException e) {
							
							response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
						}
					}
					if (confirm != null) {
						String nameMv = request.getParameter("nameMv");
						String genre = request.getParameter("genre");
						String des = request.getParameter("des");
						String totalEpi = request.getParameter("totalEpi");
						Part avt = request.getPart("avt");
						if (Integer.parseInt(totalEpi) < mv.getCurrentEpisode()) {
							response.getWriter().print("Tổng số tập phải lớn hơn số tập hiện có");
						}
						try {
							boolean success = DAOMV.updateMovie(nameMv, genre, totalEpi, avt, des, savePath, mv);
							if (success) {
								getServletContext().setAttribute("listMovie", DAOMV.getMovie());
								request.getRequestDispatcher("/admin/movieManagerment.jsp").forward(request, response);
							} else {
								response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
							}
						} catch (ClassNotFoundException | SQLException e) {
						
							response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
						}

					}
					if (settingChapter != null) {
						request.setAttribute("movieBeUpdate", mv);
						request.getRequestDispatcher("/admin/ChapterSetting.jsp").forward(request, response);
					}
				} else {
					response.getWriter().print("khong tim thay phim ");
				}
				break;
			}
			case "addMovie": {
				movie DAOMV = new movie();
				String nameMv = request.getParameter("nameMv");
				String genre = request.getParameter("genre");
				String des = request.getParameter("des");
				String totalEpi = request.getParameter("totalEpi");
				Part avt = request.getPart("avt");
				String appPath = request.getServletContext().getRealPath("");
				String savePath = appPath + "anime-main\\storage\\";
				try {
					boolean success = DAOMV.addMovie(nameMv, genre, des, totalEpi, avt, savePath);
					if (success) {
						getServletContext().setAttribute("listMovie", DAOMV.getMovie());
						request.getRequestDispatcher("/admin/movieManagerment.jsp").forward(request, response);
					} else {
						response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
						
					}
				} catch (ClassNotFoundException | SQLException e) {
			
					response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
					
					}
				break;
			}
			case "settingAccount": {
					String update = request.getParameter("update");
					String delete = request.getParameter("delete");
					String email = request.getParameter("email");
					String userName = request.getParameter("userName");
					String pass = request.getParameter("password");
					Encode encrypt = new Encode();
					String passWord = encrypt.toSHA1(pass);
					if(pass.equalsIgnoreCase("")) {
						ListAccount listAc = (ListAccount) getServletContext().getAttribute("listUser");
						Account usFocus = listAc.findByUserName(userName);
						passWord=usFocus.getPassWord();
					}
					if(update!=null) {
						try {
							boolean isSuccess = new Login().settingUser(userName, passWord, email);
							if(isSuccess) {
								getServletContext().setAttribute("listUser",new ListAccount( new Login().getConnection()));
								request.getRequestDispatcher("/admin/userManagerment.jsp").forward(request, response);
							}else {
								response.getWriter().print("error");
							}
						} catch (ClassNotFoundException | SQLException e) {
				
							response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
						}
					}
					if(delete!=null) {
						try {
							boolean isSuccess = new Login().deleteUser(userName);
							if(isSuccess) {
								getServletContext().setAttribute("listUser",new ListAccount( new Login().getConnection()));
								request.getRequestDispatcher("/admin/userManagerment.jsp").forward(request, response);
							}else {
								response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
							}
						}catch (Exception e) {
							response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
						}
					}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + query);
			}
		} else {
			request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
