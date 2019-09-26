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
            <select class="form-control" id="fetch_open" name="fetch_open"	data-value="${ config.fetch_open }">
                <option value="0">--否--</option>
                <option value="1">--是--</option>
            </select>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="row">
        <label for="parentId" class="col-sm-2 control-label">提现时间*</label>
        <div class="col-lg-3 ">
            <select id="fetch_startTime" name="fetch_startTime"	data-value="${ config.fetch_startTime }">
                <option value="01">01</option>
                <option value="02">02</option>
                <option value="03">03</option>
                <option value="04">04</option>
                <option value="05">05</option>
                <option value="06">06</option>
                <option value="07">07</option>
                <option value="08">08</option>
                <option value="09">09</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
            </select>
            <span>:00-</span>
            <select id="fetch_endTime" name="fetch_endTime"	data-value="${ config.fetch_endTime }">
                <option value="01">01</option>
                <option value="02">02</option>
                <option value="03">03</option>
                <option value="04">04</option>
                <option value="05">05</option>
                <option value="06">06</option>
                <option value="07">07</option>
                <option value="08">08</option>
                <option value="09">09</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
            </select>
            <span>:00</span>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">提现规则*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="fetch_rule" name="fetch_rule" value="${ config.fetch_rule }"
                   data-rule-required="true"
                   data-rule-maxlength="11">
            <c:if test="${ config.fetch_type==1 }">规则：X天前的营业额可提现</c:if>
            <c:if test="${ config.fetch_type==2 }">规则：订单结束后X小时可提现</c:if>
            <c:if test="${ config.fetch_type==3 }">规则：最低X元可提现</c:if>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">提现方式*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="fetch_way" name="fetch_way"	data-value="${ config.fetch_way }">
                <option value="1">--微信--</option>
                <option value="2">--支付宝--</option>
                <option value="3">--银行卡--</option>
            </select>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="row">
        <label for="parentId" class="col-sm-2 control-label">手续费*</label>
        <div class="col-lg-3 ">
            <span><input type="text" id="fetch_poundage" name="fetch_poundage" value="${ config.fetch_poundage }"
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

