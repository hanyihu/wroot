/**
 * 放大图片
 */
jQuery(function ($) {
    var $body = $("body");
    
    //1 鼠标触发  显示原图  在鼠标的左下方
    var $template = $("<img style='display:none;' src='' alt=''/>");
    $("body").on({//绑定未来事件
    	mouseover:function(e){

            var $this = $(this);
            var $amplifier = $this.data("vic-dashboard-amplifier");
            if (!$amplifier) {
                $amplifier = $template.clone();
                $amplifier.attr("src", this.src);
                $this.data("vic-dashboard-amplifier", $amplifier);
                $body.append($amplifier);
            }
            $amplifier.css({
                "z-index":1,
                "position": "absolute",
                "top": (e.pageY + 10) + "px",
                "left": (e.pageX + 10) + "px"
            }).show("fast");
        
    	},
    	mouseout: function(e){
            var $this = $(this);
            var $amplifier = $this.data("vic-dashboard-amplifier");
            if ($amplifier) {
                $amplifier.hide();
            }
        
    	},
    	mousemove: function(e){
    		 var $this = $(this);
    	        var $amplifier = $this.data("vic-dashboard-amplifier");
    	        if ($amplifier) {
    	            $amplifier.css({
    	                "top": (e.pageY + 10) + "px",
    	                "left": (e.pageX + 10) + "px"
    	            });
    	        }
    	}
    	
    },"img.amplifier");
    
    
    
    
});