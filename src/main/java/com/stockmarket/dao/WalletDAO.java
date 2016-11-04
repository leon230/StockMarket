package com.stockmarket.dao;

import com.stockmarket.model.User;
import com.stockmarket.model.Wallet;
import java.util.List;

/**
 * Created by Leon on 2016-11-04.
 */
public interface WalletDAO {
    public void insertOrUpdate(Wallet wallet, User user);
    public void delete(int walletId);
    public List<Wallet> listWallet(String wallet);
}
