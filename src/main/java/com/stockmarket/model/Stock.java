package com.stockmarket.model;

import java.util.List;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
public class Stock {
    private String publicationDate;
    List<StockItem> items;

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<StockItem> getItems() {
        return items;
    }

    public void setItems(List<StockItem> items) {
        this.items = items;
    }
}
