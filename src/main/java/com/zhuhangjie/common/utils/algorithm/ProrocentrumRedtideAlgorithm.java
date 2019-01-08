package com.zhuhangjie.common.utils.algorithm;

import com.zhuhangjie.common.utils.MathUtils;
import com.zhuhangjie.common.utils.netcdf.NetcdfUtils;
/**
 * 东海原甲藻提取算法
 * <p>Title: ProprocentrumRedtideAlgorithm</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class ProrocentrumRedtideAlgorithm extends RedtideAlgorithm{

	/**
	 * 构造函数将待解析文件的绝对路径带入
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @param fileName
	 */
	public ProrocentrumRedtideAlgorithm(String fileName) {
		super(fileName);
		
	}
	
	public ProrocentrumRedtideAlgorithm() {
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
		double PDI = 0;
		double DI = 0;
		if (doubleArray[6] < 0.014 && reflectAB > 1.25) {
			PDI = MathUtils.countPDI(doubleArray[3], doubleArray[4], doubleArray[6]);
			DI = MathUtils.countDI(doubleArray[6], doubleArray[7], doubleArray[8]);
			if(DI < ((25 * PDI) - 0.125)) {
				isRedtide[i][j] = true;
			}
		}
	}


	@Override
	public void hook() {
	}

}
