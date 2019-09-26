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
		$("button.editor").on("click", function(){
			var $editor = $(this);
			$editor.closest("td").siblings("td").children(":input").prop("disabled", false).focus();
			
			$editor.toggleClass("hidden", true);
			$editor.siblings("button.confirm").toggleClass("hidden", false);
		});
		
		$("button.confirm").on("click", function(){
			var $confirm = $(this);
			var $input = $confirm.closest("td").siblings("td").children(":input");
			var value = $input.val();
			var code = $input.data("code");
			if(!value) {
				$common.tipDialog("提示", "请填写设置值",function(){$input.focus()});
				return false;
			}
			
			$.ajax("async",{
				dataType:"json",
				type:"post",
				async: false,
				data:{code:code, content: value}
			}).done(function(data){
				//暂不多加判断
				$common.tipDialog("提示", "操作成功");
			}).fail(function(jqXHR, textStatus){
				$common.tipDialog("提示", "操作失败");
			});
			
			$input.prop("disabled", true);
			$confirm.toggleClass("hidden", true);
			$confirm.siblings("button.editor").toggleClass("hidden");
		});
	});

	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<div class="row row-nocol">

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
						<th>编码</th>
						<th>项目</th>
						<th>说明</th>
						<th>配置值</th>
						<th class="text-info">操作</th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach items="${datas}" var="cur" varStatus="status">
						<tr>
							<td>${ cur.code }</td>
							<td>${ cur.name }</span></td>
							<td title="${cur.remark }">${fn:substring(cur.remark, 0, 20)}${fn:length(cur.remark) gt 20 ? "..." : ""}</td>
							<td>
								<c:choose>
									<c:when test="${cur.type!=3 }">
										<input data-code="${ cur.code}" value="${cur.content }" disabled="true" />
									</c:when>
									<c:otherwise>
										${cur.content }
									</c:otherwise>
								</c:choose>
							</td>

							<td>
								<div class="btn-group-dis">
									
									<c:if test="${cur.type!=3 }">
										<button type="button" class="btn btn-xs btn-info editor show" data-toggle="tooltip" title="编辑"> 
											<i class="glyphicon glyphicon-edit"></i>编辑
										</button>
										
										<button type="button"  class="btn btn-xs btn-success confirm hidden" data-toggle="tooltip" title="确认" >
											<i class="glyphicon glyphicon-ok"></i>确认
										</button>
									</c:if>
									
									<c:if test="${cur.type==3 }">
											<a class="btn btn-xs btn-primary " href="${ cur.code}/update"
											data-toggle="tooltip" title="编辑"> 
											<i class="glyphicon glyphicon-edit"></i>详情
										</a>
									</c:if>

									
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
