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
		<form class="form-inline lookup" role="form" method="post">
			<div class="form-group">
				<label class="sr-only" for="lookup-name">名称</label> 
				<input 	type="text" class="form-control" id="name" name="name" value="${pager.lookup.name }" placeholder="请输入名称">
			</div>

			<div class="form-group">
				<label class="sr-only" for="lookup-name">备注</label> 
				<input	type="text" class="form-control" id="name" name="remark"	value="${pager.lookup.remark }" placeholder="请输入备注">
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
					<i class="glyphicon glyphicon-plus"></i> 添加
				</a>
				
				<button class="btn btn=sm btn-info action-get" type="button" data-href="some">
					<i class="glyphicon glyphicon-screenshot"></i> 测试
				</button>
					
				<button class="btn btn-sm btn-danger action-post" type="button"
					data-checkbox-required="pks" data-href="remove" data-confirm="确认删除吗">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button>
			</div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="pks"></th>
						<th>名称</th>
						<th>备注</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${cur.id }" name="pks"></td>
							<td>${cur.name }</td>
							<td>${cur.remark }</td>
							<td>
								<div class="btn-group">
									<a class="btn btn-xs btn-success " href="${cur.id}"
										data-toggle="tooltip" title="编辑" data-btn="update"> 
										<i class="glyphicon glyphicon-edit"></i>
									</a>
									
									<button class="btn btn-xs btn-danger action-get" data-btn="delete"
										data-href="${cur.id }/remove" data-toggle="tooltip" title="删除"
										data-confirm="确认删除本条记录吗">
										<i class="glyphicon glyphicon-trash"></i>
									</button>
								</div>
							</td>
						</tr>
					</c:forEach>
					<c:forEach begin="${fn:length(pager.datas)}" end="${pager.size-1}">
						<tr>
							<td>&nbsp;</td>
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
