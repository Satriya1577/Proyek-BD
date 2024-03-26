package com.example.laundry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class StaffController implements Initializable {
    @FXML
    DatePicker datePicker;
    @FXML
    private TextField txtStaffID;
    @FXML
    private TextField txtNamaDepan;
    @FXML
    private TextField txtNamaBelakang;
    @FXML
    private TextField txtAlamat;
    @FXML
    private TextField txtNoHp;
    @FXML
    private TableView<Staff>table;
    @FXML
    private TableColumn<Staff,Integer>staffIdColumn;
    @FXML
    private TableColumn<Staff,String> namaDepanColumn;
    @FXML
    private TableColumn<Staff,String> namaBelakangColumn;
    @FXML
    private TableColumn<Staff,String> alamatColumn;
    @FXML
    private TableColumn<Staff,String> noHpColumn;
    @FXML
    private TableColumn<Staff, Date> tanggalMasukColumn;
    @FXML
    private Button ButtonInsert;
    @FXML
    private Button ButtonUpdate;
    @FXML
    private Button ButtonDelete;
    @FXML
    private Button ButtonClear;
    @FXML
    private TextField filterField;
    public Connection databaseLink;

    @FXML
    private void handleButtonAction(ActionEvent event){
        if (event.getSource() == ButtonInsert){
            insertButton();
            searchStaff();
        } else if (event.getSource() == ButtonUpdate) {
            updateButton();
            searchStaff();
        } else if (event.getSource() == ButtonDelete) {
            deleteButton();
            searchStaff();
        } else if (event.getSource() == ButtonClear) {
            clearButton();
            searchStaff();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showStaff();
        searchStaff();
    }
    //connect ke sql
    public Connection getConnection() {
        String databaseName = "laundry";
        String databaseUser = "root";
        String databasePass = "12345";

        String url = "jdbc:mysql://localhost/"+databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePass);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }

    public ObservableList<Staff> getStaffList(){
        ObservableList<Staff> staffList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select * from staff";
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Staff staff;
            while (resultSet.next()){
                staff = new Staff(resultSet.getInt("staff_id"),resultSet.getString("first_name"),
                        resultSet.getString("last_name"),resultSet.getString("address"),resultSet.getString("handphone_number"),
                        resultSet.getString("tgl_masukKerja"));
                staffList.add(staff);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return staffList;
    }
    public void showStaff(){
        ObservableList<Staff> list = getStaffList();
        staffIdColumn.setCellValueFactory(new PropertyValueFactory<Staff,Integer>("id")); //diambil dr class staff
        namaDepanColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("namaDepan"));
        namaBelakangColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("namaBelakang"));
        alamatColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("alamat"));
        noHpColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("noHp"));
        tanggalMasukColumn.setCellValueFactory(new PropertyValueFactory<Staff,Date>("tanggalMasuk"));

        table.setItems(list);
    }
    private void insertButton(){
        if(txtNamaDepan.getText().equals("") || txtNamaBelakang.getText().equals("") || txtNoHp.getText().equals("") || datePicker.getValue() == null){
            Alert alert =  new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setHeight(100);
            alert.setContentText("Harap Di isi");
            alert.setTitle("Error");
            alert.showAndWait();
            doNothing();
        }
        else {
            String query = "insert into staff values ('"+ txtNamaDepan.getText() + "','" + txtNamaBelakang.getText() + "','" + txtAlamat.getText()
                    + "','" + txtNoHp.getText() + "','" + datePicker.getValue().toString() + "')";
            try {
                executeQuery(query);
                clearButton();
                showStaff();
                searchStaff();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void updateButton(){
        if(txtStaffID.getText().equals("") || txtNamaDepan.getText().equals("") || txtNamaBelakang.getText().equals("") || txtNoHp.getText().equals("") || datePicker.getValue() == null){
            Alert alert =  new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setHeight(100);
            alert.setContentText("Harap Di isi");
            alert.setTitle("Error");
            alert.showAndWait();
            doNothing();
        }
        else {
            String query = "update staff set first_name = '"+txtNamaDepan.getText()+"', last_name = '"+txtNamaBelakang.getText()+"', address = '"+txtAlamat.getText()+"', handphone_number = '"+txtNoHp.getText()+
                    "', tgl_masukKerja = '"+datePicker.getValue().toString()+"' where staff_id = "+txtStaffID.getText()+"";
            try {
                executeQuery(query);
                clearButton();
                showStaff();
                searchStaff();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        String query = "update staff set nama_depan = '"+txtNamaDepan.getText()+"', nama_belakang = '"+txtNamaBelakang.getText()+"', alamat = '"+txtAlamat.getText()+"', no_hp = '"+txtNoHp.getText()+
//                "', tgl_masukKerja = '"+datePicker.getValue().toString()+"' where staff_id = "+txtStaffID.getText()+"";
//        executeQuery(query);
//        showStaff();
    }
    private void deleteButton(){
        String query = "delete from staff where staff_id = "+txtStaffID.getText()+"";
        executeQuery(query);
        clearButton();
        showStaff();
        searchStaff();

    }
    private void clearButton(){
        txtStaffID.setText("");
        txtNamaDepan.setText("");
        txtNamaBelakang.setText("");
        txtAlamat.setText("");
        txtNoHp.setText("");
        datePicker.setValue(null);
        filterField.setText("");
    }

    private void doNothing(){
        txtStaffID.setText(txtStaffID.getText());
        txtNamaDepan.setText(txtNamaDepan.getText());
        txtNamaBelakang.setText(txtNamaBelakang.getText());
        txtAlamat.setText(txtAlamat.getText());
        txtNoHp.setText(txtNoHp.getText());
        datePicker.setValue(datePicker.getValue());
    }

    public void searchStaff(){
        ObservableList<Staff> list = getStaffList();
        staffIdColumn.setCellValueFactory(new PropertyValueFactory<Staff,Integer>("id")); //diambil dr class staff
        namaDepanColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("namaDepan"));
        namaBelakangColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("namaBelakang"));
        alamatColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("alamat"));
        noHpColumn.setCellValueFactory(new PropertyValueFactory<Staff,String>("noHp"));
        tanggalMasukColumn.setCellValueFactory(new PropertyValueFactory<Staff,Date>("tanggalMasuk"));

        table.setItems(list);

        FilteredList<Staff>FilteredList = new FilteredList<>(list, b -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList.setPredicate(Staff -> {
                //kalo search value kosong nampilin semua data
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String keyword = newValue.toLowerCase();

                if (Staff.getNamaDepan().toLowerCase().contains(keyword)) {
                    return true; //berarti nemu yang cocok
                } else if (Staff.getNamaBelakang().toLowerCase().contains(keyword)) {
                    return true;
                } else if (Staff.getAlamat().toLowerCase().contains(keyword)) {
                    return true;
                } else if (Staff.getNoHp().toLowerCase().contains(keyword)) {
                    return true;
                } else if (Staff.getTanggalMasuk().toLowerCase().contains(keyword)) {
                    return true;
                }else {
                    return false; //ga ada yang cocok
                }
            });
        });
        SortedList<Staff>sortedList = new SortedList<>(FilteredList);
        sortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedList);

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
        Staff staff = table.getSelectionModel().getSelectedItem();
        txtStaffID.setText(String.valueOf(staff.getId()));
        txtNamaDepan.setText(staff.getNamaDepan());
        txtNamaBelakang.setText(staff.getNamaBelakang());
        txtAlamat.setText(staff.getAlamat());
        txtNoHp.setText(staff.getNoHp());
        datePicker.setValue(LocalDate.parse(staff.getTanggalMasuk()));
    }
}