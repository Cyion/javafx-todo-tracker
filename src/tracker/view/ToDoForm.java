package tracker.view;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tracker.database.DBUtil;
import tracker.model.Login;

public class ToDoForm extends BorderPane {
	private final TextField titleField = new TextField();
	private final TextArea descriptionField = new TextArea();
	private final Button createButton = new Button("Erstellen");
	
	public ToDoForm() {
		createView();
	}

	private void createView() {
		GridPane inputForm = new GridPane();
		inputForm.add(new Label("Titel:"), 0, 0);
		inputForm.add(this.titleField, 1, 0);
		inputForm.add(new Label("Beschreibung:"), 0, 2);
		inputForm.add(this.descriptionField, 1, 2);
		inputForm.getColumnConstraints().add(new ColumnConstraints(125));
		inputForm.setVgap(15);
		
		this.createButton.setOnAction(e -> {
			if (!titleField.getText().isEmpty()) {
				DBUtil.insertToDo(this.titleField.getText(), this.descriptionField.getText(), LocalDate.now(), Login.getInstance().getUser());
				((Stage) getScene().getWindow()).close();
			}
		});
		
		setCenter(inputForm);
		setBottom(this.createButton);
		setAlignment(this.createButton, Pos.CENTER);
		setPadding(new Insets(20));
	}

	public TextField getTitleField() {
		return this.titleField;
	}

	public TextArea getDescriptionField() {
		return this.descriptionField;
	}

	public Button getCreateButton() {
		return this.createButton;
	}
}
