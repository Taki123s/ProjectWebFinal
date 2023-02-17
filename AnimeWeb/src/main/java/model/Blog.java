package model;

import java.util.ArrayList;


public class Blog {
	private int idBlog;
	private String title;
	private ArrayList<blogComment> listCmt;
	private String folder;
	private String datePost;
	private String avt;
	private String dayDebut;

	public Blog(int idBlog, String title, ArrayList<blogComment> listCmt, String folder, String datePost, String avt,
			String dayDebut) {
		super();
		this.idBlog = idBlog;
		this.title = title;
		this.listCmt = listCmt;
		this.folder = folder;
		this.datePost = datePost;
		this.avt = avt;
		this.dayDebut = dayDebut;

	}

	public String getDayDebut() {
		return dayDebut;
	}

	public void setDayDebut(String dayDebut) {
		this.dayDebut = dayDebut;
	}

	public String getAvt() {
		return avt;
	}

	public void setAvt(String avt) {
		this.avt = avt;
	}

	public int getIdBlog() {
		return idBlog;
	}

	public void setIdBlog(int idBlog) {
		this.idBlog = idBlog;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<blogComment> getListCmt() {
		return listCmt;
	}

	public void setListCmt(ArrayList<blogComment> listCmt) {
		this.listCmt = listCmt;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getDatePost() {
		return datePost;
	}

	public void setDatePost(String datePost) {
		this.datePost = datePost;
	}
	public int getTotalComment() {
		int rs=listCmt.size();
		for(blogComment bc : listCmt) {
			rs+=bc.getList().size();
		}
		
		return rs;
	}
	@Override
	public String toString() {
		return "Blog [idBlog=" + idBlog + ", title=" + title + ", listCmt=" + listCmt + ", folder=" + folder
				+ ", datePost=" + datePost + ", avt=" + avt + ", dayDebut=" + dayDebut + "]";
	}
	

	

}
