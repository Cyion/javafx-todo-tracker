package tracker.util;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import tracker.controller.LoginViewController;
import tracker.controller.NewToDoViewController;
import tracker.controller.NewUserViewController;
import tracker.view.LoginView;
import tracker.view.MainView;
import tracker.view.NewToDoView;
import tracker.view.NewUserView;

public class StageBuilderUtil {

	public static Scene getLoginScene() {
		final LoginView view = new LoginView();
		final LoginViewController controller = new LoginViewController(view);
		return new Scene(view, 500, 250);
	}

	public static Scene getMainScene() {
		return new Scene (new MainView(), 1600, 800);
	}

	public static Scene getUserFormScene() {
		final NewUserView view = new NewUserView();
		final NewUserViewController controller = new NewUserViewController(view);
		return new Scene (view, 500, 250);
	}

	public static Scene getToDoFormScene() {
		final NewToDoView view = new NewToDoView();
		final NewToDoViewController controller = new NewToDoViewController(view);
		return new Scene (view, 600, 600);
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

	public static void closeStage(Node node) {
		final Window window = node.getScene().getWindow();
		if (window instanceof Stage) {
			((Stage) window).close();
		}
	}

	private static Stage createStage(Scene scene, String title) {
		final Stage stage = new Stage();
		stage.setTitle(title);
		stage.setResizable(false);
		stage.setScene(scene);
		return stage;
	}
}
