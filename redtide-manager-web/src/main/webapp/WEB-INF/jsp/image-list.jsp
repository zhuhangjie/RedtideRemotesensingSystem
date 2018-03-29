<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="imageList" title="影像列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'${pageContext.request.contextPath}/pic/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:100">影像ID</th>
            <th data-options="field:'name',width:200">影像文件名</th>
            <th data-options="field:'date',width:200,align:'center',formatter:formatDatebox">影像日期</th>
            <th data-options="field:'chl',width:150, formatter:function(value){if(value==1){return '有';}else{return '无';}}">是否有叶绿素信息</th>
            <th data-options="field:'rt',width:150,formatter:function(value){if(value==1){return '有';}else{return '无';}}">是否有赤潮信息</th>
            <th data-options="field:'cloud',width:150,formatter:function(value){if(value==1){return '有';}else{return '无';}}">是否有云图信息</th>
            <th data-options="field:'area',width:200">赤潮面积</th>
            
        </tr>
    </thead>
</table>
<div id="imageEditWindow" class="easyui-window" title="编辑影像信息" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/image-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>
	//获得所有被选中的id，数组
    function getImageSelectionsIds(){
    	var imageList = $("#imageList");
    	var sels = imageList.datagrid("getSelections");
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
        	$(".tree-title:contains('新增影像')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getImageSelectionsIds();
        	//如果被选中数组为null
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一幅影像才能编辑!');
        		return ;
        	}
        	//如果被选中大于1
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个影像!');
        		return ;
        	}
        	
        	$("#imageEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#imageList").datagrid("getSelections")[0];
        			data.priceView = E3.formatPrice(data.price);
        			$("#imageEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getImageSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中影像!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的影像吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("${pageContext.request.contextPath}/image/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除影像成功!',undefined,function(){
            					//删除成功后，重新刷新影像列表
            					$("#imageList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
    
    //日期转换函数,用于data-options可以使日期显示正常
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
      
        return dt.format("yyyy-MM-dd"); 
    } 
</script>