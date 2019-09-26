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
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
										
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">分类名称*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="name" name="name" value="${ entity.name }" 
						placeholder="请输入分类名称">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">图标*</label>
			<div class="col-sm-6 ">
				<input type="hidden" name="icon" value="${entity.icon}" data-rule-required="true" /> 
				<input class="fileupload" id="icon" type="file" data-display-id="icon"	data-preview="true" data-rule-accept="image/*" />	
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">类别*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="type" name="type" data-value="${entity.type }" data-rule-required="true">
                    <option value="">--请选择类别--</option>
                    <option value="1">--餐饮美食--</option>
                    <option value="2">--商超便利--</option>
                    <option value="3">--时尚达人--</option>
                    <option value="4">--母婴亲子--</option>
                    <option value="5">--同城便利--</option>
                    <option value="6">--本地医疗--</option>
                    <option value="7">--学习培训--</option>
                    <option value="8">--生活服务--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">是否启用*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="enabled" name="enabled" data-value="${entity.enabled }" data-rule-required="true">
					<option value="0">--禁用--</option>
					<option value="1">--启用--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">排序*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="sort" name="sort" value="${ entity.sort }" 
						data-rule-required="true" data-rule-digits="true" placeholder="请输入排序字段">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">服务费百分比*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="servicePercent" name="servicePercent" value="${ entity.servicePercent }" 
						data-rule-required="true" data-rule-digits="true" placeholder="请输入服务费百分比">
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
