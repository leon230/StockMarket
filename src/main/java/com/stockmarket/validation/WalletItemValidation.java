package com.stockmarket.validation;

import com.stockmarket.model.WalletItem;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Leon on 2016-11-05.
 */
@Component
public class WalletItemValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return WalletItem.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
//        WalletItem walletItem = (WalletItem) target;
//
//        if(walletItem.getWalletItemAmount() == 0){
//            errors.rejectValue("walletItemAmount", "NotEmpty.StockForm.walletItemAmount");
//        }



    }
}
//TODO fuckup jak null w amount