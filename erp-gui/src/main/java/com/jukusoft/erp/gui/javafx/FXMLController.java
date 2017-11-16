package com.jukusoft.erp.gui.javafx;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Justin on 30.05.2017.
 */
public interface FXMLController extends Runnable, Initializable {

    public void init(Stage stage, Scene scene, Pane pane);

}
