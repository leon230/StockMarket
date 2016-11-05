package com.stockmarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmarket.dao.UserDAO;
import com.stockmarket.dao.WalletDAO;
import com.stockmarket.model.*;
import com.stockmarket.utils.ReadFromServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
@Controller

public class MarketController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    WalletDAO walletDAO;
/**
 * Home mapping
 */
    @RequestMapping(value="/")
    public String redirect(){
        return "redirect:/home";
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView marketOverview(ModelAndView model) throws IOException{

        List<Stock> stockList = new ArrayList<>();

        Wallet wallet = new Wallet();
        User user = new User();
        user = userDAO.getUser(this.setUser());
        wallet = walletDAO.getWallet(this.setUser());
        wallet.setWalletStockList(walletDAO.getWalletItems(wallet.getWalletId()));
//        List<items> stockJsonList = new ArrayList<>();
        items stockJsonList = new items();
        String exc = "";

        ReadFromServer readFromServer = new ReadFromServer();

        String jsonData = readFromServer.getJSON();

        try {
            StockJson stockJson = new StockJson();
            ObjectMapper mapper = new ObjectMapper();

            stockJsonList = mapper.readValue(jsonData, items.class);
        }

         catch (Exception e) {
            exc = e.getMessage();
        }







        user.setWallet(wallet);

        Stock stock = new Stock();
        stock.setStockId(1);
        stock.setStockCompany("Future Processing (FP)");
        stock.setStockBuyPrice(10.6);
        stock.setStockUnit(1);

        Stock stock2 = new Stock();
        stock2.setStockId(2);
        stock2.setStockCompany("FP Lab (FPL)");
        stock2.setStockBuyPrice(200.4);
        stock2.setStockUnit(100);

        stockList.add(stock);
        stockList.add(stock2);


        model.addObject("walletId",wallet.getWalletId());
        model.addObject("stockJsonList",stockJsonList);
        model.addObject("exc",exc);
        model.addObject("stockList", stockList);
        model.addObject("wallItems", wallet.getWalletStockList());




        model.setViewName("home");

        return model;
    }
    /**
     * Buy Stock form
     */
    @RequestMapping(value = "/home/buyStock", method = RequestMethod.GET)
    public ModelAndView newUser(ModelAndView model, HttpServletRequest request) {
        int stockId = Integer.parseInt(request.getParameter("stockId"));
        String stockName = request.getParameter("stockName");
        double stockBuyPrice = Double.parseDouble(request.getParameter("stockBuyPrice"));



        Wallet wallet = new Wallet();
        wallet = walletDAO.getWallet(this.setUser());


        WalletItem walletItem = new WalletItem();


        walletItem.setWalletItemStockName(stockName);
        walletItem.setWalletItemPrice(stockBuyPrice);


//        model.addObject("walletItem", walletItem);
        model.addObject("walletId", wallet.getWalletId());
        model.addObject("stockName", stockName);
        model.addObject("StockForm", walletItem);
        model.setViewName("StockForm");
//
        return model;
    }

    @RequestMapping(value = "/home/json", method = RequestMethod.GET)
    public ModelAndView newUser(ModelAndView model) {

        model.setViewName("json");
//
        return model;
    }

    @RequestMapping(value = "home/addStock", method = RequestMethod.POST)
    public ModelAndView CheckForm(HttpServletRequest request, @ModelAttribute("StockForm") @Validated WalletItem walletItem, BindingResult result
            , ModelAndView model) {
//        if (result.hasErrors()) {
////            ticket.initModelList();
//            model.setViewName("UserForm");
////            model.addObject("clusters", Ticket.getClustersList());
////            model.addObject("statuses", Ticket.getStatusesList());
////            model.addObject("priorities", Ticket.getPrioritiesList());
//            return model;
//        }
//        else {
        String walletId = request.getParameter("walletId");
        walletDAO.addItem(walletItem, walletId);



        return new ModelAndView("redirect:/");
//        }
    }
    public String setUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); //get logged in username


    }

}
