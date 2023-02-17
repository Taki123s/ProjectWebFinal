package model;

import java.util.ArrayList;

public class Rate {
	ArrayList<Integer> listScore;
		public Rate(ArrayList<Integer> listScore) {
			this.listScore = listScore;
		}
		public ArrayList<Integer> getListScore() {
			return listScore;
		}
		public void setListScore(ArrayList<Integer> listScore) {
			this.listScore = listScore;
		}
		public double sumScore() {
			double rs =0;
			for(Integer i : listScore) {
				rs+=i;
			}
			return rs;
		}
		public double averageScore() {
			double sum=sumScore();
			return sum/listScore.size();
	
		}
	
}
