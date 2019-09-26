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
			<label for="parentId" class="col-sm-2 control-label">pid*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="parentId" name="parentId" value="${ entity.parentId }" 
						placeholder="请输入pid">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">名称*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="name" name="name" value="${ entity.name }" 
						placeholder="请输入名称">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">简称*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="shortName" name="shortName" value="${ entity.shortName }" 
						placeholder="请输入简称">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">层级:0国家级别，1省级，2地市级，3区县级*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="levelType" name="levelType" value="${ entity.levelType }" 
						placeholder="请输入层级:0国家级别，1省级，2地市级，3区县级">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">父层级名称*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="mergerName" name="mergerName" value="${ entity.mergerName }" 
						placeholder="请输入父层级名称">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">父层级简称*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="mergerShortName" name="mergerShortName" value="${ entity.mergerShortName }" 
						placeholder="请输入父层级简称">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">城市编码*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="areaCode" name="areaCode" value="${ entity.areaCode }" 
						placeholder="请输入城市编码">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">邮政编码*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="postalCode" name="postalCode" value="${ entity.postalCode }" 
						placeholder="请输入邮政编码">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">拼音*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="pinyin" name="pinyin" value="${ entity.pinyin }" 
						placeholder="请输入拼音">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">简拼*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="jianpin" name="jianpin" value="${ entity.jianpin }" 
						placeholder="请输入简拼">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">首字母*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="firstChar" name="firstChar" value="${ entity.firstChar }" 
						placeholder="请输入首字母">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">经度*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="lng" name="lng" value="${ entity.lng }" 
						placeholder="请输入经度">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">纬度*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="lat" name="lat" value="${ entity.lat }" 
						placeholder="请输入纬度">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">是否开放*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="opened" name="opened" value="${ entity.opened }" 
						placeholder="请输入是否开放">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">是否热门城市*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="hot" name="hot" value="${ entity.hot }" 
						placeholder="请输入是否热门城市">
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
