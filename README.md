Using Mysql
package com.example.ftp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

**************tạo local database theo cac thông tin này cho đồng bộ, để mỗi khi commit không có thay đổi gì ở đây

package com.example.ftp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




    public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String USER = "son";
    private static final String PASSWORD = "081103";

   
    private static Connection connection;

    public static Connection connect() throws SQLException {
       
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}