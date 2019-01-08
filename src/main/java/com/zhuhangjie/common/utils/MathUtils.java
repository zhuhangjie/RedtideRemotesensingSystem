package com.zhuhangjie.common.utils;


public class MathUtils {
	public static double maxDoule(double[] doubleArray) {
		double max = 0;
		for(int i = 0; i<doubleArray.length; i++) {
			//第一次循环，将最大值设置成数组第一个数
			if (i == 0) {
				max = doubleArray[i];
				continue;
			}
			max = Math.max(max, doubleArray[i]);
		}
		return max;
	}
	
	public static double countPDI(double r488, double r531, double r555) {
		double r555minusr531 = r555 - r531;
		double left = r555minusr531 / (555d - 531d);
		double r531minusr488 = r531 - r488;
		double right = r531minusr488 / (531d - 488d);
		double son = left - right;
		double mother = r555 - r488;
		return son / mother;
	}
	
	public static double countDI(double r555, double r645, double r667) {
		double ratio = 90d/112d;
		double r667minusr555 = r667 - r555;
		double muti = ratio * r667minusr555;
		double right = r555 + muti;
		double son = r645 - right;
		double mother = r645;
		return son / mother;
	}
}
