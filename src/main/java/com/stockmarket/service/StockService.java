package com.stockmarket.service;

/**
 * Created by lukasz.homik on 2016-11-24.
 */
public interface StockService {
    public void buyStock(String stockName, int stockAmount);
    public void sellStock(String stockName, int stockAmount);
}
