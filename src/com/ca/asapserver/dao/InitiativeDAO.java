package com.ca.asapserver.dao;

import java.util.List;

import com.ca.asapserver.vo.InitiativeVo;
import com.ca.asapserver.vo.UserVo;

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
	 * Returns List with all initiatives persisted in the database
	 * 
	 * @return
	 */
	public List<InitiativeVo> getInitiatives();
	
	/**
	 * createInitiative
	 * 
	 * Create initiative
	 * 
	 * @return
	 */
	public InitiativeVo createInitiative(InitiativeVo initiativeVo, int userId);
	
	/**
	 * bindUserToInitiative
	 * 
	 * @param initiativeVo
	 * @param userId
	 */
	public void bindUserToInitiative(InitiativeVo initiativeVo, int userId);
	
}
