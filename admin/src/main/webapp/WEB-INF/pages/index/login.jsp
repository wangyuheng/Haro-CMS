<%@ page pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>

<!doctype html>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="/static/css/common/base-min.css">
	<link type="text/css" rel="stylesheet" href="/static/css/login/login.css">
	<script src="/static/js/common/jquery-1.8.3.min.js"></script>

	<script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;

        function forbidBackSpace(e) {
            // 获取event对象
            var ev = e || window.event;
            // 获取事件源
            var obj = ev.target || ev.srcElement;
            // 获取事件源类型
            var t = obj.type || obj.getAttribute('type');
            // 获取作为判断条件的事件类型
            var vReadOnly = obj.readOnly;
            var vDisabled = obj.disabled;
            // 处理undefined值情况
            vReadOnly = (vReadOnly === undefined) ? false : vReadOnly;
            vDisabled = (vDisabled === undefined) ? true : vDisabled;
            // 当敲Backspace键时，事件源类型为密码或单行、多行文本的，
            // 并且readOnly属性为true或disabled属性为true的，则退格键失效
            var flag1 = ev.keyCode === 8 && (t === "password" || t === "text" || t === "textarea") && (vReadOnly === true || vDisabled == true);
            // 当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
            var flag2 = ev.keyCode === 8 && t !== "password" && t !== "text" && t !== "textarea";
            // 判断
            if (flag2 || flag1) return false;
        }
	</script>
	<script type="text/javascript">

		function login() {
			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			if(username == "" || password == "") {
				alert("请输入用户名和密码！");
				return;
			}
			document.forms[0].action = "/login/login";
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