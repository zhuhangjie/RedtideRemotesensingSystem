package com.zhuhangjie.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhangjie.common.utils.EasyUIResult;
import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.mapper.RecordMapper;
import com.zhuhangjie.mapper.UserVoyageMapper;
import com.zhuhangjie.mapper.VoyageMapper;
import com.zhuhangjie.pojo.Pic;
import com.zhuhangjie.pojo.PicExample;
import com.zhuhangjie.pojo.Record;
import com.zhuhangjie.pojo.RecordExample;
import com.zhuhangjie.pojo.UserVoyage;
import com.zhuhangjie.pojo.UserVoyageExample;
import com.zhuhangjie.pojo.UserVoyageExample.Criteria;
import com.zhuhangjie.pojo.Voyage;
import com.zhuhangjie.pojo.VoyageExample;
import com.zhuhangjie.service.VoyageService;

@Service
public class VoyageServiceImpl implements VoyageService{
	@Autowired
	private VoyageMapper voyageMapper;
	@Autowired
	private UserVoyageMapper userVoyageMapper;
	@Autowired
	private RecordMapper recordMapper;
	
	@Override
	public RTResult saveVoyage(Voyage voyage) {
		voyageMapper.insert(voyage);
		return RTResult.ok();
	}

	@Override
	public EasyUIResult selectVoyageByPage(Integer page, Integer rows) {
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(page, rows);
		VoyageExample example = new VoyageExample();
		List<Voyage> list = voyageMapper.selectByExample(example);
		PageInfo<Voyage> pageInfo = new PageInfo<>(list);
		Integer total = (int) pageInfo.getTotal();
		EasyUIResult result = new EasyUIResult(total, list);
		return result;
	}

	@Override
	public RTResult updateVoyage(Voyage voyage) {
		voyageMapper.updateByPrimaryKey(voyage);
		return RTResult.ok();
	}

	@Override
	public RTResult deleteVoyage(String ids) {
		if(ids == null || ids == "") {
			return RTResult.build(400, "请选择要删除的航次");
		}
		long id = 0L;
		String[] idsArray = ids.split(",");
		for (String idStr : idsArray) {
			id = Long.parseLong(idStr);
			UserVoyageExample example = new UserVoyageExample();
			Criteria criteria = example.createCriteria();
			criteria.andVoyageIdEqualTo(id);
			userVoyageMapper.deleteByExample(example);
			RecordExample recordExample = new RecordExample();
			com.zhuhangjie.pojo.RecordExample.Criteria recordCriteria = recordExample.createCriteria();
			recordCriteria.andVoyageIdEqualTo(id);
			List<Record> recordList = recordMapper.selectByExample(recordExample);
			if(recordList != null && recordList.size() > 0) {
				for (Record record : recordList) {
					record.setVoyageId(null);
					recordMapper.updateByPrimaryKey(record);
				}
			}
			voyageMapper.deleteByPrimaryKey(id);
		}
		return RTResult.ok();
	}

	@Override
	public EasyUIResult selectVoyageByUserId(Long userId, Integer page, Integer rows) {
		EasyUIResult result = null;
		UserVoyageExample userVoyageExample = new UserVoyageExample();
		Criteria userVoyagecriteria = userVoyageExample.createCriteria();
		userVoyagecriteria.andUserIdEqualTo(userId);
		List<UserVoyage> list = userVoyageMapper.selectByExample(userVoyageExample);
		if(list == null || list.size() <= 0) {
			return result;
		}
		List<Long> voyageIdList = new ArrayList<Long>();
		Long voyageId = 0L; 
		for (UserVoyage userVoyage : list) {
			voyageId = userVoyage.getVoyageId();
			voyageIdList.add(voyageId);
		}
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(page, rows);
		VoyageExample voyageExample = new VoyageExample();
		com.zhuhangjie.pojo.VoyageExample.Criteria criteria = voyageExample.createCriteria();
		criteria.andIdIn(voyageIdList);
		List<Voyage> voyageList = voyageMapper.selectByExample(voyageExample);
		PageInfo<Voyage> pageInfo = new PageInfo<>(voyageList);
		Integer total = (int) pageInfo.getTotal();
		result = new EasyUIResult(total, voyageList);
		return result;
	}

	@Override
	public RTResult relateUserWithVoyages(Long userId, String ids) {
		if(userId == null || ids == null || ids == "") {
			return RTResult.build(400, "无参数");
		}
		UserVoyage uv = new UserVoyage();
		uv.setUserId(userId);
		UserVoyageExample example = new UserVoyageExample();
		Criteria criteria = example.createCriteria();
		String[] voyageIds = ids.split(",");
		Long voyageId = 0L;
		List<UserVoyage> list = null;
		for (String vId : voyageIds) {
			voyageId = Long.parseLong(vId);
			example = new UserVoyageExample();
			criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userId);
			criteria.andVoyageIdEqualTo(voyageId);
			list = userVoyageMapper.selectByExample(example);
			if(list != null && list.size() > 0) {
				return RTResult.build(401, "关联已存在");
			}
			uv.setVoyageId(voyageId);
			userVoyageMapper.insert(uv);
		}
		
		return RTResult.ok();
	}

	@Override
	public RTResult deletCorrelationOfUserAndVoyage(Long userId, String ids) {
		if(userId == null || ids == null || ids == "") {
			return RTResult.build(400, "无参数");
		}
		String[] idsArray = ids.split(",");
		UserVoyageExample example = new UserVoyageExample();
		Criteria criteria = example.createCriteria();
		Long voyageId = 0L;
		for (String vid : idsArray) {
			voyageId = Long.parseLong(vid);
			example = new UserVoyageExample();
			criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userId);
			criteria.andVoyageIdEqualTo(voyageId);
			try {
				userVoyageMapper.deleteByExample(example);
			} catch (Exception e) {
				return RTResult.build(400, "没有需要删除的关联");
			}
		}
		
		return RTResult.ok();
	}

	@Override
	public RTResult selectAllVoyage() {
		VoyageExample example = new VoyageExample();
		List<Voyage> list = voyageMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return RTResult.ok(list);
		}
		return RTResult.build(401, "不存在航次信息");
	}

}
