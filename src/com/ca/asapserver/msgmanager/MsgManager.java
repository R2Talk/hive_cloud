package com.ca.asapserver.msgmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.ca.asapserver.dao.MessageDAO;
import com.ca.asapserver.springutils.AppContextHelper;
import com.ca.asapserver.vo.MessageVo;

/**
 * MsgManager
 * 
 * Manages send and consulting messages. Use the DAO classes for access and save messages.
 * 
 * @author Rodrigo Carvalho
 *
 */
public class MsgManager {
	
	@Autowired
	private ApplicationContext appContext;

	public MsgManager(){
	}
	
	/*
	public void insertMsg(String msg) {
		MocMsgRepository msgRepository = MocMsgRepository.getInstance();
		msgRepository.insertMessage(msg);
	}
	*/
	
	public void insertMessage(MessageVo messageVo) {
		
		MessageDAO messageDAO = (MessageDAO) AppContextHelper.getApplicationContext().getBean("messageDAO");
		
		messageDAO.insertMessage(messageVo);
	}

	public List<MessageVo> getMessages() {
		
		MessageDAO messageDAO = (MessageDAO) AppContextHelper.getApplicationContext().getBean("messageDAO");
		
		return messageDAO.getAllMessages();
	}
	
}
