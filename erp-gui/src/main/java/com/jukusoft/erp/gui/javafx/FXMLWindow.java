package com.jukusoft.erp.gui.javafx;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 30.05.2017.
 */
public class FXMLWindow implements Window {

    //JavaFX stage means an window
    protected Stage stage = null;

    //JavaFX scene
    protected Scene scene = null;

    //root AnchorPane
    protected Pane rootPane = null;

    //window ID (set by window manager)
    protected long windowID = 0;

    //controller
    protected FXMLController controller = null;

    //list with OnCloseRequestHandler
    protected List<EventHandler<WindowEvent>> closeHandlerList = new ArrayList<>();

    public FXMLWindow (Stage stage, String title, int width, int height, String fxmlPath, FXMLController controller) {
        this.stage = stage;

        //set title
        stage.setTitle(title);

        //set width & height
        stage.setWidth(width);
        stage.setHeight(height);

        // load fxml
        try {
            FXMLLoader loader = new FXMLLoader(new File(fxmlPath).toURI().toURL());

            //set controller
            if (controller != null) {
                loader.setController(controller);
            }

            rootPane = loader.load();//FXMLLoader.load(new File(fxmlPath).toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //add scene
        this.scene = new Scene(this.rootPane);

        //set scene
        stage.setScene(scene);

        //set controller
        this.controller = controller;

        //initialize controller
        if (controller != null) {
            controller.init(stage, scene, rootPane);

            //run controller logic in new thread
            new Thread(controller::run).start();
        }

        //show window
        //stage.show();

        //set on close request
        this.stage.setOnCloseRequest(event -> {
            //call all listeners
            this.closeHandlerList.forEach(entry -> {
                entry.handle(event);
            });
        });
    }

    public FXMLWindow (String title, int width, int height, String fxmlPath, FXMLController controller) {
        this(new Stage(), title, width, height, fxmlPath, controller);
    }

    public FXMLWindow (String title, int width, int height, String fxmlPath) {
        this(title, width, height, fxmlPath, null);
    }

    @Override
    public Stage getStage () {
        return this.stage;
    }

    @Override
    public Pane getRootPane () {
        return this.rootPane;
    }

    @Override
    public boolean isVisible () {
        return this.stage.isShowing();
    }

    @Override
    public void setVisible (boolean visible) {
        if (!visible) {
            this.stage.hide();
        } else {
            this.stage.show();
        }
    }

    @Override
    public String getTitle() {
        return this.stage.getTitle();
    }

    @Override
    public void setTitle(String title) {
        this.stage.setTitle(title);
    }

    @Override
    public boolean isMaximized() {
        return this.stage.isMaximized();
    }

    @Override
    public void setMaximized(boolean maximized) {
        this.stage.setMaximized(maximized);
    }

    @Override
    public boolean isFullscreen() {
        return this.stage.isFullScreen();
    }

    @Override
    public void setFullscreen (boolean fullscreen) {
        this.stage.setFullScreen(fullscreen);
    }

    @Override
    public void addOnCloseRequestHandler(EventHandler<WindowEvent> handler) {
        this.closeHandlerList.add(handler);
    }

    public void removeOnCloseRequestHandler (EventHandler<WindowEvent> handler) {
        this.closeHandlerList.remove(handler);
    }

    public FXMLController getController() {
        return this.controller;
    }

    @Override
    public long getWindowID() {
        if (this.windowID <= 0) {
            throw new IllegalStateException("register window to window manager first, before use windowID.");
        }

        return this.windowID;
    }

    @Override
    public void setWindowID(long windowID) {
        this.windowID = windowID;
    }

}
