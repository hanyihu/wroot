<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>后台</title>
<%@include file="/page/jspf/head.jspf"%>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<div class="row row-nocol">
		<form class="form-inline lookup hidden" role="form" method="post">
			<div class="form-group">
				<label class="sr-only" for="lookup-name">名称</label> <input
					type="text" class="form-control" id="name" name="name"
					value="${pager.lookup.name }" placeholder="请输入名称">
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
		<hr class="hidden"/>
		<!-- 操作信息提示的地方 -->
		<c:if test="${not empty remind}">
			<div class="alert alert-${remind.level}">
				<button data-dismiss="alert" class="close" type="button">
					<i class="glyphicon glyphicon-remove"></i>
				</button>
				${remind.message}
			</div>
		</c:if>
		<!--列表栏-->
		<form class="nocol">
			<div class="action-bar">
				<a class="btn btn-sm btn-success" href="0" data-btn="add">
					<i class="glyphicon glyphicon-plus"></i> 添加</a>
				<button class="btn btn-sm btn-danger action-post" type="button" data-btn="deletes"
					data-checkbox-required="pks" data-href="remove" data-confirm="确认删除吗">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button>
			</div>

			<table class="table table-striped table-bordered table-hover sub">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="pks"></th>
						<th>模块名称</th>
						<th>排序</th>
						<th>URL</th>
						<th>按钮</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.datas}" var="cur" varStatus="status">
						<tr class="${cur.parentId eq 0 ? 'info' : ''}"  data-value="${cur.id }" data-parent="${cur.parentId }">
							<td class="${cur.parentId eq 0 ? 'text-center' : 'text-right'}"><input type="checkbox" value="${cur.id }" name="pks"></td>
							<td>${cur.name }</td>
							<td>${cur.sort }</td>
							<td>${cur.url }</td>
							<td>${cur.btns }</td>
							<td class="text-center">
								<div class="btn-group btn-group-dis">
									<c:if test="${cur.parentId eq 0}">
										<a class="btn btn-xs btn-success " href="${cur.id}"  data-btn="update"
											data-toggle="tooltip" title="编辑"> 
											<i class="glyphicon glyphicon-edit"></i>
										</a>
										
										<button class="btn btn-xs btn-info action-get" type="button" data-btn="add" data-href="sub/0" data-toggle="tooltip" title="添加子模块">
											<i class="glyphicon glyphicon-plus"></i>
											<i class="action-param" data-key="pId" data-value="${cur.id}" ></i>
										</button>
									
									</c:if>
									
									<c:if test="${cur.parentId ne 0}">
										<a class="btn btn-xs btn-success " href="sub/${cur.id}"	data-toggle="tooltip" title="编辑"  data-btn="update">  
											<i class="glyphicon glyphicon-edit"></i>
											<i class="action-param" data-key="pId" data-value="${cur.id}" ></i>
										</a>
										
									
									</c:if>
									
									<button class="btn btn-xs btn-danger action-get" type="button"  data-btn="delete"
										data-href="${cur.id }/remove" data-toggle="tooltip" title="删除"
										data-confirm="确认删除本条记录吗">
										<i class="glyphicon glyphicon-trash"></i>
									</button>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
