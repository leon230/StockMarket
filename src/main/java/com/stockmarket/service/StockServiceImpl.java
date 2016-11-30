package com.stockmarket.service;

import com.stockmarket.dao.StockDAO;
import com.stockmarket.dao.WalletDAO;
import com.stockmarket.model.Wallet;
import com.stockmarket.model.WalletItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by lukasz.homik on 2016-11-24.
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private WalletDAO walletDAO;
    @Autowired
    private StockDAO stockDAO;
    private JdbcTemplate jdbcTemplate;
    public StockServiceImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void buyStock(WalletItem walletItem, Wallet wallet) {

        walletDAO.addItem(walletItem, wallet.getWalletId()); //Add item to user wallet
        //Updating userwallet_d, userwallet, stock_initial tables
        walletDAO.updateResources(wallet.getWalletId(), wallet.getWalletResource() - (walletItem.getWalletItemAmount()*walletItem.getWalletItemPrice()));

        String updateSql = "UPDATE stock_initial SET stock_amount=stock_amount - ? WHERE stock_name=?";
        jdbcTemplate.update(updateSql, walletItem.getWalletItemAmount(), walletItem.getWalletItemStockName());
    }

    @Override
    public void sellStock(String stockName, int stockAmount, int walletItemId, Wallet wallet, double resourceAmount) {

        walletDAO.delete(walletItemId);
        walletDAO.updateResources(wallet.getWalletId(), wallet.getWalletResource() + resourceAmount);
        String updateSql = "UPDATE stock_initial SET stock_amount=stock_amount + ? WHERE stock_name=?";
        jdbcTemplate.update(updateSql, stockAmount, stockName);
    }

    @Override
    public int getStockAmount(String stockName) {
        return stockDAO.getAmountAvailable(stockName);
    }
}
