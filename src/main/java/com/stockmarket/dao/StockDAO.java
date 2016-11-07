package com.stockmarket.dao;

/**
 * Created by Leon on 2016-11-06.
 */
public interface StockDAO {
    public int getAmountAvailable(String stockName);
    public void updateAmountAvailable(String stockName, int stockAmount, String operationType);
}
