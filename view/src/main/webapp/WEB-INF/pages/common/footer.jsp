<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<div class="footer yahei">
    <c:out value="${layoutInfo.copyright}"></c:out><br />
    通讯地址：<c:out value="${layoutInfo.address}"></c:out> 邮政编码：<c:out value="${layoutInfo.zipCode}"></c:out> <c:out value="${layoutInfo.icpNo}"></c:out>
</div>