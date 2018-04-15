<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>汉诺 | 大连</title>
	<%@ include file="/WEB-INF/pages/common/common.jsp"%>

    <script type="text/javascript" src="/static/js/common/validation/ValidationTools.js"></script>
    <script>
        $(function(){
            $('#password').val("");
            $('#repassword').val("");
            $('#newRepassword').val("");
        });
        function save(){
            if ($("#entityForm").valid() && $("#insFlg").val() == "0") {
                var newPassword = $('#newPassword').val();
                var repassword = $('#repassword').val();
                if (newPassword != repassword) {
                    $.dialog.tips('请重新输入，两次输入的密码不一致', 1, 'fail.png');
                    return;
                }
                $("#insFlg").val("1");
                ajaxSubmit({
                    url : "/user/password/save",
                    form : "entityForm",
                    onSuccess : function(data, textStatus) {
                        if ("1" == data) {
                            $.dialog.tips('保存成功！', 1, 'succ.png');
                            $('#password').val("");
                            $('#repassword').val("");
                            $('#newRepassword').val("");
                        } else {
                            $.dialog.tips('保存失败， 请稍后再试！', 1, 'fail.png');
                        }
                        $("#insFlg").val("0");
                    },
                    onError : function(req) {
                        $.dialog.tips('保存失败， 请稍后再试！', 1, 'fail.png');
                        $("#insFlg").val("0");
                    },
                    maskId : "container"
                });
            }
        }
    </script>
</head>
<body>
	<div id="breadCrumb" style="display:none">
		<a href="#">HN</a>
		>>
		账号管理
	</div>
	<div id="container" class="popBox-out">
		<div class="popBox-in">
			<form id="entityForm" name="entityForm" method="post">
                <input type="hidden" id="insFlg" value="0" />
                <input type="hidden" name="id" value="${user.id}" />
				<div class="pdT10 pdR10 pdB10 pdL10">
					<div class="bdr-dashed-blue enterInfo-box">
						<h3 class="tit fb">账号信息</h3>
						<table width="100%">
							<tbody>
							<tr>
								<td class="w302 tr pdR10">
                                    用户名：</td>
								<td>${user.username}</td>
							</tr>
                            <tr>
                                <td class="w302 tr pdR10">
                                    密码：</td>
                                <td><input type="text" class="valBox fl" id="password"
                                           name="password" maxlength="20" /></td>
                            </tr>
                            <tr>
                                <td class="w302 tr pdR10">
                                    新密码：</td>
                                <td><input type="text" class="valBox fl" id="newPassword"
                                           name="newPassword" maxlength="20" /></td>
                            </tr>
                            <tr>
                                <td class="w302 tr pdR10">
                                    重复新密码：</td>
                                <td><input type="text" class="valBox fl" id="repassword"
                                           name="repassword" maxlength="20" /></td>
                            </tr>

							</tbody>
						</table>
					</div>
					<div class="subBar tc">
						<ul class="clearfix">
							<li class="fl"><a class="btn-a-1" href="###"
											  onclick="save();"><span>保存</span></a></li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>