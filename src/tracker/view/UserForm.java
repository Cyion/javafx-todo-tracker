package tracker.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tracker.database.DBUtil;

public class UserForm extends BorderPane{
	private final TextField emailField = new TextField();
	private final PasswordField passwordField = new PasswordField();
	private final TextField firstnameField = new TextField();
	private final TextField lastnameField = new TextField();
	private final Button createButton = new Button("Erstellen");
	
	public UserForm() {
		createView();
	}
	
	private void createView() {
		GridPane inputForm = new GridPane();
		inputForm.add(new Label("Email:"), 0, 0);
		inputForm.add(this.emailField, 1, 0);
		inputForm.add(new Label("Passwort:"), 0, 1);
		inputForm.add(this.passwordField, 1, 1);
		inputForm.add(new Label("Vorname:"), 0, 2);
		inputForm.add(this.firstnameField, 1, 2);
		inputForm.add(new Label("Nachname:"), 0, 3);
		inputForm.add(this.lastnameField, 1, 3);
		inputForm.getColumnConstraints().add(new ColumnConstraints(125));
		inputForm.setVgap(15);
		
		this.createButton.setPrefWidth(100);
		this.createButton.setOnAction(e -> {
			if (!this.emailField.getText().isEmpty() && !this.passwordField.getText().isEmpty() && !this.firstnameField.getText().isEmpty() && !this.lastnameField.getText().isEmpty()) {
				DBUtil.insertUser(this.emailField.getText(), this.passwordField.getText(), this.firstnameField.getText(), this.lastnameField.getText());
				((Stage) getScene().getWindow()).close();
			}
		});
		
		setCenter(inputForm);
		setBottom(this.createButton);
		setAlignment(this.createButton, Pos.CENTER);
		setPadding(new Insets(20));
	}
}
