package com.ca.asapserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import com.ca.asapserver.initiative.DeliverableManager;
import com.ca.asapserver.vo.DeliverableVo;

/**
 * HomeController
 * 
 * MVC Controller for Hive Home Page View.
 * 
 * @author Rodrigo Carvalho
 * 
 */
@Controller
public class HomeController{
 
   @RequestMapping(value = "/home", method = RequestMethod.GET)	
   public String printHello(ModelMap model) {
      model.addAttribute("message", "Written by Rodrigo Carvalho.");
      return "home";
   }
   
      
   @RequestMapping(value = "/index", method = RequestMethod.GET)
      public String showIndex(ModelMap model) {
         return "index";
      }
   
   @RequestMapping(value = "/hive", method = RequestMethod.GET)
   public String showHive(ModelMap model) {
	   
	   //DelvierbaleVo
	   DeliverableManager deliverableManager;
	      
	   //DeliverableVo List
	   List<DeliverableVo> deliverables;
	   
	   deliverableManager = new DeliverableManager();
	   
	   //get DeliverablesVo list
	   deliverables = deliverableManager.getPrioritizedDeliverables();
	   
	   //put a DeliverableVo list into Spring model
	   model.addAttribute("deliverables", deliverables);
	   
	   return "hive";
   }
}