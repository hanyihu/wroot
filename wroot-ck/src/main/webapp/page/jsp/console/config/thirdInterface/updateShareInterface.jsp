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
            <a href="https://open.weixin.qq.com/">微信开放平台</a>&nbsp;&nbsp;
            <a href="http://wiki.open.qq.com/">腾讯开放平台</a>&nbsp;&nbsp;
            <a href="https://open.weibo.com/sharebutton">微博开放平台</a>&nbsp;&nbsp;
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>
    <c:if test="${thirdInterface.id==14 || thirdInterface.id==13}">
        <div class="form-group">
            <label for="parentId" class="col-sm-2 control-label">AppID*</label>
            <div class="col-sm-6 ">
                <input type="text" class="form-control" id="app_id" name="app_id" value="${ thirdInterface.app_id }"
                       data-rule-required="true"
                       data-rule-maxlength="500"placeholder="请输入AppID">
            </div>
            <div class="col-sm-4  help-inline form-control-static"></div>
        </div>
    </c:if>
    <c:if test="${thirdInterface.id==14 || thirdInterface.id==15}">
        <div class="form-group">
            <label for="parentId" class="col-sm-2 control-label">AppKey*</label>
            <div class="col-sm-6 ">
                <input type="text" class="form-control" id="app_key" name="app_key" value="${ thirdInterface.app_key }"
                       data-rule-required="true"
                       data-rule-maxlength="500"placeholder="请输入AppKey">
            </div>
            <div class="col-sm-4  help-inline form-control-static"></div>
        </div>
    </c:if>

    <c:if test="${thirdInterface.id==13}">
        <div class="form-group">
            <label for="parentId" class="col-sm-2 control-label">AppSecret*</label>
            <div class="col-sm-6 ">
                <input type="text" class="form-control" id="app_secret" name="app_secret" value="${ thirdInterface.app_secret }"
                       data-rule-required="true"
                       data-rule-maxlength="500"placeholder="请输入AppSecret">
            </div>
            <div class="col-sm-4  help-inline form-control-static"></div>
        </div>
    </c:if>



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

