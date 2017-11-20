package com.jukusoft.erp.gui.window;

import com.jukusoft.erp.gui.controller.MainWindowController;
import com.jukusoft.erp.gui.javafx.FXMLController;
import com.jukusoft.erp.gui.javafx.FXMLWindow;
import javafx.stage.Stage;

public class MainWindow extends FXMLWindow {

    public MainWindow(Stage stage, String title) {
        super(stage, title, 800, 600, "./data/fxml/main_window.fxml", new MainWindowController());

        //set fullscreen
        this.setFullscreen(true);
    }

}
