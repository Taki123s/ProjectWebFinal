package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import model.Comment;

public class comment {
	public comment() {
		
	}
	public boolean addComment(Comment comment) throws ClassNotFoundException, SQLException {
		Connection conn = null;

		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("insert into comment values(?,?,?,?)");
		ps.setString(1, comment.getUserName());
		ps.setString(2, comment.getComment());
		ps.setInt(3, comment.getIdMovie());
		ps.setString(4, comment.getTimeComment());
			try {
				ps.executeUpdate();
				 
				return true;
			}catch (Exception e) {
				 
				return false;
			}

	}
	
}
