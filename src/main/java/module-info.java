module com.example.laundry {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.mysql.connector.java;


    opens com.example.laundry to javafx.fxml;
    exports com.example.laundry;
}