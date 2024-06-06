package com.example.ftp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientApp extends Application {

    private Label messageLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ClientApp.fxml"));
        primaryStage.setTitle("Client Application");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    private void connectToServer() {
        new Thread(() -> {
            try {
                int port = 8881;
                Socket socket = new Socket("localhost", port);
                System.out.println("Connected to server at port: " + port);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String messageFromServer = in.readLine();
                System.out.println("Message from server: " + messageFromServer);

                // Update UI on the JavaFX Application Thread
                javafx.application.Platform.runLater(() -> messageLabel.setText("Message from server: " + messageFromServer));

                //socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                javafx.application.Platform.runLater(() -> messageLabel.setText("Failed to connect to server"));
            }
            System.out.println("Fuck");
        }).start();
    }
}
