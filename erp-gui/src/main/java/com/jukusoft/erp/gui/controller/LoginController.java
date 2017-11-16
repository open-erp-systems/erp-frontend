package com.jukusoft.erp.gui.controller;

import com.jukusoft.erp.gui.javafx.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements FXMLController, Initializable {

    @FXML
    protected TextField serverTextField;

    @FXML
    protected TextField userTextField;

    @FXML
    protected PasswordField passwordTextField;

    @FXML
    protected Button loginButton;

    //default values
    protected String defaultServerIP = "";
    protected String defaultUsername = "";

    public LoginController (String defaultServerIP, String defaultUsername) {
        this.defaultServerIP = defaultServerIP;
        this.defaultUsername = defaultUsername;
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        //set default values to text fields
        this.serverTextField.setText(this.defaultServerIP);
        this.userTextField.setText(this.defaultUsername);
    }

    @Override
    public void run() {
        //
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

}
