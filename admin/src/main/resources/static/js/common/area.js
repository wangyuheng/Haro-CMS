/**
 * 地区的三级联动 使用方法页面中引用该js, $(function(){
 * Edu.Area.init({defaultData:['110000','110100','110108']}); }); <select
 * class="provinceSelect"></select> <select class="citySelect"></select>
 * <select class="countySelect"></select>
 * 
 * @author houpo@chinaedu.net
 */
if (typeof Edu == 'undefined') {
    var Edu = {};
}
if (typeof _basePath == 'undefined') {
    _basePath = '';
}
Edu.Area = {
    settings : {
        url : _basePath + '100/136/204.do',
        provinceClass : '.provinceSelect',
        cityClass : '.citySelect',
        countyClass : '.countySelect',
        defaultData : []
    },
    provinceData : [],
    loading : false,
    init : function(config) {
        $.extend(this.settings, config);
        if (this.provinceData.length > 0) {
            if ('defaultData' in config) {
                this.setValue(config.defaultData);
            }
            return;
        }
        if (!this.loading) {
            this.initData();
        }
    },
    // 设置值
    setValue : function(dataArr) {
        var $this = this;
        if ($(this.settings.provinceClass).length > 0) {
            this.setProvince();
            if (dataArr.length > 0) {
                $($this.settings.provinceClass).val(dataArr[0]);
                $($this.settings.provinceClass).trigger("change");
                dataArr.length > 1 ? $($this.settings.cityClass).val(dataArr[1]) : '';
                $($this.settings.cityClass).trigger("change");
                dataArr.length > 2 ? $($this.settings.countyClass).val(dataArr[2]) : '';
            }
        }
    },
    initData : function() {
        this.loading = true;
        var $this = this;
        ajaxSubmit({
            url : $this.settings.url,
            params : {},
            onSuccess : function(data) {
                $this.disposeData(data.areaData);
                $this.setValue($this.settings.defaultData);
            }
        });
    },
    // 处理数据
    disposeData : function(data) {
        var nowPro = null;
        var nowCity = null;
        for ( var i = 0; i < data.length; i++) {
            if (!data[i][1]) {
                nowPro = {
                    code : data[i][0],
                    name : data[i][2],
                    id : data[i][3],
                    citys : []
                };
                this.provinceData.push(nowPro);
            } else {
                if (data[i][1] == nowPro.code) {
                    nowCity = {
                        code : data[i][0],
                        name : data[i][2],
                        id : data[i][3],
                        countys : []
                    };
                    nowPro.citys.push(nowCity);
                } else if (data[i][1] == nowCity.code) {
                    nowCity.countys.push({
                        code : data[i][0],
                        name : data[i][2],
                        id : data[i][3]
                    });
                }
            }
        }
    },
    bindEvent : function() {
        $(this.settings.provinceClass).change($.proxy(this, 'setCityData'));
        $(this.settings.cityClass).change($.proxy(this, 'setCountyData'));
    },
    setProvince : function() {
        var pselect = $(this.settings.provinceClass);
        var cselect = $(this.settings.cityClass);
        var couselect = $(this.settings.countyClass);
        pselect.empty();
        cselect.empty();
        couselect.empty();
        this._addOption(cselect[0], "", "请选择市...");
        this._addOption(couselect[0], "", "请选择区/县...");
        this._addOption(pselect[0], "", "请选择省...");
        for ( var i = 0; i < this.provinceData.length; i++) {
            this._addOption(pselect[0], this.provinceData[i].id,
                    this.provinceData[i].name);
        }
    },
    setCityData : function() {
        var pselect = $(this.settings.provinceClass);
        var cselect = $(this.settings.cityClass);
        var couselect = $(this.settings.countyClass);
        var province = this.provinceData[pselect[0].selectedIndex - 1];

        cselect.empty();
        couselect.empty();
        this._addOption(couselect[0], "", "请选择区/县...");
        this._addOption(cselect[0], "", "请选择市...");
        if (!province || !province.citys.length) {
            cselect.attr("disabled", "disabled");
            couselect.attr("disabled", "disabled");
        } else {
            cselect.removeAttr("disabled");
            couselect.removeAttr("disabled");
            for ( var i = 0; i < province.citys.length; i++) {
                this._addOption(cselect[0], province.citys[i].id, province.citys[i].name);
            }
        }
    },
    setCountyData : function() {
        var pselect = $(this.settings.provinceClass);
        var cselect = $(this.settings.cityClass);
        var couselect = $(this.settings.countyClass);
        var province = this.provinceData[pselect[0].selectedIndex - 1];
        var city = province ? province.citys[cselect[0].selectedIndex - 1] : null;
        couselect.empty();
        this._addOption(couselect[0], "", "请选择区/县...");
        if (!city || !city.countys.length) {
            couselect.attr("disabled", "disabled");
        } else {
            couselect.removeAttr("disabled");
            for ( var i = 0; i < city.countys.length; i++) {
                this._addOption(couselect[0], city.countys[i].id, city.countys[i].name);
            }
        }

    },
    _addOption : function(select, value, text) {
        var y = document.createElement('option');
        y.text = text;
        y.value = value;
        try {
            select.add(y, null);
        } catch (ex) {
            select.add(y);
        }
    }

}

Edu.Area.initData();// 加载数据

$(function() {
    Edu.Area.bindEvent();
    if ($(Edu.Area.settings.provinceClass + ' option').length < 2) {
        Edu.Area.setValue(Edu.Area.settings.defaultData);
    }
});
