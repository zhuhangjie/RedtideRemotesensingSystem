package com.zhuhangjie.common.utils.netcdf;

import java.io.IOException;

import com.zhuhangjie.common.utils.ThreadOut;

import ucar.ma2.Array;
import ucar.nc2.Group;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

public abstract class InformationFromNcdf {
	private String fileName;
	
	public InformationFromNcdf(String fileName) {
		this.fileName = fileName;
	}
	
	public InformationFromNcdf() {
	}

	public double[][] getData(String variableName) {
		//声明double二维数组存放计算后的反射率
		double[][] resultData = null;
		//声明Netcdf对象
		NetcdfFile ncfile = null;
		try {
			//以ncdf包里的对象的形式打开文件
		    ncfile = NetcdfFile.open(fileName);
		    
		    Group group = getGroup(ncfile);
		    //取得分组（进去文件夹）
		    //Group group = ncfile.findGroup("geophysical_data");
		    
		    //从分组中通过变量名参数（在这里就是波段名就是要取得的变量名参数，一般形式例如Rrs_555）取得变量对象。
		    Variable variable = group.findVariable(variableName);
		    if (variable == null) throw new NullPointerException("变量为空");//没找到变量的话报错
		    //获得变量的维度，即图像的横竖像元
		    int[] varShape = variable.getShape();
		    //存放计算后的反射率
		    resultData = new double[varShape[0]][varShape[1]];
		    //将变量中的data数据提取成Array对象
		    Array data = variable.read();
		    
		    resultData = putDataIntoArray(data,varShape);
		  } catch (IOException io) {
		   io.printStackTrace();
		  } finally { 
		    if (null != ncfile) try {
		      ncfile.close();
		    } catch (IOException ioe) {
		    }
		  }
		//ThreadOut.println(fileName + "-----" + variableName + "-----finished");
		//返回反射率二维数组
		  return resultData;
	}
	
	//模板方法模式，让子类实现新方法
	public abstract double[][] putDataIntoArray(Array data,int[] varShape);
	
	//模板方法模式，让子类实现的抽象方法
	public abstract Group getGroup(NetcdfFile ncfile);
	
	@Override
	public String toString() {
		return "InformationFromNcdf [fileName=" + fileName + "]";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
