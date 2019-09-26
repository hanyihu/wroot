<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>后台</title>
<%@include file="/page/jspf/head.jspf"%>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
	
		<div class="form-group">
			<label for="parentName" class="col-sm-2 control-label">所属父模块</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="parentName" disabled="disabled" value="${entity.parentName }" >
				<input type="hidden" class="form-control" id="parentId" name="parentId" readonly="readonly" value="${entity.parentId }" >
			</div>
			<div class="col-sm-4 help-inline form-control-static"></div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label"><span class="text-danger">*</span>模块名称</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="name" name="name" value="${entity.name }" 
					data-rule-required="true"	data-rule-remote="${ctx}/console/system/menu/check?id=${entity.id}&parentId=${entity.parentId }" data-msg-remote="名称重复" 
					placeholder="请输入模块名称">
			</div>
			<div class="col-sm-4 help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="sort" class="col-sm-2 control-label"><span class="text-danger">*</span>URL</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="sort" name="url" value="${entity.url }" 	data-rule-required="true"   placeholder="请输入url">
			</div>
			<div class="col-sm-4 help-inline form-control-static"></div>
		</div>
		
		 <div class="form-group">
                <label class="col-sm-2 control-label" for="categoryPks">按钮：</label>
                <div class="col-sm-6">
                    <c:forEach items="${entity.btnList}" var="cur" varStatus="status">
                        <label><input type="checkbox" name="btns" value="${cur.id}"  <c:if test="${cur.checked }"> checked="checked" </c:if> >${cur.name}&nbsp;</label>
                     </c:forEach>
                </div>
                <label class="col-sm-4 help-inline form-control-static" for="categoryPks"></label>
            </div>

		<div class="form-group">
			<label for="sort" class="col-sm-2 control-label">排序</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="sort" name="sort" value="${entity.sort }" 	data-rule-digits="true" data-rule-range="[0,9999]"  placeholder="请输排序">
			</div>
			<div class="col-sm-4 help-inline form-control-static"></div>
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
