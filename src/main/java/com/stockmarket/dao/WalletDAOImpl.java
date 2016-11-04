package com.stockmarket.dao;

import com.stockmarket.model.User;
import com.stockmarket.model.Wallet;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Leon on 2016-11-04.
 */
public class WalletDAOImpl implements WalletDAO {
    private JdbcTemplate jdbcTemplate;

    public WalletDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public void insertOrUpdate(Wallet wallet, User user) {
        if (wallet.getWalletId().length() > 0 ) {
            //update
//            String sql = "UPDATE ticketz SET TICKET_NO=?, TICKET_TITLE=?, TICKET_OWNER=?, "
//                    + "CLUSTER=?, OPEN_DATE=?, CLOSE_DATE=?, DESCRIPTION=?" +
//                    ",REPORTED_BY=?, PRIORITY=?, STATUS=?, ACC_OWNER=?, REQUEST_DATE=?,DUE_DATE =? WHERE ID=?";
//            jdbcTemplate.update(sql, user.getUserName());
        } else {
            // insert userwallet table
            String walletSql = "INSERT INTO userwallet (WALLET_ID, USER_ID, WALLET_RESOURCE)"
                    + " VALUES (?, ?, ?)";
            jdbcTemplate.update(walletSql, "w_" + user.getUserName(), user.getUserName(), wallet.getWalletResource());
            // insert userrole table
            String walletDetailsSql = "INSERT INTO userwallet_d (WALLET_ID, STOCK_ID, STOCK_AMOUNT, UNIT_PRICE)"
                    + " VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(walletDetailsSql, "w_" + user.getUserName(), 1, 10, 10.9);
        }
    }

    @Override
    public void delete(int walletId) {

    }

    @Override
    public List<Wallet> listWallet(String wallet) {
        return null;
    }
}
