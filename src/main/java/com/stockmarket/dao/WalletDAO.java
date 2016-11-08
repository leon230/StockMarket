package com.stockmarket.dao;

import com.stockmarket.model.User;
import com.stockmarket.model.Wallet;
import com.stockmarket.model.WalletItem;

import java.util.List;

/**
 * Created by Leon on 2016-11-04.
 */
public interface WalletDAO {
    public void insertOrUpdate(Wallet wallet, User user); //inserts or updates data in userwallet table
    public void addItem (WalletItem walletItem, String walletId); //adds record to userwallet_d table
    public void updateResources (String walletId, double walletResources); //updates resources in userwallet table
    public void delete(int walletItemId); //deletes record on Sell action triggered by user.
    public Wallet getWallet(String username);
    public List<WalletItem> getWalletItems(String walletId);
}
