package tracker.view;

import java.time.LocalDate;

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
import tracker.util.DBUtil;
import tracker.model.ToDo;

public class ToDoView extends VBox {
	private ToDo toDo;
	private final Button updateButton = new Button("Speichern");
	private final Button deleteButton = new Button("L�schen");
	private final TextField titleField = new TextField();
	private final TextArea descriptionField = new TextArea();
	private final Label startLabel = new Label();
	private final Label endLabel = new Label();
	private final CheckBox finished = new CheckBox();

	public ToDoView(ToDo toDo) {
		this.toDo = toDo;
		if (this.toDo != null) {
			this.titleField.setText(this.toDo.getTitle());
			this.descriptionField.setText(this.toDo.getDescription());
			this.startLabel.setText(this.toDo.getStartDate().toString());
			if (this.toDo.isFinished()) {
				this.deleteButton.setDisable(true);
				this.updateButton.setDisable(true);
				this.titleField.setEditable(false);
				this.descriptionField.setEditable(false);
				this.endLabel.setText(this.toDo.getFinishedDate().toString());
			}
		}
		createView();
	}

	private void createView() {
		setPadding(new Insets(20));
		setSpacing(350);
		GridPane todoGrid = new GridPane();
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
			todoGrid.add(this.endLabel, 1, 6);
		} else {
			todoGrid.add(this.finished, 1, 6);
		}
		todoGrid.setVgap(15);
		this.updateButton.setPrefWidth(100);
		this.updateButton.setOnAction(e -> {
			if (this.toDo != null && !this.titleField.getText().isEmpty()) {
				DBUtil.updateTodo(this.toDo.getId(), this.titleField.getText(), this.descriptionField.getText());
				if (this.finished.isSelected()) {
					DBUtil.updateTodoSetFinished(this.toDo.getId(), LocalDate.now());
				}
				((MainView) getParent()).updateList();
			}
		});
		this.deleteButton.setPrefWidth(100);
		this.deleteButton.setOnAction(e -> {
			if (this.toDo != null) {
				DBUtil.deleteTodo(this.toDo.getId());
				((MainView) getParent()).updateList();
			}
		});
		HBox controlBox = new HBox(this.updateButton, this.deleteButton);
		controlBox.setSpacing(50);
		controlBox.setAlignment(Pos.CENTER);
		getChildren().addAll(todoGrid, controlBox);
	}

}
