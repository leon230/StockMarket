package com.stockmarket.model;

import java.util.List;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
public class Wallet {
    private String walletId;
    private Double walletResource;
    private List<WalletItem> walletStockList;

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public Double getWalletResource() {
        return walletResource;
    }

    public void setWalletResource(Double walletResource) {
        this.walletResource = walletResource;
    }

    public List<WalletItem> getWalletStockList() {
        return walletStockList;
    }

    public void setWalletStockList(List<WalletItem> walletStockList) {
        this.walletStockList = walletStockList;
    }
}