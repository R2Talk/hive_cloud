package com.ca.asapserver.initiative;

import java.util.List;

import com.ca.asapserver.dao.DeliverableDAO;
import com.ca.asapserver.springutils.AppContextHelper;
import com.ca.asapserver.vo.DeliverableVo;
import com.ca.asapserver.vo.InitiativeVo;

/**
 * DeliverableManager
 * 
 * Functions to create, read, update and delete deliverables.
 *  
 * @author Rodrigo Carvalho
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
	
}
