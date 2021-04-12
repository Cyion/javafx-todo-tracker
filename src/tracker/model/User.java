package tracker.model;

public class User {
	private final String email;
	private final String firstname;
	private final String lastname;
	
	public User(String email, String firstname, String lastname) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public String getLastname() {
		return this.lastname;
	}
}
