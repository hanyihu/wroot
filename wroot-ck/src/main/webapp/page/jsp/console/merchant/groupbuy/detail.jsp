<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<style type="text/css">
	.table th, .table td { 
		text-align: center;
		vertical-align: middle!important;
	}
</style>
<%@include file="/page/jspf/head.jspf"%>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">

		<div class="text-center">
			<h3 class="text-info">团购详情</h3>
			<br/>
		</div>
		<div class="col-sm-10 col-sm-offset-1">
			<table class="table table-striped table-condensed table-bordered">
				<tbody>
					<tr class="success">
						<td>商家名称</td>
						<td>商户电话</td>
						<td>团购名称</td>
						<td>团购价格</td>
						<td>团购图标</td>
						
					</tr>
					<tr>
						<td>${entity.merchantName }</td>
						<td>${entity.merchantMobile}</td>
						<td>${entity.name }</td>
						<td>${entity.price }</td>
						<td><img  data-attachment="${ entity.icon }" class="pop"/></td>
					</tr>
					

					<tr class="success">
						<td>开始时间</td>
						<td>结束时间</td>
						<td>是否24小时消费</td>
						<td>消费时间</td>
						<td>创建时间</td>
					</tr>
					<tr >
						<td><fmt:formatDate value="${entity.startTime }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${entity.startTime }" pattern="yyyy-MM-dd"/></td>
						<td>${entity.consumedAllHours?'是':'否' }</td>
						<td><fmt:formatDate value="${entity.consumeStartTime }" pattern="HH:mm"/>-<fmt:formatDate value="${entity.consumeEndTime }" pattern="HH:mm"/></td>
						<td><fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd HH:mm" /></td>
					</tr>
					
					<tr class="success">
						<td>团购标签</td>
						<td colspan="4">团购须知</td>
					</tr>
					<tr >
						<td>${entity.labels }</td>
						<td colspan="4">${entity.tips }</td>
					</tr>
					
					<tr class="success">
						<td>评星</td>
						<td>销售数量</td>
						<td>状态</td>
						<td colspan="2">团购图片</td>
					</tr>
					<tr >
						<td>${entity.star }</td>
						<td>${entity.sellNum }</td>
						<td><span data-random-label="${ cur.status }">${cur.status ==0?'初始':(cur.status==1?'上架':'下架') }</span > <span class="label label-default">${cur.isDelete==1?'已删除':'未删除' }</span></td>
						<td colspan="2">
							<c:if test="${not empty entity.images }">
								<c:forEach items="${ fn:split(entity.images, ',') }" var="pic">
									<img  data-attachment="${pic }" class="pop"/>	
								</c:forEach>
							</c:if> 
						</td>
					</tr>
					
					<tr class="info">
						<td colspan="5">团购介绍</td>
					</tr>
					<tr>
						<td colspan="5">${entity.describe }</td>
					</tr>
					
					
			</table>
			
		</div> 




		<div class="form-group clearfix">
			<div class="col-sm-offset-4 col-sm-8">
				<button type="button" class="btn btn-info action-back">
					<i class="glyphicon glyphicon-repeat"></i> 返回
				</button>
			</div>
		</div>

	</form>
	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
