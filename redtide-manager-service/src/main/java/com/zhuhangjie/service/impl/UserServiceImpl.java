package com.zhuhangjie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.mapper.UserMapper;
import com.zhuhangjie.pojo.User;
import com.zhuhangjie.pojo.UserExample;
import com.zhuhangjie.pojo.UserExample.Criteria;
import com.zhuhangjie.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public RTResult checkRegister(String data, Integer type) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if(type == 1) {
			criteria.andEmailEqualTo(data);
		}else if(type == 2) {
			criteria.andUsernameEqualTo(data);
		} else {
			return RTResult.build(400, "缺少检查参数种类");
		}
		List<User> list = userMapper.selectByExample(example);
		
		if(list != null && list.size() > 0) {
			return RTResult.build(401, "信息已存在数据库请重新输入");
		} 
		
		return RTResult.ok();
	}
	
	@Override
	public RTResult register(User user) {
		//对用户信息再次校验
		if("".equals(user.getUsername()) || "".equals(user.getPassword()) || "".equals(user.getEmail())) {
			return RTResult.build(400, "用户数据不完整,注册失败");
		}
		RTResult result = checkRegister(user.getEmail(), 1);
		if(result.getStatus() != 200) {
			return RTResult.build(400, "邮箱已存在");
		}
		result= checkRegister(user.getUsername(), 2);
		if(result.getStatus() != 200) {
			return RTResult.build(400, "用户名已存在");
		}
		//补全信息
		//对密码进行MD5加密
		String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Password);
		//用户数据插入数据库
		userMapper.insert(user);
		//返回成功
		return RTResult.ok();
	}

	@Override
	public RTResult login(String username, String password) {
		//后台检查用户名密码是否为空
		if(username == null || password == null) {
			return RTResult.build(400, "用户名或者密码不能为空");
		}
		//创建查询条件
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userMapper.selectByExample(example);
		//检查结果list如果为空或者大小不大于0，返回错误
		if(list == null || list.size() <= 0 ) {
			return RTResult.build(400, "用户名不存在");
		}
		//取出user
		User user = list.get(0);
		//判断加密后的密码是否相等
		if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
			return RTResult.build(400, "用户名字或密码不正确");
		}
		//用户名密码正确，返回带有user的结果
		return RTResult.ok(user);
	}

	@Override
	public RTResult update(User user) {
		if(user == null || user.getPassword() == null || user.getName() == null || user.getEmail() == null) {
			return RTResult.build(400, "输入信息有误");
		}
		String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).toString();
		user.setPassword(password);
		userMapper.updateByPrimaryKey(user);
		return RTResult.ok();
	}

}
