<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
<%@include file="/page/jspf/head.jspf"%>
<%@include file="/page/jspf/console-uj.jspf"%>
<!-- 上传js需要引入的 -->
<script type="text/javascript" src="${ctx }/static/js/jquery.fileupload.js"></script> 
<script type="text/javascript" src="${ctx }/static/js/jquery.iframe-transport.js"></script>  
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
					
		<div class="form-group hidden">
			<label for="parentId" class="col-sm-2 control-label">code*</label>
			<div class="col-sm-6 ">
				<input name="code" value="${entity.code }"/>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>		
								
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">${entity.name }*</label>
			<div class="col-sm-6 ">
				<script class="u-editor" id="content"  name="content"  type="text/plain">
        				${entity.content}
   				</script>		
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
