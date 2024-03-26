package com.example.laundry;

public class Session {
    private static com.mysql.cj.Session instance;
    private Staff currentStaff;
    private void Session(){

    }
    public static Session getInstance(){
        if (instance == null){
            instance = (com.mysql.cj.Session) new Session();
        }
        return (Session) instance;
    }
    public void setCurrentStaff(Staff staff){
        this.currentStaff = staff;
    }
    public Staff getCurrentStaff(){
        return currentStaff;
    }
}
