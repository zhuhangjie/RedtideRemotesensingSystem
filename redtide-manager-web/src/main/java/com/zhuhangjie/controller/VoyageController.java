package com.zhuhangjie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuhangjie.common.utils.EasyUIResult;
import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.pojo.Voyage;
import com.zhuhangjie.service.VoyageService;

@Controller
public class VoyageController {
	@Autowired
	private VoyageService voyageService;
	
	@RequestMapping("/voyage/save")
	@ResponseBody
	public RTResult saveVoyage(Voyage voyage) {
		RTResult result = voyageService.saveVoyage(voyage);
		return result;
	}
	
	@RequestMapping("/voyage/list")
	@ResponseBody
	public EasyUIResult voyageList(Integer page, Integer rows) {
		EasyUIResult result = voyageService.selectVoyageByPage(page, rows);
		return result;
	}
	
	@RequestMapping("/voyage/update")
	@ResponseBody
	public RTResult updateVoyage(Voyage voyage) {
		RTResult result = voyageService.updateVoyage(voyage);
		return result;
	}
	
	@RequestMapping("/voyage/delete")
	@ResponseBody
	public RTResult deleteVoyage(String ids) {
		RTResult result = voyageService.deleteVoyage(ids);
		return result;
	}
	
	@RequestMapping("/voyage/list/{userId}")
	@ResponseBody
	public EasyUIResult voyageListByUserId(@PathVariable Long userId,Integer page, Integer rows) {
		EasyUIResult result = voyageService.selectVoyageByUserId(userId, page, rows);
		return result;
	}
	
	@RequestMapping("/voyage/relate/{userId}")
	@ResponseBody
	public RTResult relateUserWithVoyages(@PathVariable Long userId,String ids) {
		RTResult rtResult = voyageService.relateUserWithVoyages(userId, ids);
		return rtResult;
	}
	
	@RequestMapping("/voyage/delete/{userId}")
	@ResponseBody
	public RTResult deleteUserWithVoyages(@PathVariable Long userId,String ids) {
		RTResult result = voyageService.deletCorrelationOfUserAndVoyage(userId, ids);
		return result;
	}
	
	@RequestMapping("/voyage/selectIds")
	@ResponseBody
	public RTResult selectAllVoyageId() {
		RTResult result = voyageService.selectAllVoyage();
		return result;
	}
}
