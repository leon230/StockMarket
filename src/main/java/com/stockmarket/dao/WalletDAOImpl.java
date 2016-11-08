package com.stockmarket.dao;

import com.stockmarket.model.User;
import com.stockmarket.model.Wallet;
import com.stockmarket.model.WalletItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            String updateSql = "UPDATE userwallet SET WALLET_RESOURCE=? WHERE WALLET_ID=?";
            jdbcTemplate.update(updateSql, wallet.getWalletResource(), wallet.getWalletId());
        } else {
            // insert userwallet table
            String walletSql = "INSERT INTO userwallet (WALLET_ID, USER_ID, WALLET_RESOURCE)"
                    + " VALUES (?, ?, IFNULL(?,0))";
            jdbcTemplate.update(walletSql, "w_" + user.getUserName(), user.getUserName(), wallet.getWalletResource());
        }
    }
    @Override
    public void updateResources(String walletId, double walletResources){
        String updateSql = "UPDATE userwallet SET WALLET_RESOURCE=? WHERE WALLET_ID=?";
        jdbcTemplate.update(updateSql, walletResources, walletId);
    }
    @Override
    public void addItem(WalletItem walletItem, String walletId){

        String itemSql = "INSERT INTO userwallet_d (WALLET_ID, STOCK_NAME, STOCK_AMOUNT, UNIT_PRICE)"
                + " VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(itemSql, walletId, walletItem.getWalletItemStockName(), walletItem.getWalletItemAmount(), walletItem.getWalletItemPrice());
    }

    @Override
    public void delete(int walletItemId) {
        String sql = "DELETE FROM userwallet_d WHERE wallet_item_id=?";
        jdbcTemplate.update(sql, walletItemId);
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
    @Override
    public List<WalletItem> getWalletItems(String walletId){
        String sql = "SELECT uw.wallet_id, uwd.wallet_item_id, uwd.stock_name, uwd.stock_amount, uwd.unit_price, " +
                "uwd.stock_amount * uwd.unit_price AS ITEM_VALUE FROM userwallet uw, userwallet_d uwd WHERE uw.wallet_id = uwd.wallet_id " +
                "AND uw.wallet_id ='" + walletId + "'";
        List<WalletItem> itemList = jdbcTemplate.query(sql, new RowMapper<WalletItem>() {

            @Override
            public WalletItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                WalletItem walletItem = new WalletItem();

                walletItem.setWalletItemId(rs.getString("WALLET_ITEM_ID"));
                walletItem.setWalletItemStockName(rs.getString("STOCK_NAME"));
                walletItem.setWalletItemAmount(rs.getInt("STOCK_AMOUNT"));
                walletItem.setWalletItemPrice(rs.getDouble("UNIT_PRICE"));
                walletItem.setWalletItemValue(rs.getDouble("ITEM_VALUE"));

                return walletItem;
            }

        });

        return itemList;


    }
}