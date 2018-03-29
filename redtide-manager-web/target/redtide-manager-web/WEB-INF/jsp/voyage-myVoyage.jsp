<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="voyageUserList" title="航次列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'${pageContext.request.contextPath}/voyage/list/${user.id}',method:'post',pageSize:30,toolbar:toolbar">
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
		$("#voyageUserList").datagrid('hideColumn', "id");
	});  */
    function getVoyageUserSelectionsIds(){
    	var voyageUserList = $("#voyageUserList");
    	var sels = voyageUserList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'取消关联',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getVoyageUserSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中航次!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 航次的关联吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("${pageContext.request.contextPath}/voyage/delete/${user.id}",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除航次成功!',undefined,function(){
            				//此处不能用reload因为当没有信息时（内容被删除完）会有bug浏览器还是会显示有
            				//所以要重新关闭再打开改选项卡开解决该问题
            				/* $("#voyageUserList").datagrid("reload");   */
            				tabsClose();
            				$(".tree-title:contains('我的航次')").parent().click();
            				});
            			}else {
            				$.messager.alert('提示','删除关联失败！');
            			}
            		});
        	    }
        	});
        }
    }];
    
    //关闭当前选项卡的函数
    function tabsClose(){  
        var tab=$('#tabs').tabs('getSelected');//获取当前选中tabs  
        var index = $('#tabs').tabs('getTabIndex',tab);//获取当前选中tabs的index  
        $('#tabs').tabs('close',index);//关闭对应index的tabs  
    } 
    
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