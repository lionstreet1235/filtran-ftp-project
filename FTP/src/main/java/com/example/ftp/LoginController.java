package com.example.ftp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
}
