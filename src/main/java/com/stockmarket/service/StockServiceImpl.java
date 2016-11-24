package com.stockmarket.service;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by lukasz.homik on 2016-11-24.
 */
public class StockServiceImpl implements StockService {

    private JdbcTemplate jdbcTemplate;
    public StockServiceImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void buyStock(String stockName, int stockAmount) {
        String updateSql = "UPDATE stock_initial SET stock_amount=stock_amount - ? WHERE stock_name=?";
        jdbcTemplate.update(updateSql, stockAmount, stockName);
    }

    @Override
    public void sellStock(String stockName, int stockAmount) {
        String updateSql = "UPDATE stock_initial SET stock_amount=stock_amount + ? WHERE stock_name=?";
        jdbcTemplate.update(updateSql, stockAmount, stockName);
    }
}
