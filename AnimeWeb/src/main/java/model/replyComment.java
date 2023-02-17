package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class replyComment {
	private int idBlog;
	private int idcmt;
	private String userNameCmt;
	private String userNameReply;
	private String message;
	private String dayCmt;
	private int sttCmt;

	public replyComment(int idBlog, int idcmt, String userNameCmt, String userNameReply, String message, String dayCmt,
			int sttCmt) {
		super();
		this.idBlog = idBlog;
		this.idcmt = idcmt;
		this.userNameCmt = userNameCmt;
		this.userNameReply = userNameReply;
		this.message = message;
		this.dayCmt = dayCmt;
		this.sttCmt = sttCmt;
	}

	public int getIdBlog() {
		return idBlog;
	}

	public void setIdBlog(int idBlog) {
		this.idBlog = idBlog;
	}

	public int getIdcmt() {
		return idcmt;
	}

	public void setIdcmt(int idcmt) {
		this.idcmt = idcmt;
	}

	public String getUserNameCmt() {
		return userNameCmt;
	}

	public void setUserNameCmt(String userNameCmt) {
		this.userNameCmt = userNameCmt;
	}

	public String getUserNameReply() {
		return userNameReply;
	}

	public void setUserNameReply(String userNameReply) {
		this.userNameReply = userNameReply;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDayCmt() {
		return dayCmt;
	}

	public void setDayCmt(String dayCmt) {
		this.dayCmt = dayCmt;
	}

	public int getSttCmt() {
		return sttCmt;
	}

	public void setSttCmt(int sttCmt) {
		this.sttCmt = sttCmt;
	}

	@Override
	public String toString() {
		return "replyComment [idBlog=" + idBlog + ", idcmt=" + idcmt + ", userNameCmt=" + userNameCmt
				+ ", userNameReply=" + userNameReply + ", message=" + message + ", dayCmt=" + dayCmt + ", sttCmt="
				+ sttCmt + "]";
	}
	public String getAvtUserName(ListAccount list) {
		Account user =list.findByUserName(getUserNameReply());
		if(user==null) {
			return null;
		}
		return user.getAvatar();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String fm = format.format(date);
		System.out.println(fm);
	}

}
