<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>汉诺 | 大连</title>

	<%@ include file="/WEB-INF/pages/common/common.jsp"%>
	<script type="text/javascript">
		function newsView(vId){
			ajaxSubmit({
				url:_basePath+"news/view",
				params:{id:vId},
				updateId:"entities"
			});
		}
	</script>
</head>
	<body>
		<%@ include file="/WEB-INF/pages/common/top.jsp"%>
		<%@ include file="/WEB-INF/pages/common/menu.jsp"%>

		<div class="banner banner_news"></div>
		<div class="mbx"><div class="box"><span>您的位置：</span><a href="/index">网站首页</a><em class="songti">&gt;&gt;</em>${entity.assemblyTypeEnum.value}</div></div>
		<form id="newsForm" name="newsForm">
			<input type="hidden" name="type" value="${newsDto.type}" />
		</form>
		<div class="box">
			<div class="left-div">
				<span class="yahei">${assemblyCategory.value}</span>
				<ul>
					<c:forEach items="${assemblyList}" var="it">
						<li><a href="/news/list/${assemblyCategory.key}/${it.key}"
								<c:if test="${entity.type == it.key}"> class="active"</c:if>
								>${it.value}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="right-div" id="entities">
				<jsp:include page="newsListFrag.jsp"></jsp:include>
			</div>
			<div class="clr"></div>
		</div>
		<%@ include file="/WEB-INF/pages/common/footer.jsp"%>
	</body>
</html>
