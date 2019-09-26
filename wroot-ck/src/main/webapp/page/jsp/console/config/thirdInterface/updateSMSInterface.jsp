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
            <a href="https://www.aliyun.com/product/sms">阿里短信</a>&nbsp;&nbsp;
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">AccessKeyId*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="access_key_id" name="access_key_id" value="${ thirdInterface.access_key_id }"
                   data-rule-required="true"
                   data-rule-maxlength="500"placeholder="请输入AccessKeyId">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">AccessKeySecret*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="access_key_secret" name="access_key_secret" value="${ thirdInterface.access_key_secret }"
                   data-rule-required="true"
                   data-rule-maxlength="500"placeholder="请输入AccessKeySecret">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">SignName*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="sign_name" name="sign_name" value="${ thirdInterface.sign_name }"
                   data-rule-required="true"
                   data-rule-maxlength="500"placeholder="请输入SignName">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">TemplateCode*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="template_code" name="template_code" value="${ thirdInterface.template_code }"
                   data-rule-required="true"
                   data-rule-maxlength="500"placeholder="请输入TemplateCode">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">TemplateParam*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="template_param" name="template_param" value="${ thirdInterface.template_param }"
                   data-rule-required="true"
                   data-rule-maxlength="500"placeholder="请输入TemplateParam">
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

