package com.zhuhangjie.common.utils.netcdf;

import java.io.IOException;
import java.lang.annotation.Retention;

import com.zhuhangjie.common.utils.algorithm.NewRedtideAlgorithm;
import com.zhuhangjie.common.utils.algorithm.ProrocentrumRedtideAlgorithm;
import com.zhuhangjie.common.utils.algorithm.RedtideAlgorithm;

import ucar.ma2.Array;
import ucar.nc2.Group;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

/**
 * 该工具用来读取MODIS L2A级的nc格式的数据，并提供方法获得其影像的经纬度以及赤潮发生情况
 * 改成单例模式，调用此工具类需要使用NetcdfUtils.getNetcdfUtils(String fileName)。 2018/10/09
 * <p>Title: NetcdfUtils</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class NetcdfUtils {
	public static final String R412 = "Rrs_412";
	public static final String R443 = "Rrs_443";
	public static final String R469 = "Rrs_469";
	public static final String R488 = "Rrs_488";
	public static final String R531 = "Rrs_531";
	public static final String R547 = "Rrs_547";
	public static final String R555 = "Rrs_555";
	public static final String R645 = "Rrs_645";
	public static final String R667 = "Rrs_667";
	public static final String R678 = "Rrs_678";
	public static final String LONGITUDE = "longitude";
	public static final String LATITUDE = "latitude";
	
	//nc文件的路径
	private String fileName;
	
	//ncdf提取接口
	private InformationFromNcdf geoInformation;
	
	//赤潮算法接口
	private RedtideAlgorithm redtideAlgorithm;
	
	//构造器设置nc文件的路径
	public NetcdfUtils(String fileName) {
		this.fileName = fileName;
		geoInformation = new GeoInformationFromNcdf(fileName);
	}
	
	public NetcdfUtils() {
		geoInformation = new GeoInformationFromNcdf();
	}

	/**
	 * 用于获取nc文件中的经纬度
	 * <p>Title: getGeo</p>
	 * <p>Description: </p>
	 * @param typeName LONGITUDE代表精度，LATITUDE代表纬度
	 * @return
	 */
	public double[][] getGeo(String variableName){
		return geoInformation.getData(variableName);
	}
	
	public boolean[][] getRedtide(){
		return redtideAlgorithm.countRedtide();
	}
	
	public void setRedtideAlgorithm(RedtideAlgorithm redtideAlgorithm) {
		redtideAlgorithm.setFilename(fileName);
		this.redtideAlgorithm = redtideAlgorithm;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
		geoInformation.setFileName(fileName);
		redtideAlgorithm.setFilename(fileName);
	}
}
/*public boolean[][] getProrocentrumByNewFunction(){
return prorocentrumRedtideAlgorithm.countRedtide();
}*/
/*public double[][] getGeo(String typeName){
double[][] geoData = null;
NetcdfFile ncfile = null;
try {
ncfile = NetcdfFile.open(fileName);
Group group = ncfile.findGroup("navigation_data");
Variable variable = group.findVariable(typeName);
if (variable == null) throw new NullPointerException("变量为空");
int[] varShape = variable.getShape();
geoData = new double[varShape[0]][varShape[1]];
Array data = variable.read();

double[] javaArray = (double[]) data.get1DJavaArray(double.class);
int n = 0;
for (int i=0; i<varShape[0]; i++) {
    for (int j=0; j<varShape[1]; j++) {
    	geoData[i][j] = javaArray[n++];
    }
}
} catch (IOException io) {
io.printStackTrace();
} finally { 
if (null != ncfile) try {
  ncfile.close();
} catch (IOException ioe) {
}
}
  return geoData;
}*/

/*public double[][] getReflect(String bandName) {
//声明double二维数组存放计算后的反射率
double[][] reflectData = null;
//声明Netcdf对象
NetcdfFile ncfile = null;
try {
	//以ncdf包里的对象的形式打开文件
    ncfile = NetcdfFile.open(fileName);
    //取得分组（进去文件夹）
    Group group = ncfile.findGroup("geophysical_data");
    //从分组中通过变量名参数（在这里就是波段名就是要取得的变量名参数，一般形式例如Rrs_555）取得变量对象。
    Variable variable = group.findVariable(bandName);
    if (variable == null) throw new NullPointerException("变量为空");//没找到变量的话报错
    //获得变量的维度，即图像的横竖像元
    int[] varShape = variable.getShape();
    //存放计算后的反射率,移到子类中
    //reflectData = new double[varShape[0]][varShape[1]];
    //将变量中的data数据提取成Array对象
    Array data = variable.read();
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
        		reflectData[i][j] = 0;
        		continue;
			}
        	//通过给定的文件内部公式得到反射率信息并以
        	d1 = auto * 2;
        	d2 = d1 * 0.000001;
        	reflectData[i][j] = d2 + 0.05;
        }
    }
    System.out.println(fileName + "-----" + bandName + "-----finished");
  } catch (IOException io) {
   io.printStackTrace();
  } finally { 
    if (null != ncfile) try {
      ncfile.close();
    } catch (IOException ioe) {
    }
  }
//返回反射率二维数组
  return reflectData;
}*/

/*public boolean[][] getRedtideByOldeFunction() {
//分别获得公式中需要用到的反射率图像
double[][] r555 = getReflect(NetcdfUtils.R555);
double[][] r443 = getReflect(NetcdfUtils.R443);
double[][] r488 = getReflect(NetcdfUtils.R488);
//声明一个赤潮信息图像
boolean[][] isRedtide = new boolean[r555.length][r555[0].length];

//计数变量flag，统计总共有多少个赤潮点
int flag = 0;
for (int i = 0; i < r555.length; i++) {
	for(int j = 0; j < r555[0].length; j++) {
		//如果计算涉及的值有小于或者等于0的情况舍去该点
		if (r555[i][j] <= 0 || r443[i][j] <= 0 || r488[i][j] <= 0) {
			continue;
		}
		//计算出结果值
		double b = ((r555[i][j] - r443[i][j]) * 0.4018 + r443[i][j] - r488[i][j])/r488[i][j];
		//如果大于阈值0.2就把该点的赤潮信息设置为true
		if(b > 0.2) {
			isRedtide[i][j] = true;
			flag++;
		}
		//System.out.print(isRedtide[i][j]?1:0);
	}
	//System.out.println();
}
System.out.println(flag);
return isRedtide;
}*/

//
/*public int[][] getRedtideByNewFunction() {
//新公式需要获得所有波段
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
int[][] isRedtide = new int[r555.length][r555[0].length];

//计数变量flag，统计总共有多少个赤潮点
double[] doubleArray = new double[10];
double doubleMax = 0;
//用来表示R555除以R531的比值
double bandRito = 0;
double PDI = 0;
double DI = 0;
int flag = 0;
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
			bandRito = doubleMax/r531[i][j];
			if (doubleMax < 0.014 && bandRito > 1.25) {
				//PDI = MathUtils.countPDI(r488[i][j], r531[i][j], r555[i][j]);
				//DI = MathUtils.countDI(r555[i][j], r645[i][j], r667[i][j]);
				//if(DI < ((25 * PDI) - 0.125)) {
				
					isRedtide[i][j] = 3;
					flag++;
				//}
			}
		}
	}
	//System.out.println();
}
System.out.println(flag);
return isRedtide;
}*/