package com.example.laundry;

public class Report {
    String noTransaksi,namaBarang,jenisJasa,tgl_transaksi;
    int id_transaksi,idJenisJasa,qty,harga,jumlah,totalHarga;

    public Report(String noTransaksi, String namaBarang, String jenisJasa, String tgl_transaksi, int id_transaksi, int idJenisJasa, int qty, int harga, int jumlah, int totalHarga) {
        this.noTransaksi = noTransaksi;
        this.namaBarang = namaBarang;
        this.jenisJasa = jenisJasa;
        this.tgl_transaksi = tgl_transaksi;
        this.id_transaksi = id_transaksi;
        this.idJenisJasa = idJenisJasa;
        this.qty = qty;
        this.harga = harga;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
    }

    public Report() {

    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getJenisJasa() {
        return jenisJasa;
    }

    public void setJenisJasa(String jenisJasa) {
        this.jenisJasa = jenisJasa;
    }

    public String getTgl_transaksi() {
        return tgl_transaksi;
    }

    public void setTgl_transaksi(String tgl_transaksi) {
        this.tgl_transaksi = tgl_transaksi;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getIdJenisJasa() {
        return idJenisJasa;
    }

    public void setIdJenisJasa(int idJenisJasa) {
        this.idJenisJasa = idJenisJasa;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
}
