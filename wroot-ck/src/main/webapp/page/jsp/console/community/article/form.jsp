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
			<label for="parentId" class="col-sm-2 control-label">标题*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="title" name="title" value="${ entity.title }" 	placeholder="请输入标题" data-rule-required="true" >
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">简介*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="summary" name="summary" value="${ entity.summary }" placeholder="请输入简介" data-rule-required="true" >
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
		
		<!-- 权限为全国的则显示选择城市 -->
		<c:if test="${empty principal.cityId}">
								
			<div class="form-group">
				<label for="parentId" class="col-sm-2 control-label">所属城市</label>
				<div class="col-sm-6 ">
					<select class="form-control" id="cityId" name="cityId" data-value="${entity.cityId }" >
						<option value="0">--请选择城市--</option>
						<c:forEach items="${cityList }" var="item">
						<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-4  help-inline form-control-static"></div>
			</div>
		</c:if>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">所属分类*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="categoryId" name="categoryId" data-value="${entity.categoryId }" data-rule-required="true" >
					<option value="">--请选择所属分类--</option>
					<c:forEach items="${categoryList }" var="item">
						<option value="${item.id }">${item.name }</option>
					</c:forEach>
				</select>		
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">是否显示*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="enabled" name="enabled" data-value="${entity.enabled }" data-rule-required="true">
					<option value="0">--关闭--</option>
					<option value="1">--显示--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>

        <div class="form-group">
            <label for="parentId" class="col-sm-2 control-label">是否开启评论*</label>
            <div class="col-sm-6 ">
                <select class="form-control" id="openComment" name="openComment" data-value="${entity.openComment }" data-rule-required="true">
                    <option value="0">--关闭--</option>
                    <option value="1">--开启--</option>
                </select>
            </div>
            <div class="col-sm-4  help-inline form-control-static"></div>
        </div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">内容*</label>
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
