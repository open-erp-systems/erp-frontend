package com.jukusoft.erp.gui.menu;

import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public interface MenuManager {

    /**
    * create menu
    */
    public void fillMenu (MenuBar menuBar, Stage stage, int menuID);

}
