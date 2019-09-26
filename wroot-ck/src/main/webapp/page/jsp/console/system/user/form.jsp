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
			<label for="firstname" class="col-sm-2 control-label">用户名*</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="username" name="username" value="${entity.username }" 
					data-rule-required="true"	data-rule-remote="${ctx}/console/system/user/check?id=${entity.id}" data-msg-remote="名称重复" 
					placeholder="请输入用户名">
			</div>
			<div class="col-sm-4 help-inline form-control-static"></div>
		</div>

		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">昵称</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="nickname" name="nickname" value="${entity.nickname }" data-rule-required="true"	placeholder="昵称">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">电话</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="mobile" name="mobile" value="${entity.mobile }" 
						placeholder="电话">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">管辖城市*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="cityId" name="cityId" data-value="${entity.cityId }" >
					<option value="">--全国--</option>
					<c:forEach items="${cityList }" var="item">
					<option value="${item.id }">${item.name }</option>
					</c:forEach>
				</select>
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
