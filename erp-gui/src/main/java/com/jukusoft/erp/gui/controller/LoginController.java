package com.jukusoft.erp.gui.controller;

import com.jukusoft.erp.gui.javafx.FXMLController;
import com.jukusoft.erp.network.manager.NetworkManager;
import com.jukusoft.erp.network.utils.NetworkResult;
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

        this.loginButton.setOnAction((event) -> {
            //get server ip and port
            String server = serverTextField.getText();
            String[] array = server.split(":");

            String serverIP = "";
            int port = 2200;

            if (array.length > 1) {
                serverIP = array[0];
                port = Integer.parseInt(array[1]);
            } else {
                serverIP = array[0];
            }

            //get network manager
            NetworkManager network = NetworkManager.getInstance();

            //check, if client is already connecting
            if (network.isConnecting()) {
                System.out.println("client is already connecting.");
                return;
            }

            //disable login button
            this.loginButton.setText("Connecting...");
            this.loginButton.setDisable(true);

            //try to connect
            NetworkManager.getInstance().connect(serverIP, port, (NetworkResult<Boolean> res) -> {
                if (!res.succeeded()) {
                    this.loginButton.setText("Login");
                    this.loginButton.setDisable(false);
                } else {
                    //try to login
                    System.out.println("login");
                }
            });
        });
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
