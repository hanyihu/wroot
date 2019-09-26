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
	<div class="row row-nocol">
		<form class="form-inline lookup" role="form" method="post">
			<div class="form-group">
				<label class="sr-only" for="lookup-orderno">订单号</label> 
				<input	type="text" class="form-control" id="lookup-orderno" name="orderno"	value="${ pager.lookup.orderno }" placeholder="订单号">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-name">用户名</label> 
				<input	type="text" class="form-control" id="lookup-name" name="name"	value="${ pager.lookup.name }" placeholder="用户名">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-mobile">手机号</label> 
				<input	type="text" class="form-control" id="lookup-mobile" name="mobile"	value="${ pager.lookup.mobile }" placeholder="手机号">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-merchantName">商户名</label> 
				<input	type="text" class="form-control" id="lookup-merchantName" name="merchantName"	value="${ pager.lookup.merchantName }" placeholder="商户名">
			</div>
			<div class="form-group">
				<label class="sr-only" for="lookup-merchantMobile">商户电话</label> 
				<input	type="text" class="form-control" id="lookup-merchantMobile" name="merchantMobile"	value="${ pager.lookup.merchantMobile }" placeholder="商户电话">
			</div>

			<div class="form-group">
				<label class="sr-only" for="lookup-status">订单状态</label> 
				<select	class="form-control" id="lookup-status" name="status" data-value="${ pager.lookup.status }">
					<option value="">--订单状态--</option>
					<c:forEach items="${statuses }" var="status">
						<option value="${status.key }">${status.value }</option>
					</c:forEach>
				</select>
			</div>
			
			<!-- 权限为全国的则显示选择城市 -->
			<c:if test="${empty principal.cityId}">
				<div class="form-group">
					<label class="sr-only" for="lookup-cityId">请选择城市</label> <select
						class="form-control" id="lookup-cityId" name="cityId"
						data-value="${ pager.lookup.cityId }">
						<option value="">--请选择城市--</option>
						<c:forEach items="${cityList }" var="city">
							<option value="${city.id }">${city.name }</option>
						</c:forEach>
					</select>
				</div>
			</c:if>	
			
			<%--<div class="form-group">
				<label class="sr-only" for="lookup-isDelete">是否删除</label> 
				<select	class="form-control" id="lookup-isDelete" name="isDelete" data-value="${ pager.lookup.isDelete }">
					<option value="">--是否删除--</option>
					<option value="0">--否--</option>
					<option value="1">--是--</option>
				</select>
			</div>--%>
			<div class="form-group">
				<label class="sr-only" for="lookup-type">订单类型</label> 
				<select	class="form-control" id="lookup-type" name="type" data-value="${ pager.lookup.type }">
					<option value="">--订单类型--</option>
					<option value="1">--外卖--</option>
					<option value="2">--自提--</option>
					<option value="3">--其他--</option>
				</select>
			</div>
			<div class="form-group">
				<label class="sr-only" for="lookup-payType">支付方式</label> 
				<select	class="form-control" id="lookup-payType" name="payType" data-value="${ pager.lookup.payType }">
					<option value="">--支付方式--</option>
					<option value="1">--余额--</option>
					<option value="2">--支付宝--</option>
					<option value="3">--微信--</option>
				</select>
			</div>
			

			<div class="form-group form-btn-bar">
				<button type="submit" class="btn btn-primary btn-sm">
					<i class="glyphicon glyphicon-search"></i> 查询
				</button>
				<button type="button" class="btn btn-info btn-sm reset">
					<i class="glyphicon glyphicon-refresh"></i> 重置
				</button>
			</div>

		</form>
		<hr />
		<!-- 操作信息提示的地方 -->
		<c:if test="${ not empty remind}">
			<div class="alert alert-${ remind.level}">
				<button data-dismiss="alert" class="close" type="button">
					<i class="glyphicon glyphicon-remove"></i>
				</button>
				${ remind.message }
			</div>
		</c:if>
		<!--列表栏-->
		<form class="nocol">
			<div class="action-bar">
				<%--<a class="btn btn-sm btn-success" href="0">
					<i class="glyphicon glyphicon-plus"></i> 添加
				</a>--%>

				<%--<button class="btn btn-sm btn-danger action-post" type="button"
					data-checkbox-required="ids" data-href="remove"  
					data-confirm="确认删除吗">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button>--%>
			</div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>订单号</th>																									
						<th>订单类型</th>
						<th>城市</th>
						<th>用户名</th>
						<th>电话</th>
						<th>所属商家</th>
						<th>商家电话</th>
						<th>总金额</th>
						<th>实际支付</th>
						<th>购买数量</th>
						<th>订单状态</th>
						<th>购物物品</th>
						<th>支付方式</th>
						<th>第三方支付单号</th>
						<th>购买时间</th>
						<th>是否删除</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td>${ cur.orderno }</td>
							<td><span data-random-label="${ cur.orderType }">${cur.orderType ==1?'直接购买':(cur.orderType==2?'团购':'酒店预订') }</span></td>
							<td>${ cur.cityName }</td>
							<td>${ cur.customerName }</td>
							<td>${ cur.mobile }</td>
							<td>${ cur.merchantName }</td>
							<td>${ cur.merchantMobile }</td>
							<td>${ cur.totalFee }</td>
							<td>${ cur.amount }</td>
							<td>${ cur.quantity }</td>
							<td><span data-random-label="${ cur.status }">${cur.statusDesc}</span></td>
							<td>${ cur.orderName }</td>
							<td><span data-random-label="${ cur.payType }">${cur.payType ==1?'余额':(cur.payType==2?'支付宝':'微信') }</span></td>
							<td>${ cur.payOutNo }</td>
							<td><fmt:formatDate value="${cur.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td><span data-random-label="${ cur.isDelete }">${cur.isDelete?'是':'否' }</span></td>
							<td>
								<div class="btn-group-dis">
								
									<a class="btn btn-xs btn-success " href="${ cur.orderno}" data-toggle="tooltip" title="详情" > 
										<i	class="glyphicon glyphicon-list-alt"></i>
									</a>
									<a class="btn btn-xs btn-info " href="${ cur.orderno}/timeline" data-toggle="tooltip" title="订单时间轴" > 
										<i	class="glyphicon glyphicon-time"></i>
									</a>
									
								</div>
							</td>
						</tr>
					</c:forEach>
					<c:forEach begin="${ fn:length(pager.datas)}" end="${ pager.size-1}">
						<tr>
							<td>&nbsp;</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<%@include file="/page/jspf/console-pager.jspf"%>
	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
