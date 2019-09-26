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
				<label class="sr-only" for="lookup-username">名称</label> 
				<input type="text" class="form-control" id="lookup-name" name="name" value="${ pager.lookup.name }" placeholder="请输入名称">
			</div>

			<div class="form-group">
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
						<th>名称</th>
						<th>分类</th>
						<th>所需积分</th>
						<th>商品价格</th>
						<th>规格</th>
						<th>图标</th>
						<th>状态</th>
						<th>商户名称</th>
						<th>商户热线</th>
						<th>服务时间</th>
						<th>已兑换</th>
						<th></th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td>${ cur.name }</td>
							<td>${ cur.categoryName }</td>
							<td>${ cur.score }</td>
							<td>${ cur.price }</td>
							<td title="${cur.norms }">${fn:substring(cur.norms, 0, 20)}${fn:length(cur.norms) gt 20 ? "..." : ""}</td>
							<td><img data-attachment="${ cur.icon }" class="amplifier" /></td>
							<td><span data-random-label="${cur.enabled}">${cur.enabled ==1?'上架':'下架' }</span></td>
							<td>${ cur.merchantName }</td>
							<td>${ cur.merchantTel }</td>
							<td>${ cur.merchantServiceTime }</td>
							<td>${ cur.swapedNum }</td>
							<td><fmt:formatDate value="${cur.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				
							<td>
								<div class="btn-group-dis">
								
									<c:if test="${cur.enabled eq 0  }">
										<button class="btn btn-xs btn-info action-get" type="button"										
											data-href="${cur.id }/enabled"  
											data-toggle="tooltip" title="上架" data-confirm="确认上架吗">
											<i class="glyphicon glyphicon-eye-open"></i>
											<i class="action-param" data-key="enabled" data-value="1" ></i>
										</button>
									</c:if>
									
									<c:if test="${cur.enabled eq 1  }">
										<button class="btn btn-xs btn-warning action-get"  type="button"	
											data-href="${cur.id }/enabled"
											data-toggle="tooltip" title="下架" data-confirm="确认下架吗">
											<i class="glyphicon glyphicon-lock"></i>
											<i class="action-param" data-key="enabled" data-value="0" ></i>
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
