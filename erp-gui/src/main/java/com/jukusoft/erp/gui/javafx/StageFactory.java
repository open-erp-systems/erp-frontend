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
public class StageFactory {

    public static Stage createStage (String title, int width, int height, String fxmlPath) throws IOException {
        //create new stage
        Stage stage = new Stage();

        //set title
        stage.setTitle(title);

        //set width & height
        stage.setWidth(width);
        stage.setHeight(height);

        // load fxml
        Pane pane = FXMLLoader.load(new File(fxmlPath).toURI().toURL());

        //add scene
        Scene scene = new Scene(pane);

        //set scene
        stage.setScene(scene);

        //show window
        stage.show();

        return stage;
    }

}
