<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" >
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<link rel="stylesheet" href="css/dialog.css">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="js/sweetalert.min.js"></script>
<script src="js/highcharts/highcharts.js"></script>
<script src="js/highcharts/modules/exporting.js"></script>
<script src="js/dialog.js"></script>
<style>
        .point{position:absolute;left:50%; top:50%;}
        .pop{width:600px; height:400px; position:absolute;left:-250px; top:-250px; border:1px solid black; }
</style>
<script type="text/javascript">
	$(function() {
		var d = dialog({
		    button: [
		        {
		            value: '一月',
		            callback: function () {
		            	alert('你同意')
		            }
		        },
		        {
		            value: '二月',
		            callback: function () {
		                alert('你不同意')
		            }
		        },
		        {
		            value: '三月',
		            callback: function () {
		            	alert('你同意')
		            }
		        },
		        {
		            value: '四月',
		            callback: function () {
		                alert('你不同意')
		            }
		        },
		        {
		            value: '五月',
		            callback: function () {
		            	alert('你同意')
		            }
		        },
		        {
		            value: '六月',
		            callback: function () {
		                alert('你不同意')
		            }
		        },
		        {
		            value: '七月',
		            callback: function () {
		            	alert('你同意')
		            }
		        },
		        {
		            value: '八月',
		            callback: function () {
		                alert('你不同意')
		            }
		        },
		        {
		            value: '九月',
		            callback: function () {
		            	alert('你同意')
		            }
		        },
		        {
		            value: '十月',
		            callback: function () {
		                alert('你不同意')
		            }
		        },
		        {
		            value: '十一月',
		            callback: function () {
		            	alert('你同意')
		            }
		        },
		        {
		            value: '十二月',
		            callback: function () {
		                alert('你不同意')
		            }
		        }
		        
		    ]
		});
		d.show();
		
		//页面加载完后，动态加载图表
		var chart = Highcharts.chart('monthDate', {
		    chart: {
		        type: 'line'
		    },
		    title: {
		        text: '月'
		    },
		    xAxis: {
		        categories:['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12','13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28','29', '30'] 
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
		        data: [7.0, 6.9, 9.5, 1000000, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6,7.0, 6.9, 9.5, 14.5, 18.4, 0, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6,25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
		    }]
		});
	})
	function showPop() {
		/* $("#pop1").css("display","none"); */
		$("#yuntu").trigger("click");
	}
</script>


</head>
<body>
	 <li><a id="yuntu" data-toggle="modal" data-target="#aa" )>遥感云图</a></li>
	<div id="aa" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	    <div class="modal-dialog modal-sm">
	    <div class="modal-content" style="right:250px;top:50px">
	    	 <div id="monthDate"></div> 
	    </div>
		</div>
	</div>
	
	<input type="text" data-toggle="modal" data-target="#aa">
	
	<li><a data-toggle="modal" data-target=".bs-example-modal-sm" onclick="checkVal()")>遥感云图</a></li>
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	    <div class="modal-dialog modal-sm">
	    <div class="modal-content" style="right:250px;top:50px">
	    	 哈哈哈
	    </div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	    <div class="modal-dialog modal-sm">
	    <div class="modal-content" style="right:250px;top:50px">
	    	 <div id="monthDate"></div> 
	    </div>
		</div>
	</div>
	<input type="button" value="我是按钮" onclick="showPop()">
	<!-- <div id="pop1" class="point">
            <div class="pop">
           &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp x
				<div id="monthDate"></div>
			</div>
    </div> -->
	
</body>
</html>