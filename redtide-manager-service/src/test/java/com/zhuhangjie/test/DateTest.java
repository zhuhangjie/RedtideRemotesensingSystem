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
import com.zhuhangjie.common.utils.netcdf.NetcdfUtils;

public class DateTest {

	/*@Test
	public void dateTest() throws Exception {
		String convertToDate = DayToDate.convertToDate(2018, 91);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simpleDateFormat.parse(convertToDate);
		String name = NameConStr.DateToName(date);
		String nameStr = NameConStr.nameStr(2018, 91);
		System.out.println(nameStr);
		System.out.println(name);
		
	}*/
	
	/*@Test
	public void sss() throws Exception {
		NetcdfUtils netcdfUtils = new NetcdfUtils("D:\\A2017272044000.L2_LAC_OC.nc");
		//boolean[][] bs = netcdfUtils.getRedtideByOldeFunction();
		double[][] geo = netcdfUtils.getGeo(NetcdfUtils.LONGITUDE);
		//double[][] ds = netcdfUtils.getReflect(NetcdfUtils.R412);
		//double[][] longt = netcdfUtils.getGeo(1);
		for (int i = 0; i < geo.length; i++) {
			for (int j = 0; j < geo[0].length; j++) {
				System.out.print(geo[i][j]);
			}
			System.out.println();
		}
	}*/
	
	/*@Test
	public void dayTest() throws Exception {
		Date date = new Date();
		String string = DayToDate.convertToDayOfYearBeforeOneDay(date);
		System.out.println(string);
	}
	
	@Test
	public void name() {
		System.out.println(DayToDate.convertToDate(2018, 91));
		
	}*/
}
