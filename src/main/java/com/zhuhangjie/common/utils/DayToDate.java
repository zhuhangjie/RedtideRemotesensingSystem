package com.zhuhangjie.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 把天数转换为日期
 * <p>Title: DayToDate</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class DayToDate {
	
	public static String convertToDate(int year, int day)
	{
		//判断是否为闰年
		int leapFlag = isLeapYear(year);
		int monthDays[] = {31,28,31,30,31,30,31,31,30,31,30,31};	
		if(leapFlag == 1 )
		{
			monthDays[1] = 29;
			
		}
		int dateDay = day;
		int dateMonth = 0;
		for(int i = 0 ; i < 12; i++)
		{
			dateMonth = i+1;
			if(dateDay-monthDays[i] <= 0)
			{
				break;
			}
			dateDay = dateDay-monthDays[i];
		}
		String rst;
		if(dateDay <10 && dateMonth>=10) 
			rst = year+"-"+ dateMonth +"-0"+dateDay;
		else if(dateDay <10 && dateMonth <10)
			rst = year+"-0"+ dateMonth +"-0"+dateDay;
		else if(dateDay >=10 && dateMonth <10)
			rst = year+"-0"+ dateMonth +"-"+dateDay;
		else
			rst = year+"-"+ dateMonth +"-"+dateDay;
		System.out.println(rst);
		return rst;	
	}
	
	//判断是否为闰年
	public static int isLeapYear(int year)
	{
		if(year % 4 == 0)
			return 1;
		else
			return 0;
	}

	//日期转换成一年中的日子
	@SuppressWarnings("deprecation")
	public static String convertToDayOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String year = calendar.get(Calendar.YEAR) + "";
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		String dayStr = "";
		if (day<10) {
			dayStr = "00" + day;
		} else if(day>9 && day<100) {
			dayStr = "0" + day;
		} else {
			dayStr = day + "";
		}
		return year + dayStr;
	}
	
	public static String convertToDayOfYearBeforeOneDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_calendar = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day_of_calendar-1);
		String year = calendar.get(Calendar.YEAR) + "";
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		String dayStr = "";
		if (day<10) {
			dayStr = "00" + day;
		} else if(day>9 && day<100) {
			dayStr = "0" + day;
		} else {
			dayStr = day + "";
		}
		return year + dayStr;
	}
	
	
}
