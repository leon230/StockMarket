package com.stockmarket.model;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
public class WalletItem {
    private String walletItemId;
    private String walletItemStockName;
    private Integer walletItemAmount;

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

    public Integer getWalletItemAmount() {
        return walletItemAmount;
    }

    public void setWalletItemAmount(Integer walletItemAmount) {
        this.walletItemAmount = walletItemAmount;
    }
}
// TODO check if integer is fine for amount value
