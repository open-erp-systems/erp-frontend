package com.jukusoft.erp.gui.app;

import com.jukusoft.erp.gui.eventbus.EventBus;
import com.jukusoft.erp.gui.utils.JavaFXUtils;
import com.jukusoft.erp.gui.window.LoginWindow;
import com.jukusoft.erp.gui.window.MainWindow;
import com.jukusoft.erp.network.manager.NetworkManager;
import com.jukusoft.erp.network.user.Account;
import io.vertx.core.json.JsonObject;
import javafx.application.Application;
import javafx.application.Platform;
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

    //main window
    protected MainWindow mainWindow = null;

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

        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        //read default config
        this.readDefaultConfig();

        //initialize networking
        this.networkManager = NetworkManager.getInstance();
        this.networkManager.init();

        //create and show new login window
        this.loginWindow = new LoginWindow(this.primaryStage, "ERP System - Login, (c) 2017 JuKuSoft.com", defaultCfg.getOrDefault("serverIP", ""), defaultCfg.getOrDefault("user", ""), this::onLogin);
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

    protected void onLogin (Account account) {
        System.out.println("User '" + account.getUsername() + "' (userID: " + account.getUserID() + ") logged in successfully.");

        //hide login window
        this.loginWindow.setVisible(false);

        //redirect internal events to event bus
        NetworkManager.getInstance().addGlobalSubscriber(msg -> {
            if (msg.getEvent().startsWith("internal:")) {
                //call eventbus
                EventBus.getInstance().raiseEvent(msg);
            }
        });

        //add event listener for warnings notifications
        EventBus.getInstance().addListener("warning_notification", (event, eventData) -> {
            Platform.runLater(() -> {
                String title = eventData.getString("title");
                String text = eventData.getString("text");

                JavaFXUtils.showErrorDialog(title, text);
            });
        });

        //add event listener for important notifications
        EventBus.getInstance().addListener("info_notification", (event, eventData) -> {
            Platform.runLater(() -> {
                String title = eventData.getString("title");
                String text = eventData.getString("text");

                JavaFXUtils.showInfoDialog(title, text);
            });
        });

        //create and show new main window
        this.mainWindow = new MainWindow(this.primaryStage, "OS ERP System");
        this.mainWindow.setVisible(true);

    }

}
