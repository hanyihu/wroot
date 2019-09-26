<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="application.title" /></title>
    <%@include file="/page/jspf/head.jspf"%>
</head>
<body>
<%@include file="/page/jspf/console-body-first.jspf"%>
<form class="form-horizontal validate" role="form" method="post">

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">系统名称*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="name" name="name" value="${ config.name }"
                   data-rule-required="true"
                   data-rule-maxlength="16"placeholder="请输入系统名称（16字以内）">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">ICP备案*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="icp_record" name="icp_record" value="${ config.icp_record }"
                   data-rule-required="true"
                   data-rule-maxlength="100"placeholder="请输入ICP备案（100字以内）">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">网站关键字*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="web_keyword" name="web_keyword" value="${ config.web_keyword }"
                   data-rule-required="true"
                   data-rule-maxlength="100"placeholder="请输入网站关键字（100字以内）">
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
