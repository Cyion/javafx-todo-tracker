package tracker.controller;

import tracker.model.ToDo;
import tracker.util.DBUtil;
import tracker.view.ToDoView;

public class ToDoViewController {
    private final ToDoView view;
    private final ToDo todo;

    public ToDoViewController(ToDoView view, ToDo todo) {
        this.view = view;
        this.todo = todo;
        initialize();
    }

    public ToDoView getView() {
        return this.view;
    }

    public ToDo getTodo() {
        return this.todo;
    }

    private void initialize() {
        initializeListeners();
    }

    private void initializeListeners() {
        this.view.getUpdateButton().setOnAction(e -> updateTodo());
        this.view.getDeleteButton().setOnAction(e -> deleteTodo());
    }

    private void updateTodo() {
        final String title = this.view.getTitleField().getText();
        final String description = this.view.getDescriptionField().getText();
        if (this.todo != null && !title.isEmpty()) {
            this.todo.setTitle(title);
            this.todo.setDescription(description);
            if (this.view.getFinishedBox().isSelected()) {
                this.todo.setFinished();
            }
            DBUtil.updateTodo(this.todo);
        }
    }

    private void deleteTodo() {
        if (this.todo != null) {
            DBUtil.deleteTodo(this.todo);
        }
    }
}
