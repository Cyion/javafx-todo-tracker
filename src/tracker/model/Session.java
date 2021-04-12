package tracker.model;

import tracker.util.DBUtil;

public class Session {
	private final static Session instance = new Session();
	private User user;
	
	private Session() {
		
	}
	
	public static Session getInstance() {
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
