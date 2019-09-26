<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/page/jspf/prepare.jspf" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="application.title"/></title>
    <%@include file="/page/jspf/head.jspf" %>
    <script type="text/javascript">

    </script>
</head>
<body>
<%@include file="/page/jspf/console-body-first.jspf" %>
<div class="row row-nocol">
    <hr/>
    <a href="${ctx}/console/test/send/">点击发送消息</a>

</div>
<%@include file="/page/jspf/console-body-last.jspf" %>
</body>
</html>
