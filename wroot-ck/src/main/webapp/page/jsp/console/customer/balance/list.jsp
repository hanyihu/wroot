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
				<label class="sr-only" for="lookup-username">用户昵称</label> 
				<input type="text" class="form-control" id="lookup-name" name="name" value="${ pager.lookup.name }" placeholder="用户昵称">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-username">用户手机</label> 
				<input type="text" class="form-control" id="lookup-mobile" name="mobile" value="${ pager.lookup.mobile }" placeholder="用户手机">
			</div>
			
			
			<%-- <div class="form-group">
				<label class="sr-only" for="lookup-mobile">用户状态</label> 
				<select	class="form-control" id="lookup-status" name="status"data-value="${ pager.lookup.status }">
					<option value="">--请选择--</option>
					<option value="1">--激活--</option>
					<option value="0">--冻结--</option>
				</select>
			</div> --%>

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
			
			</div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>用户昵称</th>
						<th>用户手机</th>
						<th>余额</th>
						<th>版本</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td>${ cur.customerName }</td>
							<td>${ cur.customerMobile }</td>
							<td>${ cur.balance }</td>
							<td>${ cur.version }</td>
							<td>
								<div class="btn-group-dis">
								
									<%-- <a class="btn btn-xs btn-success " href="${ cur.id}"
										data-toggle="tooltip" title="编辑" > 
										<i class="glyphicon glyphicon-edit"></i>
									</a> --%>
									
									<!--  -->
									<a class="btn btn-xs btn-success " href="${ cur.customerId}/records"
										data-toggle="tooltip" title="余额明细" > 
										<i class="glyphicon glyphicon-th-list"></i>
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
