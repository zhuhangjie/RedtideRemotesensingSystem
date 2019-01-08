package com.zhuhangjie.common.utils.algorithm;

import com.zhuhangjie.common.utils.MathUtils;
import com.zhuhangjie.common.utils.ThreadOut;
import com.zhuhangjie.common.utils.netcdf.GeoInformationFromNcdf;
import com.zhuhangjie.common.utils.netcdf.InformationFromNcdf;
import com.zhuhangjie.common.utils.netcdf.NetcdfUtils;
import com.zhuhangjie.common.utils.netcdf.ReflectInformationFromNcdf;

public abstract class RedtideAlgorithm {
	
	private InformationFromNcdf reflect = null;
	
	//有参构造
	public RedtideAlgorithm(String fileName) {
		reflect = new ReflectInformationFromNcdf(fileName);
	}
	
	//无参构造，后续需要使用setfileName方法来进行文件设置
	public RedtideAlgorithm() {
	}
	
	public boolean[][] countRedtide(){
		double[][] r412 = getReflect(NetcdfUtils.R412);
		double[][] r443 = getReflect(NetcdfUtils.R443);
		double[][] r469 = getReflect(NetcdfUtils.R469);
		double[][] r488 = getReflect(NetcdfUtils.R488);
		double[][] r531 = getReflect(NetcdfUtils.R531);
		double[][] r547 = getReflect(NetcdfUtils.R547);
		double[][] r555 = getReflect(NetcdfUtils.R555);
		double[][] r645 = getReflect(NetcdfUtils.R645);
		double[][] r667 = getReflect(NetcdfUtils.R667);
		double[][] r678 = getReflect(NetcdfUtils.R678);
		
		//声明一个赤潮信息图像
		boolean[][] isRedtide = new boolean[r555.length][r555[0].length];
		
		//计数变量flag，统计总共有多少个赤潮点
		double[] doubleArray = new double[10];
		double doubleMax = 0;
		//用来表示R555除以R531的比值
		double bandRito = 0;
		double PDI = 0;
		double DI = 0;
		for (int i = 0; i < r555.length; i++) {
			for(int j = 0; j < r555[0].length; j++) {
				//如果计算涉及的值有小于或者等于0的情况舍去该点
				if (r555[i][j] <= 0 || r531[i][j] <= 0 || r645[i][j] <= 0) {
					continue;
				}
				//判断r555是否为波段中最大值
				doubleArray[0] = r412[i][j];
				doubleArray[1] = r443[i][j];
				doubleArray[2] = r469[i][j];
				doubleArray[3] = r488[i][j];
				doubleArray[4] = r531[i][j];
				doubleArray[5] = r547[i][j];
				doubleArray[6] = r555[i][j];
				doubleArray[7] = r645[i][j];
				doubleArray[8] = r667[i][j];
				doubleArray[9] = r678[i][j];
				doubleMax = MathUtils.maxDoule(doubleArray);
				//判断555处是否为最大反射率值
				if (doubleMax == r555[i][j]) {
					distinguish(doubleArray,isRedtide,i,j);
					
				}
			}
			//钩子方法
			hook();
		}
		ThreadOut.println(reflect.toString());
		return isRedtide;
	};
	
	//模板方法模式中固定的方法
	public double[][] getReflect(String bandName) {
		return reflect.getData(bandName);
	}
	
	//模板方法中的选择方法
	public abstract void distinguish(double[] doubleArray,boolean[][] isRedtide,int i, int j);
	
	//钩子
	public abstract void hook();

	public void setFilename(String fileName) {
		this.reflect = new ReflectInformationFromNcdf(fileName);
	}

	@Override
	public String toString() {
		return "RedtideAlgorithm [reflect=" + reflect + "]";
	}
	
	
}
