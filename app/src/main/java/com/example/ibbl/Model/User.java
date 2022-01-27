package com.example.ibbl.Model;

public class User {

    String Deposite;
    String Email;
    String ID;
    String Name;
    String Password;
    String Profile_img;
    String Profit;
    String Total_Due;
    String Total_profit;
    String Total_tk;

    public User(String deposite, String email, String ID, String name, String password, String profile_img, String profit, String total_Due, String total_profit, String total_tk) {
        Deposite = deposite;
        Email = email;
        this.ID = ID;
        Name = name;
        Password = password;
        Profile_img = profile_img;
        Profit = profit;
        Total_Due = total_Due;
        Total_profit = total_profit;
        Total_tk = total_tk;
    }

    public User() {
    }

    public String getDeposite() {
        return Deposite;
    }

    public void setDeposite(String deposite) {
        Deposite = deposite;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getProfile_img() {
        return Profile_img;
    }

    public void setProfile_img(String profile_img) {
        Profile_img = profile_img;
    }

    public String getProfit() {
        return Profit;
    }

    public void setProfit(String profit) {
        Profit = profit;
    }

    public String getTotal_Due() {
        return Total_Due;
    }

    public void setTotal_Due(String total_Due) {
        Total_Due = total_Due;
    }

    public String getTotal_profit() {
        return Total_profit;
    }

    public void setTotal_profit(String total_profit) {
        Total_profit = total_profit;
    }

    public String getTotal_tk() {
        return Total_tk;
    }

    public void setTotal_tk(String total_tk) {
        Total_tk = total_tk;
    }
}
