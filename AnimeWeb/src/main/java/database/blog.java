package database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.Part;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import model.Blog;
import model.blogComment;
import model.replyComment;

public class blog {
	public blog() {

	}

	public ArrayList<replyComment> getlistReplyCmt(int idBlog, int idCmt) throws ClassNotFoundException, SQLException {
		ArrayList<replyComment> list = new ArrayList<>();
		Connection conn = null;

		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select * from replyComment where idBlog=? and idCmt=?");
		ps.setInt(1, idBlog);
		ps.setInt(2, idCmt);
		ResultSet rs = ps.executeQuery();
		replyComment replyCmt;
		while (rs.next()) {
			replyCmt = new replyComment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7));
			list.add(replyCmt);
		}
		
		return list;
	}

	public ArrayList<blogComment> getBlogComment(int idBlog) throws ClassNotFoundException, SQLException {
		ArrayList<blogComment> list = new ArrayList<>();
		Connection conn = null;

		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select * from blogComment where idBlog=?");
		ps.setInt(1, idBlog);
		ResultSet rs = ps.executeQuery();
		blogComment blogcmt;
		ArrayList<replyComment> listReplyCmt;
		while (rs.next()) {
			int idblog = rs.getInt(1);
			int idcmt = rs.getInt(2);
			listReplyCmt = getlistReplyCmt(idblog, idcmt);
			blogcmt = new blogComment(idblog, idcmt, rs.getString(3), rs.getString(4), rs.getString(5), listReplyCmt);
			list.add(blogcmt);
		}
		
		return list;
	}

	public ArrayList<Blog> getlistBlog() throws ClassNotFoundException, SQLException {
		ArrayList<Blog> list = new ArrayList<>();
		Connection conn = null;

		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select * from blog");
		ResultSet rs = ps.executeQuery();
		Blog blog;
		int blogid;
		ArrayList<blogComment> listcmt;
		while (rs.next()) {
			blogid = rs.getInt(1);
			listcmt = getBlogComment(blogid);
			blog = new Blog(blogid, rs.getString(2), listcmt, rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6));
			list.add(blog);
		}

		return list;
	}

	public void commentBlog(int idBlog, String userName, String mess) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = DataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO `projectweb`.`blogcomment` (`idBlog`, `mess`, `UserName`, `dateCmt`) values(?,?,?,?)");
		ps.setInt(1, idBlog);
	
		ps.setString(2, mess);
		ps.setString(3, userName);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String fm = format.format(date);
		ps.setString(4, fm);
		ps.executeUpdate();

	}

	public void replyCommentBlog(int idBlog, int idCmt, String userNameCmt, String userNameReply, String mess)
			throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = DataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO `projectweb`.`replycomment` (`idBlog`, `idCmt`, `UserNameCmt`, `UserNameReply`, `mess`, `daycmt`) values(?,?,?,?,?,?)");

		ps.setInt(1, idBlog);
		ps.setInt(2, idCmt);
		ps.setString(3, userNameCmt);
		ps.setString(4, userNameReply);
		ps.setString(5, mess);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String fm = format.format(date);
		ps.setString(6, fm);

		ps.executeUpdate();


	}

	public boolean checkAvtFormat(String name) {
		if (name.endsWith("jpg") || name.endsWith("png")) {
			return true;
		}
		return false;
	}

	public boolean insertBlog(String path, Part doc, String title, Part avt, String dayDebut) throws Exception {
		Connection conn = null;
		boolean m = checkAvtFormat(avt.getSubmittedFileName());
		conn = DataSource.getConnection();
		int newId=1;
		PreparedStatement ps = conn.prepareStatement("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND   TABLE_NAME   = ?");
		ps.setString(1, "projectweb");
		ps.setString(2, "blog");
		ResultSet n = ps.executeQuery();
		String avtname = "avt." + avt.getSubmittedFileName().substring(avt.getSubmittedFileName().length() - 3);
		n.next();
			newId=n.getInt(1);
		
	
		String query = "INSERT INTO `projectweb`.`blog` (`title`, `folder`, `datePost`, `avt`, `dayDebut`) values(?,?,?,?,?)";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String fm = format.format(date);
		PreparedStatement pps = conn.prepareStatement(query);
	
		pps.setString(1, title);
		pps.setString(2, newId + "_blog");
		pps.setString(3, fm);
		pps.setString(4, avtname);
		pps.setString(5, dayDebut);
		pps.executeUpdate();

		
		if (doc.getSize() != 0 && m) {
			File folder = new File(path + "\\" + newId + "_blog");

			if (folder.exists()) {
				for (File f : folder.listFiles()) {
					f.delete();
				}
				folder.delete();
			}
			folder.mkdirs();
			
			saveWordtoServer(folder.getAbsolutePath(), doc);
			saveAvttoServer(folder.getAbsolutePath(), avt);
			
		

			return true;
		}

		return false;

	}

	public void saveWordtoServer(String path, Part doc) throws Exception {

		String linkDocx = path + "\\" + "docfile.docx";
		doc.write(linkDocx);
		Document fileDocx = new Document(linkDocx);
		String htmlFile = path + "\\" + "htmlfile.html";
		fileDocx.save(htmlFile, SaveFormat.HTML);
		
	}
	public void saveAvttoServer(String path,Part avt) throws IOException {
		String avtname = "avt." + avt.getSubmittedFileName().substring(avt.getSubmittedFileName().length() - 3);
		avt.write(path + "\\" + avtname);
	}

	public boolean deleteBlog(int idBlog) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DataSource.getConnection();
			String deleteBlog = "delete from blog where idBlog=?";

			PreparedStatement ps = conn.prepareStatement(deleteBlog);
			ps.setInt(1, idBlog);
			int check = ps.executeUpdate();
			if (check > 0) {
			
				return true;
			} else {
			
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			conn.rollback();
		}
		return false;

	}

	public boolean updateBlog(String savePath, Part file, String title, Part avt, String dayDebut, int idblog)
			throws Exception {
		Connection conn = null;
		boolean m = checkAvtFormat(avt.getSubmittedFileName());
		conn = DataSource.getConnection();
	
	
		if (m) {
			File folderSave = new File(savePath);
			// thao tac folder blog.html va avt
			if(file.getSize() != 0 ) {
				
				//xoa het img va file 
				for(File f : folderSave.listFiles()) {
					f.delete();
				}
				saveAvttoServer(savePath, avt);
				saveWordtoServer(savePath, file);
			}else {
				
				//xoa avt
				for(File f : folderSave.listFiles()) {
					if(f.getName().contains("avt")) f.delete();
					
				}
				saveAvttoServer(savePath, avt);
			}
			
			
			// database
			String updateBlog = "update blog set title=? , avt=? , dayDebut=? where idBlog =?";
			String avtname = "avt." + avt.getSubmittedFileName().substring(avt.getSubmittedFileName().length() - 3);
			PreparedStatement ps = conn.prepareStatement(updateBlog);
			ps.setString(1, title);
			ps.setString(2, avtname);
			ps.setString(3, dayDebut);
			ps.setInt(4, idblog);
			ps.executeUpdate();

			return true;
		}else if(avt.getSize()==0){
			File folderSave = new File(savePath);
			// thao tac folder blog.html va avt
			if(file.getSize() != 0 ) {
				
				//xoa het file 
				for(File f : folderSave.listFiles()) {
					if(f.getName().contains("html")||f.getName().contains("docfile")) f.delete();
				}
				saveWordtoServer(savePath, file);
			}
			// database
			String updateBlog = "update blog set title=? , dayDebut=? where idBlog =?";
			
			PreparedStatement ps = conn.prepareStatement(updateBlog);
			ps.setString(1, title);
			
			ps.setString(2, dayDebut);
			ps.setInt(3, idblog);
			ps.executeUpdate();
		
			return true;
		}else {
		
			//loi~
			return false;
		}

	}
}
