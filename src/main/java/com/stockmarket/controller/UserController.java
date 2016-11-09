package com.stockmarket.controller;

import com.stockmarket.dao.StockDAO;
import com.stockmarket.dao.UserDAO;
import com.stockmarket.dao.WalletDAO;
import com.stockmarket.model.StockItem;
import com.stockmarket.model.User;
import com.stockmarket.model.Wallet;
import com.stockmarket.model.WalletItem;
import com.stockmarket.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    WalletDAO walletDAO;
    @Autowired
    StockDAO stockDAO;
    @Autowired
    UserValidation userValidation;


    @InitBinder("UserForm")
    public void initBinder(WebDataBinder binder){
        binder.setValidator(userValidation);
    }
    /**
     * Security mapping
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;
    }
/**
 * User registration form mapping
 */
    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public ModelAndView newUser(ModelAndView model) {
        User newUser = new User();
        Wallet wallet = new Wallet();

        newUser.setWallet(wallet);
        model.addObject("listStock", wallet.getWalletStockList());
        model.addObject("UserForm", newUser);
        model.setViewName("UserForm");

        return model;
    }

    /**
     * Mapping for user creation submit button
     */
    @RequestMapping(value = "**/saveUser", method = RequestMethod.POST)
    public ModelAndView CheckForm(@ModelAttribute("UserForm") @Validated User user, BindingResult result
            , ModelAndView model,HttpServletRequest request) {
        if (result.hasErrors()) {
            model.setViewName("UserForm");
            return model;
        }
        else {
            //Inserting new data to users and userwallet tables
            userDAO.insertOrUpdate(user);
            walletDAO.insertOrUpdate(user.getWallet(), user);
            //redirect depends whether user is editing profile or creating new user
            if(user.getUserId() > 0) {
                return new ModelAndView("redirect:/");
            }
            else {
                return new ModelAndView("redirect:/home/InitialWallet");
            }
        }
    }
/**
 * User Edit mapping.
 */
    @RequestMapping(value = "**/editUser", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        User editedUser = userDAO.getUser(username); //Retrieving username
        editedUser.setWallet(walletDAO.getWallet(username)); //setting up wallet

        ModelAndView model = new ModelAndView("UserForm");
        model.addObject("formType","Edit");
        model.addObject("UserForm", editedUser);

        return model;
    }
    /**
     * Initial wallet
     */
    @RequestMapping(value = "/home/InitialWallet", method = RequestMethod.GET)
    public ModelAndView initialWallet(HttpServletRequest request) {
        String username = request.getParameter("username");

        Wallet wallet = new Wallet();

        List<WalletItem> walletItemList = new ArrayList<>();
        //Getting stock list from stocks table and populate data in walletStockList
        for (StockItem stockItem: stockDAO.getStockList()
             ) {
                WalletItem walletItem = new WalletItem();
                walletItem.setWalletItemAmount(0);
                walletItem.setWalletItemPrice(0);
                walletItem.setWalletItemStockName(stockItem.getName());
                walletItemList.add(walletItem);
        }

        wallet.setWalletStockList(walletItemList);

        ModelAndView model = new ModelAndView("InitialWallet");
        model.addObject("InitialWallet", wallet);

        return model;
    }

    /**
     *  Mapping for saving initial wallet
     */
    @RequestMapping(value = "**/saveInitialWallet", method = RequestMethod.POST)
    public ModelAndView saveInitialWallet(@ModelAttribute("InitialWallet") Wallet wallet) {

        List<WalletItem> walletItems = wallet.getWalletStockList();
        //Loops wallet Items and saves only those which price and amount is not equal to 0
        for (WalletItem walletItem : walletItems) {
            if(walletItem.getWalletItemPrice() != 0 && walletItem.getWalletItemAmount() != 0)
            walletDAO.addItem(walletItem, walletDAO.getWallet(setUser()).getWalletId());
        }
        return new ModelAndView("redirect:/");
    }
     //Retrieves loged user
    public static String setUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); //get logged in username
    }

}