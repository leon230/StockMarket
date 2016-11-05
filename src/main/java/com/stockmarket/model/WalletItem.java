package com.stockmarket.model;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
public class WalletItem {
    private String walletItemId;
    private String walletItemStockName;
    private int walletItemAmount;
    private double walletItemPrice;
    private double walletItemValue;

    public String getWalletItemId() {
        return walletItemId;
    }

    public void setWalletItemId(String walletItemId) {
        this.walletItemId = walletItemId;
    }

    public String getWalletItemStockName() {
        return walletItemStockName;
    }

    public void setWalletItemStockName(String walletItemStockName) {
        this.walletItemStockName = walletItemStockName;
    }

    public int getWalletItemAmount() {
        return walletItemAmount;
    }

    public void setWalletItemAmount(int walletItemAmount) {
        this.walletItemAmount = walletItemAmount;
    }

    public double getWalletItemPrice() {
        return walletItemPrice;
    }

    public void setWalletItemPrice(double walletItemPrice) {
        this.walletItemPrice = walletItemPrice;
    }

    public double getWalletItemValue() {
        return walletItemValue;
    }

    public void setWalletItemValue(double walletItemValue) {
        this.walletItemValue = walletItemValue;
    }
}
// TODO check if integer is fine for amount value
