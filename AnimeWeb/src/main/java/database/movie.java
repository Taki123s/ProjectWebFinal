package database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.Part;

import model.Account;
import model.Comment;
import model.ListMovie;
import model.Movie;
import model.chapter;

public class movie {
	public movie() {

	}

	public Account findUser(String userName) throws ClassNotFoundException, SQLException {
		Login login = new Login();
		ArrayList<Account> listAccount = login.getConnection();
		for (Account ac : listAccount) {
			if (userName.equalsIgnoreCase(ac.getUserName())) {
				return ac;

			}
		}
		return null;
	}

	public ArrayList<Comment> getComment(int idMovie) throws ClassNotFoundException, SQLException {
		ArrayList<Comment> result = new ArrayList<>();
		Connection conn = null;
		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select * from comment where idMovie=? order by timecomment desc");
		ps.setInt(1, idMovie);
		ResultSet rs = ps.executeQuery();
		Comment cm;
		while (rs.next()) {
			String userName = rs.getString(1);
			Account ac = findUser(userName);
			cm = new Comment(userName, rs.getString(2), rs.getInt(3), rs.getString(4));
			cm.setAccount(ac);
			result.add(cm);
		}
		;
		return result;
	}

	public ArrayList<chapter> getChapters(int idMovie) throws ClassNotFoundException, SQLException {
		ArrayList<chapter> result = new ArrayList<>();
		Connection conn = null;
		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select * from chapter where idMovie=?");
		ps.setInt(1, idMovie);
		ResultSet rs = ps.executeQuery();
		chapter ct;
		while (rs.next()) {
			ct = new chapter(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			result.add(ct);
		}
		;
		return result;
	}

	public void updateView(int idMovie) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("update movie set viewer=viewer+1 where idMovie=" + idMovie);

		ps.executeUpdate();
		;
	}

	public ArrayList<String> getlistGenre() throws ClassNotFoundException, SQLException {
		ArrayList<String> result = new ArrayList<>();
		Connection conn = null;
		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select distinct genre from movie");
		ResultSet rs = ps.executeQuery();

		String genre;
		while (rs.next()) {
			genre = rs.getString(1);
			result.add(genre);
		};
		return result;
	}

	public ArrayList<Movie> getlistMovie() throws ClassNotFoundException, SQLException {
		ArrayList<Movie> result = new ArrayList<>();
		Connection conn = null;
		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select * from movie order by idMovie desc");
		ResultSet rs = ps.executeQuery();
		Movie movie;
		int idMovie;
		while (rs.next()) {
			idMovie = rs.getInt(1);
			ArrayList<Comment> comments = getComment(idMovie);
			ArrayList<chapter> chapters = getChapters(idMovie);
			movie = new Movie(idMovie, rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
					rs.getString(7), comments, chapters, rs.getString(8));
			result.add(movie);
		}
		;
		return result;

	}

//	public ArrayList<Movie> gettop5Movie() throws ClassNotFoundException, SQLException {
//		ArrayList<Movie> result = new ArrayList<>();
//		Connection conn = null;
//		conn = new DatabaseContext().getConnection();
//
//		PreparedStatement ps = conn.prepareStatement("select top 5 * from movie order by viewer desc");
//		ResultSet rs = ps.executeQuery();
//		Movie movie;
//		int idMovie;
//		while (rs.next()) {
//			idMovie = rs.getInt(1);
//			ArrayList<Comment> comments = getComment(idMovie);
//			ArrayList<chapter> chapters = getChapters(idMovie);
//			movie = new Movie(idMovie, rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
//					rs.getString(7), comments, chapters, rs.getString(8));
//			result.add(movie);
//		}
//		;
//		return result;
//
//	}



	public ListMovie getMovie() throws ClassNotFoundException, SQLException {
		ListMovie m;
		ArrayList<Movie> movie = getlistMovie();
		m = new ListMovie(movie);
		m.setListGenre(getlistGenre());
		return m;
	}

	public ArrayList<Movie> getFollowMovie(String userName) throws SQLException, ClassNotFoundException {
		ArrayList<Movie> result = new ArrayList<>();
		Connection conn = null;
		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select * from follow where UserName=?");

		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();
		ListMovie listMovie = new ListMovie(getlistMovie());
		int idMovie;
		Movie mv;
		while (rs.next()) {
			rs.getString(1);
			idMovie = rs.getInt(2);
			mv = listMovie.findMovie(idMovie);
			if (mv != null) {
				result.add(mv);
			}

		}
		;
		return result;

	}

	public boolean isFollow(String userName, int idMovie) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select * from follow where UserName=? and idMovie=?");

		ps.setString(1, userName);
		ps.setInt(2, idMovie);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	public boolean follow(String userName, int idMovie) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		boolean isFollow = isFollow(userName, idMovie);
		conn = DataSource.getConnection();
		String query;
		if (isFollow == false) {
			query = "insert into follow values(?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, userName);
			ps.setInt(2, idMovie);
			ps.executeUpdate();
			;
			return true;
		} else {
			query = "delete follow where UserName=? and idMovie=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, userName);
			ps.setInt(2, idMovie);
			ps.executeUpdate();
			;
			return false;
		}
	}

	public boolean removeMovie(Movie movie, String path) throws ClassNotFoundException, SQLException {
		String query = "delete from movie where idMovie=?";
		Connection conn = null;
		conn = DataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, movie.getIdMovie());
		int row = ps.executeUpdate();
		if (row > 0) {
			String avtPath = path + "\\avatarMovie\\" + movie.getAvatar();
			String chapterPath = path + "\\chapter\\";
			File avtFile = new File(avtPath);
			avtFile.delete();
			File chapterFolder = new File(chapterPath);
			for (File f : chapterFolder.listFiles()) {
				if (f.getName().startsWith(movie.getIdMovie() + "_")) {
					f.delete();
				}
			}

			;
			return true;
		}
		;
		return false;

	}

	public boolean updateMovie(String nameMv, String genre, String totalEpi, Part avt, String des, String path,
			Movie mv) throws ClassNotFoundException, SQLException, IOException {
		Connection conn = null;
		conn = DataSource.getConnection();
		int totalEp;
		if (nameMv.equalsIgnoreCase(""))
			nameMv = mv.getNameMovie();
		if (genre.equalsIgnoreCase(""))
			genre = mv.getGenre();
		if (totalEpi.equalsIgnoreCase("")) {
			totalEp = mv.getTotalEpisodes();
		} else {
			totalEp = Integer.parseInt(totalEpi);
		}
		if (des.equalsIgnoreCase(""))
			des = mv.getDescription();
		String query = "update movie set nameMovie=? , genre=? , totalEpisodes=?,descriptionit=?,avatar=? where idMovie =?";
		PreparedStatement ps = conn.prepareStatement(query);
		boolean m = checkAvtFormat(avt.getSubmittedFileName());
		String avtname = mv.getAvatar();
		if (m) {
			avtname = mv.getIdMovie() + "avt."
					+ avt.getSubmittedFileName().substring(avt.getSubmittedFileName().length() - 3);
			saveAvttoServer(path, avt, mv);
		}

		ps.setString(1, nameMv);
		ps.setString(2, genre);
		ps.setInt(3, totalEp);
		ps.setString(4, des);
		ps.setString(5, avtname);
		ps.setInt(6, mv.getIdMovie());
		int check = ps.executeUpdate();
		if (check > 0) {
			;
			return true;
		} else {
			;
			return false;
		}

	}

	public boolean checkAvtFormat(String name) {
		if (name.endsWith("jpg") || name.endsWith("png")) {
			return true;
		}
		return false;
	}

	public void saveAvttoServer(String path, Part avt, Movie mv) throws IOException {

		String savePath = path + "avatarMovie";
		File folder = new File(savePath);
		for (File f : folder.listFiles()) {
			if (f.getName().equals(mv.getAvatar())) {
				f.delete();
			}
		}
		String avtname = mv.getIdMovie() + "avt."
				+ avt.getSubmittedFileName().substring(avt.getSubmittedFileName().length() - 3);
		avt.write(savePath + "\\" + avtname);
	}

	public boolean removeChapter(Movie mv, String index, String path) throws IOException, ClassNotFoundException {
		File folder = new File(path);
		String focusChapterFile = path + File.separator + mv.getIdMovie() + "_" + index + ".mp4";
		File chapterFocus = new File(focusChapterFile);
		if (chapterFocus.exists()) {
			chapterFocus.delete();
		}
		for (File chap : folder.listFiles()) {
			if (chap.getName().startsWith(mv.getIdMovie() + "_")) {
				String indexChaper = chap.getName().split("_")[1];
				StringTokenizer token = new StringTokenizer(indexChaper, ".");
				int chapIndex = Integer.parseInt(token.nextToken());
				if (chapIndex > Integer.parseInt(index)) {
					chapIndex--;

					String name = mv.getIdMovie() + "_" + chapIndex + "." + token.nextToken();
					File newFile = new File(chap.getParent(), name);
					Files.move(chap.toPath(), newFile.toPath());
				}
			}
		}
		String query = "delete from chapter where idMovie=? and stt=?";
		try {
			Connection conn = null;
			conn = DataSource.getConnection();
			PreparedStatement del = conn.prepareStatement(query);
			del.setInt(1, mv.getIdMovie());
			del.setInt(2, Integer.parseInt(index));
			del.executeUpdate();
			PreparedStatement update = conn.prepareStatement("update chapter set stt=? where idMovie=? and stt=?");
			for (int i = Integer.parseInt(index); i < mv.getListchapter().size(); i++) {
				update.setInt(1, mv.getListchapter().get(i).getIndex() - 1);
				update.setInt(2, mv.getIdMovie());
				update.setInt(3, mv.getListchapter().get(i).getIndex());
				update.executeUpdate();
			}
			PreparedStatement updateMv = conn.prepareStatement("update movie set currentEpisode=? where idMovie=?");
			updateMv.setInt(1, mv.getListchapter().size() - 1);
			updateMv.setInt(2, mv.getIdMovie());

			updateMv.executeUpdate();
			;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public boolean checkVideoName(String name) {
		if (name.endsWith(".mp4")) {
			return true;
		}
		return false;
	}

	public boolean updateChaper(Movie mv, String index, String savePath, Part file, String opening)
			throws IOException, ClassNotFoundException, SQLException {

		int stt;
		int op;
		try {
			stt = Integer.parseInt(index);
			if (opening.equalsIgnoreCase(""))
				opening = String.valueOf(mv.getListchapter().get(stt - 1).getOpening());
			op = Integer.parseInt(opening);
		} catch (Exception e) {
			return false;
		}

		String focusChapterFile = savePath + File.separator + mv.getIdMovie() + "_" + index + ".mp4";
		File focusFile = new File(focusChapterFile);
		String videoName = file.getSubmittedFileName();
		boolean check = checkVideoName(videoName);
		Connection conn = null;
		conn = DataSource.getConnection();

		if (check) {
			if (focusFile.exists()) {
				focusFile.delete();
			}
			file.write(focusChapterFile);

		}
		PreparedStatement ps = conn.prepareStatement("update chapter set opening=? where idMovie=? and stt=?");
		ps.setInt(1, op);
		ps.setInt(2, mv.getIdMovie());
		ps.setInt(3, stt);
		int sc = ps.executeUpdate();
		if (sc > 0) {
			;
			return true;
		} else {
			;
			return false;
		}

	}

	public boolean addChapter(Movie mv, String index, String savePath, Part file, String opening)
			throws ClassNotFoundException, SQLException, IOException {
		int stt;
		int op;
		try {
			stt = Integer.parseInt(index);
			if (opening.equalsIgnoreCase(""))
				opening = "0";
			op = Integer.parseInt(opening);
		} catch (Exception e) {
			return false;
		}
		String focusChapterFile = savePath + File.separator + mv.getIdMovie() + "_" + index + ".mp4";
		String videoName = file.getSubmittedFileName();
		boolean check = checkVideoName(videoName);
		Connection conn = null;
		conn = DataSource.getConnection();
		if (check) {

			file.write(focusChapterFile);
			PreparedStatement ps = conn.prepareStatement("insert into chapter values(?,?,?)");
			ps.setInt(1, mv.getIdMovie());
			ps.setInt(2, stt);
			ps.setInt(3, op);
			int sc = ps.executeUpdate();
			ps.close();
			PreparedStatement p = conn.prepareStatement("update movie set currentEpisode=? where idMovie=?");
			p.setInt(1, mv.getListchapter().size() + 1);
			p.setInt(2, mv.getIdMovie());
			sc += p.executeUpdate();
			if (sc > 1) {
				;
				return true;
			} else {
				;
				return false;
			}
		}
		return false;
	}

	public boolean addMovie(String nameMv, String genre, String des, String totalEpi, Part avt, String savePath) throws ClassNotFoundException, SQLException, IOException {
		int ttep;
		if (totalEpi.equalsIgnoreCase(""))totalEpi = "1";
		ttep = Integer.parseInt(totalEpi);
		Connection conn=null;
		conn = DataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND   TABLE_NAME   = ?");
		ps.setString(1, "projectweb");
		ps.setString(2, "movie");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int newId = rs.getInt(1);
	
	
		String avtname = newId + "avt."
				+ avt.getSubmittedFileName().substring(avt.getSubmittedFileName().length() - 3);
		Movie currentMv = new Movie(newId, nameMv, genre, 0, ttep, 0, avtname, null, null, des);
	
		saveAvttoServer(savePath, avt, currentMv);
		String query ="INSERT INTO `projectweb`.`movie` (`nameMovie`, `genre`, `currentEpisode`, `totalEpisodes`, `viewer`, `avatar`, `descriptionit`) values(?,?,?,?,?,?,?)";

		PreparedStatement p = conn.prepareStatement(query);
		
			p.setString(1, nameMv);
			p.setString(2, genre);
			p.setInt(3, currentMv.getCurrentEpisode());
			p.setInt(4, currentMv.getTotalEpisodes());
			p.setInt(5, currentMv.getView());
			p.setString(6, currentMv.getAvatar());
			p.setString(7, des);
			int cs=p.executeUpdate();
			if(cs>0) {
				;
				return true;
			}else {
				;
				return false;
			}
	
	}

}
