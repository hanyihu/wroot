<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf" %>
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<title>后台登陆</title>
		<%@include file="/page/jspf/head.jspf" %>
		<script type="text/javascript" src="${ctx }/static/js/jquery.cookie.js"></script>
		<script type="text/javascript">
		$(function(){//简单的记住我
			if ($.cookie("rmbMe") == "true") {  
		        $("#rmbMe").prop("checked", true);  
		        $("#username").val($.cookie("username")); 
		        $("#password").val($.cookie("password"));  
	   		}  
					
			$("form").on("submit", function(){
				var rmbMe = $("#rmbMe").is(':checked');
				if(rmbMe) {
					var username = $("#username").val(); ;
					var password = $("#password").val(); ;
					$.cookie("rmbMe", "true", { expires: 7 }); 
   				    $.cookie("username", username, { expires: 7 });   
					$.cookie("password", password, { expires: 7 });   
				}else {
					$.cookie("rmbMe", "false", { expires: -1 }); 
   				    $.cookie("username", '', { expires: 7 });   
					$.cookie("password", '', { expires: -1 });   
				}
				
			});
		});
		</script>
	</head>

	<body style="background: #525252;">
		<div class="container">
			<div class="row clear text-center" >
				<div class="col-md-10 col-md-offset-1 column  ">
					<h3 style="color: palegreen">镗镗锣 <small style="color: mistyrose;">后台登录页面</small>	</h3>
					<div class="login-layout">
						<div class="row clearfix">
							<hr style="width: 80%;" />
							<div class="col-xs-12 col-sm-12 column">
								<form class="form-horizontal validate" role="form" method="post" action="${ctx}/console/login" >
									<div class="form-group">
										<label for="username" class="col-xs-3 col-sm-3 control-label">账号：</label>
										<div class="col-xs-8 col-sm-8">
											<input class="form-control" id="username" name="username" type="text" data-msg-required=" " data-rule-required="true" />
										</div>
									</div>
									
									<div class="form-group">
										<label for="password" class="col-xs-3 col-sm-3 control-label">密码：</label>
										<div class="col-xs-8 col-sm-8">
											<input class="form-control" id="password" name="password" type="password" data-rule-required="true" data-msg-required=" "/>
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-xs-offset-2 col-sm-offset-2   col-sx-9 col-sm-9">
											<div class="checkbox  pull-left">
												<label><input type="checkbox" id="rmbMe"/> 记住我</label>
											</div>
											<button type="submit" class="btn btn-info">登录</button>
										</div>
									</div>
									<div class="text-danger">
									 	  ${loginMsg }
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>