package com.ca.asapserver.dao;

import java.util.List;

import com.ca.asapserver.vo.MessageVo;

/**
 * MessageDAO
 * 
 * Message Entity DAO interface.
 * 
 * @author Rodrigo Carvalho.
 *
 */
public interface MessageDAO {
	
	/**
	 * getAllMessages
	 * 
	 * Returns List with all messages persisted in the database
	 * 
	 * @return
	 */
	public List<MessageVo> getAllMessages();
	
	/**
	 * insertMessage
	 * 
	 * Insert a message into the message repository.
	 * 
	 * @return
	 */
	public void insertMessage(MessageVo messageVo);
	
	/**
	 * deleteMessagesByDeliverableId
	 * 
	 * Delete all messages associated with a determined deliverable
	 * 
	 * @return
	 */
	public void deleteMessagesByDeliverableId(int deliverableId);

}
