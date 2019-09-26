<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
<%@include file="/page/jspf/head.jspf"%>
<script type="text/javascript">
	$(function(){
		$("#check").on("click", function(){
    		var $radio = $("input:radio:checked");
    		console.info("$radio :" + $radio);
    		var id = $radio.val();
    		if(!id || isNaN(id)){
    			$common.tipDialog("提示","请先选择商家");
    			return false;
    		}
    		var name = $($radio).closest("td").siblings(".mine_name").text();
    		console.info("id-"+id+",name-"+name);
    		window.opener.callback(id, name);//调用父类方法传值
    		window.opener = null;
    		window.close();
    	});
	});
	
</script>
</head>
<body>
	<div class="row row-nocol">
		<form class="form-inline lookup" role="form" method="post">
			<div class="form-group">
				<label class="sr-only" for="lookup-username">商家名称</label> 
				<input type="text" class="form-control" id="lookup-name" name="name" value="${ pager.lookup.name }" placeholder="请输入商家名称">
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
			
			<div class="form-group hidden">
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
			
			
			<div class="form-group hidden" >
				<label class="sr-only" for="lookup-status">审核状态</label> 
				<input type="text" class="form-control" id="lookup-status" name="status" value="${ pager.lookup.status }" placeholder="审核状态">
			</div>
			
			<div class="form-group hidden" >
				<label class="sr-only" for="lookup-cityId">城市id</label> 
				<input type="text" class="form-control" id="lookup-cityId" name="cityId" value="${ pager.lookup.cityId }" placeholder="城市id">
			</div>
			
			
			

			<div class="form-group form-btn-bar">
				<button type="submit" class="btn btn-primary btn-sm">
					<i class="glyphicon glyphicon-search"></i> 查询
				</button>
				<!-- <button type="button" class="btn btn-info btn-sm reset">
					<i class="glyphicon glyphicon-refresh"></i> 重置
				</button> -->
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
				 <button class="btn btn-sm btn-success" type="button" id="check"><i class="glyphicon glyphicon-check"></i> 确定选择</button>
			</div>

			<table class="table table-striped table-bordered table-hover ">
				<thead>
					<tr class="text-center">
						<th class="center" style="width:1.5em;"></th>
                        <th style="width:4em;">序号</th>
						<th>商户名称</th>
						<th>电话</th>
						<th>城市</th>
						<th>分类</th>
						<th>价格</th>
						<th>是否精选</th>
						<th>创建时间</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${ pager.datas}" var="cur" varStatus="status">
						<tr>
							<td class="center"><input type="radio" name="pk" value="${cur.id}"/></td>
                            <td>${status.count}</td>
							<td class="mine_name">${ cur.name }</td>
							<td>${ cur.mobile }</td>
							<td>${ cur.cityName }</td>
							<td>${ cur.categoryName }</td>
							<td>${ cur.price }</td>
							<td><span data-random-label="${cur.wellChosened}">${cur.wellChosened ==1?'是':'否' }</span></td>
							<td><fmt:formatDate value="${cur.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
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
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<%@include file="/page/jspf/console-pager.jspf"%>
</body>
</html>
