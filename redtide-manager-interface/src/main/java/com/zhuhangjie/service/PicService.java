package com.zhuhangjie.service;

import java.util.Date;

import com.zhuhangjie.common.utils.EasyUIResult;
import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.pojo.Pic;

public interface PicService {
	//通过id查找影像
	public Pic selectPicById(Integer id);
	//插入影像数据
	public RTResult insertPic(Date date,String fileName);
	//更新影像数据
	public RTResult updatePic(Pic pic);
	//删除影像数据
	public RTResult deletePicByIds(String ids);
	//分页查询
	public EasyUIResult selectPicsByPage(Integer page, Integer rows);
	//更新赤潮面积
	public RTResult updateArea(Double area, Date date);
	//查找年份
	public RTResult selectYears();
	//查找每月的赤潮面积信息
	public RTResult selectAreaByMonth(Integer year,String month);
	//查询最近日子的数据
	public Pic selectLastDate();
	//直接往数据库插入数据而不进行任何操作
	public RTResult savePic(Pic pic);
	//通过日期查询影像
	public Pic selectByDate(String date);
	
}
