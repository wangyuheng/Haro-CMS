<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>汉诺 | 大连</title>
	<%@ include file="/WEB-INF/pages/common/common.jsp"%>
	<style type="text/css">
		body,html{height:100%;overflow:hidden;overflow:visible\9;}
		body{overflow:hidden;background:#deeff7;overflow:visible\9;}
	</style>
</head>
<body>
<!--top start  -->
<div id="header">
	<%@ include file="/WEB-INF/pages/layout/top.jsp"%>
</div>
<div id="header-short">
	<a href="###" class="btn-toggle"></a>
</div>
<!--top end  -->

<div id="container">
	<!-- menu start -->
	<div position="left" title="系统导航">
		<%@ include file="/WEB-INF/pages/layout/leftMenu.jsp"%>
	</div>
	<!-- menu end -->

	<div position="center" >
		<div id="breadCrumb">
			<a href="###">汉诺管理平台</a>
		</div>
		<div id="navTabBox">
			<div class="navTab-hd">
				<a class="btn-navTab-left" href="###" style="display: inline;"></a>
				<a class="btn-navTab-right" href="###" style="display: inline;"></a>
				<ul class="navTab-menu clearfix">
					<li>
						<a href="###" class="tab-l">
							<span><i class="home_icon"></i>我的主页</span>
						</a>
					</li>
				</ul>
			</div>
			<div class="navTab-panel">
				<div class="tabUnit">
					<iframe src="/index/home" frameborder="0" style="width:100%;height:100%"></iframe>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>