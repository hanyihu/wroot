<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>后台</title>
<%@include file="/page/jspf/head.jspf"%>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
	
		<div class="form-group">
			<label for="old" class="col-sm-2 control-label">原密码</label>
			<div class="col-sm-6">
				<input type="password" class="form-control" id="old" name="old"  	data-rule-required="true"	placeholder="请输入原密码">
			</div>
			<div class="col-sm-4 help-inline form-control-static"></div>
		</div>

		<div class="form-group">
			<label for="password" class="col-sm-2 control-label"><span class="text-danger">*</span>新密码</label>
			<div class="col-sm-6 ">
				<input type="password" class="form-control" id="password" name="password" 	data-rule-required="true"	placeholder="请输入新密码">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		<div class="form-group">
			<label for="password2" class="col-sm-2 control-label"><span class="text-danger">*</span>确认新密码</label>
			<div class="col-sm-6 ">
				<input type="password" class="form-control" id="password2" data-rule-equalTo="#password" placeholder="确认新密码">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>


		<div class="form-group clearfix">
			<div class="col-sm-offset-3 col-sm-9">
				<button type="submit" class="btn btn-primary">
					<i class="glyphicon glyphicon-ok"></i> 提交
				</button>
				<button type="button" class="btn btn-info action-back">
					<i class="glyphicon glyphicon-repeat"></i> 返回
				</button>
			</div>
		</div>

	</form>
	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
