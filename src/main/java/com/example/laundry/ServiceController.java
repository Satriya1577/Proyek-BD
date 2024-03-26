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

public class ServiceController implements Initializable {
    @FXML
    private TextField berat;

    @FXML
    private Button clearSearch;

    @FXML
    private TableColumn<Service, Integer> columnBerat;

    @FXML
    private TableColumn<Service, Integer> columnHarga;

    @FXML
    private TableColumn<Service, String> columnItem;

    @FXML
    private TableColumn<Service, String> columnJenisJasa;

    @FXML
    private TextField harga;

    @FXML
    private TextField item;
    @FXML
    private TextField txt_id;

    @FXML
    private TableColumn<Service, Integer> column_id;
    @FXML
    private TextField jenisJasa;

    @FXML
    private TableView<Service> jenisJasaTable;

    @FXML
    private TextField searchJasaTextField;

    Connection conn = null;
    ResultSet rs = null;

    PreparedStatement pst = null;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        showService();
        searchService();

    }

    //connect ke sql
    public Connection getConnection() {
        String databaseName = "laundry";
        String databaseUser = "root";
        String databasePass = "12345";

        String url = "jdbc:mysql://localhost/" + databaseName;

        Connection databaseLink = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePass);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }

    public ObservableList<Service> getServiceList() {
        ObservableList<Service> serviceList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM jenis_jasa");
            Service service;
            while (resultSet.next()) {
                service = new Service(resultSet.getInt("id_jenis_jasa"), resultSet.getInt("berat"),
                        resultSet.getInt("harga"), resultSet.getString("nama_jenis"), resultSet.getString("item"));
                serviceList.add(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceList;
    }

    public void showService() {
        ObservableList<Service> list = getServiceList();
        column_id.setCellValueFactory(new PropertyValueFactory<Service, Integer>("id_jenis_jasa")); //diambil dr class staff
        columnJenisJasa.setCellValueFactory(new PropertyValueFactory<Service, String>("nama_jenis"));
        columnItem.setCellValueFactory(new PropertyValueFactory<Service, String>("item"));
        columnBerat.setCellValueFactory(new PropertyValueFactory<Service, Integer>("berat"));
        columnHarga.setCellValueFactory(new PropertyValueFactory<Service, Integer>("harga"));

        jenisJasaTable.setItems(list);
    }

    public void insertButton() {
        if (jenisJasa.getText().equals("") || item.getText().equals("") || berat.getText().equals("") || harga.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setHeight(100);
            alert.setContentText("Harap Di isi");
            alert.setTitle("Error");
            alert.showAndWait();
            doNothing();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confrimation");
            alert.setHeaderText("Save");
            alert.setContentText("Are You Sure Want to Save?");
            alert.showAndWait();
            ButtonType buttonType = alert.getResult();
            if (buttonType == ButtonType.OK) {
                String query = "insert into jenis_jasa(nama_jenis,item,berat,harga) values ('" + jenisJasa.getText() + "','" + item.getText() + "','" + berat.getText()
                        + "','" + harga.getText() + "')";
                try {
                    executeQuery(query);
                    clearButton();
                    showService();
                    searchService();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.close();
            }
        }
    }

    public void updateButton() {
        if (jenisJasa.getText().equals("") || item.getText().equals("") || berat.getText().equals("") || harga.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setHeight(100);
            alert.setContentText("Harap Dipilih terlebih dahulu di table tersebut");
            alert.setTitle("Error");
            alert.showAndWait();
            doNothing();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confrimation");
            alert.setHeaderText("Update");
            alert.setContentText("Are You Sure Want to Update?");
            alert.showAndWait();
            ButtonType buttonType = alert.getResult();
            if (buttonType == ButtonType.OK) {
                String query = "update jenis_jasa set nama_jenis = '" + jenisJasa.getText() + "', item = '" + item.getText() + "', berat = '" + berat.getText() + "', harga = '" + harga.getText() +
                        "' where id_jenis_jasa = " + txt_id.getText() + "";
                try {
                    executeQuery(query);
                    clearButton();
                    showService();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                searchService();
            } else {
                alert.close();
            }

        }
    }

    public void deleteButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confrimation");
        alert.setHeaderText("Delete");
        alert.setContentText("Are you sure want to delete?");
        alert.showAndWait();
        ButtonType buttonType = alert.getResult();
        if (buttonType == ButtonType.OK) {
            String query = "delete from jenis_jasa where id_jenis_jasa = " + txt_id.getText() + "";
            executeQuery(query);
            clearButton();
            showService();
            searchService();
        } else {
            alert.close();
        }
    }

    public void clearButton() {
        jenisJasa.setText("");
        item.setText("");
        berat.setText("");
        harga.setText("");
    }

    private void doNothing() {
        jenisJasa.setText(jenisJasa.getText());
        item.setText(item.getText());
        berat.setText(berat.getText());
        harga.setText(harga.getText());
        txt_id.setText(txt_id.getText());
    }

    public void searchService() {
        ObservableList<Service> list = getServiceList();
        column_id.setCellValueFactory(new PropertyValueFactory<Service, Integer>("id_jenis_jasa")); //diambil dr class staff
        columnJenisJasa.setCellValueFactory(new PropertyValueFactory<Service, String>("nama_jenis"));
        columnItem.setCellValueFactory(new PropertyValueFactory<Service, String>("item"));
        columnBerat.setCellValueFactory(new PropertyValueFactory<Service, Integer>("berat"));
        columnHarga.setCellValueFactory(new PropertyValueFactory<Service, Integer>("harga"));

        jenisJasaTable.setItems(list);

        FilteredList<Service> FilteredList = new FilteredList<>(list, b -> true);

        searchJasaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList.setPredicate(Service -> {
                //kalo search value kosong nampilin semua data
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String keyword = newValue.toLowerCase();

                if (Service.getNama_jenis().toLowerCase().contains(keyword)) {
                    return true; //berarti nemu yang cocok
                } else if (Service.getItem().toLowerCase().contains(keyword)) {
                    return true;
                } else {
                    return false; //ga ada yang cocok
                }
            });
        });
        SortedList<Service> sortedList = new SortedList<>(FilteredList);
        sortedList.comparatorProperty().bind(jenisJasaTable.comparatorProperty());
        jenisJasaTable.setItems(sortedList);

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
        Service service = jenisJasaTable.getSelectionModel().getSelectedItem();
        txt_id.setText(String.valueOf(service.getId_jenis_jasa()));
        jenisJasa.setText(service.getNama_jenis());
        item.setText(service.getItem());
        berat.setText(String.valueOf(service.getBerat()));
        harga.setText(String.valueOf(service.getHarga()));
    }
}