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
				<label class="sr-only" for="lookup-name">昵称</label> 
				<input type="text" class="form-control" id="lookup-name" name="name" value="${ pager.lookup.name }" placeholder="请输入昵称">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-mobile">电话</label> 
				<input type="text" class="form-control" id="lookup-mobile" name="mobile" value="${ pager.lookup.mobile }" placeholder="请输入电话">
			</div>
			
			
			<div class="form-group">
				<label class="sr-only" for="lookup-enabled">用户状态</label> 
				<select	class="form-control" id="lookup-enabled" name="enabled"data-value="${ pager.lookup.enabled }">
					<option value="">--用户状态--</option>
					<option value="1">--激活--</option>
					<option value="0">--冻结--</option>
				</select>
			</div>
			<div class="form-group">
				<label class="sr-only" for="lookup-type">用户类型</label> 
				<select	class="form-control" id="lookup-type" name="type"data-value="${ pager.lookup.type }">
					<option value="">--用户类型--</option>
					<option value="1">--用户--</option>
					<option value="2">--商家--</option>
				</select>
			</div>
			<div class="form-group">
				<label class="sr-only" for="lookup-gender">用户性别</label> 
				<select	class="form-control" id="lookup-gender" name="gender"data-value="${ pager.lookup.gender }">
					<option value="">--用户性别--</option>
					<option value="1">--男--</option>
					<option value="2">--女--</option>
				</select>
			</div>

			<div class="form-group">
				<label class="sr-only" for="lookup-homeCityName">家乡城市</label> 
				<input type="text" class="form-control" id="lookup-homeCityName" name="homeCityName" value="${ pager.lookup.homeCityName }" placeholder="家乡城市">
			</div>
			<div class="form-group">
				<label class="sr-only" for="lookup-liveCityName">居住城市</label> 
				<input type="text" class="form-control" id="lookup-liveCityName" name="liveCityName" value="${ pager.lookup.liveCityName }" placeholder="家乡(市)">
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
			<!-- 	<a class="btn btn-sm btn-success" href="0"> 
					<i class="glyphicon glyphicon-plus"></i> 添加
				</a>

				<button class="btn btn-sm btn-danger action-post" type="button" 
					data-checkbox-required="ids" data-href="remove"  
					data-confirm="确认删除吗">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button> -->
			</div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>手机号</th>
						<th>昵称</th>
						<th>性别</th>
						<th>类型</th>
						<th>居住城市</th>
						<th>家乡城市</th>
						<th>积分</th>
						<th>状态</th>
						<th>创建时间</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td>${ cur.mobile }</td>
							<td>${ cur.nickname }</td>
							<td><span data-random-label="${cur.gender}">${cur.gender ==1?'男':(cur.gender==2?'女':'保密') }</span></td>
							<td><span data-random-label="${cur.type}">${cur.type ==1?'用户':'商家' }</span></td>
							<td>${ cur.liveCityName }</td>
							<td>${ cur.homeCityName }</td>
							<td>${ cur.score }</td>
							<td><span data-random-label="${cur.enabled}">${cur.enabled?'激活':'冻结' }</span></td>
							<td><fmt:formatDate value="${cur.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td>
								<div class="btn-group-dis">
									<c:if test="${!cur.enabled}">
										<button class="btn btn-xs btn-info action-get" type="button"										
											data-href="${cur.id }/enabled"  
											data-toggle="tooltip" title="激活" data-confirm="确认激活吗">
											<i class="glyphicon glyphicon-eye-open"></i>
											<i class="action-param" data-key="enabled" data-value="1" ></i>
										</button>
									</c:if>
									
									<c:if test="${cur.enabled  }">
										<button class="btn btn-xs btn-warning action-get"  type="button"	
											data-href="${cur.id }/enabled"
											data-toggle="tooltip" title="禁用" data-confirm="确认禁用吗">
											<i class="glyphicon glyphicon-lock"></i>
											<i class="action-param" data-key="enabled" data-value="0" ></i>
										</button>
									</c:if>
								</div>
							</td>
						</tr>
					</c:forEach>
					<c:forEach begin="${ fn:length(pager.datas)}" end="${ pager.size-1}">
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
