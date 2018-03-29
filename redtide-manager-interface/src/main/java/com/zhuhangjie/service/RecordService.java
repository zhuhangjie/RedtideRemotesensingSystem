package com.zhuhangjie.service;

import com.zhuhangjie.common.utils.EasyUIResult;
import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.pojo.Record;

public interface RecordService {
	//添加记录
	public RTResult saveRecord(Record record);
	//添加记录
	public RTResult updateRecord(Record record);
	//删除数据
	public RTResult deleteRecord(String ids);
	//分页查询条件查询记录
	public EasyUIResult selectByPageAndCondition(int page, int rows, Long voyageIdQuery, String pointNameQuery);
	//根据id给record加图片
	/*public RTResult updateImageById(Long recordId,String picrtureName);*/
}
