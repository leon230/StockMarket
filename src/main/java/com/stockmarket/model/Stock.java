package com.stockmarket.model;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
public class Stock {
    private int stockId;
    private String stockCompany;
    private double stockValue;

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getStockCompany() {
        return stockCompany;
    }

    public void setStockCompany(String stockCompany) {
        this.stockCompany = stockCompany;
    }

    public double getStockValue() {
        return stockValue;
    }

    public void setStockValue(double stockValue) {
        this.stockValue = stockValue;
    }
}
