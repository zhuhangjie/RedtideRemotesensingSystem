package com.zhuhangjie.pojo;

import java.io.Serializable;

public class QueryPoJo implements Serializable{
	private Integer year;
	private String month;
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
}
