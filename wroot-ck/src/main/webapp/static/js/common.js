/**
 * 一些公用的操作
 */
(function(){
	var $common = {};
	/**
	 * 显示提示框
	 */
	$common.tipDialog = function(title, msg, callback){
		title = title || "提示信息";
		msg = msg || "OK";
		callback = callback || $.noop;
		var $dialog = $('<div  title="' + title + '" >' + msg + '</div>');
		$dialog.dialog({
			resizable: false,
			height: 180,
			modal: true,
			buttons: {
				'确认': function() {
					callback.call();
					$(this).dialog("close");
					$dialog.remove();
				},
				
				'关闭': function() {
					$(this).dialog("close");
					$dialog.remove();
				}
			}
		});
	};
	window.$common = $common;
})();
