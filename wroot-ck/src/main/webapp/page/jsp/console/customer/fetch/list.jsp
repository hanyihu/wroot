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
				<label class="sr-only" for="lookup-name">用户昵称</label> 
				<input type="text" class="form-control" id="lookup-name" name="name" value="${ pager.lookup.name }" placeholder="用户昵称">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-mobile">用户电话</label> 
				<input type="text" class="form-control" id="lookup-mobile" name="mobile" value="${ pager.lookup.mobile }" placeholder="用户电话">
			</div>
			
			
			<div class="form-group">
				<label class="sr-only" for="lookup-status">审核状态</label> 
				<select	class="form-control" id="lookup-status" name="status" data-value="${ pager.lookup.status }">
					<option value="">--审核状态--</option>
					<option value="0">--待审核--</option>
					<option value="1">--已打款--</option>
					<option value="2">--已拒绝--</option>
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
				<!-- <a class="btn btn-sm btn-success" href="0"> 
					<i class="glyphicon glyphicon-plus"></i> 添加
				</a>

				<button class="btn btn-sm btn-danger action-post" type="button" 
					data-checkbox-required="ids" data-href="remove"  
					data-confirm="确认删除吗">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button> -->
			</div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>用户昵称</th>
						<th>用户电话</th>
						<th>提现金额</th>
						<th>申请时间</th>
						<th>审核状态</th>
						<th>审核日期</th>
						<th>不通过的原因</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td>${ cur.customerName }</td>
							<td>${ cur.customerMobile }</td>
							<td>${ cur.money }</td>
							<td><fmt:formatDate value="${cur.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td><span data-random-label="${cur.status}">${cur.status==0?'待审核':(cur.status==1?'已打款':'已拒绝') }</span></td>
							<td><fmt:formatDate value="${cur.fetchTime}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td>${ cur.denyReason }</td>
							<td>
								<div class="btn-group-dis">
								
									<c:if test="${cur.status==0}">
										<a class="btn btn-xs btn-success " href="${ cur.id}"
											data-toggle="tooltip" title="审核" > 
											<i class="glyphicon glyphicon-edit"></i>
										</a>	
									</c:if>
									
									<c:if test="${cur.status!=0}">
										<a class="btn btn-xs btn-success " href="${ cur.id}/detail"
											data-toggle="tooltip" title="查看" > 
											<i class="glyphicon glyphicon-eye-open"></i>
										</a>	
									</c:if>
									
									
									
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
