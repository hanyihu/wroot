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

			<%--<div class="form-group">
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
			

			<div class="form-group form-btn-bar">
				<button type="submit" class="btn btn-primary btn-sm">
					<i class="glyphicon glyphicon-search"></i> 查询
				</button>
				<button type="button" class="btn btn-info btn-sm reset">
					<i class="glyphicon glyphicon-refresh"></i> 重置
				</button>
			</div>--%>

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
						<th>订单号</th>
						<th>商家名称</th>
						<th>订单金额</th>
						<th>订单实付金额</th>
						<th>结算金额</th>
						<th>结算详情</th>

					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>

							<td>${ cur.orderno }</td>
							<td>${ cur.merchantName }</td>
							<td>${ cur.amount }</td>
							<td>${ cur.payAmount }</td>
							<td>${ cur.settlementAmount }</td>
                            <c:if test="${ cur.settlementDetail ==4}">
								<td>已结算</td>
							</c:if>
							<c:if test="${ cur.settlementDetail==5}">
								<td>已结算</td>
							</c:if>
							<c:if test="${ cur.settlementDetail ==6}">
								<td>已结算</td>
							</c:if>
							<c:if test="${ cur.settlementDetail ==0}">
								<td>未结算</td>
							</c:if>
							<c:if test="${ cur.settlementDetail ==1}">
								<td>未结算</td>
							</c:if>
							<c:if test="${ cur.settlementDetail ==2}">
								<td>未结算</td>
							</c:if>
                            <c:if test="${ cur.settlementDetail ==3}">
								<td>未结算</td>
							</c:if>
                            <c:if test="${ cur.settlementDetail ==7}">
								<td>已退款</td>
							</c:if>


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
