<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>后台</title>
<style type="text/css">
.bar {
    height: 18px;
    background: green;
}
</style>
<%@include file="/page/jspf/head.jspf"%>
<link rel="stylesheet" href="${ctx }/static/css/dropzone.css" />
<script>
$(function() {
	
	
});
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
		<div class="form-group">
                <label class="col-sm-2 control-label" for="uploadUrlString">number：</label>
                <div class="col-sm-6">
                    <input type="text"  data-preview="true" data-display-id="downloadUrl" data-rule-number="true"/>
                </div>
                <label class="col-sm-4 help-inline form-control-static" for="uploadUrlString"></label>
            </div>  

              <label class="col-sm-2 control-label" for="icon">fileupload</label>
              <div class="col-sm-6">
                  <input type="hidden" name="icon" value="${entity.icon}"	data-rule-required="true" /> 
                  <input class="fileupload" id="shop-icon" type="file" data-display-id="icon"	data-preview="true" data-rule-accept="image/*" />
              </div>
              <label class="col-sm-4 help-inline form-control-static"
                     for="icon"></label>
          </div>


		<div class="form-group clearfix">
			<div class="col-sm-offset-3 col-sm-9">
			<!-- 	<button type="submit" class="btn btn-primary">
					<i class="glyphicon glyphicon-ok"></i> 提交
				</button> -->
				<button type="button" class="btn btn-info action-back">
					<i class="glyphicon glyphicon-repeat"></i> 返回
				</button>
			</div>
		</div>

	</form>
	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
