package com.ca.asapserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ca.asapserver.vo.UserVo;
import com.google.gson.Gson;

/**
 * Rest Controller for Sign In
 * 
 * @author Rodrigo Carvalho
 *
 */
@RestController
public class SignInController{
   
   @RequestMapping(value = "/signin", method = RequestMethod.GET)	
   public String signin(@RequestParam("name") String name, @RequestParam("password") String password) {
	   
	  System.out.println(name);
	  System.out.println(password);
	  
	  UserVo userVo = new UserVo(name, password, true);
	  
	  Gson gson = new Gson();
	  String jasonUserVo = gson.toJson(userVo);
		
      return jasonUserVo;
   }
   
}
