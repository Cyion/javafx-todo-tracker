package tracker.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class NewToDoView extends BorderPane {
	private final TextField titleField = new TextField();
	private final TextArea descriptionField = new TextArea();
	private final Button createButton = new Button("Erstellen");
	
	public NewToDoView() {
		createView();
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

	private void createView() {
		final GridPane inputForm = new GridPane();
		inputForm.add(new Label("Titel:"), 0, 0);
		inputForm.add(this.titleField, 1, 0);
		inputForm.add(new Label("Beschreibung:"), 0, 2);
		inputForm.add(this.descriptionField, 1, 2);
		inputForm.getColumnConstraints().add(new ColumnConstraints(125));
		inputForm.setVgap(15);
		setCenter(inputForm);
		setBottom(this.createButton);
		setAlignment(this.createButton, Pos.CENTER);
		setPadding(new Insets(20));
	}
}
