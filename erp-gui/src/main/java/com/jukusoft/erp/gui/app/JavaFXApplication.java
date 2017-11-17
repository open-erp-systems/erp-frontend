package com.jukusoft.erp.gui.app;

import com.jukusoft.erp.gui.window.LoginWindow;
import com.jukusoft.erp.network.manager.NetworkManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.File;
import java.io.IOException;

/**
 * Created by Justin on 29.08.2017.
 */
public class JavaFXApplication extends Application {

    //primary stage
    protected Stage primaryStage = null;

    //login window
    protected LoginWindow loginWindow = null;

    //default configuration
    protected Ini ini = new Ini();
    protected Profile.Section defaultCfg = null;

    //network manager
    protected NetworkManager networkManager = null;

    /**
    * default constructor
    */
    public JavaFXApplication() {
        //
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //save primary stage
        this.primaryStage = primaryStage;

        //read default config
        this.readDefaultConfig();

        //initialize networking
        this.networkManager = NetworkManager.getInstance();
        this.networkManager.init();

        //create and show new login window
        this.loginWindow = new LoginWindow(this.primaryStage, "ERP System - Login, (c) 2017 JuKuSoft.com", defaultCfg.getOrDefault("serverIP", ""), defaultCfg.getOrDefault("user", ""));
        this.loginWindow.setVisible(true);
    }

    /**
    * read default login values from configuration
    */
    protected void readDefaultConfig () {
        try {
            this.ini = new Ini(new File("./config/default.cfg"));
            this.defaultCfg = this.ini.get("Default");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
