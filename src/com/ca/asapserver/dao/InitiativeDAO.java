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
	
	/**
	 * getInitiatives
	 * 
	 * Returns List with all initiatives persisted in the database
	 * 
	 * @return
	 */
	public List<InitiativeVo> getInitiatives();
	
	/**
	 * getInitiativesByUserId
	 * 
	 * Returns List with all user initiatives persisted in the database
	 * 
	 * @return
	 */
	public List<InitiativeVo> getInitiativesByUserId(String userId);
	
	/**
	 * deleteInitiativeById
	 * 
	 * Delete the identified initiative and all it´s deliverables
	 * 
	 * @return
	 */
	public void deleteInitiativeById(String initiativeId);
	
	/**
	 * getInitiativeUsersByInitiativeId
	 * 
	 * @param initiativeId
	 * @return
	 */
	public List<UserVo> getInitiativeUsersByInitiativeId(String initiativeId);
	
	/**
	 * getKnownUsersByUserId
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserVo> getKnownUsersByUserId(String userId);
	
}
