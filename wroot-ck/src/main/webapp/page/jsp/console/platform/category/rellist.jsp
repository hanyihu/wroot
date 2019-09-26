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
				<label class="sr-only" for="lookup-username">商家名称</label> <input
					type="text" class="form-control" id="lookup-name" name="name"
					value="${ pager.lookup.name }" placeholder="商家名称">
			</div>


				<div class="form-group">
				<label class="sr-only" for="lookup-type">展示位置</label> <select
					class="form-control" id="lookup-type" name="type"
					data-value="${ pager.lookup.type }">
					<option value="">展示位置</option>
					<option value="1">--首页--</option>
					<option value="2">--百惠店--</option>
				</select>
			</div>
				
				
			<div class="form-group">
				<label class="sr-only" for="lookup-cityId">请选择城市</label> 
				<select	class="form-control" id="lookup-cityId" name="cityId" data-value="${ pager.lookup.cityId }"
					data-linkage="true" data-linkage-target="#lookup-categoryId" data-linkage-url="${ctx }/console/platform/category/cityCategoryList">
					<option value="">--请选择城市--</option>
					<c:forEach items="${cityList }" var="city">
						<option value="${city.id }">${city.name }</option>
					</c:forEach>
				</select>
			</div>	
			<div class="form-group">
				<label class="sr-only" for="lookup-categoryId">特色分类</label> 
				<select	class="form-control" id="lookup-categoryId" name="categoryId" data-value="${ pager.lookup.categoryId }">
					<option value="">--特色分类--</option>
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
						<th>展示位置</th>
						<th>分类名称</th>
						<th>商家名称</th>
						<th>所属城市</th>
						<th>创建时间</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox"
								value="${ cur.id }" name="ids"></td>
							<td><span data-random-label="${cur.type}">${cur.type ==1?'首页':'百惠店' }</span></td>
							<td>${ cur.categoryName }</td>
							<td>${ cur.merchantName }</td>
							<td>${ cur.cityName }</td>
							<td><fmt:formatDate value="${cur.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>

							<td>
								<div class="btn-group-dis">

									<a class="btn btn-xs btn-success " href="${ cur.id}"
										data-toggle="tooltip" title="编辑"> <i
										class="glyphicon glyphicon-edit"></i>
									</a>


									<button class="btn btn-xs btn-danger action-get" type="button"
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
