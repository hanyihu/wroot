<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
<%@include file="/page/jspf/head.jspf"%>
<!-- 上传js需要引入的 -->
<script type="text/javascript" src="${ctx }/static/js/jquery.fileupload.js"></script> 
<script type="text/javascript" src="${ctx }/static/js/jquery.iframe-transport.js"></script>  
<script type="text/javascript">
	function openWin(url, title){
		var style = "width=1200,height=700,location=no,directories=no,toolbar=no,status=no,menubar=no,resizable=no,scrollbars=no,z-look=yes,screenX=300px,screenY=200px";
	    window.open(url, title, style);        
	}
	
	function callback(merchantId, merchantName){
		$("#merchantId").val(merchantId);
		$("#merchantName").val(merchantName);
	}
	
	$(function(){
		//前往选择商家
		$("#choose-merchant").on("click", function(){
			var cityId = $("#cityId").val();
			var categoryId = $("#merchantCategory").val();
			if(cityId== 0 || cityId == null || !cityId ) {
				$common.tipDialog("提示","请先选择城市");
				return false;
			}
			if(categoryId== 0 || categoryId == null || !categoryId ) {
				$common.tipDialog("提示","请先选择分类");
				return false;
			}
			
			var url = "${ctx}/console/merchant/merchant/toLookup2/?cityId=" + cityId + "&categoryId=" + categoryId;
			openWin(url, "选择商家");
		});
	});
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
					
		<div class="form-group">
			<label for="cityId" class="col-sm-2 control-label">所属城市*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="cityId" name="cityId" data-value="${entity.cityId }" data-rule-required="true">
					<option value="0">--请选择城市--</option>
					<c:forEach items="${cityList }" var="item">
					<option value="${item.id }">${item.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="merchantCategory" class="col-sm-2 control-label">所属分类*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="merchantCategory" name="merchantCategory" data-value="${entity.merchantCategory }" data-rule-required="true">
					<option value="0">--请选择分类--</option>
					<c:forEach items="${categoryList }" var="item">
					<option value="${item.id }">${item.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>				
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">
				<a class="btn btn-sm btn-info" id="choose-merchant">选择商家</a><span class="text-danger">*</span>
			</label>
			<div class="col-sm-6 ">
				<input type="text"  class="form-control" id="merchantName" value="${entity.merchantName}" readonly placeholder="商家" />
				<input type="text"  class="form-control hidden" id="merchantId" name="merchantId" value="${entity.merchantId}"  data-rule-required="true">		
						
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">图标*</label>
			<div class="col-sm-6 ">
				<input type="hidden" name="icon" value="${entity.icon}" data-rule-required="true" /> 
				<input class="fileupload" id="image" type="file" data-display-id="icon"	data-preview="true" data-rule-accept="image/*" />	
					
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>

		<div class="form-group">
			<label for="platformDesc" class="col-sm-2 control-label">商家描述*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="platformDesc" name="platformDesc" value="${ entity.platformDesc}"  data-rule-required="true" 
						data-rule-maxlength="100"	placeholder="请输入商家描述">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="enabled" class="col-sm-2 control-label">状态*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="enabled" name="enabled" data-value="${entity.enabled }" data-rule-required="true">
					<option value="1">--启用-</option>
					<option value="0">--禁用--</option>
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
