<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<div class="panelBar">
	<ul class="toolBar clearfix">
		<li><a class="btn-del" href="###" onclick="batchDelete('itemId', del);">批量删除</a></li>
		<li class="div-line"></li>
	</ul>
</div>
<div class="gridBox">
	<c:if test="${not empty adviceList}">
		<table width="100%" class="grid-01">
			<thead>
				<tr>
					<th class="cellBg-gary">&nbsp;</th>
					<th class="cellBg-gary">
						<input type="checkbox" id="topChk" name="topChk" onclick="checkChildren(this, 'itemId');" />
					</th>
                    <th>标题</th>
                    <th>昵称</th>
                    <th>电子邮箱</th>
                    <th>处理状态</th>
                    <th>提交时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${adviceList}" var="it" varStatus="itStatus">
					<tr>
						<td class="cellBg-gary">${itStatus.index+1}</td>
						<td class="cellBg-gary">
							<input type="checkbox" name="itemId" value="${it.id}" onclick="checkParent('topChk', 'itemId');" />
						</td>
						<td>${it.title}</td>
						<td>${it.name}</td>
						<td>${it.email}</td>
						<td>${it.stateValue}</td>
						<td>${it.createTime}</td>
						<td>
							<a href="###" onclick="view('${it.id}')">查看</a>
							<a href="###" onclick="dele('${it.id}')">删除</a>
							<c:if test="${1 == it.state}">
								<a href="###" onclick="changeState('${it.id}','${it.state}')">开始处理</a>
							</c:if>
							<c:if test="${2 == it.state}">
								<a href="###" onclick="changeState('${it.id}','${it.state}')">处理完成</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<div class="pagesBox clearfix">
		<haro:Page action="/advice/listFrag"
						   totalCount="${advice.totalCount}" pageNo="${advice.pageNo}"
						   pageSize="${advice.pageSize}" update="entities" form="searchForm" />
	</div>
</div>
