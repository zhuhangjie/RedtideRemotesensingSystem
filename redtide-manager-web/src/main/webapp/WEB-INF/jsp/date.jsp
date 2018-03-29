<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/laydate/laydate.js"></script>
<style>
  body{padding: 20px;}
  .demo-input{padding-left: 10px; height: 38px; min-width: 262px; line-height: 38px; border: 1px solid #e6e6e6;  background-color: #fff;  border-radius: 2px;}
  </style>
  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div style="padding:10px 10px 10px 10px">
	<form id="imageAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	         <tr>
	            <td>影像名称:</td>
	            <td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>影像生成日期:</td>
	            <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	    </table>
	</form>
	<input type="text" class="demo-input" placeholder="请选择日期" id="test1">
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<input type="text" class="demo-input" placeholder="请选择日期" id="test1">

<script type="text/javascript" charset="utf-8" src="/js/laydate/laydate.js"></script> <!-- 改成你的路径 -->
<script>
lay('#version').html('-v'+ laydate.v);

//执行一个laydate实例
laydate.render({
  elem: '#test1' //指定元素
});
</script>
<script type="text/javascript">

	var itemAddEditor ;
	//页面初始化完毕后执行此方法
	$(function(){
		
		//创建富文本编辑器
		itemAddEditor = E3.createEditor("#imageAddForm [name=desc]");
		//初始化类目选择和图片上传器
		E3.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
			//E3.changeItemParam(node, "imageAddForm");
		}});
	});
	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#imageAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//取商品价格，单位为“分”
		$("#imageAddForm [name=price]").val(eval($("#imageAddForm [name=priceView]").val()) * 100);

		$.post("/image/save",$("#imageAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增商品成功!');
			}
		});
	}
	
	function clearForm(){
		$('#imageAddForm').form('reset');
		itemAddEditor.html('');
	}
</script>
</body>
</html>