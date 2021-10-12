package tracker.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import tracker.database.DBUtil;

import java.util.Objects;

public class Session {
	private final static Session instance = new Session();
	private final ReadOnlyObjectWrapper<User> user = new ReadOnlyObjectWrapper<>(this, "user", null);
	
	private Session() {
		
	}
	
	public static Session getInstance() {
		return instance;
	}
	
	public boolean login(String email, String password) {
		this.user.setValue(DBUtil.getUser(email, password));
		return Objects.nonNull(this.user.getValue());
	}

	public void logout() {
		this.user.setValue(null);
	}

	public ReadOnlyObjectProperty<User> userProperty() {
		return this.user.getReadOnlyProperty();
	}

	public User getUser() {
		return this.user.getValue();
	}
}
