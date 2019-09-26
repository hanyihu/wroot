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
				<label class="sr-only" for="lookup-name">城市名称</label> <input
					type="text" class="form-control" id="lookup-name"
					name="name" value="${ pager.lookup.name }"
					placeholder="请输入名称">
			</div>
			
			
			<div class="form-group">
				<label class="sr-only" for="lookup-pid">省份</label> <select
					class="form-control" id="lookup-pid" name="pid"
					data-value="${ pager.lookup.pid }">
					<option value="">--省份--</option>
					<c:forEach items="${provinces }" var="item">
						<option value="${item.id }">--${item.name }--</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<label class="sr-only" for="lookup-opened">是否开放</label> <select
					class="form-control" id="lookup-opened" name="opened"
					data-value="${ pager.lookup.opened }">
					<option value="">--是否开放--</option>
					<option value="1">--是--</option>
					<option value="0">--否--</option>
				</select>
			</div>
			<div class="form-group">
				<label class="sr-only" for="lookup-hot">是否热门</label> <select
					class="form-control" id="lookup-hot" name="hot"
					data-value="${ pager.lookup.hot }">
					<option value="">--是否热门--</option>
					<option value="1">--是--</option>
					<option value="0">--否--</option>
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
						<th>名称</th>
						<th>父层级简称</th>
						<th>城市编码</th>
						<th>首字母</th>
						<th>是否开放</th>
						<th>是否热门城市</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td>${ cur.name }</td>
							<td>${ cur.mergerShortName }</td>
							<td>${ cur.areaCode }</td>
							<td>${ cur.firstChar }</td>
							<td><span class="label label-${cur.opened eq 0 ? 'warning':'info'}">${ cur.openedStr }</span></td>
							<td><span class="label label-${cur.hot eq 0 ? 'danger':'success'}">${ cur.hotStr }</span></td>

							<td>
								<div class="btn-group-dis">
								
									<c:if test="${cur.opened eq 0  }">
										<button class="btn btn-xs btn-info action-get"
											data-href="${cur.id }/opened" 
											data-toggle="tooltip" title="开放城市" data-confirm="确认开放此城市吗吗">
											<i class="glyphicon glyphicon-eye-open"></i>
											<i class="action-param" data-key="opened" data-value="1" ></i>
										</button>
									</c:if>
									<c:if test="${cur.opened eq 1  }">
										<button class="btn btn-xs btn-warning action-get" 
											data-href="${cur.id }/opened"
											data-toggle="tooltip" title="关闭城市" data-confirm="确认关闭此城市吗">
											<i class="glyphicon glyphicon-lock"></i>
											<i class="action-param" data-key="opened" data-value="0" ></i>
										</button>
									</c:if>
									<c:if test="${cur.hot eq 0  }">
										<button class="btn btn-xs btn-success action-get"
											data-href="${cur.id }/hot" 
											data-toggle="tooltip" title="设为热门" data-confirm="确认设为热门城市吗">
											<i class="glyphicon glyphicon-fire"></i>
											<i class="action-param" data-key="hot" data-value="1" ></i>
										</button>
									</c:if>
									<c:if test="${cur.hot eq 1  }">
										<button class="btn btn-xs btn-danger action-get" 
											data-href="${cur.id}/hot"
											data-toggle="tooltip" title="设为非热门" data-confirm="确认设为非热门城市吗">
											<i class="glyphicon glyphicon-off"></i>
											<i class="action-param" data-key="hot" data-value="0" ></i>
										</button>
									</c:if>

									<%-- <a class="btn btn-xs btn-success " href="${cur.id}"
										data-toggle="tooltip" title="编辑"> <i
										class="glyphicon glyphicon-edit"></i>
									</a>
									<button class="btn btn-xs btn-danger action-get"
										data-href="${ cur.id }/remove"
										data-toggle="tooltip" title="删除" data-confirm="确认删除本条记录吗">
										<i class="glyphicon glyphicon-trash"></i>
									</button> --%>
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
