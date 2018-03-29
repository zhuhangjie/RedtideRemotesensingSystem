<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="userEditForm" class="" method="post">
		<!-- 回显带的个人信息id -->
		<input type="hidden" name="id" value="${user.id }"/>
		<input type="hidden" name="username" value="${user.username }"/>
		
	    <table cellpadding="5">
	        <tr>
	            <td>新密码:</td>
	            <td><input id="password" class="easyui-validatebox" type="text" name="password" data-options="required:true,validType:'range[6,18]'" style="width: 280px;" ></input></td>
	        </tr>
	        <tr>
	            <td>确认新密码:</td>
	            <td><input class="easyui-validatebox" type="text" name="confirm" data-options="required:true" validType="equalTo['#password']" invalidMessage="两次输入密码不匹配" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>姓名:</td>
	            <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true,validType:'range[1,32]'" style="width: 280px;" value="${user.name }"></input></td>
	        </tr>
	        <tr>
	            <td>邮箱:</td>
	            <td><input class="easyui-validatebox" type="text" name="email" data-options="required:true,validType:'email'" style="width: 280px;" value="${user.email }"></input></td>
	        </tr>
	    </table>
	    <input type="hidden" name="itemParams"/>
	    <input type="hidden" name="itemParamId"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">修改</a>
	</div>
</div>
<script type="text/javascript">
	
	function submitForm(){
		if(!$('#userEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		/* var paramJson = [];
		$("#userEditForm .params li").each(function(i,e){
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
		
		$("#userEditForm [name=itemParams]").val(paramJson); */
		
		$.post("${pageContext.request.contextPath}/user/update",$("#userEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改个人信息成功!','info',function(){
					$("#userEditWindow").window('close');
				});
			} else {
				$.messager.alert('提示',"修改异常");
				$("#userEditWindow").window('close');
			}
		});
		
		
	}
	
	$.extend($.fn.validatebox.defaults.rules, {
	    range: {
			validator: function(value, param){
				return value.length >= param[0] && value.length <= param[1];
			},
			message: '密码必须在{0}到{1}位之间.'
	    }
	});
	
	$.extend($.fn.validatebox.defaults.rules, { 
		/*必须和某个字段相等*/
		equalTo: {
			validator:function(value,param){
				return $(param[0]).val() == value;
			},
			message:'两次输入不一'
		}
	});
</script>
