package com.stockmarket.dao;

import com.stockmarket.model.User;
import com.stockmarket.model.Wallet;

/**
 * Created by Leon on 2016-11-04.
 */
public interface WalletDAO {
    public void insertOrUpdate(Wallet wallet, User user);
    public void delete(int walletId);
    public Wallet getWallet(String walletId);
}
