/**
 * 一些可能跟着项目走的东西
 */
;
(function($, $nojs) {
	if(!$ || !$nojs) {
		try {
			console.warn("jQuery or $nojs is not define ");
		} catch(e) {
			console.warn("what happend ");
		}
	}
	//1 -随机标签的颜色
	$nojs.randomLabel = {
	    enable:true,
	    selector: "span[data-random-label]",
	    labelClass:['label label-primary',  'label label-success', 'label label-warning',  'label label-info','label label-danger', 'label label-default' ],
	    init: function (context) {
	    	var model = this;
	    	$(model.selector, context).each(function(){
	    		var $label = $(this);
	    		var lableNumer = $label.data("randomLabel");
	    		if(isNaN(lableNumer)) {//此处不再做额外的处理
	    			lableNumer = 0;
	    		}
	    		var index = lableNumer % 6;
	    		$label.addClass(model.labelClass[index]);
	    		
	    	});
	    }
	};
	
	
	//2 弹出图片原图在窗口中间
	$nojs.popPicture = {
		enable:true,
		selector: "img.pop",
		$popHtml:$('<div class="pop-outer-div" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;"><div class="inner-div" style="position:absolute;"><img style="border:5px solid #fff;" src="" /></div></div>'),
		init: function(context){
			var model = this;
			console.info($(model.selector, context).size());
			$(model.selector, context).each(function(){
				var $img = $(this);
				var src = $img.attr("src");
				if(!src) return true;//此处不能return false 不然会中断循环 也不能使用continue  jquery.each不支持
				$img.on("click", function(){
					var $popOuter = $img.data("vic-pop-img");
					if(!$popOuter || $popOuter.size()===0) {
						$popOuter = model.$popHtml.clone();
						$img.data("vic-pop-img", $popOuter);
						 $("body").append($popOuter);
					}
					var $popInner = $(".inner-div", $popOuter);
					var $popImg =  $("img", $popOuter);
					
					$popImg.attr("src", src).load(function(){
						var windowW = $(window).width(); //获取当前窗口宽度  
						var windowH = $(window).height(); //获取当前窗口高度  
						var realWidth = this.width; //获取图片真实宽度  
						var realHeight = this.height; //获取图片真实高度  
						var imgWidth, imgHeight;
						var scale = 0.8; //缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放  

						if(realHeight > windowH * scale) { //判断图片高度  
							imgHeight = windowH * scale; //如大于窗口高度，图片高度进行缩放  
							imgWidth = imgHeight / realHeight * realWidth; //等比例缩放宽度  
							if(imgWidth > windowW * scale) { //如宽度扔大于窗口宽度  
								imgWidth = windowW * scale; //再对宽度进行缩放  
							}
						} else if(realWidth > windowW * scale) { //如图片高度合适，判断图片宽度  
							imgWidth = windowW * scale; //如大于窗口宽度，图片宽度进行缩放  
							imgHeight = imgWidth / realWidth * realHeight; //等比例缩放高度  
						} else { //如果图片真实高度和宽度都符合要求，高宽不变  
							imgWidth = realWidth;
							imgHeight = realHeight;
						}
						
						$popImg.css("width", imgWidth); //以最终的宽度对图片缩放  

						var w = (windowW - imgWidth) / 2; //计算图片与窗口左边距  
						var h = (windowH - imgHeight) / 2; //计算图片与窗口上边距  
						$popInner.css({
							"top": h,
							"left": w
						}); //设置innerdiv的top和left属性  
						$popOuter.fadeIn("fast"); //淡入显示   
					
					});
					$popOuter.click(function() { //再次点击淡出消失弹出层  
						$(this).fadeOut("fast");
					});
					
				});
				
				
			});
		}
			
	};
	

})(jQuery, $nojs);