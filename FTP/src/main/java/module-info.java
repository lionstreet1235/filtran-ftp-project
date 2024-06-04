module com.example.ftp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.ftp to javafx.fxml;
    exports com.example.ftp;
}