<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="application.title" /></title>
    <%@include file="/page/jspf/head.jspf"%>
    <!-- 上传js需要引入的 -->
    <script type="text/javascript" src="${ctx }/static/js/jquery.fileupload.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/jquery.iframe-transport.js"></script>

</head>
<body>
<%@include file="/page/jspf/console-body-first.jspf"%>
<form class="form-horizontal validate" role="form" method="post">

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">提示音*</label>
        <div class="col-sm-6 ">
            <input type="hidden" name="prompt_attachId" value="${config.prompt_attachId}" data-rule-required="true" />
            <input class="fileupload" id="prompt_attachId" type="file" data-display-id="prompt_attachId"	data-preview="true" data-rule-accept="audio/*" />

        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>


    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">提示音类型*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="prompt_type" name="prompt_type" data-value="${config.prompt_type }" data-rule-required="true">
                <option value="1">--用户端语音--</option>
                <option value="2">--商家端语音--</option>
                <option value="3">--骑手端语音--</option>
            </select>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>


    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">提示音名称*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="prompt_name" name="prompt_name"	value="${ config.prompt_name }" data-rule-required="true"
                   placeholder="请输入提示音名称">
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
