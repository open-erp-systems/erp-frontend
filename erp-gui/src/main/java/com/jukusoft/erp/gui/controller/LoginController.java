package com.jukusoft.erp.gui.controller;

import com.jukusoft.erp.gui.javafx.FXMLController;
import com.jukusoft.erp.network.manager.NetworkManager;
import com.jukusoft.erp.network.utils.NetworkResult;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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

    @FXML
    protected Label errorTextLabel;

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

        this.serverTextField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                connect();
            }
        });

        this.userTextField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                connect();
            }
        });

        this.passwordTextField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                connect();
            }
        });

        this.loginButton.setOnAction((event) -> {
            connect();
        });

        this.errorTextLabel.setVisible(false);
    }

    protected void connect () {
        //hide old error text first
        this.errorTextLabel.setVisible(false);

        //get network manager
        NetworkManager network = NetworkManager.getInstance();

        //disable login button
        disableButton();

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

        //get username and password
        String username = this.userTextField.getText();
        String password = this.passwordTextField.getText();

        //check, if client is already connecting
        if (network.isConnecting()) {
            System.out.println("client is already connecting.");
            return;
        }

        final String serverIP1 = serverIP;
        final int port1 = port;

        //execute in network thread
        network.executeBlocking(() -> {
            //try to connect
            network.connect(serverIP1, port1, (NetworkResult<Boolean> res) -> {
                Platform.runLater(() -> {
                    if (!res.succeeded()) {
                        enableButton();

                        String message = res.cause().getMessage();
                        String array1[] = message.split(":");
                        message = array1[0];

                        if (message.contains("Connection refused")) {
                            message = "Server isn't available!";
                        }

                        errorTextLabel.setText("Error: " + message);
                        errorTextLabel.setVisible(true);
                    } else {
                        //try to login
                        System.out.println("login");

                        loginButton.setText("Login...");

                        network.login(username, password, res1 -> {
                            Platform.runLater(() -> {
                                if (!res1.succeeded()) {
                                    System.out.println("Couldnt login! " + res1.cause().getMessage());

                                    errorTextLabel.setText("Error: " + res1.cause().getMessage());
                                    errorTextLabel.setVisible(true);

                                    enableButton();
                                } else {
                                    System.out.println("Login successfully!");

                                    //TODO: go to next window

                                    //hide login button
                                    this.loginButton.setVisible(false);
                                }
                            });
                        });

                        //hide login button
                        //this.loginButton.setVisible(false);
                        this.errorTextLabel.setVisible(false);
                    }
                });
            });
        });
    }

    protected void disableButton () {
        //disable login button
        this.loginButton.setText("Connecting...");
        this.loginButton.setDisable(true);
    }

    protected void enableButton () {
        this.loginButton.setText("Login");
        this.loginButton.setDisable(false);
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
