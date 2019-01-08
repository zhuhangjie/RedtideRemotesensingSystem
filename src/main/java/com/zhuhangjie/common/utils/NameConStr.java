package com.zhuhangjie.common.utils;

import java.util.Date;

public class NameConStr {
	static int i = 366;
	
	public static String DateToName(Date date) {
		String string = DayToDate.convertToDayOfYear(date);
		int year = Integer.parseInt(string.substring(0, 4));
		int day = Integer.parseInt(string.substring(4, 7));
		String nameStr = NameConStr.nameStr(year, day);
		return nameStr;
	}
	
	public static String nameStr(int year, int day){
		String hds = "";
		int min = 0;
		switch (year) {
		//这里的512是随便一个16的整数，假设加了512之后是16的年（因为不知道负数怎么算余数的所以加个512，免得麻烦），16年要比15年多一个15年日子的年份也就是365，所以减去365
		case 2009:min = (day + 4096 - 365 - 365 - 365 - 366 - 365 - 365 - 365)%16;break;
		case 2010:min = (day + 4096 - 365 - 365 - 365 - 366 - 365 - 365)%16;break;
		case 2011:min = (day + 4096 - 365 - 365 - 365 - 366 - 365)%16;break;
		case 2012:min = (day + 4096 - 365 - 365 - 365 - 366)%16;break;
		case 2013:min = (day + 4096 - 365 - 365 -365)%16;break;
		case 2014:min = (day + 4096 - 365 - 365)%16;break;
		case 2015:min = (day + 4096 - 365)%16;break;
		case 2016:min = day%16;break;
		case 2017:min = (366 + day) % 16;;break;
		case 2018:min = (366 + 365 + day)%16;break;
		case 2019:min = (366 + 365 + 365 + day)%16;break;
		case 2020:min = (366 + 365 + 365 + 365 + day)%16;break;
		case 2021:min = (366 + 365 + 365 + 365 + 366 + day)%16;break;
		case 2022:min = (366 + 365 + 365 + 365 + 366 + 365 + day)%16;break;
		default:
			break;
		}
		
		switch(min)
		{
			case 0: hds = "0430";break;//2008/425??
			case 1: hds = "0510";break;//1
			case 2: hds = "0555";break;//1
			case 3: hds = "0500";break;//1
			case 4: hds = "0540";break;//1
			case 5: hds = "0445";break;//1
			case 6: hds = "0530";break;//1
			case 7: hds = "0435";break;//1
			case 8: hds = "0515";break;//1
			case 9: hds = "0420";break;//1
			case 10: hds = "0505";break;//1
			case 11: hds = "0550";break;//2008/545??
			case 12: hds = "0450";break;//1
			case 13: hds = "0535";break;//1
			case 14: hds = "0440";break;//1
			case 15: hds = "0525";break;//2008/520??
			default:break;
		}
		String name = "";
		if(day < 10)
			name = "A" + year +"00" + day + hds + "00.L2_LAC_OC";
		if(day >= 10 && day <100)
			name = "A" + year +"0" + day + hds + "00.L2_LAC_OC";
		if(day >=100)
			name = "A" + year + day + hds + "00.L2_LAC_OC";

		return name;
	}
}
