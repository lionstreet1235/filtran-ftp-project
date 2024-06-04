package com.example.ftp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void register() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (isValidInput(username, password, confirmPassword)) {
            if (password.equals(confirmPassword)) {
                if (DatabaseManager.registerUser(username, password)) {
                    messageLabel.setText("Registration successful!");
                    // Optionally, you can clear the input fields after successful registration
                    usernameField.clear();
                    passwordField.clear();
                    confirmPasswordField.clear();
                } else {
                    messageLabel.setText("Failed to register user.");
                }
            } else {
                messageLabel.setText("Passwords do not match.");
            }
        } else {
            messageLabel.setText("Please enter username, password, and confirm password.");
        }
    }

    private boolean isValidInput(String username, String password, String confirmPassword) {
        return !username.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty();
    }
}
