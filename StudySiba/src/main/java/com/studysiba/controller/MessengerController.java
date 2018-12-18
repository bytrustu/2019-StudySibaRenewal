package com.studysiba.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studysiba.domain.messenger.MessageVO;
import com.studysiba.service.member.MemberService;
import com.studysiba.service.messenger.MessengerService;

@Controller
@RequestMapping("/messenger")
public class MessengerController {

	@Autowired
	private MessengerService messengerService;
	@Autowired
	private MemberService memberService;

	@ResponseBody
	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	public String findUser(@RequestParam("nick") String nick, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		String result = messengerService.checkNick(id, nick, "getBoolean");
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/getImage", method = RequestMethod.POST)
	public String getImage(@RequestParam("nick") String nick) {
		String result = messengerService.getUserImage(nick);
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
	
	@ResponseBody
	@RequestMapping(value="getMessengerUserList", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	public String getMessengerUserList(HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		String result = messengerService.getMessengerUserList(id);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="deleteMessage", method=RequestMethod.POST)
	public String deleteMessage(@RequestParam("nick") String nick, HttpSession session) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		String result = messengerService.deleteMessage(id, nick);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="checkFriendStatus", method=RequestMethod.POST)
	public String checkFriendStatus(@RequestParam("nick") String nick, HttpSession session) {
		String result = memberService.checkNick(nick);
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		if ( !result.equals("true") ) {
			return result;
		}
		String status = messengerService.checkFriendStatus(id,nick);
		return status;
	}
	
	@ResponseBody
	@RequestMapping(value="applyFriend", method=RequestMethod.POST)
	public String applyFriend(@RequestParam("nick") String nick, HttpSession session ) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		String result = messengerService.applyFriend(id, nick);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="refuseFriend", method=RequestMethod.POST)
	public String refuseFriend(@RequestParam("no") int no, @RequestParam("nick") String nick, HttpSession session ) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		String result = messengerService.refuseFriend(no, id, nick);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="acceptFriend", method=RequestMethod.POST)
	public String acceptFriend(@RequestParam("no") int no, @RequestParam("nick") String nick, HttpSession session ) {
		String id = ((HashMap<String, String>) session.getAttribute("userSession")).get("id");
		String result = messengerService.acceptFriend(no, id, nick);
		return result;
	}
	
	
}
