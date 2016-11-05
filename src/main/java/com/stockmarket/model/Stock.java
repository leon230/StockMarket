package com.stockmarket.model;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
public class Stock {
    private int stockId;
    private String stockCompany;
    private double stockBuyPrice;
    private int stockUnit;

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

    public double getStockBuyPrice() {
        return stockBuyPrice;
    }

    public void setStockBuyPrice(double stockBuyPrice) {
        this.stockBuyPrice = stockBuyPrice;
    }

    public int getStockUnit() {
        return stockUnit;
    }

    public void setStockUnit(int stockUnit) {
        this.stockUnit = stockUnit;
    }
}
