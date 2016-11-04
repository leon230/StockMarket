package com.stockmarket.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lukasz.homik on 2016-11-04.
 */
@Controller

public class MarketController {

/**
 * Home mapping
 */
    @RequestMapping(value="/")
    public String redirect(){
        return "redirect:/home";
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView marketOverview(ModelAndView model) throws IOException{

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        model.addObject("currentDate",cal.getTime());
        model.addObject("title", "Stock Main");
        model.setViewName("home");

        return model;
    }
}
