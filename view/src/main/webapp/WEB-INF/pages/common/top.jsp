<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<div class="box top">
    <a href="/index"><h1>汉诺工程</h1></a>
    <div class="tel yahei">服务热线 : <span class="en"><c:out value="${layoutInfo.hotline}"></c:out></span></div>
    <div class="search">
        <form action="/news/search" method="post">
            <input type="text" name="title" class="t" />
            <input type="submit" class="s" />
        </form>
    </div>
</div>