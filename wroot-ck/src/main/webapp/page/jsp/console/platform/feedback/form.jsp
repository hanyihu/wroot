<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
<%@include file="/page/jspf/head.jspf"%>
 <script type="text/javascript" src="${ctx }/static/js/jquery.fileupload.js"></script> 
<!-- 上传js需要引入的 -->
 <script type="text/javascript" src="${ctx }/static/js/jquery.iframe-transport.js"></script>  
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
										
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">反馈人：</label>
			<div class="col-sm-6 ">
				 <label class="form-control-static">${entity.customerName }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">反馈时间：</label>
			<div class="col-sm-6 ">
				 <label class="form-control-static">${entity.createTimeDesc }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">反馈内容：</label>
			<div class="col-sm-6 ">
				<label class="form-control-static">${entity.content }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">用户类型：</label>
			<div class="col-sm-6 ">
				<label class="form-control-static">${entity.typeDesc }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">反馈图片：</label>
			<div class="col-sm-6 ">
					<input type="hidden" disabled="disabled" name="images" value="${entity.images }" /> 
                    <input class="fileupload" id="commodity-images" type="file" multiple data-preview="true"  data-only-show-preview="true"
                    data-display-id="images"  
                    style="display:none" 
                   />
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		
		
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">回复内容：</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="reply" name="reply" value="${ entity.reply }" 
						placeholder="请输入回复内容">
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
