package com.zhuhangjie.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.zhuhangjie.common.utils.DayToDate;
import com.zhuhangjie.common.utils.NameConStr;

public class DateTest {

	@Test
	public void dateTest() throws Exception {
		String date = DayToDate.convertToDate(2017, 272);
	}
	
	@Test
	public void dayTest() throws Exception {
		Date date = new Date();
		String string = DayToDate.convertToDayOfYearBeforeOneDay(date);
		System.out.println(string);
	}
	
	@Test
	public void name() {
		Date date = new Date();
		String string = DayToDate.convertToDayOfYear(date);
		int year = Integer.parseInt(string.substring(0, 4));
		int day = Integer.parseInt(string.substring(4, 7));
		String nameStr = NameConStr.nameStr(year, day);
		System.out.println(nameStr);
	}
}
