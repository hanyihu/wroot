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
			<label for="parentId" class="col-sm-2 control-label">商户id*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="merchantId" name="merchantId" value="${ entity.merchantId }" 
						placeholder="请输入商户id">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">团购名称*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="name" name="name" value="${ entity.name }" 
						placeholder="请输入团购名称">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">团购价格*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="price" name="price" value="${ entity.price }" 
						placeholder="请输入团购价格">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">开始时间*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="startTime" name="startTime" value="${ entity.startTime }" 
						placeholder="请输入开始时间">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">结束时间*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="endTime" name="endTime" value="${ entity.endTime }" 
						placeholder="请输入结束时间">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">是否24小时可消费*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="consumedAllHours" name="consumedAllHours" value="${ entity.consumedAllHours }" 
						placeholder="请输入是否24小时可消费">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">消费开始时间*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="consumeStartTime" name="consumeStartTime" value="${ entity.consumeStartTime }" 
						placeholder="请输入消费开始时间">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">消费结束时间*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="consumeEndTime" name="consumeEndTime" value="${ entity.consumeEndTime }" 
						placeholder="请输入消费结束时间">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">团购标签(政策) 多个逗号分隔*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="labels" name="labels" value="${ entity.labels }" 
						placeholder="请输入团购标签(政策) 多个逗号分隔">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">购买须知 多个逗号分隔*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="tips" name="tips" value="${ entity.tips }" 
						placeholder="请输入购买须知 多个逗号分隔">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">团购文字介绍*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="describe" name="describe" value="${ entity.describe }" 
						placeholder="请输入团购文字介绍">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">团购图片*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="images" name="images" value="${ entity.images }" 
						placeholder="请输入团购图片">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">0-初始 1-上架 2-下架- 3-删除*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="status" name="status" value="${ entity.status }" 
						placeholder="请输入0-初始 1-上架 2-下架- 3-删除">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">销售数量*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="sellNum" name="sellNum" value="${ entity.sellNum }" 
						placeholder="请输入销售数量">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">评论数*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="commentNum" name="commentNum" value="${ entity.commentNum }" 
						placeholder="请输入评论数">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">团购图标*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="icon" name="icon" value="${ entity.icon }" 
						placeholder="请输入团购图标">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">是否删除*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="isDelete" name="isDelete" value="${ entity.isDelete }" 
						placeholder="请输入是否删除">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="createTime" name="createTime" value="${ entity.createTime }" 
						placeholder="请输入">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">平均评星*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="star" name="star" value="${ entity.star }" 
						placeholder="请输入平均评星">
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
