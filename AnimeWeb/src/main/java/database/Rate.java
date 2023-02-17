package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Rate {
	public Rate() {
		
	}
	public ArrayList<Integer> getListRate(int idMovie) throws ClassNotFoundException, SQLException{
		ArrayList<Integer> result= new ArrayList<>();
		Connection conn = null;
		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select score from rate where idMovie=?");
		ps.setInt(1, idMovie);
		ResultSet rs = ps.executeQuery();
	
		while(rs.next()) {
			result.add(rs.getInt(1));
		}
		
		;
		if(result.size()==0) {
			result.add(0);
		}
		return result;
	}
	public boolean checkRate(String userName,int idMovie) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select score from rate where idMovie=? and UserName=?" );
		ps.setInt(1, idMovie);
		ps.setString(2, userName);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
		
				;
				return false;
			
		}
		
		;
		return true;
	
	}
	public int getRate(String userName,int idMovie) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select score from rate where idMovie=? and UserName=?" );
		ps.setInt(1, idMovie);
		ps.setString(2, userName);
		int score=1;
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			score=rs.getInt(1);
		}
		;
		return score;
	
	}
	public void voteRate(String userName,int idMovie,int score) throws ClassNotFoundException, SQLException {
		boolean check = checkRate(userName, idMovie);
		Connection conn = null;
		conn = DataSource.getConnection();
		PreparedStatement ps;
		String query;
		if(check==true) {
			query="	insert into rate values(?,?,?)" ;
			ps = conn.prepareStatement(query);
			ps.setInt(1, idMovie);
			ps.setString(2, userName);
			ps.setInt(3, score);
			ps.executeUpdate();
		}else {
			query="update rate set score=? where idMovie=? and UserName=?" ;
			ps = conn.prepareStatement(query);
			ps.setInt(1, score);
			ps.setInt(2, idMovie);
			ps.setString(3, userName);
			ps.executeUpdate();
		}
		;
	}
	
}
