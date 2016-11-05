package com.stockmarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmarket.dao.UserDAO;
import com.stockmarket.dao.WalletDAO;
import com.stockmarket.model.Stock;
import com.stockmarket.model.User;
import com.stockmarket.model.Wallet;
import com.stockmarket.model.WalletItem;
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

        Wallet wallet = new Wallet();
        User user = new User();
        user = userDAO.getUser(this.setUser());
        wallet = walletDAO.getWallet(this.setUser());
        wallet.setWalletStockList(walletDAO.getWalletItems(wallet.getWalletId()));

        Stock stockJson = new Stock();
        String jsonData = ReadFromServer.getJSON();
        ObjectMapper mapper = new ObjectMapper();
        stockJson = mapper.readValue(jsonData, Stock.class);
        user.setWallet(wallet);

        model.addObject("walletId",wallet.getWalletId());
        model.addObject("walletResources",wallet.getWalletResource());
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

    @RequestMapping(value = "home/addStock", method = RequestMethod.POST)
    public ModelAndView CheckForm(HttpServletRequest request, @ModelAttribute("StockForm") @Validated WalletItem walletItem, BindingResult result
            , ModelAndView model) {
        if (result.hasErrors()) {
            model.setViewName("StockForm");
            return model;
        }
        else {
            Wallet wallet = new Wallet();
            wallet = walletDAO.getWallet(this.setUser());
            String walletId = request.getParameter("walletId");
            walletDAO.addItem(walletItem, walletId);
            walletDAO.updateResources(walletId, wallet.getWalletResource() - (walletItem.getWalletItemAmount()*walletItem.getWalletItemPrice()));

        return new ModelAndView("redirect:/");
        }
    }

    /**
     * Retrieves loged user
     */
    public String setUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); //get logged in username


    }
    @RequestMapping(value = "/home/json", method = RequestMethod.GET)
    public ModelAndView newUser(ModelAndView model) {
        model.setViewName("json");
        return model;
    }

}
// TODO parameters to another form using java