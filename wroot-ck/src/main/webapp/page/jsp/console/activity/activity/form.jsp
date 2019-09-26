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

	$(function(){
		$("#type").on("change", function(){
			var $this = $(this);
			var $red = $("#redpacketRule");
			var $charge =  $("#phoneChargeRule");
			var $recomment =  $("#recommentRule");
			switch ($this.val()){
				case "1": //抢红包
					$red.prop("disabled", false).closest("div.form-group").show();
					$charge.prop("disabled", true).closest("div.form-group").hide();
					$recomment.prop("disabled", true).closest("div.form-group").hide();
					break;
				case "2": //抢话费
					$red.prop("disabled", true).closest("div.form-group").hide();
					$charge.prop("disabled", false).closest("div.form-group").show();
					$recomment.prop("disabled", true).closest("div.form-group").hide();
					break;
				case "3"://推荐商家有奖
					$red.prop("disabled", true).closest("div.form-group").hide();
					$charge.prop("disabled", true).closest("div.form-group").hide();
					$recomment.prop("disabled", false).closest("div.form-group").show();
					break;
					
			}
				
		}).trigger("change");
		
		
	});
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
										
		<div class="form-group">
			<label for="type" class="col-sm-2 control-label">活动类型*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="type" name="type" data-value="${entity.type }" data-rule-required="true" >
					<option value="1">--抢红包--</option>
					<option value="2">--抢话费--</option>
					<option value="3">--推荐商家有奖--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="redpacketRule" class="col-sm-2 control-label"> 红包总流水占比*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="redpacketRule" name="redpacketRule" value="${ entity.redpacketRule }" 
					data-rule-digits="true" data-rule-max="10"
					placeholder="请输入为红包类型时：设置的红包总总流水占比 ，如 5-->5%" />
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="recommentRule" class="col-sm-2 control-label"> 推荐商家奖励金额*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="recommentRule" name="recommentRule" value="${ entity.recommentRule }" 
						data-rule-number="true" data-rule-required="true" 
						placeholder="推荐商家奖励金额(元)" />
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">话费规则*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="phoneChargeRule" name="phoneChargeRule" value="${ entity.phoneChargeRule }" 
						placeholder="请输入话费规则，总共分5级，邀请人数对应金额如 3:50,6:100,10:150,20:200,40:300"
						data-rule-required="true"  data-rule-reg="^\d+[:]\d+[,]\d+[:]\d+[,]\d+[:]\d+[,]\d+[:]\d+[,]\d+[:]\d+$" 
						${not empty entity.id ? 'readonly':'' }
						/>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">标题*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="title" name="title" value="${ entity.title }"  data-rule-required="true" 
						placeholder="请输入标题">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">图标*</label>
			<div class="col-sm-6 ">
				<input type="hidden" name="icon" value="${entity.icon}" data-rule-required="true" /> 
				<input class="fileupload" id="icon" type="file" data-display-id="icon"	data-preview="true" data-rule-accept="image/*" />	
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
	            
	    <div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">开始时间*</label>
			<div class="col-sm-6 ">
				   <div class="input-group">
						<input  class="datetime"  id="startDate" name="startDate" type="text" value="<fmt:formatDate value="${entity.startDate}" pattern="yyyy-MM-dd"/>" data-date-format="yyyy-mm-dd"  data-min-view="2"      
							data-end-date-target="endDate"
						readonly>
					</div>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>        
	            
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">结束时间*</label>
			<div class="col-sm-6 ">
				<input  class="datetime"  id="endDate" name="endDate" type="text"value="<fmt:formatDate value="${entity.endDate}" pattern="yyyy-MM-dd"/>" data-date-format="yyyy-mm-dd"  data-min-view="2"    
					data-start-date-target="startDate"	
					readonly  /> 
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
			<label for="parentId" class="col-sm-2 control-label">活动内容*</label>
			<div class="col-sm-6 ">
				<script class="u-editor" id="content"  name="content"  type="text/plain">
        				${entity.content}
   				</script>		
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
