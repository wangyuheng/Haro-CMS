<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	if(path.length()>1) {path = path + "/";} else {
		path = "/";
	}
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=path%>static/css/common/base-min.css">
	<link type="text/css" rel="stylesheet" href="<%=path%>static/css/login/login.css">
	<script src="<%=path%>static/js/common/jquery-1.8.3.min.js"></script>

	<script type="text/javascript">

		function login() {
			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			if(username == "" || password == "") {
				alert("请输入用户名和密码！");
				return;
			}
			document.forms[0].action = "<%=path%>login/login";
			document.forms[0].submit();
		}

		$(function() {
			$('#password').keydown(function(event) {
				if (event.keyCode == 13) {
					login();
				}
			});
		});
	</script>
</head>
<body>
<div class="login-box">
	<div class="login-hd"></div>
	<div class="login-ct">
		<h2 class="login-logo">汉诺管理系统</h2>
		<div class="login-in">
			<form method="post">
				<div class="fmor1">
					<p class="form-box clearfix">
						<label>用户名：</label>
						<input type="text" id="username" name="username" class="fl" value="admin" />
					</p>
					<p class="form-box clearfix">
						<label>密码：</label>
						<input type="text" id="password" name="password" class="fl" value="123456" />
					</p>
					<p class="form-box" align="right">
						<font color="red">${message}</font>
					</p>
					<p class="tr pdT5">
						<input type="button" id="dl" value="登录" onclick="login();" class="login-btn" />
					</p>
				</div>
			</form>
		</div>
	</div>
	<div class="login-ft"></div>
</div>
</body>
</html>