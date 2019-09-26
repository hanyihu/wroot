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
            <select class="form-control" id="integral_open" name="integral_open"	data-value="${ management.integral_open }">
                <option value="0">--否--</option>
                <option value="1">--是--</option>
            </select>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">积分规则*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="integral_x" name="integral_x" value="${ management.integral_x }"
                   data-rule-required="true"
                   data-rule-maxlength="11">
            <c:if test="${ management.integral_type==1 || management.integral_type==3 || management.integral_type==4 }">规则：X积分/元</c:if>
            <c:if test="${ management.integral_type==2 }">规则：X积分/无图评论</c:if>
            <c:if test="${ management.integral_type==5 }">规则：X积分/天/转发(一天奖励一次/人)</c:if>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <c:if test="${ management.integral_type==2 || management.integral_type==4 }">
        <div class="form-group">
            <label for="parentId" class="col-sm-2 control-label"></label>
            <div class="col-sm-6 ">
                <input type="text" class="form-control" id="integral_y" name="integral_y" value="${ management.integral_y }"
                       data-rule-required="true"
                       data-rule-maxlength="11">
                <c:if test="${ management.integral_type==2 }">规则：Y积分/有图评论</c:if>
                <c:if test="${ management.integral_type==4 }">规则：累计Y天</c:if>
            </div>
            <div class="col-sm-4  help-inline form-control-static"></div>
        </div>
    </c:if>

    <c:if test="${ management.integral_type==4 }">
        <div class="form-group">
            <label for="parentId" class="col-sm-2 control-label"></label>
            <div class="col-sm-6 ">
                <input type="text" class="form-control" id="integral_z" name="integral_z" value="${ management.integral_z }"
                       data-rule-required="true"
                       data-rule-maxlength="11">
                规则：一次性奖励Z积分
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

