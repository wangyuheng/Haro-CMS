<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<link type="text/css" rel="stylesheet" href="/static/style/core.css">

<link type="text/css" rel="stylesheet" href="/static/images/common.css">
<link type="text/css" rel="stylesheet" href="/static/images/style.css">

<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/core.js"></script>
<script src="/static/js/menu.js"></script>


<script type="text/javascript">
    var _basePath = '/';
    var Menu = {
        companyRoute: function (routeKey) {
            location.href = _basePath + "company#" + routeKey;
        }
    };
</script>



