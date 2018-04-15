<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<div class="panelBar">
	<ul class="toolBar clearfix">
		<li><a href="/partner/project/image/add?partnerProjectId=${partnerProjectImageDto.partnerProjectId}" class="btn-add">添加</a></li>
		<li class="div-line"></li>
	</ul>
</div>
<div class="gridBox">
	<c:if test="${not empty partnerProjectImageList}">
		<table width="100%" class="grid-01">
			<thead>
				<tr>
					<th class="cellBg-gary">&nbsp;</th>
                    <th>图片</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${partnerProjectImageList}" var="it" varStatus="itStatus">
					<tr>
						<td class="cellBg-gary">${itStatus.index+1}</td>
						<td><img src="/file/show?id=${it.photoName}&fileType=${it.photoType}" style="width: 165px;height: 100px;" /></td>
						<td>
							<a href="###" onclick="dele('${it.id}')">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<div class="pagesBox clearfix">
		<haro:Page action="/partner/project/listFrag"
						   totalCount="${partnerProjectImageDto.totalCount}" pageNo="${partnerProjectImageDto.pageNo}"
						   pageSize="${partnerProjectImageDto.pageSize}" update="entities" form="searchForm" />
	</div>
</div>
