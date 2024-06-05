package com.example.ftp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientAppController {

    @FXML
    private Label messageLabel;
    @FXML
    private TextArea directoryTextArea;

    @FXML
    void connectToServer() {
        new Thread(() -> {
            try {
                int port = 8881;
                Socket socket = new Socket("localhost", port);
                System.out.println("Connected to server at port: " + port);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuilder serverMessages = new StringBuilder();
                String messageFromServer;
                while ((messageFromServer = in.readLine()) != null) {
                    serverMessages.append(messageFromServer).append("\n");
                }

                // Update UI on the JavaFX Application Thread
                Platform.runLater(() -> directoryTextArea.setText(serverMessages.toString()));

                //socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                Platform.runLater(() -> messageLabel.setText("Failed to connect to server"));
            }
        }).start();
    }

    @FXML
    private void login() {
        openLoginForm();

    }

    @FXML
    private void register() {
        openRegisterForm();
    }

    private void openLoginForm() {
        try {
            // Load the login form FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            // Create a new stage for the login form
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(root));
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openRegisterForm() {
        try {
            // Load the registration form FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Parent root = loader.load();

            // Create a new stage for the registration form
            Stage registerStage = new Stage();
            registerStage.setTitle("Register");
            registerStage.setScene(new Scene(root));
            registerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
