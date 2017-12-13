package com.ca.asapserver.controller;


import java.lang.reflect.Type;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ca.asapserver.initiative.DeliverableManager;
import com.ca.asapserver.initiative.InitiativeManager;
import com.ca.asapserver.vo.DeliverableVo;
import com.ca.asapserver.vo.InitiativeVo;
import com.ca.asapserver.vo.MessageVo;
import com.ca.asapserver.vo.UserVo;
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
	
	/**
	 * getInitiativeUsersByInitiativeId
	 * 
	 * @param initiativeId
	 * @return
	 */
	@RequestMapping(value = "/getInitiativeUsersByInitiativeId", method = RequestMethod.GET, produces = "application/json")	
	public String getInitiativeUsersByInitiativeId(@RequestParam("initiativeId") String p_initiativeId) { 
		Gson gson;	
		List<UserVo> userVoList;
		
		InitiativeManager initiativeManager = new InitiativeManager();
		userVoList  = initiativeManager.getInitiativeUsersByInitiativeId(p_initiativeId);
		
		gson = new Gson();
		String userVoListToJason = gson.toJson(userVoList);
		
		return userVoListToJason;
	}
	
	/**
	 * getKnownUsersByUserId
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getKnownUsersByUserId", method = RequestMethod.GET, produces = "application/json")	
	public String getKnownUsersByUserId(@RequestParam("userId") String p_userId) { 
		Gson gson;	
		List<UserVo> userVoList;
		
		InitiativeManager initiativeManager = new InitiativeManager();
		userVoList  = initiativeManager.getKnownUsersByUserId(p_userId);
		
		gson = new Gson();
		String userVoListToJason = gson.toJson(userVoList);
		
		return userVoListToJason;
	}
	
	/**
	 * addUserToInitiative
	 * 
	 * @param userEmail
	 * @param initiativeId
	 * @return
	 */
	@RequestMapping(value = "/addUserToInitiative", method = RequestMethod.GET, produces = "application/json")	
	public String addUserToInitiative(@RequestParam("userEmail") String p_userEmail, @RequestParam("initiativeId") String p_initiativeId) { 
		String return_code;
		
		InitiativeManager initiativeManager = new InitiativeManager();
		return_code  = initiativeManager.addUserToInitiative(p_userEmail, p_initiativeId);
		
		return return_code;
	}
	
	/**
	 * deleteUserFromInitiative
	 * 
	 * @param userId
	 * @param initiativeId
	 * @return
	 */
	@RequestMapping(value = "/deleteUserFromInitiative", method = RequestMethod.GET, produces = "application/json")	
	public String deleteUserFromInitiative(@RequestParam("userId") String p_userId, @RequestParam("initiativeId") String p_initiativeId) { 
		String return_code;
		
		InitiativeManager initiativeManager = new InitiativeManager();
		return_code  = initiativeManager.deleteUserFromInitiative(p_userId, p_initiativeId);
		
		return return_code;
	}
}
