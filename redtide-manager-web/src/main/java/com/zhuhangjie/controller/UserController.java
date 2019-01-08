package com.zhuhangjie.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.util.Md5Utils;
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
	
	//用shiro框架
	@RequestMapping("/user/login")
	public String login(String username , String password, HttpSession session) {
		/*RTResult result = userService.login(username, password);
		User user = (User) result.getData();
		//使用
		session.setAttribute("user", user);*/
		//使用shiro框架提供的方式认证
		Subject subject = SecurityUtils.getSubject();//获得当前用户对象，状态为“未认证”
		AuthenticationToken token = new UsernamePasswordToken(username,DigestUtils.md5DigestAsHex(password.getBytes()));//创建用户名密码令牌对象
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/login";
		}
		//session.setMaxInactiveInterval(60);
		//把用户放入session
		User user = (User) subject.getPrincipal();
		session.setAttribute("user", user);
		return "redirect:/pic/portal";
	}
	
	@RequestMapping("/user/update")
	@ResponseBody
	public RTResult update(User user) {
		RTResult result = userService.update(user);
		return result;
	}
}
