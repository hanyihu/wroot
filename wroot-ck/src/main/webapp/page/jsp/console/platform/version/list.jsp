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
				<label class="sr-only" for="lookup-name">版本名称</label> 
				<input type="text" class="form-control" id="lookup-name" name="name" value="${ pager.lookup.name }" placeholder="版本名称">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-number">数字编号</label> 
				<input type="text" class="form-control" id="lookup-number" name="number" value="${ pager.lookup.number }" placeholder="数字编号">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-version">版本</label> 
				<input type="text" class="form-control" id="lookup-version" name="version" value="${ pager.lookup.version }" placeholder="版本">
			</div>


			<div class="form-group">
				<label class="sr-only" for="lookup-machineType">手机类型</label> 
				<select	class="form-control" id="lookup-machineType" name="machineType"
					data-value="${ pager.lookup.machineType }">
					<option value="">--请选择手机类型--</option>
					<option value="1">--安卓--</option>
					<option value="2">--IOS--</option>
				</select>
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-appType">APP类型</label> 
				<select	class="form-control" id="lookup-appType" name="appType"
					data-value="${ pager.lookup.appType }">
					<option value="">--请选择APP类型--</option>
					<option value="1">--用户端--</option>
					<option value="2">--商家端--</option>
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
				<a class="btn btn-sm btn-success" href="0"> <i
					class="glyphicon glyphicon-plus"></i> 添加
				</a>

				<!-- <button class="btn btn-sm btn-danger action-post" type="button"
					data-checkbox-required="ids" data-href="remove"
					data-confirm="确认删除吗">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button> -->
			</div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>版本名称</th>
						<th>版本</th>
						<th>数字编号</th>
						<th>手机类型</th>
						<th>APP类型</th>
						<th>更新说明</th>
						<th>下载地址</th>
						<th>创建日期</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td>${ cur.name }</td>
							<td>${ cur.version }</td>
							<td>${ cur.number }</td>
							<td><span data-random-label="${ cur.machineType }">${ cur.machineType==1?'安卓':'IOS' }</span></td>
							<td><span data-random-label="${ cur.appType }">${ cur.appType==1?'用户端':'商户端' }</span></td>
							<td title="${ cur.log }">${fn:substring(cur.log, 0, 20 )} ${fn:length(cur.log) gt 20 ? "..." : "" }</td>
							<td>${ cur.downloadUrl }</td>
							<td><fmt:formatDate value="${cur.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td>
								<div class="btn-group btn-group-dis">

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
					<c:forEach begin="${ fn:length(pager.datas)}"	end="${ pager.size-1}">
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
