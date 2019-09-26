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
			<label for="parentId" class="col-sm-2 control-label">公司名称*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="companyName" name="companyName" value="${ entity.companyName }" 
						placeholder="请输入公司名称">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">公司地址*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="companyAddress" name="companyAddress" value="${ entity.companyAddress }" 
						placeholder="请输入公司地址">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">公司负责人*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="companyPerson" name="companyPerson" value="${ entity.companyPerson }" 
						placeholder="请输入公司负责人">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">投放产品*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="product" name="product" value="${ entity.product }" 
						placeholder="请输入投放产品">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">1-商城 2-城市代理*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="type" name="type" value="${ entity.type }" 
						placeholder="请输入1-商城 2-城市代理">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">意向代理城市*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="city" name="city" value="${ entity.city }" 
						placeholder="请输入意向代理城市">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">意向投资金额*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="amount" name="amount" value="${ entity.amount }" 
						placeholder="请输入意向投资金额">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="customerId" name="customerId" value="${ entity.customerId }" 
						placeholder="请输入">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">状态 0-初始 1-已处理*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="status" name="status" value="${ entity.status }" 
						placeholder="请输入状态 0-初始 1-已处理">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">公司电话*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="mobile" name="mobile" value="${ entity.mobile }" 
						placeholder="请输入公司电话">
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
