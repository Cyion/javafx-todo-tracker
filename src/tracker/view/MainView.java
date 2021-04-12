package tracker.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tracker.model.Session;
import tracker.model.ToDo;

public class MainView extends BorderPane {
	private final ListView<ToDo> toDoListView = createListView();
	private final ListView<ToDo> finishedToDoListView = createListView();
	private final Button createButton = new Button("Neues ToDo erstellen");
	private final Button logoutButton = new Button("Logout");

	public MainView() {
		createView();
	}

	public ListView<ToDo> getToDoListView() {
		return this.toDoListView;
	}

	public ListView<ToDo> getFinishedToDoListView() {
		return this.finishedToDoListView;
	}

	public Button getCreateButton() {
		return this.createButton;
	}

	public Button getLogoutButton() {
		return this.logoutButton;
	}

	private void createView() {
		setTop(createTopView());
		setCenter(createCenterView());
	}
	
	private BorderPane createTopView() {
		final BorderPane topView = new BorderPane();
		topView.setPadding(new Insets(20));
		Label userLabel = new Label("Angemeldet: " + Session.getInstance().getUser().getEmail());
		final HBox userBox = new HBox(userLabel, this.logoutButton);
		userBox.setAlignment(Pos.CENTER_LEFT);
		userBox.setSpacing(20);
		topView.setLeft(userBox);
		return topView;
	}

	private BorderPane createCenterView() {
		final BorderPane centerView = new BorderPane();
		centerView.setPadding(new Insets(20));
		VBox listView = new VBox(new Label("ToDos:"), this.toDoListView, new Label("Beendet:"), this.finishedToDoListView);
		listView.setSpacing(20);
		centerView.setCenter(listView);
		HBox buttonView = new HBox(this.createButton);
		buttonView.setPadding(new Insets(20, 0, 0, 0));
		buttonView.setAlignment(Pos.CENTER);
		centerView.setBottom(buttonView);
		return centerView;
	}
	
	private ListView<ToDo> createListView() {
		final ListView<ToDo> listView = new ListView<>();
		listView.setPrefHeight(350);
		return listView;
	}
}
