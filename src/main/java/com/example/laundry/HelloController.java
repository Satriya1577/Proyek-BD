package com.example.laundry;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;


public class HelloController{

    @FXML
    private Button button;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    public HelloController(Button button, PasswordField password, TextField username) {
        this.button = button;
        this.password = password;
        this.username = username;
    }

    public HelloController() {
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }
    int index = -1;

    ResultSet rs = null;
    PreparedStatement pst = null;
    public Connection getConnection() {
        String databaseName = "laundry";
        String databaseUser = "root";
        String databasePass = "12345";

        String url = "jdbc:mysql://localhost/"+databaseName;

        Connection databaseLink = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePass);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
    public void ConnectDB() throws SQLException {
        String usernames = username.getText();
        String passwords = password.getText();
        try {
            Connection conn = getConnection() ;
            String sql = "SELECT * FROM staff WHERE username = ? AND password = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, usernames);
            pst.setString(2, passwords);
            rs = pst.executeQuery();
            if (rs.next()) {
                button.getScene().getWindow();
                Parent root =  FXMLLoader.load(getClass().getResource("home.fxml"));
                Stage mainStage = new Stage();
                mainStage.setResizable(false);
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Login Gagal", ButtonType.OK);
                alert.setTitle("Error");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}