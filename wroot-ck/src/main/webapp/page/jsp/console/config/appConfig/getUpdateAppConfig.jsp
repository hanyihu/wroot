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
        <label for="parentId" class="col-sm-2 control-label">微信公众平台</label>
        <div class="col-sm-6 ">
            <a href="https://mp.weixin.qq.com/wxopen/waregister?action=step1">微信公众平台</a>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">小程序名称*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="app_name" name="app_name" value="${ config.app_name }"
                   data-rule-required="true"
                   data-rule-maxlength="16"placeholder="请输入小程序名称">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">AppId（小程序ID）*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="app_appid" name="app_appid" value="${ config.app_appid }"
                   data-rule-required="true"
                   data-rule-maxlength="255"placeholder="请输入AppId">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">AppSecret（小程序密钥）*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="app_appsecret" name="app_appsecret" value="${ config.app_appsecret }"
                   data-rule-required="true"
                   data-rule-maxlength="255"placeholder="请输入小程序密钥">
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

