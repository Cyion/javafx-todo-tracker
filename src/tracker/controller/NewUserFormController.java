package tracker.controller;

import javafx.stage.Stage;
import tracker.database.DBUtil;
import tracker.model.User;
import tracker.view.NewUserForm;

public class NewUserFormController {
    private final NewUserForm view;

    public NewUserFormController(NewUserForm view) {
        this.view = view;
        initialize();
    }

    public NewUserForm getView() {
        return this.view;
    }

    private void initialize() {
        initializeListeners();
    }

    private void initializeListeners() {
        this.view.getCreateButton().setOnAction(e -> createNewUser());
    }

    private void createNewUser() {
        if (isInputCorrect()) {
            final User user = new User(this.view.getEmailField().getText(), this.view.getFirstnameField().getText(), this.view.getLastnameField().getText());
            DBUtil.insertUser(user, this.view.getPasswordField().getText());
            ((Stage) this.view.getScene().getWindow()).close();
        }
    }

    private boolean isInputCorrect() {
        return isEmailCorrect() && isPasswordCorrect() && isFirstnameCorrect() && isLastnameCorrect();
    }

    private boolean isEmailCorrect() {
        return !this.view.getEmailField().getText().isEmpty();
    }

    private boolean isPasswordCorrect() {
        return !this.view.getPasswordField().getText().isEmpty();
    }

    private boolean isFirstnameCorrect() {
        return !this.view.getFirstnameField().getText().isEmpty();
    }

    private boolean isLastnameCorrect() {
        return !this.view.getLastnameField().getText().isEmpty();
    }
}
