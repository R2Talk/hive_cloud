package com.ca.asapserver.controller;


import java.lang.reflect.Type;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ca.asapserver.initiative.DeliverableManager;
import com.ca.asapserver.vo.DeliverableVo;
import com.ca.asapserver.vo.InitiativeVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Controller
 * 
 * Rest Controller for getting and updating deliverables.
 * 
 * @author Rodrigo Carvalho
 *
 */

@RestController
public class DeliverableController {

	/**
	 * getDeliverablesByInitiative
	 * 
	 * @param initiativeVo
	 * @return
	 */
	@RequestMapping(value = "/getDeliverablesByInitiative", method = RequestMethod.GET)	
	public String getDeliverablesByInitiative(@RequestParam("initiativeVo") String p_initiativeVo) { 
		Gson gson;	
		InitiativeVo initiativeVo;
		List<DeliverableVo> deliverables;
		
		//deserialize generic type for List of MessageVo
        gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
        Type initiativeVoType = new TypeToken<InitiativeVo>(){}.getType(); //this is necessary because we are deserializing a generic class type
        initiativeVo = gson.fromJson(p_initiativeVo, initiativeVoType);
		
		DeliverableManager deliverableManager = new DeliverableManager();
		deliverables = deliverableManager.getDeliverablesByInitiative(initiativeVo);
		
		gson = new Gson();
		String deliverablesToJason = gson.toJson(deliverables);
		
		return deliverablesToJason;
	}
	
	/**
	 * getPrioritezedDeliverables
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getPrioritizedDeliverables", method = RequestMethod.GET, produces = "application/json")
	public String getPrioritezedDeliverables() { 
		DeliverableManager deliverableManager = new DeliverableManager();
		
		List<DeliverableVo> deliverables = deliverableManager.getPrioritizedDeliverables();
		
		Gson gson = new Gson();
		String deliverablesToJason = gson.toJson(deliverables);
		
		
		return deliverablesToJason;
		
	}	
	
	/**
	 * updateDeliverable
	 * 
	 * @param deliverableVo
	 * @return
	 */
	@RequestMapping(value = "/updateDeliverable", method = RequestMethod.GET)	
	public String updateDeliverable(@RequestParam("deliverableVo") String deliverableVo) { 
		//Gson gson;
		//MessageVo messageVo;
		
		//deserialize generic type for List of MessageVo
        //gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
        //Type messageType = new TypeToken<InitiativeVo>(){}.getType(); //this is necessary because we are deserializing a generic class type
        //messageVo = gson.fromJson(initiativeVo, messageType);
		
		//MsgManager msgManager = new MsgManager();
		//msgManager.insertMessage(messageVo);
		
		return "";
	}
}
