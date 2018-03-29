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
		return "portal";
	}
	
	@RequestMapping("/upload")
	public String toUpLoad() {
		return "upload";
	}
	
	@RequestMapping("/")
	public String toHome() {
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}
