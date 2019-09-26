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
			<label for="parentId" class="col-sm-2 control-label">名称*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="name" name="name" value="${ entity.name }" 
						data-rule-required="true"
						placeholder="请输入名称">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
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
			<label for="parentId" class="col-sm-2 control-label">所需积分*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="score" name="score" value="${ entity.score }" 
						data-rule-required="true" data-rule-digits="true"
						placeholder="请输入兑换所需积分">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">商品价格*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="price" name="price" value="${ entity.price }" 
						data-rule-required="true"  data-rule-number="true"
						placeholder="请输入商品价格">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">规格描述*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="norms" name="norms" value="${ entity.norms }" 
						data-rule-required="true"  placeholder="请输入规格描述，多个用英文逗号分隔">
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
			<label for="parentId" class="col-sm-2 control-label">是否上架*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="enabled" name="enabled" data-value="${entity.enabled }" data-rule-required="true">
					<option value="0">--下架--</option>
					<option value="1">--上架--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">商户名称*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="merchantName" name="merchantName" value="${ entity.merchantName }" 
						data-rule-required="true"  placeholder="请输入商户名称">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">商户热线*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="merchantTel" name="merchantTel" value="${ entity.merchantTel }" 
						data-rule-required="true"  placeholder="请输入商户热线">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">商户服务时间*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="merchantServiceTime" name="merchantServiceTime" value="${ entity.merchantServiceTime }" 
						data-rule-required="true"  placeholder="请输入商户服务时间">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">图文描述*</label>
			<div class="col-sm-6 ">
				<script class="u-editor" id="describe"  name="describe"  type="text/plain">
        				${entity.describe}
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
