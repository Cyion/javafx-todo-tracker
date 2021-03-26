package tracker.application;

import javafx.application.Application;
import javafx.stage.Stage;
import tracker.database.DBUtil;
import tracker.controller.StageBuilderUtil;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		DBUtil.createTables();
		primaryStage.setScene(StageBuilderUtil.getLoginScene());
		primaryStage.setResizable(false);
		primaryStage.setTitle("ToDo Tracker");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
