package com.zhuhangjie.common.utils.algorithm;

import com.zhuhangjie.common.utils.netcdf.NetcdfUtils;

/**
 * 2015年淘帮一赤潮算法用于提取东海赤潮
 * <p>Title: NewRedtideAlgorithm</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class NewRedtideAlgorithm extends RedtideAlgorithm{

	/**
	 * 构造函数需要知道影像文件名称
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @param fileName
	 */
	public NewRedtideAlgorithm(String fileName) {
		super(fileName);
		
	}
	
	public NewRedtideAlgorithm() {
		super();
	}
	
	/**
	 * 算法核心部分，该部分主要通过对传入的像元的下标，各个波段反射率，赤潮识别数组以及赤潮数量进行计算
	 * <p>Title: distinguish</p>
	 * <p>Description: </p>
	 * @param doubleArray 像元在各个波段的反射率值
	 * 			doubleArray[0] = r412[i][j];
				doubleArray[1] = r443[i][j];
				doubleArray[2] = r469[i][j];
				doubleArray[3] = r488[i][j];
				doubleArray[4] = r531[i][j];
				doubleArray[5] = r547[i][j];
				doubleArray[6] = r555[i][j];
				doubleArray[7] = r645[i][j];
				doubleArray[8] = r667[i][j];
				doubleArray[9] = r678[i][j];
	 * @param isRedtide 赤潮矢量信息
	 * @param i 像元下标
	 * @param j 像元下标
	 * @param flag 赤潮像元数
	 * @see com.zhuhangjie.common.utils.algorithm.RedtideAlgorithm#distinguish(double[], boolean[][], int, int, int)
	 */
	@Override
	public void distinguish(double[] doubleArray, boolean[][] isRedtide, int i, int j) {
		double reflectAB = doubleArray[6]/doubleArray[4];
		if (doubleArray[6] < 0.014 && reflectAB > 1.25) {
				isRedtide[i][j] = true;
		}
	}

	//目前是空实现
	@Override
	public void hook() {
	}

}
