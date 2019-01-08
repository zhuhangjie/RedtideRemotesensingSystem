package com.zhuhangjie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageShowController {

	@RequestMapping("/manager")
	public String toManager() {
		return "simplemanager";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/portal")
	public String toPortal() {
		return "redirect:/pic/portal";
	}
	
	@RequestMapping("/upload")
	public String toUpLoad() {
		return "upload";
	}
	
	//重定向到查找最近日期
	@RequestMapping("/")
	public String toHome() {
		return "redirect:/pic/portal";
	}
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}
