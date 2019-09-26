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
				<label class="sr-only" for="lookup-startDate">开始时间</label> 
						<input  class="datetime form-control"  id="lookup-startDate" name="startDate" type="text" value="<fmt:formatDate value="${pager.lookup.startDate}" pattern="yyyy-MM-dd"/>" data-date-format="yyyy-mm-dd"  data-min-view="2"      
							data-end-date-target="lookup-endDate"	readonly placeholder="开始日期"  " />
			</div>

			<div class="form-group">
				<label class="sr-only" for="lookup-startDate">结束时间</label>
				<div class="input-group">
					<input  class="datetime form-control"   id="lookup-endDate" name="endDate" type="text"value="<fmt:formatDate value="${pager.lookup.endDate}" pattern="yyyy-MM-dd"/>" data-date-format="yyyy-mm-dd"  data-min-view="2"
							data-start-date-target="lookup-startDate"	readonly placeholder="结束日期"  />
				</div>
			</div>

			<div class="form-group">
				<label class="sr-only" for="lookup-status">订单状态</label>
				<select	class="form-control" id="lookup-status" name="status"	data-value="${ pager.lookup.status }">
					<option value="0" selected>--订单状态--</option>
					<option value="1">--有效订单--</option>
					<option value="2">--无效订单--</option>
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
			<div class="action-bar"></div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<%--<th><input type="checkbox" data-member="ids"></th>--%>
						<%--<th>日期</th>--%>
						<th>订单数</th>
						<th>订单额</th>
						<th>有效订单数</th>
						<th>有效订单额</th>
						<th>下单会员数</th>
						<th>配送支出</th>
					</tr>
				</thead>
				<tbody align="center">

						<tr>
							<%--<td class="center"><input type="checkbox"value="${ statistics.id }" name="ids"></td>--%>
							<%--<td><fmt:formatDate value="${statistics.createTime}" pattern="yyyy-MM-dd" /></td>--%>
							<td>${ allStatistics.orderNumber }</td>
							<td>${ allStatistics.orderAmount }</td>
						    <c:if test="${statistics.status !=4}">
								<td>0</td>
								<td>0</td>
							</c:if>
								<c:if test="${statistics.status ==4}">
									<td>${ statistics.validOrderNum }</td>
									<td>${ statistics.validOrderAmount }</td>
								</c:if>

							<td>${ allStatistics.orderMember }</td>
							<td></td>

						</tr>


						<tr>
							<%--<td>&nbsp;</td>--%>
							<%--<td></td>--%>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>

						</tr>

				</tbody>
			</table>
		</form>
	</div>

	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
