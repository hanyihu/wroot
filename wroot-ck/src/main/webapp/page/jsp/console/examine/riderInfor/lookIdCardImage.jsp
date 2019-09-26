<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
<%@include file="/page/jspf/head.jspf"%>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<div class="row row-nocol">
		<c:forEach items="${idList }" var="item" varStatus="statu" >
			<img  width="10%" height="10%" data-attachment="${item}" class="amplifier" />
			&nbsp; &nbsp; &nbsp;
		</c:forEach>

	</div>

	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
