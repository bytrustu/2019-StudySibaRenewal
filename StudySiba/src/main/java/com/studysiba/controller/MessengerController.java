package com.studysiba.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studysiba.service.messenger.MessengerService;

@Controller
@RequestMapping("/messenger")
public class MessengerController {

	@Autowired
	private MessengerService messengerService;
	
	@ResponseBody
	@RequestMapping(value="/findUser", method=RequestMethod.POST)
	public String findUser(@RequestParam("nick") String nick, HttpSession session) {
		
		@SuppressWarnings("unchecked")
		String id = ( (HashMap<String, String>) session.getAttribute("userSession") ).get("id");
		String result = messengerService.checkNick(id, nick);
		System.out.println(result);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/getImage", method=RequestMethod.POST)
	public String getImage(@RequestParam("nick") String nick) {
		String result = messengerService.getUserImage(nick);
		System.out.println(result);
		return result;
	}
	
}
