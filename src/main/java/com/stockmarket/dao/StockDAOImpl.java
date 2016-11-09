package com.stockmarket.dao;

import com.stockmarket.config.MvcConfiguration;
import com.stockmarket.model.StockItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        String sql = "SELECT si.stock_amount FROM " + MvcConfiguration.databaseSchema + ".stock_initial si WHERE si.stock_name ='" + stockName + "'";

        return jdbcTemplate.queryForObject(sql,Integer.class );
    }

    @Override
    public void updateAmountAvailable(String stockName, int stockAmount, String operationType) {
        String updateSql;
        if(operationType.equals("Buy")){
            updateSql = "UPDATE " + MvcConfiguration.databaseSchema + ".stock_initial SET stock_amount=stock_amount - ? WHERE stock_name=?";
        }
        else{
            updateSql = "UPDATE " + MvcConfiguration.databaseSchema + ".stock_initial SET stock_amount=stock_amount + ? WHERE stock_name=?";
        }

        jdbcTemplate.update(updateSql, stockAmount, stockName);
    }

    @Override
    public List<StockItem> getStockList() {
        String sql = "SELECT company_name, company_code FROM " + MvcConfiguration.databaseSchema + ".stocks s";
        List<StockItem> stockListr = jdbcTemplate.query(sql, new RowMapper<StockItem>() {

            @Override
            public StockItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                StockItem stockItem = new StockItem();
                stockItem.setName(rs.getString("COMPANY_NAME"));
                stockItem.setCode("COMPANY_CODE");
                stockItem.setPrice(0);
                stockItem.setUnit("0");
                return stockItem;
            }

        });

        return stockListr;



    }
}
