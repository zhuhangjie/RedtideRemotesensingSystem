<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>浙江沿海赤潮系统-注册</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register-login.css">
<script src='${pageContext.request.contextPath}/js/particles.js' type="text/javascript"></script>
<script src='${pageContext.request.contextPath}/js/background.js' type="text/javascript"></script>
<script src='${pageContext.request.contextPath}/js/jquery-1.11.3.min.js' type="text/javascript"></script>
<script src='${pageContext.request.contextPath}/js/layer/layer.js' type="text/javascript"></script>
<script>
	var validFlag = 0;
	
	function toLogin() {
		window.location.href="${pageContext.request.contextPath}/login";
	}
	
	$(function() {
		
			$("#button").click(function(){
				
				if($("#email").val() == "" || $("#user").val() == "" || $("#password").val() == "" || $("#password1").val() == ""
				){
					$("#email").trigger("blur");
					$("#user").trigger("blur");
					$("#password").trigger("blur");
					$("#password1").trigger("blur");
				} else if(validFlag == 1){
					var email = $("#email").val();
					var username = $("#user").val();
					var password = $("#password").val();
					 $.ajax({
					 url: '${pageContext.request.contextPath}/user/register',
					 type: 'post',
			 		 //jsonp: 'jsonpcallback',
			         //jsonpCallback: "flightHandler", 
					 async: false,
					 dataType:"json",
					 data: {
					 	'email':email,
					 	'username':username,
					 	'password':password
					 },
					 success: function(data){
					 	layer.msg("注册成功，请登录");
					 	setTimeout("toLogin()",2000);
					 }
					 }) 
				} else {
					alert("请输入正确的用户信息");
				}
		})
		
		//检查email
		//后缀暂时设定为以下这些
		$("#email").blur(function(){
			if($("#email").val() == ""){
				$("#email_span").html("邮箱不能为空");
			}else {
				if($("#email").val().indexOf("@") <= 0 || $("#email").val().indexOf(".") <= 0){
					$("#email_span").html("请输入正确邮箱格式");
					validFlag = 0;
				}else {
					$.ajax({
						 url: '${pageContext.request.contextPath}/user/checkRegister',
						 type: 'post',
						 dataType:"json",
						 data: {
						 	'data':$("#email").val(),
						 	'type':1
						 },
						 success: function(data){
							 if(data.status == 200){
								 $("#email_span").html("");
								 validFlag = 1;
							 }else {
								 $("#email_span").html("邮箱已存在");
								 validFlag = 0;
							 }
						 }
						 })
				}
				
			}
		});
		
		
		//检查用户名
		$("#user").blur(function(){
			if($("#user").val() == ""){
				$("#user_span").html("用户名不能为空");
				validFlag = 0;
			}else if($("#user").val().length > 18 || $("#user").val().length < 6){
				$("#user_span").html("用户名长度必须在6到18位之间");
				validFlag = 0;
			}else {
				$.ajax({
					 url: '${pageContext.request.contextPath}/user/checkRegister',
					 type: 'post',
					 dataType:"json",
					 data: {
					 	'data':$("#user").val(),
					 	'type':2
					 },
					 success: function(data){
						 if(data.status == 200){
							 $("#user_span").html("");
							 validFlag = 1;
						 }else {
							 $("#user_span").html("用户名已存在");
							 validFlag = 0;
						 }
					 }
					 })
			}
		});
		
		//校验密码
		$("#password").blur(function(){
			if($("#password").val() == ""){
				$("#password_span").html("密码不能为空");
				validFlag = 0;
			}else if($("#password").val().length > 18 || $("#password").val().length < 6){
				$("#password_span").html("密码长度必须在6到18位之间");
				validFlag = 0;
			}else {
				$("#password_span").html("");
				validFlag = 1;
			}
			if($("#password1").val() != $("#password").val()) {
				$("#password1_span").html("两次输入的密码不一致");
				validFlag = 0;
			} else if( $("#password1").val() == "" ){
				validFlag = 0;
			}
			else {
				$("#password1_span").html("");
				validFlag = 1;
			}
		});
		
		//确认密码
		$("#password1").blur(function(){
			if($("#password1").val() != $("#password").val()){
				$("#password1_span").html("两次输入的密码不一致");
				validFlag = 0;
			}else {
				$("#password1_span").html("");
				validFlag = 1;
			}
		});
		//记住密码选项效果
		$("#remember-me").click(function(){
			var n = document.getElementById("remember-me").checked;
			if(n){
				$(".zt").show();
			}else{
				$(".zt").hide();
			}
		})
	});
	
		/* $('.imgcode').hover(function(){
		layer.tips("看不清？点击更换", '.verify', {
	    		time: 6000,
	    		tips: [2, "#3c3c3c"]
			})
	},function(){
		layer.closeAll('tips');
	}).click(function(){
		$(this).attr('src','http://zrong.me/home/index/imgcode?id=' + Math.random());
	}) */
	
</script>
</head>
<body>
<div id="box"></div>
<div class="cent-box register-box">

	<div class="cont-main clearfix">
		<div class="index-tab">
			<div class="index-slide-nav">
				<a href="${pageContext.request.contextPath}/login">登录</a>
				<a href="${pageContext.request.contextPath}/register" class="active">注册</a>
				<div class="slide-bar slide-bar1"></div>				
			</div>
		</div>
		<div class="login form">
			<div class="group">
				<div class="group-ipt email">
					<input type="email" name="email" id="email" class="ipt" placeholder="邮箱地址" required>
				</div><span id="email_span" style="color: red;font-size:13px;"></span>
				<div class="group-ipt user">
				<!-- 注意这里由于框架关系id还是user -->
					<input type="text" name="user" id="user" class="ipt" placeholder="用户名" required>
				</div><span id="user_span" style="color: red;font-size:13px;"></span>
				<div class="group-ipt password">
					<input type="password" name="password" id="password" class="ipt" placeholder="设置登录密码" required>
				</div><span id="password_span" style="color: red;font-size:13px;"></span>
				<div class="group-ipt password1">
					<input type="password" name="password1" id="password1" class="ipt" placeholder="重复密码" required>
				</div><span id="password1_span" style="color: red;font-size:13px;"></span>
				<div class="group-ipt verify">
					<input type="text" name="verify" id="verify" class="ipt" placeholder="输入验证码" required>
					<!-- <img src="http://zrong.me/home/index/imgcode?id=" class="imgcode"> -->
				</div><span id="password_span"></span>
			</div>
		</div>

		<div class="button">
			<button type="submit" class="login-btn register-btn" id="button">注册</button>
		</div>
		
	</div>
</div>

<div class="footer">
	<p>浙江省舟山海洋局</p>
</div>
</body>
</html>