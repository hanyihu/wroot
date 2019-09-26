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
				<label class="sr-only" for="lookup-username">商家名称</label> <input
					type="text" class="form-control" id="lookup-name" name="name"
					value="${ pager.lookup.name }" placeholder="请输入商家名称">
			</div>


			<div class="form-group">
				<label class="sr-only" for="lookup-status">审核状态</label> <select
					class="form-control" id="lookup-status" name="status"
					data-value="${ pager.lookup.status }">
					<option value="">--审核状态--</option>
					<option value="1">--待审核--</option>
					<option value="2">--通过--</option>
					<option value="3">--不通过--</option>
				</select>
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-type">商家类型</label> <select
					class="form-control" id="lookup-type" name="type"
					data-value="${ pager.lookup.type }">
					<option value="">--商家类型--</option>
					<option value="1">--美食--</option>
					<option value="2">--酒店--</option>
					<option value="3">--其他--</option>
				</select>
			</div>
			
			<div class="form-group">
				<label class="sr-only" for="lookup-categoryId">商家分类</label> <select
					class="form-control" id="lookup-categoryId" name="categoryId"
					data-value="${ pager.lookup.categoryId }">
					<option value="">--请选择商家分类--</option>
					<c:forEach items="${categoryList }" var="item">
						<option value="${item.id }">-${item.name }--</option>
					
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label class="sr-only" for="lookup-wellChosened">是否镗镗锣精选</label> <select
					class="form-control" id="lookup-wellChosened" name="wellChosened"
					data-value="${ pager.lookup.wellChosened }">
					<option value="">-是否镗镗锣精选-</option>
					<option value="1">--是--</option>
					<option value="0">--否--</option>
				</select>
			</div>
			<!-- 权限为全国的则显示选择城市 -->
			<c:if test="${empty principal.cityId}">
				<div class="form-group">
					<label class="sr-only" for="lookup-provinceId">请选择省</label> 
					<select	class="form-control" id="lookup-provinceId" name="provinceId" data-value="${ pager.lookup.provinceId }" 
						data-linkage="true" data-linkage-target="#lookup-cityId" data-linkage-url="${ctx }/console/system/area/children">
						<option value="">-请选择省-</option>
						<c:forEach items="${provinceList }" var="item">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label class="sr-only" for="lookup-cityId">请选择城市</label> 
					<select	class="form-control" id="lookup-cityId" name="cityId" data-value="${ pager.lookup.cityId }"
						
						data-linkage="true" data-linkage-target="#lookup-areaId" data-linkage-url="${ctx }/console/system/area/children">
						<option value="">--请选择市--</option>
					</select>
				</div>
				<div class="form-group">
					<label class="sr-only" for="lookup-areaId">请选择区域</label> 
					<select	class="form-control" id="lookup-areaId" name="areaId" data-value="${ pager.lookup.areaId }">
						<option value="">--请选择区--</option>
					</select>
				</div>
			</c:if>
			

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
				<a class="btn btn-sm btn-success" href="0/state"> <i
						class="glyphicon glyphicon-trash"></i> 商家回收站
				</a>
			</div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th><input type="checkbox" data-member="ids"></th>
						<th>所属用户</th>
						<th>商户名称</th>
						<th>电话</th>
						<th>地区</th>
						<th>类型</th>
						<th>分类</th>
						<th>外景照</th>
						<th>评价</th>
						<th>价格</th>
						<th>是否精选</th>
						<th>精选排序</th>
						<th>二维码</th>
						<th>审核状态</th>
						<th>创建时间</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
							<td>${ cur.customerName }</td>
							<td>${ cur.name }</td>
							<td>${ cur.mobile }</td>
							<td>${ cur.areaName }</td>
							<td><span data-random-label="${ cur.type }">${ cur.typeDesc }</span></td>
							<td>${ cur.categoryName }</td>
							<td><img  data-attachment="${ cur.outPic }" class="pop"/></td>
							<td>${ cur.star }</td>
							<td>${ cur.price }</td>
							<td><span data-random-label="${cur.wellChosened}">${cur.wellChosened ==1?'是':'否' }</span></td>
							<td>${ cur.wellChosenSort }</td>
							<td><img  data-attachment="${ cur.qrcode }" class="amplifier"/></td>
							<td><span data-random-label="${ cur.status }">${ cur.statusDesc }</span></td>
							<td><fmt:formatDate value="${cur.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>

							<td>
								<div class="btn-group-dis">
								
									<a class="btn btn-xs btn-success " href="${ cur.id}"
										data-toggle="tooltip" title="编辑" > 
										<i class="glyphicon glyphicon-edit"></i>
									</a>

									<c:if test="${cur.status eq 1  }">
										<button class="btn btn-xs btn-info action-get" type="button"										
											data-href="${cur.id }/status"  
											data-toggle="tooltip" title="通过审核" data-confirm="确认通过审核吗">
											<i class="glyphicon glyphicon-ok-circle"></i>
											<i class="action-param" data-key="status" data-value="2" ></i>
										</button>
										<button class="btn btn-xs btn-warning action-get" type="button"										
											data-href="${cur.id }/status"  
											data-toggle="tooltip" title="审核不通过" data-confirm="确认不通过审核吗">
											<i class="glyphicon glyphicon-ban-circle"></i>
											<i class="action-param" data-key="status" data-value="3" ></i>
										</button>
									</c:if>
									
									<c:if test="${cur.status eq 2  }">
										<button class="btn btn-xs btn-warning action-get" type="button"										
											data-href="${cur.id }/status"  
											data-toggle="tooltip" title="审核不通过" data-confirm="确认不通过审核吗">
											<i class="glyphicon glyphicon-ban-circle"></i>
											<i class="action-param" data-key="status" data-value="3" ></i>
										</button>
									</c:if>
									<c:if test="${cur.status eq 3  }">
										<button class="btn btn-xs btn-info action-get" type="button"										
											data-href="${cur.id }/status"  
											data-toggle="tooltip" title="通过审核" data-confirm="确认通过审核吗">
											<i class="glyphicon glyphicon-ok-circle"></i>
											<i class="action-param" data-key="status" data-value="2" ></i>
										</button>
									</c:if>


									<a class="btn btn-xs btn-success " href="${ cur.id}/detail" data-toggle="tooltip" title="详情" > 
										<i	class="glyphicon glyphicon-list-alt"></i>
									</a>
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
