package com.ca.asapserver.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ca.asapserver.user.NotUniqueEmailException;
import com.ca.asapserver.user.UserManager;
import com.ca.asapserver.vo.UserVo;
import com.google.gson.Gson;

/**
 * Rest Controller for SignUp
 * 
 * @author Rodrigo Carvalho
 *
 */
@RestController
public class SignUpController{
   
   @RequestMapping(value = "/signup", method = RequestMethod.GET, produces = "application/json")	
   public String signin(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password) {
	  
	  UserVo userVo = null;
	  UserManager userManager = null;
	  
	  System.out.println(name);
	  System.out.println(email);
	  System.out.println(password);
	  
	  try {
		  userManager = new UserManager();
		  userVo = userManager.createUser(name, email, password);
	  
		  Gson gson = new Gson();
		  String jasonUserVo = gson.toJson(userVo);
		
		  return jasonUserVo;
	  } catch (NotUniqueEmailException e){
		  return "";
	  } catch (Exception e){
		  return ""; // TODO: refactoring is needed to treat technical exceptions
	  }
	  
   }
   
}
