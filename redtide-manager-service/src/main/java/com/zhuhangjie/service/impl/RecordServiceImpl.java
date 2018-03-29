package com.zhuhangjie.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermap.data.conversion.ImportDataInfoSDEVector;
import com.zhuhangjie.common.utils.EasyUIResult;
import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.mapper.RecordMapper;
import com.zhuhangjie.mapper.VoyageMapper;
import com.zhuhangjie.pojo.Pic;
import com.zhuhangjie.pojo.PicExample;
import com.zhuhangjie.pojo.Record;
import com.zhuhangjie.pojo.RecordExample;
import com.zhuhangjie.pojo.RecordResult;
import com.zhuhangjie.pojo.RecordExample.Criteria;
import com.zhuhangjie.pojo.Voyage;
import com.zhuhangjie.service.RecordService;
@Service
public class RecordServiceImpl implements RecordService{
	@Autowired
	private RecordMapper recordMapper;
	
	@Autowired
	private VoyageMapper voyageMapper;
	
	@Override
	public RTResult saveRecord(Record record) {
		if(record == null) {
			return RTResult.build(400, "缺少参数");
		}
		recordMapper.insert(record);
		return RTResult.ok();
	}

	@Override
	public EasyUIResult selectByPageAndCondition(int page, int rows, Long voyageIdQuery, String pointNameQuery) {
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(page, rows);
		RecordExample example = new RecordExample();
		Criteria criteria = example.createCriteria();
		if(voyageIdQuery != null) {
			criteria.andVoyageIdEqualTo(voyageIdQuery);
		}
		if (pointNameQuery != null) {
			criteria.andPointNameLike("%"+pointNameQuery+"%");
		}
		List<Record> list = recordMapper.selectByExample(example);
		for (int i=0; i<list.size(); i++) {
			Record record = list.get(i);
			Long voyageId = record.getVoyageId();
			Voyage voyage = voyageMapper.selectByPrimaryKey(voyageId);
			RecordResult recordR = new RecordResult(record);
			recordR.setVoyageName(voyage.getVoyageName());
			list.set(i, recordR);
		}
		PageInfo<Record> pageInfo = new PageInfo<>(list);
		Integer total = (int) pageInfo.getTotal();
		EasyUIResult result = new EasyUIResult(total, list);
		return result;
	}

	/*@Override
	public RTResult updateImageById(Long recordId, String picrtureName) {
		Record record = recordMapper.selectByPrimaryKey(recordId);
		record.setWaterPicture(picrtureName);
		recordMapper.updateByPrimaryKey(record);
		return RTResult.ok();
	}*/

	@Override
	public RTResult updateRecord(Record record) {
		if(record == null) {
			return RTResult.build(400, "缺少参数");
		}
		recordMapper.updateByPrimaryKey(record);
		return RTResult.ok();
	}

	@Override
	public RTResult deleteRecord(String ids) {
		if (ids == null || ids == "") {
			return RTResult.build(400, "请选择要删除的id");
		}
		Long id = 0L;
		String[] idsArray = ids.split(",");
		for (String idStr : idsArray) {
			id = Long.parseLong(idStr);
			recordMapper.deleteByPrimaryKey(id);
		}
		return RTResult.ok();
	}

}
