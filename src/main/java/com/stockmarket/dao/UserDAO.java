package com.stockmarket.dao;

import com.stockmarket.model.User;
/**
 * Created by lukasz.homik on 2016-11-04.
 */
public interface UserDAO {

    public void insertOrUpdate(User user);
    public User getUser(String username);
    public int findUsername(String username);
//    public List<User> listuser(String userName);
}
