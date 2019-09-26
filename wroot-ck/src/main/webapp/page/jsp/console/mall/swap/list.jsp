<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">

<%@include file="/page/jspf/head.jspf"%>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<div class="row row-nocol">
		<form class="form-inline lookup" role="form" method="post">
			<div class="form-group">
				<label class="sr-only" for="lookup-swapno">商家名称</label> 
				<input	type="text" class="form-control" id="lookup-swapno" name="swapno"	value="${ pager.lookup.swapno }" placeholder="请输入兑换码">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-name">用户名</label> 
				<input	type="text" class="form-control" id="lookup-name" name="name"	value="${ pager.lookup.name }" placeholder="请输入用户名">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-mobile">手机号</label> 
				<input	type="text" class="form-control" id="lookup-mobile" name="mobile"	value="${ pager.lookup.mobile }" placeholder="请输入手机号" />
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-swapMallName">商品名</label> 
				<input	type="text" class="form-control" id="lookup-swapMallName" name="swapMallName"	value="${ pager.lookup.swapMallName }" placeholder="商品名">
			</div>

			<div class="form-group">
				<label class="sr-only" for="lookup-status">订单状态</label> <select
					class="form-control" id="lookup-status" name="status"
					data-value="${ pager.lookup.status }">
					<option value="">--订单状态--</option>
					<option value="0">--未发货--</option>
					<option value="1">--配送中--</option>
					<option value="2">--已完成--</option>
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
						<th>所属用户</th>
						<th>兑换码</th>
						<th>电话</th>
						<th>商品图标</th>
						<th>商品</th>
						<th>数量</th>
						<th>总积分</th>
						<th>备注</th>
						<th>状态</th>
						<th>兑换时间</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td>${ cur.customerName }</td>
							<td>${ cur.swapno }</td>
							<td>${ cur.mobile }</td>
							<td><img  data-attachment="${cur.icon }" class="amplifier"/></td>
							<td>${ cur.swapMallName }</td>
							<td>${ cur.quantity }</td>
							<td>${ cur.score }</td>
							<td title="${cur.remark }">${fn:substring(cur.remark, 0, 20)}${fn:length(cur.remark) gt 20 ? "..." : ""}</td>
							<td><span data-random-label="${ cur.status }">${cur.status ==0?'未发货':(cur.status==1?'配送中':'已完成') }</span></td>
							<td><fmt:formatDate value="${cur.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>

							<td>
								<div class="btn-group-dis">
									<a class="btn btn-xs btn-success " href="${ cur.id}" data-toggle="tooltip" title="详情"> 
										<i	class="glyphicon glyphicon-list-alt"></i>
									</a>

									<c:if test="${cur.status eq 0  }">
										<button class="btn btn-xs btn-info action-get" type="button"										
											data-href="${cur.id }/status"  
											data-toggle="tooltip" title="发货" data-confirm="确认发货并配送吗">
											<i class="glyphicon glyphicon-send"></i>
											<i class="action-param" data-key="status" data-value="1" ></i>
										</button>
									</c:if>
									
									<c:if test="${cur.status eq 1  }">
										<button class="btn btn-xs btn-success action-get" type="button"										
											data-href="${cur.id }/status"  
											data-toggle="tooltip" title="完成" data-confirm="确认完成吗">
											<i class="glyphicon glyphicon-check"></i>
											<i class="action-param" data-key="status" data-value="2" ></i>
										</button>
									</c:if>

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
