package model;

import java.util.ArrayList;



public class blogComment {
	private int idBlog;
	private int idCmt;
	private String mess;
	private String userName;
	private String dayCmt;
	private ArrayList<replyComment> list;
	public blogComment(int idBlog, int idCmt, String mess, String userName, String dayCmt,
			ArrayList<replyComment> list) {
		super();
		this.idBlog = idBlog;
		this.idCmt = idCmt;
		this.mess = mess;
		this.userName = userName;
		this.dayCmt = dayCmt;
		this.list = list;
	}
	public int getIdBlog() {
		return idBlog;
	}
	public void setIdBlog(int idBlog) {
		this.idBlog = idBlog;
	}
	public int getIdCmt() {
		return idCmt;
	}
	public void setIdCmt(int idCmt) {
		this.idCmt = idCmt;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDayCmt() {
		return dayCmt;
	}
	public void setDayCmt(String dayCmt) {
		this.dayCmt = dayCmt;
	}
	public ArrayList<replyComment> getList() {
		return list;
	}
	public void setList(ArrayList<replyComment> list) {
		this.list = list;
	}
	public String getAvtUserName(ListAccount list) {
		Account user =list.findByUserName(getUserName());
		if(user==null) {
			return null;
		}
		return user.getAvatar();
	}
	@Override
	public String toString() {
		return "blogComment [idBlog=" + idBlog + ", idCmt=" + idCmt + ", mess=" + mess + ", userName=" + userName
				+ ", dayCmt=" + dayCmt + ", list=" + list + "]";
	}
	
	

	
}
