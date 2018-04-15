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
        var News = {
            um: null,
            ctor: function () {
            },
            addCtor: function () {
                this.um && this.um.destroy();
                this.um = UM.getEditor('newsEditor');
                this.um.addListener('focus', function () {
                    $('#newsEditor').html('')
                });
            },
            save: function(){
                if ( $("#content").val() ) {
                    $.dialog.tips('请添加新闻内容！', 1, 'fail.png');
                    return;
                }
                if ($('#entityForm').valid() && $("#insFlg").val() == "0") {
                    $("#insFlg").val("1");
                    $("#content").val(this.um.getContent());
                    ajaxSubmit({
                        url : "/news/company/save",
                        form : "entityForm",
                        onSuccess : function(data, textStatus) {
                            $.dialog.tips('添加成功！', 1, 'succ.png');
                            $("#insFlg").val("0");
                        },
                        onError : function(req) {
                            if (req.status == 461 && req.responseText == "-2") {
                                $.dialog.tips('此课程编码已存在！', 2, 'fail.png');
                            } else {
                                $.dialog.tips('添加失败！', 1, 'fail.png');
                            }
                            $("#insFlg").val("0");
                        },
                        maskId : "container"
                    });
                }
            }
        };
        $(function(){
            News.addCtor();
        })
    </script>
</head>
<body>
	<div id="breadCrumb" style="display:none">
		<a href="#">HN</a>
		>>
		新闻中心
	</div>
	<div id="container" class="popBox-out">
		<div class="popBox-in">
			<form id="entityForm" name="entityForm" method="post">
                <input type="hidden" id="insFlg" value="0" />
                <input type="hidden" id="content" name="content" value="" />
				<div class="pdT10 pdR10 pdB10 pdL10">
					<div class="bdr-dashed-blue enterInfo-box">
						<h3 class="tit fb">基本信息</h3>
						<table width="100%">
							<tbody>
							<tr>
								<td class="w302 tr pdR10"><span class="mark-must">*</span>
                                    标题：</td>
								<td><input type="text" class="valBox fl" id="title"
										   maxlength="200" name="title" valid="{required:true,maxlength:200}" /></td>
							</tr>
                            <tr>
                                <td class="w302 tr pdR10">
                                    来源：</td>
                                <td><input type="text" class="valBox fl" id="source"
                                           name="name" maxlength="40" /></td>
                            </tr>
                            <tr>
                                <td class="w302 tr pdR10"><span class="mark-must">*</span>
                                    作者：</td>
                                <td><input type="text" class="valBox fl" id="author"
                                           name="author" maxlength="40" valid="{required:true}" /></td>
                            </tr>
                            <tr>
                                <td class="w302 tr pdR10"><span class="mark-must">*</span>
                                    概述：</td>
                                <td><textarea name="summary" valid="{required:true}"></textarea>
                            </tr>
							</tbody>
						</table>
					</div>
                    <div class="bdr-dashed-blue enterInfo-box none">
                        <h3 class="tit fb">新闻内容</h3>
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td width="15%"></td>
                                <td>
                                    <script type="text/plain" id="newsEditor" style="width:100%;height:440px;">
                                        <p>可以在这里编辑要发布的新闻内容</p>
                                    </script>
                                <td>
                                <td width="15%"></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
					<div class="subBar tc">
						<ul class="clearfix">
							<li class="fl"><a class="btn-a-1" href="###"
											  onclick="News.save();"><span>保存</span></a></li>
							<li class="fl"><a class="btn-a-1"
											  href="/news/company/list"><span>返回</span></a></li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>