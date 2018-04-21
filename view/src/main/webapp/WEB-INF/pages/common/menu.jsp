<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<div id="menu">
    <div class="box menu">
        <ul>
            <li id="menu_1" class="first"><a href="/index">首页</a></li>
            <li class="fg"></li>
            <li id="menu_2"><a href="/assembly/1/1">走进HN</a>
                <div class="submenu">
                    <div class="submenu_con">
                        <ul>
                            <c:forEach items="${companies}" var="company">
                                <li><a href="/assembly/1/${company.key}">${company.value}</a></li>
                            </c:forEach>
                        </ul>
                        <br class="clr" />
                    </div>
                </div></li>
            <li class="fg"></li>
            <li id="menu_3"><a href="/assembly/2/6">企业文化</a>
                <div class="submenu">
                    <div class="submenu_con">
                        <ul>
                            <c:forEach items="${cultures}" var="culture">
                                <li><a href="/assembly/2/${culture.key}">${culture.value}</a></li>
                            </c:forEach>
                        </ul>
                        <br class="clr" />
                    </div>
                </div></li>
            <li class="fg"></li>
            <li id="menu_4"><a href="/news/list/3/8">新闻中心</a>
                <div class="submenu">
                    <div class="submenu_con">
                        <ul>
                            <c:forEach items="${newsList}" var="news">
                                <li><a href="/news/list/3/${news.key}">${news.value}</a></li>
                            </c:forEach>
                        </ul>
                        <br class="clr" />
                    </div>
                </div></li>
            <li class="fg"></li>
            <li id="menu_5"><a href="/assembly/4/10">业务领域</a>
                <div class="submenu">
                    <div class="submenu_con">
                        <ul>
                            <c:forEach items="${businesses}" var="business">
                                <li><a href="/assembly/4/${business.key}">${business.value}</a></li>
                            </c:forEach>
                        </ul>
                        <br class="clr" />
                    </div>
                </div></li>
            <li class="fg"></li>
            <li id="menu_6"><a href="/partner/project/list/5/14">合作伙伴</a>
                <div class="submenu">
                    <div class="submenu_con">
                        <ul>
                            <c:forEach items="${partners}" var="partner">
                                <li><a href="/partner/project/list/5/${partner.key}">${partner.value}</a></li>
                            </c:forEach>
                        </ul>
                        <br class="clr" />
                    </div>
                </div></li>
            <li class="fg"></li>
            <li id="menu_7"><a href="/assembly/6/16">技术研发</a>
                <div class="submenu">
                    <div class="submenu_con">
                        <ul>
                            <c:forEach items="${technologies}" var="technology">
                                <li><a href="/assembly/6/${technology.key}">${technology.value}</a></li>
                            </c:forEach>
                        </ul>
                        <br class="clr" />
                    </div>
                </div></li>
            <li class="fg"></li>
            <li id="menu_8"><a href="/assembly/7/18">人力资源</a>
                <div class="submenu">
                    <div class="submenu_con">
                        <ul>
                            <c:forEach items="${talents}" var="talent">
                                <li><a href="/assembly/7/${talent.key}">${talent.value}</a></li>
                            </c:forEach>
                        </ul>
                        <br class="clr" />
                    </div>
                </div></li>
            <li class="fg"></li>
            <li id="menu_9"><a href="/assembly/8/21">服务中心</a>
                <div class="submenu">
                    <div class="submenu_con">
                        <ul>
                            <c:forEach items="${services}" var="service">
                                <li><a href="/assembly/8/${service.key}">${service.value}</a></li>
                            </c:forEach>
                        </ul>
                        <br class="clr" />
                    </div>
                </div></li>
        </ul>
        <br class="clr" />
    </div>
</div>