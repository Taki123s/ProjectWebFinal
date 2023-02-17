package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class ListMovie {
	private ArrayList<Movie> listMovie;
	private ArrayList<String> listGenre;

	public ListMovie(ArrayList<Movie> listMovie) {
		super();
		this.listMovie = listMovie;
	}

	public ListMovie() {

	}

	public ArrayList<Movie> getListMovie() {
		return listMovie;
	}

	public void setListMovie(ArrayList<Movie> listMovie) {
		this.listMovie = listMovie;
	}

	public ArrayList<String> getListGenre() {
		return listGenre;
	}

	public void setListGenre(ArrayList<String> listGenre) {
		this.listGenre = listGenre;
	}

	public Movie findMovie(int idMovie) {
		for (Movie m : listMovie) {
			if (m.getIdMovie() == idMovie) {
				return m;
			}
		}
		return null;
	}

	public int beginOffset(ArrayList<Movie> list) {
		return list.size() / 9;
	}

	public int lastOffset(ArrayList<Movie> list) {
		return list.size() % 9;
	}

	public ArrayList<Movie> getMoviebyGenre(String Genre) {
		ArrayList<Movie> result = new ArrayList<>();
		ArrayList<Movie> listMv = sortMovieByView();
	
		for (Movie mv : listMv) {
			if (mv.getGenre().equalsIgnoreCase(Genre)) {
				result.add(mv);
			}
		}

		return result;
	}
	public int totalPage(ArrayList<Movie> list) {
		int begin = beginOffset(list);
		int last = lastOffset(list);
		int sumPage = begin;
		if(last!=0) sumPage++;
		return sumPage;
	}
	public ArrayList<Movie> getpageMovie(String offset, ArrayList<Movie> list) {
		int index = Integer.parseInt(offset);
		
		ArrayList<Movie> result = new ArrayList<>();
		int begin = beginOffset(list);
		int last = lastOffset(list);
		
		int sumPage = totalPage(list);
		if (index > sumPage) {
			return result;
		}
		if (index <= begin && index > 0) {
			int start = (index - 1) * 9;
			for (int i = start; i < start + 9; i++) {
				result.add(list.get(i));
			}
			return result;
		} else {
			int start = list.size() - last;
			for (int i = start; i < start + last; i++) {
				result.add(list.get(i));
			}
			return result;
		}
	}

	@Override
	public String toString() {
		String rs = "";
		for (Movie mv : listMovie) {
			rs += mv.toString();
		}
		return rs;
	}

	public double getTotalView() {
		double rs = 0.0;
		for (Movie mv : listMovie) {
			rs += mv.getView();
		}
		return rs;
	}

	public ArrayList<Movie> findMovie(String nameMovie) throws ClassNotFoundException, SQLException {
		ArrayList<Movie> result = new ArrayList<>();
		for (Movie mv : listMovie) {
			if (mv.getNameMovie().toLowerCase().contains(nameMovie.toLowerCase())) {
				result.add(mv);
			}
		}
		return result;

	}

	public ArrayList<Movie> sortMovieById() {
		ArrayList<Movie> clone = (ArrayList<Movie>) listMovie.clone();
		Collections.sort(clone, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				return o1.getIdMovie() > o2.getIdMovie() ? 1 : -1;
			}
		});
		return clone;
	}

	public ArrayList<Movie> sortMovieByView() {
		ArrayList<Movie> clone = (ArrayList<Movie>) listMovie.clone();
		Collections.sort(clone, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				return o1.getView() > o2.getView() ? -1 : 1;
			}
		});
		return clone;
	}

	public ArrayList<Movie> getTop5Movie() {
		ArrayList<Movie> list = sortMovieByView();
		ArrayList<Movie> rs = new ArrayList<>();
		if (list.size() >= 5) {
			for (int i = 0; i < 5; i++) {
				rs.add(list.get(i));
			}
			
			return rs;
		} else {
			for (Movie mv : list) {
				rs.add(mv);
			}
			return rs;
		}

	}

}
