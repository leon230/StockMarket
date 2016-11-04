package com.stockmarket.dao;

import com.stockmarket.model.User;
import java.util.List;
/**
 * Created by lukasz.homik on 2016-11-04.
 */
public interface UserDAO {
    public List<User> listuser(String userName);
}
