package com.ca.asapserver.initiative;

import java.util.List;

import com.ca.asapserver.dao.InitiativeDAO;
import com.ca.asapserver.springutils.AppContextHelper;
import com.ca.asapserver.user.UserManager;
import com.ca.asapserver.vo.InitiativeVo;

/**
 * InitiativeManager
 * 
 * Functions to create, read, update and delete deliverables.
 *  
 * @author Rodrigo Carvalho
 *
 */
public class InitiativeManager {

	/**
	 * createInitiative
	 * 
	 * @param initiativeVo
	 * @return
	 */
	public InitiativeVo createInitiative(InitiativeVo initiativeVo, int userId){
				
		InitiativeDAO initiativeDAO = (InitiativeDAO) AppContextHelper.getApplicationContext().getBean("initiativeDAO");
		
		initiativeVo = initiativeDAO.createInitiative(initiativeVo, userId);
		
		initiativeDAO.bindUserToInitiative(initiativeVo, userId); //BE WARE: this is a business rule. For database structure dependencies the code remains in the DAO.
		
		//TODO: remove code below. For test version only.
		//if(userId != 3) {
		//	initiativeDAO.bindUserToInitiative(initiativeVo, userId);
		//}
		
		return initiativeVo; 
	}
	
	/**
	 * getInitiatives
	 * 
	 * @return
	 */
	public List<InitiativeVo> getInitiatives(){
		InitiativeDAO initiativeDAO = (InitiativeDAO) AppContextHelper.getApplicationContext().getBean("initiativeDAO");
		
		return initiativeDAO.getInitiatives();
		
	}
	
	/**
	 * getInitiativesByUserId
	 * 
	 * @return
	 */
	public List<InitiativeVo> getInitiativesByUserId(String userId){
		InitiativeDAO initiativeDAO = (InitiativeDAO) AppContextHelper.getApplicationContext().getBean("initiativeDAO");
		
		return initiativeDAO.getInitiativesByUserId(userId);
	}
	
	/**
	 * deleteInitiativeById
	 * 
	 * @return
	 */
	public String deleteInitiativeById(String initiativeId){
		
		//deleteDeliverablesByInitiativeId
		//BE WARE: The delete initiative operation has the premise that all deliverables were previously removed, this is done by the Initiative manager before calling this method
		//TO CONDIDER: this should be a database rule to ensure that child entities are remove before parent
		DeliverableManager deliverableManager = new DeliverableManager();
		deliverableManager.deleteDeliverablesByInitiativeId(initiativeId);
		
		//deleteUserInitiativeAssociation
		//BE WARE: The delete initiative operation has the premise that all user associations were previously removed, this is done by the Initiative manager before calling this method
		//TO CONDIDER: this should be a database rule to ensure that child entities are remove before parent
		UserManager userManager = new UserManager();
		userManager.removeUserInitiativeAssociation(Integer.parseInt(initiativeId));
				
		//delete initiative
		InitiativeDAO initiativeDAO = (InitiativeDAO) AppContextHelper.getApplicationContext().getBean("initiativeDAO");
		
		initiativeDAO.deleteInitiativeById(initiativeId);
		
		return "";
	}
	
}
