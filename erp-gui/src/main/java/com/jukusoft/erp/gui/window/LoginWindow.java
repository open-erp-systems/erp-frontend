package com.jukusoft.erp.gui.window;

import com.jukusoft.erp.gui.controller.LoginController;
import com.jukusoft.erp.gui.javafx.FXMLController;
import com.jukusoft.erp.gui.javafx.FXMLWindow;
import com.jukusoft.erp.network.user.Account;
import com.jukusoft.erp.network.utils.Callback;
import javafx.stage.Stage;

public class LoginWindow extends FXMLWindow {

    public LoginWindow(Stage stage, String title, String defaultServer, String defaultUsername, Callback<Account> loginCallback) {
        super(stage, title, 660, 440, "./data/fxml/login_window.fxml", new LoginController(defaultServer, defaultUsername, loginCallback));
    }

}
