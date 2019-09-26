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
			<label for="parentId" class="col-sm-2 control-label">*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="merchantId" name="merchantId" value="${ entity.merchantId }" 
						placeholder="请输入">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">图标*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="icon" name="icon" value="${ entity.icon }" 
						placeholder="请输入图标">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">图片*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="images" name="images" value="${ entity.images }" 
						placeholder="请输入图片">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">房间标题*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="name" name="name" value="${ entity.name }" 
						placeholder="请输入房间标题">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">价格*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="price" name="price" value="${ entity.price }" 
						placeholder="请输入价格">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">库存数*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="stock" name="stock" value="${ entity.stock }" 
						placeholder="请输入库存数">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="unsubscribeWay" name="unsubscribeWay" value="${ entity.unsubscribeWay }" 
						placeholder="请输入退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">订单确认时间 1-1小时内确认 2-立即确认*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="verifyWay" name="verifyWay" value="${ entity.verifyWay }" 
						placeholder="请输入订单确认时间 1-1小时内确认 2-立即确认">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">床型 1-单人床 2-豪华大床 3-双人床 4-三人床*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="roomBed" name="roomBed" value="${ entity.roomBed }" 
						placeholder="请输入床型 1-单人床 2-豪华大床 3-双人床 4-三人床">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">可住人数*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="roomPersonNum" name="roomPersonNum" value="${ entity.roomPersonNum }" 
						placeholder="请输入可住人数">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">宽带 1-无网络 2-有线宽带 3-无线WIFI*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="roomNetwork" name="roomNetwork" value="${ entity.roomNetwork }" 
						placeholder="请输入宽带 1-无网络 2-有线宽带 3-无线WIFI">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">窗户 1-无窗 2-有窗 3-部分有窗*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="roomWindow" name="roomWindow" value="${ entity.roomWindow }" 
						placeholder="请输入窗户 1-无窗 2-有窗 3-部分有窗">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">面积*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="roomArea" name="roomArea" value="${ entity.roomArea }" 
						placeholder="请输入面积">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">楼层*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="roomFloor" name="roomFloor" value="${ entity.roomFloor }" 
						placeholder="请输入楼层">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">电话 1-无电话 2-免费电话 3-收费电话*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="roomTel" name="roomTel" value="${ entity.roomTel }" 
						placeholder="请输入电话 1-无电话 2-免费电话 3-收费电话">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">窗景：1-无 2-风景2-城景 3-花园景 4-地标景 5-无敌海景*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="roomWindowmView" name="roomWindowmView" value="${ entity.roomWindowmView }" 
						placeholder="请输入窗景：1-无 2-风景2-城景 3-花园景 4-地标景 5-无敌海景">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">早餐 1-无早餐 2-含早餐 3-双份早餐*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="roomBreakfast" name="roomBreakfast" value="${ entity.roomBreakfast }" 
						placeholder="请输入早餐 1-无早餐 2-含早餐 3-双份早餐">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">可否加床*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="roomAddBeded" name="roomAddBeded" value="${ entity.roomAddBeded }" 
						placeholder="请输入可否加床">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">无烟 1- 可吸烟2-该房无烟处理*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="roomSmoke" name="roomSmoke" value="${ entity.roomSmoke }" 
						placeholder="请输入无烟 1- 可吸烟2-该房无烟处理">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">床宽1-圆形床 2-1.5米大床 3-1.8米大床 3-2.0米大床*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="roomBedWide" name="roomBedWide" value="${ entity.roomBedWide }" 
						placeholder="请输入床宽1-圆形床 2-1.5米大床 3-1.8米大床 3-2.0米大床">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="isDelete" name="isDelete" value="${ entity.isDelete }" 
						placeholder="请输入">
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
			<label for="parentId" class="col-sm-2 control-label">0-未上架 1-已上架 2-已下架*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="status" name="status" value="${ entity.status }" 
						placeholder="请输入0-未上架 1-已上架 2-已下架">
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
