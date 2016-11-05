package com.stockmarket.controller;

import com.stockmarket.dao.UserDAO;
import com.stockmarket.dao.WalletDAO;
import com.stockmarket.model.Stock;
import com.stockmarket.model.User;
import com.stockmarket.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        user = userDAO.getUser(name);
        wallet = walletDAO.getWallet(name);
        wallet.setWalletStockList(walletDAO.getWalletItems(wallet.getWalletId()));


        user.setWallet(wallet);






        Stock stock = new Stock();
        stock.setStockId(1);
        stock.setStockCompany("Future Processing (FP)");
        stock.setStockValue(10.6);
        stock.setStockUnit(1);

        Stock stock2 = new Stock();
        stock2.setStockId(2);
        stock2.setStockCompany("FP Lab (FPL)");
        stock2.setStockValue(200.4);
        stock2.setStockUnit(100);

        stockList.add(stock);
        stockList.add(stock2);


        model.addObject("walletId",wallet.getWalletId());
        model.addObject("stockList", stockList);
        model.addObject("wallItems", wallet.getWalletStockList());




        model.setViewName("home");

        return model;
    }




}
