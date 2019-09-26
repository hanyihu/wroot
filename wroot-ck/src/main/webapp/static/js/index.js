$(function() {
	/*//复制列表
	var $item = $(".post_list .post_item");
	$(".post_list").append($item.clone()).append($item.clone()).append($item.clone());

	$("#t1").datetimepicker();*/

	/***
	 *博客站点首页js 
	 */
	/*获得新浪天气*/
	var url = "http://sapi.sina.cn/ls/allasync?ver=3";
	$.ajax({
		url: url,
		type: "get",
		timeout: 60000,
		dataType: "jsonp",
		success: function(record, type) {
			var d = eval(record);
			var img = d.retData.weather.icon_v3; //图标 icon icon_v3
			var city = d.retData.weather.city;
			var up = d.retData.weather.up;
			var down = d.retData.weather.down;

			var e = $("#tweather");
			if(img) {
				e.empty();
				e.append("<div id='wicon' class='pull-right' style='margin-right:5px'><img src='" + img + "' width=20 height=20 />&nbsp;" + city + "&nbsp; " + down + "&nbsp;~&nbsp;" + up + "℃</div>");
			}

		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {}
	});
	
	/**显示和隐藏查询框 并去掉搜索框的值*/
	$("#for-nav-search").on("click", function(){
		$("#nav-search").toggle(400);
		$("#nav-search").find(":input").val("");
		return false;
	});
	
	/**点击查询按钮进行查询*/
	$("#nav-search-btn").on("click", function(){
		var keywords = $(this).closest("form").find(":input").val();
		if(!keywords) return;
		$.pjax({url: ctx+"/site/search", container: '#content',timeout: 8000,data:{keywords : keywords}});
	});
	
	
	
	/*******PJAX start**********/
	var pjaxObj = {cache: false, timeout: 50000};
	$(document).on('click', 'a[target!=_blank][data-pjax]', function() {
		var url = $(this).attr("href");
		var container = $(this).data("pjax");
		$.pjax({
			url: url,
			container: container,
			//fragment: containerId,
			timeout: 8000
		});

		return false;
	});
	
	
	 $(document).on('pjax:start', function () {
	     //   $("#content").hide();
	        $('#load-dialog').modal('show');
	    }).on("pjax:end", function () {
	        var $content = $("#content");
	        $content.find("a[data-pjax]").pjax(pjaxObj);
	        $('#load-dialog').modal('hide');
         //   $content.show();
	        /*setTimeout(function () {
	            $('#load-dialog').modal('hide');
	            $content.show(400);
	        }, 200);*/
	    });
	
	
	
	//PJAX跳转到第二级
	if(curUrl){
		$.pjax({url: curUrl, container: '#content',timeout: 8000});
	}
	

});