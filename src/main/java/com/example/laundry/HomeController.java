package com.example.laundry;

import com.mysql.cj.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {


    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_nama;
    private static Session instance;
    private Staff currentStaff;

    private void HomeController() {

    }

    public void ServiceButton() throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("MemberPage.fxml"));
        Stage mainStage = new Stage();
        mainStage.setResizable(false);
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    public void MemberButton() throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("service.fxml"));
        Stage mainStage = new Stage();
        mainStage.setResizable(false);
        mainStage.setTitle("Member");
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    public void StaffButton() throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("staff.fxml"));
        Stage mainStage = new Stage();
        mainStage.setResizable(false);
        mainStage.setTitle("Staff");
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    public void OrderButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("order.fxml"));
        Stage mainStage = new Stage();
        mainStage.setResizable(false);
        mainStage.setTitle("Staff");
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
