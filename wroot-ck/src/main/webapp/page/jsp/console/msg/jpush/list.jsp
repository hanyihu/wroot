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
				<label class="sr-only" for="lookup-status">状态</label> 
				<select	class="form-control" id="lookup-status" name="status"data-value="${ pager.lookup.status }">
					<option value="">--推送状态--</option>
					<option value="1">--已推送--</option>
					<option value="0">--未推送--</option>
				</select>
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-type">平台</label> 
				<select	class="form-control" id="lookup-type" name="type"data-value="${ pager.lookup.type }">
					<option value="">--选择平台--</option>
					<option value="1">--用户端--</option>
					<option value="2">--商家端--</option>
					<option value="3">--骑手端--</option>
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
				<a class="btn btn-sm btn-success" href="0"> 
					<i class="glyphicon glyphicon-plus"></i> 添加
				</a>
			</div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>平台</th>
						<th>标题</th>
						<th>内容</th>
						<th>推送状态</th>
						<th>目标</th>
						<th>创建时间</th>
						<th>推送时间</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td><span data-random-label="${cur.role}">${cur.role==1?'用户端':cur.role==2?'商家端':'骑手端' }</span></td>
							<td>${ cur.title }</td>
							<td title="${cur.content }">${fn:substring(cur.content, 0, 10)}${fn:length(cur.content) gt 10 ? "..." : ""}</td>
							<%--<td>${ cur.content }</td>--%>
							<td><span data-random-label="${cur.status}">${cur.status==1?'已推送':'未推送' }</span></td>
							<td>${ cur.targetDesc }</td>
							<td><fmt:formatDate value="${cur.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td><fmt:formatDate value="${cur.pushTime}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td>
								<div class="btn-group btn-group-dis">
									<c:if test="${cur.status == 0 }">
										<a class="btn btn-xs btn-success " href="${ cur.id}"
											data-toggle="tooltip" title="编辑" > 
											<i class="glyphicon glyphicon-edit"></i>
										</a>
										<a class="btn btn-xs btn-info " href="${ cur.id}/push"
											data-toggle="tooltip" title="推送" > 
											<i class="glyphicon glyphicon-send"></i>
										</a>
										<button class="btn btn-xs btn-danger action-get"
											data-href="${ cur.id }/remove"
											data-toggle="tooltip" title="删除" data-confirm="确认删除本条记录吗">
											<i class="glyphicon glyphicon-trash"></i>
										</button>
									
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
