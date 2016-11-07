package com.stockmarket.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Leon on 2016-11-06.
 */
public class StockDAOImpl implements StockDAO  {
    private JdbcTemplate jdbcTemplate;
    public StockDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int getAmountAvailable(String stockName) {
        String sql = "SELECT si.stock_amount FROM stock_initial si WHERE si.stock_name ='" + stockName + "'";

        return jdbcTemplate.queryForObject(sql,Integer.class );
    }

    @Override
    public void updateAmountAvailable(String stockName, int stockAmount, String operationType) {
        String updateSql;
        if(operationType.equals("Buy")){
            updateSql = "UPDATE stock_initial SET stock_amount=stock_amount - ? WHERE stock_name=?";
        }
        else{
            updateSql = "UPDATE stock_initial SET stock_amount=stock_amount + ? WHERE stock_name=?";
        }

        jdbcTemplate.update(updateSql, stockAmount, stockName);
    }
}
