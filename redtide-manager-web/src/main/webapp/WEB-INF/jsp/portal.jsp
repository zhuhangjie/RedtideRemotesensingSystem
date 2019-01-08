<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>赤潮系统首页</title>

<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" >
     <link href="${pageContext.request.contextPath}/css/sweetalert.css" rel="stylesheet" type="text/css" > 
    <link href="${pageContext.request.contextPath}/css/dialog.css" rel="stylesheet" >
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	 <script src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script> 
	<script src="${pageContext.request.contextPath}/js/dialog.js"></script>
	<script src="${pageContext.request.contextPath}/js/highcharts/highcharts.js"></script>
	<script src="${pageContext.request.contextPath}/js/highcharts/modules/exporting.js"></script>
	<style type="text/css">
	body{
            margin: 0;
            overflow: hidden;
            background: #fff;
        }
	 #map{
	 		top:-21px;
            position: relative;
            height: 616px;
            border:0px solid #3473b7; 
        }
      #colorbar{
         weight:140px;
         height: 280px;
     } 
     #algbar{
         weight:80px;
         height: 80px;
     } 
     #modelButton{
			/* top:-21px; */
			top:8px;
			/* right:3px; */
        	position: relative;
     }
	</style>
	<script src="${pageContext.request.contextPath}/libs/SuperMap.Include.js"></script> 
	<script type="text/javascript">
	//iClient需要用到的变量
	var map, local, layer, drawLine, lineLayer, drawPolygon, polygonLayer, url, baseUrl;
	var url1, url2, layer1, layer2, pickedDate;
	//防止统计页面的月份选择栏被被重新打开
	var isMonthMenuOpened = false;
	//
	var rt_area = 0;
	var rt_distance = 0;
	//设置一个只清除回显面积的标签
	var areaFlag = 0;
	//界面交互需要用到的变量
	var chlrtConversion = "0";
	var currentDate, currentDate_changed, date_nomal;
	style = {
            strokeColor: "#304DBE",
            strokeWidth: 2,
            pointerEvents: "visiblePainted",
            fillColor: "#304DBE",
            fillOpacity: 0.8
        },
        /* 一定要改回来 */
    baseUrl = "http://localhost:8090/iserver/services/map-databaseworkspace/rest/maps/"
    //baseUrl = "http://localhost:8090/iserver/services/map-RedtideWorkspace/rest/maps/"
	url1 = "";
	url2 = "";
	function init() {
		$().alert("1");
		//取得回显的面积值
		var tempResult = $("#result").val();
		/* 页面端把传过来的科学计数法的double格式转换成标准的String格式
		tempResult = parseFloat(tempResult).toString(); */
		//把显示的距离值更改为回显的值,先四舍五入
		var tempResult_km_round = (tempResult/1000000).toFixed(2);
		if(tempResult==0) {
			tempResult_km_round = $("#resultP").val();
		}
		$("#area_result").html("测量结果:" + tempResult_km_round + "平方千米"); 
		var date_Info = $("#dateInfo").val(); 
		date_nomal = date_Info.split('_').join('-');
		
		//设置云图路径，图片服务器
	    var cloudSrc = "http://localhost:8080/picture/cloud" + date_nomal + ".jpg"; 
	    
	    $("#cloudImage").attr("src",cloudSrc);
		/* $("#cloudImage").attr("src","" + ""); */
		url1 = baseUrl + "chl" + date_Info +"map";
		
		url2 = baseUrl + "RT" + date_Info +"map";
		
		//借用一下马上换回来,用来展示的图一般情况下注销就行了
		//url2 = "http://localhost:8090/iserver/services/map-China/rest/maps/China";
		//新建线矢量图层
        lineLayer = new SuperMap.Layer.Vector("lineLayer");
        //对线图层应用样式style（前面有定义）
        lineLayer.style = style;
        //创建画线控制，图层是lineLayer;这里DrawFeature(图层,类型,属性)；multi:true在将要素放入图层之前是否先将其放入几何图层中
        drawLine = new SuperMap.Control.DrawFeature(lineLayer, SuperMap.Handler.Path, { multi: true });
        /*
        	注册featureadded事件,触发drawCompleted()方法
        	例如注册"loadstart"事件的单独监听
        	events.on({ "loadstart": loadStartListener });
        */
        drawLine.events.on({"featureadded": drawLineCompleted});
        
      	//新建面矢量图层
        polygonLayer = new SuperMap.Layer.Vector("polygonLayer");
        //对面图层应用样式style（前面有定义）
        polygonLayer.style = style;
        /*
		        注册featureadded事件,触发drawCompleted()方法
		        例如注册"loadstart"事件的单独监听
        events.on({ "loadstart": loadStartListener });
        */
       //创建画面控制，图层是polygonLayer
       drawPolygon = new SuperMap.Control.DrawFeature(polygonLayer, SuperMap.Handler.Polygon);
       drawPolygon.events.on({"featureadded": drawPolygonCompleted});
        
       map = new SuperMap.Map("map",{controls: [
            new SuperMap.Control.LayerSwitcher(),
            new SuperMap.Control.ScaleLine(),
            new SuperMap.Control.Zoom(),
            //new SuperMap.Control.OverviewMap(),
            new SuperMap.Control.MousePosition(),
            new SuperMap.Control.Navigation({
                dragPanOptions: {
                    enableKinetic: true
                }}),
            drawLine,
            drawPolygon]
        });
      //定义layer图层，TiledDynamicRESTLayer：分块动态 REST 图层
        layer1 = new SuperMap.Layer.TiledDynamicRESTLayer("ChlMap", url1, { transparent: true, cacheEnabled: true }, { maxResolution: "auto" });
      	//为图层初始化完毕添加addLayer()事件
        layer1.events.on({"layerInitialized":addLayer1});
      	//这个只是为了显示色素图表所以才在初始化完毕后进入赤潮模式
        toRTModel();
	}
	
	function addLayer1() {
        /* map.addLayers([layer, lineLayer, polygonLayer]);
		map.setCenter(new SuperMap.LonLat(122.31, 30.16), 0); */
		layer2 = new SuperMap.Layer.TiledDynamicRESTLayer("RtMap", url2, {transparent: true, cacheEnabled: true});
        layer2.events.on({"layerInitialized":addLayer2});
    }
	
	function addLayer2() {
		layer1.isBaseLayer=true;
		layer2.isBaseLayer=true;
		//这里可以选择初始显示的是赤潮还是叶绿素layer2在前面代表默认先显示赤潮
	    map.addLayers([layer2, layer1, lineLayer, polygonLayer]);
		map.setCenter(new SuperMap.LonLat(122.31, 30.16), 3);
    }
	
	//开始测量距离
	function distanceMeasure(){
		//测量距离开始时候如果正在测量面积的状态，停止面积测量画面控制状态。
		drawPolygon.deactivate();
		//测量距离前如果前面测量过面积，先把面积图层全部清空
		polygonLayer.removeAllFeatures();
		//开启测量距离模式
        drawLine.activate();
    }
	
	//开始测量面积
	function areaMeasure(){
		//这里由于是可以连续测量面积或者连续测量长度的模式所以不能用清空方法把整个矢量层以及参数偶读清空
        /* clearFeatures(); */
        //测量距离开始时候如果正在测量长度的状态，停止长度测量画面控制状态
        drawLine.deactivate();
        //测量距离前如果前面测量过距离，先把距离图层全部清空
        lineLayer.removeAllFeatures();
        //开始测量面积模式
        drawPolygon.activate();
    }
	
	//绘完触发事件
    function drawLineCompleted(drawGeometryArgs) {
        //停止画面控制
        drawLine.deactivate();
        //获得图层几何对象
        var geometry = drawGeometryArgs.feature.geometry,
                measureParam = new SuperMap.REST.MeasureParameters(geometry), /* MeasureParameters：量算参数类。 客户端要量算的地物间的距离或某个区域的面积*/
                myMeasuerService = new SuperMap.REST.MeasureService(url1); //量算服务类，该类负责将量算参数传递到服务端，并获取服务端返回的量算结果
        myMeasuerService.events.on({ "processCompleted": measureLineCompleted });

        //对MeasureService类型进行判断和赋值，当判断出是LineString时设置MeasureMode.DISTANCE，否则是MeasureMode.AREA

        myMeasuerService.measureMode = SuperMap.REST.MeasureMode.DISTANCE;

        myMeasuerService.processAsync(measureParam); //processAsync负责将客户端的量算参数传递到服务端。
    }
	
  //面积测量结束调用事件
    function drawPolygonCompleted(drawGeometryArgs) {
        //停止画面控制
        drawPolygon.deactivate();
        //获得图层几何对象
        var geometry = drawGeometryArgs.feature.geometry,
                measureParam = new SuperMap.REST.MeasureParameters(geometry), /* MeasureParameters：量算参数类。 客户端要量算的地物间的距离或某个区域的面积*/
                myMeasuerService = new SuperMap.REST.MeasureService(url1); //量算服务类，该类负责将量算参数传递到服务端，并获取服务端返回的量算结果
        myMeasuerService.events.on({ "processCompleted": measurePolygonCompleted });

        //对MeasureService类型进行判断和赋值，当判断出是LineString时设置MeasureMode.DISTANCE，否则是MeasureMode.AREA

        myMeasuerService.measureMode = SuperMap.REST.MeasureMode.AREA;

        myMeasuerService.processAsync(measureParam); //processAsync负责将客户端的量算参数传递到服务端。
        //只有在面积测量完成后，才开可以使用保存按钮保存面积
        $("#saveButton").removeAttr("disabled");
    }

  	//距离测量结束调用事件
    function measureLineCompleted(measureEventArgs) {
    	if(areaFlag == 0){
   		  $("#area_result").html("测量结果:");
   		  areaFlag += 1;
   	  	}
        var distance = measureEventArgs.result.distance;
        var  unit = measureEventArgs.result.unit;
        //alert("量算结果:"+distance + "米");
        var distance_number = Number(distance);
        rt_distance += distance_number;
        //在显示前转换成千米，并省略到3位小数
        var rt_distance_km_round = (rt_distance/1000).toFixed(2);
        $("#area_result").html("测量结果:" + rt_distance_km_round + "千米"); 
    }
	
  //测量结束调用事件
    function measurePolygonCompleted(measureEventArgs) {
		if(areaFlag == 0){
			$("#area_result").html("测量结果:");
			areaFlag += 1;
		}
        var area = measureEventArgs.result.area,
        unit = measureEventArgs.result.unit;
        //alert("量算结果:"+ area + "平方米");
        var area_number = Number(area); 
        rt_area += area_number;
      //在显示前转换成千米，并省略到3位小数
        var rt_area_km_round = (rt_area/1000000).toFixed(2);
        $("#area_result").html("测量结果:" + rt_area_km_round + "平方千米"); 
    }
  
  	//移除图层要素
    function clearFeatures(){
  		//测量结果归0
    	$("#area_result").html("测量结果:");
  		//清楚要素后把保存面积按钮设置成不可用
  		$("#saveButton").attr("disabled",'disabled');
  		//变量归0
  		rt_area = 0;
  		rt_distance = 0;
  		//线矢量面清楚所有元素
        lineLayer.removeAllFeatures();
  		//面矢量面清除所有元素
        polygonLayer.removeAllFeatures();
  		//取消线矢量的编辑状态
        drawLine.deactivate();
  		//曲线面矢量的编辑状态
        drawPolygon.deactivate();
    }
  	
  	//按钮转换为叶绿素模式
  	function toChlModel() {
  		//切换模式时切换按钮样式
  		$("#btnchl").attr("class","btn btn-primary");
  		$("#btnrt").attr("class","btn btn-default");
  		//切换到叶绿素模式时，显示图标
  		$("#colorbar").attr("style",'display:block');
  		//切换到叶绿素模式时藻类色标隐藏
  		$("#algbar").attr("style",'display:none');
  		//切换图层样式
  		layer1.setVisibility(true);
        layer2.setVisibility(false);
        //设置显示图层
		map.setBaseLayer(layer1);
  	}
  	
  	//按钮转换为赤潮模式
  	function toRTModel() {
  		//切换模式时切换按钮样式
  		$("#btnrt").attr("class","btn btn-primary");
  		$("#btnchl").attr("class","btn btn-default");
  		//切换到叶绿素模式时，显示图标
  		$("#algbar").attr("style",'display:block');
  		//切换到赤潮模式时，隐藏图标
  		$("#colorbar").attr("style",'display:none');
  		//切换图层样式
  		layer1.setVisibility(false);
        layer2.setVisibility(true);
      	//设置显示图层
		map.setBaseLayer(layer2);
  	}
  	
  	//保存赤潮面积到数据库
  	 function saveArea() {
  		$.post("${pageContext.request.contextPath}/pic/saveArea", {"area":rt_area,"date":date_nomal},
  			  function(data){
  					if(data.status == 200) {
  						sweetAlert("保存成功");
  					} else {
  						sweetAlert("保存失败", "保存过程中遇到了一些问题!", "error");
  					}
  					//保存操作结束后，将保存按钮设置为不可点击
  					$("#saveButton").attr("disabled",'disabled');
  			  });
  	} 
  	
 	$(function(){
 		//发送get请求显示数据统计年份选项
 		$.get("${pageContext.request.contextPath}/pic/selectYears",
 				  function(data){
 					var yearInfo = ""
 						if(data != null) {
 							for(var i=0; i<data.data.length; i++) {
 								yearInfo += '<li onclick="statisticByMonth(' + data.data[i] + ')"><a href="javascript:void(0)">' + data.data[i] + '</a></li>'
 							}
 	 				  		$("#yearMenu").html(yearInfo);
 						}
 				  });
 		
 	});
 	
 	function selectCartogram(year,month){
 		isMonthMenuOpened = false;
 		$.get(  
 			"${pageContext.request.contextPath}/pic/selectAreaByMonth",
 			{ "year":year, "month":month },
 			function(data){
 				//页面加载完后，动态加载图表
 				var chart = Highcharts.chart('monthDate', {
 				    chart: {
 				        type: 'line'
 				    },
 				    title: {
 				        text: month + '月'
 				    },
 				    xAxis: {
 				        categories:data.data[0]
 				        	/* ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12','13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28','29', '30']  */
 				    },
 				    yAxis: {
 				        title: {
 				            text: '面积(平方公里)'
 				        }
 				    },
 				    plotOptions: {
 				        line: {
 				            dataLabels: {
 				                enabled: true          // 开启数据标签
 				            },
 				            enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
 				        }
 				    },
 				    series: [{
 				        name: '赤潮面积',
 				        data: data.data[1]
 				        	/* [7.0, 6.9, 9.5, 1000000, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6,7.0, 6.9, 9.5, 14.5, 18.4, 0, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6,25.2, 26.5, 23.3, 18.3, 13.9, 9.6] */
 				    }]
 				});
 			 }
 		);
 		
		
 		
 		 $("#cartogramButton").trigger("click"); 
 	}
 	
 	//统计信息选择月份
 	function statisticByMonth(year) {
 		if(isMonthMenuOpened != true){
 			isMonthMenuOpened = true;
 			var d = dialog({
 	 		    content: '请选择要查看的日期',
 			    button: [
 			        {
 			            value: '一月',
 			            callback: function () {
 			            	selectCartogram(year,"1");
 			            }
 			        },
 			        {
 			            value: '二月',
 			            callback: function () {
 			            	selectCartogram(year,"2");
 			            }
 			        },
 			        {
 			            value: '三月',
 			            callback: function () {
 			            	selectCartogram(year,"3");
 			            }
 			        },
 			        {
 			            value: '四月',
 			            callback: function () {
 			            	selectCartogram(year,"4");
 			            }
 			        },
 			        {
 			            value: '五月',
 			            callback: function () {
 			            	selectCartogram(year,"5");
 			            }
 			        },
 			        {
 			            value: '六月',
 			            callback: function () {
 			            	selectCartogram(year,"6");
 			            }
 			        },
 			        {
 			            value: '七月',
 			            callback: function () {
 			            	selectCartogram(year,"7");
 			            }
 			        },
 			        {
 			            value: '八月',
 			            callback: function () {
 			            	selectCartogram(year,"8");
 			            }
 			        },
 			        {
 			            value: '九月',
 			            callback: function () {
 			            	selectCartogram(year,"9");
 			            }
 			        },
 			        {
 			            value: '十月',
 			            callback: function () {
 			            	selectCartogram(year,"10");
 			            }
 			        },
 			        {
 			            value: '十一月',
 			            callback: function () {
 			            	selectCartogram(year,"11");
 			            }
 			        },
 			        {
 			            value: '十二月',
 			            callback: function () {
 			            	selectCartogram(year,"12");
 			            }
 			            
 			        },
 			        {	
 		                value: '取消',
 		               callback: function () {
 		            	  isMonthMenuOpened = false;
			            }
 		            }
 			    ]
 			});
 			d.show();
 		}
 		
 	}
 	
 	//点击主页云图时实际点击的是一个隐藏的input:cloudButton
 	/* function clickCloudButton(){
 		$("#cloudButton").trigger("click");
 	} */
 	
 	//重新设计onchange方法来根据日期选择图片
 	function changeDate() {
 		//清楚矢量要素
  		clearFeatures();
  		//取得选择日期
  		pickedDate = $("#dateInput").val();
  		window.location.href = "${pageContext.request.contextPath}/pic/changeDate?date=" + pickedDate;
 	}
	</script>
</head>


<body onload="init()">
<!-- 警告 -->
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	  <span aria-hidden="true">&times;</span>
	</button>
	<!-- cartogramForm -->
	<!-- 设置一个隐形的按钮，给按钮绑定data-target，从而使得按钮的点击事件出发后可以弹出统计图弹窗-->
	<input id="cartogramButton" type="hidden" data-toggle="modal" data-target="#cartogramForm">
	<!-- 隐藏按钮来接收回显日期 -->
	<input id="dateInfo" type="hidden" value="${newDate}">
	<!-- 设置隐形按钮来回显手动测量的赤潮面积 -->
	<input id="result" type="hidden" value="${areaResult}">
	<!-- 设置隐形按钮来回显自动测量的赤潮面积 -->
	<input id="resultP" type="hidden" value="${areaOfPoint}">
	<!-- 设置云图隐形按钮，主要是一个中介按钮，格式原因传递点击事件，把点击云图当做点击这个按钮 -->
	<!-- <input id="cloudButton" type="hidden" data-toggle="modal" data-target="#cloudPicture"> -->

	
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <!-- 标题栏 -->
	    <div class="navbar-header">
		    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" >浙江近海赤潮卫星遥感监测系统</a> 
	    </div>
	    
	    <!-- 日期选择控件 -->
		<form class="navbar-form navbar-left">
	        <div class="form-group">
                <div class="input-group date form_datetime" data-link-field="dtp_input1">
                	<div class="input-group-addon">日期:</div>
                    <input id="dateInput" class="form-control" size="10" type="text" value="${oldDate}" readonly onchange="changeDate()">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                   	<!--  <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> -->
                </div>
				<!-- <input type="hidden" id="dtp_input1" value="" /><br/> -->
	          	<%-- <input id="dateInput" size="8" type="text" value="${oldDate}" class="form_date form-control" readonly onchange="changeDate()"> --%>
	        </div>
      	</form>
      	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	    
	      <ul class="nav navbar-nav">
		    <!-- 叶绿素和赤潮切换框 -->
		    <li>
			    <div id="modelButton" class="btn-group" role="group" aria-label="...">
			    	<button id="btnrt" type="button" class="btn btn-primary" style="width: 68px" onclick="toRTModel()">赤    潮</button>
					<button id="btnchl" type="button" class="btn btn-default" onclick="toChlModel()">叶绿素</button>
				</div>
			</li>
			
	        <!-- <li class="active"><a href="#">历史记录<span class="sr-only">(current)</span></a></li> -->
	        <!-- <li><a href="#" >历史记录<span class="sr-only">(current)</span></a></li> -->
	        <!-- <li><a data-toggle="modal" data-target="#cloudPicture" onclick="checkVal()")>遥感云图</a></li> -->
	        <!-- 遥感云图按钮 -->
	        <!-- <li><a href="#" onclick="clickCloudButton()" >遥感云图</a></li> -->
	        
	        <!-- 数据统计下拉框 -->
			<li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" onclick="selectYears()">数据统计<span class="caret"></span></a>
	          <ul id="yearMenu" class="dropdown-menu">
	            <!-- <li onclick="statisticByMonth()"><a href="javascript:void(0)"></a></li> -->
	          </ul>
	        </li> 
	        
	        <!-- 测量工具下拉框 -->
	        <!-- 
	       		<span class="caret"> 这是下拉框后面的小三角形
	        	sr-only是隐藏属性的意思
	        -->
	         <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">测量工具 <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li onclick="distanceMeasure()"><a href="javascript:void(0)" >距离量算</a></li>
	            <li onclick="areaMeasure()" ><a href="javascript:void(0)">面积量算</a></li>
	            <li role="separator" class="divider"></li>
	            <li onclick="clearFeatures()" ><a href="javascript:void(0)">清除</a></li>
	            <!-- <li role="separator" class="divider"></li>
	            <li><a href="#">One more separated link</a></li> -->
	          </ul>
	        </li>
	         
	        <!-- 测量结果显示行 -->
	        <li><a id="area_result" value="" href="#" disable="">测量结果:</a></li> 
	        <li style="top:8px;right: 6px">
	        <!-- 保存按钮只能保存面积 -->
		    	<input id="saveButton" class="btn btn-default" type="submit" value="保存" disabled="disabled" onclick="saveArea()">
		    </li>
		  </ul>
		  <!-- 登录后台功能 -->
	      <%-- </ul>
	      <ul class="nav navbar-nav navbar-right">
		       <li>
		        <c:if test="${requestScope.user != null}">
		        	<a href="${pageContext.request.contextPath}/index">${requestScope.user.username}</a>
		        	<p class="navbar-text navbar-right"><a href="${pageContext.request.contextPath}/index" class="navbar-link">${requestScope.user.username}</a>&nbsp;&nbsp;&nbsp;&nbsp;</p>
		        </c:if>
		        <c:if test="${requestScope.user == null}">
		        	<a href="${pageContext.request.contextPath}/login">请登录</a>
		        	<p class="navbar-text navbar-right"><a href="${pageContext.request.contextPath}/login" class="navbar-link">请登录</a></p>
		        </c:if>
		        </li>
      	  </ul> --%>
      	   <ul class="nav navbar-nav navbar-right">
		       <li>
		        	<a href="${pageContext.request.contextPath}/index">管理</a>
		        	<%-- <p class="navbar-text navbar-right"><a href="${pageContext.request.contextPath}/index" class="navbar-link">管理</a>&nbsp;&nbsp;&nbsp;&nbsp;</p> --%>
		        </li>
      	  </ul> 
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
	
	<div id="map"></div>
	
	<!-- 叶绿素色标 -->
	<!-- 直接在页面最底下位置放置一个image，通过css位置调整让其位置移到地图上 -->
	<%-- <div style="position:fixed;bottom:20px;left:100px">
		<img id="colorbar" alt="色标路径未找到" src="${pageContext.request.contextPath}/images/colorbar.png" style="display:none;position:relative;bottom:300px;">
	</div> --%>
	<div style="position:fixed;bottom:50px;right:25px;">
		<img id="colorbar" alt="色标路径未找到" src="${pageContext.request.contextPath}/images/colorbar1.png" style="display:none;position:relative;bottom:300px;">
	</div>
	<div style="position:fixed;bottom:50px;right:25px">
		<img id="algbar" alt="色标路径未找到" src="${pageContext.request.contextPath}/images/algbar1.jpg" style="width:10;display:block;position:relative;bottom:300px">
	</div>
	<!-- 云图功能取消，云图弹出框 -->
	<!-- <div id="cloudPicture" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	    <div class="modal-dialog modal-sm">
	    <div class="modal-content" style="right:250px;top:50px">
	      <img id="cloudImage" src="" alt="该日云图信息未下载" style="height:500px;weight:500px">
	    </div>
		</div>
	</div> -->
	
	<!-- 图表弹出层 -->
	<div id="cartogramForm" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	    <div class="modal-dialog modal-sm">
	    <div class="modal-content" style="right:250px;top:50px">
	      	<div id="monthDate"></div> 
	    </div>
		</div>
	</div>
	
	<script type="text/javascript">
		$('.form_datetime').datetimepicker({
			format:"yyyy/mm/dd",
	        language:  'zh-CN', 
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
	</script>
</body>
</html>