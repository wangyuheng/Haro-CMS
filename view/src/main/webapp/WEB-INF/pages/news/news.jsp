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
<div class="mbx"><div class="box"><span>您的位置：</span><a href="/index">网站首页</a><em class="songti">&gt;&gt;</em>公司新闻</div></div>

<div class="box">
	<div class="left-div">
		<span class="yahei">新闻中心</span>
		<ul>
			<li><a href="/news/newsList" class="active">公司新闻</a></li>
			<li><a href="">行业动态</a></li>
		</ul>
	</div>
	<div class="right-div" id="entities">
		<div class="news-title yahei"><c:out value="${news.title}"/></div>
		<div class="news-info">发布时间: <fmt:formatDate value="${news.createTime}" pattern="yyyy-MM-dd" />    111 次浏览</div>
		<div class="news-desc"><c:out value="${news.summary}"/></div>
		<div class="news-content">
			${news.content}
		</div>
	</div>
	<div class="clr"></div>
</div>
<%@ include file="/WEB-INF/pages/common/footer.jsp"%>
</body>
</html>

