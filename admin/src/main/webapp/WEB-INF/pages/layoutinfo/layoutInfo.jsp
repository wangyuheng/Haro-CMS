<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>汉诺 | 大连</title>
	<%@ include file="/WEB-INF/pages/common/common.jsp"%>

    <script type="text/javascript" src="/static/js/common/validation/ValidationTools.js"></script>
    <script>
        function save(){
            if ($("#insFlg").val() == "0") {
                $("#insFlg").val("1");
                ajaxSubmit({
                    url : "/layoutInfo/save",
                    form : "entityForm",
                    onSuccess : function(data, textStatus) {
                        if ("1" == data) {
                            $.dialog.tips('保存成功！', 1, 'succ.png');
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
		布局管理
	</div>
	<div id="container" class="popBox-out">
		<div class="popBox-in">
			<form id="entityForm" name="entityForm" method="post">
                <input type="hidden" id="insFlg" value="0" />
                <input type="hidden" name="id" value="${layoutInfo.id}" />
				<div class="pdT10 pdR10 pdB10 pdL10">
					<div class="bdr-dashed-blue enterInfo-box">
						<h3 class="tit fb">布局信息</h3>
						<table width="100%">
							<tbody>
							<tr>
								<td class="w302 tr pdR10">
                                    热线电话：</td>
								<td><input type="text" class="valBox fl" id="hotline"
										   maxlength="25" name="hotline" value="${layoutInfo.hotline}" /></td>
							</tr>
                            <tr>
                                <td class="w302 tr pdR10">
                                    通讯地址：</td>
                                <td><input type="text" class="valBox fl" id="address"
                                           name="address" maxlength="100" value="${layoutInfo.address}" /></td>
                            </tr>
                            <tr>
                                <td class="w302 tr pdR10">
                                    邮政编码：</td>
                                <td><input type="text" class="valBox fl" id="zipCode"
                                           name="zipCode" maxlength="20" value="${layoutInfo.zipCode}" /></td>
                            </tr>
                            <tr>
                                <td class="w302 tr pdR10">
                                    联系电话：</td>
                                <td><input type="text" class="valBox fl" id="telephone"
                                           name="telephone" maxlength="20" value="${layoutInfo.telephone}" /></td>
                            </tr>
                            <tr>
                                <td class="w302 tr pdR10">
                                    ICP：</td>
                                <td><input type="text" class="valBox fl" id="icpNo"
                                           name="icpNo" maxlength="20" value="${layoutInfo.icpNo}" /></td>
                            </tr>
                            <tr>
                                <td class="w302 tr pdR10">
                                    版权信息：</td>
                                <td><textarea name="copyright" maxlength="50" rows="3" style="width: 400px;">${layoutInfo.copyright}</textarea>
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