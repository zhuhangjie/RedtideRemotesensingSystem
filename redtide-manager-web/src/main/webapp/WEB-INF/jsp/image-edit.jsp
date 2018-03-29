<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="imageEditForm" class="itemForm" method="post">
		<!-- 回显带的影像id -->
		<input type="hidden" name="id"/>
		
	    <table cellpadding="5">
	        <tr>
	            <td>影像文件名:</td>
	            <td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width: 280px;" disabled="disabled"></input></td>
	        </tr>
	        <tr>
	        	<td>是否有叶绿素信息:</td>
	        	<td>
	            <select name="chl" class="easyui-combobox" style="width: 200px">  
				    <option value="0">无</option>  
				    <option value="1">有</option>  
				</select>
				</td>  
	        </tr>
	        <tr>
	        	<td>是否有赤潮信息:</td>
	        	<td>
	            <select name="rt" class="easyui-combobox" style="width: 200px">  
				    <option value="0">无</option>  
				    <option value="1">有</option>  
				</select>
				</td>  
	        </tr>
	        <tr>
	        	<td>是否有云图信息:</td>
	        	<td>
	            <select name="cloud" class="easyui-combobox" style="width: 200px">  
				    <option value="0">无</option>  
				    <option value="1">有</option>  
				</select>
				</td>  
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
	//日期转换函数
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
	
	function submitForm(){
		if(!$('#imageEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		/* var paramJson = [];
		$("#imageEditForm .params li").each(function(i,e){
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
		
		$("#imageEditForm [name=itemParams]").val(paramJson); */
		
		$.post("${pageContext.request.contextPath}/image/update",$("#imageEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改影像成功!','info',function(){
					$("#imageEditWindow").window('close');
					//修改成功后刷新列表
					$("#imageList").datagrid("reload");
				});
			} else {
				$.messager.alert('提示',"修改异常");
				$("#imageEditWindow").window('close');
			}
		});
	}
</script>
