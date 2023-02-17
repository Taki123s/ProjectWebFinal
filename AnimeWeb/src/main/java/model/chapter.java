package model;

public class chapter {
	private int idMovie;
	private int index;
	private int opening;
	public chapter(int idMovie, int index,int opening) {
		super();
		this.idMovie = idMovie;
		this.index = index;
		this.opening = opening;
	}
	public int getIdMovie() {
		return idMovie;
	}
	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getOpening() {
		return opening;
	}
	public void setOpening(int opening) {
		this.opening = opening;
	}
	@Override
	public String toString() {
		return "chapter [idMovie=" + idMovie + ", index=" + index + "]";
	}
	

}
