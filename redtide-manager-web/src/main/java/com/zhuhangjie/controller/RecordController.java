package com.zhuhangjie.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhuhangjie.common.utils.EasyUIResult;
import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.pojo.Record;
import com.zhuhangjie.service.RecordService;

@Controller
public class RecordController {
	@Autowired
	private RecordService recordService;
	
	@RequestMapping("/record/save")
	@ResponseBody
	public RTResult saveRecord(Record record, @RequestParam(value="waterFile",required=false) MultipartFile file) {
		if(file == null) {
			RTResult result = recordService.saveRecord(record);
			return result;
		}
		//保存图片到F:\RedTideData\picture
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		//获取上传文件的后缀
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		//直接保存
		try {
			file.transferTo(new File("F:\\RedTideData\\picture\\" + name + "." + ext));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		record.setWaterPicture(name + "." + ext);
		RTResult result = recordService.saveRecord(record);
		return result;
	}
	
	@RequestMapping("/record/update")
	@ResponseBody
	public RTResult updateRecord(Record record,@RequestParam(value="waterFile",required=false) MultipartFile file) {
		if(file == null) {
			RTResult result = recordService.updateRecord(record);
			return result;
		}
		//保存图片到F:\RedTideData\picture
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		//获取上传文件的后缀
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		//直接保存
		try {
			file.transferTo(new File("F:\\RedTideData\\picture\\" + name + "." + ext));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		record.setWaterPicture(name + "." + ext);
		RTResult result = recordService.updateRecord(record);
		return RTResult.ok();
	}
	
	@RequestMapping("/record/delete")
	@ResponseBody
	public RTResult deleteRecord(String ids) {
		RTResult result = recordService.deleteRecord(ids);
		return result;
	}
	
	@RequestMapping("/record/list")
	@ResponseBody
	public EasyUIResult listByPage(int page, int rows, Long voyageIdQuery, String pointNameQuery) {
		EasyUIResult result = recordService.selectByPageAndCondition(page, rows, voyageIdQuery, pointNameQuery);
		return result;
	}
	
	@RequestMapping("/record/backShow")
	@ResponseBody
	public Map backShow(Long voyageIdQuery, String pointNameQuery) {
		Map<String, String> backMap = new HashMap<>();
		backMap.put("voyage", "v");
		backMap.put("point", pointNameQuery);
		return backMap;
	}
	
	/*@RequestMapping("/record/pictureUpload")
	@ResponseBody
	public RTResult pictureUpload(Long recordId, MultipartFile waterFile) {
		//保存图片到F:\RedTideData\picture
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		//获取上传文件的后缀
		String ext = FilenameUtils.getExtension(waterFile.getOriginalFilename());
		//直接保存
		try {
			waterFile.transferTo(new File("F:\\RedTideData\\picture\\" + name + "." + ext));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RTResult result = recordService.updateImageById(recordId, name + "." + ext);
		return result;
	}*/
}
