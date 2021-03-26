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

public class NewUserForm extends BorderPane {
	private final TextField emailField = new TextField();
	private final PasswordField passwordField = new PasswordField();
	private final TextField firstnameField = new TextField();
	private final TextField lastnameField = new TextField();
	private final Button createButton = new Button("Erstellen");
	
	public NewUserForm() {
		createView();
	}

	public TextField getEmailField() {
		return this.emailField;
	}

	public PasswordField getPasswordField() {
		return this.passwordField;
	}

	public TextField getFirstnameField() {
		return this.firstnameField;
	}

	public TextField getLastnameField() {
		return this.lastnameField;
	}

	public Button getCreateButton() {
		return this.createButton;
	}
	
	private void createView() {
		final GridPane inputForm = new GridPane();
		inputForm.add(new Label("Email:"), 0, 0);
		inputForm.add(this.emailField, 1, 0);
		inputForm.add(new Label("Passwort:"), 0, 1);
		inputForm.add(this.passwordField, 1, 1);
		inputForm.add(new Label("Vorname:"), 0, 2);
		inputForm.add(this.firstnameField, 1, 2);
		inputForm.add(new Label("Nachname:"), 0, 3);
		inputForm.add(this.lastnameField, 1, 3);
		inputForm.getColumnConstraints().add(new ColumnConstraints(125));
		inputForm.getColumnConstraints().add(new ColumnConstraints(300));
		inputForm.setVgap(15);
		inputForm.setAlignment(Pos.TOP_CENTER);
		
		this.createButton.setPrefWidth(100);
		
		setCenter(inputForm);
		setBottom(this.createButton);
		setAlignment(this.createButton, Pos.CENTER);
		setPadding(new Insets(20));
	}
}
