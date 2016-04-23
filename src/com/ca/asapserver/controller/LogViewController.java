package com.ca.asapserver.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ca.asapserver.message.MsgManager;
import com.ca.asapserver.vo.MessageVo;
import com.google.gson.Gson;

/**
 * LogViewController
 * 
 * @author Rodrigo Carvalho
 *
 */
@Controller
public class LogViewController {
	
	@RequestMapping("/logMessages")	
	public String logMessages(ModelMap model){ 	
		
		List<MessageVo> msgs;
		MsgManager msgManager = new MsgManager();
		
		msgs = msgManager.getMessages();
		
		Gson gson = new Gson();
		String msgsToJason = gson.toJson(msgs);
		
		model.addAttribute("messages", msgsToJason);
		
		return "LogMessages";
	}
}
