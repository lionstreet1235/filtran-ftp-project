
Tạo local database Mysql tên "userdb", người dùng "son", pass"081103" để không bị xung đột mỗi khi commit bracnh của mình lên project. 






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
