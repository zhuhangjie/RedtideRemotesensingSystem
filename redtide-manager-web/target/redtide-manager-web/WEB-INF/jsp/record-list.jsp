<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<table class="easyui-datagrid" id="recordList" title="记录列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'${pageContext.request.contextPath}/record/list',method:'post',pageSize:30,toolbar:toolbar">
    <thead>
    <!-- ?voyageIdQuery='+ voyageIdQuery +'&pointNameQuery=' + pointNameQuery -->
        <tr>
        	<!-- 除了图片不用显示其他都要显示 -->
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">记录ID</th> 
            <th data-options="field:'recordNumber',width:60">序号</th>
            <th data-options="field:'pointName',width:100">点位名称</th>
            <th data-options="field:'voyageName',width:100">所属航次</th>
            <th data-options="field:'weather',width:100">天气</th>
            <th data-options="field:'arriveTime',width:130,align:'center',formatter:formatDatebox">测量时间</th>
            <th data-options="field:'waterDemo',width:100">水样编号</th>
            <th data-options="field:'chlDemo',width:100">叶绿素浓度</th>
            <th data-options="field:'cdomDemo',width:100">黄色物质浓度</th>
            <th data-options="field:'granuleDemo',width:100">颗粒物浓度</th>
            <th data-options="field:'spectrumDemo',width:100">光谱编号</th>
            <th data-options="field:'positionX',width:100">经度</th>
            <th data-options="field:'positionY',width:100">纬度</th>
        </tr>
    </thead>
</table>
<!-- <div id="queryBar">
	航次:<input type="text" name="voyageName" id="voyageName"/>
</div> -->
<div id="recordEditWindow" class="easyui-window" title="编辑记录" data-options="modal:true,closed:true,iconCls:'icon-save',href:'record-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<div id="pictureUploadWindow" class="easyui-window" title="图片上传" data-options="modal:true,closed:true" style="width:200px;height:150px;padding:10px;margin-left:auto;margin-right:auto;">
	<form id="uploadForm" method="post" enctype="multipart/form-data" action="/record/pictureUpload">
		图片上传:<input id="waterFile" type="file" name="waterFile"><br><br>
		<input type="hidden" id="hiddenId" name="recordId">
		<a id="submitPicture" href="javascript:void(0)" class="easyui-linkbutton">提交</a>
	</form>
	<!-- <button id="bb">提交</button> -->
</div>
<!-- <div id="recordSearchWindow" class="easyui-window" title="查询记录" data-options="closed:true,iconCls:'icon-search'" style="width:200px;height:200px;padding:10px;margin-left:auto;margin-right:auto;">
	<form id="recordSearchForm" class="" method="post">
	航次:<input id="voyageIdQuery" name="voyageIdQuery" class="easyui-combobox voyageSelect"></input><br><br>
	点位名称:<input type="text" name="pointNameQuery" id="pointNameQuery"/><br><br>
	
	<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search" onclick="submitRecordQueryForm()">点击查询</a>
	</form>
</div> -->
<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addRecord()">新增</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateRecord()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="deleteRecord()">删除</a>
    <!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="addPicture()">添加图片</a> -->
    <a href="javascript:void(0)" id="searchbtn" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="queryRecord()">查询</a>
    
    <form id="searchForm">
        <table>
            <tr>
                <th>航次:</th>
                <td>
                    <input id="voyageIdQuery" name="voyageIdQuery" class="easyui-combobox voyageSelect" value="${voyageNameBackShow}"></input>
                </td>
                 <th>点位名称:</th>
                <td>
                    <input id="pointNameQuery" name="pointNameQuery" type="text" value="${pointNameBackShow}" />
                </td>
            </tr>
        </table>
    </form>
</div>
<script>
	  $(function(){
		  //当关闭选项卡时，把参数都重置
		   VoyageSelectInit();
		  //并不需要
		   /* $('#tabs').tabs({
				  onBeforeClose: function(title,index){
				    if(title == "查询记录") {
				    	pointNameQuery = "";
				    	voyageIdQuery = null;
				    }
				  }
				});  */
	  });
		 
	//关闭当前选项卡的函数
    function tabsClose(){  
        var tab=$('#tabs').tabs('getSelected');//获取当前选中tabs  
        var index = $('#tabs').tabs('getTabIndex',tab);//获取当前选中tabs的index  
        $('#tabs').tabs('close',index);//关闭对应index的tabs  
    } 
	
	var voyageIdQuery,pointNameQuery;
	
    
	//初始化航次选择
	function VoyageSelectInit(){
		$.ajax({
	        url:"${pageContext.request.contextPath}/voyage/selectIds",  
	        dataType:"json", 
	        type:"POST",
	        success:function(data){
	                    //绑定第一个下拉框
	                    $(".voyageSelect").combobox({
	                            data: data.data,
	                            valueField: 'id',
	                            textField: 'voyageName'
	                    });   
	                    
	       },
	       error:function(error){
	           alert("初始化下拉控件失败");
	       }
	    });
	}
	
    function getrecordSelectionsIds(){
    	var recordList = $("#recordList");
    	var sels = recordList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    
    //添加记录
   
    function addRecord() {
    	$(".tree-title:contains('新增记录')").parent().click();
    }
    
    //编辑记录
    function updateRecord() {
    	var ids = getrecordSelectionsIds();
    	if(ids.length == 0){
    		$.messager.alert('提示','必须选择一个记录才能编辑!');
    		return ;
    	}
    	if(ids.indexOf(',') > 0){
    		$.messager.alert('提示','只能选择一个记录!');
    		return ;
    	}
    	$("#recordEditWindow").window({
    		onLoad :function(){
    			//回显数据
    			var data = $("#recordList").datagrid("getSelections")[0];
    			//日期由于格式原因无法回显
    			//由于form的load方法直接把全部的data添加时会无法回显，所以这里要分开添加
    			$("#recordEditForm").form("load",{"id":data.id}); 
    			$("#recordEditForm").form("load",{"voyageId":data.voyageId}); 
    			$("#recordEditForm").form("load",{"pointName":data.pointName}); 
    			$("#recordEditForm").form("load",{"recordNumber":data.recordNumber}); 
    			$("#recordEditForm").form("load",{"positionX":data.positionX}); 
    			$("#recordEditForm").form("load",{"positionY":data.positionY}); 
    			$("#recordEditForm").form("load",{"weather":data.weather}); 
    			$("#recordEditForm").form("load",{"waterDemo":data.waterDemo}); 
    			$("#recordEditForm").form("load",{"chlDemo":data.chlDemo}); 
    			$("#recordEditForm").form("load",{"cdomDemo":data.cdomDemo}); 
    			$("#recordEditForm").form("load",{"granuleDemo":data.granuleDemo}); 
    			$("#recordEditForm").form("load",{"spectrumDemo":data.spectrumDemo}); 
    			
    		}
    	}).window("open");
    }
    
    //删除记录
    function deleteRecord() {
    	var ids = getrecordSelectionsIds();
    	if(ids.length == 0){
    		$.messager.alert('提示','未选中记录!');
    		return ;
    	}
    	$.messager.confirm('确认','确定删除ID为 '+ids+' 的记录吗？',function(r){
    	    if (r){
    	    	var params = {"ids":ids};
            	$.post("${pageContext.request.contextPath}/record/delete",params, function(data){
        			if(data.status == 200){
        				$.messager.alert('提示','删除记录成功!',undefined,function(){
        					//刷新记录列表
        					$("#recordList").datagrid("reload");
        					//刷新我的记录列表
        					$("#recordUserList").datagrid("reload");
        					//初始化选择记录combobox
        					recordSelectInit();
        				});
        			}
        		});
    	    }
    	});
    }
    
    //添加图片
 /*    function addPicture() {
    	var ids = getrecordSelectionsIds();
    	if(ids.length == 0){
    		$.messager.alert('提示','必须选择一个记录才能编辑!');
    		return ;
    	}
    	if(ids.indexOf(',') > 0){
    		$.messager.alert('提示','只能选择一个记录!');
    		return ;
    	}
    	$("#hiddenId").val(ids);
    	$("#waterFile").val("");
    	$("#pictureUploadWindow").window("open");
    } */
    
    //查询记录
    function queryRecord() {
    	//赋值查询条件
		//这里一定要用easyuicombobox内置的combobox方法才能取值！！！！！
		voyageIdQuery = $('#voyageIdQuery').combobox('getValue');
		pointNameQuery = $("#pointNameQuery").val();
		//点击确定后重新发送分页查询请求
		$('#recordList').datagrid('load',{          
            voyageIdQuery:voyageIdQuery,pointNameQuery:pointNameQuery          
        });
		
	//提交表格
 
	
	
		/*  tabsClose();
		$(".tree-title:contains('查询记录')").parent().click();  */
    }
/*     var toolbar =[ {
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增记录')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getrecordSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个记录才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个记录!');
        		return ;
        	}
        	
        	$("#recordEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#recordList").datagrid("getSelections")[0];
        			//日期由于格式原因无法回显
        			//由于form的load方法直接把全部的data添加时会无法回显，所以这里要分开添加
        			$("#recordEditForm").form("load",{"id":data.id}); 
        			$("#recordEditForm").form("load",{"recordName":data.recordName}); 
        			$("#recordEditForm").form("load",{"leader":data.leader}); 
        			$("#recordEditForm").form("load",{"position":data.position}); 
        			$("#recordEditForm").form("load",{"shipCode":data.shipCode}); 
        			$("#recordEditForm").form("load",{"mission":data.mission}); 
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getrecordSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中记录!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的记录吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/record/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除记录成功!',undefined,function(){
            					//刷新记录列表
            					$("#recordList").datagrid("reload");
            					//刷新我的记录列表
            					$("#recordUserList").datagrid("reload");
            					//初始化选择记录combobox
            					recordSelectInit();
            				});
            			}
            		});
        	    }
        	});
        }
    },
    {
        text:'关联',
        iconCls:'icon-ok',
        handler:function(){
        	var ids = getrecordSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中记录!');
        		return ;
        	}
        	$.messager.confirm('确认','确定本用户关联ID为 '+ids+' 的记录吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/record/relate/${user.id}",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','关联记录成功!',undefined,function(){
            					//打开我的记录
            					$(".tree-title:contains('我的记录')").parent().click();
            					//刷新我的记录列表
            					$("#recordUserList").datagrid("reload");
            				});
            			}else if (data.status == 401){
            				$.messager.alert('提示','关联记录重复，关联失败!');
            			} else{
            				$.messager.alert('提示','关联错误!');
            			}
            		});
        	    }
        	});
        }
    },
    {
        text:'搜索',
        iconCls:'icon-search',
        handler:function(){
        	$("#recordSearchWindow").window("open");
        	//初始化航次选择combobox
        	VoyageSelectInit();
        }
    } 
    ];
     */
     var toolbar = $("#toolbar");
    //日期转换
    function formatDatebox(value) {  
        if (value == null || value == '') {  
            return '';  
        }  
        var dt;  
        if (value instanceof Date) {  
            dt = value;  
        } else {  
            dt = new Date(value);  
        }  
      
        return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)  
    } 
</script>