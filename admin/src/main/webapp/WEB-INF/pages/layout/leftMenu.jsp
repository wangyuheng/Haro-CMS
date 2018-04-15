<%@ page language="java" pageEncoding="UTF-8"%>
<div id="accordion-box">
    <div class="accordion-hd"><h2><i class="acc-ico-01"></i>系统管理</h2></div>
    <div class="accordion-ct">
        <div class="leftFrameMenu menuTree">
            <ul>
                <li class="child" url="/layoutInfo"><a href="#">布局元素管理</a></li>
                <li class="child" url="/user/password/modify"><a href="#">修改账号密码</a></li>
            </ul>
        </div>
    </div>

    <div class="accordion-hd"><h2><i class="acc-ico-01"></i>走进HN</h2></div>
    <div class="accordion-ct">
        <div class="leftFrameMenu menuTree">
            <ul>
                <c:forEach items="${companies}" var="item">
                    <li class="child" url="/assembly/1/${item.key}"><a href="#">${item.value}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="accordion-hd"><h2><i class="acc-ico-01"></i>企业文化</h2></div>
    <div class="accordion-ct">
        <div class="leftFrameMenu menuTree">
            <ul>
                <c:forEach items="${cultures}" var="item">
                    <li class="child" url="/assembly/2/${item.key}"><a href="#">${item.value}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="accordion-hd"><h2><i class="acc-ico-01"></i>新闻中心</h2></div>
    <div class="accordion-ct">
        <div class="leftFrameMenu menuTree">
            <ul>
                <li class="child" url="/news/company/list"><a href="#">公司新闻</a></li>
                <li class="child" url="/news/industry/list"><a href="#">行业动态</a></li>
            </ul>
        </div>
    </div>

    <div class="accordion-hd"><h2><i class="acc-ico-01"></i>业务领域</h2></div>
    <div class="accordion-ct">
        <div class="leftFrameMenu menuTree">
            <ul>
                <c:forEach items="${businesses}" var="item">
                    <li class="child" url="/assembly/4/${item.key}"><a href="#">${item.value}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="accordion-hd"><h2><i class="acc-ico-01"></i>合作伙伴</h2></div>
    <div class="accordion-ct">
        <div class="leftFrameMenu menuTree">
            <ul>
                <li class="child" url="/partner/project/list"><a href="#">示范工程</a></li>
                <li class="child" url="/assembly/5/15"><a href="#">客户名录表</a></li>
            </ul>
        </div>
    </div>

    <div class="accordion-hd"><h2><i class="acc-ico-01"></i>技术研发</h2></div>
    <div class="accordion-ct">
        <div class="leftFrameMenu menuTree">
            <ul>
                <c:forEach items="${technologies}" var="item">
                    <li class="child" url="/assembly/6/${item.key}"><a href="#">${item.value}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="accordion-hd"><h2><i class="acc-ico-01"></i>人力资源</h2></div>
    <div class="accordion-ct">
        <div class="leftFrameMenu menuTree">
            <ul>
                <c:forEach items="${talents}" var="item">
                    <li class="child" url="/assembly/7/${item.key}"><a href="#">${item.value}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="accordion-hd"><h2><i class="acc-ico-01"></i>服务中心</h2></div>
    <div class="accordion-ct">
        <div class="leftFrameMenu menuTree">
            <ul>
                <c:forEach items="${services}" var="item">
                    <li class="child" url="/assembly/8/${item.key}"><a href="#">${item.value}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>

</div>