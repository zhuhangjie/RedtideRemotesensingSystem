package com.zhuhangjie.service;

import com.zhuhangjie.common.utils.EasyUIResult;
import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.pojo.Voyage;

public interface VoyageService {
	//添加航次
	public RTResult saveVoyage(Voyage voyage);
	//查询航次列表
	public EasyUIResult selectVoyageByPage(Integer page, Integer rows);
	//添加航次
	public RTResult updateVoyage(Voyage voyage);
	//删除航次
	public RTResult deleteVoyage(String ids);
	//通过用户id查询相关的航次
	public EasyUIResult selectVoyageByUserId(Long userId, Integer page, Integer rows);
	//把已知id的用户和ids的航次关联
	public RTResult relateUserWithVoyages(Long userId, String ids);
	//把已知id的用户和ids的关联删除
	public RTResult deletCorrelationOfUserAndVoyage(Long userId, String ids);
	//普通查询所有航次
	public RTResult selectAllVoyage();
}
