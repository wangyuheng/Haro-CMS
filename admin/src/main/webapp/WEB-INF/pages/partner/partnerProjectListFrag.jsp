<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/import.jsp"%>
<div class="panelBar">
	<ul class="toolBar clearfix">
		<li><a href="/partner/project/add" class="btn-add">添加</a></li>
		<li class="div-line"></li>
	</ul>
</div>
<div class="gridBox">
	<c:if test="${not empty partnerProjectList}">
		<table width="100%" class="grid-01">
			<thead>
				<tr>
					<th class="cellBg-gary">&nbsp;</th>
					<th class="cellBg-gary">
						<input type="checkbox" id="topChk" name="topChk" onclick="checkChildren(this, 'itemId');" />
					</th>
                    <th>名称</th>
                    <th>封面图片</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${partnerProjectList}" var="it" varStatus="itStatus">
					<tr>
						<td class="cellBg-gary">${itStatus.index+1}</td>
						<td class="cellBg-gary">
							<input type="checkbox" name="itemId" value="${it.id}" onclick="checkParent('topChk', 'itemId');" />
						</td>
						<td>${it.name}</td>
						<td><img src="/file/show?id=${it.coverPhotoName}&fileType=${it.coverPhotoType}" style="width: 165px;height: 100px;" /></td>
						<td>
							<a href="###" onclick="dele('${it.id}')">删除</a>
							<a href="/partner/project/image/list?partnerProjectId=${it.id}">详细设置</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<div class="pagesBox clearfix">
		<haro:Page action="/partner/project/listFrag"
						   totalCount="${partnerDto.totalCount}" pageNo="${partnerDto.pageNo}"
						   pageSize="${partnerDto.pageSize}" update="entities" form="searchForm" />
	</div>
</div>
