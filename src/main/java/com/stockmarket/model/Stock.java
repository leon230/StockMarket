package com.stockmarket.model;

import java.util.Date;
import java.util.List;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
public class Stock {
    private Date publicationDate;
    List<StockItem> items;

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<StockItem> getItems() {
        return items;
    }

    public void setItems(List<StockItem> items) {
        this.items = items;
    }
}
