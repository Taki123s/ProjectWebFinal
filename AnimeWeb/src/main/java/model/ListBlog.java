package model;

import java.util.ArrayList;

public class ListBlog {
	private ArrayList<Blog> list;

	public ListBlog(ArrayList<Blog> list) {
		super();
		this.list = list;
	}

	public ArrayList<Blog> getList() {
		return list;
	}

	public void setList(ArrayList<Blog> list) {
		this.list = list;
	}
	public String toString() {
		String rs="";
		for(Blog bl : list) {
			rs+=bl.toString()+"\n";
		}
		return rs;
	}
	public int getColumn() {
		return list.size()/6;
	}
	public int getLastColumn() {
		
		return list.size()%6;
	}
	public ArrayList<Blog> getbyColumn(int index){
		int value;
		ArrayList<Blog> result = new ArrayList<>();
		int max=index*6+6;
		for(value=index*6;value<max;value++) {
			result.add(list.get(value));
		}
		return result;
	}
	public ArrayList<Blog> getbyLastColumn(int size){
		int value;
		ArrayList<Blog> result = new ArrayList<>();
		if(size<=0) {
			return result;
		}else {
			for(value=list.size()-size;value<list.size();value++) {
				result.add(list.get(value));
			}
		}
		return result;
	}
	public Blog findBlogById(int id) {
		for(Blog blog : list) {
			if(blog.getIdBlog()==id) {
				return blog;
			}
		}
		return null;
	}


}
