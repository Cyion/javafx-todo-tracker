package tracker.model;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import java.util.Objects;

public class User {
	private final ReadOnlyStringWrapper email = new ReadOnlyStringWrapper(this, "email", "");
	private final ReadOnlyStringWrapper firstname = new ReadOnlyStringWrapper(this, "firstname", "");
	private final ReadOnlyStringWrapper lastname = new ReadOnlyStringWrapper(this, "lastname", "");
	
	public User(String email, String firstname, String lastname) {
		Objects.requireNonNull(email, "email");
		Objects.requireNonNull(firstname, "firstname");
		Objects.requireNonNull(lastname, "lastname");
		this.email.setValue(email);
		this.firstname.setValue(firstname);
		this.lastname.setValue(lastname);
	}

	public ReadOnlyStringProperty emailProperty() {
		return this.email.getReadOnlyProperty();
	}

	public String getEmail() {
		return this.email.getValue();
	}

	public ReadOnlyStringProperty firstnameProperty() {
		return this.firstname.getReadOnlyProperty();
	}

	public String getFirstname() {
		return this.firstname.getValue();
	}

	public ReadOnlyStringProperty lastnameProperty() {
		return this.lastname.getReadOnlyProperty();
	}

	public String getLastname() {
		return this.lastname.getValue();
	}
}
