package com.ca.asapserver.controller;


import java.lang.reflect.Type;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ca.asapserver.message.MsgManager;
import com.ca.asapserver.vo.MessageVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * MessageController
 * 
 * Rest Controller for sending and consulting user messages.
 * 
 * @author Rodrigo Carvalho
 *
 */

@RestController
public class MessageController {

	@RequestMapping(value = "/sendMessage", method = RequestMethod.GET)	
	public String sendMessage(@RequestParam("msg") String msg) { 
		Gson gson;
		MessageVo messageVo;
		
		//deserialize generic type for List of MessageVo
        gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();
        Type messageType = new TypeToken<MessageVo>(){}.getType(); //this is necessary because we are deserializing a generic class type
        messageVo = gson.fromJson(msg, messageType);
		
        System.out.println("sending message : " + messageVo.getText());
        
		MsgManager msgManager = new MsgManager();
		msgManager.insertMessage(messageVo);
		
		return "Message delivered";
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
