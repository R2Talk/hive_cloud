package com.ca.asapserver.initiative;

import java.util.Iterator;
import java.util.List;

import com.ca.asapserver.dao.DeliverableDAO;
import com.ca.asapserver.message.MsgManager;
import com.ca.asapserver.springutils.AppContextHelper;
import com.ca.asapserver.user.UserManager;
import com.ca.asapserver.vo.DeliverableVo;
import com.ca.asapserver.vo.InitiativeVo;
import com.ca.asapserver.vo.UserVo;

/**
 * DeliverableManager
 * 
 * Functions to create, read, update and delete deliverables.
 *  
 * @author Rodrigo Carvalho.
 *
 */
public class DeliverableManager {

	/**
	 * getDeliverablesByInitiative
	 * 
	 * @return
	 */
	public List<DeliverableVo> getDeliverablesByInitiative(InitiativeVo initiativeVo){
		DeliverableDAO deliverableDAO = (DeliverableDAO) AppContextHelper.getApplicationContext().getBean("deliverableDAO");
		
		return deliverableDAO.getDeliverablesByInitiative(initiativeVo);
	}
	
	
	public List<DeliverableVo> getPrioritizedDeliverables(){
		DeliverableDAO deliverableDAO = (DeliverableDAO) AppContextHelper.getApplicationContext().getBean("deliverableDAO");
		
		return deliverableDAO.getPrioritizedDeliverables();
		
	}
	
	/**
	 * createDeliverable
	 * 
	 * @param deliverableVo
	 * @return
	 */
	public DeliverableVo createDeliverable(DeliverableVo deliverableVo, int userId){
				
		//Get user name
		UserManager userManager = new UserManager();
		
		UserVo userVo = userManager.getUserById(userId);
		
		//update deliverableVo
		deliverableVo.setCurrentusername(userVo.getName());
		
		//create deliverable
		DeliverableDAO deliverableDAO = (DeliverableDAO) AppContextHelper.getApplicationContext().getBean("deliverableDAO");
		
		return deliverableDAO.createDeliverable(deliverableVo);
	}
	
	/**
	 * deleteDeliverable
	 * 
	 * @param deliverableId
	 * @return
	 */
	public void deleteDeliverable(int deliverableId){ //TODO: Need re-factoring to throw exceptions
					
		//Delete deliverable messages
		MsgManager msgManager = new MsgManager();
		msgManager.deleteMessagesByDeliverableId(deliverableId);
				
		//Delete users association
		UserManager userManager = new UserManager();
		userManager.removeUserDeliverableAssociation(deliverableId);
		
		//Delete deliverable
		DeliverableDAO deliverableDAO = (DeliverableDAO) AppContextHelper.getApplicationContext().getBean("deliverableDAO");
		//TODO: evaluate the case for code for removing entities that are associated with the deliverable should be part of DAO if it's database structure constraint. In This case for messages and users associated with the deliverable.
		deliverableDAO.deleteDeliverable(deliverableId); 
		
		return;
	}
	
	
	/**
	 * deleteDeliverablesByInitiativeId
	 * 
	 * @param initiativeId
	 * @return
	 */
	public void deleteDeliverablesByInitiativeId(String initiativeId){ //TODO: Need re-factoring to throw exceptions
		
		InitiativeVo initiativeVo;
		List<DeliverableVo> deliverableVoList;
		
		//prepare InitiativeVo only for identity
		initiativeVo = new InitiativeVo(initiativeId, "", "");
		
		//Iterate the deliverables of the identified Initiative
		deliverableVoList = getDeliverablesByInitiative(initiativeVo);
		
		for (Iterator<DeliverableVo> iter = deliverableVoList.iterator(); iter.hasNext(); ) {
			DeliverableVo deliverableVo = iter.next();
			//deleteDeliverable
			deleteDeliverable(Integer.parseInt(deliverableVo.getIddeliverable()));
		}
		
		return;
	}
	
	/**
	 * setDeliverablePriority
	 * 
	 * @param deliverableVo
	 */
	public void setDeliverablePriority(DeliverableVo deliverableVo){
		
		//set deliverable priority
		DeliverableDAO deliverableDAO = (DeliverableDAO) AppContextHelper.getApplicationContext().getBean("deliverableDAO");
		deliverableDAO.setPriority(deliverableVo);
		
	}
	
	/**
	 * resetDeliverablePriority
	 * 
	 * @param deliverableVo
	 */
	public void resetDeliverablePriority(DeliverableVo deliverableVo){
		
		//reset deliverable priority
		DeliverableDAO deliverableDAO = (DeliverableDAO) AppContextHelper.getApplicationContext().getBean("deliverableDAO");
		deliverableDAO.resetPriority(deliverableVo);
	}
	
	/**
	 * updateDeliverable
	 * 
	 * @param deliverableVo
	 */
	public void updateDeliverable(DeliverableVo deliverableVo){
		
		//update deliverable 
		DeliverableDAO deliverableDAO = (DeliverableDAO) AppContextHelper.getApplicationContext().getBean("deliverableDAO");
		deliverableDAO.updateDeliverable(deliverableVo);
		
	}
}
