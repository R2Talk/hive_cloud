package com.ca.asapserver.deliverable;

import java.util.List;

import com.ca.asapserver.dao.DeliverableDAO;
import com.ca.asapserver.springutils.AppContextHelper;
import com.ca.asapserver.vo.DeliverableVo;

/**
 * DeliverableManager
 * 
 * Functions to create, read, update and delete deliverables.
 *  
 * @author Rodrigo Carvalho
 *
 */
public class DeliverableManager {

	public List<DeliverableVo> getPrioritizedDeliverables(){
		DeliverableDAO deliverableDAO = (DeliverableDAO) AppContextHelper.getApplicationContext().getBean("deliverableDAO");
		
		return deliverableDAO.getPrioritizedDeliverables();
		
	}
	
}
