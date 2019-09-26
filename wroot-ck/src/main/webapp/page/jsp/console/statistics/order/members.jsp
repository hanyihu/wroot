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
                <th>会员名称</th>
                <th>订单数</th>
                <th>订单额</th>
                <th>退款额</th>
                <th>订单均价</th>
            </tr>
            </thead>
            <tbody align="center">


            <c:forEach items="${ businessData}" var="cur" varStatus="status">
                <tr>
                    <td class="center"><input type="checkbox"value="" name="ids"></td>
                    <td>${cur.membersName}</td>
                    <td>${cur.orders}</td>
                    <td>${cur.orderAmount}</td>
                    <td>${cur.refundAmounts}</td>
                    <td>${cur.orderPrice}</td>
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
