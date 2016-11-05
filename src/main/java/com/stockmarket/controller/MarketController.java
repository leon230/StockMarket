package com.stockmarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmarket.dao.UserDAO;
import com.stockmarket.dao.WalletDAO;
import com.stockmarket.model.*;
import com.stockmarket.utils.ReadFromServer;
import com.stockmarket.validation.WalletItemValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
    @Autowired
    WalletItemValidation walletItemValidation;

    @InitBinder("StockForm")
    public void initBinder(WebDataBinder binder){
        binder.setValidator(walletItemValidation);
    }
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
        Stock stockJson = new Stock();
        String exc = "";

        ReadFromServer readFromServer = new ReadFromServer();

        String jsonData = readFromServer.getJSON();

        try {
            ObjectMapper mapper = new ObjectMapper();

            stockJson = mapper.readValue(jsonData, Stock.class);
        }

         catch (Exception e) {
            exc = e.getMessage();
        }


        user.setWallet(wallet);


        model.addObject("walletId",wallet.getWalletId());
        model.addObject("stockJson",stockJson);
        model.addObject("wallItems", wallet.getWalletStockList());




        model.setViewName("home");

        return model;
    }
    /**
     * Buy Stock form
     */
    @RequestMapping(value = "/home/buyStock", method = RequestMethod.GET)
    public ModelAndView newUser(ModelAndView model, HttpServletRequest request) {
        String stockName = request.getParameter("stockName");
        double stockBuyPrice = Double.parseDouble(request.getParameter("stockBuyPrice"));

        Wallet wallet = new Wallet();
        WalletItem walletItem = new WalletItem();

        wallet = walletDAO.getWallet(this.setUser());

        walletItem.setWalletItemStockName(stockName);
        walletItem.setWalletItemPrice(stockBuyPrice);

        model.addObject("walletId", wallet.getWalletId());
        model.addObject("StockForm", walletItem);
        model.setViewName("StockForm");

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
        if (result.hasErrors()) {
            model.setViewName("StockForm");
            return model;
        }
        else {
        String walletId = request.getParameter("walletId");
        walletDAO.addItem(walletItem, walletId);

        return new ModelAndView("redirect:/");
        }
    }
    public String setUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); //get logged in username


    }

}
// TODO parameters to another form using java