package tracker.controller;

import tracker.model.Login;
import tracker.util.StageBuilderUtil;
import tracker.view.LoginView;

public class LoginViewController {
    private final LoginView view;

    public LoginViewController(LoginView view) {
        this.view = view;
        initialize();
    }

    public LoginView getView() {
        return this.view;
    }

    private void initialize() {
        initializeListeners();
    }

    private void initializeListeners() {
        this.view.getRegisterButton().setOnAction(e -> StageBuilderUtil.getUserFormStage().showAndWait());

        this.view.getLoginButton().setOnAction(e -> login());
    }

    private void login() {
        final String email = this.view.getEmailTextField().getText();
        final String password = this.view.getPasswordTextField().getText();

        if (!email.isEmpty() && !password.isEmpty()) {
            if (Login.getInstance().login(email, password)) {
                StageBuilderUtil.getMainStage().show();
                StageBuilderUtil.closeStage(this.view);
            }
        }
    }
}
