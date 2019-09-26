/*
 * 按钮权限控制
 */
$(function() {
	var checkBtn = {
		selector : "[data-btn]",
		check : function() {
			var currentUrl = window.location.href;
			/*var temHtx = htx.replace(":80/","/")//防止80端口的省略
			currentUrl = currentUrl.replace(":80/","/");*/
			currentUrl = currentUrl.substring(htx.length,currentUrl.length);
			var  btns =btnJson[currentUrl];
			var $controls = $(this.selector);
			if(!btns){
				$controls.hide();
				return;
			}
			$controls.each(function(){
				var $btn = $(this);
				var control = $btn.data("btn");
				if(btns[control]){
					$btn.show();
				}else {
					$btn.hide();
				}
			});
		}
	}.check();
});