package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class Register {
	public Register() {
		// TODO Auto-generated constructor stub
	}



	public void createAccount(String userName, String passWord, String email)
			throws ClassNotFoundException, SQLException {

		Connection conn = null;
		conn = DataSource.getConnection();
		String avt = "defaultavatar.jpg";
		PreparedStatement ps = conn.prepareStatement("insert into account values(?,?,?,?,?)");
		ps.setString(1, userName);
		ps.setString(2, passWord);
		ps.setString(3, email);
		ps.setString(4, avt);
		ps.setInt(5, 0);
		ps.executeUpdate();
		;

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

	}
}
