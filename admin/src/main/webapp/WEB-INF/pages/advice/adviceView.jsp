<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>汉诺 | 大连</title>
	<%@ include file="/WEB-INF/pages/common/common.jsp"%>
</head>
<body>
	<div id="breadCrumb" style="display:none">
		<a href="#">HN</a>
		>>
		投诉建议
	</div>
		<div class="popBox-in">
			<form id="entityForm" name="entityForm" method="post">
                <input type="hidden" id="insFlg" value="0" />
                <input type="hidden" id="content" name="content" value="" />
				<div class="pdT10 pdR10 pdB10 pdL10">
					<div class="bdr-dashed-blue enterInfo-box">
						<h3 class="tit fb">详情</h3>
						<table width="100%">
							<tbody>
                            <tr>
                                <td class="w70 tr pdR10">
                                    标题：</td>
                                <td><c:out value="${advice.title}" /></td>
                            </tr>
                            <tr>
                                <td class="w50 tr pdR10">
                                    处理状态：</td>
                                <td><c:out value="${advice.stateValue}" /></td>
                            </tr>
                            <tr>
                                <td class="w50 tr pdR10">
                                    昵称：</td>
                                <td><c:out value="${advice.name}" /></td>
                            </tr>
                            <tr>
                                <td class="w50 tr pdR10">
                                    电子邮箱：</td>
                                <td><c:out value="${advice.email}" /></td>
                            </tr>
                            <tr>
                                <td class="w50 tr pdR10">
                                    提交时间：</td>
                                <td><fmt:formatDate value="${advice.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                            </tr>
                            <tr>
                                <td class="w50 tr pdR10">
                                    内容：</td>
                                <td><c:out value="${advice.content}" /></td>
                            </tr>
							</tbody>
						</table>
					</div>

				</div>
			</form>
		</div>
</body>
</html>