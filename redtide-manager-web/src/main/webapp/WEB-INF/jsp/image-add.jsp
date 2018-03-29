<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<div style="padding:10px 10px 10px 10px">
	<form id="imageAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>影像生成日期:</td>
	            <td><input class="easyui-datebox" type="date" name="date" id="datetime" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>


<script type="text/javascript">

	//提交表单
	function submitForm(){
		
		//有效性验证
		if(!$('#imageAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//取商品价格，单位为“分”
	
		$.post("${pageContext.request.contextPath}/pic/insert",$("#imageAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增影像成功!');
				//增加成功后重置添加表格
				$('#imageAddForm').form('reset');
				//增加影像后重新刷新影像列表
				$("#imageList").datagrid("reload");
			} else if(data.status == 402) {
				$.messager.alert('提示',data.msg);
			} else if(data.status == 403) {
				$.messager.alert('提示',data.msg);
			}
			else {
				$.messager.alert('提示',"录入异常");
			}
		});
	}
	
	function clearForm(){
		$('#imageAddForm').form('reset');
	}
</script>
