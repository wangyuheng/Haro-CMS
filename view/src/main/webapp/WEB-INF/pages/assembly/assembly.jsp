<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>汉诺 | 大连</title>
	<%@ include file="/WEB-INF/pages/common/common.jsp"%>
	<script type="text/javascript">
		$(function(){


        });
	</script>
</head>
	<body>
		<%@ include file="/WEB-INF/pages/common/top.jsp"%>
		<%@ include file="/WEB-INF/pages/common/menu.jsp"%>


		<c:choose>
			<c:when test="${8 == assemblyCategory.key}">
				<div class="banner banner_service"></div>
			</c:when>
			<c:when test="${1 == assemblyCategory.key}">
				<div class="banner banner_goin"></div>
			</c:when>
			<c:otherwise>
				<div class="banner banner_news"></div>
			</c:otherwise>
		</c:choose>
		<div class="mbx"><div class="box"><span class="location">您的位置：</span><a href="/index">网站首页</a><em class="songti">&gt;&gt;</em>${entity.assemblyTypeEnum.value}</div></div>

		<div class="box">
			<div class="left-div">
				<span class="yahei">${assemblyCategory.value}</span>
				<ul>
					<c:forEach items="${assemblyList}" var="it">
						<li><a href="/assembly/${assemblyCategory.key}/${it.key}"
                                <c:if test="${entity.type == it.key}"> class="active"</c:if>
                                >${it.value}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="right-div">
				<div class="goin-content">
					${entity.content}
				</div>
			</div>
			<div class="clr"></div>
		</div>
		<%@ include file="/WEB-INF/pages/common/footer.jsp"%>
	</body>
</html>
