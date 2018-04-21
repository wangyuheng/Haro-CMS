<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>汉诺 | 大连</title>

	<%@ include file="/WEB-INF/pages/common/common.jsp"%>
	<script type="text/javascript">
		function save(){
			ajaxSubmit({
				url:_basePath+"advice/submit",
				form:"entityForm",
				onSuccess:function(data, textStatus) {
					alert("提交成功，我们会尽快与您取得联系");
					clearForm();
				}

			});
		}
		function clearForm(){
			$("#entityForm")[0].reset();
		}
	</script>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/top.jsp"%>
<%@ include file="/WEB-INF/pages/common/menu.jsp"%>

<div class="banner banner_goin"></div>
<div class="mbx"><div class="box"><span class="location">您的位置：</span><a href="/index">网站首页</a><em class="songti">&gt;&gt;</em>${entity.assemblyTypeEnum.value}</div></div>

<div class="box">
	<div class="left-div">
		<span class="yahei">${assemblyCategory.value}</span>
		<ul>
			<c:forEach items="${assemblyList}" var="it">
				<li><a href="/assembly/${assemblyCategory.key}/${it.key}"
						<c:if test="${entity.type == it.key}"> class="active"</c:if>
						>${it.value}</a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="right-div">
		<div class="serivce-form">
			<form id="entityForm" name="entityForm">
				<span class="yahei">标题</span><em>*</em>
				<input type="text" name="title" class="t" />
				<span class="yahei">昵称</span><em>*</em>
				<input type="text" name="name" class="t" />
				<span class="yahei">电子邮箱</span><em>*</em>
				<input type="text" name="email" class="t" />
				<span class="yahei">内容</span><em>*</em>
				<textarea name="content" id=""></textarea>
			</form>
			<input type="button" value="提交" class="s" onclick="save();" />
		</div>
	</div>
	<div class="clr"></div>
</div>
<%@ include file="/WEB-INF/pages/common/footer.jsp"%>
</body>
</html>
