package tracker.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tracker.model.ToDo;

import java.util.Objects;

public class ToDoView extends VBox {
	private ToDo toDo;
	private final Button updateButton = new Button("Speichern");
	private final Button deleteButton = new Button("Löschen");
	private final TextField titleField = new TextField();
	private final TextArea descriptionField = new TextArea();
	private final Label startLabel = new Label();
	private final Label finishedLabel = new Label();
	private final CheckBox finishedBox = new CheckBox();

	public ToDoView(ToDo toDo) {
		this.toDo = toDo;
		createView();
	}

	public Button getUpdateButton() {
		return this.updateButton;
	}

	public Button getDeleteButton() {
		return  this.deleteButton;
	}

	public TextField getTitleField() {
		return this.titleField;
	}

	public TextArea getDescriptionField() {
		return this.descriptionField;
	}

	public Label getStartLabel() {
		return this.startLabel;
	}

	public Label getFinishedLabel() {
		return this.finishedLabel;
	}

	public CheckBox getFinishedBox() {
		return this.finishedBox;
	}

	private void createView() {
		initViewComponents();

		setPadding(new Insets(20));
		setSpacing(350);
		final GridPane todoGrid = new GridPane();
		todoGrid.getColumnConstraints().add(new ColumnConstraints(125));
		todoGrid.getColumnConstraints().add(new ColumnConstraints(400));
		todoGrid.add(new Label("Titel:"), 0, 0);
		todoGrid.add(this.titleField, 1, 0);
		todoGrid.add(new Label("Beschreibung:"), 0, 2);
		todoGrid.add(this.descriptionField, 1, 2);
		todoGrid.add(new Label("Start:"), 0, 4);
		todoGrid.add(this.startLabel, 1, 4);
		todoGrid.add(new Label("Beendet:"), 0, 6);
		if (this.toDo != null && this.toDo.isFinished()) {
			todoGrid.add(this.finishedLabel, 1, 6);
		} else {
			todoGrid.add(this.finishedBox, 1, 6);
		}
		todoGrid.setVgap(15);
		this.updateButton.setPrefWidth(100);
		this.deleteButton.setPrefWidth(100);
		final HBox controlBox = new HBox(this.updateButton, this.deleteButton);
		controlBox.setSpacing(50);
		controlBox.setAlignment(Pos.CENTER);
		getChildren().addAll(todoGrid, controlBox);
	}

	private void initViewComponents() {
		if (Objects.nonNull(this.toDo)) {
			this.titleField.setText(this.toDo.getTitle());
			this.descriptionField.setText(this.toDo.getDescription());
			this.startLabel.setText(this.toDo.getStartDate().toString());

			if (this.toDo.isFinished()) {
				disableViewComponents();
				this.finishedLabel.setText(this.toDo.getFinishedDate().toString());
			}
		} else {
			disableViewComponents();
		}
	}

	private void disableViewComponents() {
		this.deleteButton.setDisable(true);
		this.updateButton.setDisable(true);
		this.titleField.setEditable(false);
		this.descriptionField.setEditable(false);
		this.finishedBox.setDisable(true);
	}
}
