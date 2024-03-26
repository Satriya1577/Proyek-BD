package com.example.laundry;

public class Order {
    private String noTransaksi,namaJenis,namaBarang;
    private int idBarang,harga,jumlah,total;

    public Order(String noTransaksi, String namaJenis, String namaBarang, int idBarang, int harga, int jumlah, int total) {
        this.noTransaksi = noTransaksi;
        this.namaJenis = namaJenis;
        this.namaBarang = namaBarang;
        this.idBarang = idBarang;
        this.harga = harga;
        this.jumlah = jumlah;
        this.total = total;
    }

    public Order(String toString) {
        this.total = total;
    }

    public Order() {

    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public String getNamaJenis() {
        return namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
