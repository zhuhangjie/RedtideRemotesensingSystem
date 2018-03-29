<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>赤潮监测系统后台管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/e3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>

</head>
<body class="easyui-layout">
    <!-- 头部标题 -->
	<div data-options="region:'north',border:false" style="height:60px; padding:5px; background:#F3F3F3"> 
		<span class="northTitle">赤潮监测系统后台</span>
	    <span class="loginInfo">登录用户：${user.username}&nbsp;&nbsp;姓名：${user.name}&nbsp;&nbsp;角色：系统管理员&nbsp;&nbsp;<button id="userQuery">查看用户信息</button><button id="userUpdate">修改用户信息</button></span>
	</div>
	
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>影像管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'image-add'}">新增影像</li>
	         		<li data-options="attributes:{'url':'image-list'}">查询影像</li>
	         	</ul>
         	</li>
          	<li>
         		<span>航次管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'voyage-add'}">新增航次</li>
	         		<li data-options="attributes:{'url':'voyage-list'}">查询航次</li>
	         		<li data-options="attributes:{'url':'voyage-myVoyage'}">我的航次</li>
	         	</ul>
         	</li>
         	<li>
         		<span>记录管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'record-add'}">新增记录</li>
	         		<li data-options="attributes:{'url':'record-list'}">查询记录</li>
	         	</ul>
         	</li>  
         </ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		        	<h1><a href="${pageContext.request.contextPath}/pic/portal" style="font-size: 20px">返回赤潮显示首页</a></h1>
		    </div>
		</div>
    </div>
    <!-- 页脚信息 -->
	<div data-options="region:'south',border:false" style="height:20px; background:#F3F3F3; padding:2px; vertical-align:middle;">
		<span id="sysVersion">系统版本：V1.0</span>
	    <span id="nowTime"></span>
	</div>
	<div id="userEditWindow" class="easyui-window" title="修改个人信息" data-options="modal:true,closed:true,iconCls:'icon-save',href:'${pageContext.request.contextPath}/user-edit'" style="width:80%;height:80%;padding:10px;">
	</div>
<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
	
	$("#userUpdate").click(function(){
		$("#userEditWindow").window({
    		onLoad :function(){
    			/* //回显数据
    			var data = $("#imageList").datagrid("getSelections")[0];
    			data.priceView = E3.formatPrice(data.price);
    			$("#imageEditForm").form("load",data); */
    		}
    	}).window("open");
	});
});
setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
</script>
</body>
</html>