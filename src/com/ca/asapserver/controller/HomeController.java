package com.ca.asapserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

/**
 * HomeController
 * 
 * MVC Controller for Hive Home Page View.
 * 
 * @author Rodrigo Carvalho
 * 
 */
@Controller
@RequestMapping("/home")
public class HomeController{
 
   @RequestMapping(method = RequestMethod.GET)
   public String printHello(ModelMap model) {
      model.addAttribute("message", "Written by Rodrigo Carvalho.");
      return "home";
   }
}