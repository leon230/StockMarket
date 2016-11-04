package com.stockmarket.model;

import java.util.List;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
public class Wallet {
    private String walletId;
    private Long walletResource;
    private List<WalletItem> walletStockList;
}
// TODO check if Long is fine for wallet resource