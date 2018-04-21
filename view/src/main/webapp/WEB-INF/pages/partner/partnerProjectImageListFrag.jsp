<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>

<div class="galleria">
	<c:forEach items="${entityList}" var="item">
		<img src="/image/${item.photoName}/${item.photoType}" />
	</c:forEach>
</div>
<script>
	Galleria.loadTheme('static/js/themes/classic/galleria.classic.min.js');
	Galleria.run('.galleria');
</script>