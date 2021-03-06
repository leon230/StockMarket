package com.stockmarket.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmarket.dao.StockDAO;
import com.stockmarket.model.Stock;
import com.stockmarket.model.StockItem;
import com.stockmarket.model.WalletItem;
import com.stockmarket.utils.ReadJSONData;
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
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean supports(Class<?> clazz) {
        return WalletItem.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        WalletItem walletItem = (WalletItem) target;

        Stock stockJson = new Stock();
        String jsonData = ReadJSONData.getStockDataJSON();
        try {
            stockJson = mapper.readValue(jsonData, Stock.class);
        } catch (IOException e) {
            errors.reject("CantGetJson.StockForm.getJsonError");
        }

        if(walletItem.getWalletItemAmount() > stockDAO.getAmountAvailable(walletItem.getWalletItemStockName())){
            errors.rejectValue("walletItemAmount", "NoStockAmount.StockForm.walletItemAmount");
        }
            for (StockItem stockItem: stockJson.getItems()
                 ) {
                        if(stockItem.getName().equals(walletItem.getWalletItemStockName())){
                            if(stockItem.getPrice() != walletItem.getWalletItemPrice()){
                                errors.rejectValue("walletItemPrice", "IncorrectPrice.StockForm.walletItemPrice");
                            }
                        }
            }
    }
}