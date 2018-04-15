<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<div class="panelBar">
	<ul class="toolBar clearfix">
		<li><a href="/news/company/add" class="btn-add">添加</a></li>
		<li class="div-line"></li>
		<li><a class="btn-del" href="###" onclick="batchDelete('itemId', del);">批量删除</a></li>
		<li class="div-line"></li>
	</ul>
</div>
<div class="gridBox">
	<c:if test="${not empty newsList}">
		<table width="100%" class="grid-01">
			<thead>
				<tr>
					<th class="cellBg-gary">&nbsp;</th>
					<th class="cellBg-gary">
						<input type="checkbox" id="topChk" name="topChk" onclick="checkChildren(this, 'itemId');" />
					</th>
                    <th>标题</th>
                    <th>作者</th>
                    <th>日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${newsList}" var="it" varStatus="itStatus">
					<tr>
						<td class="cellBg-gary">${itStatus.index+1}</td>
						<td class="cellBg-gary">
							<input type="checkbox" name="itemId" value="${it.id}" onclick="checkParent('topChk', 'itemId');" />
						</td>
						<td>${it.title}</td>
						<td>${it.author}</td>
						<td>${it.createTime}</td>
						<td>
							<a href="###" onclick="dele('${it.id}')">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<div class="pagesBox clearfix">
		<haro:Page action="/news/company/listFrag"
						   totalCount="${news.totalCount}" pageNo="${news.pageNo}"
						   pageSize="${news.pageSize}" update="entities" form="searchForm" />
	</div>
</div>
