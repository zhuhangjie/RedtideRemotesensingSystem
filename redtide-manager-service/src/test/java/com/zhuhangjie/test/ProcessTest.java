package com.zhuhangjie.test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;

import org.junit.Test;

public class ProcessTest {

	private String fullName;
	
	/*@Test
	public void processTest() throws Exception {
		//将文件名写入txt
		fullName = "A2016314050500.L2_LAC_OC.nc";
				File filename = new File("");
				FileWriter writer = new FileWriter("netcdf_filename.txt");
				writer.write("F:\\RedTideData\\oc\\" + fullName);
				writer.flush();
				writer.close();
				System.out.println("写入源文件");
				
				//运行exe将OC转化为shp
				Runtime rt = Runtime.getRuntime();
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
			    	  File newShp = new File(rootPath+"\\RT"+"test"+".shp");
			    	  File newShx = new File(rootPath+"\\RT"+"test"+".shx");
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
			      }
			   
			    }
			    catch (Exception e) {
			      System.out.println("Error exec ConvertData.exe");
			    }
			   

			
			    //将shp数据打包
			 byte[] buffer = new byte[1024];   
			  
		       //生成的ZIP文件名为Demo.zip   
		  
		       String strZipName = SHP_ZIP_PATH+date+".zip";   
		  
		       ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipName));   
		  
		       //需要同时下载的两个文件result.txt ，source.txt   
		  
		       File[] file1 = {new File(SHP_ZIP_PATH+date+".shx"),new File(SHP_ZIP_PATH+date+".shp")};   
		  
		       for(int i=0;i<file1.length;i++) {   
		  
		           FileInputStream fis = new FileInputStream(file1[i]);   
		  
		           out.putNextEntry(new ZipEntry(file1[i].getName()));   
		  
		           int len;   
		  
		           //读入需要下载的文件的内容，打包到zip文件   
		  
		          while((len = fis.read(buffer))>0) {   
		  
		           out.write(buffer,0,len);    
		  
		          }   
		  
		           out.closeEntry();   
		  
		           fis.close();   
		  
		       }   
		  
		        out.close();   
		  
		        System.out.println("生成zip成功");   
	}*/
}
