package com.example.laundry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReportController {
    @FXML
    private TableColumn<Report, Integer> Column_Qty;

    @FXML
    private TableColumn<Report, String> coloumn_JenisJasa;

    @FXML
    private TableColumn<Report, String> column_Item;

    @FXML
    private DatePicker date_end;

    @FXML
    private DatePicker date_start;
    @FXML
    private TableColumn<Report, Integer> column_harga;

    @FXML
    private TableColumn<Report, Integer> column_id_jenis_jasa;

    @FXML
    private TableView<Report> table_report;

    @FXML
    private Label lbl_member_name;

    @FXML
    private Label lbl_nomor;
    @FXML
    private Label lbl_total;

    @FXML
    private Label lbl_staff_name;

    @FXML
    private Label lbl_tgl;

    public ObservableList<Report> getReportList(){
        ObservableList<Report> staffList = FXCollections.observableArrayList();
        Connection connection = mysqlconnect.ConnectDb();
        Statement statement;
        ResultSet resultSet;
        try {
            String start_date = String.valueOf(date_start);
            String end_date = String.valueOf(date_end);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM detail_transaksi WHERE tgl_transaksi BETWEEN '"+ start_date +"' AND '"+ end_date +"'");
            Report report;
            while (resultSet.next()){
                report = new Report(resultSet.getString("noTransaksi"),resultSet.getString("namaBarang"),
                        resultSet.getString("jenisJasa"),resultSet.getString("tgl_transaksi"),resultSet.getInt("id_transaksi"),
                        resultSet.getInt("idJenisJasa"),resultSet.getInt("qty"),resultSet.getInt("harga"),
                        resultSet.getInt("jumlah"),resultSet.getInt("totalHarga"));
                staffList.add(report);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return staffList;
    }
    public void showReport(){
        ObservableList<Report> list = getReportList();
        column_id_jenis_jasa.setCellValueFactory(new PropertyValueFactory<Report,Integer>("idJenisJasa")); //diambil dr class staff
        coloumn_JenisJasa.setCellValueFactory(new PropertyValueFactory<Report,String>("jenisJasa"));
        column_Item.setCellValueFactory(new PropertyValueFactory<Report,String>("namaBarang"));
        Column_Qty.setCellValueFactory(new PropertyValueFactory<Report,Integer>("qty"));
        column_harga.setCellValueFactory(new PropertyValueFactory<Report,Integer>("harga"));

        table_report.setItems(list);
    }
    public void setDataLabel(){
        Report report = new Report();
        lbl_nomor.setText(report.getNoTransaksi());
        lbl_tgl.setText(report.getTgl_transaksi());
        lbl_total.setText(String.valueOf(report.getTotalHarga()));
    }
    public void Initializable (){
        showReport();
    }
}
