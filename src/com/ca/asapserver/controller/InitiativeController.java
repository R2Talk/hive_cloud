package com.ca.asapserver.controller;


import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ca.asapserver.initiative.InitiativeManager;
import com.ca.asapserver.vo.InitiativeVo;
import com.google.gson.Gson;

/**
 * Controller
 * 
 * Rest Controller for getting and updating initiatives.
 * 
 * @author Rodrigo Carvalho
 *
 */

@RestController
public class InitiativeController {

	
	/**
	 * getInitiatives
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getInitiatives", method = RequestMethod.GET, produces = "application/json")
	public String getInitiatives() { 
		InitiativeManager initiativeManager = new InitiativeManager();
		
		List<InitiativeVo> initiatives = initiativeManager.getInitiatives();
		
		Gson gson = new Gson();
		String initiativesToJason = gson.toJson(initiatives);
		
		return initiativesToJason;
		
	}	
	
	
}
