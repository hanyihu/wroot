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

			<%--<div class="form-group">
				<label class="sr-only" for="lookup-categoryId">一級分类</label>
				<select	class="form-control" id="lookup-categoryId" name="categoryId"	data-value="${ pager.lookup.categoryId }"
						   data-linkage="true" data-linkage-target="#lookup-subCategoryId" data-linkage-url="${ctx }/console/platform/commodity/subCategoryList">
					<option value=""> 一级分类 </option>
					<c:forEach items="${categoryList }" var="cur">
						<option value="${cur.id }">--${cur.name }--</option>
					</c:forEach>
				</select>
			</div>--%>
			<%--
			<div class="form-group">
				<label class="sr-only" for="lookup-subCategoryId">二级分类</label> 
				<select	class="form-control" id="lookup-subCategoryId" name="subCategoryId" data-value="${ pager.lookup.subCategoryId }">
					<option value="">--二级分类--</option>
				</select>
			</div>--%>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-enabled">状态</label>
				<select	class="form-control" id="lookup-enabled" name="enabled"	data-value="${ pager.lookup.enabled }">
					<option value="">--状态--</option>
					<option value="3">--待上架--</option>
					<option value="2">--待下架--</option>
					<option value="1">--上架--</option>
					<option value="0">--下架--</option>
				</select>
			</div>

			<div class="form-group">
				<label class="sr-only" for="lookup-examine">审核状态</label>
				<select	class="form-control" id="lookup-examine" name="examine"	data-value="${ pager.lookup.examine }">

					<option value="">--审核状态--</option>
					<option value="0">--待审核--</option>
					<option value="2">--审核通过--</option>
					<option value="1">--审核未通过--</option>
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
						<th>商品名称</th>
						<%--<th>所属商家</th>--%>
						<th>一级分类</th>
						<th>商品图片</th>
						<th>规格</th>
						<th>单价</th>
						<th>单位</th>
						<th>库存</th>
						<th>状态</th>
						<th>审核</th>
						<th>审核说明</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center">
								<input type="checkbox"	value="${ cur.id }" name="ids">
							</td>
							<td title="${cur.name }">${fn:substring(cur.name, 0, 10)}${fn:length(cur.name) gt 10 ? "..." : ""}</td>
								<%--<td>${ cur.merchantName }</td>--%>
							<td>${ cur.categoryName }</td>
							<td><img class="amplifier" data-attachment="${ cur.images }"></td>
							<td>${ cur.spec }</td>
							<td>${ cur.price }</td>
							<td>${ cur.company }</td>
							<td>
								<c:if test="${ cur.stock == 0 }">
									<span data-random-label="${ cur.stock }">${cur.stock==0?'售罄':cur.stock }</span>
								</c:if>
								<c:if test="${ cur.stock != 0 }">
									${ cur.stock }
								</c:if>
							</td>


							<td><span data-random-label="${cur.enabled}">
									${cur.enabled==0 ?'已下架':cur.enabled==1?'已上架':cur.enabled==2?'待下架':'待上架'}
									</span></td>
							<td><span data-random-label="${cur.examine}">
								${cur.examine==0?'待审核':cur.examine==1?'审核未通过':'审核通过'}

							</span></td>
							<td>${ cur.examineRemarks }</td>
							<td>
								<div class="btn-group btn-group-dis">

									<button class="btn btn-xs btn-info action-get"
											data-href="${cur.id }/examine"
											data-toggle="tooltip" title="审核通过" data-confirm="确认通过吗">
										<i class="glyphicon glyphicon-eye-open"></i>
									</button>

										<button class="btn btn-xs btn-warning action-get"
												data-href="${cur.id }/examineNoPass_1"
												data-toggle="tooltip" title="审核不通过" >
											<i class="glyphicon glyphicon-eye-close"></i>

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
