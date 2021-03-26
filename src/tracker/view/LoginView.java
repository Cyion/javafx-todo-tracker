package tracker.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tracker.util.StageBuilderUtil;
import tracker.model.Login;

public class LoginView extends BorderPane {
	private final TextField emailInput = new TextField();
	private final PasswordField passwordInput = new PasswordField();
	private final Button registerButton = new Button("Registrieren");
	private final Button loginButton = new Button("Login");

	public LoginView() {
		createView();
		addActionListeners();
	}

	private void createView() {
		GridPane loginForm = new GridPane();
		loginForm.add(new Label("Email: "), 0, 0);
		loginForm.add(this.emailInput, 1, 0);
		loginForm.add(new Label("Passwort: "), 0, 1);
		loginForm.add(this.passwordInput, 1, 1);
		this.registerButton.setPrefWidth(100);
		loginForm.add(this.registerButton, 0, 2);
		this.loginButton.setPrefWidth(100);
		loginForm.add(this.loginButton, 1, 2);
		loginForm.setVgap(15);
		loginForm.setHgap(10);
		loginForm.setAlignment(Pos.CENTER);
		setCenter(loginForm);
	}

	private void addActionListeners() {
		this.registerButton.setOnAction(e -> {
			StageBuilderUtil.getUserFormStage().showAndWait();
		});

		this.loginButton.setOnAction(e -> {
			if (!this.emailInput.getText().isEmpty() && !this.passwordInput.getText().isEmpty()) {
				if (Login.getInstance().login(this.emailInput.getText(), this.passwordInput.getText())) {
					StageBuilderUtil.getMainStage().show();
					((Stage) getScene().getWindow()).close();
				}
			}
		});
	}
}
