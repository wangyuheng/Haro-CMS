/**
 * @author houpo@chinaedu.net
 */
if (typeof Edu == 'undefined') {
    var Edu = {};
}
(function($) {
    Edu.fn = {
        /**
         * 合并多行为一行 tableId table的id conFun 比较两个单元格使用的函数 index 从哪一行开始合并 默认0
         */
        mergeRows : function(tableId, conFun) {
            var table = $("#" + tableId);
            var trs = table.children("tbody").children("tr");
            conFun = conFun || Edu.fn._cellEquals;
            if (trs.length < 2)
                return;
            var colLen = trs.eq(0).children("td").length;
            var rowLen = trs.length;
            var tableArr = Edu.fn.make2x2Arr(rowLen);
            for ( var i = 0; i < colLen; i++) {
                var mergeCount = 1;
                var pos = {// 记录当前合并到的位置
                    i : i,
                    j : 0
                };
                for ( var j = 0; j < rowLen - 1; j++) {
                    if (!tableArr[j][i]) {
                        tableArr[j][i] = 1;
                    }
                    tableArr[j + 1][i] = 1;
                    var td = trs.eq(j).children("td").eq(i);
                    var nextTd = trs.eq(j + 1).children("td").eq(i);
                    if (conFun.call(this, td, nextTd)) {
                        mergeCount++;
                        tableArr[j + 1][i] = -1;// 要删除的单元格
                        if (j == rowLen - 2) {// 如果比较到最后一行时
                            tableArr[pos.j][pos.i] = mergeCount;
                        }
                    } else {
                        if (mergeCount > 1) {
                            tableArr[pos.j][pos.i] = mergeCount;
                        }
                        pos.j = j + 1;
                        mergeCount = 1;
                    }
                }
            }
            for ( var j = 0; j < rowLen; j++) {
                for ( var i = colLen - 1; i >= 0; i--) {
                    if (tableArr[j][i] > 1) {
                        trs.eq(j).children("td").eq(i).attr("rowspan", tableArr[j][i]);
                    } else if (tableArr[j][i] == -1) {
                        trs.eq(j).children("td").eq(i).remove();
                    }
                }
            }
        },

        /**
         * 默认比较两个单元格内容是否相等
         */
        _cellEquals : function(td, nextTd) {
            return $.trim(td.text()) == $.trim(nextTd.text());
        },

        /**
         * 合并多列为一列
         */
        mergeCols : function(tableId, conFun) {
            var table = $("#" + tableId);
            var trs = table.children("tbody").children("tr");
            conFun = conFun || Edu.fn._cellEquals;
            if (trs.length < 2)
                return;
            var colLen = trs.eq(0).children("td").length;
            var rowLen = trs.length;
            var tableArr = Edu.fn.make2x2Arr(rowLen);
            for ( var i = 0; i < rowLen; i++) {
                var mergeCount = 1;
                var pos = {// 记录当前合并到的位置
                    i : i,
                    j : 0
                };
                for ( var j = 0; j < colLen - 1; j++) {
                    if (!tableArr[i][j]) {
                        tableArr[i][j] = 1;
                    }
                    tableArr[i][j + 1] = 1;
                    var td = trs.eq(i).children("td").eq(j);
                    var nextTd = trs.eq(i).children("td").eq(j + 1);
                    if (conFun.call(this, td, nextTd)) {
                        mergeCount++;
                        tableArr[i][j + 1] = -1;// 要删除的单元格
                        if (j == colLen - 2) {// 如果比较到最后一行时
                            tableArr[pos.i][pos.j] = mergeCount;
                        }
                    } else {
                        if (mergeCount > 1) {
                            tableArr[pos.i][pos.j] = mergeCount;
                        }
                        pos.j = j + 1;
                        mergeCount = 1;
                    }
                }
            }
            for ( var j = 0; j < rowLen; j++) {
                for ( var i = colLen - 1; i >= 0; i--) {
                    if (tableArr[j][i] > 1) {
                        trs.eq(j).children("td").eq(i).attr("colspan", tableArr[j][i]);
                    } else if (tableArr[j][i] == -1) {
                        trs.eq(j).children("td").eq(i).remove();
                    }
                }
            }
        },

        /**
         * 生成二维数组 n 行数
         */
        make2x2Arr : function(n) {
            var arr = new Array();
            for ( var i = 0; i < n; i++) {
                arr[i] = new Array();
            }
            return arr;
        },
        /**
         * js类继承
         */
        extendClass : function(child, parent) {
            child.prototype = $.extend(true, {}, parent.prototype, child.prototype);
            child.prototype.contractor = child;
        },
        /**
         * js对象深度复制
         */
        cloneObject : function(source) {
            if (typeof source !== "object") {
                return source;
            }
            var s = {};
            if ($.isArray(source)) {
                s = [];
            }
            for ( var key in source) {
                if (key.substr(0, 6) == 'jQuery') {
                    continue;
                }
                s[key] = this.cloneObject(source[key]);
            }
            return s;
        },
        /**
         * 把obj,填充到表单里边 ，form为jquery对象
         */
        objectToForm : function(obj, form) {
          if (obj && typeof obj === "object" && !$.isArray(obj)) {
            this._objectToForm('', obj, form);
          }
        },

        _objectToForm : function(prefix, obj, form) {
          if (obj != null && !$.isArray(obj) && typeof obj !== "object" && typeof obj !== "function") {
            prefix = prefix.replace(/\./g, '\\.').replace(/\[/g, '\\[').replace(/\]/g, '\\]');
            var inp = form.find(':input[name="' + prefix + '"]');
            if (inp.length > 0) {
              inp.val(obj);
            }
          } else if (typeof obj === "object" && !$.isArray(obj)) {
            for ( var p in obj)
              this._objectToForm(prefix + (prefix == '' ? '' : ".") + p, obj[p], form);
          } else if ($.isArray(obj)) {
            for ( var i = 0; i < obj.length; i++) {
              var tmpPrefix = prefix + "[" + i + "]";
              this._objectToForm(tmpPrefix, obj[i], form);
            }
          }
        },
        /**
         * 把表单转换为对象
         */
        formToObject : function(form) {
            var inps = form.serializeArray();
            var obj = {};
            this._sortFormValues(inps);
            for ( var i = 0; i < inps.length; i++) {
                var inp = inps[i];
                this.setObjectProperty(obj, inp.name, inp.value);
            }
            return obj;
        },
        /**
         * 表单项以name属性排序
         */
        _sortFormValues : function(inps) {
            for ( var i = 0; i < inps.length; i++) {
                for ( var j = 0; j < inps.length - i - 1; j++) {
                    if (inps[j + 1].name < inps[j].name) {
                        var act = inps[j + 1];
                        inps[j + 1] = inps[j];
                        inps[j] = act;
                    }
                }
            }
        },
        /**
         * 给一对象的单个字段设置值 eg.setObjectProperty(obj,'works[0].id','1')
         */
        setObjectProperty : function(obj, field, value) {
            var ps = field.split(".");
            var nowObj = obj;
            var matchs = null;
            for ( var i = 0; i < ps.length - 1; i++) {
                var f = ps[i];
                var index = 0;
                matchs = /^(.+)\[(\d+)\]$/.exec(f);
                if (matchs) {
                    f = matchs[1];
                    index = matchs[2] * 1;
                }
                if (!(f in nowObj)) {
                    if (matchs) {
                        nowObj[f] = [];
                    } else {
                        nowObj[f] = {};
                    }
                }
                if (matchs) {
                    if (nowObj[f].length <= index || !nowObj[f][index]) {
                        nowObj[f][index] = {}
                    }
                    nowObj = nowObj[f][index];
                } else {
                    nowObj = nowObj[f];
                }
            }
            var lastF = ps[ps.length - 1];
            matchs = /^(.+)\[(\d+)\]$/.exec(lastF);
            if (matchs) {
                nowObj[lastF][matchs[2] * 1] = value;
            } else {
                nowObj[lastF] = value;
            }
        },
        /**
         * 截取字符串，两个英文字符长度相当一个汉字的长度 author hp.
         * 
         * @param str
         * @param len
         *            返回的字符串的长度
         * @param suffix
         *            当超长时要显示的后缀
         * @returns
         */
        cutStrForShow : function(str, len, suffix) {
            if (str.length <= len) {
                return str;
            }
            var nowLen = 0.0;
            var reStr = [];
            var sf = suffix || '...';
            var sfLen = 0;
            for ( var j = 0; j < sf.length; j++) {
                sfLen += (sf.charCodeAt(i) <= 255 ? 0.5 : 1.0);
            }
            for ( var i = 0; i < str.length; i++) {
                nowLen += (str.charCodeAt(i) <= 255 ? 0.5 : 1.0);
                reStr.push(str.substr(i, 1));
                if (nowLen >= len && i != str.length && sfLen != 0) {
                    for ( var j = i; j >= 0; j--) {
                        nowLen -= (str.charCodeAt(i) <= 255 ? 0.5 : 1.0);
                        reStr.pop();
                        if (nowLen + sfLen <= len) {
                            break;
                        }
                    }
                    reStr.push(sf);
                    break;
                }
            }
            return reStr.join('');
        },
        /**
         * 移除所有的html标签
         */
        removeHtmlTags : function(html) {
            return html.replace(/\<.+?\>|&[a-zA-Z0-9]{2,5};/g, '');
        }

    };
})(jQuery)