package com.zhuhangjie.service.quartz;

import java.awt.Color;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.supermap.data.Dataset;
import com.supermap.data.DatasetImage;
import com.supermap.data.DatasetVector;
import com.supermap.data.Datasets;
import com.supermap.data.Datasource;
import com.supermap.data.FillGradientMode;
import com.supermap.data.Maps;
import com.supermap.data.PrjCoordSys;
import com.supermap.data.Workspace;
import com.supermap.data.WorkspaceConnectionInfo;
import com.supermap.data.WorkspaceType;
import com.supermap.data.conversion.DataImport;
import com.supermap.data.conversion.ImportMode;
import com.supermap.data.conversion.ImportResult;
import com.supermap.data.conversion.ImportSetting;
import com.supermap.data.conversion.ImportSettingSHP;
import com.supermap.data.conversion.ImportSettingTIF;
import com.supermap.mapping.LayerSettingImage;
import com.supermap.mapping.LayerSettingVector;
import com.supermap.mapping.Layers;
import com.supermap.mapping.Map;
import com.supermap.ui.MapControl;
import com.zhuhangjie.common.utils.DayToDate;
import com.zhuhangjie.common.utils.NameConStr;
import com.zhuhangjie.mapper.PicMapper;
import com.zhuhangjie.pojo.Pic;
import com.zhuhangjie.service.PicService;

public class DownloadJob {
	@Autowired
	private PicService picService;
	
	public void run() {
		//创建新的图片对象
		Pic pic = new Pic();
		//得到当前日期
		Date date = new Date();
		//得到当前日历
		Calendar calendar = Calendar.getInstance();
		//得到当前日历对应的月中日数
		int tempDate = calendar.get(Calendar.DATE);
		//把日历设置成前一天
		calendar.set(Calendar.DATE, tempDate-1);
		//把日历转换成昨天的日期对象
		Date dateBeforeToday = calendar.getTime();
		String picDate = new SimpleDateFormat("yyyy-MM-dd").format(dateBeforeToday);
		//把对象的日期字段设置成昨天的日期
		pic.setDate(dateBeforeToday);
		//用工具类把日期转换成名字，再设置对象名字
		pic.setName(NameConStr.DateToName(dateBeforeToday));
		//设置日期
		pic.setYear(calendar.get(Calendar.YEAR));
		//用工具类把昨天的日期对象封装成yyyy-day的形式的字符串
		String dateInfo = DayToDate.convertToDayOfYear(dateBeforeToday);
		String sURL = "https://gibs.earthdata.nasa.gov/image-download?TIME="+dateInfo+"&extent=120,25,125,32&epsg=4326&layers=Coastlines,MODIS_Aqua_Chlorophyll_A&opacities=1,1&worldfile=false&format=image/geotiff&width=592&height=800";
		int nStartPos = 0;
		int nRead = 0;
		//System.out.println(sURL);
		String sPath = "F:\\RedTideData\\chla\\";   //输出路径
		try {
			URL url = new URL(sURL);
			// 打开连接
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			// 获得文件长度
			//long nEndPos = getFileSize(sURL);
			long nEndPos = 1422830;
			RandomAccessFile oSavedFile = new RandomAccessFile(sPath + "chl" + picDate +".tiff", "rw");
			httpConnection.setRequestProperty("User-Agent", "Internet Explorer");
			String sProperty = "bytes=" + nStartPos + "-";
			// 告诉服务器book.rar这个文件从nStartPos字节开始传
			httpConnection.setRequestProperty("RANGE", sProperty);
			System.out.println(sProperty);
			InputStream input = httpConnection.getInputStream();
			byte[] b = new byte[1024];
			// 读取网络文件,写入指定的文件中
			System.out.println("叶绿素正在下载");
			while ((nRead = input.read(b, 0, 1024)) > 0 && nStartPos < nEndPos) {
				oSavedFile.write(b, 0, nRead);
				nStartPos += nRead;
			}
			
			System.out.println("叶绿素下载完成");
			pic.setChl("1");
			httpConnection.disconnect();
			input.close();
			oSavedFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//下载云图数据
		try {
			//下载云图逻辑
			OutputStream os = null;  
			try {  
			//Jsoup网页解析器
			byte[] imgBytes = Jsoup.connect("http://gibs.earthdata.nasa.gov/image-download?TIME=" + picDate + "&&extent=117.017578125,25.3740234375,129.322265625,33.1435546875&epsg=4326&layers=MODIS_Aqua_CorrectedReflectance_TrueColor,Coastlines&opacities=1,1&worldfile=false&format=image/jpeg&width=1400&height=884").timeout(20000).ignoreContentType(true).execute().bodyAsBytes();  
			// 输出的文件流    
			File sf=new File("F:\\RedTideData\\cloud\\");    
			if(!sf.exists()){    
			sf.mkdirs();    
			}    
			String sep = File.separator;  
			File file = new File(sf.getPath() + sep + "cloud" + picDate + ".jpg");
			if(file.exists()){
				System.out.println("云图已存在");
				
			}
			os = new FileOutputStream(sf.getPath() + sep + "cloud" + picDate + ".jpg");    
			os.write(imgBytes, 0, imgBytes.length);  
			pic.setCloud("1");
			System.out.println("云图保存成功");
			} catch (Exception e) {  
			e.printStackTrace();  
			System.out.println("保存图片异常：" + picDate + ".jpg");  
			}finally{  
			try {  
			// 完毕，关闭所有链接    
			os.close();    
			} catch (Exception e2) {  
			}  
			}  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//处理矢量数据
		/*String fullName = pic.getName() + ".nc";
		File filename = new File("");
		FileWriter writer;
		try {
			writer = new FileWriter("netcdf_filename.txt");
			writer.write("F:\\RedTideData\\oc\\" + fullName);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("写入源文件");*/
		
		//由於目前缺少数据源所以无法进行以下操作
		//运行exe将OC转化为shp
		/*Runtime rt = Runtime.getRuntime();
	    Process p = null;
	    try {
	    	System.out.println("启动处理程序");
	    	p = rt.exec("D:\\1.8WorkSpace\\redtide-manager\\redtide-manager-service\\red_tide_area.exe");
	        p.waitFor();//等待完成
	        System.out.println("处理完成");
	        File exShp = new File("F:\\RedTideData\\redtide\\red_tide_area.shp");
	        File exShx = new File("F:\\RedTideData\\redtide\\red_tide_area.shx");
	      
	      if(!exShp.exists() && !exShx.exists())
	      {       	  
	    	  System.out.println("nodata");
	      }
	      else
	      {
	    	//修改文件名为shp+日期
	    	  String rootPath = exShp.getParent();
	    	  File newShp = new File(rootPath+"\\RT"+picDate+".shp");
	    	  File newShx = new File(rootPath+"\\RT"+picDate+".shx");
	    	  System.out.println("修改后文件名称是："+newShp.getName());
	    	  if (exShp.renameTo(newShp)) 
	    	  {
	    	   System.out.println("修改成功!");
	    	  } 
	    	  else 
	    	  {
	    	   System.out.println("修改失败");
	    	  }
	    	  //修改文件名为shx+日期
	    	  if (exShx.renameTo(newShx)) 
	    	  {
	    	   System.out.println("修改成功!");
	    	  } 
	    	  else 
	    	  {
	    	   System.out.println("修改失败");
	    	  }
	    	  pic.setRt("1");
	      }
	   
	    }
	    catch (Exception e) {
	      System.out.println("Error exec ConvertData.exe");
	    }*/
		
		//获取秘钥并且删除工作空间的发布
	    String token = null;
	    try {
			URL url = new URL("http://localhost:8090/iserver/services/security/tokens.rjson");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Accept", "application/json");//设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json");//设置发送数据的格式
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			out.append("{\"userName\": \"admin\",\"password\": \"123456\",\"clientType\": \"RequestIP\",\"expiration\": 60}");
			out.flush();
			out.close();
			
			int code = connection.getResponseCode();  
            InputStream is = null;  
            if (code == 200) {  
                is = connection.getInputStream();  
            } else {  
                is = connection.getErrorStream();  
            }  
            int length = (int) connection.getContentLength();
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8");
                System.out.println("获取秘钥成功");
                token = result;
            }
            
            
            //删除工作空间
            url = new URL("http://localhost:8090/iserver/manager/workspaces.rjson?token=" + token);
			connection = (HttpURLConnection) url.openConnection();  
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("PUT");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Accept", "application/json");//设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json");//设置发送数据的格式
			connection.setRequestProperty("Charset", "UTF-8");
			DataOutputStream dataOut = new DataOutputStream(connection.getOutputStream());
			String jsonParam="{\"workspaceConnectionInfo\":\"D:\\\\SM\\\\sampleworkspace.smwu\"}";
			dataOut.writeBytes(jsonParam);
			dataOut.flush();
			dataOut.close();
			
			code = connection.getResponseCode();  
            is = null;  
            if (code == 200) {  
                is = connection.getInputStream();  
            } else {  
                is = connection.getErrorStream();  
            }  
            length = (int) connection.getContentLength();
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8");
                System.out.println(result);
                System.out.println("删除工作空间成功");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
	  //用iobject发布
	    Workspace workspace = new Workspace();
	    MapControl mapControl = new MapControl();
	    Map map = null;
	    Maps maps = null;
	    Layers layers;
	    try {
	    	// 打开工作空间
    		WorkspaceConnectionInfo conInfo = new WorkspaceConnectionInfo(
    		"D:/SM/sampleworkspace.smwu");
    		conInfo.setType(WorkspaceType.SMWU);
    		workspace.open(conInfo);
    		System.out.println("工作空间打开成功");
    		
    		
    		//获得数据源
			Datasource datasource = workspace.getDatasources().get("temp");
			System.out.println("数据源获取成功");
			//矢量数据导入设置对相
			/*ImportSettingSHP iss= new ImportSettingSHP();*/
			//设置导入参数
			/*iss.setImportMode(ImportMode.OVERWRITE);
			iss.setTargetDatasource(datasource);
			iss.setSourceFilePath("F:/RedTideData/redtide/RT"+ picDate +".shp");*/
			//数据导入对象
			//DataImport di = new DataImport();
			//取出设置集，加入设置
			//di.getImportSettings().add(iss);
			//运行导入
			/*ImportResult result = di.run();*/
			/*ImportSetting[] failedSettings = result.getFailedSettings();
			if(failedSettings.length == 0) {
				System.out.println("矢量数据成功数据导入数据集");
			} else {
				System.out.println("矢量数据导入失败");
			}
			di.dispose();*/
			
			
			//影像数据导入设置对相
			ImportSettingTIF ist = new ImportSettingTIF();
			//设置导入参数
			ist.setImportMode(ImportMode.OVERWRITE);
			ist.setTargetDatasource(datasource);
			//isi.setMultiBandImportMode(MultiBandImportMode.MULTIBAND);
			ist.setPyramidBuilt(false);
			ist.setSourceFilePath("F:/RedTideData/chla/chl" + picDate + ".tiff");
			//数据导入对象
			DataImport di = new DataImport();
			//取出设置集，加入设置
			di.getImportSettings().add(ist);
			//运行设置软件 
			ImportResult result = di.run();
			//获取结果信息
			ImportSetting[] failedSettings = result.getFailedSettings();
			if(failedSettings.length == 0) {
				System.out.println("叶绿素数据成功数据导入数据集");
			} else {
				System.out.println("叶绿素导入失败");
			}
			
			//本身就是1984坐标系不用导入坐标系
			di.dispose();
			
			
			
			String picDate_afer = picDate.replaceAll("-", "_");
			Datasets datasets = datasource.getDatasets();
			
			/*//获取样品数据集的坐标
			Dataset dataset1 = datasets.get("templet");
			PrjCoordSys prjCoordSys1 = dataset1.getPrjCoordSys();
			PrjCoordSys prjCoordSys2 = new PrjCoordSys(prjCoordSys1);
			//把坐标赋给新加入的数据集
			Dataset dataset = datasets.get("RT" + picDate_afer);
			dataset.setPrjCoordSys(prjCoordSys2);
			System.out.println("坐标系定义完成（WGS1984）");*/
			
			//通过地图控件得到地图对象
			map = mapControl.getMap();
			//地图对象与工作空间关联
			map.setWorkspace(workspace);
			maps = workspace.getMaps();
			
			//打开指定序号地图才能编辑
			map.open(maps.get(0));
			//把底图转换为xml
			String base_xml = map.toXML();
			
			//把底图作为新地图添加到底图集合
			/*maps.add("RT" + picDate_afer + "map", base_xml);
			//用地图打开新加的地图
			map.open(maps.get(maps.indexOf("RT" + picDate_afer + "map")));
			//通过地图获取图层
			layers = map.getLayers();
			System.out.println("取得图层集");*/
			/*//把数据集转换成矢量数据集
			dataset = (DatasetVector) dataset;
			//设置矢量风格
			LayerSettingVector setting_vector = new LayerSettingVector();
			setting_vector.getStyle().setLineColor(Color.RED);
			setting_vector.getStyle().setLineSymbolID(11);
			setting_vector.getStyle().setLineWidth(0.5);
			setting_vector.getStyle().setFillForeColor(new Color(255, 0, 0));
			setting_vector.getStyle().setFillBackColor(new Color(255, 0, 0));
			setting_vector.getStyle().setFillGradientMode(FillGradientMode.RADIAL);
			//图层集添加图层
			layers.add(dataset,setting_vector,true);
			//设置新添加的图层最小显示集合图形尺寸为0
			layers.get(0).setMinVisibleGeometrySize(0);
			//将编辑后的地图转换成XML
			String xml_rt = map.toXML();
			//再将XML保存到工作空间地图的指定序号位置
			workspace.getMaps().setMapXML(maps.indexOf("RT" + picDate_afer + "map"), xml_rt);
			System.out.println("赤潮信息地图保存成功");*/
			
			
			maps.add("chl" + picDate_afer + "map",base_xml);
			map.open(maps.get(maps.indexOf("chl" + picDate_afer + "map")));
			layers = map.getLayers();
			DatasetImage datasetChl = (DatasetImage) datasource.getDatasets()
					.get("chl" + picDate_afer);
			LayerSettingImage setting_image = new LayerSettingImage();
			//设置背景透明色
			setting_image.setTransparent(true);
			setting_image.setTransparentColorTolerance(150);
			layers.add(datasetChl,setting_image,true);
			//把编辑的地图保存到地图组
			String xml_chl = map.toXML();
			workspace.getMaps().setMapXML(maps.indexOf("chl" + picDate_afer + "map"), xml_chl);
			System.out.println("叶绿素信息导入成功");
			//System.out.println(dataset.getName());
			
			workspace.save();
			map.close();
			workspace.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
		//发布工作空间
	    try {

			URL url = new URL("http://localhost:8090/iserver/manager/workspaces.rjson?token=" + token);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Accept", "application/json");//设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json");//设置发送数据的格式
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			out.append("{\"servicesTypes\":[\"RESTMAP\",\"RESTDATA\",\"WMS111\"],\"workspaceConnectionInfo\":\"D:\\\\SM\\\\sampleworkspace.smwu\"}");
			out.flush();
			out.close();
			System.out.println("发布成功");
			int code = connection.getResponseCode();  
			InputStream is = null;  
            if (code == 200) {  
                is = connection.getInputStream();  
            } else {  
                is = connection.getErrorStream();  
            }  
            int length = (int) connection.getContentLength();
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8");
                System.out.println(result);
            }
          //将pic保存到数据库
    		System.out.println("正在插入数据");
    		picService.savePic(pic);
    		System.out.println("插入完成");
		}catch(Exception e) {
		}
	}
}
