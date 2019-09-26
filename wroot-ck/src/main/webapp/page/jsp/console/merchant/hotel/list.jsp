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
				<label class="sr-only" for="lookup-name">名称</label> 
				<input	type="text" class="form-control" id="lookup-name" name="name" value="${ pager.lookup.name }" placeholder="名称">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-merchantName">所属商户</label> 
				<input	type="text" class="form-control" id="lookup-merchantName" name="merchantName" value="${ pager.lookup.merchantName }" placeholder="所属商户">
			</div>

			<div class="form-group">
				<label class="sr-only" for="lookup-merchantMobile">商户电话</label> 
				<input	type="text" class="form-control" id="lookup-merchantMobile" name="mobile" value="${ pager.lookup.mobile }" placeholder="商户电话">
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-mobile">状态</label> 
				<select	class="form-control" id="lookup-status" name="status"	data-value="${ pager.lookup.status }">
					<option value="">--请选择状态--</option>
					<option value="0">--初始--</option>
					<option value="1">--上架--</option>
					<option value="2">--下架--</option>
				</select>
			</div>
			
			
			<div class="form-group">
				<label class="sr-only" for="lookup-isDelete">是否删除</label> 
				<select	class="form-control" id="lookup-isDelete" name="isDelete"	data-value="${ pager.lookup.isDelete }">
					<option value="">--是否删除--</option>
					<option value="0">--否--</option>
					<option value="1">--是--</option>
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
				<!-- <a class="btn btn-sm btn-success" href="0"> 
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
						<th>所属商户</th>
						<th>商户电话</th>
						<th>名称</th>
						<th>图标</th>
						<th>价格</th>
						<th>库存数</th>
						<th>可住人数</th>
						<th>楼层</th>
						<th>面积</th>
						<th>床型</th>
						<th>早餐</th>
						<th>是否删除</th>
						<th>创建时间</th>
						<th>状态</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td>${ cur.merchantName }</td>
							<td>${ cur.merchantMobile }</td>
							<td>${ cur.name }</td>
							<td><img data-attachment="${ cur.icon }" class="pop"/></td>
							<td>${ cur.price }</td>
							<td>${ cur.stock }</td>
							<td>${ cur.roomPersonNum }</td>
							<td>${ cur.roomFloor }</td>
							<td>${ cur.roomArea }</td>
							<td><span data-random-label="${ cur.roomBed }">${cur.roomBed ==4?'三人床':(cur.roomBed==3?'双人床':(cur.roomBed==2?'豪华大床':'单人床')) }</td>
							<td><span data-random-label="${ cur.roomBreakfast }">${cur.roomBreakfast ==3?'双份早餐':(cur.roomBreakfast==2?'含早餐床':'无早餐') }</td>
							<td><span data-random-label="${ cur.isDelete }">${cur.isDelete==1?'是':'否' }</td>
							<td><fmt:formatDate value="${cur.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td><span data-random-label="${ cur.status }">${cur.status ==0?'初始':(cur.status==1?'上架':'下架') }</td>

							<td>
								<div class="btn-group-dis">
								
									
									
									<c:if test="${cur.status eq 0  || cur.status eq 1 }">
											<button class="btn btn-xs btn-warning action-get" type="button"										
												data-href="${cur.id }/status"  
												data-toggle="tooltip" title="下架" data-confirm="确认下架吗">
												<i class="glyphicon glyphicon-arrow-down"></i>
												<i class="action-param" data-key="status" data-value="2" ></i>
											</button>
									</c:if>
									
									<c:if test="${cur.status eq 0  || cur.status eq 2  }">
										<button class="btn btn-xs btn-info action-get" type="button"										
											data-href="${cur.id }/status"  
											data-toggle="tooltip" title="上架" data-confirm="确认上架吗">
											<i class="glyphicon glyphicon-arrow-up"></i>
											<i class="action-param" data-key="status" data-value="1" ></i>
										</button>
									</c:if>
									
									<a class="btn btn-xs btn-success " href="${ cur.id}/detail" data-toggle="tooltip" title="详情" > 
										<i	class="glyphicon glyphicon-list-alt"></i>
									</a>



								<%-- 	<a class="btn btn-xs btn-success " href="${ cur.id}"
										data-toggle="tooltip" title="编辑" > 
										<i class="glyphicon glyphicon-edit"></i>
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
