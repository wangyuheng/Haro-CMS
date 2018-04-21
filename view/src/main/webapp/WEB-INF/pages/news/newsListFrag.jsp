<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>

<div class="news-list">
	<ul>
		<c:forEach items="${entityList}" var="item">
			<li>
				<a href="###" class="cat">[公司新闻]</a>
				<a href="###" class="title" onclick="newsView('${item.id}')" >${item.title}</a>
				<span>${item.createTime}</span>
			</li>
		</c:forEach>
	</ul>
</div>
<haro:FontPageTag action="/news/listFrag"
				  totalCount="${newsDto.totalCount}" pageNo="${newsDto.pageNo}"
				  pageSize="${newsDto.pageSize}" update="entities" form="newsForm" />
