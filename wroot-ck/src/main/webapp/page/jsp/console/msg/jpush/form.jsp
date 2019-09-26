<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
	<%@include file="/page/jspf/console-uj.jspf"%>
	<!-- 上传js需要引入的 -->
	<script type="text/javascript" src="${ctx }/static/js/jquery.fileupload.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/jquery.iframe-transport.js"></script>
	<%@include file="/page/jspf/head.jspf"%>

<script type="text/javascript">
$(function(){
	$("#targtType").on("change", function(){
		var targtType = $(this).val();
		var $city = $("#cityId");
		var $mobile = $("#mobile");
		switch (targtType) {
			case "1"://全平台
				$city.prop("disabled", true).closest("div.form-group").hide();
				$mobile.prop("disabled", true).closest("div.form-group").hide();
				break;
			case "2"://城市
				$city.prop("disabled", false).closest("div.form-group").show();
				$mobile.prop("disabled", true).closest("div.form-group").hide();
				break;
			
			case "3"://个人
				$city.prop("disabled", true).closest("div.form-group").hide();
				$mobile.prop("disabled", false).closest("div.form-group").show();
				break;
				
			default:
				break;
		}
	}).trigger("change");
});
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
										
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">平台*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="role" name="role" data-value="${entity.role }" data-rule-required="true">
					<option value="1">--用户端--</option>
					<option value="2">--商家端--</option>
					<option value="3">--骑手端--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">标题*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="title" name="title" value="${ entity.title}"  data-rule-required="true"
					   placeholder="请输入标题">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>

		<div class="form-group">
			<label for="content" class="col-sm-2 control-label">内容*</label>
			<div class="col-sm-6 ">
				<script class="u-editor" id="content"  name="content"  type="text/plain">
        				${entity.content}
   				</script>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>

		<%--<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">内容*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="content" name="content" value="${ entity.content }" 
						data-rule-required="true" 
						data-rule-maxlength="100"placeholder="请输入推送内容(100字以内)">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		--%>
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">推送目标*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="targtType" name="targtType" data-value="${entity.targtType }" data-rule-required="true">
					<option value="1">--全平台--</option>
					<option value="2">--城市--</option>
					<option value="3">--个人--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="cityId" class="col-sm-2 control-label">推送的城市*</label>
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
			<label for="mobile" class="col-sm-2 control-label">目标电话*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="mobile" name="mobile" value="${ entity.mobile }" 
						data-rule-required="true" data-rule-mobile="true"  placeholder="请输入推送目标的手机号，确保和平台账号手机号保持一致且存在">
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
