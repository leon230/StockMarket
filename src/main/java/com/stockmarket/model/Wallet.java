package com.stockmarket.model;

import java.util.List;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
public class Wallet {
    private String walletId;
    private Long walletResource;
    private List<WalletItem> walletStockList;

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public Long getWalletResource() {
        return walletResource;
    }

    public void setWalletResource(Long walletResource) {
        this.walletResource = walletResource;
    }

    public List<WalletItem> getWalletStockList() {
        return walletStockList;
    }

    public void setWalletStockList(List<WalletItem> walletStockList) {
        this.walletStockList = walletStockList;
    }
}
// TODO check if Long is fine for wallet resource