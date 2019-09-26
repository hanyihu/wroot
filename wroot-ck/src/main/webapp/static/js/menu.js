$.fn.extend({
	menu:function(){
		var opts ={
			canClosed :'&nbsp<span class="glyphicon glyphicon-minus pull-right"></span>',
			canOpened:'&nbsp<span class="glyphicon glyphicon-plus pull-right"></span>'
		};
		var $menu = $(this);
		

		
		/*// 父菜单选中的时候  样式以及图标的改变
		$(" > li > a", $menu).next().hide().end().append(opts.canOpened).on("click", function(){
		 	$(this).toggleClass("active").next("ul").slideToggle(250).parent("li").siblings().children("a").toggleClass("active", false);
		 	$("span", $(this)).toggleClass("glyphicon-minus","glyphicon-plus" );
		});*/
		$(" > li > ul", $menu).not(function(i, e){	return $(e).has("li.current").size() == 1;}).hide().siblings().append(opts.canOpened);
		
		$(" > li > ul", $menu).not(function(i, e){	return $(e).has("li.current").size() == 0;}).siblings().append(opts.canClosed);
		
		$(" > li > a", $menu).on("click", function(){
		 	$(this).toggleClass("active").next("ul").slideToggle(250).parent("li").siblings().children("a").toggleClass("active", false);
		 	$("span", $(this)).toggleClass("glyphicon-minus","glyphicon-plus" );
		});
		
		
		// 选择子菜单的时候 子菜单被选中状态 父菜单被选中  其他的取消选中状态
		$(".item li", $menu).on("click", function(){
			$(this).addClass("current").siblings().removeClass("current").closest("ul").siblings("a").addClass("active")
			.closest("li").siblings().children("a").removeClass("active").next().children().removeClass("current");
		});
		
		//展开当前菜单
		$(".left_menu li:has(a.current)").trigger("click").closest("ul").siblings("a").trigger("click");
		
	}
});

$(document).ready(function(){
	
	$(".left_menu").menu();//初始化左侧按钮
	var $menuDiv = $(".menu_div");
	var $menuBtn = $(".menu-btn");
	
	$menuBtn.on("click", function(){//响应式菜单按钮的点击事件
		$menuDiv.toggle();
	});
	
	$(window).resize(function() {//根据窗口大小显示或者隐藏菜单
		var hided = $(".menu-btn").is(":hidden");
		if(!hided){
			$menuDiv.hide();
		}else {
			$menuDiv.show();
		}

	}).trigger("resize");		
	
});
