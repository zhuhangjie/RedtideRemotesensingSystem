package com.zhuhangjie.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhuhangjie.pojo.User;

public class LoginInterceptor implements HandlerInterceptor{
	
	@Value("${LOGIN_PATH}")
	private String LOGIN_PATH;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//前处理，执行handler前执行
		//返回true放行，false拦截
		
		//获得session对象
		HttpSession session = request.getSession();
		//取出session的user
		User user = (User) session.getAttribute("user");
		//判断如果user不存在则代表没登录,直接放行
		if(user == null) {
			response.sendRedirect(LOGIN_PATH);
			return false;
		}
		//如果用户存在把用户存入request
		request.setAttribute("user", user);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//执行handler后返回modeandview之前
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//完成处理，返回modelandview
		//可以在此处理异常
	}

}
