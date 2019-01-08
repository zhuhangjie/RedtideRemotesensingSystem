package com.zhuhangjie.common.utils.netcdf;

import ucar.ma2.Array;
import ucar.nc2.Group;
import ucar.nc2.NetcdfFile;

/**
 * 提取影像中波段信息的子类
 * <p>Title: ReflectInformationFromNcdf</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class ReflectInformationFromNcdf extends InformationFromNcdf{
	public static final String REFLECT_GROUP = "geophysical_data";
    /**
     * 构造方法，需要传入待解析的文件名
     * <p>Title: </p>
     * <p>Description: </p>
     * @param fileName
     */
	public ReflectInformationFromNcdf(String fileName) {
		super(fileName);
	}

	/**
	 * 将波段信息取出后按照公式计算成反射率，把无效像元替换成0值返回波段反射率的数据
	 * <p>Title: putDataIntoArray</p>
	 * <p>Description: </p>
	 * @param data
	 * @param varShape
	 * @return
	 * @see com.zhuhangjie.common.utils.netcdf.InformationFromNcdf#putDataIntoArray(ucar.ma2.Array, int[])
	 */
	@Override
	public double[][] putDataIntoArray(Array data, int[] varShape) {
		double[][] resultData = new double[varShape[0]][varShape[1]];
		//通过Array对象的方法获把影像提取成一位数组的java数组javaArray
	    int[] javaArray = (int[]) data.get1DJavaArray(int.class);
	    
	    int n = 0;
	    int auto = 0;
	    double d1 = 0;
	    double d2 = 0;
	    
	    for (int i=0; i<varShape[0]; i++) {
	        for (int j=0; j<varShape[1]; j++) {
	        	//用n计数把javaArray中的数字一个个取出来
	        	auto = javaArray[n++];
	        	//判断如果为-32767代表空数据，直接将反射率置为0，并直接进入下一循环
	        	if (auto == -32767) {
	        		resultData[i][j] = 0;
	        		continue;
				}
	        	//通过给定的文件内部公式得到反射率信息并以
	        	d1 = auto * 2;
	        	d2 = d1 * 0.000001;
	        	resultData[i][j] = d2 + 0.05;
	        }
	    }
		return resultData;
	}
	
	/**
	 * 方法中的过程，通过分组名称取得分组
	 * <p>Title: getGroup</p>
	 * <p>Description: </p>
	 * @param ncfile
	 * @return
	 * @see com.zhuhangjie.common.utils.netcdf.InformationFromNcdf#getGroup(ucar.nc2.NetcdfFile)
	 */
	@Override
	public Group getGroup(NetcdfFile ncfile) {
		return ncfile.findGroup(REFLECT_GROUP);
	}
}
