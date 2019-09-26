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
				<label class="sr-only" for="lookup-username">用户名</label> 
				<input type="text" class="form-control" id="lookup-username" name="username" value="${pager.lookup.username }" placeholder="请输入用户名">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-mobile">电话</label> 
				<input type="text" class="form-control" id="lookup-mobile" name="mobile" value="${pager.lookup.mobile }" placeholder="请输电话">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-mobile">用户状态</label> 
				<select	class="form-control" id="lookup-status" name="status"data-value="${pager.lookup.status }">
					<option value="">--请选择--</option>
					<option value="1">--激活--</option>
					<option value="0">--冻结--</option>
				</select>
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-cityId">管辖城市</label> 
				<select	class="form-control" id="lookup-cityId" name="cityId" data-value="${ pager.lookup.cityId }">
					<option value="">全国</option>
					<c:forEach items="${cityList }"  var="city">
						<option value="${city.id }">${city.name }</option>
					</c:forEach>
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

				<button class="btn btn-sm btn-danger action-post" type="button"
					data-checkbox-required="ids" data-href="remove"  data-btn="deletes"
					data-confirm="确认删除吗">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button>
			</div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>用户名</th>
						<th>昵称</th>
						<th>电话</th>
						<th>管辖城市</th>
						<th>状态</th>
						<th>创建时间</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${cur.id }" name="ids"></td>
							<td>${cur.username }</td>
							<td>${cur.nickname}</td>
							<td>${cur.mobile }</td>
							<td>${cur.cityName }</td>
							<td><span class="label label-${cur.status eq 0 ? 'warning':'info'}">${cur.statusStr}</span></td>
							<td>${cur.createTimeStr }</td>
							<td>
								<div class=" btn-group-dis">
									<c:if test="${cur.status eq 0  }">
										<button class="btn btn-xs btn-info action-get"
											data-href="${cur.id }/status" data-btn="activate"
											data-toggle="tooltip" title="激活" data-confirm="确认激活此用户吗">
											<i class="glyphicon glyphicon-eye-open"></i>
											<i class="action-param" data-key="status" data-value="1" ></i>
										</button>
									</c:if>
									
									<c:if test="${cur.status eq 1  }">
										<button class="btn btn-xs btn-warning action-get" data-btn="frozen"
											data-href="${cur.id }/status"
											data-toggle="tooltip" title="冻结" data-confirm="确认冻结此用户吗">
											<i class="glyphicon glyphicon-lock"></i>
											<i class="action-param" data-key="status" data-value="0" ></i>
										</button>
									</c:if>
								
									<a class="btn btn-xs btn-success " href="${cur.id}"
										data-toggle="tooltip" title="编辑" data-btn="update"> 
										<i class="glyphicon glyphicon-edit"></i>
									</a>
									
									<button class="btn btn-xs btn-info action-get"
										data-btn="delete" data-href="${cur.id }/role" data-btn="role"
										data-toggle="tooltip" title="赋权" data-confirm="确认前往分配角色吗">
										<i class="glyphicon glyphicon-cog"></i>
									</button>

									<button class="btn btn-xs btn-danger action-get"
										data-btn="delete" data-href="${cur.id }/remove"
										data-toggle="tooltip" title="删除" data-confirm="确认删除本条记录吗">
										<i class="glyphicon glyphicon-trash"></i>
									</button>
									<a class="btn btn-xs btn-info action-get" data-href="${cur.id}/reset" data-btn="reset"
										data-toggle="tooltip" title="重置密码"  data-confirm="确认重置此用户密码为初始状态吗" data-btn="update">
											 <i class="glyphicon  glyphicon-certificate"></i>
									</a>
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
