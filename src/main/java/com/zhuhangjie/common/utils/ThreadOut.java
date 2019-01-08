package com.zhuhangjie.common.utils;


public class ThreadOut{
	
	public static void println(String string) {
		System.out.println(Thread.currentThread().getName() + " " + string);
	}

	public static void print(String string) {
		System.out.print(Thread.currentThread().getName() + " " + string);
	}
	
}
