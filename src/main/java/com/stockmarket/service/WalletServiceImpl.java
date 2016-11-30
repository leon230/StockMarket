package com.stockmarket.service;

import com.stockmarket.dao.WalletDAO;
import com.stockmarket.model.Wallet;
import com.stockmarket.model.WalletItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Leon on 2016-11-30.
 */
@Component
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletDAO walletDAO;

    @Override
    public Wallet getWallet(String username) {
        return walletDAO.getWallet(username);
    }

    @Override
    public WalletItem createWalletItem(String stockName, double stockBuyPrice) {
        WalletItem walletItem = new WalletItem();
        walletItem.setWalletItemStockName(stockName);
        walletItem.setWalletItemPrice(stockBuyPrice);
        return walletItem;
    }
}
