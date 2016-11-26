package com.stockmarket.service;

import com.stockmarket.model.Wallet;
import com.stockmarket.model.WalletItem;

/**
 * Created by lukasz.homik on 2016-11-24.
 */
public interface StockService {
    public void buyStock(WalletItem walletItem, Wallet wallet);
    public void sellStock(String stockName, int stockAmount);
}
