<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="voyageAddForm" class="itemForm" method="post">
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
	            <td><input class="easyui-textbox" name="mission" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input></td>
	        </tr>
	        
	        <!-- <tr>
	            <td>航次合影:</td>
	            <td>
	            	 <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
	                 <input type="hidden" name="image"/>
	            </td>
	        </tr> -->
	        
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
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	/* var itemAddEditor ;
	//页面初始化完毕后执行此方法
	$(function(){
		//创建富文本编辑器
		itemAddEditor = E3.createEditor("#voyageAddForm [name=desc]");
		//初始化类目选择和图片上传器
		E3.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
			//E3.changeItemParam(node, "voyageAddForm");
		}});
	}); */
	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#voyageAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//取商品价格，单位为“分”
		/* $("#voyageAddForm [name=price]").val(eval($("#voyageAddForm [name=priceView]").val()) * 100);
		//同步文本框中的商品描述
		itemAddEditor.sync(); */
		//取商品的规格
		/*
		var paramJson = [];
		$("#voyageAddForm .params li").each(function(i,e){
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
		//把json对象转换成字符串
		paramJson = JSON.stringify(paramJson);
		$("#voyageAddForm [name=itemParams]").val(paramJson);
		*/
		//ajax的post方式提交表单
		//$("#voyageAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("${pageContext.request.contextPath}/voyage/save",$("#voyageAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增航次成功!');
				//添加完成后,刷新航次列表
				$("#voyageList").datagrid("reload");
				//添加完成后打开航次列表界面
				$(".tree-title:contains('查询航次')").parent().click();
				//添加航次完成后，初始化纪录添加中的combobox列表
				VoyageSelectInit();
				//清空表格
				clearForm();
			} else {
				$.messager.alert('提示','新增航次失败!');
			}
		});
	}
	
	function clearForm(){
		$('#voyageAddForm').form('reset');
	}
</script>
