package com.zhuhangjie.conversion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 转换日期类型的数据
 * <p>Title: DateConverter</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String dateStr) {
		try {
			if(null != dateStr) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date date = df.parse(dateStr);
				return date;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
