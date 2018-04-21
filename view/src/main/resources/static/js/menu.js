jQuery(function($){
	$(".menu").children("ul").children("li").hover(
		function(){
			var obj = $(this).children("div.submenu");
			obj.fadeIn("600");
			$(this).children("a:first").addClass("onmouse");
			
			var pos = obj.offset();
			var pos1 = $(".box").offset();
			var x = ($(".box").width()+pos1.left) - (pos.left+obj.width());
			
		},
		function(){
			$(this).children("div.submenu").hide();
			$(this).children("a:first").removeClass("onmouse");
		}
	);
});