package com.ca.asapserver.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ca.asapserver.msgmanager.MsgManager;

/**
 * MessageController
 * 
 * Rest Controller for sending and consulting user messages
 * 
 * @author Rodrigo Carvalho
 *
 */
@RestController
public class MessageController {

	@RequestMapping(value = "/sendMessage", method = RequestMethod.GET)	
	public void sendMessage(@RequestParam("msg") String msg) { 
		
		MsgManager msgManager = new MsgManager();
		msgManager.insertMsg(msg);
	}	
	
}
