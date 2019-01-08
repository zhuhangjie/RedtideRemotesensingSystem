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
import org.springframework.beans.factory.annotation.Value;
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
import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.mapper.PicMapper;
import com.zhuhangjie.pojo.Pic;
import com.zhuhangjie.service.PicService;

public class DownloadJob {
	
	@Value("${TIFF_SAVE_PATH}")
	private String TIFF_SAVE_PATH;
	
	@Value("${CLOUD_SAVE_PATH}")
	private String CLOUD_SAVE_PATH;
	
	public void run() {
/*		//得到当前日历
		Calendar calendar = Calendar.getInstance();
		//得到当前日历对应的月中日数
		int tempDate = calendar.get(Calendar.DATE);
		//把日历设置成前一天
		calendar.set(Calendar.DATE, tempDate-1);
		//把日历转换成昨天的日期对象
		Date dateBeforeToday = calendar.getTime();
		//把昨天的日期转换成yyyy-MM-dd的形式
		String picDate = new SimpleDateFormat("yyyy-MM-dd").format(dateBeforeToday);
		
		//下载叶绿素tiff数据
		//datainfo为2017213这种形式
		String dateInfo = DayToDate.convertToDayOfYear(dateBeforeToday);
		//tiff数据下载路径
		String sURL = "https://gibs.earthdata.nasa.gov/image-download?TIME="+dateInfo+"&extent=120,25,125,32&epsg=4326&layers=Coastlines,MODIS_Aqua_Chlorophyll_A&opacities=1,1&worldfile=false&format=image/geotiff&width=592&height=800";
		int nStartPos = 0;
		int nRead = 0;
		String sep = File.separator;
		InputStream input = null;
		RandomAccessFile raf = null;
		//System.out.println(sURL);
		try{
			File tiffPath=new File(TIFF_SAVE_PATH);    
			if(!tiffPath.exists()){    
				tiffPath.mkdirs();    
			}
			File file = new File(tiffPath.getPath() + sep + "chl" + picDate +".tiff");
			if (!file.exists()) {
				URL url = new URL(sURL);
				// 打开连接
				HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
				// 获得文件长度
				long nEndPos = 1422830;
				//tiff保存路径
				raf = new RandomAccessFile(tiffPath.getPath() + sep + "chl" + picDate +".tiff", "rw");
				httpConnection.setRequestProperty("User-Agent", "Internet Explorer");
				String sProperty = "bytes=" + nStartPos + "-";
				// 告诉服务器book.rar这个文件从nStartPos字节开始传
				httpConnection.setRequestProperty("RANGE", sProperty);
				System.out.println(sProperty);
				input = httpConnection.getInputStream();
				byte[] b = new byte[1024];
				// 读取网络文件,写入指定的文件中
				System.out.println("叶绿素正在下载");
				while ((nRead = input.read(b, 0, 1024)) > 0 && nStartPos < nEndPos) {
					raf.write(b, 0, nRead);
					nStartPos += nRead;
				}
				System.out.println("叶绿素下载完成");
				httpConnection.disconnect();
			}else {
				System.out.println("叶绿素文件已存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if (input != null) {
					input.close();
				}
				if (raf != null) {
					raf.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		OutputStream os = null;  
		try {  
			//检查文件夹是否存在
			File cloudPath=new File(CLOUD_SAVE_PATH);   
			//不过文件夹不存在，创建路径名的文件夹
			if(!cloudPath.exists()){    
				cloudPath.mkdirs();    
			}    
			//创建文件
			File file = new File(cloudPath.getPath() + sep + "cloud" + picDate + ".jpg");
			//如果该路径文件不存在则开始下载，否则
			if(!file.exists()){
				System.out.println("开始下载云图");
				//Jsoup网页解析器
				byte[] imgBytes = Jsoup.connect("http://gibs.earthdata.nasa.gov/image-download?TIME=" + picDate + "&&extent=117.017578125,25.3740234375,129.322265625,33.1435546875&epsg=4326&layers=MODIS_Aqua_CorrectedReflectance_TrueColor,Coastlines&opacities=1,1&worldfile=false&format=image/jpeg&width=1400&height=884").timeout(20000).ignoreContentType(true).execute().bodyAsBytes();
				os = new FileOutputStream(cloudPath.getPath() + sep + "cloud" + picDate + ".jpg");  
				os.write(imgBytes, 0, imgBytes.length);  
				System.out.println("云图下载成功");
			}else {
				System.out.println("云图已存在");
			}
		} catch (Exception e) {  
			e.printStackTrace();  
			System.out.println("云图保存异常：" + picDate + ".jpg");  
		}finally{  
			try {  
				// 完毕，关闭所有链接
				if (os != null) {
					os.close(); 
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}  
		}  */
	}
}
