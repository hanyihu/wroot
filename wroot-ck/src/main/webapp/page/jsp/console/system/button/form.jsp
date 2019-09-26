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
			<label for="firstname" class="col-sm-2 control-label">名称</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="name" name="name" value="${entity.name }" 
					data-rule-required="true"	data-rule-remote="${ctx}/console/system/btn/check?id=${entity.id}" data-msg-remote="名称重复" 
					placeholder="请输入名称">
			</div>
			<div class="col-sm-4 help-inline form-control-static"></div>
		</div>

		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">编码</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="code" name="code" value="${entity.code }" 
					data-rule-required="true"	placeholder="请输入名称">
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
