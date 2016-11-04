package com.stockmarket.controller;

import com.stockmarket.dao.UserDAO;
import com.stockmarket.model.User;
import com.stockmarket.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
@Controller
public class SecurityController {

    @Autowired
    private UserDAO userDAO;
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
        SimpleDateFormat printFormat = new SimpleDateFormat("yyyyMMdd_kkmmss");
        Date date = new Date();

//        newUser.setNumber(System.getProperty("user.name") + "_" + printFormat.format(date));
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
//        if (result.hasErrors()) {
////            ticket.initModelList();
//            model.setViewName("UserForm");
////            model.addObject("clusters", Ticket.getClustersList());
////            model.addObject("statuses", Ticket.getStatusesList());
////            model.addObject("priorities", Ticket.getPrioritiesList());
//            return model;
//        }
//        else {
//            ticketDAO.saveOrUpdate(ticket);
//            return new ModelAndView("redirect:/");
//        }
        user.setUserRole("ROLE_USER");
        user.setUserWalletId(user.getUserName() + "_wallet");
        userDAO.saveOrUpdate(user);
        return new ModelAndView("redirect:/");
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