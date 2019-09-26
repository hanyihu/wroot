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
        <label for="parentId" class="col-sm-2 control-label">商品名称：</label>
        <div class="col-sm-6 ">
            <label class="form-control-static">${evaluation.commodity_name }</label>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">评价人姓名：</label>
        <div class="col-sm-6 ">
            <label class="form-control-static">${evaluation.customer_name }</label>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">评价内容：</label>
        <div class="col-sm-6 ">
            <label class="form-control-static">${evaluation.content }</label>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">评价时间：</label>
        <div class="col-sm-6 ">
            <label class="form-control-static">${evaluation.evaluationTimeDesc }</label>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">是否包含违禁词*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="is_illegal_word" name="is_illegal_word"	data-value="${ evaluation.is_illegal_word }">
                <option value="">--是否包含违禁词--</option>
                <option value="0">--否--</option>
                <option value="1">--是--</option>
            </select>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">处理状态*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="status" name="status"	data-value="${ evaluation.status }">
                <option value="">--请选择处理状态--</option>
                <option value="2">--已处理--</option>
                <option value="3">--不处理--</option>
            </select>
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

