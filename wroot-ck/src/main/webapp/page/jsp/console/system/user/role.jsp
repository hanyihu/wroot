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
                <label class="col-sm-2 control-label" for="categoryPks">角色：</label>
                <div class="col-sm-6">
                    <c:forEach items="${roles}" var="cur" varStatus="status">
                        <label><input type="checkbox" name="roles" value="${cur.id}"  <c:if test="${cur.checked }"> checked="checked" </c:if> >${cur.name}&nbsp;</label>
                     </c:forEach>
                </div>
                <label class="col-sm-4 help-inline form-control-static" for="categoryPks"></label>
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
