var ztreeSetting = {
	view:{showLine :false},
	callback : {
		
		}
};

$(function(){
	$("ul.ztree:not(.self)").each(function(){
		var $ul = $(this);
		var url = $ul.data("url");
		
		var curId = $ul.data("cur");//当前选选中的节点
		var json = $ul.data("json");//同步加载树所需的节点数据
		
		var opts = $ul.data("options");
		if(opts){
			opts = "{" + opts + "}";
			console.info(opts);
			try{
				opts =  eval('(' + opts + ')');
			}catch(Exception){
				console.error("转化ztree的options到json出错");
				opts = {};
			}
		}else {
			opts = {};
		}
		
		if(json){//同步加载树
			initTree($ul, opts, json, curId);
		}
		
		if(!url){
			return;
		}
		
		$.ajax(url,{//异步加载树
			type: "POST",
			dataType: "JSON"
		}).done(function(data){
			if(data && data.code == 0) {
				initTree($ul, opts, data.data, curId);
			}
		}).fail(function(){
			
		});
	});
	
	function initTree($tree, opts, nodes, curId){
		console.info(JSON.stringify(opts));
		var setting = $.extend(true, {}, ztreeSetting, opts);
		console.info(JSON.stringify(setting));
		var zTreeObj = $.fn.zTree.init($tree, setting, nodes);
		$tree.data("ztree", zTreeObj);
		if(curId){
			zTreeObj.selectNode(zTreeObj.getNodeByParam("id", curId, null)); 
		}
	}
	
});
