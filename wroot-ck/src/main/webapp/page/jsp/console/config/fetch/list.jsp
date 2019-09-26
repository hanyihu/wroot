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
        <table class="table table-striped table-bordered table-hover ">
            <thead>
            <tr class="text-center">
                <th>提现类型</th>
                <th>是否开启</th>
                <th>提现时间</th>
                <th>提现规则</th>
                <th>提现方式</th>
                <th>手续费</th>
                <th class="text-info">操作</th>
            </tr>
            </thead>
            <tbody align="center">
            <c:forEach items="${configs}" var="cur" varStatus="status">
                <tr>
                    <td>
                        <c:if test="${ cur.fetch_type==1 }">商家提现</c:if>
                        <c:if test="${ cur.fetch_type==2 }">骑手提现</c:if>
                        <c:if test="${ cur.fetch_type==3 }">用户提现</c:if>
                    </td>
                    <td>
                        <c:if test="${ cur.fetch_open==0 }">否</c:if>
                        <c:if test="${ cur.fetch_open==1 }">是</c:if>
                    </td>
                    <td>${ cur.fetch_startTime }:00-${ cur.fetch_endTime }:00</td>
                    <td>
                        <c:if test="${ cur.fetch_type==1 }">${ cur.fetch_rule }天前的营业额可提现</c:if>
                        <c:if test="${ cur.fetch_type==2 }">订单结束后${ cur.fetch_rule }小时可提现</c:if>
                        <c:if test="${ cur.fetch_type==3 }">最低${ cur.fetch_rule }元可提现</c:if>
                    </td>
                    <td>
                        <c:if test="${ cur.fetch_way==1 }">微信</c:if>
                        <c:if test="${ cur.fetch_way==2 }">支付宝</c:if>
                        <c:if test="${ cur.fetch_way==3 }">银行卡</c:if>
                    </td>
                    <td>${ cur.fetch_poundage }%</td>
                    <td>
                        <div class="btn-group-dis">
                            <a class="btn btn-xs btn-primary " href="${ctx }/console/config/fetch/update/${ cur.id }"
                               data-toggle="tooltip" title="编辑">
                                <i class="glyphicon glyphicon-edit"></i>编辑
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
