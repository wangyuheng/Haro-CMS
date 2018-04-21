/**
 * Created by CR on 2015/9/22.
 */
function ajaxSubmit(options) {
    options.async = options.async||true;
    if (!(options.url)) {
        alert("您没有设置提交的地址！");
        return;
    }
    var vCeduAjaxFlag="1";
    if (options.updateId) {
        vCeduAjaxFlag="2";
    }
    var vUrl = options.url;
    var vParam;
    if (options.form) {
        vParam = $("#" + options.form).serialize();
    }
    if (options.params && !($.isEmptyObject(options.params))) {
        if (vParam && vParam.length) {
            vParam = vParam + "&" + $.param(options.params);
        } else {
            vParam = $.param(options.params);
        }
    }
    if (!(vParam)) {
        vParam = "rand=" + Math.random();
    } else {
        vParam = vParam + "&rand=" + Math.random();
    }

    if (options.onBefore) {
        options.onBefore();
    }

    $.ajax({
        type : 'POST',
        url : vUrl,
        data : vParam,
        beforeSend:function(XHR){XHR.setRequestHeader("CeduAjax",vCeduAjaxFlag);},
        success : function(data, textStatus) {
            if (options.updateId) {
                $("#" + options.updateId).html(data);
            }
            if (options.onSuccess) {
                options.onSuccess(data, textStatus);
            }
        },
        error : function(res, textStatus, errorThrown) {
            if (options.maskId) {
                $("#" + options.maskId).unmask();
            }
            if (options.onError) {
                options.onError(res, textStatus, errorThrown);
            }else if(res.status == 462){
                if (options.updateId) {
                    $("#" + options.updateId).html(res.responseText);
                }else{
                    alert("系统处理出现错误，请联系系统管理员！");
                }
            }
        }
    });
}
