<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简单后台管理页面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		alert("jquery加载成功");
		$("#lastInfoButton").click(function(){
			$.ajax({
				  type: "POST",
				  url: "test.js",
				  success:function(data) {
					  if(data.status = 200) {
						  alert("");
					  }
				  }
			})
		});
	})
</script>
</head>
<body>
<button id="lastInfoButton">查看最新赤潮信息</button>
</br></br>

<input type="text" name="dateInfo">

</body>
</html>