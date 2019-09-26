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
			<h3 class="text-info">预定详情</h3>
			<br/>
		</div>
		<div class="col-sm-10 col-sm-offset-1">
			<table class="table table-striped table-condensed table-bordered">
				<tbody>
					<tr class="success">
						<td>商家名称</td>
						<td>商户电话</td>
						<td>名称</td>
						<td>价格</td>
						<td>图标</td>
						
					</tr>
					<tr>
						<td>${entity.merchantName }</td>
						<td>${entity.merchantMobile}</td>
						<td>${entity.name }</td>
						<td>${entity.price }</td>
						<td><img  data-attachment="${ entity.icon }" class="pop"/></td>
					</tr>
					

					<tr class="success">
						<td>库存数</td>
						<td>退订政策</td>
						<td>订单确认时间</td>
						<td>床型</td>
						<td>可住人数</td>
					</tr>
					<tr >
						<td>${entity.stock }</td>
						<td>${entity.unsubscribeWay==1?'当天18：00前可免费取消订单':'不可变更取消' }</td>
						<td>${entity.verifyWay==1?'1小时内确认':'立即确认' }</td>
						<td>${entity.roomBed ==4?'三人床':(entity.roomBed==3?'双人床':(entity.roomBed==2?'豪华大床':'单人床')) }</td>
						<td>${entity.roomPersonNum }</td>
					</tr>
					
					<tr class="success">
						<td>宽带</td>
						<td>窗户</td>
						<td>面积</td>
						<td>楼层</td>
						<td>电话</td>
					</tr>
					<tr >
						<td>${entity.roomNetwork ==3?'无线WIFI':(entity.roomNetwork==2?'有窗':'无窗') }</td>
						<td>${entity.roomWindow ==3?'部分有窗':(entity.roomWindow==2?'含早餐床':'无早餐') }</td>
						<td>${entity.roomArea }</td>
						<td>${entity.roomFloor }</td>
						<td>${entity.roomTel ==3?'收费电话':(entity.roomTel==2?'免费电话':'无电话') }</td>
					</tr>
					
					<tr class="success">
						<td>早餐</td>
						<td>可否加床</td>
						<td>无烟</td>
						<td>床宽</td>
						<td colspan="2">团购图片</td>
					</tr>
					<tr >
						<td>${entity.roomBreakfast ==3?'双份早餐':(entity.roomBreakfast==2?'含早餐床':'无早餐') }</td>
						<td>${entity.roomAddBeded?'可':'不可' }</td>
						<td>${entity.roomSmoke ==1?'可吸烟':'该房无烟处理' }</td>
						<td>${entity.roomBedWide ==4?'2.0米大床':(entity.roomBedWide==3?'1.8米大床':(entity.roomBedWide==2?'1.5米大床':'圆形床')) }</td>
					</tr>
					
					<tr class="info">
						<td >状态</td>
						<td >是否删除</td>
						<td colspan="3">图片</td>
					</tr>
					<tr>
						<td><span data-random-label="${ cur.status }">${entity.status ==0?'初始':(cur.status==1?'上架':'下架') }</span > </td>
						<td><span class="label label-default">${cur.isDelete==1?'已删除':'未删除' }</span></td>
						<td colspan="3">
							<c:if test="${not empty entity.images }">
								<c:forEach items="${ fn:split(entity.images, ',') }" var="pic">
									<img  data-attachment="${pic }" class="pop"/>	
								</c:forEach>
							</c:if> 
						</td>
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
