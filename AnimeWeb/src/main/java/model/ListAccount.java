package model;


import java.util.ArrayList;


public class ListAccount {
	private ArrayList<Account> list;

	public ListAccount(ArrayList<Account> list) {
		super();
		this.list = list;
	}

	public ArrayList<Account> getList() {
		return list;
	}

	public void setList(ArrayList<Account> list) {
		this.list = list;
	}
	public Account findByUserName(String userName) {
		for(Account acc : list) {
			if(acc.getUserName().equals(userName)) {
				return acc;
			}
		}
		return null;
	}
	public Account checkLogin(String userName, String passWord) {
		for (Account ac : list) {
			if (ac.getUserName().equalsIgnoreCase(userName) && ac.getPassWord().equals(passWord)) {
				return ac;
			}
		}
		return null;
	}
	public Account checkAdmin(String userName, String passWord) {
		for (Account ac : list) {
			if (ac.getUserName().equalsIgnoreCase(userName) && ac.getPassWord().equals(passWord)&&ac.getIsAdmin()==1) {
				return ac;
			}
		}
		return null;
	}
	public boolean checkRegister(String userName){
		
	
		if(findByUserName(userName)!=null) {
			return false;
		}
	

		return true;
	}
	public boolean validateLogin(String userName,String passWord) {
		if(userName.equals("") || passWord.equals("")) {
			return false;
		}
		return true;
	}
}
