package com.jukusoft.erp.gui.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by Justin on 30.05.2017.
 */
public class FXMLWindow {

    //JavaFX stage means an window
    protected Stage stage = null;

    //JavaFX scene
    protected Scene scene = null;

    //root AnchorPane
    protected Pane rootPane = null;

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

        //initialize controller
        if (controller != null) {
            controller.init(stage, scene, rootPane);

            //run controller logic in new thread
            new Thread(controller::run).start();
        }

        //show window
        //stage.show();
    }

    public FXMLWindow (String title, int width, int height, String fxmlPath, FXMLController controller) {
        this(new Stage(), title, width, height, fxmlPath, controller);
    }

    public FXMLWindow (String title, int width, int height, String fxmlPath) {
        this(title, width, height, fxmlPath, null);
    }

    public Stage getStage () {
        return this.stage;
    }

    public void setVisible (boolean visible) {
        if (!visible) {
            this.stage.hide();
        } else {
            this.stage.show();
        }
    }

    public void setFullscreen (boolean fullscreen) {
        this.stage.setFullScreen(fullscreen);
    }

}
