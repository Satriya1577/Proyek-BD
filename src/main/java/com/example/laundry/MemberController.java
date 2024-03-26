package com.example.laundry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class MemberController implements Initializable {

    @FXML
    private TableColumn<DbMember,Integer> id_column;
    @FXML
    private TableColumn<DbMember, String> alamat_column;

    @FXML
    private TableColumn<DbMember, String> firstName_column;

    @FXML
    private TableColumn<DbMember, Integer> id;

    @FXML
    private TableColumn<DbMember, String> lastName_column;

    @FXML
    private TableColumn<DbMember, Integer> noHp_column;

    @FXML
    private TableView<DbMember> table_service;

    @FXML
    private TextArea txt_alamat;

    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_search;

    @FXML
    private  Button btn_hapus;
    @FXML
    private TextField txt_hp;

    @FXML
    private TextField txt_firstName;

    @FXML
    private TextField txt_lastName;


    Connection conn = null;
    ResultSet rs = null;

    PreparedStatement pst = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showMember();
        searchMember();
    }
    //connect ke sql
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

    public ObservableList<DbMember> getStaffList(){
        ObservableList<DbMember> staffList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM member");
            DbMember member;
            while (resultSet.next()){
                member= new DbMember(resultSet.getInt("member_id"),resultSet.getString("first_name"),
                        resultSet.getString("last_name"),resultSet.getString("alamat"),resultSet.getInt("no_hp"));
                staffList.add(member);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return staffList;
    }
    public void showMember(){
        ObservableList<DbMember> list = getStaffList();
        id_column.setCellValueFactory(new PropertyValueFactory<DbMember,Integer>("member_id")); //diambil dr class staff
        firstName_column.setCellValueFactory(new PropertyValueFactory<DbMember,String>("first_name"));
        lastName_column.setCellValueFactory(new PropertyValueFactory<DbMember,String>("last_name"));
        noHp_column.setCellValueFactory(new PropertyValueFactory<DbMember,Integer>("no_hp"));
        alamat_column.setCellValueFactory(new PropertyValueFactory<DbMember,String>("alamat"));

        table_service.setItems(list);
    }
    public void insertButton(){
        if(txt_firstName.getText().equals("") || txt_lastName.getText().equals("") || txt_hp.getText().equals("") || txt_alamat.getText().equals("")){
            Alert alert =  new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setHeight(100);
            alert.setContentText("Harap Di isi");
            alert.setTitle("Error");
            alert.showAndWait();
            doNothing();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confrimation");
            alert.setHeaderText("Save");
            alert.setContentText("Are You Sure Want to Save?");
            alert.showAndWait();
            ButtonType buttonType = alert.getResult();
            if (buttonType == ButtonType.OK){
                String query = "insert into member(first_name,last_name,no_hp,alamat) values ('"+ txt_firstName.getText() + "','" + txt_lastName.getText() + "','" + txt_hp.getText()
                        + "','" + txt_alamat.getText() +"')";
                try {
                    executeQuery(query);
                    clearButton();
                    showMember();
                    searchMember();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                alert.close();
            }
        }
    }
    public void updateButton(){
        if(txt_firstName.getText().equals("") || txt_lastName.getText().equals("") || txt_alamat.getText().equals("") || txt_hp.getText().equals("")){
            Alert alert =  new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setHeight(100);
            alert.setContentText("Harap Dipilih terlebih dahulu di table tersebut");
            alert.setTitle("Error");
            alert.showAndWait();
            doNothing();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confrimation");
            alert.setHeaderText("Update");
            alert.setContentText("Are You Sure Want to Update?");
            alert.showAndWait();
            ButtonType buttonType = alert.getResult();
            if (buttonType == ButtonType.OK){
                String query = "update member set first_name = '"+txt_firstName.getText()+"', last_name = '"+txt_lastName.getText()+"', alamat = '"+txt_alamat.getText()+"', no_hp = '"+txt_hp.getText()+
                        "' where member_id = "+txt_id.getText()+"";
                try {
                    executeQuery(query);
                    clearButton();
                    showMember();
                    searchMember();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                alert.close();
            }

        }
    }
    public void deleteButton(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confrimation");
        alert.setHeaderText("Delete");
        alert.setContentText("Are you sure want to delete?");
        alert.showAndWait();
        ButtonType buttonType = alert.getResult();
        if (buttonType == ButtonType.OK){
            String query = "delete from member where member_id = "+txt_id.getText()+"";
            executeQuery(query);
            clearButton();
            showMember();
            searchMember();
        }else {
            alert.close();
        }
    }
    public void clearButton(){
        txt_firstName.setText("");
        txt_lastName.setText("");
        txt_alamat.setText("");
        txt_hp.setText("");
    }

    private void doNothing(){
        txt_firstName.setText(txt_firstName.getText());
        txt_lastName.setText(txt_lastName.getText());
        txt_alamat.setText(txt_alamat.getText());
        txt_hp.setText(txt_hp.getText());
        txt_id.setText(txt_id.getText());
    }

    public void searchMember(){
        ObservableList<DbMember> list = getStaffList();
        id_column.setCellValueFactory(new PropertyValueFactory<DbMember,Integer>("member_id")); //diambil dr class staff
        firstName_column.setCellValueFactory(new PropertyValueFactory<DbMember,String>("first_name"));
        lastName_column.setCellValueFactory(new PropertyValueFactory<DbMember,String>("last_name"));
        alamat_column.setCellValueFactory(new PropertyValueFactory<DbMember,String>("alamat"));
        noHp_column.setCellValueFactory(new PropertyValueFactory<DbMember,Integer>("no_hp"));

        table_service.setItems(list);

        FilteredList<DbMember> FilteredList = new FilteredList<>(list, b -> true);

        txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList.setPredicate(DbMember -> {
                //kalo search value kosong nampilin semua data
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String keyword = newValue.toLowerCase();

                if (DbMember.getFirst_name().toLowerCase().contains(keyword)) {
                    return true; //berarti nemu yang cocok
                } else if (DbMember.getLast_name().toLowerCase().contains(keyword)) {
                    return true;
                }else {
                    return false; //ga ada yang cocok
                }
            });
        });
        SortedList<DbMember> sortedList = new SortedList<>(FilteredList);
        sortedList.comparatorProperty().bind(table_service.comparatorProperty());
        table_service.setItems(sortedList);

    }
    private void executeQuery(String query) {
        Connection connection = getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getRowTable(javafx.scene.input.MouseEvent mouseEvent) {
        DbMember member = table_service.getSelectionModel().getSelectedItem();
        txt_id.setText(String.valueOf(member.getMember_id()));
        txt_firstName.setText(member.getFirst_name());
        txt_lastName.setText(member.getLast_name());
        txt_alamat.setText(member.getAlamat());
        txt_hp.setText(String.valueOf(member.getNo_hp()));
    }
}
