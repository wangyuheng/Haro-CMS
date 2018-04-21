<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>

<div class="img-list">
	<ul>
		<c:forEach items="${entityList}" var="item">
			<li>
				<a href="###" onclick="viewImages('${item.id}')"><img src="/image/${item.coverPhotoName}/${item.coverPhotoType}" alt="" style="width: 230px;height: 200px;"/><span>${item.name}</span></a>
			</li>
		</c:forEach>
	</ul>
	<br class="clr" />
</div>

<haro:FontPageTag action="/partner/project/listFrag"
				  totalCount="${partnerDto.totalCount}" pageNo="${partnerDto.pageNo}"
				  pageSize="${partnerDto.pageSize}" update="entities" form="entityForm" />
