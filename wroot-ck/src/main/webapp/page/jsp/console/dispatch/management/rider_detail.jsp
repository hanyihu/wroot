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
				<label class="sr-only" for="lookup-username">名称</label> 
				<input type="text" class="form-control" id="lookup-name" name="name" value="${ pager.lookup.name }" placeholder="请输入名称">
			</div>--%>

			<%--<div class="form-group">
				<label class="sr-only" for="lookup-categoryId">请选择分类</label>
				<select	class="form-control" id="lookup-categoryId" name="categoryId"		data-value="${ pager.lookup.categoryId }">
					<option value="">--请选择分类--</option>
					<c:forEach items="${categoryList }" var="categoryId">
						<option value="${categoryId.id }">${categoryId.name }</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<label class="sr-only" for="lookup-enabled">上架状态</label>
				<select	class="form-control" id="lookup-enabled" name="enabled"	data-value="${ pager.lookup.enabled }">
					<option value="">--请选择上架状态--</option>
					<option value="1">--上架--</option>
					<option value="0">--下架--</option>
				</select>
			</div>
--%>
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


			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>骑手名</th>
						<th>骑手距离</th>
						<th>骑手名单状态</th>


						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td>${ cur.nickname }</td>
							<td>${ cur.diatance }</td>

							<td><span data-random-label="${cur.listStatus}">${cur.listStatus ==1?'黑名单':cur.listStatus ==2?'白名单':'普通'}</span></td>

							<td>
								<div class="btn-group-dis">

									<a class="btn btn-xs btn-success " href="${ cur.id}/rider_detail"
										data-toggle="tooltip" title="推送">
										<i class="glyphicon glyphicon-hand-right"></i>
									</a>


								</div>
							</td>
						</tr>
					</c:forEach>
					<%--<c:forEach begin="${ fn:length(pager.datas)}"
						end="${ pager.size-1}">
						<tr>
							<td>&nbsp;</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>

							
						</tr>
					</c:forEach>--%>
				</tbody>
			</table>
		</form>
	</div>
	<%--<%@include file="/page/jspf/console-pager.jspf"%>--%>
	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
