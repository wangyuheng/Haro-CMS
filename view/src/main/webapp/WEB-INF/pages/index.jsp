<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>汉诺 | 大连</title>

	<%@ include file="/WEB-INF/pages/common/common.jsp"%>
	<script src="/js/superslide.2.1.js"></script>
	<script src="/js/jquery.cxscroll.js"></script>
	<script src="/js/jquery.soChange.js"></script>
	<script type="text/javascript">
		jQuery(function($){
			$("#idx-sfgc").cxScroll();

			$('#idx-news-slide .a_bigImg').soChange({
				thumbObj:'#idx-news-slide .ul_change_a2 span',
				thumbNowClass:'on',//自定义导航对象当前class为on
				changeTime:4000//自定义切换时间为4000ms

			});
		});
	</script>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/top.jsp"%>
<%@ include file="/WEB-INF/pages/common/menu.jsp"%>



<div id="fullSlide" class="fullSlide">
	<div class="bd">
		<ul>
			<li _src="url(static/images/slide_1.jpg)" style="background:#fff center 0 no-repeat;"><a href="###"></a></li>
			<li _src="url(static/images/slide_2.jpg)" style="background:#fff center 0 no-repeat;"><a href="###"></a></li>
		</ul>
	</div>
	<div class="hd">
		<ul>
		</ul>
	</div>
	<span class="prev"></span> <span class="next"></span>
</div>
<script type="text/javascript">
	jQuery(".fullSlide").slide({
		titCell: ".hd ul",
		mainCell: ".bd ul",
		effect: "fold",
		interTime: "5000",
		delayTime : "1000",
		autoPlay: true,
		autoPage: true,
		trigger: "click",
		startFun: function(i) {
			var curLi = jQuery(".fullSlide .bd li").eq(i);
			if (curLi.attr("_src")) {
				curLi.css("background-image", curLi.attr("_src")).removeAttr("_src")
			}
		}
	});
</script>
<div class="box idx-quick">
	<a href="/assembly/4/10"></a>
	<a href="/assembly/4/11"></a>
	<a href="/assembly/4/12"></a>
	<a href="/assembly/4/13"></a>
	<img src="/images/quick_link.png" alt="" />
</div>

<div class="box idx-box">
	<div class="idx-left">
		<div class="idx-cat"><span class="yahei">公司简介</span></div>
		<div class="idx-gsjj">${companyIntroduction.content}</div>

		<div class="idx-cat idx-news"><span class="yahei">公司新闻</span><a href="/news/list/3/8" class="yahei">更多...</a></div>
		<div class="idx-news-list">
			<ul>
				<c:forEach items="${companyNews}" var="item">
					<li><a href="/news/company/${item.id}">${item.title}</a><span>${item.createTime}</span></li>
				</c:forEach>
			</ul>
		</div>
		<div class="clr"></div>
	</div>
	<div class="idx-right">
		<div class="idx-ad"><a href=""><img src="/images/ad.jpg" alt="" /></a></div>

		<div class="idx-cat idx-hydt"><span class="yahei">行业动态</span><a href="/news/list/3/9" class="yahei">更多...</a></div>
		<div class="idx-news-list idx-hydt-list">
			<ul>
				<c:forEach items="${industryNews}" var="item">
					<li><a href="/news/industry/${item.id}">${item.title}</a><span>${item.createTime}</span></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="clr"></div>

	<div class="idx-cat idx-hydt"><span class="yahei">示范工程</span></div>
	<div class="idx-sfgc" id="idx-sfgc">
		<div class="box">
			<ul class="list">
				<c:forEach items="${partnerProjectList}" var="it">
					<li><a href="/partner/project/image/index?partnerProjectId=${it.id}"><img src="/image/${it.coverPhotoName}/${it.coverPhotoType}" alt="" /><span>${it.name}</span></a></li>
				</c:forEach>
			</ul>
			<br class="clr" />
		</div>
	</div>
</div>

    <%@ include file="/WEB-INF/pages/common/footer.jsp"%>
</body>
</html>
