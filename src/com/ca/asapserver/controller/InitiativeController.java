package com.ca.asapserver.controller;


import java.lang.reflect.Type;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ca.asapserver.initiative.InitiativeManager;
import com.ca.asapserver.vo.InitiativeVo;
import com.ca.asapserver.vo.MessageVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
	
	/**
	 * getInitiativesByUserId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getInitiativesByUserId", method = RequestMethod.GET, produces = "application/json")
	public String getInitiativesByUserId(@RequestParam("userId") String userId) { 
		InitiativeManager initiativeManager = new InitiativeManager();
		
		List<InitiativeVo> initiatives = initiativeManager.getInitiativesByUserId(userId); // TODO: call getInitiativesByUserId
		
		Gson gson = new Gson();
		String initiativesToJason = gson.toJson(initiatives);
		
		return initiativesToJason;
		
	}
	
	/**
	 * createInitiative
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createInitiative", method = RequestMethod.GET, produces = "application/json")
	public String createInitiative(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("userId") String userId) { 
			
		Gson gson = null;
		InitiativeVo initiativeVo = null;
		
        //prepare InitiativeVo
		initiativeVo = new InitiativeVo("", title, description);
		
		//create InitiativeVo using DAO object
		InitiativeManager initiativeManager = new InitiativeManager();
		//create and return newly created initiative with auto incremented created id
		initiativeVo = initiativeManager.createInitiative(initiativeVo, Integer.parseInt(userId));
        
        //associate user with initiative using DAO object
        
        //Return initiativeVo newly created
		gson = new Gson();
		String jasonInitiativeVo = gson.toJson(initiativeVo);
		
		return jasonInitiativeVo;
		
	}
	
	
	/**
	 * deleteInitiativeById
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteInitiativeById", method = RequestMethod.GET, produces = "application/json")
	public String deleteInitiativeById(@RequestParam("initiativeId") String initiativeId) { 
		
		String result="";
		
		//create InitiativeVo using DAO object
		InitiativeManager initiativeManager = new InitiativeManager();
		//create and return newly created initiative with auto incremented created id
		result = initiativeManager.deleteInitiativeById(initiativeId);
		
		return result;
		
	}
	
	/** inviteUser
	 * 
	 * @return
	 */
	@RequestMapping(value = "/inviteUser", method = RequestMethod.GET, produces = "application/json")
	public String inviteUser(@RequestParam("email") String email, @RequestParam("initiativeId") String initiativeId) { 
			
		//TODO: check if initiative exists. if does not exist, return initiative not found status
		//TODO: check if email exist. if does not exist, insert into invite table and return postponed status
		//TODO: otherwise get userIdByEmail and insert into INITIATIVE_USER a new association, and return success status
		
		return "";
		
	}
}
