<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<%@include file="/page/jspf/head.jspf"%>
<title><spring:message code="application.title" /></title>
<script type="text/javascript">
	
</script>
</head>
<body>

	<div class="jumbotron">
		<div class="container">
			<h1> ${empty code ? 500 : code}错误</h1>
			<p>对不起，出错啦！</p>
			
			<p>您访问的页面出错，服务器返回错误信息：</p>
			<pre class="alert alert-danger">${exception.message}</pre>
			<br>
			<p><small>求个美工写页面吧。</small></p>
			<p>
				<a href="javascript:window.history.back();" class="btn btn-primary btn-lg" role="button">  Go Back</a>
				<a href="${ctx }/" class="btn btn-inf btn-md" role="button">  Go Back</a>
			</p>
		</div>
	</div>
</body>
</html>
