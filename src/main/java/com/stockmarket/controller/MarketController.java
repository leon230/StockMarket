package com.stockmarket.controller;

import com.stockmarket.dao.StockDAO;
import com.stockmarket.dao.UserDAO;
import com.stockmarket.dao.WalletDAO;
import com.stockmarket.model.User;
import com.stockmarket.model.Wallet;
import com.stockmarket.model.WalletItem;
import com.stockmarket.validation.WalletItemValidation;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
    StockDAO stockDAO;
    @Autowired
    WalletItemValidation walletItemValidation;

    @InitBinder("StockForm")
    public void initBinder(WebDataBinder binder){
        binder.setValidator(walletItemValidation);
    }
/**
 * Home mapping
 * Automatic redirect from / to /home
 */
    @RequestMapping(value="/")
    public String redirect(){
        return "redirect:/home";
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView marketOverview(ModelAndView model) throws IOException{
/**
 * User and Wallet set up.
 */
        Wallet wallet = new Wallet();
        User user = new User();
        user = userDAO.getUser(UserController.setUser());
        wallet = walletDAO.getWallet(UserController.setUser());
        wallet.setWalletStockList(walletDAO.getWalletItems(wallet.getWalletId()));
/**
 * JSON data retrieval using JAVA
 * Removed due to AJAX query use. query function in LoadData.js refreshData()
 * This change requires changing access control setting in the browser. Otherwise Access-Control-Allow-Origin occurs.
 */
//        Stock stockJson = new Stock();
//        try {
//            String jsonData = ReadFromServer.getJSON();
//            ObjectMapper mapper = new ObjectMapper();
//            stockJson = mapper.readValue(jsonData, Stock.class);
//            model.addObject("connectionErrorMsg",null);
//        }
//        catch (RuntimeException e){
//            model.addObject("connectionErrorMsg","No connection to stock server...");
//        }
        NumberFormat formatter;
        formatter = new DecimalFormat("0000.000");
        user.setWallet(wallet);
        model.addObject("walletId",wallet.getWalletId());
        model.addObject("walletResources",formatter.format(wallet.getWalletResource()));
//        model.addObject("stockJson",stockJson);
        model.addObject("wallItems", wallet.getWalletStockList());
        model.setViewName("home");

        return model;
    }
/**
 * Buy Stock form mapping.
 * Mapped when user clicks Buy link stock on first table
 * It retrieves the data from main page stock table to populate of the form.
 */
    @RequestMapping(value = "**/buyStock", method = RequestMethod.GET)
    public ModelAndView buyStock(ModelAndView model, HttpServletRequest request) {

        String stockName = request.getParameter("stockName");
        double stockBuyPrice = Double.parseDouble(request.getParameter("stockBuyPrice"));
        int stockUnit = Integer.parseInt(request.getParameter("stockUnit"));
/**
 * Query for user wallet details
 */
        Wallet wallet = new Wallet();
        WalletItem walletItem = new WalletItem();
        wallet = walletDAO.getWallet(UserController.setUser()); //setUser retrieves username from logged in user
        walletItem.setWalletItemStockName(stockName);
        walletItem.setWalletItemPrice(stockBuyPrice);

        model.addObject("walletId", wallet.getWalletId());
        model.addObject("walletResources", wallet.getWalletResource());
        model.addObject("StockForm", walletItem);
        model.addObject("stockUnit", stockUnit);
        model.addObject("stockAmount", stockDAO.getAmountAvailable(stockName));
        model.setViewName("StockForm");

        return model;
    }
/**
 *  Mapping when user confirms to buy a stock from StockForm
 */
    @RequestMapping(value = "**/addStock", method = RequestMethod.POST)
    public ModelAndView addStock(HttpServletRequest request, @ModelAttribute("StockForm") @Validated WalletItem walletItem, BindingResult result
            , ModelAndView model) {
        if (result.hasErrors()) {
            model.addObject("stockAmount", stockDAO.getAmountAvailable(walletItem.getWalletItemStockName())); //refreshes stock amount available
            model.setViewName("StockForm");
            return model;
        }
        else {
            Wallet wallet = new Wallet();
            wallet = walletDAO.getWallet(UserController.setUser()); //setUser retrieves username from logged in user
            //Updating userwallet_d, userwallet, stock_initial tables
            walletDAO.addItem(walletItem, wallet.getWalletId());
            walletDAO.updateResources(wallet.getWalletId(), wallet.getWalletResource() - (walletItem.getWalletItemAmount()*walletItem.getWalletItemPrice()));
            stockDAO.updateAmountAvailable(walletItem.getWalletItemStockName(), walletItem.getWalletItemAmount(), "Buy");

        return new ModelAndView("redirect:/");
        }
    }
/**
 * Sell stock mapping
 * Mapped when user clicks Sell link stock on second table
 */
    @RequestMapping(value = "**/sellStock", method = RequestMethod.GET)
    public ModelAndView sellStock(HttpServletRequest request) {
        //Retrieve data from URL
        int walletItemId = Integer.parseInt(request.getParameter("walletItemId"));
        int stockAmount = Integer.parseInt(request.getParameter("stockAmount"));
        double resourceAmount = Double.parseDouble(request.getParameter("resourceAmount"));
        String stockName = request.getParameter("stockName");

        Wallet wallet = new Wallet();
        wallet = walletDAO.getWallet(UserController.setUser());
        //Updating userwallet_d, userwallet, stock_initial tables
        walletDAO.delete(walletItemId);
        walletDAO.updateResources(wallet.getWalletId(), wallet.getWalletResource() + resourceAmount);
        stockDAO.updateAmountAvailable(stockName, stockAmount, "Sell");
        return new ModelAndView("redirect:/");
    }
}