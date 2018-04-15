/**
 * 表单验证组件
 * @author houpo@chinaedu.net
 */
if (typeof Vt == 'undefined') {
    var Vt = {};
}
(function($) {
    Vt.format = function(source, params) {
        if (arguments.length === 1) {
            return function() {
                var args = $.makeArray(arguments);
                args.unshift(source);
                return Vt.format.apply(this, args);
            };
        }
        if (arguments.length > 2 && params.constructor !== Array) {
            params = $.makeArray(arguments).slice(1);
        }
        if (params.constructor !== Array) {
            params = [ params ];
        }
        $.each(params, function(i, n) {
            source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
        });
        return source;
    };
    
    Vt.encodeName = function(name){
        return name.replace(/\./g, "\\.").replace(/\[/g, '\\[').replace(/\]/g,
        '\\]');
    };

    Vt.validator = {
        defaults : {
            messages : {},
            elErrorClass : 'elError',
            errorClass : 'formError',
            validClass : 'valid',
            errorElement : 'div',
            infoElement : 'div',
            infoIdSuffix : '_info',//
            errorIdSuffix : '_error',
            focusInvalid : true,
            focusCleanup : true,
            validGroup : null,// 待验证的分组
            errorContainer : $([]),// 显示错误信息的容器
            errorLabelContainer : $([]),// 显示或者生成错误信息的位置 jquery对象
            errorPlacement : null,// 出错时，自定义显示错误信息的回调函数function(label,
            // element) eg.
            // validByJs.html
            successValid : null,// 对单个控件校验正确时回调的函数 function(label, element) eg.
            // validByJs.html
            validCallback : null,// 使用valid时，结束时要做的处理 errorsInps,successInps
            onsubmit : true,
            ignore : ":hidden",
            // ignoreTitle : false,
            // 得到焦点的处理
            onfocusin : function(element, event) {
                // console.log("onfocusin");
                if (this.settings.focusCleanup) {
                    var inp = new Vt.element($(element), this);
                    inp.showInfo();
                }
            },
            // 失去焦点的处理
            onfocusout : function(element, event) {
                // console.log("onfocusout");
                var inp = new Vt.element($(element), this);
                inp.validInp();
            },
            // 按键的处理
            onkeyup : function(element, event) {
                // var inp = new Vt.element($(element), this);
                // inp.validInp();
            },
            // 鼠标单击的处理
            onclick : function(element, event) {
                // console.log("onclick");
                var tmpInp = $(element);
                if (!tmpInp.is("[name][valid]")) {
                    if (tmpInp.is(":checkbox,:radio")) {
                        tmpInp = $("[name=\"" + Vt.encodeName(tmpInp.attr('name')) + "\"][valid]");
                    }
                }
                if (tmpInp.length) {
                    var inp = new Vt.element(tmpInp, this);
                    inp.validInp();
                }
            }
        },

        messages : {
            required : "该项为必填项。",
            email : "请输入一个有效的email地址。",
            url : "请输入一个有效的URL。",
            date : "请输入一个有效的日期。",
            dateISO : "请输入一个有效的(ISO)标准的日期。",
            number : "请输入一个有效的数值。",
            digits : "请输入一个有效的整数。",
            nonnegativeInt : "请输入一个有效的大于或等于0的整数。",
            equalTo : "请再次输入，两次输入的不一致。",
            maxlength : Vt.format("该项长度要小于等于 {0}字符。"),
            minlength : Vt.format("该项长度要大于等于 {0}字符。"),
            rangelength : Vt.format("该项长度要大于等于 {0} 小于等于 {1}。"),
            range : Vt.format("输入值应该大于等于 {0} 小于等于 {1}。"),
            max : Vt.format("输入的值要小于等于 {0}。"),
            min : Vt.format("输入的值要大于等于 {0}。"),
            regex : Vt.format("输入的值要匹配正则 {0}。"),
            zip : "请输入6位数字。",
            normalchar : '请输入字母、数字或者它们的组合。',
            username : '请输入字母或者字母和数字的组合，并且首位必须为字母。',
            telephone : '请输入有效的号码。',
            icard : '请输入有效的身份证件号。',
            realName : '请输入汉字或英文字母。'
        },
        setDefaults : function(settings) {
            $.extend(Vt.validator.defaults, settings);
        },
        // http://docs.jquery.com/Plugins/Validation/Validator/addMethod
        addMethod : function(name, method, message) {
            Vt.validator.methods[name] = method;
            Vt.validator.messages[name] = message !== undefined ? message
                    : $.validator.messages[name];
        },
        methods : {
            // http://docs.jquery.com/Plugins/Validation/Methods/required
            required : function(value, element, param, fromOptional) {
                param = param || true;
                if (!this.depend(param, element)) {
                    return true;
                }
                if (element.nodeName.toLowerCase() === "select") {
                    // could be an array for select-multiple or a string, both
                    // are
                    // fine this way
                    var val = $(element).val();
                    return (val && val.length > 0) == param;
                }
                if (this.checkable(element)) {
                    return (this.getLength(value, element) > 0) == param;
                }
                if(!fromOptional){
                	value = $.trim(value);
                }
                return value.length > 0 || param == false;
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/minlength
            minlength : function(value, element, param) {
                var length = $.isArray(value) ? value.length : this.getLength($
                        .trim(value), element);
                return this.optional(element) || length >= param;
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/maxlength
            maxlength : function(value, element, param) {
                var length = $.isArray(value) ? value.length : this.getLength($
                        .trim(value), element);
                return this.optional(element) || length <= param;
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/rangelength
            rangelength : function(value, element, param) {
                var length = $.isArray(value) ? value.length : this.getLength($
                        .trim(value), element);
                return this.optional(element)
                        || (length >= param[0] && length <= param[1]);
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/min
            min : function(value, element, param) {
                return this.optional(element) || 1 * value >= 1 * param;
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/max
            max : function(value, element, param) {
                return this.optional(element) || 1 * value <= 1 * param;
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/range
            range : function(value, element, param) {
                return this.optional(element) || (value >= param[0] && value <= param[1]);
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/email
            email : function(value, element) {
                // contributed by Scott Gonzalez:
                // http://projects.scottsplayground.com/email_address_validation/
                return this.optional(element)
                        || /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i
                                .test(value);
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/url
            url : function(value, element) {
                // contributed by Scott Gonzalez:
                // http://projects.scottsplayground.com/iri/
                return this.optional(element)
                        || /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i
                                .test(value);
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/date
            date : function(value, element) {
                return this.optional(element) || !/Invalid|NaN/.test(new Date(value));
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/dateISO
            dateISO : function(value, element) {
                return this.optional(element)
                        || /^\d{4}[\/\-]\d{1,2}[\/\-]\d{1,2}$/.test(value);
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/number
            number : function(value, element) {
                return this.optional(element)
                        || /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value);
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/digits
            digits : function(value, element) {
                return this.optional(element) || /^\d+$/.test(value);
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/creditcard
            // based on http://en.wikipedia.org/wiki/Luhn
            regex : function(value, element, param) {
                return this.optional(element) || param.test(value);
            },

            // 电话较验
            telephone : function(value, element) {
                return this.optional(element)
                        || /^(\+?)[0-9\-]{7,18}$/.test(value);
            },
            // 身份证件号验证
            icard : function(value, element) {
                return this.optional(element)
                        || /^((\d{15})|(\d{18})|(\d{17}x))$/i.test(value);
            },

            // 邮政编码
            zip : function(value, element) {
                return this.optional(element) || /^\d{6}$/.test(value);
            },

            // 字母和数字
            normalchar : function(value, element) {
                return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
            },
            
            // 用户名
            username : function(value, element) {
                return this.optional(element) || /^[a-zA-Z]{1}[a-zA-Z0-9]*$/.test(value);
            },
            
            realName : function(value, element) {
            	return this.optional(element) || /^[\u4e00-\u9fa5a-z]+$/gi.test(value);
            },
            
            nonnegativeInt : function(value, element) {
                return this.optional(element) || /^(0|[1-9]\d*)$/.test(value);
            },

            // http://docs.jquery.com/Plugins/Validation/Methods/equalTo
            equalTo : function(value, element, param) {
                var target = $(param);
                if (this.settings.onfocusout) {
                    var nowInp = this;
                    target.unbind(".validate-equalTo").bind("blur.validate-equalTo",
                            function() {
                                new Vt.element(nowInp.El, nowInp.form).validInp();
                            });
                }
                return value === target.val();
            }
        }

    };
    /**
     * 需要验证的控件 inp: 待验证的单个控件(jQuery) form: 控件所在的表单(jQuery)
     */
    Vt.element = function(inp, form, options) {
        var validAttr = inp.attr('valid');
        this.El = inp;// 把当前对象绑定为 El
        this.form = form || inp.parents('form');
        this.name = inp.attr('name');
        if (!form.length) {
            this.form = $('body');
        }
        this.rules = {};
        if (validAttr) {
            try {
                this.rules = eval("(" + validAttr + ")");// 获取所有的验证的规则
            } catch (e) {
                if (window.console) {
                    console.log('name为"' + this.name + '"的控件，valid表达式书写有误！');
                }
                throw e;
            }
        }
        var formOptions = this.form.data("validator.options");
        var inpOptions = this.rules.options;// 属性值里配置的options
        if (inpOptions) {
            delete this.rules.options;
        }
        if (this.rules.group) {// 设置该控件所在的组
            this.group = this.rules.group;
            delete this.rules.group;
        }
        this.settings = $.extend(true, {}, Vt.validator.defaults, formOptions, options,
                inpOptions);
    }

    Vt.element.prototype = {
        // 对当前单个控件进行校验
        validInp : function() {
            var result = true;
            var val = this.elementValue();
            if (this.isInGroup()) {// 判断该控件是否在要验证的组中
                for ( var method in this.rules) {
                    var parameters = this.rules[method];
                    var methodParam = parameters;
                    var msg = null;
                    if ($.isArray(parameters)) {
                        methodParam = parameters[0];
                        if (parameters.length > 1) {
                            msg = parameters[1];
                        }
                    }
                    try {
                        result = Vt.validator.methods[method].call(this, val, this.El[0],
                                methodParam);
                    } catch (e) {
                        if (window.console) {
                            console.log("exception occured when checking element "
                                    + this.El[0].id + ", check the '" + method
                                    + "' method", e);
                        }
                        throw e;
                    }
                    if (!result) {
                        var msgInfoOrFun = msg || Vt.validator.messages[method];
                        this.errorInfo = {
                            result : true,
                            msg : (typeof msgInfoOrFun == 'function') ? msgInfoOrFun(methodParam)
                                    : msgInfoOrFun
                        };// 记录当前控件的错误信息
                        break;
                    }
                }
            }
            var label = this.getErrorLabel();
            if (this.errorInfo) {
                this.showError();
                label = this.getErrorLabel();
                if (this.settings.errorPlacement) {
                    this.settings.errorPlacement.call(this, label, this.El);
                }
                if (this.settings.errorContainer.length) {
                    this.settings.errorContainer.show();
                }
                return false;
            } else {
                this.hideError();
            }
            return true;
        },
        // 判断当前控件是否在要验证的组里边
        isInGroup : function() {
            var vGroup = this.settings.validGroup;
            if (vGroup) {
                if (!this.group) {
                    return false;
                } else {
                    if (!$.isArray(this.group) && vGroup != this.group) {
                        return false;
                    } else if ($.isArray(this.group)) {
                        for ( var i = 0; i < this.group.length; i++) {
                            if (this.group[i] == vGroup) {
                                return true;
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        },

        // 获取显示错误信息的标签
        getErrorLabel : function() {
            return $(this.settings.errorElement
                    + "#"
                    + Vt.encodeName(this.name) + this.settings.errorIdSuffix);
        },
        // 获取显示tip的标签
        getInfoLabel : function() {
            return $(this.settings.infoElement
                    + "#"
                    + Vt.encodeName(this.name) + this.settings.infoIdSuffix);
        },

        showInfo : function() {
            var label = this.getInfoLabel();
            label && label.length ? label.show() : null;
            var errLabel = this.getErrorLabel();
            (errLabel && errLabel.length) ? errLabel.hide() : null;
        },

        hideInfo : function() {
            var infoLabel = this.getInfoLabel();
            (infoLabel && infoLabel.length) ? infoLabel.hide() : null;
        },

        showError : function() {
            this.El.addClass(this.settings.elErrorClass);
            this.El.removeClass(this.settings.validClass);
            var label = this.getErrorLabel();
            if (!label || !label.length) {
                // 生成一个新的label
                label = $("<" + this.settings.errorElement + "/>").attr({
                    "id" : this.name + this.settings.errorIdSuffix,
                    generated : true
                }).addClass(this.settings.errorClass);
                if (!this.settings.errorLabelContainer.length) {
                    label.insertAfter(this.El);
                } else {
                    this.settings.errorLabelContainer.append(label);
                }
            }
            label.html(this.errorInfo.msg);
            label.attr("style","display:inline-block; _zoom:1;_display:inline;");
            this.hideInfo();
        },

        hideError : function() {
            this.El.addClass(this.settings.validClass);
            this.El.removeClass(this.settings.elErrorClass);
            var label = this.getErrorLabel();
            (label && label.length) ? label.hide() : null;
            if (this.settings.successValid) {
                this.settings.successValid.call(this, label, this.El);
            }
        },

        // 获取当前控件的值
        elementValue : function() {
            var type = this.El.attr('type'), val = this.El.val();

            if (type === 'radio' || type === 'checkbox') {
                return $('input[name="' + Vt.encodeName(this.El.attr('name')) + '"]:checked').val();
            }
            if (typeof val === 'string') {
                return val.replace(/\r/g, "");
            }
            return val;
        },

        checkable : function(element) {
            return (/radio|checkbox/i).test(element.type);
        },

        optional : function() {
            var val = this.elementValue();
            return !Vt.validator.methods.required.call(this, val, this.El[0], true, true);
        },

        findByName : function(name) {
            return $(this.form).find('[name="' + Vt.encodeName(name) + '"]');
        },

        getLength : function(value, element) {
            switch (element.nodeName.toLowerCase()) {
                case 'select':
                    return $("option:selected", element).length;
                case 'input':
                    if (this.checkable(element)) {
                        return this.findByName(element.name).filter(':checked').length;
                    }
            }
            return value.length;
        },

        depend : function(param, element) {
            return this.dependTypes[typeof param] ? this.dependTypes[typeof param](param,
                    this) : true;
        },

        dependTypes : {
            "boolean" : function(param, element) {
                return param;
            },
            "string" : function(param, element) {
                return !!$(param, element.form).length;
            },
            "function" : function(param, element) {
                return param(element);
            }
        }

    };

})(jQuery);

(function($) {
    // only implement if not provided by jQuery core (since 1.4)
    // TODO verify if jQuery 1.4's implementation is compatible with older
    // jQuery
    // special-event APIs
    if (!jQuery.event.special.focusin && !jQuery.event.special.focusout
            && document.addEventListener) {
        $.each({
            focus : 'focusin',
            blur : 'focusout'
        }, function(original, fix) {
            $.event.special[fix] = {
                setup : function() {
                    this.addEventListener(original, handler, true);
                },
                teardown : function() {
                    this.removeEventListener(original, handler, true);
                },
                handler : function(e) {
                    var args = arguments;
                    args[0] = $.event.fix(e);
                    args[0].type = fix;
                    return $.event.handle.apply(this, args);
                }
            };
            function handler(e) {
                e = $.event.fix(e);
                e.type = fix;
                return $.event.handle.call(this, e);
            }
        });
    }
    $
            .extend(
                    $.fn,
                    {
                        // 对form进行自动验证
                        validate : function(options) {
                            if (options) {
                                this.data("validator.options", options);// 把options包存在form上
                            }
                            function delegate(event) {
                                var eventType = "on" + event.type;
                                var validator = {};
                                validator.settings = $.extend(true, {},
                                        Vt.validator.defaults, options);
                                event.data.form.settings = validator.settings;
                                if (validator.settings[eventType]) {
                                    validator.settings[eventType].call(event.data.form,
                                            this, event, options);
                                }
                            }
                            this
                                    .delegate(
                                            ":text, [type='password'], [type='file'], select, textarea, "
                                                    + "[type='number'], [type='search'] ,[type='tel'], [type='url'], "
                                                    + "[type='email'], [type='datetime'], [type='date'], [type='month'], "
                                                    + "[type='week'], [type='time'], [type='datetime-local'], "
                                                    + "[type='range'], [type='color'] ",
                                            "focusin focusout keyup", {
                                                form : this,
                                                options : options
                                            }, delegate)
                                    .delegate(
                                            "[type='radio'], [type='checkbox'], select, option",
                                            "click", {
                                                form : this,
                                                options : options
                                            }, delegate);

                            if (this.is("form")) {
                                this.submit(function() {
                                    return $(this).valid();
                                });
                            }
                        },
                        // 对所有控件进行验证，有一个失败就返回false
                        valid : function(options) {
                            // if (!this.is(":input")) {
                            // this.validate(options);
                            // }
                            var errorList = [];
                            var successList = [];
                            var willValidInps = this;
                            var form = $('body');
                            if (!this.eq(0).is(":input:not(:button)")) {// 只是对单个控件的校验
                                var ignore = options ? options.ignore
                                        : Vt.validator.defaults.ignore;
                                willValidInps = this.find(":input:not(:button)[valid]")
                                        .not(":submit, :reset, :image, [disabled]").not(
                                                ignore);
                                form = this;
                            }
                            willValidInps.each(function() {
                                var ele = new Vt.element($(this), form, options);
                                ele.validInp();
                                if (ele.errorInfo) {
                                    errorList.push(ele);
                                } else {
                                    successList.push(ele);
                                }
                            });

                            var errorContainer = Vt.validator.defaults.errorContainer;
                            var formOptions = form.data('validator.options');
                            if (options && options.errorContainer) {
                                errorContainer = options.errorContainer;
                            } else if (formOptions && formOptions.errorContainer) {
                                errorContainer = formOptions.errorContainer;
                            }
                            if (!errorList.length && !errorContainer.length) {
                                errorContainer.hide();
                            }
                            var validCallback = null;
                            validCallback = options && options.validCallback ? options.validCallback
                                    : validCallback;
                            if (!validCallback) {
                                validCallback = formOptions && formOptions.validCallback ? formOptions.validCallback
                                        : validCallback;
                            }
                            if (validCallback) {
                                validCallback.call(this, errorList, successList);
                            }
                            if(errorList.length > 0){
                            	errorList[0].El.focus();
                            }
                            return errorList.length == 0;
                        }
                    });

    // 对于存在valid属性的form进行自动验证
    $(function() {
        $('form[valid]').each(function() {
            var options = {};
            var $this = $(this);
            if ($this.attr('valid')) {
                options = eval('(' + $this.attr('valid') + ')');
            }
            $this.validate(options);
        });
    });
}(jQuery));