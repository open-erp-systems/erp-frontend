package com.jukusoft.erp.gui.app;

import com.jukusoft.erp.gui.utils.FileUtils;
import com.jukusoft.erp.gui.window.LoginWindow;
import io.vertx.core.json.JsonObject;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Justin on 29.08.2017.
 */
public class JavaFXApplication extends Application {

    //primary stage
    protected Stage primaryStage = null;

    //login window
    protected LoginWindow loginWindow = null;

    protected JsonObject defaultCfg = null;

    public JavaFXApplication() {
        //
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //save primary stage
        this.primaryStage = primaryStage;

        //read default config
        this.readDefaultConfig();

        //create and show new login window
        this.loginWindow = new LoginWindow(this.primaryStage, "ERP System - Login    (c) 2017 JuKuSoft.com", defaultCfg.getString("serverIP", ""), defaultCfg.getString("user", ""));
        this.loginWindow.setVisible(true);
    }

    protected void readDefaultConfig () {
        try {
            String jsonStr = FileUtils.readFile("./config/default.cfg", StandardCharsets.UTF_8);
            this.defaultCfg = new JsonObject(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
            this.defaultCfg = new JsonObject();
        }
    }

}
