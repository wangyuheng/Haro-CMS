<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>汉诺 | 大连</title>
	<%@ include file="/WEB-INF/pages/common/common.jsp"%>
    <link type="text/css" rel="stylesheet" href="/static/js/common/umeditor/themes/default/css/umeditor.css">

    <script type="text/javascript" src="/static/js/common/umeditor/umeditor.js"></script>
    <script type="text/javascript" src="/static/js/common/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" src="/static/js/common/umeditor/lang/zh-cn/zh-cn.js"></script>

    <script type="text/javascript" src="/static/js/common/validation/ValidationTools.js"></script>
    <script>
        var um = null;
        $(function(){
            um = UM.getEditor('assemblyEditor', {
            });
        });
        function save(){
            if ($("#insFlg").val() == "0") {
                $("#content").val(um.getContent());
                $("#insFlg").val("1");
                ajaxSubmit({
                    url : "/assembly/save",
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
        ${category.value}
	</div>
	<div id="container" class="popBox-out">
		<div class="popBox-in">
			<form id="entityForm" name="entityForm" method="post">
                <input type="hidden" id="insFlg" value="0" />
                <input type="hidden" name="id" value="${entity.id}" />
                <input type="hidden" name="content" id="content" />
				<div class="pdT10 pdR10 pdB10 pdL10">
                    <div class="bdr-dashed-blue enterInfo-box none">
                        <h3 class="tit fb">${entity.assemblyTypeEnum.value}</h3>
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="15%"></td>
                                <td>
                                    <script type="text/plain" id="assemblyEditor" style="width:100%;height:440px;">${entity.content}</script>
                                <td>
                                <td width="15%"></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
					<div class="subBar tc">
						<ul class="clearfix">
							<li class="fl"><a class="btn-a-1" href="###" onclick="save();"><span>保存</span></a></li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>