<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
<%@include file="/page/jspf/head.jspf"%>
 <script type="text/javascript" src="${ctx }/static/js/jquery.fileupload.js"></script> 
 <script type="text/javascript" src="${ctx }/static/js/jquery.iframe-transport.js"></script>  
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
										
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">图片*</label>
			<div class="col-sm-6 ">
				<input type="hidden" name="image" value="${entity.image}" data-rule-required="true" /> 
				<input class="fileupload" id="image" type="file" data-display-id="image"	data-preview="true" data-rule-accept="image/*" />	
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">标题*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="title" name="title" value="${ entity.title }" data-rule-required="true"
						placeholder="请输入标题">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="status" class="col-sm-2 control-label">状态*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="status" name="status" data-value="${entity.status }" data-rule-required="true">
					<option value="0">--禁用--</option>
					<option value="1">--启用--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		
								
		<div class="form-group">
			<label for="sort" class="col-sm-2 control-label">排序*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="sort" name="sort" value="${ entity.sort }" 
						placeholder="请输入排序">
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
