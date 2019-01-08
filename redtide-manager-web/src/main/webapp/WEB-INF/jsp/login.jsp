<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/login.css" rel='stylesheet' type='text/css' />
</head>
<body LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 CLASS=PageBody>

<div class="main">
		<div class="login">
			<h1>赤潮监测系统</h1>
			<div class="inset">
				<!--start-main-->
				<form method="post" action="${pageContext.request.contextPath}/user/login">
			         <div>
			         	<h2>登录</h2>
						<span><label>用户名</label></span>
						<span><input type="text" class="textbox" name="username"></span>
					 </div>
					 <div>
						<span><label>密码</label></span>
					    <span><input type="password" class="password" name="password"></span>
					 </div>
					<div class="sign">
                        <input type="submit" value="登录" class="submit" />
					</div>
					</form>
				</div>
			</div>
		<!--//end-main-->
		</div>

</div>

</body>


</html>