package com.zhuhangjie.common.utils.geotools;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.DataUtilities;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.common.utils.ThreadOut;
import com.zhuhangjie.common.utils.algorithm.NewRedtideAlgorithm;
import com.zhuhangjie.common.utils.algorithm.RedtideAlgorithm;
import com.zhuhangjie.common.utils.netcdf.NetcdfUtils;

/***
 * 创建矢量数据的工具类，主要方法为getShape(String fileName,String outputShpfile)
 * 通过setAlgorithm方法来更换要使用的算法，默认算法为计算所有赤潮疑似区域面积。
 * <p>Title: GeoToolsUtils</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class GeoToolsUtils {
	private static GeometryFactory geometryFactory;
	private static ShapefileDataStoreFactory shapefileDataStoreFactory; 
	private static Object obj1;
	private static Object obj2;
	//默认为使用新算法
	private static RedtideAlgorithm redtideAlgorithm = new NewRedtideAlgorithm();
	
	//如果需要对算法中的数据进行修改就用这个变量代替算法的结果
	private static boolean[][] updateData = null;
	
	static {
		geometryFactory = JTSFactoryFinder.getGeometryFactory();//创建一个几何工厂（GeometryFactory）
		shapefileDataStoreFactory = new ShapefileDataStoreFactory();//创建shp文件数据存储工厂 
		obj1 = new Object();
		obj2 = new Object();
	}
	
	public static RTResult getShape(String fileName,String outputShpfile) throws Exception{
        //创建一个简单shp类型，the_geom可以是点线或者面,这里用面表示赤潮
        final SimpleFeatureType TYPE =
                DataUtilities.createType(
                        "Location",
                        "the_geom:Polygon:EPSG=4326"
                        );
        //创建一个列表来存放SimpleFeature，也就是简单要素对象
        List<SimpleFeature> features = new ArrayList<>();
        
        //创建要素（feature）的工厂
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);//创建一个简单要素创建者（SimpleFeatureBuilder），并把先前的TYPE作为构造参数
        
        //创建ncdf工具对象，读取有效赤潮点位及其对应的经纬度
        NetcdfUtils netcdfUtils = new NetcdfUtils(fileName);
        netcdfUtils.setRedtideAlgorithm(redtideAlgorithm);
        
        //！！！！！重要，子类中实现模板方法模式中的步骤，所以该方法设置为抽象方法！！！
        boolean [][] isRedtide = netcdfUtils.getRedtide();
        
        //设置方法修改赤潮信息，用于优化显示赤潮信息
        //默认实现就返回原来的结果
        isRedtide = updateRedtideData(isRedtide);
        
        double [][] longitude = netcdfUtils.getGeo(NetcdfUtils.LONGITUDE);
        double [][] latitude = netcdfUtils.getGeo(NetcdfUtils.LATITUDE);
        
        //声明点对象
        Point point = null;
        Geometry buffer = null;
        SimpleFeature feature = null;
        boolean isRedtided = false;
        //用来计算赤潮的像元数
        int redtideFlag = 0;
        double longitudePoint = 0;
        double latitudePoint = 0;
        for(int i=0; i < isRedtide.length ; i++ ) {
        	for(int j=0; j < isRedtide[0].length ; j++) {
        		longitudePoint = longitude[i][j];
        		latitudePoint = latitude[i][j];
        		//如果赤潮值为真，且经纬度在范围内，插入点，为了防止超出地图，赤潮判定范围要稍小于总体经纬度
        		if (isRedtide[i][j] && 119<longitudePoint && longitudePoint<129 && latitudePoint>26 && latitudePoint<34 ) {
        			synchronized (obj1) {
        				//根据经纬度信息，创建点对象（作为位置），顺序为x：经度，y：纬度
                		point = geometryFactory.createPoint(new Coordinate(longitudePoint, latitudePoint));
					}
            		//用公式计算出边长，得到缓冲区。第二个参数为1的时候缓冲区为四边形
            		buffer = point.buffer(0.005 * Math.sqrt(2),1);
					featureBuilder.add(buffer);//把图形加入工厂
                	feature = featureBuilder.buildFeature(null);//工厂产生要素
                    features.add(feature);//把要素加入List<SimpleFeature>集合
                    redtideFlag++;
				}
        	}
        }
        System.out.println(redtideFlag);
        if (!isRedtided) {
        	synchronized (obj1) {
        		point = geometryFactory.createPoint(new Coordinate(119, 26));
        	}
        	//最好可以小到忽略不计，防止没有点无法创建shp
        	buffer = point.buffer(0.0000000001);
			featureBuilder.add(buffer);//把图形加入工厂
        	feature = featureBuilder.buildFeature(null);//工厂产生要素
            features.add(feature);//把要素加入List<SimpleFeature>集合
		}
        
        //创建一个输出文件。
        File newFile = new File(outputShpfile);
        
        Map<String, Serializable> params = new HashMap<>();//用hashmap来封装参数
        params.put("url", newFile.toURI().toURL());//新的shp的url地址
        params.put("create spatial index", Boolean.TRUE);//设置空间索引为true
        
        ShapefileDataStore shapefileDataStore = null;
        
        synchronized (obj2) {
        	 shapefileDataStore =
        			 (ShapefileDataStore) shapefileDataStoreFactory.createNewDataStore(params);//用shp文件数据存储工厂的createNewDataStore(params)来创建新的Shp数据存储
		}
       
        
        shapefileDataStore.createSchema(TYPE);
        
        Transaction transaction = new DefaultTransaction("create");//有点像事务，暂且当他是事务把
        
        String typeName = shapefileDataStore.getTypeNames()[0];//获取shp数据存储的所有Type名字并取得索引为0的那个名字为typename
        SimpleFeatureSource featureSource = shapefileDataStore.getFeatureSource(typeName);//shp数据存储的getFeatureSource(typeName)方法得到要素源
        SimpleFeatureType SHAPE_TYPE = featureSource.getSchema();//调用要素源的获得图片方法获得SimpleFeatureType 的SHAPE_TYPE（和TYPE是同一个类）

        if (featureSource instanceof SimpleFeatureStore) {//查看要素源是否属于简单要素存储类型是的话往下执行。
            SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;//把要素源向下转型为简单要素存储（真的绕）
            
            SimpleFeatureCollection collection = new ListFeatureCollection(TYPE, features);//用SimpleFeatureType和List<SimpleFeature>创建简单要素集合
            featureStore.setTransaction(transaction);//要素存储开启事务
            try {
                featureStore.addFeatures(collection);//要素存储用添加方法,添加前面的简单要素集合
                transaction.commit();//要素存储提交事务
                
            } catch (Exception problem) {
                problem.printStackTrace();
                transaction.rollback();
            } finally {
                transaction.close();
            }
        } else {
        	ThreadOut.println(typeName + " does not support read/write access");//如果要素源不属于简单要素存储就打印无法读写。
        }
        ThreadOut.println("Shpefile finshed");
        return new RTResult().ok(redtideFlag);
	}

	protected static boolean[][] updateRedtideData(boolean[][] isRedtide) {
		if (updateData == null) {
			return isRedtide;
		}
		return updateData;
	}

	//public abstract boolean[][] getRedtide(NetcdfUtils netcdfUtils);

	//---------------------------------------------以下是getter和setter，一般来说没什么用
	public static void setUpdateData(boolean[][] updateData) {
		GeoToolsUtils.updateData = updateData;
	}
	
	//以下是原版代码
	/*public RTResult getShpByOldFunction(String fileName,String outputShpfile) throws Exception{
        //创建一个简单shp类型，the_geom可以是点线或者面,这里用面表示赤潮
        final SimpleFeatureType TYPE =
                DataUtilities.createType(
                        "Location",
                        "the_geom:Polygon:EPSG=4326"
                        );
        //创建一个列表来存放SimpleFeature，也就是简单要素对象
        List<SimpleFeature> features = new ArrayList<>();
        
        //几何工厂，用来创建点对象，例如点线面
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();//创建一个几何工厂（GeometryFactory）
        //创建要素（feature）的工厂
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);//创建一个简单要素创建者（SimpleFeatureBuilder），并把先前的TYPE作为构造参数
        
        //创建ncdf工具对象，读取有效赤潮点位及其对应的经纬度
        NetcdfUtils netcdfUtils = new NetcdfUtils(fileName);
        boolean [][] isRedtide = netcdfUtils.getRedtideByOldeFunction();
        double [][] longitude = netcdfUtils.getGeo(NetcdfUtils.LONGITUDE);
        double [][] latitude = netcdfUtils.getGeo(NetcdfUtils.LATITUDE);
        //声明点对象
        Point point = null;
        for(int i=0; i < isRedtide.length ; i++ ) {
        	for(int j=0; j < isRedtide[0].length ; j++) {
        		//如果赤潮值为真，插入点
        		if (isRedtide[i][j]) {
        			 //根据经纬度信息，创建点对象（作为位置），顺序为x：经度，y：纬度
        			point = geometryFactory.createPoint(new Coordinate(longitude[i][j], latitude[i][j]));
        			//用公式计算出边长，得到缓冲区。第二个参数为1的时候缓冲区为四边形
        			Geometry buffer = point.buffer(0.005 * Math.sqrt(2),1);
        			featureBuilder.add(buffer);//把图形加入工厂
                	SimpleFeature feature = featureBuilder.buildFeature(null);//工厂产生要素
                    features.add(feature);//把要素加入List<SimpleFeature>集合
				}
        	}
        }
        
         * 从FeatureCollection创建shapefile
			在我们创建shapefile时需要注意的事项：
			使用DataStoreFactory和一个指示我们需要空间索引的参数
			使用createSchema（SimpleFeatureType）方法来设置shapefile（我们将在下一节中创建getNewShapeFile方法）
         * 
         
        
        //创建一个输出文件。
        File newFile = new File(outputShpfile);
        
        ShapefileDataStoreFactory shapefileDataStoreFactory = new ShapefileDataStoreFactory();//创建shp文件数据存储工厂

        Map<String, Serializable> params = new HashMap<>();//用hashmap来封装参数
        params.put("url", newFile.toURI().toURL());//新的shp的url地址
        params.put("create spatial index", Boolean.TRUE);//设置空间索引为true

        ShapefileDataStore shapefileDataStore =
 (ShapefileDataStore) shapefileDataStoreFactory.createNewDataStore(params);//用shp文件数据存储工厂的createNewDataStore(params)来创建新的Shp数据存储

        
         * TYPE is used as a template to describe the file contents
         * TYPE用作模板来描述文件内容
         
        shapefileDataStore.createSchema(TYPE);
        
        
         * 将特征数据写入shapefile
			注意事项：
			通过确认我们的FeatureSource对象实现FeatureStore方法，我们检查是否具有读取和写入权限
			花点时间来检查shapefile能够匹配我们的模板（SimpleFeatureType TYPE）的密切程度。 比较这个输出，看看它们是如何不同的。
			我们用来做这件事的SimpleFeatureStore需要一个FeatureCollection对象，所以我们在ListFeatureCollection中包装我们的功能列表。
			使用transaction.commit（）可以一次性安全地写出要素。
         
        
        
         * Write the features to the shapefile
         
        Transaction transaction = new DefaultTransaction("create");//有点像事务，暂且当他是事务把

        String typeName = shapefileDataStore.getTypeNames()[0];//获取shp数据存储的所有Type名字并取得索引为0的那个名字为typename
        SimpleFeatureSource featureSource = shapefileDataStore.getFeatureSource(typeName);//shp数据存储的getFeatureSource(typeName)方法得到要素源
        SimpleFeatureType SHAPE_TYPE = featureSource.getSchema();//调用要素源的获得图片方法获得SimpleFeatureType 的SHAPE_TYPE（和TYPE是同一个类）
        
         * The Shapefile format has a couple limitations:
         * Shapfile文件的格式有一些限制
         * - "the_geom" is always first, and used for the geometry attribute name
         * "the_geom" 要在第一个，作用于集合属性名字
         * - "the_geom" must be of type Point, MultiPoint, MuiltiLineString, MultiPolygon
         * "the_geom"一定要是点，多个点，多个线，多个面之中的一个。
         * - Attribute names are limited in length
         * 属性名字有限制长度（15吧好像是）
         * - Not all data types are supported (example Timestamp represented as Date)
         *	并不支持所有的数据类型，比如时间戳会被当做日期
         * Each data store has different limitations so check the resulting SimpleFeatureType.
         * 每个数据存储都有不同的限制，所以请检查生成的SimpleFeatureType，也就是SHAPE_TYPE
         
        System.out.println("SHAPE:" + SHAPE_TYPE);

        if (featureSource instanceof SimpleFeatureStore) {//查看要素源是否属于简单要素存储类型是的话往下执行。
            SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;//把要素源向下转型为简单要素存储（真的绕）
            
             * SimpleFeatureStore has a method to add features from a
             * SimpleFeatureCollection object, so we use the ListFeatureCollection
             * class to wrap our list of features.
             * SimpleFeatureStore有一个方法来添加来自SimpleFeatureCollection对象的特征，所以我们使用ListFeatureCollection类来包装我们的特征列表。
             
            SimpleFeatureCollection collection = new ListFeatureCollection(TYPE, features);//用SimpleFeatureType和List<SimpleFeature>创建简单要素集合
            featureStore.setTransaction(transaction);//要素存储开启事务
            try {
                featureStore.addFeatures(collection);//要素存储用添加方法,添加前面的简单要素集合
                transaction.commit();//要素存储提交事务
            } catch (Exception problem) {
                problem.printStackTrace();
                transaction.rollback();
            } finally {
                transaction.close();
            }
            System.exit(0); // success!
        } else {
            System.out.println(typeName + " does not support read/write access");//如果要素源不属于简单要素存储就打印无法读写。
            System.exit(1);
        }
        return new RTResult().ok();
	}*/
	
	//设置算法
	public static void setRedtideAlgorithm(RedtideAlgorithm redtideAlgorithm) {
		GeoToolsUtils.redtideAlgorithm = redtideAlgorithm;
	}
}
