package tracker.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tracker.controller.ToDoViewController;
import tracker.util.StageBuilderUtil;
import tracker.util.DBUtil;
import tracker.model.Login;
import tracker.model.ToDo;

public class MainView extends BorderPane {
	private final ListView<ToDo> toDoList = createListView();
	private final ListView<ToDo> finishedToDoList = createListView();
	private final Button createButton = new Button("Neues ToDo erstellen");
	private final Button logoutButton = new Button("Logout");

	public MainView() {
		createView();
		updateList();
	}

	private void createView() {
		setCenter(createCenterView());
		setTop(createTopView());
	}
	
	private BorderPane createTopView() {
		BorderPane topView = new BorderPane();
		topView.setPadding(new Insets(20));
		Label userLabel = new Label("Angemeldet: " + Login.getInstance().getUser().getEmail());
		this.logoutButton.setOnAction(e -> {
			Login.getInstance().logout();
			StageBuilderUtil.getLoginStage().show();
			((Stage) getScene().getWindow()).close();
		});
		HBox userBox = new HBox(userLabel, this.logoutButton);
		userBox.setAlignment(Pos.CENTER_LEFT);
		userBox.setSpacing(20);
		topView.setLeft(userBox);
		return topView;
	}

	private BorderPane createCenterView() {
		BorderPane centerView = new BorderPane();
		centerView.setPadding(new Insets(20));
		VBox listView = new VBox(new Label("ToDos:"), this.toDoList, new Label("Beendet:"), this.finishedToDoList);
		listView.setSpacing(20);
		centerView.setCenter(listView);
		this.createButton.setOnAction(e -> {
			StageBuilderUtil.getToDoFormStage().showAndWait();
			updateList();
		});
		HBox buttonView = new HBox(this.createButton);
		buttonView.setPadding(new Insets(20, 0, 0, 0));
		buttonView.setAlignment(Pos.CENTER);
		centerView.setBottom(buttonView);
		return centerView;
	}
	
	private ListView<ToDo> createListView() {
		ListView<ToDo> listView = new ListView<>();
		listView.setPrefHeight(350);
		listView.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
			if (newFocus) {
				ToDo todo = listView.getSelectionModel().getSelectedItem();
				ToDoView view = new ToDoView(todo);
				ToDoViewController controller = new ToDoViewController(view, todo);
				setRight(view);
			}
		});
		listView.getSelectionModel().selectedItemProperty().addListener((observable, oldTodo, newTodo) -> {
			ToDoView view = new ToDoView(newTodo);
			ToDoViewController controller = new ToDoViewController(view, newTodo);
			setRight(view);
		});
		return listView;
	}

	public void updateList() {
		this.toDoList.setItems(DBUtil.getAllUnfinishedTodosByUser(Login.getInstance().getUser()));
		this.finishedToDoList.setItems(DBUtil.getAllFinishedTodosByUser(Login.getInstance().getUser()));
	}

}
