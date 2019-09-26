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
                <label class="sr-only" for="lookup-categoryId">一級分类</label>
				<select	class="form-control" id="lookup-categoryId" name="categoryId"	data-value="${ pager.lookup.categoryId }"
					data-linkage="true" data-linkage-target="#lookup-subCategoryId" data-linkage-url="${ctx }/console/platform/commodity/subCategoryList">
				<option value=""> 一级分类 </option>
					<c:forEach items="${categoryList }" var="cur">
						<option value="${cur.id }">--${cur.name }--</option>
					</c:forEach>
				</select>
			</div>
			<%--
			<div class="form-group">
				<label class="sr-only" for="lookup-subCategoryId">二级分类</label> 
				<select	class="form-control" id="lookup-subCategoryId" name="subCategoryId" data-value="${ pager.lookup.subCategoryId }">
					<option value="">--二级分类--</option>
				</select>
			</div>--%>


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
                        <th>商品名称</th>
						<%-- <th>所属商家</th>--%>
                        <th>一级分类</th>
                        <th>商品图片</th>
                        <th>规格</th>
                        <th>单价</th>
                        <th>单位</th>
                        <th>库存</th>
                        <%--
                                                <th>描述详情</th>--%>
                        <th>是否团购</th>
						<th>状态</th>
                        <th>创建时间</th>
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
								<%-- <td>${ cur.merchantName }</td>--%>
                            <td>${ cur.categoryName }</td>
                            <td><img class="amplifier" data-attachment="${ cur.images }"></td>
                            <td>${ cur.spec }</td>
							<td>${ cur.price }</td>
                            <td>${ cur.company }</td>
                            <td><span data-random-label="${ cur.stock }">${cur.stock==0?'售罄':cur.stock }</span></td>

                                <%--<td>${ cur.content }</td>--%>
                            <td><span data-random-label="${ cur.isgroupbuy }">${cur.isgroupbuy==1?'是':'否' }</span></td>

                            <td><span
                                    data-random-label="${ cur.enabled }">${cur.enabled==1?'上架':cur.enabled==2?"下架审核中": cur.enabled==3 ? '上架审核中':"下架"}</span>
                            </td>

                            <td><fmt:formatDate value="${cur.create_time}" pattern="yyyy-MM-dd"/></td>

                            <td>
								<div class="btn-group btn-group-dis">
                                    <a class="btn btn-xs btn-success " href="${ cur.id}"
                                       data-toggle="tooltip" title="编辑"> <i
										class="glyphicon glyphicon-edit"></i>
									</a>

                                    <button class="btn btn-xs btn-warning action-get"
                                            data-href="${ cur.id }/lowerShelf" data-toggle="tooltip"
                                            title="下架" data-confirm="确认下架此商品吗">
                                        <i class="glyphicon glyphicon-arrow-down"></i>
                                    </button>

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
