<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
<%@include file="/page/jspf/head.jspf"%>
	<link href="${ctx }/static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<script type="text/javascript" src="${ctx }/static/js/jquery.fileupload.js"></script> 
<script type="text/javascript" src="${ctx }/static/js/jquery.iframe-transport.js"></script>

<script type="text/javascript">
$(function(){
	$("#machineType").on("change", function(){
		var $this = $(this);
		var $iosUrl = $("#iosUrl");
		var $androidUrl =  $("#androidUrl");
		switch ($this.val()){
			case "1": //安卓 
				$androidUrl.prop("disabled", false).closest("div.form-group").show();
				$iosUrl.prop("disabled", true).closest("div.form-group").hide();
				break;
			case "2": //IOS
				$iosUrl.prop("disabled", false).closest("div.form-group").show();
				$androidUrl.prop("disabled", true).closest("div.form-group").hide();
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
			<label for="parentId" class="col-sm-2 control-label">版本名称*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="name" name="name" value="${ entity.name }" placeholder="请输入版本名称" data-rule-required="true" />
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">版本*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="version" name="version" value="${ entity.version }" placeholder="请输入版本" data-rule-required="true" />
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">数字编号*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="number" name="number" value="${ entity.number }"  placeholder="请输入数字编号，高版本始终大于低版本" 
					data-rule-required="true" data-rule-digits="true" />
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">手机类型*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="machineType" name="machineType" data-value="${entity.machineType }" data-rule-required="true" />
					<option value="1">--安卓--</option>
					<option value="2">--IOS--</option>
				</select>		
						
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">APP类型*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="appType" name="appType" data-value="${entity.appType }" data-rule-required="true" >
					<option value="1">--用户端--</option>
					<option value="2">--商家端--</option>
				</select>				
						
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">更新说明*</label>
			<div class="col-sm-6 ">
				<textarea rows="3" style="width: 100%" data-rule-maxlength="260" name="log" id="log" >${entity.log }</textarea>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">IOS下载地址*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="iosUrl" name="iosUrl" value="${ entity.iosUrl }" 
						placeholder="请输入ios下载地址 应指向苹果商店" data-rule-required="true" />
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">安卓APK文件*</label>
			<div class="col-sm-6 ">
				 <input type="hidden" id="androidUrl" name="androidUrl" value="${entity.androidUrl}" data-rule-required="true" />																	
                 <input class="fileupload"  id="androidUrlUpfile" type="file"  data-display-id="androidUrl" data-rule-extension="apk" data-rule-msg="传入合法APK" />	
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>

		<div class="form-group" id="data_1">
			<label class="font-noraml">简单示例</label>
			<div class="input-group date">
				<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
				<input type="text" class="form-control" value="2014-11-11">
			</div>
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
