package com.example.ftp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    // Other database-related methods...

    public static boolean authenticateUser(String username, String password) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0; // If count > 0, username and password match
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public static boolean registerUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0; // If rowsInserted > 0, registration was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//    public static boolean forgotPsw(String username, String password) {
//        String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
//
//    } quen mat khau
}
