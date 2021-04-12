package tracker.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class LoginView extends BorderPane {
	private final TextField emailTextField = new TextField();
	private final PasswordField passwordTextField = new PasswordField();
	private final Button registerButton = new Button("Registrieren");
	private final Button loginButton = new Button("Login");

	public LoginView() {
		createView();
	}

	public TextField getEmailTextField() {
		return this.emailTextField;
	}

	public PasswordField getPasswordTextField() {
		return this.passwordTextField;
	}

	public Button getRegisterButton() {
		return this.registerButton;
	}

	public Button getLoginButton() {
		return this.loginButton;
	}

	private void createView() {
		final GridPane loginForm = new GridPane();
		loginForm.add(new Label("Email: "), 0, 0);
		loginForm.add(this.emailTextField, 1, 0);
		loginForm.add(new Label("Passwort: "), 0, 1);
		loginForm.add(this.passwordTextField, 1, 1);
		this.registerButton.setPrefWidth(100);
		loginForm.add(this.registerButton, 0, 2);
		this.loginButton.setPrefWidth(100);
		loginForm.add(this.loginButton, 1, 2);
		loginForm.setVgap(15);
		loginForm.setHgap(10);
		loginForm.setAlignment(Pos.CENTER);
		setCenter(loginForm);
	}
}
