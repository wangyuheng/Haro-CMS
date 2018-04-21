<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>

<div class="news-title yahei"><c:out value="${news.title}"/></div>
<div class="news-info">发布时间: <fmt:formatDate value="${news.createTime}" pattern="yyyy-MM-dd" />    111 次浏览</div>
<div class="news-desc"><c:out value="${news.summary}"/></div>
<div class="news-content">
	${news.content}
</div>