package com.ca.asapserver.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ca.asapserver.user.UserManager;
import com.ca.asapserver.vo.UserVo;
import com.google.gson.Gson;

/**
 * Rest Controller for SignIn
 * 
 * @author Rodrigo Carvalho
 *
 */
@RestController
public class SignInController{
   
   @RequestMapping(value = "/signin", method = RequestMethod.GET, produces = "application/json")	
   public String signin(@RequestParam("name") String name, @RequestParam("password") String password) {
	  
	  UserVo userVo = null;
	  UserManager userManager = null;
	  System.out.println(name);
	  System.out.println(password);
	  
	  userManager = new UserManager();
	  userVo = userManager.validateUser(name, password);
	  
	  Gson gson = new Gson();
	  String jasonUserVo = gson.toJson(userVo);
		
      return jasonUserVo;
   }
   
}
