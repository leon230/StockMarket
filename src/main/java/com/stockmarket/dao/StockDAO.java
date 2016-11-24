package com.stockmarket.dao;

import com.stockmarket.model.StockItem;
import java.util.List;

/**
 * Created by Leon on 2016-11-06.
 */
public interface StockDAO {
    public int getAmountAvailable(String stockName); //Retrieves amount of stock available from stock_initial table
    public List<StockItem> getStockList(); //Retrieves list of stocks for initial wallet values
}
