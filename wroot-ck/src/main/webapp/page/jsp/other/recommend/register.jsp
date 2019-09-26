<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="0">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>镗镗锣用户注册</title>

<style type="text/css">
	.error-tip{
		width: 368px;
		padding: 2px 2px;
	    margin: 5px auto;
	   /*  text-align:center; */
	}

</style>
<%@include file="/page/jspf/head.jspf"%>

<link rel="stylesheet"  href="${ctx }/static/recommend/css/style.css" />
<link rel="stylesheet"  href="${ctx }/static/recommend/css/common.css" />
<script type="text/javascript" src="${ctx }/static/js/jquery.timer.js"></script>
<script type="text/javascript">

	$(function(){
		document.getElementById("registerForm").reset(); 
		 $("form").validate({
			 ignore: ".ignore",
			 errorClass:"help-inline text-danger",
			 errorElement:"label",//指定使用什么标签标记错误。
				errorPlacement: function(error, element){//跟一个函数，可以自定义错误放到哪里。
					 $(element).closest('li').find(".error-tip").append(error);
				},
		 });
		 
		 //发送手机验证码
		 $("#smscode").on("click", function(){
				var mobile = $("#mobile").val();
				console.info(moblie) 
				//if(! mobile ||  !(/^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/).test(mobile)) {
				if(!mobile ||  !(/^1[3|4|5|7|8][0-9]\d{8}$/).test(mobile)) {
					alert("请输入正确的手机号码!");	
					return false;	
				}
				$nojs.smscode.init();
				var url = "${ctx}/api/auth/smscode";
				console.info(url);
				$.ajax(url, {
					type: "GET",
					dataType: "json",
					data:{mobile: mobile},
				}).done(function(result){
					//console.info(result);
				}).fail(function(){
					alert("短信发送失败");
				});
		 })
		 
		 $(".resiger p i").click(function(){
		  		$(this).toggleClass("on");
			});
	});
	
</script>
</head>
<body >

	<div class="header">
		<!-- <h1>注册镗镗锣</h1> -->
	</div>
	<!--头部end-->
	
	<!--图片satrt-->
	<div class="img">
	  <img src="${ctx }/static/recommend/images/img_02.jpg" alt="" />
	</div>
<!--图片end-->
	
	
	<!--注册start-->
	<div class="resiger">
		<div style="color: red;font-size: 18px;text-align:center; margin-top: 10px; ">
				<c:if test="${not empty  errorMsg}">
			 	  ${errorMsg }
			 	</c:if>
		</div>
		<form  method="post" id="registerForm" class="" role="form">
		
		  <ul class="form">
				<li>
					<input type="text" placeholder="请输入手机号码" class="text_info" name="mobile" id="mobile"
						data-rule-required="true" data-msg-required="手机号不能为空"  data-rule-mobile="true" />
					<div class="error-tip"></div>
					
				</li>
				<li>
					<input  type="password" name="password" placeholder="设置登录密码" class="text_info" 
						data-rule-required="true" data-msg-required="密码不能为空" data-rule-rangelength ="[6,24]" data-msg-rangelength="密码长度6-24"/>
					<div class="error-tip"></div>
				</li>
				<li>	
					<input type="text" name="smscode" placeholder="短信验证码" class="text_info" 
						data-rule-required="true" data-msg-required="验证码不能为空" data-rule-rangelength ="[6,6]" data-msg-rangelength="验证码长度6"/>
					<a href="javascript:;" class="get_code" data-smscode="true"><span id="smscode">获取验证码</span></a>
					<div class="error-tip"></div>
				</li>
				<li><p><i class="on"></i><input type="checkbox" hidden /> 注册代表您阅读并同意 <a href="${ctx}/other/recommend/protocol" title="">《镗镗锣用户协议》</a></p>
					<div class="error-tip"></div>
				</li>
			</ul>
			<div class="btn">
			  	<input type="submit" value="注册" />
					<p>先注册  后下载APP</p>
			</div>
		</form>
		
	</div>



	
	
</body>
</html>
