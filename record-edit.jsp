<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="recordEditForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/record/update">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
	        <tr>
	            <td>选择航次:</td>
	            <td><input id="voyageCombobox" name="voyageId" class="easyui-combobox voyageSelect" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>点位名称:</td>
	            <td><input class="easyui-textbox" type="text" name="pointName" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>记录序号:</td>
	            <td><input class="easyui-textbox" type="text" name="recordNumber" data-options="" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>经度:</td>
	            <td><input class="easyui-textbox" type="text" name="positionX" data-options="" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>纬度:</td>
	            <td><input class="easyui-textbox" type="text" name="positionY" data-options="" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>天气:</td>
	            <td><input class="easyui-textbox" type="text" name="weather" data-options="" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>水样编号:</td>
	            <td><input class="easyui-textbox" type="text" name="waterDemo" data-options="" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>叶绿素含量:</td>
	            <td><input class="easyui-textbox" type="text" name="chlDemo" data-options="" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>黄色物质含量:</td>
	            <td><input class="easyui-textbox" type="text" name="cdomDemo" data-options="" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>悬浮颗粒含量:</td>
	            <td><input class="easyui-textbox" type="text" name="granuleDemo" data-options="" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>光谱编号:</td>
	            <td><input class="easyui-textbox" type="text" name="spectrumDemo" data-options="" style="width: 280px;"></input></td>
	        </tr>
	        
	        <tr>
	            <td>测量日期:</td>
	            <td><input class="easyui-datebox" type="date" name="arriveTime" id="datetime" data-options="" style="width: 280px;"></input></td>
	        </tr> 
	         <tr>
	            <td>水体照片:</td>
	            <td><input id="" type="file" name="waterFile" value="null" style="width: 280px;"></input></td>
	        </tr> 
	    </table>
	    <input type="hidden" name="itemParams"/>
	    <input type="hidden" name="itemParamId"/>
	</form>
	<div style="padding:5px">
	    <a id="recordEditSubmit" href="javascript:void(0)" class="easyui-linkbutton" onclick="">提交</a>
	</div>
</div>
<script type="text/javascript">

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

	 $(function(){
		 VoyageSelectInit()
		 $("#recordEditSubmit").click(function() {
			//有效性验证
			if(!$('#recordEditForm').form('validate')){
				$.messager.alert('提示','表单还未填写完成!');
				return ;
			} 
			
			$("#recordEditForm").ajaxSubmit({
	  			success:function(data){
	  				if(data.status == 200) {
	  					$.messager.alert('提示','提交成功!');
	  					$("#pictureUploadWindow").window("close");
	  				}else{
	  					$.messager.alert('提示','提交失败!');
	  					$("#pictureUploadWindow").window("close");
	  				}
	  			},
	  			error:function(){
	  				$.messager.alert('提示','请上传图片!');
	  			}
	  			
			}
	  	);
		});
	}); 
	
	
	
	
</script>
