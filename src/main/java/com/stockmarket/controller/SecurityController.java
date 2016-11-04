package com.stockmarket.controller;

import com.stockmarket.dao.UserDAO;
import com.stockmarket.dao.WalletDAO;
import com.stockmarket.model.Stock;
import com.stockmarket.model.User;
import com.stockmarket.model.Wallet;
import com.stockmarket.model.WalletItem;
import com.stockmarket.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
@Controller
public class SecurityController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    WalletDAO walletDAO;
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
     * User registration
     */
    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public ModelAndView newUser(ModelAndView model) {
        User newUser = new User();
        Wallet wallet = new Wallet();
        Stock stock = new Stock();
        List<WalletItem> stockList = new ArrayList<WalletItem>();
        WalletItem walletItem = new WalletItem();


        stock.setStockCompany("Future Processing (FP)");
        stock.setStockId(1);
        stock.setStockValue(10.5);

        walletItem.setWalletItemStockName(stock.getStockCompany());
        walletItem.setWalletItemAmount(10);

        stockList.add(walletItem);

        wallet.setWalletStockList(stockList);

        newUser.setWallet(wallet);
        model.addObject("listStock", wallet.getWalletStockList());
        model.addObject("UserForm", newUser);
        model.setViewName("UserForm");

//        model.addObject("clusters", Ticket.getClustersList());
//        model.addObject("statuses", Ticket.getStatusesList());
//        model.addObject("priorities", Ticket.getPrioritiesList());
        return model;
    }
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ModelAndView CheckForm(@ModelAttribute("UserForm") @Validated User user, BindingResult result
            , ModelAndView model) {
        if (result.hasErrors()) {
//            ticket.initModelList();
            model.setViewName("UserForm");
//            model.addObject("clusters", Ticket.getClustersList());
//            model.addObject("statuses", Ticket.getStatusesList());
//            model.addObject("priorities", Ticket.getPrioritiesList());
            return model;
        }
        else {
            userDAO.insertOrUpdate(user);
            walletDAO.insertOrUpdate(user.getWallet(), user);
            return new ModelAndView("redirect:/");
        }
    }

    /**for 403 access denied page
     *
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

}
// TODO add check for username length to match DB