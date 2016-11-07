package com.stockmarket.dao;

import com.stockmarket.model.StockItem;

import java.util.List;

/**
 * Created by Leon on 2016-11-06.
 */
public interface StockDAO {
    public int getAmountAvailable(String stockName);
    public void updateAmountAvailable(String stockName, int stockAmount, String operationType);
    public List<StockItem> getStockList();
}
