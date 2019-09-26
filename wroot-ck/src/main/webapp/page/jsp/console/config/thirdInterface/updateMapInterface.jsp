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
            <a href="http://lbsyun.baidu.com/">百度地图开放平台</a>&nbsp;&nbsp;
            <a href="https://lbs.amap.com/">高德地图开放平台</a>&nbsp;&nbsp;
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">第三方接口id*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="interface_id" name="interface_id" value="${ thirdInterface.interface_id }"
                   data-rule-required="true"
                   data-rule-maxlength="255"placeholder="请输入第三方接口id">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">第三方接口公钥*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="public_key" name="public_key" value="${ thirdInterface.public_key }"
                   data-rule-required="true"
                   data-rule-maxlength="500"placeholder="请输入第三方接口公钥">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">第三方接口私钥*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="private_key" name="private_key" value="${ thirdInterface.private_key }"
                   data-rule-required="true"
                   data-rule-maxlength="500"placeholder="请输入第三方接口私钥">
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

