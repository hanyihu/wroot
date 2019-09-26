<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
<%@include file="/page/jspf/head.jspf"%>
	<%@include file="/page/jspf/console-uj.jspf"%>

	<!-- 上传js需要引入的 -->
 <script type="text/javascript" src="${ctx }/static/js/jquery.fileupload.js"></script> 
 <script type="text/javascript" src="${ctx }/static/js/jquery.iframe-transport.js"></script>  
<script type="text/javascript">

function openWin(url, title){
	var style = "width=1200,height=700,location=no,directories=no,toolbar=no,status=no,menubar=no,resizable=no,scrollbars=no,z-look=yes,screenX=300px,screenY=200px";
    window.open(url, title, style);        
}

function callback(merchantId, merchantName){
	$("#merchantId").val(merchantId);
	$("#merchantName").val(merchantName);
}
$(function(){
	
	
	
	//前往选择商家
	 $("#choose-merchant").on("click", function(){
		var url = "${ctx}/console/merchant/merchant/toLookup2/";
		openWin(url, "选择展示的商家");
	}); 
	
	
	$("#urlType").on("change", function(){
		var $this = $(this);
		var $target = $("#target");//跳转到网页
		var $merchantId =  $("#merchantId");//跳转到商家
		switch ($this.val()){
			case "1": //网页
				$target.prop("disabled", false).closest("div.form-group").show();
				$merchantId.prop("disabled", true).closest("div.form-group").hide();
				break;
			case "2": //商家
				$target.prop("disabled", true).closest("div.form-group").hide();
				$merchantId.prop("disabled", false).closest("div.form-group").show();
				break;
				
		} 
	
	}).trigger("change");
	
	
});
	
</script>

</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
<%--

		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">Banner图*</label>
			<div class="col-sm-6 ">
				<input type="hidden" name="image" value="${entity.image}" data-rule-required="true" /> 
				<input class="fileupload" id="image" type="file" data-display-id="image"	data-preview="true" data-rule-accept="image/*" />	
					
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
--%>
	<div class="form-group">
		<label for="parentId" class="col-sm-2 control-label">Banner图*</label>
		<div class="col-sm-6 ">
			<input type="hidden" name="image"  value="${entity.image}" data-rule-required="true" />
			<input class="fileupload" id="image"  type="file"  multiple data-display-id="image"	data-preview="true" data-rule-accept="image/*" />
		</div>
		<div class="col-sm-4  help-inline form-control-static"></div>
	</div>

		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">Banner类型*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="type" name="type" data-value="${entity.type }" data-rule-required="true">
					<option value="1">--首页--</option>
					<option value="2">--百惠店--</option>
					<option value="3">--社区--</option>
					<option value="4">--积分商城--</option>
					<option value="5">--骑手二维码图--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>


		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">跳转方式*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="urlType" name="urlType" data-value="${entity.urlType }" data-rule-required="true">
					<option value="1">--页面--</option>
					<option value="2">--商家--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>


		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">页面地址*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="target" name="target"	value="${ entity.target }" data-rule-required="true"
					placeholder="请输入跳转目标。跳转方式为页面则为页面url，商家则为商家id">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">
				<a class="btn btn-sm btn-info" id="choose-merchant">选择商家</a><span class="text-danger">*</span>
			</label>
			<div class="col-sm-6 ">
				<input type="text"  class="form-control" id="merchantName" value="${entity.targetDesc}" readonly placeholder="商家" />
				<input type="text"  class="form-control hidden" id="merchantId" name="target" value="${entity.target}"  data-rule-required="true">		
						
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		

		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">所属城市</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="cityId" name="cityId" data-value="${entity.cityId }" >
					<option value="0">--请选择城市--</option>
					<c:forEach items="${cityList }" var="item">
					<option value="${item.id }">${item.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>


		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">是否启用*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="enabled" name="enabled" data-value="${entity.enabled }" data-rule-required="true">
					<option value="0">--禁用--</option>
					<option value="1">--启用--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>


		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">排序*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="sort" name="sort" 	value="${ entity.sort }" 
				data-rule-required="true" data-rule-digits="true"
				placeholder="请输入排序">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>


		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">简单说明*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="title" name="title"	value="${ entity.title }" placeholder="请输入简单说明">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>


		<div class="form-group clearfix">
			<div class="col-sm-offset-3 col-sm-9">
				<button type="submit" class="btn btn-primary">
					<i class="glyphicon glyphicon-ok"></i> 提交
				</button>
				<button type="button" class="btn btn-info action-back">
					<i class="glyphicon glyphicon-repeat"></i> 返回
				</button>
			</div>
		</div>

	</form>
	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
