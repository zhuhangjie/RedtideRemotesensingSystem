package com.zhuhangjie.common.utils.algorithm;

import com.zhuhangjie.common.utils.MathUtils;

/**
 * 硅藻提取算法
 * <p>Title: DiatomRedtideAlgorithm</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class DiatomRedtideAlgorithm extends RedtideAlgorithm{

	public DiatomRedtideAlgorithm(String fileName) {
		super(fileName);
		
	}
	
	public DiatomRedtideAlgorithm() {
		super();
	}
	
	//和东海原甲藻几乎一样就是最后一步判断变化
	@Override
	public void distinguish(double[] doubleArray, boolean[][] isRedtide, int i, int j) {
		double reflectAB = doubleArray[6]/doubleArray[4];
		double PDI = 0;
		double DI = 0;
		if (doubleArray[6] < 0.014 && reflectAB > 1.25) {
			PDI = MathUtils.countPDI(doubleArray[3], doubleArray[4], doubleArray[6]);
			DI = MathUtils.countDI(doubleArray[6], doubleArray[7], doubleArray[8]);
			//把东海原甲藻最后一步判断的小于换成大于
			if(DI > ((25 * PDI) - 0.125)) {
				isRedtide[i][j] = true;
			}
		}
	}

	@Override
	public void hook() {
	}

}
