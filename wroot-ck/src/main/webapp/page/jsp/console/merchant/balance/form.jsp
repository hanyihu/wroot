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
	<form class="form-horizontal validate" role="form" method="post">
										
		
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">商户名称</label>
			<div class="col-sm-6 ">
				 <label class="form-control-static">${entity.merchantName }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">商户电话</label>
			<div class="col-sm-6 ">
				<label class="form-control-static">${entity.merchantMobile }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">银行</label>
			<div class="col-sm-6 ">
				 <label class="form-control-static text-danger">${entity.bankName }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">账户名</label>
			<div class="col-sm-6 ">
				 <label class="form-control-static text-danger">${entity.accountName }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">账户</label>
			<div class="col-sm-6 ">
				 <label class="form-control-static text-danger">${entity.bankAccount }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">当前余额</label>
			<div class="col-sm-6 ">
				 <label class="form-control-static text-danger">${entity.balance }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">当前余额</label>
			<div class="col-sm-6 ">
				 <label class="form-control-static text-danger">${entity.balance }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">打款金额*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="balance" name="balance" placeholder="请输入要提取的金额，应小于当前账户余额" 
				data-rule-required="true" data-rule-number="true">
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
