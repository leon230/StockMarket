//package com.stockmarket.validation;
//
//import com.stockmarket.dao.UserDAO;
//import com.stockmarket.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
///**
// * Created by Leon on 2016-11-06.
// */
//@Component
//public class BuyStockValidation implements Validator {
//    @Autowired
//    private UserDAO userDAO;
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return User.class.equals(clazz);
//    }
//    @Override
//    public void validate(Object target, Errors errors) {
//
//
//
//    }
//}
