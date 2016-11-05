package com.stockmarket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Leon on 2016-11-05.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class items {
    private String publicationDate;
    List<StockJson> items;

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<StockJson> getItems() {
        return items;
    }

    public void setItems(List<StockJson> items) {
        this.items = items;
    }
}
