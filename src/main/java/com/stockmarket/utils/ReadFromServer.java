package com.stockmarket.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * Created by Leon on 2016-11-05.
 */
/**
 * Method used only to retrieve JSON from the server
 * Not used due to AJAX query. query function in LoadData.js refreshData()
 */
public class ReadFromServer {

    public static String getJSON() {
        try {

            URL url = new URL("http://webtask.future-processing.com:8068/stocks");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            //Server output
            while ((output = br.readLine()) != null) {

                return output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }



}
