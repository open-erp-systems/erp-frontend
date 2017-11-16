package com.jukusoft.erp.gui.javafx;

import com.jukusoft.erp.gui.window.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Justin on 29.08.2017.
 */
public class JavaFXApplication extends Application {

    //primary stage
    protected Stage primaryStage = null;

    //login window
    protected LoginWindow loginWindow = null;

    public JavaFXApplication() {
        //
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //save primary stage
        this.primaryStage = primaryStage;

        //create new login window
        this.loginWindow = new LoginWindow(this.primaryStage, "ERP System - Login    (c) 2017 JuKuSoft.com");
        this.loginWindow.setVisible(true);
    }

}
