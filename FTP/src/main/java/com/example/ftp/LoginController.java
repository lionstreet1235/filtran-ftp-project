package com.example.ftp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidInput(username, password)) {
            if (DatabaseManager.authenticateUser(username, password)) {
                messageLabel.setText("Login successful!");
                createDirectory(username);//truyen vao username de dat ten cho fil ecua user
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.close();
                //sau khi login thi connect den server
                ClientAppController clientAppController = new ClientAppController();
                clientAppController.connectToServer();

            } else {
                messageLabel.setText("Invalid username or password.");
            }
        } else {
            messageLabel.setText("Please enter username and password.");
        }
    }

    private boolean isValidInput(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }
    private void createDirectory(String username) {
        String userHome = System.getProperty("user.home");
        File userDir = new File(userHome + File.separator + username);
        if (!userDir.exists()) {
            if (userDir.mkdir()) {
                System.out.println("Directory created for user: " + username);
            } else {
                System.out.println("Failed to create directory for user: " + username);
            }
        } else {
            System.out.println("Directory already exists for user: " + username);
        }
    }
}
