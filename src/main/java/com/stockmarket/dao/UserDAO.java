package com.stockmarket.dao;

import com.stockmarket.model.User;
/**
 * Created by lukasz.homik on 2016-11-04.
 */
public interface UserDAO {

    public void insertOrUpdate(User user);
    public void delete(int userId);
    public User getUser(String username);
//    public List<User> listuser(String userName);
}
