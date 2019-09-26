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
			<h1>404！</h1>
			<p>这是一篇荒芜之地。</p>
			<p>
				<a class="btn btn-primary btn-lg" role="button" onclick="javascript:history.back(-1);"> 点我返回</a>
			</p>
		</div>
	</div>
</body>
</html>
