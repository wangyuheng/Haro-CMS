var basePath;
/**
 * 判断字符串是否是以某字符串开头
 * 
 * @author houpo@chinaedu.net
 */
String.prototype.startWith = function(str, ignoreCase) {
    if (typeof str == 'undefined' || this.length < str.length) {
        return false;
    }
    var startStr = this.substr(0, str.length);
    if (ignoreCase) {
        startStr = startStr.toLowerCase();
        str = str.toLowerCase();
    }
    if (startStr == str) {
        return true;
    }
    return false;
}

/**
 * 判断字符串是否是以某字符串结尾
 * 
 * @author houpo@chinaedu.net
 */
String.prototype.endWith = function(str, ignoreCase) {
    if (typeof str == 'undefined' || this.length < str.length) {
        return false;
    }
    var endStr = this.substring(this.length - str.length);
    if (ignoreCase) {
        endStr = endStr.toLowerCase();
        str = str.toLowerCase();
    }
    if (endStr == str) {
        return true;
    }
    return false;
}

String.prototype.trim = function() {
    return $.trim(this)
}

String.prototype.isNumber = function() {
    var pattern = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
    return pattern.test(this);
}

String.prototype.toDate = function() {
    var result;
    var vSrc = this;
    if (!this.isEmpty()) {
        var r = new RegExp(
                "^[1-2]\\d{3}-(0?[1-9]||1[0-2])-(0?[1-9]||[1-2][1-9]||3[0-1])$");
        if (r.test(this)) {
            var arr = vSrc.split("-");
            result = new Date(arr[0], arr[1], arr[2], 0, 0, 0);
        }
    }
    return result;
}

String.prototype.toDateTime = function() {
    var result;
    var vSrc = this;
    if (!this.isEmpty()) {
        var r = new RegExp(
                "^[1-2]\\d{3}-(0?[1-9]||1[0-2])-(0?[1-9]||[1-2][0-9]||3[0-1]) ((0)?[0-9]||1[0-9]||2[0-4]):((0)?[0-9]||[1-5][0-9]):((0)?[0-9]||[1-5][0-9])$");
        if (r.test(this)) {
            var arr = vSrc.replace(/[^\d]/g, "-").split("-");
            result = new Date(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
        }
    }

    return result;
}

String.prototype.isDigit = function() {
    return /^\d+$/.test(this);
}

String.prototype.isEmpty = function() {
    var vTemp = $.trim(this);
    return vTemp == "";
}

String.prototype.isEmail = function() {
    var vTemp = $.trim(this);
    var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    return pattern.test(vTemp);
}

String.prototype.isPostalcode = function() {
    var vTemp = $.trim(this);
    var pattern = /^[1-9]\d{5}$/;
    return pattern.test(vTemp);
}

String.prototype.isPhone = function() {
    var vTemp = $.trim(this);
    var pattern = /^(\d{3}\-)?(0\d{2,3}-)?[1-9]\d{6,7}$/;
    return pattern.test(vTemp);
}

String.prototype.isMobile = function() {
    var vTemp = $.trim(this);
    var pattern = /^1[3,5,8]{1}[0-9]{1}[0-9]{8}$/;
    return pattern.test(vTemp);
}

String.prototype.isLetter = function() {
    var vTemp = $.trim(this);
    var pattern = /^[A-Za-z]+$/;
    return pattern.test(vTemp);
}

String.prototype.isChinese = function() {
    var vTemp = $.trim(this);
    var pattern = /^[\u4e00-\u9fa5]+$/;
    return pattern.test(vTemp);
}

String.prototype.isEnglishChinese = function() {
    var vTemp = $.trim(this);
    var pattern = /^[A-Za-z\u4e00-\u9fa5 ]+$/;
    return pattern.test(vTemp);
}

String.prototype.isLetterOrNumber = function() {
    var vTemp = $.trim(this);
    var pattern = /^[A-Za-z0-9]+$/;
    return pattern.test(vTemp);
}

String.prototype.isLetterOrNumberOrUnderline = function() {
    var vTemp = $.trim(this);
    var pattern = /^\w+$/;
    return pattern.test(vTemp);
}

String.prototype.isCurrency = function() {
    var vTemp = $.trim(this);
    var pattern = /^\d+(\.\d{1,2})?$/;
    return pattern.test(vTemp);
}

String.prototype.containIllegalChar = function(vPattern) {
    var express = "[`~!@#$^&*()=|{}':;',\\[\\].<>/?]";
    if (vPattern) {
        express = vPattern;
    }
    var vTemp = $.trim(this);
    var pattern = new RegExp(express);
    return pattern.test(vTemp);
}

String.prototype.getActualLength = function() {
    var i;
    var len = 0;
    for (i = 0; i < this.trim().length; i++) {
        if (this.charCodeAt(i) > 255) {
            len += 2;
        } else {
            len++;
        }
    }
    return len;
}

function onKeyUpDigits(obj) {
    var srcValue = $(obj).val();
    var realValue = srcValue.replace(/[^\d]/g, "")
    $(obj).val(realValue);
}

function onKeyUpLetter(obj) {
    var srcValue = $(obj).val();
    var realValue = srcValue.replace(/[^a-zA-Z]*/g, "")
    $(obj).val(realValue);
}

function limitLength(obj, maxLength) {
    var srcValue = $(obj).val().trim();
    if (srcValue.length > maxLength) {
        $(obj).val(srcValue.substring(0, maxLength));
    }
}

function makeAlertAndFocus(obj, message) {
    alert(message);
    $(obj).focus();
    $(obj).select();
}

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

    if (options.maskId) {
        var vMaskLabel = "正在执行操作，请稍候.....!";
        if (options.maskLabel) {
            vMaskLabel = options.maskLabel;
        }
        $("#" + options.maskId).mask(vMaskLabel);
    }
    
    $.ajax({
        type : 'POST',
        url : vUrl,
        data : vParam,
        beforeSend:function(XHR){XHR.setRequestHeader("CeduAjax",vCeduAjaxFlag);},
        success : function(data, textStatus) {
            if (options.maskId) {
                $("#" + options.maskId).unmask();
            }
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

    if (options.onAfter) {
        options.onAfter();
    }
}

function showValInfo(infos) {
    if (infos && !($.isEmptyObject(infos))) {
        $.each(infos, function(key, value) {
            $("#" + key + "_info").hide();
            $("#" + key + "_error").val(value).show();
        })
    }
}

function dialSuccess(pJq, succInfo) {
    pJq("#qryFlg").val("1");
    if (succInfo) {
        pJq.dialog.tips(succInfo, 1, 'succ.png');
    } else {
        pJq.dialog.tips('保存成功！', 1, 'succ.png');
    }
}

function dialError(pJq, request, textStatus, errorThrown, failInfo) {
    if (request.status == 460 && request.responseText) {
        pJq.dialog.tips('业务处理不成功，请注意提示信息！', 1, 'fail.png');
        var infos = eval('(' + request.responseText + ')');
        $.each(infos, function(key, value) {
            var key2 = key.replace(/\./g, "\\.").replace(/\[/g, '\\[').replace(/\]/g,
            '\\]');
            $("#" + key2 + "_info").hide();
            if (!$("#" + key2 + "_error").length) {
                createError(key);
            }
            $("#" + key2 + "_error").html(value).show();
        })

    } else if (request.status == 461) {
        if (request.responseText == "-1") {
            pJq.dialog.tips('您没有修改任何数据！', 2, 'fail.png');
        } else if (request.responseText == "0") {
            pJq.dialog.tips('保存失败！', 2, 'fail.png');
        } else {
            pJq.dialog.tips(request.responseText, 2, 'fail.png');
        }
    }
}
// 生成显示错误的标签
function createError(key) {
    var label = $("<div/>").attr({
        "id" : key + "_error",
        generated : true
    }).addClass("formError");
    label.attr("style","display:inline-block; _zoom:1;_display:inline;");
    label.insertAfter($(':input[name="' + key + '"]'));
}

function ajaxPaging(vUrl, formId, updateId, pageNo) {
    var params = {
        rand : Math.random(),
        pageNo : pageNo
    };
    if (formId) {
        var JformId = "#" + formId;
        params = $(JformId).serialize();
        if (params) {
            params = params + "&pageNo=" + pageNo;
        } else {
            params = "pageNo=" + pageNo;
        }
    }
    $.ajax({
        type : 'POST',
        url : vUrl,
        data : params,
        success : function(data, textStatus) {
            if (updateId) {
                $("#" + updateId).html(data);
            }
        }
    });
}

function postPersonRecord(action) {
    if (personRecord && personRecord.url) {
        var index = action.indexOf(".do");
        var vAction = action.substring(0, index);
        alert(vAction.replace("/", "_"));
        var vParams = {
            user : personRecord.userId,
            action : vAction.replace("/", "_")
        }
        $.ajax({
            type : 'POST',
            url : personRecord.url,
            data : vParams
        });
    }
}

function submitTo(vUrl) {
    document.forms[0].action = vUrl;
    document.forms[0].submit();
}

function goTo(vUrl) {
    window.location.href = vUrl;
}

function batchDelete(tag, callback) {
    var str = getValues(tag);
    if (str == "") {
        alert("请选择要删除的数据项!");
        return false;
    }
    if (confirm('您确定要删除选取的项吗?')) {
        callback(str);
        return true;
    }
    return false;
}

function getValues(tag) {
    var str = "";
    var idx = 0;
    $("input[name='" + tag + "']:checked").each(function() {
        if (idx > 0) {
            str = str + ",";
        }
        idx++;
        str = str + $(this).val();
    });
    return str;
}

function checkParent(strAllChoiceID, childName) {
    var selector = "input[type='checkbox'][name='" + childName + "']"
    var elms = $(selector);
    var vObj = document.getElementById(strAllChoiceID);
    var isCheckAll = true;
    if (vObj.type == "checkbox") {
        for (i = 0; i < elms.length; i++) {
            if (!elms[i].checked && !elms[i].disabled) {
                isCheckAll = false;
                break;
            }
        }
        vObj.checked = isCheckAll;
    }
}

function checkChildren(spanChk, childName) {
    if (spanChk.type == "checkbox") {
        var xState = spanChk.checked;
        var selector = "input[type='checkbox'][name='" + childName + "']"
        var elms = $(selector);
        var i = 0;
        for (i = 0; i < elms.length; i++) {
            if (!elms[i].disabled) {
                elms[i].checked = xState
            }
        }
    }
}

function getRadioValue(vName){
	var vResult = "";
	$("input[type='radio'][name='"+vName+"']:checked").each(function(vIdx, vObj){
		 vResult = $(vObj).val();
         return false;	                        
     });
	return vResult;
}

/**
 * 构造select的option
 * 
 * @author houpo@chinaedu.net
 * @param sel
 * @param list
 * @param codeName
 * @param labelName
 * @param selectValue
 */
function renderSelect(option) {
    var defaults = {
        code : 'id',// 值
        label : 'name',// 名
        empty : [ false, '', '请选择' ],// 是否显示空项
        append : true,// 是追加的方式
        select : null,// 要操作的select
        list : [],// 要处理的集合
        value : null
    // 选择的值
    };

    var config = $.extend(true, {}, defaults, option);
    if (typeof config.empty === "boolean") {
        config.empty = [ config.empty, '', '请选择' ];
    }
    var list = config.list;

    var select = typeof (config.select) == "string" ? $('#' + config.select)
            : config.select;
    if (!config.append) {
        select.empty();
    }
    if (config.empty[0]) {// 添加空项
        _selectAddOption(select[0], config.empty[1], config.empty[2]);
    }
    if (list && list.length) {
        for ( var i = 0; i < list.length; i++) {
            _selectAddOption(select[0], list[i][config.code], list[i][config.label])
        }
    }
    if (config.value != null) {
        select.val(config.value);
    }
    return select;
}

function _selectAddOption(select, value, text) {
    var y = document.createElement('option');
    y.text = text;
    y.value = value;
    try {
        select.add(y, null);
    } catch (ex) {
        select.add(y);
    }
}

function passwordGrade(pwd) {
    var score = 0;
    var regexArr = ['[0-9]','[a-z]','[A-Z]','[\\W_]'];
    var repeatCount = 0;
    var prevChar = '';
    //check length
    var len = pwd.length;
    score += len > 18 ? 18 : len;
    //check type
    var num = regexArr.length;
    for (var i = 0; i < num; i++) { 
    	if (eval('/' + regexArr[i] + '/').test(pwd)) {
    		score += 4; 
    	}
    }
    //bonus point
    for (var i = 0; i < num; i++) {
    	var arrValues =pwd.match(eval('/' + regexArr[i] + '/g'));
    	if(arrValues){
    		if(arrValues.length >= 2){
    			score += 2;
    		}
    		if(arrValues.length >= 5){
    			score += 2;
    		}
    	}
    }
    //deduction
    for (var i = 0; i < num; i++) {
        if (pwd.charAt(i) == prevChar){
        	repeatCount++;
        }else{
        	prevChar = pwd.charAt(i);
        }
    }
    score -= repeatCount * 1;
    return score;
}

function holdPaged(vUrl, vFormId, vPageNo, callback) {
	ajaxSubmit({ 
		url : vUrl,
		form : vFormId,
		params : {"pageNo" : vPageNo},
		onSuccess : function() {
			callback();
			return true;
		},
		onError : function() {
			return false;
		}
	});
}