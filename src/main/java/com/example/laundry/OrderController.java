package com.example.laundry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private TableColumn<DbMember, String> alamat_column;

    @FXML
    private TableColumn<DbMember, String> firstName_column;

    @FXML
    private TableColumn<DbMember, Integer> id_column;

    @FXML
    private TableColumn<DbMember, String> lastName_column;

    @FXML
    private TableColumn<DbMember, Integer> noHp_column;

    @FXML
    private TableView<DbMember> table_member;
    @FXML
    private Button btnBatal_Customer;

    @FXML
    private Button btnPilih_Customer;

    @FXML
    private Pane pane_customer;
    @FXML
    private Pane pane_service;
    @FXML
    private TableColumn<Service, Integer> columnBerat_JenisJasa;
    @FXML
    private TableColumn<Service, Integer> colom_idJenisJasa;

    @FXML
    private TableColumn<Service, Integer> columnHarga_JenisJasa;

    @FXML
    private TableColumn<Service, String> columnNamaBarang_JenisJasa;

    @FXML
    private TableView<Service> table_service;
    @FXML
    private Button btnBatal;
    @FXML
    private TableColumn<Service, String> colunNama_JenisJasa;
    @FXML
    private  Button btnPilih;
    @FXML
    private Button btn_hapus;

    @FXML
    private Button btn_simpan;

    @FXML
    private Button btn_tambah;

    @FXML
    private TableColumn<Order, Integer> column_Harga;

    @FXML
    private TableColumn<Order, Integer> column_Jumlah;
    @FXML
    private TableColumn<Order, String> column_jenisJasa;


    @FXML
    private TableColumn<Order, String> column_NamaBarang;

    @FXML
    private TableColumn<Order, Integer> column_NoIDBarang;

    @FXML
    private TableColumn<Order, String> column_NoTransaksi;

    @FXML
    private TableColumn<Order, Integer> column_Total;

    @FXML
    private TableView<Order> table_order;
    @FXML
    private TableView<DbMember> table_customer;

    @FXML
    private Label lbl_price;

    @FXML
    private TextField txt_IDBarang;

    @FXML
    private TextField txt_IDCustomer;

    @FXML
    private TextField txt_NamaCustomer;

    @FXML
    private TextField txt_NoTransaksi;

    @FXML
    private TextField txt_bayar;
    @FXML
    private TextField txt_total;

    @FXML
    private TextField txt_date;

    @FXML
    private TextField txt_harga;

    @FXML
    private TextField txt_jenis_jasa;

    @FXML
    private TextField txt_jumlah;

    @FXML
    private TextField txt_kembalian;

    @FXML
    private TextField txt_nama_jenis;

    @FXML
    private TextField txt_totalBayar;

    int sum;
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

    ObservableList<Order> observableOrderList = FXCollections.observableArrayList();
    public void showMember(){ // ini method buat button tambah kah?? -Satriya-
        column_NoTransaksi.setCellValueFactory(new PropertyValueFactory<Order,String>("noTransaksi")); //diambil dr class staff
        column_NoIDBarang.setCellValueFactory(new PropertyValueFactory<Order,Integer>("idBarang"));
        column_jenisJasa.setCellValueFactory(new PropertyValueFactory<Order,String>("namaJenis"));
        column_NamaBarang.setCellValueFactory(new PropertyValueFactory<Order,String>("namaBarang"));
        column_Jumlah.setCellValueFactory(new PropertyValueFactory<Order,Integer>("jumlah"));
        column_Harga.setCellValueFactory(new PropertyValueFactory<Order,Integer>("harga"));
        column_Total.setCellValueFactory(new PropertyValueFactory<Order,Integer>("total"));
        
        ObservableList<Order> temp = FXCollections.observableArrayList();
        temp.addAll(observableOrderList);
        observableOrderList.clear();
        temp.add(new Order(txt_NoTransaksi.getText(),
                txt_jenis_jasa.getText(),
                txt_nama_jenis.getText(),
                Integer.parseInt(txt_IDBarang.getText()),
                Integer.parseInt(txt_harga.getText()),
                Integer.parseInt(txt_jumlah.getText()),
                Integer.parseInt(txt_total.getText()))
        );
        observableOrderList.addAll(temp);
        temp.clear();
        table_order.setItems(observableOrderList);
        sum = sum + Integer.parseInt(txt_total.getText());
        txt_totalBayar.setText("" + sum);
        lbl_price.setText("Rp. " + sum);
        clearButton2();
    }
    public void insertButton(){
        if(txt_bayar.getText().equals("")){
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
                for (int i = 0; i < table_order.getItems().size(); i++){
                    Order temp = new Order();
                    temp.setNoTransaksi(String.valueOf(table_order.getItems().get(i).getNoTransaksi()));
                    temp.setIdBarang(table_order.getItems().get(i).getIdBarang());
                    temp.setNamaBarang(table_order.getItems().get(i).getNamaBarang());
                    temp.setJumlah(table_order.getItems().get(i).getJumlah());
                    temp.setHarga(table_order.getItems().get(i).getHarga());
                    temp.setTotal(table_order.getItems().get(i).getTotal());
                    txt_date.getText();
                    //String sql = "INSERT INTO detail_transaksi(id_transaksi,id_jenis_jasa,qty,harga,total_harga) VALUES (?,?,?,?,?)";
                    String query = "INSERT INTO detail_transaksi(no_transaksi,id_jenis_jasa,nama_barang,qty,harga,total_harga,tgl_transaksi) VALUES ('"+ temp.getNoTransaksi() + "','" + temp.getIdBarang() + "','" + temp.getNamaBarang() + "','"+ temp.getJumlah()
                            + "','" + temp.getHarga() +"','" + temp.getTotal() + "','" + txt_date.getText() +"')";
                    System.out.println(query);
                    try {
                        executeQuery(query);
                        clearButton();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                txt_total.setText("");
                lbl_price.setText("Rp . 0");
                clearButton();
                utama();
            }else {
                alert.close();
            }

        }
    }
    public void utama(){
        txt_NoTransaksi.setText("");
        txt_IDBarang.setText("");
        txt_nama_jenis.setText("");
        txt_harga.setText("");
        txt_jumlah.setText("");
    }
    public void clearButton(){
        txt_IDCustomer.setText("");
        txt_NamaCustomer.setText("");
        txt_IDBarang.setText("0");
        txt_jenis_jasa.setText("");
        txt_nama_jenis.setText("");
        txt_harga.setText("0");
        txt_jumlah.setText("0");
        txt_bayar.setText("0");
        txt_totalBayar.setText("0");
        txt_kembalian.setText("0");
    }
    public void clearButton2(){
        txt_IDCustomer.setText("");
        txt_NamaCustomer.setText("");
        txt_IDBarang.setText("0");
        txt_jenis_jasa.setText("");
        txt_nama_jenis.setText("");
        txt_harga.setText("0");
        txt_jumlah.setText("0");
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
    private void doNothing(){
        txt_IDCustomer.setText(txt_IDCustomer.getText());
        txt_NamaCustomer.setText(txt_NamaCustomer.getText());
        txt_IDBarang.setText(txt_IDBarang.getText());
        txt_jenis_jasa.setText(txt_jenis_jasa.getText());
        txt_nama_jenis.setText(txt_nama_jenis.getText());
        txt_harga.setText(txt_harga.getText());
        txt_jumlah.setText(txt_jumlah.getText());
    }
//    public void autoNumber(){
//        try {
//            Connection conn = getConnection();
//            Statement statement = conn.createStatement();
//            ResultSet rst = statement.executeQuery("SELECT * FROM member");
//            if (rst.next()){
//                String NoMember = rst.getString("member_id").substring(2);
//                String TR = "" + (Integer.parseInt(NoMember) + 1);
//                String No1 = "";
//                if (TR.length() == 1){
//                    No1 = "000";
//                } else if (TR.length()==2) {
//                    No1 = " 00";
//                } else if (TR.length() == 3) {
//                    No1 = "0";
//                } else if (TR.length() == 4) {
//                    No1 ="";
//                }
//                txt_NoTransaksi.setText("20" + No1 + TR);
//            }else {
//                txt_NoTransaksi.setText("TR0001");
//            }
//            rst.close();
//            statement.close();
//        }catch (Exception e){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText("Information");
//            alert.setTitle("Information");
//            alert.setContentText("Auto Number Error");
//        }
//    }
    public void columnTotalTable(){
        int a,b,c;
        a = Integer.parseInt(txt_harga.getText());
        b = Integer.parseInt(txt_jumlah.getText());
        c = a*b;
        txt_total.setText("" + c);
        isEmpty();
    }
    public void sumTotalBayar(){
       int a = Integer.parseInt(txt_total.getText());
       sum = 0;
       sum = sum + a;
    }
    //jika kosong
    public void isEmpty(){
        if (txt_IDCustomer.getText().isEmpty() && txt_NamaCustomer.getText().isEmpty() &&
        txt_IDBarang.getText().isEmpty() && txt_jenis_jasa.getText().isEmpty() && txt_nama_jenis.getText().isEmpty()
        && txt_harga.getText().isEmpty() && txt_jumlah.getText().isEmpty() && txt_total.getText().isEmpty()){
            btn_tambah.setDisable(true);
            btn_hapus.setDisable(true);
        }else {
            btn_tambah.setDisable(false);
            btn_hapus.setDisable(false);
        }
    }
    public void kembalian(){
        int totalBayar, bayar,kembalian;
        totalBayar = Integer.parseInt(txt_totalBayar.getText());
        bayar = Integer.parseInt(txt_bayar.getText());
        if (bayar > totalBayar){
            kembalian = bayar - totalBayar;
            txt_kembalian.setText("" + kembalian);
        } else if (bayar < totalBayar) {
            kembalian = totalBayar - bayar;
            txt_kembalian.setText("" + "-" + kembalian);
        } else if (totalBayar == bayar) {
            kembalian = 0;
            txt_kembalian.setText("" + kembalian);
        }
    }

    public void dateNow(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatterDateTime = now.format(formatter);
        txt_date.setText(formatterDateTime);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane_customer.setVisible(false);
        pane_service.setVisible(false);
        dateNow();
        isEmpty();
       // showMember();
        column_NoTransaksi.setCellValueFactory(new PropertyValueFactory<Order,String>("noTransaksi")); //diambil dr class staff
        column_NoIDBarang.setCellValueFactory(new PropertyValueFactory<Order,Integer>("idBarang"));
        column_NamaBarang.setCellValueFactory(new PropertyValueFactory<Order,String>("namaBarang"));
        column_Jumlah.setCellValueFactory(new PropertyValueFactory<Order,Integer>("jumlah"));
        column_Harga.setCellValueFactory(new PropertyValueFactory<Order,Integer>("harga"));
        column_Total.setCellValueFactory(new PropertyValueFactory<Order,Integer>("total"));
    }
    public void btnHapus(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confrimation");
        alert.setHeaderText("Delete");
        alert.setContentText("Are You Sure Want to Delete?");
        alert.showAndWait();
        ButtonType buttonType = alert.getResult();
        if (buttonType == ButtonType.OK){
            ObservableList<Order> select = table_order.getSelectionModel().getSelectedItems();
            ObservableList<Order> temp = FXCollections.observableArrayList();
            temp.addAll(observableOrderList);
            observableOrderList.clear();
            temp.removeAll(select);
            table_order.refresh();
            lbl_price.setText("0");
            txt_totalBayar.setText("0");
        }else {
            alert.close();
        }

    }
    public void getRowTable(javafx.scene.input.MouseEvent mouseEvent) {
        Order order = table_order.getSelectionModel().getSelectedItem();
        txt_NoTransaksi.setText(String.valueOf(order.getNoTransaksi()));
        txt_IDBarang.setText(String.valueOf(order.getIdBarang()));
        txt_jenis_jasa.setText(order.getNamaJenis());
        txt_nama_jenis.setText(order.getNamaBarang());
        txt_harga.setText(String.valueOf(order.getHarga()));
        txt_jumlah.setText(String.valueOf(order.getJumlah()));
        txt_total.setText(String.valueOf(order.getTotal()));
    }
    public void showTableViewService(){
        pane_service.setVisible(true);
        showService();
    }
    public void btnBatal(){
        pane_service.setVisible(false);
    }
    public void showTableViewCustomer(){
        pane_customer.setVisible(true);
        showMemberList();
    }
    public void btnBatalCustomer(){
        pane_customer.setVisible(false);
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
    public void showService(){
        ObservableList<Service> list = getServiceList();
        colom_idJenisJasa.setCellValueFactory(new PropertyValueFactory<Service, Integer>("id_jenis_jasa")); //diambil dr class staff
        colunNama_JenisJasa.setCellValueFactory(new PropertyValueFactory<Service, String>("nama_jenis"));
        columnNamaBarang_JenisJasa.setCellValueFactory(new PropertyValueFactory<Service, String>("item"));
        columnBerat_JenisJasa.setCellValueFactory(new PropertyValueFactory<Service, Integer>("berat"));
        columnHarga_JenisJasa.setCellValueFactory(new PropertyValueFactory<Service, Integer>("harga"));

        table_service.setItems(list);
    }
    public void getRowTable() {
        Service service = table_service.getSelectionModel().getSelectedItem();
        txt_IDBarang.setText(String.valueOf(service.getId_jenis_jasa()));
        txt_jenis_jasa.setText(service.getNama_jenis());
        txt_nama_jenis.setText(service.getItem());
        txt_harga.setText(String.valueOf(service.getHarga()));
    }
    public void Cari_Kode(){
        if (table_service.getSelectionModel().getSelectedItem() != null) {
            txt_IDBarang.setText(String.valueOf(table_service.getSelectionModel().getSelectedItem().getId_jenis_jasa()));

            txt_nama_jenis.setText(table_service.getSelectionModel().getSelectedItem().getItem());
            txt_jenis_jasa.setText(String.valueOf(table_service.getSelectionModel().getSelectedItem().getNama_jenis()));
            txt_harga.setText(String.valueOf(table_service.getSelectionModel().getSelectedItem().getHarga()));
        }
        pane_service.setVisible(false);
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
    public void showMemberList(){
        ObservableList<DbMember> list = getStaffList();
        id_column.setCellValueFactory(new PropertyValueFactory<DbMember,Integer>("member_id")); //diambil dr class staff
        firstName_column.setCellValueFactory(new PropertyValueFactory<DbMember,String>("first_name"));
        lastName_column.setCellValueFactory(new PropertyValueFactory<DbMember,String>("last_name"));
        noHp_column.setCellValueFactory(new PropertyValueFactory<DbMember,Integer>("no_hp"));
        alamat_column.setCellValueFactory(new PropertyValueFactory<DbMember,String>("alamat"));

        table_member.setItems(list);
    }
    public void setBtnPilih_Customer(){
        if (table_member.getSelectionModel().getSelectedItem() != null) {
            txt_IDCustomer.setText(String.valueOf(table_member.getSelectionModel().getSelectedItem().getMember_id()));
            txt_NamaCustomer.setText(table_member.getSelectionModel().getSelectedItem().getFirst_name());
        }
        pane_customer.setVisible(false);
    }

}