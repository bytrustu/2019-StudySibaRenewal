package com.studysiba.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studysiba.domain.messenger.MessageVO;
import com.studysiba.service.messenger.MessengerService;

@Controller
@RequestMapping("/messenger")
public class MessengerController {

	@Autowired
	private MessengerService messengerService;

	@ResponseBody
	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	public String findUser(@RequestParam("nick") String nick, HttpSession session) {

		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		String result = messengerService.checkNick(id, nick, "getBoolean");
		System.out.println(result);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/getImage", method = RequestMethod.POST)
	public String getImage(@RequestParam("nick") String nick) {
		String result = messengerService.getUserImage(nick);
		System.out.println(result);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String sendMessage(@RequestParam HashMap<String, String> param, HttpSession session) {

		String result = null;

		String nick = param.get("nick");
		String type = param.get("type");
		String content = param.get("content");

		if (nick == null || nick.equals("") || type == null || type.equals("") || content == null || content.equals("")) {
			result = "false";
		} else {
			String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
			String toId = messengerService.checkNick(id, nick, "getId");

			System.out.println(toId);
			MessageVO messageVO = new MessageVO();
			messageVO.setType(type);
			messageVO.setId(id);
			messageVO.setToId(toId);
			messageVO.setContent(content);
			messageVO.setmRead(0);
			result = messengerService.sendMessage(messageVO);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="viewMessage", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	public String viewMessage(@RequestParam("nick") String nick, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		String result = messengerService.getMessage(id, nick);
		return result;
	}

}
