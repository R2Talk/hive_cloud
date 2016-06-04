package com.ca.asapserver.initiative;

import java.util.List;

import com.ca.asapserver.dao.DeliverableDAO;
import com.ca.asapserver.dao.InitiativeDAO;
import com.ca.asapserver.message.MsgManager;
import com.ca.asapserver.springutils.AppContextHelper;
import com.ca.asapserver.user.UserManager;
import com.ca.asapserver.vo.DeliverableVo;
import com.ca.asapserver.vo.InitiativeVo;

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
				
		DeliverableDAO deliverableDAO = (DeliverableDAO) AppContextHelper.getApplicationContext().getBean("deliverableDAO");
		
		return deliverableDAO.createDeliverable(deliverableVo, userId);
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
	
}
