package com.stockmarket.dao;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

/**
 * Created by Leon on 2016-11-05.
 */
public class GetXmlData {

    public static void getResponse() throws Exception {
        String url = "http://webtask.future-processing.com:8068/stocks";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL(url).openStream());



}
}
