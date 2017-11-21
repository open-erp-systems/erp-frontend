package com.jukusoft.erp.gui.controller;

import com.jukusoft.erp.gui.javafx.FXMLController;
import com.jukusoft.erp.gui.menu.MenuManager;
import com.jukusoft.erp.gui.menu.impl.DefaultMenuManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements FXMLController, Initializable {

    @FXML
    protected MenuBar menuBar;

    //menu manager
    protected MenuManager menuManager = null;

    protected static final int MAIN_MENU_ID = 1;

    /**
    * default constructor
    */
    public MainWindowController () {
        //create new menu manager
        this.menuManager = new DefaultMenuManager();
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        //initialize menu
        this.menuManager.fillMenu(this.menuBar, stage, MAIN_MENU_ID);
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
