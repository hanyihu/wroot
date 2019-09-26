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
        <label for="parentId" class="col-sm-2 control-label">是否开启*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="commission_open" name="commission_open"	data-value="${ config.commission_open }">
                <option value="0">--否--</option>
                <option value="1">--是--</option>
            </select>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="row">
        <label for="parentId" class="col-sm-2 control-label">佣金数目*</label>
        <div class="col-lg-4 ">
            <span><c:if test="${config.commission_type==1}">消费金额的</c:if>
                <c:if test="${config.commission_type==2}">推荐商家营业额的</c:if>
                <input type="text" id="commission_amount" name="commission_amount" value="${ config.commission_amount }"
                         data-rule-required="true"
                         data-rule-maxlength="4">%</span>
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

