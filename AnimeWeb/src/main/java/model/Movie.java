package model;

import java.sql.SQLException;
import java.util.ArrayList;


import database.Rate;


public class Movie {
	private int idMovie;
	private String nameMovie;
	private String genre;
	private int currentEpisode;
	private int totalEpisodes;
	private int view;
	private String avatar;
	private ArrayList<Comment> listComment;
	private ArrayList<chapter> listchapter;
	private String description;
	public Movie(int idMovie, String nameMovie, String genre, int currentEpisode, int totalEpisodes, int view,
			String avatar, ArrayList<Comment> listComment, ArrayList<chapter> listchapter,String description) {
		super();
		this.idMovie = idMovie;
		this.nameMovie = nameMovie;
		this.genre = genre;
		this.currentEpisode = currentEpisode;
		this.totalEpisodes = totalEpisodes;
		this.view = view;
		this.avatar = avatar;
		this.listComment = listComment;
		this.listchapter = listchapter;
		this.description = description;
	}
	public Movie() {
		
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}

	public String getNameMovie() {
		return nameMovie;
	}

	public void setNameMovie(String nameMovie) {
		this.nameMovie = nameMovie;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getCurrentEpisode() {
		return currentEpisode;
	}

	public void setCurrentEpisode(int currentEpisode) {
		this.currentEpisode = currentEpisode;
	}

	public int getTotalEpisodes() {
		return totalEpisodes;
	}

	public void setTotalEpisodes(int totalEpisodes) {
		this.totalEpisodes = totalEpisodes;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public ArrayList<Comment> getListComment() {
		return listComment;
	}

	public void setListComment(ArrayList<Comment> listComment) {
		this.listComment = listComment;
	}

	public ArrayList<chapter> getListchapter() {
		return listchapter;
	}

	public void setListchapter(ArrayList<chapter> listchapter) {
		this.listchapter = listchapter;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Movie [idMovie=" + idMovie + ", nameMovie=" + nameMovie + ", genre=" + genre + ", currentEpisode="
				+ currentEpisode + ", totalEpisodes=" + totalEpisodes + ", view=" + view + ", avatar=" + avatar
				+ ", listComment=" + listComment + ", listchapter=" + listchapter + ", description=" + description
				+ "]";
	}
	public double getAvgScore() throws ClassNotFoundException, SQLException {
		Rate DBAO = new Rate();
		ArrayList<Integer> listRate=DBAO.getListRate(idMovie);
		model.Rate rate = new model.Rate(listRate);
		double avgScore =rate.averageScore();
		
		return (double) Math.ceil(avgScore * 10) / 10; 
	}
	public int voteTotal() throws ClassNotFoundException, SQLException {
		Rate DBAO = new Rate();
		ArrayList<Integer> listRate=DBAO.getListRate(idMovie);
		return listRate.size();
	}
public int getPerCent(double total) throws ClassNotFoundException, SQLException {
		
		return (int) ((view/total)*100);
		
	}




}
