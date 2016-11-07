package com.stockmarket.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmarket.dao.StockDAO;
import com.stockmarket.model.Stock;
import com.stockmarket.model.StockItem;
import com.stockmarket.model.WalletItem;
import com.stockmarket.utils.ReadFromServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;

/**
 * Created by Leon on 2016-11-05.
 */
@Component
public class WalletItemValidation implements Validator {
    
    @Autowired
    StockDAO stockDAO;



    @Override
    public boolean supports(Class<?> clazz) {
        return WalletItem.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        WalletItem walletItem = (WalletItem) target;

        Stock stockJson = new Stock();
        String jsonData = ReadFromServer.getJSON();
        ObjectMapper mapper = new ObjectMapper();
        try {
            stockJson = mapper.readValue(jsonData, Stock.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if(walletItem.getWalletItemAmount() == 0){
//            errors.rejectValue("walletItemAmount", "NotEmpty.StockForm.walletItemAmount");
//        }
        if(walletItem.getWalletItemAmount() > stockDAO.getAmountAvailable(walletItem.getWalletItemStockName())){
            errors.rejectValue("walletItemAmount", "NoStockAmount.StockForm.walletItemAmount");
        }
        if(1==1){
            for (StockItem stockItem: stockJson.getItems()
                 ) {
                        if(stockItem.getName() == walletItem.getWalletItemStockName()){

                        }
                
            }
        }



    }
}
//TODO fuckup jak null w amount