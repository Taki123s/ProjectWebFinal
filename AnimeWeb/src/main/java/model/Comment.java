package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
	private String userName;
	private String comment;
	private int idMovie;
	private Account account;
	private String timeComment;
	public Comment(String userName, String comment, int idMovie,String timeComment) {
		super();
		this.userName = userName;
		this.comment = comment;
		this.idMovie = idMovie;
		this.timeComment=timeComment;
	}
	
	

	public String getTimeComment() {
		return timeComment;
	}



	public void setTimeComment(String timeComment) {
		this.timeComment = timeComment;
	}
	public long getLongTime() throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(timeComment);
		return date.getTime();
	}

	public String getDifferentTime(long nowTime) throws ParseException {
		long diff=nowTime-getLongTime();
		
		String year=(diff/(1000*60*60*24)/30/12)+"";
		String month=(diff/(1000*60*60*24)/30)+"";
		String day=(diff/(1000*60*60*24))+"";
		String hour=(diff/(1000*60*60))+"";
		String minutes=(diff/(1000*60))+"";
		String seconds=(diff/(1000))+"";
		if(Long.valueOf(year)>0) {
			return year+" năm";
		}
		if(Long.valueOf(month)>0) {
			return month+" tháng trước";
		}
		if(Long.valueOf(day)>0) {
			return day+" ngày trước";
		}
		if(Long.valueOf(hour)>0) {
			return hour+" giờ trước";
		}
		if(Long.valueOf(minutes)>0) {
			return minutes+" phút trước";
		}
		if(Long.valueOf(seconds)>0) {
			return seconds+" giây trước";
		}
		return "Vừa mới đây";
	}
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getIdMovie() {
		return idMovie;
	}
	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}
	public static void main(String[] args) throws ParseException {
	
	}
	
}
