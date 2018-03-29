<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="voyageEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
	        <tr>
	            <td>航次名称:</td>
	            <td><input class="easyui-textbox" type="text" name="voyageName" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>航次负责人:</td>
	            <!-- <td><input class="easyui-textbox" name="sellPoint" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input></td> -->
	            <td><input class="easyui-textbox" type="text" name="leader" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>船号:</td>
	            <td><input class="easyui-textbox" type="text" name="shipCode" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>航次位置:</td>
	            <td><input class="easyui-textbox" type="text" name="position" data-options="required:true" style="width: 280px;"></input></td>
	        </tr> 
	        <tr>
	            <td>航次任务:</td>
	            <td><input class="easyui-textbox" name="mission" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;" value="${mission }"></input></td>
	        </tr>
	        <tr>
	            <td>开始日期:</td>
	            <td><input class="easyui-datebox" type="date" name="startTime" id="datetime" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>结束日期:</td>
	            <td><input class="easyui-datebox" type="date" name="endTime" id="datetime" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	    </table>
	    <input type="hidden" name="itemParams"/>
	    <input type="hidden" name="itemParamId"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	var itemEditEditor ;
	$(function(){
		//实例化编辑器
		itemEditEditor = E3.createEditor("#voyageEditForm [name=desc]");
	});
	
	function submitForm(){
		if(!$('#voyageEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var paramJson = [];
		$("#voyageEditForm .params li").each(function(i,e){
			var trs = $(e).find("tr");
			var group = trs.eq(0).text();
			var ps = [];
			for(var i = 1;i<trs.length;i++){
				var tr = trs.eq(i);
				ps.push({
					"k" : $.trim(tr.find("td").eq(0).find("span").text()),
					"v" : $.trim(tr.find("input").val())
				});
			}
			paramJson.push({
				"group" : group,
				"params": ps
			});
		});
		paramJson = JSON.stringify(paramJson);
		
		$("#voyageEditForm [name=itemParams]").val(paramJson);
		
		$.post("${pageContext.request.contextPath}/voyage/update",$("#voyageEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改航次成功!','info',function(){
					$("#voyageEditWindow").window('close');
					//刷新航次列表
					$("#voyageList").datagrid("reload");
				});
			}
		});
	}
</script>
