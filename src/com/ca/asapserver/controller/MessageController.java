package com.ca.asapserver.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ca.asapserver.msgmanager.MsgManager;
import com.ca.asapserver.vo.MessageVo;
import com.google.gson.Gson;

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
	
	@RequestMapping(value = "/getMessages", method = RequestMethod.GET, produces = "application/json")
	public String getMessages() { 
		MsgManager msgManager = new MsgManager();
		
		List<MessageVo> messages = msgManager.getMessages();
		
		Gson gson = new Gson();
		String msgsToJason = gson.toJson(messages);
		
		System.out.println("messages to jason = "+ msgsToJason);
		
		return msgsToJason;
		
	}	
	
}
