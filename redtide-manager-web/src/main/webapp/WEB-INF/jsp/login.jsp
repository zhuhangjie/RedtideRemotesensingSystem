<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>浙江沿海赤潮系统-登录</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register-login.css">
<script src='${pageContext.request.contextPath}/js/particles.js' type="text/javascript"></script>
<script src='${pageContext.request.contextPath}/js/background.js' type="text/javascript"></script>
<script src='${pageContext.request.contextPath}/js/jquery-1.11.3.min.js' type="text/javascript"></script>
<script src='${pageContext.request.contextPath}/js/layer/layer.js' type="text/javascript"></script>
<script>
	window.onload=function(){
		
		if(window.parent != window){// 如果是在框架中
			//就让框架页面跳转到登陆页面
			window.parent.location.href = "${pageContext.request.contextPath}/login";
			}
	};
	
	function toPortal() {
		window.location.href="${pageContext.request.contextPath}/pic/portal";
	}
	
	$(function(){
		

		
		$("#remember-me").click(function(){
			var n = document.getElementById("remember-me").checked;
			if(n){
				$(".zt").show();
			}else{
				$(".zt").hide();
			}
		});
		
		//触发登录逻辑
		$(".login-btn").click(function(){
			var username = $("#user").val();
			var password = $("#password").val();
			 $.ajax({
			 url: '${pageContext.request.contextPath}/user/login',
			 type: 'post',
			 //jsonp: 'jsonpcallback',
	         //jsonpCallback: "flightHandler",
			 async: false,
			 dataType:"json",
			 data: {
			 	'username':username,
			 	'password':password
			 },
			 success: function(data){
			 	if(data.status != 200){
			 		layer.msg(data.msg);
			 	} else {
			 		layer.msg("登录成功!");
			 		setTimeout("toPortal()",1000);
			 	}
			 	
			 }
			 })
		})
		
	});
	
	
	
	
	//验证码逻辑
	/* $('.imgcode').hover(function(){
		layer.tips("看不清？点击更换", '.verify', {
        		time: 6000,
        		tips: [2, "#3c3c3c"]
    		})
	},function(){
		layer.closeAll('tips');
	}).click(function(){
		$(this).attr('src','http://zrong.me/home/index/imgcode?id=' + Math.random());
	}); */
</script>
</head>
<body>
	<div id="box"></div>
	<div class="cent-box">
		<div class="cont-main clearfix">
			<div class="index-tab">
				<div class="index-slide-nav">
					<a href="${pageContext.request.contextPath}/login" class="active">登录</a>
					<a href="${pageContext.request.contextPath}/register">注册</a>
					<div class="slide-bar"></div>				
				</div>
			</div>
			<div class="login form">
				<div class="group">
					<div class="group-ipt user">
						<input type="text" name="user" id="user" class="ipt" placeholder="用户名" required>
					</div>
					<div class="group-ipt password">
						<input type="password" name="password" id="password" class="ipt" placeholder="输入您的登录密码" required>
					</div>
					<div class="group-ipt verify">
						<input type="text" name="verify" id="verify" class="ipt" placeholder="输入验证码" required>
						<!-- 验证码图片，暂时不用 -->
						<!-- <img src="http://zrong.me/home/index/imgcode?id=" class="imgcode"> -->
					</div>
				</div>
			</div>
	
			<div class="button">
				<button type="submit" class="login-btn register-btn" id="button">登录</button>
			</div>
	
			<div class="remember clearfix">
				<label class="remember-me"><span class="icon"><span class="zt"></span></span><input type="checkbox" name="remember-me" id="remember-me" class="remember-mecheck" checked>记住我</label>
				<label class="forgot-password">
					<a href="#">忘记密码？</a>
				</label>
			</div>
			
		</div>
	</div>
	
	<div class="footer">
		<p>浙江省舟山海洋局</p>
	</div>
</body>
</html>