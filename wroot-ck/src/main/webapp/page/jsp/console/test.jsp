<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="UTF-8">
<title>test</title>
<%@include file="/page/jspf/head.jspf"%>
</head>
<body>
	<h2>后台</h2>
	<form class="validate" action="${ctx }/login">
		<div class="form-control">
			<input type="text" name="username" data-rule-required="true">
		</div>
		<div class="form-control has-error">
			<input type="text" name="password" data-rule-required="true">
		</div>
		<button type="submit" class="btn btn-info">登录</button>
	</form>
</body>
</html>
