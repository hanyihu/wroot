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
<script type="text/javascript"
	src="${ctx }/static/js/jquery.fileupload.js"></script>

<script type="text/javascript"
	src="${ctx }/static/js/jquery.iframe-transport.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">

		<div class="text-center">
			<h3>兑换详情</h3>
		</div>
		
		<div class="col-sm-10 col-sm-offset-1">
			<table class="table table-striped table-condensed table-bordered">
				<thead>
					<tr>
						<th colspan="4">兑换相关</th>
					</tr>
				</thead>
				<tbody>
					<tr class="success">
						<td>兑换码</td>
						<td>用户名</td>
						<td>用户电话</td>
						<td>用户备注</td>
					</tr>
					<tr>
						<td>${entity.swapno }</td>
						<td>${entity.customerName }</td>
						<td>${entity.mobile }</td>
						<td title="${entity.remark }">${fn:substring(entity.remark, 0, 20)}${fn:length(entity.remark) gt 20 ? "..." : ""}</td>
					</tr>

					<tr class="warning">
						<td>商品名</td>
						<td>兑换数量</td>
						<td>单位积分</td>
						<td>总积分</td>
					</tr>
					<tr >
						<td>${entity.swapMallName }</td>
						<td>${entity.quantity }</td>
						<td>${entity.unitScore }</td>
						<td>${entity.score }</td>
					</tr>
					<tr>
						<th colspan="4">收货信息</th>
					</tr>
					<tr class="success">
						<td>收货人</td>
						<td>收货人电话</td>
						<td>省市区</td>
						<td>详细地址</td>
					</tr>
					<tr >
						<td>${entity.address.deliveryName }</td>
						<td>${entity.address.deliveryMobile }</td>
						<td>${entity.address.provinceName }${entity.address.cityName }${entity.address.areaName }</td>
						<td>${entity.address.address }</td>
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
