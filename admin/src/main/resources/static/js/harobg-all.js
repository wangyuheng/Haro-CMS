/**
* jQuery ligerUI 1.1.9
* 
* http://ligerui.com
*  
* Author daomi 2012 [ gd_star@163.com ] 
* 
*/
(function ($)
{


    //ligerui 继承方法
    Function.prototype.ligerExtend = function (parent, overrides)
    {
        if (typeof parent != 'function') return this;
        //保存对父类的引用
        this.base = parent.prototype;
        this.base.constructor = parent;
        //继承
        var f = function () { };
        f.prototype = parent.prototype;
        this.prototype = new f();
        this.prototype.constructor = this;
        //附加属性方法
        if (overrides) $.extend(this.prototype, overrides);
    };

    // 核心对象
    window.liger = $.ligerui = {
        version: 'V1.1.9',
        managerCount: 0,
        //组件管理器池
        managers: {},
        managerIdPrev: 'ligerui',
        //错误提示     
     
    
        //获取ligerui对象
        //1,传入ligerui ID
        //2,传入Dom Object Array(jQuery)
        get: function (arg, idAttrName)
        {
            idAttrName = idAttrName || "ligeruiid";
            if (typeof arg == "string" || typeof arg == "number")
            {
                return $.ligerui.managers[arg];
            }
            else if (typeof arg == "object" && arg.length)
            {
                if (!arg[0][idAttrName] && !$(arg[0]).attr(idAttrName)) return null;
                return $.ligerui.managers[arg[0][idAttrName] || $(arg[0]).attr(idAttrName)];
            }
            return null;
        },
        //根据类型查找某一个对象
   
        //$.fn.liger{Plugin} 和 $.fn.ligerGet{Plugin}Manager
        //会调用这个方法,并传入作用域(this)
        //@parm [plugin]  插件名
        //@parm [args] 参数(数组)
        //@parm [ext] 扩展参数,定义命名空间或者id属性名
        run: function (plugin, args, ext)
        {
            if (!plugin) return;
            ext = $.extend({
                defaultsNamespace: 'ligerDefaults',
                methodsNamespace: 'ligerMethods',
                controlNamespace: 'controls',
                idAttrName: 'ligeruiid',
                isStatic: false,
                hasElement: true,           //是否拥有element主体(比如drag、resizable等辅助性插件就不拥有)
                propertyToElemnt: null      //链接到element的属性名
            }, ext || {});
            plugin = plugin.replace(/^ligerGet/, '');
            plugin = plugin.replace(/^liger/, '');
            if (this == null || this == window || ext.isStatic)
            {
                if (!$.ligerui.plugins[plugin])
                {
                    $.ligerui.plugins[plugin] = {
                        fn: $['liger' + plugin],
                        isStatic: true
                    };
                }
                return new $.ligerui[ext.controlNamespace][plugin]($.extend({}, $[ext.defaultsNamespace][plugin] || {}, $[ext.defaultsNamespace][plugin + 'String'] || {}, args.length > 0 ? args[0] : {}));
            }
            if (!$.ligerui.plugins[plugin])
            {
                $.ligerui.plugins[plugin] = {
                    fn: $.fn['liger' + plugin],
                    isStatic: false
                };
            }
            if (/Manager$/.test(plugin)) return $.ligerui.get(this, ext.idAttrName);
            this.each(function ()
            {
                if (this[ext.idAttrName] || $(this).attr(ext.idAttrName))
                {
                    var manager = $.ligerui.get(this[ext.idAttrName] || $(this).attr(ext.idAttrName));
                    if (manager && args.length > 0) manager.set(args[0]);
                    //已经执行过 
                    return;
                }
                if (args.length >= 1 && typeof args[0] == 'string') return;
                //只要第一个参数不是string类型,都执行组件的实例化工作
                var options = args.length > 0 ? args[0] : null;
                var p = $.extend({}, $[ext.defaultsNamespace][plugin] || {}
                , $[ext.defaultsNamespace][plugin + 'String'] || {}, options || {});
                if (ext.propertyToElemnt) p[ext.propertyToElemnt] = this;
                if (ext.hasElement)
                {
                    new $.ligerui[ext.controlNamespace][plugin](this, p);
                }
                else
                {
                    new $.ligerui[ext.controlNamespace][plugin](p);
                }
            });
            if (this.length == 0) return null;
            if (args.length == 0) return $.ligerui.get(this, ext.idAttrName);
            if (typeof args[0] == 'object') return $.ligerui.get(this, ext.idAttrName);
            if (typeof args[0] == 'string')
            {
                var manager = $.ligerui.get(this, ext.idAttrName);
                if (manager == null) return;
                if (args[0] == "option")
                {
                    if (args.length == 2)
                        return manager.get(args[1]);  //manager get
                    else if (args.length >= 3)
                        return manager.set(args[1], args[2]);  //manager set
                }
                else
                {
                    var method = args[0];
                    if (!manager[method]) return; //不存在这个方法
                    var parms = Array.apply(null, args);
                    parms.shift();
                    return manager[method].apply(manager, parms);  //manager method
                }
            }
            return null;
        },

        //扩展
        //1,默认参数     
        //2,本地化扩展 
        defaults: {},
        //3,方法接口扩展
        methods: {},
        //命名空间
        //核心控件,封装了一些常用方法
        core: {},
        //命名空间
        //组件的集合
        controls: {},
        //plugin 插件的集合
        plugins: {}
    };


    //扩展对象
    $.ligerDefaults = {};

    //扩展对象
    $.ligerMethos = {};

    //关联起来
    $.ligerui.defaults = $.ligerDefaults;
    $.ligerui.methods = $.ligerMethos;

    //获取ligerui对象
    //@parm [plugin]  插件名,可为空



    //组件基类
    //1,完成定义参数处理方法和参数属性初始化的工作
    //2,完成定义事件处理方法和事件属性初始化的工作
    $.ligerui.core.Component = function (options)
    {
        //事件容器
        this.events = this.events || {};
        //配置参数
        this.options = options || {};
        //子组件集合索引
        this.children = {};
    };
    $.extend($.ligerui.core.Component.prototype, {
        __getType: function ()
        {
            return '$.ligerui.core.Component';
        },
        __idPrev: function ()
        {
            return 'ligerui';
        },

        //设置属性
        // arg 属性名    value 属性值 
        // arg 属性/值   value 是否只设置事件
        set: function (arg, value)
        {
            if (!arg) return;
            if (typeof arg == 'object')
            {
                var tmp;
                if (this.options != arg)
                {
                    $.extend(this.options, arg);
                    tmp = arg;
                }
                else
                {
                    tmp = $.extend({}, arg);
                }
                if (value == undefined || value == true)
                {
                    for (var p in tmp)
                    {
                        if (p.indexOf('on') == 0)
                            this.set(p, tmp[p]);
                    }
                }
                if (value == undefined || value == false)
                {
                    for (var p in tmp)
                    {
                        if (p.indexOf('on') != 0)
                            this.set(p, tmp[p]);
                    }
                }
                return;
            }
            var name = arg;
            //事件参数
        
            this.trigger('propertychange', arg, value);
            if (!this.options) this.options = {};
            this.options[name] = value;
            var pn = '_set' + name.substr(0, 1).toUpperCase() + name.substr(1);
            if (this[pn])
            {
                this[pn].call(this, value);
            }
            this.trigger('propertychanged', arg, value);
        },

        //获取属性
        get: function (name)
        {
            var pn = '_get' + name.substr(0, 1).toUpperCase() + name.substr(1);
            if (this[pn])
            {
                return this[pn].call(this, name);
            }
            return this.options[name];
        },

        hasBind: function (arg)
        {
            var name = arg.toLowerCase();
            var event = this.events[name];
            if (event && event.length) return true;
            return false;
        },

        //触发事件
        //data (可选) Array(可选)传递给事件处理函数的附加参数
        trigger: function (arg, data)
        {
            var name = arg.toLowerCase();
            var event = this.events[name];
            if (!event) return;
            data = data || [];
            if ((data instanceof Array) == false)
            {
                data = [data];
            }
            for (var i = 0; i < event.length; i++)
            {
                var ev = event[i];
                if (ev.handler.apply(ev.context, data) == false)
                    return false;
            }
        }
    });


    //界面组件基类, 
    //1,完成界面初始化:设置组件id并存入组件管理器池,初始化参数
    //2,渲染的工作,细节交给子类实现
    //@parm [element] 组件对应的dom element对象
    //@parm [options] 组件的参数
    $.ligerui.core.UIComponent = function (element, options)
    {
        $.ligerui.core.UIComponent.base.constructor.call(this, options);
        var extendMethods = this._extendMethods();
        if (extendMethods) $.extend(this, extendMethods);
        this.element = element;
        this._init();
        this._preRender();
        this.trigger('render');
        this._render();
        this.trigger('rendered');
        this._rendered();
    };
    $.ligerui.core.UIComponent.ligerExtend($.ligerui.core.Component, {
        __getType: function ()
        {
            return '$.ligerui.core.UIComponent';
        },
        //扩展方法
        _extendMethods: function ()
        {

        },
        _init: function ()
        {
            this.type = this.__getType();
            if (!this.element)
            {
                this.id = this.options.id || $.ligerui.getId(this.__idPrev());
            }
            else
            {
                this.id = this.options.id || this.element.id || $.ligerui.getId(this.__idPrev());
            }
            //存入管理器池
        

            if (!this.element) return;

            //读取attr方法,并加载到参数,比如['url']
            var attributes = this.attr();
            if (attributes && attributes instanceof Array)
            {
                for (var i = 0; i < attributes.length; i++)
                {
                    var name = attributes[i];
                    this.options[name] = $(this.element).attr(name);
                }
            }
            //读取ligerui这个属性，并加载到参数，比如 ligerui = "width:120,heigth:100"
            var p = this.options;
            if ($(this.element).attr("ligerui"))
            {
                try
                {
                    var attroptions = $(this.element).attr("ligerui");
                    if (attroptions.indexOf('{') != 0) attroptions = "{" + attroptions + "}";
                    eval("attroptions = " + attroptions + ";");
                    if (attroptions) $.extend(p, attroptions);
                }
                catch (e) { }
            }
        },
        //预渲染,可以用于继承扩展
        _preRender: function ()
        {

        },
        _render: function ()
        {

        },
        _rendered: function ()
        {
            if (this.element)
            {
                $(this.element).attr("ligeruiid", this.id);
            }
        },
        //返回要转换成ligerui参数的属性,比如['url']
        attr: function ()
        {
            return [];
        }
    });


    //表单控件基类
    $.ligerui.controls.Input = function (element, options)
    {
        $.ligerui.controls.Input.base.constructor.call(this, element, options);
    };

    $.ligerui.controls.Input.ligerExtend($.ligerui.core.UIComponent, {
        __getType: function ()
        {
            return '$.ligerui.controls.Input';
        },
        attr: function ()
        {
            return ['nullText'];
        },
        setValue: function (value)
        {
            return this.set('value', value);
        },
        getValue: function ()
        {
            return this.get('value');
        },
        setEnabled: function ()
        {
            return this.set('disabled', false);
        },
        setDisabled: function ()
        {
            return this.set('disabled', true);
        },
        updateStyle: function ()
        {

        }
    });




})(jQuery);

/**
* jQuery ligerUI 1.1.9
* 
* http://ligerui.com
*  
* Author daomi 2012 [ gd_star@163.com ] 
* 
*/
(function ($)
{
		
    $.fn.ligerLayout = function (options)
    {
        return $.ligerui.run.call(this, "ligerLayout", arguments);
    };

    $.fn.ligerGetLayoutManager = function ()
    {
        return $.ligerui.run.call(this, "ligerGetLayoutManager", arguments);
    };


    $.ligerDefaults.Layout = {
        topHeight: 50,
        bottomHeight: 50,
        leftWidth: 110,
        centerWidth: 300,
        rightWidth: 170,
        InWindow: true,     //是否以窗口的高度为准 height设置为百分比时可用
        heightDiff: 0,     //高度补差
        height: '100%',      //高度
        onHeightChanged: null,
        isLeftCollapse: false,      //初始化时 左边是否隐藏
        isRightCollapse: false,     //初始化时 右边是否隐藏
        allowLeftCollapse: true,      //是否允许 左边可以隐藏
        allowRightCollapse: true,     //是否允许 右边可以隐藏
        allowLeftResize: true,      //是否允许 左边可以调整大小
        allowRightResize: true,     //是否允许 右边可以调整大小
        allowTopResize: true,      //是否允许 头部可以调整大小
        allowBottomResize: true,     //是否允许 底部可以调整大小
        space: 3, //间隔 
        onEndResize: null,          //调整大小结束事件
        minLeftWidth: 80,            //调整左侧宽度时的最小允许宽度
        minRightWidth: 80           //调整右侧宽度时的最小允许宽度
    };

    $.ligerMethos.Layout = {};

    $.ligerui.controls.Layout = function (element, options)
    {
        $.ligerui.controls.Layout.base.constructor.call(this, element, options);
    };
    $.ligerui.controls.Layout.ligerExtend($.ligerui.core.UIComponent, {
        __getType: function ()
        {
            return 'Layout';
        },
        __idPrev: function ()
        {
            return 'Layout';
        },
        _extendMethods: function ()
        {
            return $.ligerMethos.Layout;
        },
        _render: function ()
        {
            var g = this, p = this.options;
            g.layout = $(this.element);
            g.layout.addClass("l-layout");
            g.width = g.layout.width();
            //top         
            //bottom          
            //left
            if ($("> div[position=left]", g.layout).length > 0)
            {
                g.left = $("> div[position=left]", g.layout).wrap('<div class="l-layout-left" style="left:0px;"></div>').parent();
                g.left.header = $('<div class="l-layout-header"><div class="l-layout-header-toggle"></div><div class="l-layout-header-inner"></div></div>');
                g.left.prepend(g.left.header);
                g.left.header.toggle = $(".l-layout-header-toggle", g.left.header);
                g.left.content = $("> div[position=left]", g.left);
                if (!g.left.content.hasClass("l-layout-content"))
                    g.left.content.addClass("l-layout-content");
                if (!p.allowLeftCollapse) $(".l-layout-header-toggle", g.left.header).remove();
                //set title
                var lefttitle = g.left.content.attr("title");
                if (lefttitle)
                {
                    g.left.content.attr("title", "");
                    $(".l-layout-header-inner", g.left.header).html(lefttitle);
                }
                //set width
                g.leftWidth = p.leftWidth;
                if (g.leftWidth)
                    g.left.width(g.leftWidth);
            }
            //center
            if ($("> div[position=center]", g.layout).length > 0)
            {
                g.center = $("> div[position=center]", g.layout).wrap('<div class="l-layout-center" ></div>').parent();
                g.center.content = $("> div[position=center]", g.center);
                g.center.content.addClass("l-layout-content");
                //set title
                var centertitle = g.center.content.attr("title");
                if (centertitle)
                {
                    g.center.content.attr("title", "");
                    g.center.header = $('<div class="l-layout-header"></div>');
                    g.center.prepend(g.center.header);
                    g.center.header.html(centertitle);
                }
                //set width
                g.centerWidth = p.centerWidth;
                
                if (g.centerWidth)
                    g.center.width(g.centerWidth);
            }
            //right       
            //lock
      
            //DropHandle
            g._addDropHandle();

            //Collapse
            g.isLeftCollapse = p.isLeftCollapse;
            g.isRightCollapse = p.isRightCollapse;
            g.leftCollapse = $('<div class="l-layout-collapse-left" style="display: none; "><div class="l-layout-collapse-left-toggle"></div></div>');
            g.rightCollapse = $('<div class="l-layout-collapse-right" style="display: none; "><div class="l-layout-collapse-right-toggle"></div></div>');
            g.layout.append(g.leftCollapse).append(g.rightCollapse);
            g.leftCollapse.toggle = $("> .l-layout-collapse-left-toggle", g.leftCollapse);
            g.rightCollapse.toggle = $("> .l-layout-collapse-right-toggle", g.rightCollapse);
            g._setCollapse();
            //init
            g._bulid();
            $(window).resize(function ()
            {
            	
                g._onResize();
                
            });

            g.set(p);
        },
        setLeftCollapse: function (isCollapse)
        {
            var g = this, p = this.options;
            if (!g.left) return false;
            g.isLeftCollapse = isCollapse;
            if (g.isLeftCollapse)
            {
                g.leftCollapse.show();
                g.leftDropHandle && g.leftDropHandle.hide();
                g.left.hide();
				
            }
            else
            {
                g.leftCollapse.hide();
                g.leftDropHandle && g.leftDropHandle.show();
                g.left.show();
            }
            g._onResize();
        },
        setRightCollapse: function (isCollapse)
        {
            var g = this, p = this.options;
            if (!g.right) return false;
            g.isRightCollapse = isCollapse;
            g._onResize();
            if (g.isRightCollapse)
            {
                g.rightCollapse.show();
                g.rightDropHandle && g.rightDropHandle.hide();
                g.right.hide();
            }
            else
            {
                g.rightCollapse.hide();
                g.rightDropHandle && g.rightDropHandle.show();
                g.right.show();
            }
            g._onResize();
        },
        _bulid: function ()
        {
            var g = this, p = this.options;
            $("> .l-layout-left .l-layout-header,> .l-layout-right .l-layout-header", g.layout).hover(function ()
            {
                $(this).addClass("l-layout-header-over");
            }, function ()
            {
                $(this).removeClass("l-layout-header-over");

            });
            $(".l-layout-header-toggle", g.layout).hover(function ()
            {
                $(this).addClass("l-layout-header-toggle-over");
            }, function ()
            {
                $(this).removeClass("l-layout-header-toggle-over");

            });
            $(".l-layout-header-toggle", g.left).click(function ()
            {
                g.setLeftCollapse(true);
            });
            $(".l-layout-header-toggle", g.right).click(function ()
            {
                g.setRightCollapse(true);
            });
            //set top
            g.middleTop = 0;
            if (g.top)
            {
                g.middleTop += g.top.height();
                g.middleTop += parseInt(g.top.css('borderTopWidth'));
                g.middleTop += parseInt(g.top.css('borderBottomWidth'));
                g.middleTop += p.space;
            }
            if (g.left)
            {
                g.left.css({ top: g.middleTop });
                g.leftCollapse.css({ top: g.middleTop });
            }
            if (g.center) g.center.css({ top: g.middleTop });
            if (g.right)
            {
                g.right.css({ top: g.middleTop });
                g.rightCollapse.css({ top: g.middleTop });
            }
            //set left
            if (g.left) g.left.css({ left: 0 });
            g._onResize();
            g._onResize();
        },
        _setCollapse: function ()
        {
            var g = this, p = this.options;
            g.leftCollapse.hover(function ()
            {
                $(this).addClass("l-layout-collapse-left-over");
            }, function ()
            {
                $(this).removeClass("l-layout-collapse-left-over");
            });
            g.leftCollapse.toggle.hover(function ()
            {
                $(this).addClass("l-layout-collapse-left-toggle-over");
            }, function ()
            {
                $(this).removeClass("l-layout-collapse-left-toggle-over");
            });
            g.rightCollapse.hover(function ()
            {
                $(this).addClass("l-layout-collapse-right-over");
            }, function ()
            {
                $(this).removeClass("l-layout-collapse-right-over");
            });
            g.rightCollapse.toggle.hover(function ()
            {
                $(this).addClass("l-layout-collapse-right-toggle-over");
            }, function ()
            {
                $(this).removeClass("l-layout-collapse-right-toggle-over");
            });
            g.leftCollapse.toggle.click(function ()
            {
                g.setLeftCollapse(false);
            });
            g.rightCollapse.toggle.click(function ()
            {
                g.setRightCollapse(false);
            });
            if (g.left && g.isLeftCollapse)
            {
                g.leftCollapse.show();
                g.leftDropHandle && g.leftDropHandle.hide();
                g.left.hide();
            }
            if (g.right && g.isRightCollapse)
            {
                g.rightCollapse.show();
                g.rightDropHandle && g.rightDropHandle.hide();
                g.right.hide();
            }
        },
        _addDropHandle: function ()
        {
            var g = this, p = this.options;
            if (g.left && p.allowLeftResize)
            {
                g.leftDropHandle = $("<div class='l-layout-drophandle-left'></div>");
                g.layout.append(g.leftDropHandle);
                g.leftDropHandle && g.leftDropHandle.show();
                g.leftDropHandle.mousedown(function (e)
                {
                    g._start('leftresize', e);
                });
            }
            if (g.right && p.allowRightResize)
            {
                g.rightDropHandle = $("<div class='l-layout-drophandle-right'></div>");
                g.layout.append(g.rightDropHandle);
                g.rightDropHandle && g.rightDropHandle.show();
                g.rightDropHandle.mousedown(function (e)
                {
                    g._start('rightresize', e);
                });
            }
            if (g.top && p.allowTopResize)
            {
                g.topDropHandle = $("<div class='l-layout-drophandle-top'></div>");
                g.layout.append(g.topDropHandle);
                g.topDropHandle.show();
                g.topDropHandle.mousedown(function (e)
                {
                    g._start('topresize', e);
                });
            }
            if (g.bottom && p.allowBottomResize)
            {
                g.bottomDropHandle = $("<div class='l-layout-drophandle-bottom'></div>");
                g.layout.append(g.bottomDropHandle);
                g.bottomDropHandle.show();
                g.bottomDropHandle.mousedown(function (e)
                {
                    g._start('bottomresize', e);
                });
            }
            g.draggingxline = $("<div class='l-layout-dragging-xline'></div>");
            g.draggingyline = $("<div class='l-layout-dragging-yline'></div>");
            g.layout.append(g.draggingxline).append(g.draggingyline);
        },
    
        _onResize: function (c)
        { 
        	
            var g = this, p = this.options;
            var oldheight = g.layout.height();
            //set layout height 
            var h = 0;
            var windowHeight = $(window).height();
         
            var parentHeight = null;
            if (typeof (p.height) == "string" && p.height.indexOf('%') > 0)
            {
                var layoutparent = g.layout.parent();
                if (p.InWindow || layoutparent[0].tagName.toLowerCase() == "body")
                {
                    parentHeight = windowHeight;
                    parentHeight -= parseInt($('body').css('paddingTop'));
                    parentHeight -= parseInt($('body').css('paddingBottom'));
                }
                else
                {
                    parentHeight = layoutparent.height();
                }
                h = parentHeight * parseFloat(p.height) * 0.01;
                if (p.InWindow || layoutparent[0].tagName.toLowerCase() == "body")
                    h -= (g.layout.offset().top - parseInt($('body').css('paddingTop')));
            }
            else
            {
                h = parseInt(p.height);
            }
			if(c==50){
			p.heightDiff=50;
			}else{
			
			}
			if(c==0){
			p.heightDiff=-50;
			}
			
			h += p.heightDiff;
            g.layout.height(h);
            g.layoutHeight = g.layout.height();
           
            g.middleWidth = g.layout.width();
            g.middleHeight = g.layout.height();
          
            //specific
            g.middleHeight -= 2;

            if (g.hasBind('heightChanged') && g.layoutHeight != oldheight)
            {
                g.trigger('heightChanged', [{ layoutHeight: g.layoutHeight, diff: g.layoutHeight - oldheight, middleHeight: g.middleHeight}]);
            }

            if (g.center)
            {
                g.centerWidth = g.middleWidth;
                if (g.left)
                {
                    if (g.isLeftCollapse)
                    {
                        g.centerWidth -= g.leftCollapse.width();
                        g.centerWidth -= parseInt(g.leftCollapse.css('borderLeftWidth'));
                        g.centerWidth -= parseInt(g.leftCollapse.css('borderRightWidth'));
                        g.centerWidth -= parseInt(g.leftCollapse.css('left'));
                        g.centerWidth -= p.space;
                    }
                    else
                    {
                        g.centerWidth -= g.leftWidth;
                        g.centerWidth -= parseInt(g.left.css('borderLeftWidth'));
                        g.centerWidth -= parseInt(g.left.css('borderRightWidth'));
                        g.centerWidth -= parseInt(g.left.css('left'));
                        g.centerWidth -= p.space;
                    }
                }
                if (g.right)
                {
                    if (g.isRightCollapse)
                    {
                        g.centerWidth -= g.rightCollapse.width();
                        g.centerWidth -= parseInt(g.rightCollapse.css('borderLeftWidth'));
                        g.centerWidth -= parseInt(g.rightCollapse.css('borderRightWidth'));
                        g.centerWidth -= parseInt(g.rightCollapse.css('right'));
                        g.centerWidth -= p.space;
                    }
                    else
                    {
                        g.centerWidth -= g.rightWidth;
                        g.centerWidth -= parseInt(g.right.css('borderLeftWidth'));
                        g.centerWidth -= parseInt(g.right.css('borderRightWidth'));
                        g.centerWidth -= p.space;
                    }
                }
                g.centerLeft = 0;
                if (g.left)
                {
                    if (g.isLeftCollapse)
                    {
                        g.centerLeft += g.leftCollapse.width();
                        
                        g.centerLeft += parseInt(g.leftCollapse.css('left'));
                        g.centerLeft += p.space;
                    }
                    else
                    {
                        g.centerLeft += g.left.width();
                       
                        g.centerLeft += p.space;
                    }
                }
                g.center.css({ left: g.centerLeft });
                g.center.width(g.centerWidth);
                g.center.height(g.middleHeight);
				//面板高度宽度
				$('.tabUnit').height(g.middleHeight-61).attr("elemHeight",g.middleHeight-61);
				
                var contentHeight = g.middleHeight;
                if (g.center.header) contentHeight -= g.center.header.height();
                g.center.content.height(contentHeight);
            }
            if (g.left)
            {
                g.leftCollapse.height(g.middleHeight);
                g.left.height(g.middleHeight);
            }
               
        

        },
        _start: function (dragtype, e)
        {
            var g = this, p = this.options;
            g.dragtype = dragtype;
            if (dragtype == 'leftresize' || dragtype == 'rightresize')
            {
                g.xresize = { startX: e.pageX };
                g.draggingyline.css({ left: e.pageX - g.layout.offset().left, height: g.middleHeight, top: g.middleTop }).show();
                $('body').css('cursor', 'col-resize');
            }           
            else
            {
                return;
            }

            g.layout.lock.width(g.layout.width());
            g.layout.lock.height(g.layout.height());
            g.layout.lock.show();
            if ($.browser.msie || $.browser.safari) $('body').bind('selectstart', function () { return false; }); // 不能选择
        
        },
 
        _stop: function (e)
        {
            var g = this, p = this.options;
            var diff;
            if (g.xresize && g.xresize.diff != undefined)
            {
                diff = g.xresize.diff;
                if (g.dragtype == 'leftresize')
                {
                    if (p.minLeftWidth)
                    {
                        if (g.leftWidth + g.xresize.diff < p.minLeftWidth)
                            return;
                    }
                    g.leftWidth += g.xresize.diff;
                    g.left.width(g.leftWidth);
                    if (g.center)
                        g.center.width(g.center.width() - g.xresize.diff).css({ left: parseInt(g.center.css('left')) + g.xresize.diff });
                    else if (g.right)
                        g.right.width(g.left.width() - g.xresize.diff).css({ left: parseInt(g.right.css('left')) + g.xresize.diff });
                }
                else if (g.dragtype == 'rightresize')
                {
                    if (p.minRightWidth)
                    {
                        if (g.rightWidth - g.xresize.diff < p.minRightWidth)
                            return;
                    }
                    g.rightWidth -= g.xresize.diff;
                    g.right.width(g.rightWidth).css({ left: parseInt(g.right.css('left')) + g.xresize.diff });
                    if (g.center)
                        g.center.width(g.center.width() + g.xresize.diff);
                    else if (g.left)
                        g.left.width(g.left.width() + g.xresize.diff);
                }
            }
            
            g.trigger('endResize', [{
                direction: g.dragtype ? g.dragtype.replace(/resize/, '') : '',
                diff: diff
            }, e]);
    
            g.draggingxline.hide();
            g.draggingyline.hide();
            g.xresize = g.yresize = g.dragtype = false;
            g.layout.lock.hide();
          
        }
    });

})(jQuery);

/*
 * 表格表头固定,自适应宽度，固定高度
 * File:        chromatable.js
 * Version:     1.3.0
 * CVS:         $Id$
 * Description: Make a "sticky" header at the top of the table, so it stays put while the table scrolls
 * Author:      Zachary Siswick
 * Created:     Thursday 19 November 2009 8:53pm 
 * Language:    Javascript
 *
 */
(function($){
    
    $.chromatable = {
        // Default options
        defaults: {
						//specify a pixel dimension, auto, or 100%
            width: "100%", 
						height: "300px",
						scrolling: "yes" 
        }
				
		};
		
		$.fn.chromatable = function(options){
		 
		// Extend default options
		var options = $.extend({}, $.chromatable.defaults, options);

		return this.each(function(){
															
				// Add jQuery methods to the element
				var $this = $(this);
				var $uniqueID = $(this).attr("ID") + ("wrapper");
				
				
				//Add dimentsions from user or default parameters to the DOM elements
				$(this).css('width', options.width).addClass("_scrolling");
				
				$(this).wrap('<div class="scrolling_outer"><div id="'+$uniqueID+'" class="scrolling_inner"></div></div>');
								
				$(".scrolling_outer").css({'position':'relative'});
				$("#"+$uniqueID).css(
																	
					 {
						'overflow-x':'hidden',
						'overflow-y':'auto',
						'padding-right':'17px'						
						});
				
				$("#"+$uniqueID).css('height', options.height);
				$("#"+$uniqueID).css('width', options.width);
				
				// clone an exact copy of the scrolling table and add to DOM before the original table
				// replace old class with new to differentiate between the two
				$(this).before($(this).clone().attr("id", "").addClass("_thead").css(
																																															 
						{'width' : 'auto',
						 'display' : 'block', 
						 'position':'absolute', 
						 'border':'none', 
						 'top':'0'
							}));
	
				
				// remove all children within the cloned table after the thead element
				$('._thead').children('tbody').remove();
				
				
				$(this).each(function( $this ){
															
					// if the width is auto, we need to remove padding-right on scrolling container	
					
					if (options.width == "100%" || options.width == "auto") {
						
						$("#"+$uniqueID).css({'padding-right':'0px'});
					}
					
				
					if (options.scrolling == "no") {
												
						$("#"+$uniqueID).before('<a href="#" class="expander" style="width:100%;">Expand table</a>');
						
						$("#"+$uniqueID).css({'padding-right':'0px'});
						
						$(".expander").each(
	
							
							function(ints){
								
								$(this).attr("ID", ints);
								
								$( this ).bind ("click",function(){
																								 
										$("#"+$uniqueID).css({'height':'auto'});
										
										$("#"+$uniqueID+" ._thead").remove();
										
										$(this).remove();
						
									});
								});


						//this is dependant on the jQuery resizable UI plugin
						$("#"+$uniqueID).resizable({ handles: 's' }).css("overflow-y", "hidden");
	
					}
				
				});
				
				
				// Get a relative reference to the "sticky header"
				$curr = $this.prev();
				
				// Copy the cell widths across from the original table
				$("thead:eq(0)>tr th",this).each( function (i) {
																							 
					$("thead:eq(0)>tr th:eq("+i+")", $curr).width( $(this).width());
					
				});

				
				//check to see if the width is set to auto, if not, we don't need to call the resizer function
				if (options.width == "100%" || "auto"){
					
											
						// call the resizer function whenever the table width has been adjusted
						$(window).resize(function(){
																			
									resizer($this);										
						});
					}
				});
				
    };
		
		// private function to temporarily hide the header when the browser is resized
		
		function resizer($this) {
				
				// Need a relative reference to the "sticky header"
				$curr = $this.prev();
				
				$("thead:eq(0)>tr th", $this).each( function (i) {
																														 
					$("thead:eq(0)>tr th:eq("+i+")", $curr).width( $(this).width());
					
				});

  	};
		
})(jQuery);


/*
 * G3bgAccordion 
 * by 段冶
 * 2012.10.11
 * 手风琴效果
*/

	var G3bgAccordion={
			fold:function(a)
				{
					var pe=a.parentElem,
					   ce=a.clickElem,
					   se=a.showElem;
					$(pe).find(se).hide();
					$(pe).find(se).eq(0).show();
					$(pe).find(ce).eq(0).addClass('collapsible');
					$(pe).find(ce).each(function(){
						$(this).click(function(){
							if($(this).next().hasClass(se.replace(/./,''))){
								if($(this).next().css('display')==='none')
									{
										$(pe).find(se).hide();
										$(this).next().show();
										$(pe).find(ce).removeClass('collapsible');
										$(this).addClass('collapsible');
									}else{
										$(pe).find(se).hide();
										$(pe).find(ce).removeClass('collapsible');
									}
							}
							
						})
					});
				},
			show:function(a)
				{
					var pe=a.parentElem,
					   ce=a.clickElem,
					   se=a.showElem;
					var parentH=$('.l-layout-left').height();
					var sum=0,removeH=0;
					$(pe).find(ce).each(function(){
						sum+=$(this).height();
					});
					removeH=parentH-sum-31;
					$(pe).find(se).height(removeH);
					
				}
	};
	


/*
 * G3bgToggleI v0.1
 * by 段冶
 * 2012.10.11
 * 模块显示与隐藏
*/

	(function($){ 
		$.fn.G3bgToggle = function(options){ 
			var defaults={
				parentsElem:'.mod-unit',
				displayHeight:25,
				hideElem:'.mod-unit-ct',
				addClassName:'btn-toggle-active'
				
			}	
		var ops = $.extend(defaults, options); 
		this.each(function(){
			var _this=$(this);
			var thisParentH=_this.parents(ops.parentsElem).height();
			_this.bind('click',toggles);
			function toggles()
			{
				if(_this.parents(ops.parentsElem).find(ops.hideElem).css('display')=='none')
				{
					_this.parents(ops.parentsElem).find(ops.hideElem).show();
					_this.removeClass(ops.addClassName);
					
				}else{
					_this.parents(ops.parentsElem).find(ops.hideElem).hide();
					_this.addClass(ops.addClassName);
				}
				
			
			}
		}); 
		}; 
	})(jQuery); 
	
	/*
 * G3bgCombox v0.1
 * by 段冶
 * 2012.10.11
 * 下拉菜单效果实现单选，多选
*/
(function($){ 
	$.fn.G3bgCombox = function(options){ 
			var defaults={
				callback:null
			}	
		var opts = $.extend(defaults, options); 
			return this.each(function(){
				var _this=$(this);
				var thisSpan=_this.find('span').eq(0);
				var thisinput=_this.find('input:hidden').eq(0);
				var inputBox=_this.find('.inputBox');
				var defaultVal=thisSpan.text();
				var defaultIptVal=inputBox.find(":text").eq(0).val();
				thisSpan.unbind("click");
				thisSpan.bind('click',selectMove);
				inputBox.unbind("click");
				inputBox.bind('click',selectMove);
				function selectMove()
				{
					$(".select-ct").not($(this).parent().find('.select-ct')).hide();
					if($(this).parents('.select-box').hasClass('select-disabled'))
					{
						
					}else{
					var wrap=$(this).parent().find(".select-ct");
					var wrapParent=$(this).parents('.select-box');
					if(wrap.css("display")=="none")
					{
							wrap.show();
							if(wrap.children().height()>200)
							{
								wrap.children().addClass('select-Hauto');
							}
					}else{
						wrap.hide();
					}
					$('.select_ul').find('li').hover(
					  function () {
						$(this).addClass("hover");
					  },
					  function () {
						$(this).removeClass("hover");
					});
					//单选
					if(_this.attr('mode')=='radio')
					{
							
						    switch(wrap.attr('childElem').toLowerCase())
								{
								case 'ul': 
									wrap.find("li").unbind('click').bind('click',function(){
										if(inputBox.length>0)
										{
											inputBox.find(":text").eq(0).val($(this).text());
										}else{
											thisSpan.text($(this).text());	
										}
										thisinput.val($(this).text());
										wrap.hide();
									});
									break;
								case 'table':
									wrap.find(":radio").unbind('click').bind('click',function(){
											if($(this).attr("checked"))
											{
												if(inputBox.length>0)
												{
													inputBox.find(":text").eq(0).val($(this).parents('tr').children('.tdVal').text());
													
												}else{
													thisSpan.text($(this).parents('tr').children('.tdVal').text());
												}
												thisinput.val($(this).parents('tr').children('.tdVal').text());
												
											}
									});
									break;
								case 'tree': 
									wrap.find(":radio").unbind('click').bind('click',function(){
											if($(this).attr("checked"))
											{	
												if(inputBox.length>0)
												{
													if($(this).parent().is("li"))
													{
														thisinput.val($(this).next().text());
														inputBox.find(":text").eq(0).val($(this).next().text());
													}else{
														thisinput.val($(this).parent().text());
														inputBox.find(":text").eq(0).val($(this).parent().text());
													}
												}else{
													if($(this).parent().is("li"))
													{
														thisinput.val($(this).next().text());
														thisSpan.text($(this).next().text());
													}else{
														thisinput.val($(this).parent().text());
														thisSpan.text($(this).parent().text());
													}
												}
												
											
											}
									});
									break;
								case 'ztree': 
								wrap.delegate("span.chk","click",function(){	
												if(inputBox.length>0)
												{
													if($(this).parent().is("li"))
													{
														thisinput.val($(this).next().text());
														inputBox.find(":text").eq(0).val($(this).next().text());
													}else{
														thisinput.val($(this).parent().text());
														inputBox.find(":text").eq(0).val($(this).parent().text());
													}
												}else{
													if($(this).parent().is("li"))
													{
														thisinput.val($(this).next().text());
														thisSpan.text($(this).next().text());
													}else{
														thisinput.val($(this).parent().text());
														thisSpan.text($(this).parent().text());
													}
												}
									});
									break;
								}
					};
					
					
					//复选
					if(_this.attr('mode')=='check')
					{
					
						switch(wrap.attr('childElem').toLowerCase())
								{
								case 'ul':
									wrap.find(":checkbox").unbind('click').bind('click',function(){
										var h=[];
										wrap.find(":checked").each(function(){
													h.push($(this).parent().text());	
										});
										if(inputBox.length>0)
										{
											inputBox.find(":text").eq(0).val(h);
										}else{
											thisSpan.text(h);
										};
										thisinput.val(h);
										if(wrap.find(":checkbox[checked!=checked]").length==wrap.find(":checkbox").length)
										{
											if(inputBox.length>0)
											{
												inputBox.find(":text").eq(0).val(defaultIptVal);
												thisinput.val(defaultIptVal);
											}else{
												thisSpan.text(defaultVal);
												thisinput.val(defaultVal);
											};
											
										};	
									});
									break;
								case 'table':
								wrap.delegate(':checkbox','click',function(){
											var h=[];
												wrap.find(":checked:not(.checkAll)").each(function(){
															h.push($(this).parents('tr').children('.tdVal').text());
															
												});
												if(inputBox.length>0)
												{
													inputBox.find(":text").eq(0).val(h);
												}else{
													thisSpan.text(h);
												};
												thisinput.val(h);
												if(wrap.find(":checkbox[checked!=checked]").length==wrap.find(":checkbox").length)
												{
													if(inputBox.length>0)
													{
														inputBox.find(":text").eq(0).val(defaultIptVal);
														thisinput.val(defaultIptVal);
													}else{
														thisSpan.text(defaultVal);
														thisinput.val(defaultVal);
													};
												};
											
									}); 
									break;
								case 'tree':
									wrap.delegate(':checkbox','click',function(){
											var h=[];
												if($(this).hasClass('checkAllTree'))
												{
													$(this).parent().find('ul').eq(0).find(":checkbox").attr("checked",this.checked);
													if($(this).parents('.parent:last').find(".checkAllTree").eq(0).next().html()!=$(this).next().html())
													{
														$(this).parents('.parent:last').find(".checkAllTree").eq(0).attr("checked",$(this).parents('.parent:last').find(":checkbox").not($(".checkAllTree").eq(0)).length == $(this).parents('.parent:last').find(':checkbox:checked').not($(".checkAllTree").eq(0)).length ? true : false);
													}
												}else{
													$(this).parents('.parent:first').find(".checkAllTree").eq(0).attr("checked",$(this).parents('.parent:first').find(':checkbox:not(.checkAllTree)').length == $(this).parents('.parent:first').find(':checkbox:not(.checkAllTree):checked').length ? true : false);
													$(this).parents('.parent:last').find(".checkAllTree").eq(0).attr("checked",$(this).parents('.parent:last').find(':checkbox').not($(".checkAllTree").eq(0)).length == $(this).parents('.parent:last').find(':checkbox:checked').not($(".checkAllTree").eq(0)).length ? true : false);
												};
												wrap.find(":checkbox").each(function(i){
													if($(this).attr("checked"))
													{
														h.push($(this).next().text());
														
													};
												});
												if(inputBox.length>0)
												{
													inputBox.find(":text").eq(0).val(h);
												}else{
													thisSpan.text(h);
												};
												thisinput.val(h);
												if(wrap.find(":checkbox[checked!=checked]").length==wrap.find(":checkbox").length)
												{
													if(inputBox.length>0)
													{
														inputBox.find(":text").eq(0).val(defaultIptVal);
														thisinput.val(defaultIptVal);
													}else{
														thisSpan.text(defaultVal);
														thisinput.val(defaultVal);
													};
												}
									}); 
									break;
								case 'ztree':
									wrap.delegate("span.chk","click",function(){
											var h=[];
											var i=0;
											wrap.find('.checkbox_true_full').each(function(){
												i++;
											});
									}); 
									break;	
								}
						
					};
					
					//点击关闭下拉框
					$(document).unbind().bind('click',function(event){
							var src_element = $(event.srcElement || event.target);
							if(!src_element.parents().hasClass('select-box'))
							{
								$('.select-ct').hide();
								if(wrap.children().hasClass('select-Hauto'))
									{
										wrap.children().removeClass('select-Hauto');
									}
							}
							
					});
					}
				
					if (opts.callback != null && typeof(opts.callback) == 'function'){
						opts.callback.call(this,_this);
					};
				}
				
		}); 
	}; 
})(jQuery); 

/*
 * G3bgTabs v0.1
 * by 段冶
 * 2012.11.5
 * 选项卡效果
*/
(function($) {     
  //插件主要内容     
  $.fn.G3bgTabs = function(options) {
	var defaults = {     
        tabNavObj:'.tabNav',
        tabNavBtn:'li',
        tabContentObj:'.tabContent',
        tabContent:'.tabCt-list',
        currentClass:'current',
        eventType:'click'
	};     
    var opts = $.extend({}, defaults, options);     
    return this.each(function() {  
        var $this=$(this),
        $tabNavObj=$(opts.tabNavObj,$this),
        $tabContentObj=$(opts.tabContentObj,$this),
        $tabNavBtns=$(opts.tabNavBtn,$tabNavObj),
        $tabContentBlocks=$(opts.tabContent,$tabContentObj);
        $tabNavBtns.bind(opts.eventType,function(){
            var $that=$(this),
            _index=$tabNavBtns.index($that);
            $that.addClass(opts.currentClass).siblings(opts.tabNavBtn).removeClass(opts.currentClass);
            $tabContentBlocks.eq(_index).show().siblings(opts.tabContent).hide();
        }).eq(0).trigger(opts.eventType);
    });
   
  };
      
  
    
})(jQuery);
/*
 * G3bgTableUI v0.1
 * by 段冶
 * 2012.10.11
 * 实现表格奇偶行交叉颜色，鼠标移上去高亮显示
*/
(function($){ 
		$.fn.G3bgTableUI = function(options){ 
			var defaults={
				evenRowClass:'evenRow',
				oddRowClass:'oddRow',
				overRowClass:'overRow',
				activeRowClass:'activeRow'
			}	
			var options = $.extend(defaults, options); 
			this.each(function(){
				var thisTable=$(this);
				//添加奇偶行颜色
				$(thisTable).find("tr:even").addClass(options.evenRowClass);
				$(thisTable).find("tr:odd").addClass(options.oddRowClass);
				//添加活动行颜色
				$(thisTable).find("tr").bind("mouseover",function(){
					$(this).addClass(options.overRowClass);
				});
				$(thisTable).find("tr").bind("mouseout",function(){
					$(this).removeClass(options.overRowClass);
				});
			
				
			}); 
		}; 
	})(jQuery); 
	
	/*
 * G3bgTree v0.1
 * by 段冶
 * 2012.10.11
 * 树形菜单
*/

if (jQuery) (function($) {

    $.extend($.fn, {
        G3bgTree: function(o, callback) {
			var _this=$(this);
            if (!o) var o = {};
            o.data = this.html();
            if (o.menuEvent == undefined) o.menuEvent = 'click';
            if (o.expandSpeed == undefined) o.expandSpeed = 500;
            if (o.collapseSpeed == undefined) o.collapseSpeed = 500;
            if (o.expandEasing == undefined) o.expandEasing = null;
            if (o.collapseEasing == undefined) o.collapseEasing = null;
            if (o.multiOpenedSubMenu == undefined) o.multiOpenedSubMenu = false;
            if (o.parentMenuTriggerCallback == undefined) o.parentMenuTriggerCallback = false;
            if (o.expandedNode == undefined) o.expandedNode = null;

            $(this).each(function() {

                function bindTree(t) {

                    var liClickedSelector = callback != undefined ? 'LI > A' : 'LI.parent > A';
                    
                    $(t).find(liClickedSelector).bind(o.menuEvent, function() {						
                        currentItem = $(this);
						
                        if ($(this).parent().hasClass('parent')) {
                            if ($(this).parent().hasClass('expanded')) {
                                
                                $(this).parent().find('UL').slideUp({ duration: o.collapseSpeed, easing: o.collapseEasing });
                                $(this).parent().removeClass('expanded').addClass('collapsed');

                            } else {
                                
                              
                                $($(this).parent().find("UL")[0]).slideDown({ duration: o.expandSpeed, easing: o.expandEasing });
                                $(this).parent().removeClass('collapsed').addClass('expanded');
                                $(this).parent().find('LI.parent').removeClass('expanded').addClass('collapsed');

                                if (o.parentMenuTriggerCallback)
                                    callback($(this).attr('rel'));
									
                            }

                        } else {								
                            callback($(this).attr('rel'));
                        }
                        return false;
                    });

                    // Prevent A from triggering the # on non-click events
                    if (o.menuEvent.toLowerCase != 'click') $(t).find(liClickedSelector).bind('click', function() { return false; });
                }
				//点击之后添加样式
				$(this).find("li").click(function(){
					if ($(this).hasClass("child"))
					{
						_this.find('li').removeClass("curr");
						$(this).addClass("curr");
						
					}

				
				})
                // initialization
                $($(this)).find(":first").show();
                bindTree($(this));

                // Expend default node
                if (o.expandedNode) {

                    var elementToExpend = $($(this)).find("a[rel=" + o.expandedNode + "]").parent().parent();

                    // Collect all UL items that need to be extended
                    var ulMenuElements = new Array();
                    var i = 0;
                    while (elementToExpend && elementToExpend.find('DIV').length == 0) {

                        if (elementToExpend[0].tagName == "UL") {
                            ulMenuElements[i] = elementToExpend;
                            i++;
                        }
                        elementToExpend = elementToExpend.parent();
                    }
                    ulMenuElements = ulMenuElements.reverse()

                    // Extend all collected item (recursive)
                    var i = 0;
                    var openMenu = function() {

                        i++; // skip first ul(root)
                        if (i >= ulMenuElements.length) return;

                        ulMenuElements[i].removeClass('collapsed').addClass('expanded');
                        ulMenuElements[i].slideDown({ duration: o.expandSpeed, easing: o.expandEasing, complete: openMenu });

                    }
                    openMenu(ulMenuElements);
                }
            });
        }
    });

})(jQuery);
	/*
 * G3bgNavtab v0.1
 * by 段冶
 * 2012.10.11
 * 主框架tab标签
*/

(function($){ 
	$.fn.G3bgNavtab = function(options){ 
		var defaults={
			navTabmenu:'.navTab-hd',
			navTabpanel:'.navTab-panel',
			navTabUnit:'.tabUnit'
		}	
	var options = $.extend(defaults, options); 
	
			return this.each(function(){
				var _this=$(this)
				$(options.navTabUnit,_this).each(function(){
					$(this).hide();
				})
				$(options.navTabmenu,_this).find('li').eq(0).addClass('current');
				$(options.navTabUnit,_this).eq(0).show();
				$(options.navTabmenu,_this).find('li').find('span').unbind('click').bind('click',navTab);
				function navTab()
				{
					
					var i=$(this).parent().parent().index();
					var tabid=$(this).parent().parent().attr('tabid');
					$(this).parent().parent().siblings().removeClass('current');
					$(this).parent().parent().addClass('current');
					$('.tabUnit').eq(i).siblings().hide();
					$('.tabUnit').eq(i).show();
					$("#breadCrumb").html($("#breadCrumb", $('.tabUnit').eq(i).find("iframe").get(0).contentWindow.document).html());
					$(".leftFrameMenu li").removeClass('curr');
					$(".leftFrameMenu li[url='"+tabid+"']").addClass('curr');
					$("#accordion-box .accordion-ct").hide();
					$(".leftFrameMenu li[url='"+tabid+"']").parents('.accordion-ct').show();
					$("#accordion-box .accordion-hd").removeClass('collapsible');
					$(".leftFrameMenu li[url='"+tabid+"']").parents('.accordion-ct').prev().addClass('collapsible');
				}
			}); 
			
			
	
	}; 
})(jQuery); 

/**
 * Copyright (c) 2009 Sergiy Kovalchuk (serg472@gmail.com)
 * 
 * Dual licensed under the MIT (http://www.opensource.org/licenses/mit-license.php)
 * and GPL (http://www.opensource.org/licenses/gpl-license.php) licenses.
 *  
 * Following code is based on Element.mask() implementation from ExtJS framework (http://extjs.com/)
 *
 */
;(function($){
	
	/**
	 * Displays loading mask over selected element(s). Accepts both single and multiple selectors.
	 *
	 * @param label Text message that will be displayed on top of the mask besides a spinner (optional). 
	 * 				If not provided only mask will be displayed without a label or a spinner.  	
	 * @param delay Delay in milliseconds before element is masked (optional). If unmask() is called 
	 *              before the delay times out, no mask is displayed. This can be used to prevent unnecessary 
	 *              mask display for quick processes.   	
	 */
	$.fn.mask = function(label, delay){
		$(this).each(function() {
			if(delay !== undefined && delay > 0) {
		        var element = $(this);
		        element.data("_mask_timeout", setTimeout(function() { $.maskElement(element, label)}, delay));
			} else {
				$.maskElement($(this), label);
			}
		});
	};
	
	/**
	 * Removes mask from the element(s). Accepts both single and multiple selectors.
	 */
	$.fn.unmask = function(){
		$(this).each(function() {
			$.unmaskElement($(this));
		});
	};
	
	/**
	 * Checks if a single element is masked. Returns false if mask is delayed or not displayed. 
	 */
	$.fn.isMasked = function(){
		return this.hasClass("masked");
	};

	$.maskElement = function(element, label){
	
		//if this element has delayed mask scheduled then remove it and display the new one
		if (element.data("_mask_timeout") !== undefined) {
			clearTimeout(element.data("_mask_timeout"));
			element.removeData("_mask_timeout");
		}

		if(element.isMasked()) {
			$.unmaskElement(element);
		}
		
		if(element.css("position") == "static") {
			element.addClass("masked-relative");
		}
		
		element.addClass("masked");
		
		var maskDiv = $('<div class="loadmask"></div>');
		
		//auto height fix for IE
		if(navigator.userAgent.toLowerCase().indexOf("msie") > -1){
			maskDiv.height(element.height() + parseInt(element.css("padding-top")) + parseInt(element.css("padding-bottom")));
			maskDiv.width(element.width() + parseInt(element.css("padding-left")) + parseInt(element.css("padding-right")));
		}
		
		//fix for z-index bug with selects in IE6
		if(navigator.userAgent.toLowerCase().indexOf("msie 6") > -1){
			element.find("select").addClass("masked-hidden");
		}
		
		element.append(maskDiv);
		
		if(label !== undefined) {
			var maskMsgDiv = $('<div class="loadmask-msg" style="display:none;"></div>');
			maskMsgDiv.append('<div>' + label + '</div>');
			element.append(maskMsgDiv);
			
			//calculate center position
			maskMsgDiv.css("top", Math.round(element.height() / 2 - (maskMsgDiv.height() - parseInt(maskMsgDiv.css("padding-top")) - parseInt(maskMsgDiv.css("padding-bottom"))) / 2)+"px");
			maskMsgDiv.css("left", Math.round(element.width() / 2 - (maskMsgDiv.width() - parseInt(maskMsgDiv.css("padding-left")) - parseInt(maskMsgDiv.css("padding-right"))) / 2)+"px");
			
			maskMsgDiv.show();
		}
		
	};
	
	$.unmaskElement = function(element){
		//if this element has delayed mask scheduled then remove it
		if (element.data("_mask_timeout") !== undefined) {
			clearTimeout(element.data("_mask_timeout"));
			element.removeData("_mask_timeout");
		}
		
		element.find(".loadmask-msg,.loadmask").remove();
		element.removeClass("masked");
		element.removeClass("masked-relative");
		element.find("select").removeClass("masked-hidden");
	};
 
})(jQuery);

//鼠标右键菜单
(function(menu) {
    jQuery.fn.contextmenu = function(options) {
        var defaults = {
            offsetX : 2,        //鼠标在X轴偏移量
            offsetY : 2,        //鼠标在Y轴偏移量
            items   : [],       //菜单项
            action  : $.noop()  //自由菜单项回到事件
        };
        var opt = menu.extend(true, defaults, options);
        function create(e) {
            var m = menu('<ul class="simple-contextmenu"></ul>').appendTo(document.body);
            
            menu.each(opt.items, function(i, item) {
                if (item) {
                    if(item.type == "split"){ 
                        menu("<div class='m-split'></div>").appendTo(m);
                        return;
                    }
                    var row   = menu('<li><a href="javascript:void(0)"><span></span></a></li>').appendTo(m);
                    item.icon ? menu('<img src="' + item.icon + '">').insertBefore(row.find('span')) : '';
                    item.text ? row.find('span').text(item.text) : '';
                    
                    if (item.action) {
                        row.find('a').click(function() {
                            item.action(e.target);
                        });
                    }
                }
            });
            return m;
        }
        
        this.live('contextmenu', function(e) {
            var m = create(e).show("fast");
            var left = e.pageX + opt.offsetX, top = e.pageY + opt.offsetY, p = {
                wh : menu(window).height(),
                ww : menu(window).width(),
                mh : m.height(),
                mw : m.width()
            }
            top = (top + p.mh) >= p.wh ? (top -= p.mh) : top;
            //当菜单超出窗口边界时处理
            left = (left + p.mw) >= p.ww ? (left -= p.mw) : left;
            m.css({
                zIndex : 10000,
                left : left,
                top : top
            });
            $(document.body).live('contextmenu click', function() {
                m.hide("fast",function(){
                    m.remove();
                });        
            });
            
            return false;
        });
        return this;
    }
})(jQuery);

/**
 * 进度条插件 
*/
;(function($) {
	$.extend({
		progressBar: new function() {
			this.defaults = {
				steps			: 20,			// 到达目标所采取的步长，设定的值越高，进度越慢
				stepDuration	: 20,			// setInterval的时间参数，单位ms
				max				: 100,			// 最大值
				showText		: true,			// 在进度条旁边用百分数或者分数显示进度与否，默认为true
				textFormat		: 'percentage',	// ShowText的显示形式，“percentage”表示百分数，“fraction”表示分数
				width			: 120,			// 进度条图片的宽度，一般不用改的，除非你不用插件自带的图片，那时您需要计算自己的图的宽度
				height			: 12,			// 进度条图片的高度，一般不用改的，除非你不用插件自带的图片，那时您需要计算自己的图的高度
				callback		: null,			// 回调函数，当进度达到目标值的时候触发的函数，默认没有
				boxImage		: 'progressbar.gif',// boxImage : 包着进度条的图片
				barImage		: {
									0:  'progressbg_red',
									30: 'progressbg_orange',
									50: 'progressbg_black',
									70: 'progressbg_green',
									90: 'progressbg_yellow'
								},
				// 内部参数无须传入
				running_value	: 0,
				value			: 0,
				image			: null
			};

			/* 公共方法 */
			this.construct = function(arg1, arg2) {
				var argvalue	= null;
				var argconfig	= null;

				if (arg1 != null) {
					if (!isNaN(arg1)) {
						argvalue = arg1;
						if (arg2 != null) {
							argconfig = arg2;
						}
					} else {
						argconfig = arg1;
					}
				}

				return this.each(function() {
					var pb		= this;
					var config	= this.config;

					if (argvalue != null && this.bar != null && this.config != null) {
						this.config.value 		= parseInt(argvalue)
						if (argconfig != null)
							pb.config			= $.extend(this.config, argconfig);
						config	= pb.config;
					} else {
						var $this				= $(this);
						var config				= $.extend({}, $.progressBar.defaults, argconfig);
						config.id				= $this.attr('id') ? $this.attr('id') : Math.ceil(Math.random() * 100000);	// random id, if none provided

						if (argvalue == null)
							argvalue	= $this.html().replace("%","")	// 取得百分比

						config.value			= parseInt(argvalue);
						config.running_value	= 0;
						config.image			= getBarImage(config);

						var numeric = ['steps', 'stepDuration', 'max', 'width', 'height', 'running_value', 'value'];
						for (var i=0; i<numeric.length; i++)
							config[numeric[i]] = parseInt(config[numeric[i]]);

						if (config.value > config.max)
							config.value = config.max;
						$this.html("");
						var bar					= document.createElement('img');
						var text				= document.createElement('span');
						var $bar				= $(bar);
						var $text				= $(text);
						pb.bar					= $bar;
						$bar.attr('id', config.id + "_pbImage");
						$text.attr('id', config.id + "_pbText");
						$text.html(getText(config));
						$bar.attr('title', getText(config));
						$bar.attr('alt', getText(config));
						$bar.attr('src', config.boxImage);
						$bar.attr('width', config.width);
						$bar.css("width", config.width + "px");
						$bar.css("height", config.height + "px");
						$bar.addClass(config.image);
						$bar.css("background-position", ((config.width * -1)) + 'px 50%');
						$bar.css("padding", "0");
						$bar.css("margin", "0");
						$this.append($bar);
						$this.append($text);
					}

					function getPercentage(config) {
						return Math.round(config.running_value * 100 / config.max);
					}

					function getBarImage(config) {
						var image = config.barImage;
						if (typeof(config.barImage) == 'object') {
							for (var i in config.barImage) {
								if (getPercentage(config) >= parseInt(i)) {
									image = config.barImage[i];
								} else { break; }
							}
						}
						return image;
					}

					function getText(config) {
						if (config.showText) {
							if (config.textFormat == 'percentage') {
								return " " + getPercentage(config) + "%";
							} else if (config.textFormat == 'fraction') {
								return " " + config.running_value + '/' + config.max;
							}
						}
					}

					config.increment = Math.round((config.value - config.running_value)/config.steps);
					if (config.increment < 0)
						config.increment *= -1;
					if (config.increment < 1)
						config.increment = 1;

					var t = setInterval(function() {
						var pixels	= config.width / 100;			// Define how many pixels go into 1%

						if (config.running_value > config.value) {
							if (config.running_value - config.increment  < config.value) {
								config.running_value = config.value;
							} else {
								config.running_value -= config.increment;
							}
						}
						else if (config.running_value < config.value) {
							if (config.running_value + config.increment  > config.value) {
								config.running_value = config.value;
							} else {
								config.running_value += config.increment;
							}
						}

						if (config.running_value == config.value)
							clearInterval(t);

						var $bar	= $("#" + config.id + "_pbImage");
						var $text	= $("#" + config.id + "_pbText");
						var image	= getBarImage(config);
						if (image != config.image) {
							$bar.removeAttr("class");
							$bar.addClass(image);
							config.image = image;
						}
						switch(image)
						{
						case 'progressbg_red':
						  $bar.css("background-position", (((config.width * -1)) + (getPercentage(config) * pixels)) + 'px 0');
						  break;
						case 'progressbg_orange':
						 $bar.css("background-position", (((config.width * -1)) + (getPercentage(config) * pixels)) + 'px -17px');
						  break;
						case 'progressbg_black':
						  $bar.css("background-position", (((config.width * -1)) + (getPercentage(config) * pixels)) + 'px -34px');
						  break;
						case 'progressbg_green':
						  $bar.css("background-position", (((config.width * -1)) + (getPercentage(config) * pixels)) + 'px -51px');
						  break;
						case 'progressbg_yellow':
						  $bar.css("background-position", (((config.width * -1)) + (getPercentage(config) * pixels)) + 'px -68px');
						  break;
						}
						$bar.attr('title', getText(config));
						$text.html(getText(config));

						if (config.callback != null && typeof(config.callback) == 'function')
							config.callback(config);

						pb.config = config;
					}, config.stepDuration);
					argvalue=null;
				});
			};
		}
	});
	$.fn.extend({
        progressBar: $.progressBar.construct
	});
})(jQuery);

/*! Copyright (c) 2010 Brandon Aaron (http://brandonaaron.net)
 * Licensed under the MIT License (LICENSE.txt).
 *
 * Version 2.1.2
 */

(function($){

$.fn.bgiframe = ($.browser.msie && /msie 6\.0/i.test(navigator.userAgent) ? function(s) {
    s = $.extend({
        top     : 'auto', // auto == .currentStyle.borderTopWidth
        left    : 'auto', // auto == .currentStyle.borderLeftWidth
        width   : 'auto', // auto == offsetWidth
        height  : 'auto', // auto == offsetHeight
        opacity : true,
        src     : 'javascript:false;'
    }, s);
    var html = '<iframe class="bgiframe"frameborder="0"tabindex="-1"src="'+s.src+'"'+
                   'style="display:block;position:absolute;z-index:-1;'+
                       (s.opacity !== false?'filter:Alpha(Opacity=\'0\');':'')+
                       'top:'+(s.top=='auto'?'expression(((parseInt(this.parentNode.currentStyle.borderTopWidth)||0)*-1)+\'px\')':prop(s.top))+';'+
                       'left:'+(s.left=='auto'?'expression(((parseInt(this.parentNode.currentStyle.borderLeftWidth)||0)*-1)+\'px\')':prop(s.left))+';'+
                       'width:'+(s.width=='auto'?'expression(this.parentNode.offsetWidth+\'px\')':prop(s.width))+';'+
                       'height:'+(s.height=='auto'?'expression(this.parentNode.offsetHeight+\'px\')':prop(s.height))+';'+
                '"/>';
    return this.each(function() {
        if ( $(this).children('iframe.bgiframe').length === 0 )
            this.insertBefore( document.createElement(html), this.firstChild );
    });
} : function() { return this; });

// old alias
$.fn.bgIframe = $.fn.bgiframe;

function prop(n) {
    return n && n.constructor === Number ? n + 'px' : n;
}

})(jQuery);
