package com.zhuhangjie.service.impl;

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
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermap.data.*;
import com.supermap.data.conversion.*;
import com.supermap.mapping.*;
import com.supermap.ui.MapControl;
import com.zhuhangjie.common.jedis.JedisClient;
import com.zhuhangjie.common.utils.DayToDate;
import com.zhuhangjie.common.utils.EasyUIResult;
import com.zhuhangjie.common.utils.JsonUtils;
import com.zhuhangjie.common.utils.NameConStr;
import com.zhuhangjie.common.utils.RTResult;
import com.zhuhangjie.common.utils.ThreadOut;
import com.zhuhangjie.common.utils.algorithm.DiatomRedtideAlgorithm;
import com.zhuhangjie.common.utils.algorithm.ProrocentrumRedtideAlgorithm;
import com.zhuhangjie.common.utils.algorithm.RedtideAlgorithm;
import com.zhuhangjie.common.utils.geotools.GeoToolsUtils;
import com.zhuhangjie.mapper.PicMapper;
import com.zhuhangjie.pojo.Pic;
import com.zhuhangjie.pojo.PicExample;
import com.zhuhangjie.pojo.QueryPoJo;
import com.zhuhangjie.pojo.PicExample.Criteria;
import com.zhuhangjie.service.PicService;

@Service
public class PicServiceImpl implements PicService{
	
	@Autowired
	private PicMapper picMapper;
	
	@Value("${OC_SAVE_PATH}")
	private String OC_SAVE_PATH;
	
	@Value("${SHP_RESULT_PATH}")
	private String SHP_RESULT_PATH;
	
	@Value("${SUPERMAP_DATABASE_URL}")
	private String SUPERMAP_DATABASE_URL;
	
	@Value("${TIFF_SAVE_PATH}")
	private String TIFF_SAVE_PATH;
	
	@Value("${CLOUD_SAVE_PATH}")
	private String CLOUD_SAVE_PATH;
	
	@Value("${SUPERMAP_WORKSPACE_PATH}")
	private String SUPERMAP_WORKSPACE_PATH;
	
	@Value("${SHP_PRE}")
	private String SHP_PRE;
	
	@Value("${CHL_PRE}")
	private String CHL_PRE;
	
	@Value("${CLOUD_PRE}")
	private String CLOUD_PRE;
	
	@Override
	public Pic selectPicById(Integer id) {
		Pic pic = picMapper.selectByPrimaryKey(id);
		return pic;
	}
	
	@Override
	public RTResult insertPic(Date date,String fileName) {
		//文件路径
		String picName = "";
		//如果日期为空返回错误
		if(date == null) {
			return RTResult.build(300, "请填写日期");
		}
		//建立新图片对象
		Pic pic  = new Pic();
		//先设置赤潮面积为0
		pic.setArea(0D);
		//设置图片日期
		pic.setDate(date);
		//设置图片名称(名称工具类计算得到)
		if (fileName == null || fileName == "") {
			pic.setName(NameConStr.DateToName(date));
			picName = OC_SAVE_PATH + pic.getName() + ".nc";
		} else {
			pic.setName(fileName);
			picName = OC_SAVE_PATH + fileName + ".nc";
		}
		
		String picDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		picDate = sdf.format(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		
		//下载原始oc数据，假装能下载，实际已经下载不了，假装下载好了已经
		
		//处理矢量数据
		
		//判断正确命名的nc数据是否存在，如果不在就直接返回，不要执行后面的方法
		File ncFile = new File(picName);
		if(!ncFile.exists()) {
			System.out.println("请检查nc文件是否存在");
			return RTResult.build(300, "nc文件找不到");
		}
		//暂时通过判断nc在不在来定pic中的nc属性，一般情况下是要在下载成功后设置为真
		pic.setNc(true);
		
		//定义两种藻类类型赤潮的输出路径
		String outputShpfileOfProrocentrum = SHP_RESULT_PATH + "RT" + picDate + "p.shp";
		String outputShpfileOfDiatom = SHP_RESULT_PATH + "RT" + picDate + "d.shp";
		//创建需要使用的算法对象这里是东海原甲藻
		RedtideAlgorithm redtideAlgorithm = new ProrocentrumRedtideAlgorithm();
		//工具类中注入算法
		GeoToolsUtils.setRedtideAlgorithm(redtideAlgorithm);
		try {
			//运行工具类生成赤潮
			RTResult resultP = GeoToolsUtils.getShape(picName, outputShpfileOfProrocentrum);
			//将算法改成硅藻算法
			redtideAlgorithm = new DiatomRedtideAlgorithm();
			//将工具类中算法改为硅藻算法
			GeoToolsUtils.setRedtideAlgorithm(redtideAlgorithm);
			//再次运行工具类
			RTResult resultD = GeoToolsUtils.getShape(picName, outputShpfileOfDiatom);
			//判断shp文件是否生成成功s
			pic.setShp(true);
			//取出赤潮点统计
			int redtidePointCount = (int) resultP.getData() + (int) resultD.getData();
			//工具的流程如果计算发现有赤潮点，会将result的data设置为赤潮统计数。
			pic.setRedtidepoint(redtidePointCount);
		} catch (Exception e1) {
			e1.printStackTrace();
			ThreadOut.println(picDate + "提取赤潮信息出错（error in geotoolsUtils）"); 
			return RTResult.build(300, "shp读取或转换出错");
		}
		
		//下载叶绿素tiff数据
		String name = pic.getName();
		String dateInfo = DayToDate.convertToDayOfYear(date);
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
				raf = new RandomAccessFile(tiffPath.getPath() + sep + "chl" + picDate +".tiff", "rw");
				httpConnection.setRequestProperty("User-Agent", "Internet Explorer");
				String sProperty = "bytes=" + nStartPos + "-";
				// 告诉服务器book.rar这个文件从nStartPos字节开始传
				httpConnection.setRequestProperty("RANGE", sProperty);
				//System.out.println(sProperty);
				input = httpConnection.getInputStream();
				byte[] b = new byte[1024];
				// 读取网络文件,写入指定的文件中
				ThreadOut.println(picDate + "叶绿素正在下载");
				while ((nRead = input.read(b, 0, 1024)) > 0 && nStartPos < nEndPos) {
					raf.write(b, 0, nRead);
					nStartPos += nRead;
				}
				
				ThreadOut.println(picDate + "叶绿素下载完成");
				httpConnection.disconnect();
			}else {
				ThreadOut.println(picDate + "叶绿素文件已存在");
			}
			pic.setChl(true);
		} catch (Exception e) {
			e.printStackTrace();
			return RTResult.build(300, "叶绿素下载错误");
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
		
		//下载云图数据
		name = pic.getName();
		OutputStream os = null;  
		try {  
			//检查文件夹是否存在
			File cloudPath=new File(CLOUD_SAVE_PATH);   
			//不过文件夹不存在，创建路径名的文件夹
			if(!cloudPath.exists()){    
				cloudPath.mkdirs();    
			}
			//创建文件
			File file = new File(cloudPath.getPath() + sep + "cloud" + picDate + ".tiff");
			//如果该路径文件不存在则开始下载，否则
			if(!file.exists()){
				ThreadOut.println(picDate + "开始下载云图");
				//Jsoup网页解析器！！！注意！！！设置最大下载大小，不然默认为1M云图tiff只能下载很小一部分.maxBodySize(20000000)这里设置为20M
				byte[] imgBytes = Jsoup.connect("http://gibs.earthdata.nasa.gov/image-download?TIME=" + picDate + "&&extent=117.017578125,25,129.322265625,33.1435546875&epsg=4326&layers=MODIS_Aqua_CorrectedReflectance_TrueColor,Coastlines&opacities=1,1&worldfile=false&format=image/tiff&width=1400&height=884").timeout(20000).maxBodySize(20000000).ignoreContentType(true).execute().bodyAsBytes();
				os = new FileOutputStream(cloudPath.getPath() + sep + "cloud" + picDate + ".tiff");  
				os.write(imgBytes, 0, imgBytes.length);  
				ThreadOut.println(picDate + "云图下载成功");
			}else {
				ThreadOut.println(picDate + "云图已存在");
			}
			pic.setCloud(true);
		} catch (Exception e) {  
			e.printStackTrace();  
			ThreadOut.println("云图保存异常：" + picDate + ".tiff");
			return RTResult.build(300, "云图下载异常");
		}finally{  
			try {  
				// 完毕，关闭所有链接
				if (os != null) {
					os.close(); 
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}  
		} 
		
		//因为iObjects 的对象不一定是线程安全的所以需要同步
		synchronized (this) {
			//用iobject发布
		    //创建工作空间对象
		    Workspace workspace = new Workspace();
		    //创建地图控制器
		    MapControl mapControl = new MapControl();
		    //声明地图对象
		    Map map = null;
		    //声明所有地图对象
		    Maps maps = null;
		    //声明所有图层
		    Layers layers = null;
		    try {
		    	// 打开工作空间
		    	//创建工作空间连接信息对象，带入参数
	    		WorkspaceConnectionInfo conInfo = new WorkspaceConnectionInfo(SUPERMAP_WORKSPACE_PATH);
	    		//设置工作空间类型
	    		conInfo.setType(WorkspaceType.SMWU);
	    		//在工作空间中打开连接对象（正式打开工作空间）
	    		workspace.open(conInfo);
	    		ThreadOut.println("工作空间打开成功");
	    		
	    		//获得数据源
				Datasource datasource = workspace.getDatasources().get(SUPERMAP_DATABASE_URL);
				ThreadOut.println("数据源获取成功");
				
				//矢量数据的导入
				//矢量数据导入设置对象信息(东海原甲藻)
				ImportSettingSHP issP= new ImportSettingSHP();
				//设置导入参数
				issP.setImportMode(ImportMode.OVERWRITE);
				issP.setTargetDatasource(datasource);
				issP.setSourceFilePath(SHP_PRE+ picDate +"P.shp");
				
				//矢量数据导入设置对象信息(硅藻)
				ImportSettingSHP issD= new ImportSettingSHP();
				//设置导入参数
				issD.setImportMode(ImportMode.OVERWRITE);
				issD.setTargetDatasource(datasource);
				issD.setSourceFilePath(SHP_PRE+ picDate +"D.shp");
				//数据导入对象
				DataImport di = new DataImport();
				//取出设置集，加入设置
				di.getImportSettings().add(issP);
				di.getImportSettings().add(issD);
				//运行导入
				ImportResult result = di.run();
				ImportSetting[] failedSettings = result.getFailedSettings();
				if(failedSettings.length == 0) {
					ThreadOut.println("矢量数据成功数据导入数据集");
				} else {
					ThreadOut.println("矢量数据导入失败");
				}
				di.dispose();
				
				
				//栅格数据的导入
				//叶绿素影像数据导入设置
				ImportSettingTIF istChla = new ImportSettingTIF();
				istChla.setImportMode(ImportMode.OVERWRITE);
				istChla.setTargetDatasource(datasource);
				//isi.setMultiBandImportMode(MultiBandImportMode.MULTIBAND);
				istChla.setPyramidBuilt(false);
				istChla.setSourceFilePath(CHL_PRE + picDate + ".tiff");
			
				//云图影像导入设置
				ImportSettingTIF istCloud = new ImportSettingTIF();
				istCloud.setImportMode(ImportMode.OVERWRITE);
				istCloud.setTargetDatasource(datasource);
				//isi.setMultiBandImportMode(MultiBandImportMode.MULTIBAND);
				istCloud.setPyramidBuilt(false);
				istCloud.setSourceFilePath(CLOUD_PRE + picDate + ".tiff");
				
				//数据导入对象
				di = new DataImport();
				//取出设置集，加入设置
				di.getImportSettings().add(istChla);
				di.getImportSettings().add(istCloud);
				//运行设置软件 
				result = di.run();
				//获取结果信息
				failedSettings = result.getFailedSettings();
				if(failedSettings.length == 0) {
					ThreadOut.println("叶绿素云图数据成功数据导入数据集");
				} else {
					ThreadOut.println("叶绿素云图导入失败");
				}
				
				//本身就是1984坐标系不用导入坐标系
				di.dispose();
				String picDate_afer = picDate.replaceAll("-", "_");
				Datasets datasets = datasource.getDatasets();
				//获取样品数据集的坐标
				Dataset dataset1 = datasets.get("templet");
				PrjCoordSys prjCoordSys1 = dataset1.getPrjCoordSys();
				PrjCoordSys prjCoordSys2 = new PrjCoordSys(prjCoordSys1);
				//把坐标赋给新加入的数据集(东海原甲藻和硅藻)
				Dataset datasetP = datasets.get("RT" + picDate_afer + "P");
				datasetP.setPrjCoordSys(prjCoordSys2);
				Dataset datasetD = datasets.get("RT" + picDate_afer + "D");
				datasetD.setPrjCoordSys(prjCoordSys2);
				ThreadOut.println("坐标系定义完成（WGS1984）");
				
				//通过地图控件得到地图对象
				map = mapControl.getMap();
				//地图对象与工作空间关联
				map.setWorkspace(workspace);
				maps = workspace.getMaps();
				
				//打开指定序号地图才能编辑,这里换为空地图
				map.open(maps.get(maps.indexOf("newbasemap")));
				//把底图转换为xml
				String base_xml = map.toXML();
				ThreadOut.println("地图转换为xml");
				//把底图作为新地图添加到地图集合
				maps.add("RT" + picDate_afer + "map", base_xml);
				//用地图打开新加的地图
				map.open(maps.get(maps.indexOf("RT" + picDate_afer + "map")));
				//通过地图获取图层
				layers = map.getLayers();
				ThreadOut.println("取得图层集");
				
				//导入云图tiff作为底图
				DatasetImage datasetCloud = (DatasetImage) datasource.getDatasets()
						.get("cloud" + picDate_afer);
				LayerSettingImage setting_image_cloud = new LayerSettingImage();
				//插入地图作为底图
				layers.add(datasetCloud,setting_image_cloud,true);
				
				//把数据集转换成矢量数据集
				datasetP = (DatasetVector) datasetP;
				//设置矢量风格
				LayerSettingVector setting_vector = new LayerSettingVector();
				setting_vector.getStyle().setLineColor(Color.RED);
				setting_vector.getStyle().setLineSymbolID(11);
				setting_vector.getStyle().setLineWidth(0.5);
				setting_vector.getStyle().setFillForeColor(new Color(255, 0, 0));
				setting_vector.getStyle().setFillBackColor(new Color(255, 0, 0));
				setting_vector.getStyle().setFillGradientMode(FillGradientMode.RADIAL);
				//图层集添加图层
				layers.add(datasetP,setting_vector,true);
				//设置新添加的图层最小显示集合图形尺寸为0
				layers.get(0).setMinVisibleGeometrySize(0);
				
				//对硅藻设置
				datasetD = (DatasetVector) datasetD;
				setting_vector.getStyle().setLineColor(new Color(240, 133, 25));
				setting_vector.getStyle().setFillForeColor(new Color(240, 133, 25));
				setting_vector.getStyle().setFillBackColor(new Color(240, 133, 25));
				//图层集添加图层
				layers.add(datasetD,setting_vector,true);
				//设置新添加的图层最小显示集合图形尺寸为0
				layers.get(0).setMinVisibleGeometrySize(0);
				
				//将编辑后的地图转换成XML
				String xml_rt = map.toXML();
				//再将XML保存到工作空间地图的指定序号位置
				workspace.getMaps().setMapXML(maps.indexOf("RT" + picDate_afer + "map"), xml_rt);
				ThreadOut.println("赤潮信息地图保存成功");
				
				maps.add("chl" + picDate_afer + "map",base_xml);
				map.open(maps.get(maps.indexOf("chl" + picDate_afer + "map")));
				layers = map.getLayers();
				
				//云图栅格插入地图作为底图
				layers.add(datasetCloud,setting_image_cloud,true);
				
				DatasetImage datasetChl = (DatasetImage) datasource.getDatasets()
						.get("chl" + picDate_afer);
				LayerSettingImage setting_image_chla = new LayerSettingImage();
				//设置背景透明色
				setting_image_chla.setTransparent(true);
				setting_image_chla.setTransparentColorTolerance(150);
				layers.add(datasetChl,setting_image_chla,true);
				//把编辑的地图保存到地图组
				String xml_chl = map.toXML();
				workspace.getMaps().setMapXML(maps.indexOf("chl" + picDate_afer + "map"), xml_chl);
				ThreadOut.println(datasetP.getName() + datasetD.getName());
				
				workspace.save();
				map.close();
				workspace.close();
				
			} catch (Exception e) {
				if (workspace != null) {
					workspace.save();
				}
				if (map != null) {
					map.close();
				}
				if (workspace != null) {
					workspace.close();
				}
				return RTResult.build(300, "插入supermap数据库出错");
			}
		}
		
	  
	  //将影像数据插入数据库（文件名，日期）
	    try {
	    	picMapper.insert(pic);
		} catch (Exception e) {
			e.printStackTrace();
			ThreadOut.println("重复插入");
			RTResult.build(300, "数据库中已存在该影像");
		}
		return RTResult.ok();
	}
	
	
	@Override
	public RTResult updatePic(Pic pic) {
		if (pic == null) {
			return RTResult.build(400, "缺少影像信息");
		}
		Pic pic2 = picMapper.selectByPrimaryKey(pic.getId());
		pic2.setChl(pic.getChl());
		pic2.setNc(pic.getNc());
		pic2.setCloud(pic.getCloud());
		picMapper.updateByPrimaryKey(pic2);
		return RTResult.ok();
	}

	@Override
	public RTResult deletePicByIds(String ids) {
		if (ids == null || ids == "") {
			return RTResult.build(400, "请选择要删除的id");
		}
		Integer id = 0;
		String[] idsArray = ids.split(",");
		for (String idStr : idsArray) {
			id = Integer.parseInt(idStr);
			picMapper.deleteByPrimaryKey(id);
		}
		return RTResult.ok();
	}

	@Override
	public EasyUIResult selectPicsByPage(Integer page, Integer rows) {
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(page, rows);
		List<Pic> list = picMapper.selectAllPicOrderByDate();
		PageInfo<Pic> pageInfo = new PageInfo<>(list);
		Integer total = (int) pageInfo.getTotal();
		EasyUIResult result = new EasyUIResult(total, list);
		return result;
	}

	@Override
	public RTResult updateArea(Double area, Date date) {
		Pic p = null;
		PicExample example = new PicExample();
		Criteria criteria = example.createCriteria();
		criteria.andDateEqualTo(date);
		List<Pic> list = picMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			p = list.get(0);
		}
		p.setArea(area);
		picMapper.updateByPrimaryKey(p);
		return RTResult.ok();
	}

	@Override
	public RTResult selectYears() {
		List<Integer> list = picMapper.selectYears();
		/*
		 * Integer[] array2 = (Integer[])list.toArray();
		 * 注意！！！！！！！！上面这种写法或报错
		 * [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
		 * 因为数组在创建的时候里面的类型就确定了，所以无法转型，而要通过下面的反射
		 * 反射可以在创建的时候通过数组要用到的对象的class对象和数组长度来创建需要的数组
		 * 详情见Java核心技术203页
		 */
		
		/*
		 * Object array = Array.newInstance(Integer[].class,list.size());
			array = (Integer[]) list.toArray();
			return RTResult.ok(array);
			上面这段也错了，因为(Integer[]) list.toArray();这段代码根本转不了只能复制过来
		*/
		Integer[] array = new Integer[list.size()];
		for (int i = 0; i < array.length; i++) {
			Integer integer = list.get(i);
			array[i] = integer;
		}
		
		return RTResult.ok(array);
	}

	@Override
	public RTResult selectAreaByMonth(Integer year, String month) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(month.length() == 1 ) {
			month = "0" + month;
		}
		QueryPoJo query = new QueryPoJo();
		query.setMonth(month);
		query.setYear(year);
		List<Pic> list = picMapper.selectAreaByMonth(query);
		String[] dateArray = new String[list.size()];
		Double[] areaArray = new Double[list.size()];;
		Date d = null;
		String dateStr = null;
		String date = null;
		double tempArea = 0;
		if(list != null && list.size() > 0) {
			for(int i=0; i<list.size(); i++) {
				tempArea = list.get(i).getArea()/1000000;
				areaArray[i] = (double)Math.round(tempArea*100)/100;
				d = list.get(i).getDate();
				dateStr = df.format(d);
				date = dateStr.substring(8, 10);
				if(date.substring(0,1).equals("0")){
					date = date.substring(1,2);
				}
				dateArray[i] = date;
			}
		}
		Object[] objArray = {dateArray, areaArray};
		return RTResult.ok(objArray);
	}

	@Override
	public Pic selectLastDate() {
		Pic pic = picMapper.selectLastDate();
		return pic;
	}

	@Override
	public RTResult savePic(Pic pic) {
		picMapper.insert(pic);
		return RTResult.ok();
	}

	@Override
	public Pic selectByDate(String date) {
		Pic pic = picMapper.selectByDate(date);
		return pic;
	}


}
