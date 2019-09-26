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
				<label class="sr-only" for="lookup-username">公司名称</label> <input
					type="text" class="form-control" id="lookup-name" name="name"
					value="${ pager.lookup.name }" placeholder="请输入公司名称">
			</div>


			<div class="form-group">
				<label class="sr-only" for="lookup-type">类型</label> 
				<select	class="form-control" id="lookup-type" name="type" data-value="${ pager.lookup.type }">
					<option value="">--请选择类型--</option>
					<option value="1">--商城--</option>
					<option value="2">--城市代理--</option>
				</select>
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-status">是否已处理</label> 
				<select class="form-control" id="lookup-status" name="status"data-value="${ pager.lookup.status }">
					<option value="">--是否已处理--</option>
					<option value="0">--未处理--</option>
					<option value="1">--已处理--</option>
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
						<th>公司名称</th>
						<th>公司地址</th>
						<th>公司负责人</th>
						<th>投放产品</th>
						<th>类型</th>
						<th>意向代理城市</th>
						<th>意向投资金额</th>
						<th>状态</th>
						<th>公司电话</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox"value="${ cur.id }" name="ids"></td>
							<td>${ cur.companyName }</td>
							<td>${ cur.companyAddress }</td>
							<td>${ cur.companyPerson }</td>
							<td>${ cur.product }</td>
							<td><span data-random-label="${ cur.type }">${ cur.typeDesc }</span></td>
							<td>${ cur.city }</td>
							<td>${ cur.amount }</td>
							<td><span data-random-label="${ cur.status }">${ cur.statusDesc }</span></td>
							<td>${ cur.mobile }</td>

							<td>
								<div class="btn-group-dis">

									<c:if test="${cur.status eq 0  }">
										<button class="btn btn-xs btn-info action-get" type="button"										
											data-href="${cur.id }/status"  
											data-toggle="tooltip" title="设为已处理" data-confirm="确认设为已处理吗">
											<i class="glyphicon glyphicon-eye-open"></i>
											<i class="action-param" data-key="status" data-value="1" ></i>
										</button>
									</c:if>
									
									<c:if test="${cur.status eq 1  }">
										<button class="btn btn-xs btn-warning action-get"  type="button"	
											data-href="${cur.id }/status"
											data-toggle="tooltip" title="设为未处理" data-confirm="确认设为未处理吗">
											<i class="glyphicon glyphicon-lock"></i>
											<i class="action-param" data-key="status" data-value="0" ></i>
										</button>
									</c:if>

									<button class="btn btn-xs btn-danger action-get" type="button"	
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
