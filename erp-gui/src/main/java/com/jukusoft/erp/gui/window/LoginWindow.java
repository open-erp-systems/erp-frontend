package com.jukusoft.erp.gui.window;

import com.jukusoft.erp.gui.controller.LoginController;
import com.jukusoft.erp.gui.javafx.FXMLController;
import com.jukusoft.erp.gui.javafx.FXMLWindow;
import javafx.stage.Stage;

public class LoginWindow extends FXMLWindow {

    public LoginWindow(Stage stage, String title) {
        super(stage, title, 660, 440, "./data/fxml/login_window.fxml", new LoginController());
    }

}
