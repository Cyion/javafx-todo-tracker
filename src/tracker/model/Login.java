package tracker.model;

import tracker.util.DBUtil;

public class Login {
	private static Login instance = new Login();
	private User user;
	
	private Login() {
		
	}
	
	public static Login getInstance() {
		return instance;
	}
	
	public boolean login(String email, String password) {
		this.user = DBUtil.getUser(email, password);
		return this.user != null;	
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void logout() {
		this.user = null;
	}


}
