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
			<h3>订单详情</h3>
			<br/>
		</div>
		<div class="col-sm-10 col-sm-offset-1">
			<table class="table table-striped table-condensed table-bordered">
				<thead>
					<tr>
						<th colspan="5">订单相关</th>
					</tr>
				</thead>
				<tbody>
					<tr class="success">
						<td>订单号</td>
						<td>订单类型</td>
						<td>订单状态</td>
						<td>商品名</td>
						<td>数量</td>
					</tr>
					<tr>
						<td>${entity.orderno }</td>
						<td><span data-random-label="${ entity.orderType }">${entity.orderType ==1?'直接购买':(entity.orderType==2?'团购':'酒店预订') }</span></td>
						<td>${entity.statusDesc }</td>
						<td >${entity.orderName}</td>
						<td >${entity.quantity}</td>
					</tr>
					

					<tr class="warning">
						<td>购买时间</td>
						<td>总金额</td>
						<td>支付金额</td>
						<td>支付方式</td>
						<td>优惠信息</td>
					</tr>
					<tr >
						<td><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td>${entity.totalFee }</td>
						<td>${entity.amount }</td>
						<td><span data-random-label="${ entity.payType }">${entity.payType ==1?'余额':(entity.payType==2?'支付宝':'微信') }</span></td>
						<td>${entity.discountsDesc }</td>
					</tr>
					
					<tr class="danger">
						<td>支付时间</td>
						<td>第三方支付单号</td>
						<td>团购券号</td>
						<td>退款理由</td>
						<td>订单是否删除</td>
					</tr>
					<tr >
						<td><fmt:formatDate value="${entity.payTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td>${entity.payOutNo }</td>
						<td>${entity.groupbutTicketNo }</td>
						<td>${entity.refundReasonDesc}</td>
						<td><span data-random-label="${ cur.isDelete }">${cur.isDelete?'是':'否' }</span></td>
					</tr>
					
					
					
					<tr>
						<th colspan="5">用户商户信息</th>
					</tr>
					<tr class="success">
						<td>用户姓名</td>
						<td>联系电话</td>
						<td>商户名称</td>
						<td>商户电话</td>
						<td>所属城市</td>
					</tr>
					<tr >
						<td>${entity.customerName }</td>
						<td>${entity.mobile }</td>
						<td>${entity.merchantName }</td>
						<td>${entity.merchantMobile }</td>
						<td>${entity.cityName }</td>
					</tr>
					<!-- 酒店预订 -->
					<c:if test="${entity.orderType ==3 }">
						<tr>
							<th colspan="5">预定信息</th>
						</tr>
						<tr class="info">
							<td>房间名</td>
							<td>房间数</td>
							<td>入住人</td>
							<td>入住时间</td>
							<td>离开时间</td>
						</tr>
						<tr>
							<td>${entity.hotelDetail.name }</td>
							<td>${entity.roomNumber }</td>
							<td>${entity.checkPerson }</td>
							<td><fmt:formatDate value="${cur.checkStartDate}" pattern="HH:mm"/></td>
							<td><fmt:formatDate value="${cur.checkEndDate}" pattern="HH:mm"/></td>
						</tr>
					</c:if>
			</table>
		</div> 





		<div class="form-group clearfix">
			<div class="col-sm-offset-5 col-sm-7">
				<button type="button" class="btn btn-info action-back">
					<i class="glyphicon glyphicon-repeat"></i> 返回
				</button>
			</div>
		</div>

	</form>
	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
