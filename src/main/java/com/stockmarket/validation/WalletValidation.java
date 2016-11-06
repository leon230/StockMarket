//package com.stockmarket.validation;
//
//import com.stockmarket.model.Wallet;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
///**
// * Created by Leon on 2016-11-06.
// */
//
//public class WalletValidation implements Validator {
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Wallet.class.equals(clazz);
//    }
//    @Override
//    public void validate(Object target, Errors errors) {
//        Wallet wallet = (Wallet) target;
//
//        if(wallet.getWalletResource() == 0 || wallet.getWalletResource() == null){
//            errors.rejectValue("wallet.walletResource", "NotEmpty.StockForm.wallet.walletResource");
//        }
//
//
//
//    }
//}
