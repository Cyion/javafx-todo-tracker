package tracker.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import tracker.view.LoginView;
import tracker.view.MainView;
import tracker.view.ToDoForm;
import tracker.view.NewUserForm;

public class StageBuilderUtil {
	
	private static Stage createStage(Scene scene, String title) {
		Stage stage = new Stage();
		stage.setTitle(title);
		stage.setResizable(false);
		stage.setScene(scene);
		return stage;
	}
	
	public static Scene getLoginScene() {
		return new Scene(new LoginView(), 500, 250);
	}
	
	public static Scene getMainScene() {
		return new Scene (new MainView(), 1600, 800);
	}
	
	public static Scene getUserFormScene() {
		final NewUserForm view = new NewUserForm();
		final NewUserFormController controller = new NewUserFormController(view);
		return new Scene (view, 500, 250);
	}
	
	public static Scene getToDoFormScene() {
		return new Scene (new ToDoForm(), 600, 600);
	}
	
	public static Stage getLoginStage() {
		return createStage(getLoginScene(), "ToDo Tracker");
	}
	
	public static Stage getMainStage() {
		return createStage(getMainScene(), "ToDo Tracker");
	}
	
	public static Stage getUserFormStage() {
		return createStage(getUserFormScene(), "Neuen Benutzer anlegen");
	}
	
	public static Stage getToDoFormStage() {
		return createStage(getToDoFormScene(), "Neues ToDo");
	}
}
