package com.zhuhangjie.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.junit.Test;

public class DownloadTest {

	/*@Test
	public void downloadChla() throws Exception {
		String name = "A2017272044";
		String dateInfo = name.substring(1, 8);
		String picDate = "2017-08-24";
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
			System.out.println("正在下载");
			while ((nRead = input.read(b, 0, 1024)) > 0 && nStartPos < nEndPos) {
				oSavedFile.write(b, 0, nRead);
				nStartPos += nRead;
			}
			System.out.println("下载完成");
			httpConnection.disconnect();
			input.close();
			oSavedFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void downloadCloud() throws Exception {
		String name = "A2017272044";
		String dateInfo = name.substring(1, 8);
		String picDate = "2017-08-24";
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
			System.out.println("文件已存在");
		}
		os = new FileOutputStream(sf.getPath() + sep + "cloud" + picDate + ".jpg");    
		os.write(imgBytes, 0, imgBytes.length);   
		System.out.println("图片保存成功");
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
	}*/
}
