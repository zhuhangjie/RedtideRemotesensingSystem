package com.zhuhangjie.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.pojo.User;
import com.zhuhangjie.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/register")
	@ResponseBody
	public RTResult register(User user) {
		RTResult result = userService.register(user);
		return result;
	}
	
	@RequestMapping("/user/checkRegister")
	@ResponseBody
	public RTResult checkRegister(String data, Integer type) {
		RTResult result = userService.checkRegister(data, type);
		return result;
	}
	
	@RequestMapping("/user/login")
	@ResponseBody
	public RTResult login(String username , String password, HttpSession session) {
		RTResult result = userService.login(username, password);
		User user = (User) result.getData();
		session.setAttribute("user", user);
		return result;
	}
	
	@RequestMapping("/user/update")
	@ResponseBody
	public RTResult update(User user) {
		RTResult result = userService.update(user);
		return result;
	}
}
