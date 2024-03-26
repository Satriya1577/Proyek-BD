package com.example.laundry;

public class Staff {
    private int id;
    private String namaDepan;
    private String namaBelakang;
    private String alamat;
    private String noHp;
    private String tanggalMasuk;

    public Staff(int id, String namaDepan, String namaBelakang, String alamat, String noHp, String tanggalMasuk) {
        this.id = id;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.alamat = alamat;
        this.noHp = noHp;
        this.tanggalMasuk = tanggalMasuk;
    }

    public int getId() {
        return id;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }
}
