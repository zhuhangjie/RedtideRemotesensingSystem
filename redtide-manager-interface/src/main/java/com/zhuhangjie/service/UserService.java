package com.zhuhangjie.service;

import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.pojo.User;

public interface UserService {
	//根据类型来检查数据库中是否存在注册信息
	public RTResult checkRegister(String data, Integer type);
	//把用户保存到数据库
	public RTResult register(User user);
	//登录用户
	public RTResult login(String username, String password);
	//修改用户信息
	public RTResult update(User user);
}
