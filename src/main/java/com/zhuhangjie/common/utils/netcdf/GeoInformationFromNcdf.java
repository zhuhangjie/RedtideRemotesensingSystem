package com.zhuhangjie.common.utils.netcdf;

import ucar.ma2.Array;
import ucar.nc2.Group;
import ucar.nc2.NetcdfFile;
/**
 * 提取影像中地理信息的子类
 * <p>Title: GeoInformationFromNcdf</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class GeoInformationFromNcdf extends InformationFromNcdf{
	public static final String GEO_GROUP = "navigation_data";
	
	/**
	 * 构造方法需要传入待解析的文件名
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @param fileName
	 */
	public GeoInformationFromNcdf(String fileName) {
		super(fileName);
	}
	
	public GeoInformationFromNcdf() {
	}
	
	/**
	 * 将文件中数据导入数组的操作，地理信息直接导入二位数组返回即可
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
		double[] javaArray = (double[]) data.get1DJavaArray(double.class);
	    int n = 0;
	    for (int i=0; i<varShape[0]; i++) {
	        for (int j=0; j<varShape[1]; j++) {
	        	resultData[i][j] = javaArray[n++];
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
		return ncfile.findGroup(GEO_GROUP);
	}

}
