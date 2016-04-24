package com.ca.asapserver.dao;

import java.util.List;

import com.ca.asapserver.vo.DeliverableVo;

/**
 * DeliverableDAO
 * 
 * Deliverable Entity DAO interface.
 * 
 * @author Rodrigo Carvalho.
 *
 */
public interface DeliverableDAO {
	
	/**
	 * getPrioritizedDeliverables
	 * 
	 * Returns List with all prioritized deliverables persisted in the database
	 * 
	 * @return
	 */
	public List<DeliverableVo> getPrioritizedDeliverables();
	
}
