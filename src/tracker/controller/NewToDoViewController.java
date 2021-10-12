package tracker.controller;

import tracker.model.Session;
import tracker.database.DBUtil;
import tracker.model.ToDo;
import tracker.util.StageBuilderUtil;
import tracker.view.NewToDoView;

import java.time.LocalDate;

public class NewToDoViewController {
    private final NewToDoView view;

    public NewToDoViewController(NewToDoView view) {
        this.view = view;
        initialize();
    }

    public NewToDoView getView() {
        return this.view;
    }

    private void initialize() {
        initializeListeners();
    }

    private void initializeListeners() {
        this.view.getCreateButton().setOnAction(e -> createToDo());
    }

    private void createToDo() {
        final String title = this.view.getTitleField().getText();
        final String description = this.view.getDescriptionField().getText();
        if (!title.isEmpty()) {
            final ToDo.Builder builder = new ToDo.Builder(-1, title, LocalDate.now(), Session.getInstance().getUser())
                    .setDescription(description);
            DBUtil.insertToDo(builder.build());
            StageBuilderUtil.closeStage(this.view);
        }
    }
}
