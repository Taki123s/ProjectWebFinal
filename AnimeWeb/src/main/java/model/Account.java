package model;

import java.sql.SQLException;

import database.Rate;

public class Account {
	private String userName;
	private String passWord;
	private String email;
	private String avatar;
	private int isAdmin;

	public Account(String userName, String passWord, String email, String avatar,int isAdmin) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.avatar = avatar;
		this.isAdmin = isAdmin;

	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Account [userName=" + userName + ", passWord=" + passWord + "]";
	}
	public int getmyRate(int idMovie) throws ClassNotFoundException, SQLException {
		Rate rate = new Rate();
		int rs=rate.getRate(userName, idMovie);
		return rs;
	}

}
