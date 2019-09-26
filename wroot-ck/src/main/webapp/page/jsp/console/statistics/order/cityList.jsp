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
			<!-- 权限为全国的则显示选择城市 -->
			<c:if test="${empty principal.cityId}">
				<div class="form-group">
					<label class="sr-only" for="lookup-cityId">城市</label> 
					<select	class="form-control" id="lookup-cityId" name="cityId" data-value="${ pager.lookup.cityId }">
						<option value="">请选择城市</option>
						<c:forEach items="${cityList }"  var="city">
							<option value="${city.id }">${city.name }</option>
						</c:forEach>
					</select>
				</div>
			</c:if>
			<div class="form-group">
				<label class="sr-only" for="lookup-startDate">开始时间</label> 
				<div class="input-group">
					<input  class="datetime form-control"  id="lookup-startDate" name="startDate" type="text" value="<fmt:formatDate value="${pager.lookup.startDate}" pattern="yyyy-MM-dd"/>" data-date-format="yyyy-mm-dd"  data-min-view="2"      
						data-end-date-target="endDate"	readonly placeholder="开始日期" />
				</div>
			</div>
			<div class="form-group">
				<label class="sr-only" for="lookup-startDate">结束时间</label> 
				<div class="input-group">
					<input  class="datetime form-control"  id="lookup-endDate" name="endDate" type="text"value="<fmt:formatDate value="${pager.lookup.endDate}" pattern="yyyy-MM-dd"/>" data-date-format="yyyy-mm-dd"  data-min-view="2"    
					data-start-date-target="startDate"	readonly  placeholder="结束日期" />
				</div>
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
			<div class="action-bar"></div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>日期</th>
						<th>城市</th>
						<th>总流水</th>
						<th>直接购买流水</th>
						<th>团购流水</th>
						<th>酒店预订流水</th>
						<th>总订单数</th>
						<th>直接购买的订单数量</th>
						<th>团购订单数量</th>
						<th>酒店预订订单数量</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox"value="${ cur.id }" name="ids"></td>
							<td><fmt:formatDate value="${cur.atDate}" pattern="yyyy-MM-dd" /></td>
							<td>${ cur.cityName }</td>
							<td>${ cur.totalAmount }</td>
							<td>${ cur.buyAmount }</td>
							<td>${ cur.groupbuyAmount }</td>
							<td>${ cur.hotelAmount }</td>
							<td>${ cur.totalNumber }</td>
							<td>${ cur.buyNumber }</td>
							<td>${ cur.groupbuyNumber }</td>
							<td>${ cur.hotelNumber }</td>

						</tr>
					</c:forEach>
					<c:forEach begin="${ fn:length(pager.datas)}"
						end="${ pager.size-1}">
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
