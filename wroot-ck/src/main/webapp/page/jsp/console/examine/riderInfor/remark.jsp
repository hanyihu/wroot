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
										
		<div class="form-group">


			<div class="col-sm-6 ">
                <label for="examineRemark" class="col-sm-6">请说明审核不通过原因*</label>
                <textarea class="form-control" rows="5" name=examineRemark
                          value=${ entity.examineRemark } data-rule-required="true">${ entity.examineRemark }</textarea>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>



		<div class="form-group clearfix">
			<div class="col-sm-6" align="center">
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
