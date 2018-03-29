<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="voyageList" title="航次列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'${pageContext.request.contextPath}/voyage/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">航次ID</th> 
            <th data-options="field:'voyageName',width:200">航次名称</th>
            <th data-options="field:'leader',width:100">航次负责人</th>
            <th data-options="field:'shipCode',width:100">船号</th>
            <th data-options="field:'position',width:100">航次位置</th>
            <th data-options="field:'mission',width:450">航次任务</th>
            <th data-options="field:'startTime',width:130,align:'center',formatter:formatDatebox">开始日期</th>
            <th data-options="field:'endTime',width:130,align:'center',formatter:formatDatebox">结束日期</th>
            
        </tr>
    </thead>
</table>

<div id="voyageEditWindow" class="easyui-window" title="编辑航次" data-options="modal:true,closed:true,iconCls:'icon-save',href:'voyage-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>
	 /* $(function(){
		$("#voyageList").datagrid('hideColumn', "id");
	});  */
    function getVoyageSelectionsIds(){
    	var voyageList = $("#voyageList");
    	var sels = voyageList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增航次')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getVoyageSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个航次才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个航次!');
        		return ;
        	}
        	
        	$("#voyageEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#voyageList").datagrid("getSelections")[0];
        			/* str = JSON.stringify(data); */
        			
        			//日期由于格式原因无法回显
        			/* $("#voyageEditForm").form("load",{"startTime":data.startTime}); 
        			$("#voyageEditForm").form("load",{"endTime":data.endTime});  */
        			
        			//由于form的load方法直接把全部的data添加时会无法回显，所以这里要分开添加
        			$("#voyageEditForm").form("load",{"id":data.id}); 
        			$("#voyageEditForm").form("load",{"voyageName":data.voyageName}); 
        			$("#voyageEditForm").form("load",{"leader":data.leader}); 
        			$("#voyageEditForm").form("load",{"position":data.position}); 
        			$("#voyageEditForm").form("load",{"shipCode":data.shipCode}); 
        			$("#voyageEditForm").form("load",{"mission":data.mission}); 
        			/* E3.init({
        				fun:function(node){
        					E3.changeItemParam(node, "voyageEditForm");
        				}
        			});  */
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getVoyageSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中航次!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的航次吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("${pageContext.request.contextPath}/voyage/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除航次成功!',undefined,function(){
            					//刷新航次列表
            					$("#voyageList").datagrid("reload");
            					//刷新我的航次列表
            					$("#voyageUserList").datagrid("reload");
            					//初始化选择航次combobox
            					VoyageSelectInit();
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
        	var ids = getVoyageSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中航次!');
        		return ;
        	}
        	$.messager.confirm('确认','确定本用户关联ID为 '+ids+' 的航次吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("${pageContext.request.contextPath}/voyage/relate/${user.id}",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','关联航次成功!',undefined,function(){
            					//打开我的航次
            					$(".tree-title:contains('我的航次')").parent().click();
            					//刷新我的航次列表
            					$("#voyageUserList").datagrid("reload");
            				});
            			}else if (data.status == 401){
            				$.messager.alert('提示','关联航次重复，关联失败!');
            			} else{
            				$.messager.alert('提示','关联错误!');
            			}
            		});
        	    }
        	});
        }
    }
    ];
    
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