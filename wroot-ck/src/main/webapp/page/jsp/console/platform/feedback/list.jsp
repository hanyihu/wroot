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
				<label class="sr-only" for="lookup-username">名称</label> <input
					type="text" class="form-control" id="lookup-name" name="name"
					value="${ pager.lookup.name }" placeholder="请输入名称">
			</div>


			<div class="form-group">
				<label class="sr-only" for="lookup-mobile">查看状态</label> <select
					class="form-control" id="lookup-status" name="status"
					data-value="${ pager.lookup.status }">
					<option value="">--请选择查看状态--</option>
					<option value="1">--已查看--</option>
					<option value="0">--未查看--</option>
				</select>
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-mobile">用户类型</label> <select
					class="form-control" id="lookup-status" name="status"
					data-value="${ pager.lookup.type }">
					<option value="">--请选择用户类型--</option>
					<option value="1">--用户--</option>
					<option value="0">--商家--</option>
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
			
			</div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>反馈人</th>
						<th>内容</th>
						<th>用户类型</th>
						<th>提交时间</th>
						<th>状态</th>
						<th>回复内容</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox"
								value="${ cur.id }" name="ids"></td>
								
							<td>${cur.customerName }	</td>
							<td title="${cur.content }">${fn:substring(cur.content, 0, 20)}${fn:length(cur.content) gt 20 ? "..." : ""}</td>
							<td><span class="label label-${cur.type eq 1 ? 'success':'danger'}">${cur.typeDesc}</td>
							<td>${ cur.createTimeDesc }</td>
							<td><span class="label label-${cur.status eq 0 ? 'warning':'info'}">${cur.statusDesc}</td>
							<td title="${cur.reply }">${fn:substring(cur.reply, 0, 20)}${fn:length(cur.reply) gt 20 ? "..." : ""}</td>

							<td>
								<div class="btn-group-dis">

									<a class="btn btn-xs btn-success " href="${ cur.id}"
										data-toggle="tooltip" title="编辑"> <i
										class="glyphicon glyphicon-edit"></i>
									</a>


									<button class="btn btn-xs btn-danger action-get"
										data-href="${ cur.id }/remove" data-toggle="tooltip"
										title="删除" data-confirm="确认删除本条记录吗">
										<i class="glyphicon glyphicon-trash"></i>
									</button>
								</div>
							</td>
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
