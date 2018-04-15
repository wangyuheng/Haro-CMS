<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<link type="text/css" rel="stylesheet" href="/static/css/common/base-min.css">
<link type="text/css" rel="stylesheet" href="/static/css/common/core.css">
<link type="text/css" rel="stylesheet" href="/static/css/default/default.css">

<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;

    function forbidBackSpace(e) {
        // 获取event对象
        var ev = e || window.event;
        // 获取事件源
        var obj = ev.target || ev.srcElement;
        // 获取事件源类型
        var t = obj.type || obj.getAttribute('type');
        // 获取作为判断条件的事件类型
        var vReadOnly = obj.readOnly;
        var vDisabled = obj.disabled;
        // 处理undefined值情况
        vReadOnly = (vReadOnly === undefined) ? false : vReadOnly;
        vDisabled = (vDisabled === undefined) ? true : vDisabled;
        // 当敲Backspace键时，事件源类型为密码或单行、多行文本的，
        // 并且readOnly属性为true或disabled属性为true的，则退格键失效
        var flag1 = ev.keyCode === 8 && (t === "password" || t === "text" || t === "textarea") && (vReadOnly === true || vDisabled == true);
        // 当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
        var flag2 = ev.keyCode === 8 && t !== "password" && t !== "text" && t !== "textarea";
        // 判断
        if (flag2 || flag1) return false;
    }
</script>

<script src="/static/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
    var _basePath = '/';
</script>
<script src="/static/js/harobg-all.js"></script>
<script src="/static/js/harobg-UI.js"></script>
<script src="/static/js/common/lhgdialog/lhgdialog.min.js?skin=G3bg"></script>
<link type="text/css" rel="stylesheet" href="/static/js/common/mask/jquery.loadmask.css">
<script src="/static/js/common/mask/jquery.loadmask.min.js"></script>
<script src="/static/js/haro.js"></script>

