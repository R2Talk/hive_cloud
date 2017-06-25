package com.ca.asapserver.controller;


import java.lang.reflect.Type;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ca.asapserver.initiative.DeliverableManager;
import com.ca.asapserver.initiative.InitiativeManager;
import com.ca.asapserver.message.MsgManager;
import com.ca.asapserver.vo.DeliverableVo;
import com.ca.asapserver.vo.InitiativeVo;
import com.ca.asapserver.vo.MessageVo;
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
	@RequestMapping(value = "/getDeliverablesByInitiative", method = RequestMethod.GET, produces = "application/json")	
	public String getDeliverablesByInitiative(@RequestParam("initiativeVo") String p_initiativeVo) { 
		Gson gson;	
		InitiativeVo initiativeVo;
		List<DeliverableVo> deliverables;
		
		//de_serialize generic type for List of MessageVo
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
	 * createDeliverable
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createDeliverable", method = RequestMethod.GET, produces = "application/json")
	public String createDeliverable(@RequestParam("title") String title, 
			@RequestParam("description") String description, 
			@RequestParam("date") String date,
			@RequestParam("status") String status,
			@RequestParam("userId") String userId,
			@RequestParam("initiativeId") String initiativeId,
			@RequestParam("isPriority") String isPriority) { 
			
		Gson gson = null;
		DeliverableVo deliverableVo = null;
		
        //prepare InitiativeVo
		deliverableVo = new DeliverableVo("", initiativeId, title, description, 
				"", status, date, userId, "3", isPriority, "", "", "0", "", "");
		
		//create Deliverable
		DeliverableManager deliverableManager = new DeliverableManager();
		//create and return newly created initiative with auto incremented created id
		deliverableVo = deliverableManager.createDeliverable(deliverableVo, Integer.parseInt(userId));
        
        //Return initiativeVo newly created
		gson = new Gson();
		String jasonDeliverableVo = gson.toJson(deliverableVo);
		
		return jasonDeliverableVo;
		
	}
	
	/**
	 * finishDeliverable
	 * 
	 * @param deliverableId
	 * @return
	 */
	@RequestMapping(value = "/finishDeliverable", method = RequestMethod.GET)	
	public void concludeDeliverable(@RequestParam("deliverableId") String deliverableId) { //TODO: Need refactoring to throw exceptions 
		
		//delete Deliverable
		DeliverableManager deliverableManager = new DeliverableManager();
		//create and return newly created initiative with auto incremented created id
		deliverableManager.finishDeliverable(Integer.parseInt(deliverableId));
				
		return;
	}
	
	/**
	 * deleteDeliverable
	 * 
	 * @param deliverableId
	 * @return
	 */
	@RequestMapping(value = "/deleteDeliverable", method = RequestMethod.GET)	
	public void deleteDeliverable(@RequestParam("deliverableId") String deliverableId) { //TODO: Need refactoring to throw exceptions 
		
		//delete Deliverable
		DeliverableManager deliverableManager = new DeliverableManager();
		//create and return newly created initiative with auto incremented created id
		deliverableManager.deleteDeliverable(Integer.parseInt(deliverableId));
				
		return;
	}
	
	
	/**
	 * setDeliverablePriority
	 * 
	 * @param deliverableVo
	 * @return
	 */
	@RequestMapping(value = "/setDeliverablePriority", method = RequestMethod.GET)	
	public String setDeliverablePriority(@RequestParam("jasonDeliverableVo") String jasonDeliverableVo) { 
		Gson gson;
		DeliverableVo deliverableVo;
		
		//deserialize generic type 
        gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
        Type deliverableType = new TypeToken<DeliverableVo>(){}.getType(); //this is necessary because we are deserializing a generic class type
        deliverableVo = gson.fromJson(jasonDeliverableVo, deliverableType);
	
        DeliverableManager deliverableManager = new DeliverableManager();
        deliverableManager.setDeliverablePriority(deliverableVo);
        
		return "";
	}
	
	/**
	 * resetDeliverablePriority
	 * 
	 * @param deliverableVo
	 * @return
	 */
	@RequestMapping(value = "/resetDeliverablePriority", method = RequestMethod.GET)	
	public String resetDeliverablePriority(@RequestParam("jasonDeliverableVo") String jasonDeliverableVo) { 
		Gson gson;
		DeliverableVo deliverableVo;
		
		//deserialize generic type 
        gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
        Type deliverableType = new TypeToken<DeliverableVo>(){}.getType(); //this is necessary because we are deserializing a generic class type
        deliverableVo = gson.fromJson(jasonDeliverableVo, deliverableType);
	
        DeliverableManager deliverableManager = new DeliverableManager();
        deliverableManager.resetDeliverablePriority(deliverableVo);
        
		return "";
	}
	
	/**
	 * updateDeliverable
	 * 
	 * @param deliverableVo
	 * @return
	 */
	@RequestMapping(value = "/updateDeliverable", method = RequestMethod.GET)	
	public String updateDeliverable(@RequestParam("jasonDeliverableVo") String jasonDeliverableVo) { 
		Gson gson;
		DeliverableVo deliverableVo;
		
		//System.out.println(jasonDeliverableVo);
		
		//deserialize generic type 
        gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
        Type deliverableType = new TypeToken<DeliverableVo>(){}.getType(); //this is necessary because we are deserializing a generic class type
        deliverableVo = gson.fromJson(jasonDeliverableVo, deliverableType);
	
        DeliverableManager deliverableManager = new DeliverableManager();
        deliverableManager.updateDeliverable(deliverableVo);
        
		return "";
	}
}
