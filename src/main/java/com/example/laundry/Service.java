package com.example.laundry;

public class Service {
    private int id_jenis_jasa,berat,harga;
    private String nama_jenis,item;

    public Service(int id_jenis_jasa, int berat, int harga, String nama_jenis, String item) {
        this.id_jenis_jasa = id_jenis_jasa;
        this.berat = berat;
        this.harga = harga;
        this.nama_jenis = nama_jenis;
        this.item = item;
    }

    public int getId_jenis_jasa() {
        return id_jenis_jasa;
    }

    public void setId_jenis_jasa(int id_jenis_jasa) {
        this.id_jenis_jasa = id_jenis_jasa;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNama_jenis() {
        return nama_jenis;
    }

    public void setNama_jenis(String nama_jenis) {
        this.nama_jenis = nama_jenis;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
