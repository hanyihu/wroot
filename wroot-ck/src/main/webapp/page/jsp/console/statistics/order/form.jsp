<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="application.title" /></title>
    <%@include file="/page/jspf/head.jspf"%>
    <script type="text/javascript">

    </script>
</head>
<body>
<%@include file="/page/jspf/console-body-first.jspf"%>
<div class="row row-nocol">
    <form class="form-inline lookup" role="form" method="get">

        <div class="form-group">
            <label class="sr-only" for="date">订单时间</label>
            <input  class="datetime form-control"  id="date" name="date" type="text" value="" data-date-format="yyyy-mm-dd"  data-min-view="2"
                    data-end-date-target="lookup-endDate"	readonly placeholder="开始日期"  />
        </div>
        <div class="form-group form-btn-bar">
            <button type="submit" class="btn btn-primary btn-sm">
                <i class="glyphicon glyphicon-search"></i> 查询
            </button>
            <button type="button" class="btn btn-info btn-sm reset">
                <i class="glyphicon glyphicon-refresh"></i> 重置
            </button>
        </div>

    </form>
    <hr />
    <!-- 操作信息提示的地方 -->
    <c:if test="${ not empty remind}">
        <div class="alert alert-${ remind.level}">
            <button data-dismiss="alert" class="close" type="button">
                <i class="glyphicon glyphicon-remove"></i>
            </button>
                ${ remind.message }
        </div>
    </c:if>
    <!--列表栏-->
    <form class="nocol">
        <div class="action-bar"></div>

        <table class="table table-striped table-bordered table-hover ">
            <thead>
            <tr class="text-center">
                <th><input type="checkbox" data-member="ids"></th>
                <th>下单会员数</th>
                <th>人均单价</th>
                <th>订单数</th>
                <th>交易额</th>
                <th>有效订单数</th>
                <th>有效订单额</th>
                <th>商家名称</th>
                <th>商家排名</th>
            </tr>
            </thead>
            <tbody align="center">


            <c:forEach items="${ businessData}" var="cur" varStatus="status">
                <tr>
                    <td class="center"><input type="checkbox"value="${cur.id}" name="ids"></td>
                    <td>${cur.totalNumber}</td>
                    <td>${cur.capitaPrice}</td>
                    <td>${cur.orderNumber}</td>
                    <td>${cur.totalAmount}</td>
                    <td>${cur.orderSuccessNumber}</td>
                    <td>${cur.orderSuccessAmount}</td>
                    <td>${cur.merchantsName}</td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
<%--<%@include file="/page/jspf/console-pager.jspf"%>--%>
<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
