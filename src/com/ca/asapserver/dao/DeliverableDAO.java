package com.ca.asapserver.dao;

import java.util.List;

import com.ca.asapserver.vo.DeliverableVo;
import com.ca.asapserver.vo.InitiativeVo;

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
	
	/**
	 * getDeliverablesByInitiative
	 * 
	 * @return
	 */
	public List<DeliverableVo> getDeliverablesByInitiative(InitiativeVo initiativeVo);
	
	/**
	 * createDeliverable
	 * 
	 * Create deliverable
	 * 
	 * @return
	 */
	public DeliverableVo createDeliverable(DeliverableVo deliverableVo);
	
	/**
	 * finishDeliverable
	 * 
	 * @param deliverableId
	 * @return
	 */
	public void finishDeliverable(int deliverableId);
	
	/**
	 * deleteDeliverable
	 * 
	 * @param deliverableId
	 * @return
	 */
	public void deleteDeliverable(int deliverableId);
	
	/**
	 * setPriority
	 * 
	 * @param deliverableVo
	 */
	public void setPriority(DeliverableVo deliverableVo);
	
	/**
	 * resetPriority
	 * 
	 * @param deliverableVo
	 */
	public void resetPriority(DeliverableVo deliverableVo);
	
	/**
	 * updateDeliverable
	 * 
	 * @param deliverableVo
	 */
	public void updateDeliverable(DeliverableVo deliverableVo);
}
