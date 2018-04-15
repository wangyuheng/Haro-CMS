var tab = null;
var accordion = null;
var tree = null;			
$(function (){

	//自适应

	$('#container').ligerLayout({leftWidth: 200, height: '100%', onHeightChanged: f_heightChanged});
	//手风琴菜单

	G3bgAccordion.show({parentElem:'#accordion-box',clickElem:'.accordion-hd',showElem:'.accordion-ct'});
	G3bgAccordion.fold({parentElem:'#accordion-box',clickElem:'.accordion-hd',showElem:'.accordion-ct'});
	//tree菜单
	$('.menuTree').G3bgTree();
	//navTab
	$('#navTabBox').G3bgNavtab();
	//toggle
	$('.btn-toggle').G3bgToggle();
	//通用tab
	$('.tabWrap').G3bgTabs();
	//select
	$('.select-box').G3bgCombox({callback:function(el){		
		if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
			el.find('.select-ct').bgiframe();
			}		
		}	
	});	
	//表格css
	$('.grid-01').G3bgTableUI();
	$('.grid-03').G3bgTableUI();
	//IE6去除iframe横向滚动
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height())
	{ 
		$("html").css("overflowY","scroll"); 
	}
	//执行面包屑

	Breadcrumbs();
	$(".leftFrameMenu li").bind("click", function() {
		if($(this).attr("url")){
			f_addTab($(this).attr("url"), $(this).text(), $(this).attr("url"));			
		}
	});
	$("#navTabBox").delegate("a.close","click",function(){
		var i=$(this).parent('li').index();
		var tabid=$(this).parents('li').prev().attr('tabid');			
		if($(this).parent('li').hasClass('current'))
		{
			$(this).parent('li').removeClass('current');
			$(this).parent('li').prev().addClass('current');
			$('.tabUnit').eq(i).prev().show();
			//关闭标签设置面包屑导航

		$("#breadCrumb", parent.document).html($('.navTab-panel').find('iframe').eq(i-1).contents().find('#breadCrumb').html());
		$(".leftFrameMenu li").removeClass('curr');
		$(".leftFrameMenu li[url='"+tabid+"']").addClass('curr');
		$("#accordion-box .accordion-ct").hide();
		$(".leftFrameMenu li[url='"+tabid+"']").parents('.accordion-ct').show();
		$("#accordion-box .accordion-hd").removeClass('collapsible');
		$(".leftFrameMenu li[url='"+tabid+"']").parents('.accordion-ct').prev().addClass('collapsible');
		}		
		$(this).parent('li').remove();
		$('.tabUnit').eq(i).remove();
		navTabScroll();
	});
	RegScrollFramesBtn();
	selectZindex();
	Breadcrumbs()
	//高级搜索显示隐藏

	$('.btn-ad-search').click(function(){	
		var _this = $(this).parents('form').find('.ad-search-box');
		if(_this.css('display')=="none")
		{
			_this.show();
			$(this).find('span').text('收起高级搜索')
			
		}else{
			_this.hide();
			$(this).find('span').text('高级搜索');
		}
	});
	//checkbox全选，反选
	$('.gridBox').delegate(':checkbox','click',function(){
		
			if($(this).hasClass('checkAll'))
			{
				$(this).parents('.gridBox').find('.cellBg-gary :checkbox').attr("checked",this.checked);
			}else
			{
				$(this).parents('.gridBox').find(".checkAll").attr("checked",$(this).parents('.gridBox').find('.cellBg-gary :checkbox:not(.checkAll)').length == $(this).parents('.gridBox').find('.cellBg-gary :checkbox:not(.checkAll):checked').length ? true : false);
			}
	});
	//resize
	$(window).resize(function(){
		G3bgAccordion.show({parentElem:'#accordion-box',clickElem:'.accordion-hd',showElem:'.accordion-ct'});
		navTabScroll();
		//执行页面内侧栏添加滚动条
		setSidebarHeight();
		//删除select标签的CLASS,fl
		$('select').removeClass('fl');
	});
	//
	$('.iptSearch i').click(function(){
		$(".popBox").not($(this).parent().find('.popBox')).hide();
		if($(this).parent().find('.popBox').css('display')=='none')
		{
			$(this).parent().find('.popBox').show();
		}else
		{
			$(this).parent().find('.popBox').hide();
		}
	});
	//点击关闭下拉框

	$(document).unbind().bind('click',function(event){
			var src_element = $(event.srcElement || event.target);
			
			if(!src_element.parents().hasClass('iptSearch'))
			{
				$('.popBox').hide();
				
			}
			
	});
	//双击关闭标签页

	$(".navTab-menu").delegate("li","dblclick",function(){
	  var i=$(this).index();
	  if(i>0){
		var tabid=$(this).prev().attr('tabid');			
		if($(this).hasClass('current'))
		{
			$(this).removeClass('current');
			$(this).prev().addClass('current');
			$('.tabUnit').eq(i).prev().show();
			//关闭标签设置面包屑导航

		$("#breadCrumb", parent.document).html($('.navTab-panel').find('iframe').eq(i-1).contents().find('#breadCrumb').html());
		$(".leftFrameMenu li").removeClass('curr');
		$(".leftFrameMenu li[url='"+tabid+"']").addClass('curr');
		$("#accordion-box .accordion-ct").hide();
		$(".leftFrameMenu li[url='"+tabid+"']").parents('.accordion-ct').show();
		$("#accordion-box .accordion-hd").removeClass('collapsible');
		$(".leftFrameMenu li[url='"+tabid+"']").parents('.accordion-ct').prev().addClass('collapsible');
		}		
		$(this).remove();
		$('.tabUnit').eq(i).remove();
		navTabScroll();
	  }
		
	});

	//右键菜单

	$('.navTab-menu li:first').contextmenu({
		items : [{
			text :'关闭其他标签页',

			action: function(target){
				var i=$(target).parents('li').index();	
				$(target).parents('li').addClass('current');
				$('.tabUnit').eq(i).show();
				$(target).parents('li').siblings().remove();
				$('.tabUnit').eq(i).siblings().remove();
				$("#breadCrumb", parent.document).html($('.navTab-panel').find('iframe').eq(i-1).contents().find('#breadCrumb').html());
				navTabScroll();
			}
		}]
	});
	$('.navTab-menu li:not(:first)').contextmenu({
		items : [{
			text :'关闭标签页',

			action: function(target){
				var i=$(target).parents('li').index();	
				var tabid=$(target).parents('li').prev().attr('tabid');				
				if($(target).parents('li').hasClass('current'))
				{
					$(target).parents('li').removeClass('current');
					$(target).parents('li').prev().addClass('current');
					$('.tabUnit').eq(i).prev().show();
					//关闭标签设置面包屑导航

				$("#breadCrumb", parent.document).html($('.navTab-panel').find('iframe').eq(i-1).contents().find('#breadCrumb').html());
				$(".leftFrameMenu li").removeClass('curr');
				$(".leftFrameMenu li[url='"+tabid+"']").addClass('curr');
				$("#accordion-box .accordion-ct").hide();
				$(".leftFrameMenu li[url='"+tabid+"']").parents('.accordion-ct').show();
				$("#accordion-box .accordion-hd").removeClass('collapsible');
				$(".leftFrameMenu li[url='"+tabid+"']").parents('.accordion-ct').prev().addClass('collapsible');
				};
				$(target).parents('li').remove();
				$('.tabUnit').eq(i).remove();
				navTabScroll();				
			}
		},{
			text :'关闭其他标签页',

			action: function(target){
				var i=$(target).parents('li').index();	
				var tabid=$(target).parents('li').attr('tabid');
				if($(target).parents('ul').find('li').length>2){
					$(target).parents('li').addClass('current');
					$('.tabUnit').eq(i).show();
					$(target).parents('li').siblings(':not(:first)').remove();
					$('.tabUnit').eq(i).siblings(':not(:first)').remove();
					$("#breadCrumb", parent.document).html($('.navTab-panel').find('iframe').eq(i).contents().find('#breadCrumb').html());
					$(".leftFrameMenu li").removeClass('curr');
					$(".leftFrameMenu li[url='"+tabid+"']").addClass('curr');
					$("#accordion-box .accordion-ct").hide();
					$(".leftFrameMenu li[url='"+tabid+"']").parents('.accordion-ct').show();
					$("#accordion-box .accordion-hd").removeClass('collapsible');
					$(".leftFrameMenu li[url='"+tabid+"']").parents('.accordion-ct').prev().addClass('collapsible');
					navTabScroll();
				}
			}
		}]
	});
	
	$('.tabUnit').click(function(){
		
	});
	//执行页面内侧栏添加滚动条
	setSidebarHeight();
	//删除select标签的CLASS,fl
	$('select').removeClass('fl');
	
	//首页功能点击操作
	$("body").delegate("#fn-content li", "click", function(){    
		var elemId=$(this).attr('tabid');
		$(".leftFrameMenu li[url='"+ elemId + "']",parent.document).trigger('click');
		$(".navTab-hd li[tabid='"+ elemId + "'] span",parent.document).trigger('click');		
	});

});

function f_heightChanged(options)
{
	if (tab)
		tab.addHeight(options.diff);
	if (accordion && options.middleHeight - 24 > 0)
		accordion.setHeight(options.middleHeight - 24);
}

/**
* 	标签操作

*/

//添加标签

function f_addTab(tabid,text, url)
{ 
	var i=0;
	$('.navTab-menu li').each(function() {
		if($(this).attr('tabid') == tabid) {
			i++;
		}
	});
	if(i>0){
		$('.navTab-menu li').each(function() {
			if($(this).attr('tabid')==tabid) {
				$(this).siblings().removeClass('current');
				$(this).addClass('current');
			}
		});
		$('.navTab-panel .tabUnit').each(function() {
			if($(this).attr('tabid')==tabid){
				$(this).siblings().hide();
				$(this).show();
				$(this).find('iframe').attr('src',url);
				$("#breadCrumb").html($("#breadCrumb", $(this).find("iframe").get(0).contentWindow.document).html());
			}
		});
	}else{
		var tabli='<li tabid="'+tabid+'"><a class="tab-l" href="javascript:;"><span>'+text+'</span></a><a class="close" href="javascript:;"></a></li>';
		var tabct='<div class="tabUnit"  tabid="'+tabid+'"><iframe src="'+url+'" frameborder="0" style="width:100%;height:100%"></iframe></div>'
		$('.navTab-menu').append(tabli);
		$('.navTab-panel').append(tabct);
		$('#navTabBox').G3bgNavtab();
		$(".navTab-menu li[tabid='"+tabid+"']").siblings().removeClass('current');
		$(".navTab-menu li[tabid='"+tabid+"']").addClass('current');
		$(".navTab-panel .tabUnit[tabid='"+tabid+"']").siblings().hide();
		$(".navTab-panel .tabUnit[tabid='"+tabid+"']").show();
		$('.navTab-panel .tabUnit').each(function(){
			$(this).height($(this).prev().height());
		});
	};
	var i=null;
	navTabScroll($(".navTab-menu li[tabid='"+tabid+"']"));
};



//判断标签滚动

$('.l-layout-header-toggle,.l-layout-collapse-left-toggle').live('click',function(){

	navTabScroll();
})
function navTabScroll(a)
{
	var liNum=0;
	var prevAllwidth=0;
	$('.navTab-menu li').each(function(){
		liNum+=$(this).width() +2;
	});
	if(liNum>$('.navTab-hd').width())
	{
		
		$('.btn-navTab-left,.btn-navTab-right').show();
		$('.navTab-menu').css('left',-(liNum-$('.navTab-hd').width()+20)+'px');
		var ulLeft=Number($('.navTab-menu').css('left').toLowerCase().replace("px", ""));
		

			prevAllwidth+=$(this).width()+2;
		
		

		if(-ulLeft>prevAllwidth)
		{
			
			$('.navTab-menu').css('left',-(prevAllwidth-18)+"px");
		}
		
		
	}else
	{
		$('.btn-navTab-left,.btn-navTab-right').hide();
		$('.navTab-menu').css('left',0+'px');
	};
	

}

function ScrollFrames(cW, fW, y)
{
	var jList = $(".navTab-hd ul:eq(0)");
	var ml = jList.css("left");
	var liNum=0;
	$('.navTab-menu li').each(function(){
		liNum+=$(this).width() +2;
	});
	ml = Number(ml.toLowerCase().replace("px", ""));
	
	ml = ml + y;
	jList.css("left", ml+'px');
	if(ml<-(liNum-$(".navTab-hd").width()))
	{
		jList.css("left", -(liNum-$(".navTab-hd").width()+20)+'px');
	}else if(ml>18){
		jList.css("left", 18+'px');
	}
}

//标签滚动事件

function RegScrollFramesBtn()
{
	$(".btn-navTab-right").click(function() { 
		ScrollFrames($('.navTab-hd').width(),$('.navTab-hd ul:eq(0)').width(),-100);
	});
	$(".btn-navTab-left").click(function() { 
		ScrollFrames($('.navTab-hd').width(),$('.navTab-hd ul:eq(0)').width(),100);
	});
}

/**
* 	面包屑导航

*/

//面包屑导航

function Breadcrumbs()
{
	$("#breadCrumb", parent.document).html($("#breadCrumb").html());
}

/**
* 	下拉菜单赋zindex

*/

function selectZindex()
	{
		var i=1999;
		$('.select-box,.iptSearch').each(function(){
			$(this).css('z-index',i);
			i--;
		});
		var i=null;
	}


/**
* 	页面内侧栏滚动条

*/
function setSidebarHeight(){
	var s=$('ul.areaSidebar');
	var t=$('#tabUnit_Set');
	if(s.length>0){
		s.height(s.parents('.l-layout-left').height()-50);
		s.width(s.parents('.l-layout-left').width()-10);
		s.css('overflow','auto');
	}
	if(t.length>0){
		t.height(t.parent('.l-layout-content').height());
		t.css('overflow-y','auto');
	}
}


/**
* 	隐藏过多文字，提示框显示被隐藏...
*   参数说明 className:(执行操作的元素) length:(显示的长度) width:(提示框的宽度) x:提示框X轴位置 y:提示框Y轴位置
*/
function showHideContent(className,width,x,y){
	
		var el=$('.'+className);
		el.hover(
			  function () {
				var con=$(this).attr('conlist');
				var offset = $(this).offset();
				var showBox='<div class="cBox" style="padding:10px;height:auto;background:#f3f2f2;z-index:9999;border:1px solid #2B66A4;position:absolute; width:'
					+ width + 'px;left:' + (offset.left + x) + 'px;top:' +	(offset.top + y) + 'px;">' + con +'</div>';
			    $(this).after(showBox);
			    
			  },
			  function () {
				  $(this).parent().find('.cBox').remove();
		});
	
	
}