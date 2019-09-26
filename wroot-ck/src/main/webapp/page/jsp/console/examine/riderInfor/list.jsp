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
				<label class="sr-only" for="lookup-status">审核状态</label>
				<select	class="form-control" id="lookup-status" name="status"data-value="${pager.lookup.status}">

					<option value="">--审核状态--</option>
					<option value="0">--未审核--</option>
					<option value="1">--待审核--</option>
					<option value="2">--审核成功--</option>
					<option value="3">--审核失败--</option>
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


			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>昵称</th>
						<th>查看健康证11</th>
						<th>健康证号</th>
						<th>健康证有效期</th>
						<th>查看身份证</th>
						<th>审核状态</th>
						<th>审核说明</th>

						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td>${cur.nickName}</td>

							<td>
								<c:if test="${cur.healthCard==null}">
									未上传图片！
								</c:if>
								<c:if test="${cur.healthCard!=null}">
									<a class="btn btn-xs btn-info " href="${ cur.id}/lookHealthImage"
									   data-toggle="tooltip" title="查看健康证">
										<i class="glyphicon glyphicon-eye-open"></i>查看健康证
								  </a>
								</c:if>
							</td>
								<td>${cur.healthCardNo}</td>
							<td>${cur.healthValidity}</td>
							<td>
								<c:if test="${cur.idCard==null}">
									未上传图片！
								</c:if>
								<c:if test="${cur.idCard!=null}">
									<a class="btn btn-xs btn-info " href="${ cur.id}/lookIdCardImage"
									   data-toggle="tooltip" title="查看身份证">
										<i class="glyphicon glyphicon-eye-open"></i>查看身份证
									</a>
								</c:if>
							</td>

							<td><span
									data-random-label="${cur.status}">${cur.status==3?'审核失败':cur.status==1?'审核中':cur.status==2?'审核成功':'未审核' }</span>
							</td>
							<td>${ cur.examineRemark }</td>

							<td>
								<div class="btn-group btn-group-dis">


									<a class="btn btn-xs btn-success " href="${ cur.id}/passExamine"
									   data-toggle="tooltip" title="通过审核" >
										<i class="glyphicon glyphicon-ok"></i>通过审核
									</a>

									<a class="btn btn-xs btn-danger " href="${ cur.id}/remark"
									   data-toggle="tooltip" title="不通过审核" >
										<i class="glyphicon glyphicon-remove"></i>不通过审核
									</a>

									
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
