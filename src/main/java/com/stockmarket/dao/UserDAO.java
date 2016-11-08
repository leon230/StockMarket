package com.stockmarket.dao;

import com.stockmarket.model.User;
/**
 * Created by lukasz.homik on 2016-11-04.
 */
public interface UserDAO {

    public void insertOrUpdate(User user); //Insert or updates user
    public User getUser(String username); //get user data
    public int findUsername(String username); //finds only username. Used when creating new user
}
