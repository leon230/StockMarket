package com.stockmarket.dao;

import com.stockmarket.model.User;
import com.stockmarket.model.Wallet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            String updateSql = "UPDATE userwallet SET WALLET_RESOURCE=? WHERE WALLET_ID=?";
            jdbcTemplate.update(updateSql, wallet.getWalletResource(), wallet.getWalletId());
        } else {
            // insert userwallet table
            String walletSql = "INSERT INTO userwallet (WALLET_ID, USER_ID, WALLET_RESOURCE)"
                    + " VALUES (?, ?, ?)";
            jdbcTemplate.update(walletSql, "w_" + user.getUserName(), user.getUserName(), wallet.getWalletResource());
            // insert userwallet_d table
            String walletDetailsSql = "INSERT INTO userwallet_d (WALLET_ID, STOCK_ID, STOCK_AMOUNT, UNIT_PRICE)"
                    + " VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(walletDetailsSql, "w_" + user.getUserName(), 1, 10, 10.9);
        }
    }

    @Override
    public void delete(int walletId) {

    }

    @Override
    public Wallet getWallet(String username) {

        String sql = "SELECT * FROM userwallet uw WHERE uw.user_id ='" + username + "'";

        return jdbcTemplate.queryForObject(sql,new RowMapper<Wallet>() {
            public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException {
                Wallet wallet = new Wallet();
                wallet.setWalletId(rs.getString("WALLET_ID"));
                wallet.setWalletResource(rs.getDouble("WALLET_RESOURCE"));
                return wallet;
            }
        });
    }
}
