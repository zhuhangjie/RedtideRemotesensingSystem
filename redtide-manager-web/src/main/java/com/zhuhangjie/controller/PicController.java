package com.zhuhangjie.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhuhangjie.common.utils.EasyUIResult;
import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.pojo.Pic;
import com.zhuhangjie.service.PicService;

@Controller
public class PicController {
	
	@Autowired
	private PicService picService;
	
	@RequestMapping("/pic/{picId}")
	@ResponseBody
	public Pic selectPicById(@PathVariable Integer picId) {
		Pic pic = picService.selectPicById(picId);
		return pic;
	}
	
	@RequestMapping("/pic/insert")
	@ResponseBody
	public RTResult insertPic(Date date,String fileName) {
		RTResult rtResult = picService.insertPic(date,fileName);
		return rtResult;
	}
	
	@RequestMapping("/pic/list")
	@ResponseBody
	public EasyUIResult listPic(int page, int rows) {
		EasyUIResult result = picService.selectPicsByPage(page, rows);
		return result;
	}
	
	@RequestMapping("/pic/changeDate")
	public String findPic(String date, Model model) {
		model.addAttribute("oldDate", date);
		String newDate = date.replaceAll("/", "_");
		//把日期转换成数据库可以识别的模式
		String date_database = date.replaceAll("/", "-");
		Pic pic = picService.selectByDate(date_database);
		model.addAttribute("newDate", newDate);
		if(pic == null || pic.getArea() == null) {
			model.addAttribute("areaResult","");
			model.addAttribute("areaOfPoint","");
			return "portal";
		}
		Double area = pic.getArea();
		int areaP = pic.getRedtidepoint();
		String areaStr=new BigDecimal(area+"").toString();
		model.addAttribute("areaResult",areaStr);
		model.addAttribute("areaOfPoint",areaP);
		return "portal";
	}
	
	@RequestMapping("/pic/saveArea")
	@ResponseBody
	public RTResult updateArea(Double area, Date date) {
		RTResult result = picService.updateArea(area, date);
		return result;
	}
	
	@RequestMapping("/pic/selectYears")
	@ResponseBody
	public RTResult selectYears() {
		RTResult result = picService.selectYears();
		return result;
	}
	@RequestMapping("/pic/selectAreaByMonth")
	@ResponseBody
	public RTResult selectAreaByMonth(Integer year, String month) {
		RTResult result = picService.selectAreaByMonth(year, month);
		return result;
	}
	
	@RequestMapping("/pic/portal")
	public String toPortal(Model model) {
		Pic pic = picService.selectLastDate();
		
		if(pic != null) {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			String date = df.format(pic.getDate());
			model.addAttribute("oldDate", date);
			String newDate = date.replaceAll("/", "_");
			model.addAttribute("newDate", newDate);
			model.addAttribute("areaResult", pic.getArea());
		}
		
		return "portal";
	}
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public Pic uploadPicture(String name,MultipartFile picture) {
		//保存图片到目录F:\RedTideData\picture
		String picturName = UUID.randomUUID().toString().replaceAll("-", "");
		String ext = FilenameUtils.getExtension(picture.getOriginalFilename());
		try {
			picture.transferTo(new File("F:\\RedTideData\\picture\\" + picturName + "." + ext));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	@RequestMapping("/image/update")
	@ResponseBody
	public RTResult updatePic(Pic pic) {
		RTResult rtResult = picService.updatePic(pic);
		return rtResult;
	}
	
	@RequestMapping("/image/delete")
	@ResponseBody
	public RTResult deletePics(String ids) {
		RTResult rtResult = picService.deletePicByIds(ids);
		return rtResult;
	}
}
