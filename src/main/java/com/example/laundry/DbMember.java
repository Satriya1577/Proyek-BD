package com.example.laundry;

public class DbMember {
    private String first_name,last_name,alamat;
    private  int no_hp,member_id;


    public DbMember(int member_id, String first_name, String last_name, String alamat, int no_hp) {
        this.member_id = member_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.alamat = alamat;
        this.no_hp = no_hp;
    }


    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(int no_hp) {
        this.no_hp = no_hp;
    }
}
