package com.stockmarket.model;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
public class User {
    private int userId;
    private String userName;
    private String userPass;
    private String userRole;

    public User(){}

    public User(int userId, String userName, String userPass, String userRole){
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
        this.userRole = userRole;
    }

    public int getUserId(){return userId;}

    public void setUserId(int val){this.userId = val;}

    public String getUserName(){return userName;}

    public void setUserName(String val){this.userName = val;}

    public String getUserPass(){return userPass;}

    public void setUserPass(String val){this.userPass = val;}

    public String getUserRole(){return userRole;}

    public void setUserRole(String val){this.userRole = val;}



}