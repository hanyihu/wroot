<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>镗镗锣用户注册</title>

<style type="text/css">
	.error-tip{
		width: 270px;
		padding: 2px 0;
	    margin: 0 auto;
	}

</style>
<%@include file="/page/jspf/head.jspf"%>

<link rel="stylesheet"  href="${ctx }/static/recommend/style.css" />
<script type="text/javascript" src="${ctx }/static/js/jquery.timer.js"></script>
<script type="text/javascript" src="${ctx }/static/recommend/supersized.3.2.7.min.js"></script>
<script type="text/javascript">

	$(function(){
		
		$(".connect p").eq(0).animate({"left":"0%"}, 600);
		$(".connect p").eq(1).animate({"left":"0%"}, 400);
		
		 $("form").validate({
			 ignore: ".ignore",
			 errorElement:"label",//指定使用什么标签标记错误。
				errorPlacement: function(error, element){//跟一个函数，可以自定义错误放到哪里。
					 $(element).closest('div').find(".error-tip").append(error);
				},
		 });
		 
		 //发送手机验证码
		 $(".get-code").on("click", function(){
				var mobile = $("#mobile").val();
				if(! mobile ||  !(/^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/).test(mobile)) {
					alert("请输入正确的手机号码");	
					return;	
				}
				$nojs.smscode.init();
				var url = "${ctx}/api/auth/smscode";
				console.info(url);
				$.ajax(url, {
					type: "GET",
					dataType: "json",
					data:{mobile: mobile},
				}).done(function(result){
					console.info(result);
				}).fail(function(){
					alert("短信发送失败");
				});
		 })
	});
	
</script>
</head>
<body>
	<div class="register-container">
		<h1>镗镗锣</h1>
		
		<div class="connect">
			<p>欢迎注册镗镗锣平台</p>
		</div>
		<form  method="post" id="registerForm" class="" role="form">
			<div>
				<input type="password" name="password" class="password" id="password" placeholder="输入密码" oncontextmenu="return false" onpaste="return false" 
				data-rule-required="true" data-msg-required="密码不能为空" data-rule-rangelength ="[6,24]" data-msg-rangelength="密码长度6-24"/>
				<div class="error-tip"></div>
			</div>
			<div>
				<input type="password" name="confirm_password"	class="confirm_password" placeholder="再次输入密码" oncontextmenu="return false" onpaste="return false" 
					data-rule-equalTo="#password" data-msg-equalTo="两次密码不一致" />
				<div class="error-tip"></div>
			</div>
			<div>
				<input type="text" name="mobile" class="phone_number"	placeholder="输入手机号码" autocomplete="off" id="mobile" 
					data-rule-required="true" data-msg-required="手机号不能为空"  data-rule-mobile="true" data-msg-mobile="请输入正确的手机号"/>
				<div class="error-tip"></div>
			</div>
			<div>
				<input type="text" name="smscode" class="username"	placeholder="输入手机验证码" oncontextmenu="return false"	onpaste="return false" 
					data-rule-required="true" data-msg-required="验证码不能为空" data-rule-rangelength ="[6,6]" data-msg-rangelength="验证码长度6"/>
				<div class="error-tip"></div>
					
				<button class="btn btn-small get-code btn-hover" type="button" >获取验证码</button>
			</div>

			<button class="btn-hover" id="submit" type="submit">注 册</button>

		</form>
		<br>
		<div style="color: white;font-size: 18px;">
				<c:if test="${not empty  errorMsg}"></c:if>
			 	  ${errorMsg }
		</div>

	</div>
	
	<script type="text/javascript">
		$(function(){
			$.supersized({
	
		        // 功能
		        slide_interval     : 4000,    // 转换之间的长度
		        transition         : 1,    // 0 - 无，1 - 淡入淡出，2 - 滑动顶，3 - 滑动向右，4 - 滑底，5 - 滑块向左，6 - 旋转木马右键，7 - 左旋转木马
		        transition_speed   : 1000,    // 转型速度
		        performance        : 1,    // 0 - 正常，1 - 混合速度/质量，2 - 更优的图像质量，三优的转换速度//（仅适用于火狐/ IE浏览器，而不是Webkit的）
	
		        // 大小和位置
		        min_width          : 0,    // 最小允许宽度（以像素为单位）
		        min_height         : 0,    // 最小允许高度（以像素为单位）
		        vertical_center    : 1,    // 垂直居中背景
		        horizontal_center  : 1,    // 水平中心的背景
		        fit_always         : 0,    // 图像绝不会超过浏览器的宽度或高度（忽略分钟。尺寸）
		        fit_portrait       : 1,    // 纵向图像将不超过浏览器高度
		        fit_landscape      : 0,    // 景观的图像将不超过宽度的浏览器
	
		        // 组件
		        slide_links        : 'blank',    // 个别环节为每张幻灯片（选项：假的，'民'，'名'，'空'）
		        slides             : [    // 幻灯片影像
		                                 {image : '${ctx}/static/recommend/1.jpg'},
		                                 {image : '${ctx}/static/recommend/2.jpg'},
		                                 {image : '${ctx}/static/recommend/3.jpg'}
		                       ]
	
		    });	
			
		})
	
	</script>
</body>
</html>
