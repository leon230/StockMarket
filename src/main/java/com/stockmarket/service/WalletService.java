package com.stockmarket.service;

import com.stockmarket.model.Wallet;
import com.stockmarket.model.WalletItem;

/**
 * Created by Leon on 2016-11-30.
 */
public interface WalletService {
    public Wallet getWallet(String username);
    public WalletItem createWalletItem(String stockName, double stockBuyPrice);
}
