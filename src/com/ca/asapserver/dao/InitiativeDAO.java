package com.ca.asapserver.dao;

import java.util.List;

import com.ca.asapserver.vo.InitiativeVo;

/**
 * DeliverableDAO
 * 
 * Deliverable Entity DAO interface.
 * 
 * @author Rodrigo Carvalho.
 *
 */
public interface InitiativeDAO {
	
	/**
	 * getPrioritizedDeliverables
	 * 
	 * Returns List with all prioritized deliverables persisted in the database
	 * 
	 * @return
	 */
	public List<InitiativeVo> getInitiatives();
	
}
