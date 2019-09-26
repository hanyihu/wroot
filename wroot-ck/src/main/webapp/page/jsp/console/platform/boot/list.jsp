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
				<label class="sr-only" for="lookup-username">标题</label> 
				<input type="text" class="form-control" id="lookup-name" name="name" value="${ pager.lookup.name }" placeholder="请输入标题">
			</div>


			<div class="form-group">
				<label class="sr-only" for="lookup-mobile">状态</label> 
				<select class="form-control" id="lookup-status" name="status" data-value="${ pager.lookup.status }">
					<option value="">--请选择--</option>
					<option value="1">--启用--</option>
					<option value="0">--禁用--</option>
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

				<button class="btn btn-sm btn-danger action-post" type="button"
					data-checkbox-required="ids" data-href="remove"
					data-confirm="确认删除吗">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button>
			</div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>图片</th>
						<th>标题</th>
						<th>状态</th>
						<th>排序</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox"
								value="${ cur.id }" name="ids"></td>
							<td><img data-attachment="${ cur.image }"  class="amplifier"/></td>
							<td title="${cur.title }">${fn:substring(cur.title, 0, 20)}${fn:length(cur.title) gt 20 ? "..." : ""}</td>
							<td><span data-random-label="${ cur.status }">${ cur.statusDesc }</span></td>
							<td>${ cur.sort }</td>

							<td>
								<div class="btn-group-dis">
								
									<c:if test="${cur.status eq 0  }">
										<button class="btn btn-xs btn-info action-get" type="button"										
											data-href="${cur.id }/status"  
											data-toggle="tooltip" title="启用" data-confirm="确认启用吗">
											<i class="glyphicon glyphicon-eye-open"></i>
											<i class="action-param" data-key="status" data-value="1" ></i>
										</button>
									</c:if>
									
									<c:if test="${cur.status eq 1  }">
										<button class="btn btn-xs btn-warning action-get"  type="button"	
											data-href="${cur.id }/status"
											data-toggle="tooltip" title="禁用" data-confirm="确认禁用吗">
											<i class="glyphicon glyphicon-lock"></i>
											<i class="action-param" data-key="status" data-value="0" ></i>
										</button>
									</c:if>
								

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
