<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
<%@include file="/page/jspf/head.jspf"%>
<script type="text/javascript">
$(function(){
	$("#status").on("change", function(){
		var $this = $(this);
		var $denyReason =  $("#denyReason");
		if($this.val() && $this.val()==2) {
			$denyReason.prop("disabled", false).closest("div.form-group").show();
		}else {
			$denyReason.prop("disabled", true).closest("div.form-group").hide();
		}
			
	}).trigger("change");
	
	
});
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
										
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">用户昵称:</label>
			<div class="col-sm-6 ">
				<label class="form-control-static">${entity.customerName }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">充值号码:</label>
			<div class="col-sm-6 ">
				<label class="form-control-static">${entity.mobile }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">充值金额:</label>
			<div class="col-sm-6 ">
				<label class="form-control-static text-danger">${entity.money }</label>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>						
		
		
								
		<div class="form-group">
			<label for="status" class="col-sm-2 control-label">审核*</label>
			<div class="col-sm-6 ">
				<select	class="form-control" id="status" name="status" data-value="${ entity.status }" data-rule-required="true">
					<option value="">--审核状态--</option>
					<option value="0">--待审核--</option>
					<option value="1">--已打款--</option>
					<option value="2">--已拒绝--</option>
				</select>
				
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">拒绝原因:</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="denyReason" name="denyReason" value="${ entity.denyReason }" data-rule-required="true"  placeholder="请输入不通过的原因">
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
