package com.example.ibbl.Model;

public class LoanApply {
    String ID;
    String Message;
    String Status;
    String Taka;
    String Time;
    String UserName;

    public LoanApply(String ID, String message, String status, String taka, String time, String userName) {
        this.ID = ID;
        Message = message;
        Status = status;
        Taka = taka;
        Time = time;
        UserName = userName;
    }

    public LoanApply() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTaka() {
        return Taka;
    }

    public void setTaka(String taka) {
        Taka = taka;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
