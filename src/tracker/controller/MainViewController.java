package tracker.controller;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import tracker.model.Session;
import tracker.model.ToDo;
import tracker.database.DBUtil;
import tracker.util.StageBuilderUtil;
import tracker.view.MainView;
import tracker.view.ToDoView;

public class MainViewController {
    private final MainView view;

    public MainViewController(MainView view) {
        this.view = view;
        initialize();
    }

    public MainView getView() {
        return this.view;
    }

    private void initialize() {
        initializeListeners();
        updateLists();
    }

    private void initializeListeners() {
        this.view.getLogoutButton().setOnAction(e -> logout());
        this.view.getCreateButton().setOnAction(e -> createToDo());
        this.view.getToDoListView()
                .focusedProperty()
                .addListener((observable, oldFocus, newFocus) -> checkFocus(this.view.getToDoListView(), newFocus));
        this.view.getToDoListView()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> displayToDo(newValue));
        this.view.getFinishedToDoListView()
                .focusedProperty()
                .addListener((observable, oldFocus, newFocus) -> checkFocus(this.view.getFinishedToDoListView(), newFocus));
        this.view.getFinishedToDoListView()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> displayToDo(newValue));
    }

    private void logout() {
        Session.getInstance().logout();
        StageBuilderUtil.getLoginStage().show();
        StageBuilderUtil.closeStage(this.view);
    }

    private void createToDo() {
        StageBuilderUtil.getToDoFormStage().showAndWait();
        updateLists();
    }

    private void displayToDo(ToDo todo) {
        final ToDoView view = new ToDoView(todo);
        final ToDoViewController controller = new ToDoViewController(view, todo);
        view.getUpdateButton().setOnMouseClicked(e -> updateLists());
        view.getDeleteButton().setOnMouseClicked(e -> updateLists());
        this.view.setRight(view);
    }

    private void checkFocus(ListView<ToDo> listView, boolean focus) {
        if (focus) {
            displayToDo(listView.getSelectionModel().getSelectedItem());
        }
    }

    public void updateLists() {
        this.view.getToDoListView().setItems(FXCollections.observableArrayList(DBUtil.getAllUnfinishedTodosByUser(Session.getInstance().getUser())));
        this.view.getFinishedToDoListView().setItems(FXCollections.observableArrayList(DBUtil.getAllFinishedTodosByUser(Session.getInstance().getUser())));
    }
}
