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
        <label for="parentId" class="col-sm-2 control-label">接口平台</label>
        <div class="col-sm-6 ">
            <a href="https://www.jiguang.cn/">极光推送</a>&nbsp;&nbsp;
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">AppKey*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="app_key" name="app_key" value="${ thirdInterface.app_key }"
                   data-rule-required="true"
                   data-rule-maxlength="500"placeholder="请输入AppKey">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">AppSecret*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="app_secret" name="app_secret" value="${ thirdInterface.app_secret }"
                   data-rule-required="true"
                   data-rule-maxlength="500"placeholder="请输入AppSecret">
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

