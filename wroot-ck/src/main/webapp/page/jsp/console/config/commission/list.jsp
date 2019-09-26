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
                <th>佣金类型</th>
                <th>是否开启</th>
                <th>佣金数目</th>
                <th class="text-info">操作</th>
            </tr>
            </thead>
            <tbody align="center">
            <c:forEach items="${configs}" var="cur" varStatus="status">
                <tr>
                    <td>
                        <c:if test="${ cur.commission_type==1 }">消费佣金</c:if>
                        <c:if test="${ cur.commission_type==2 }">合伙人佣金</c:if>
                    </td>
                    <td>
                        <c:if test="${ cur.commission_open==0 }">否</c:if>
                        <c:if test="${ cur.commission_open==1 }">是</c:if>
                    </td>
                    <td>
                        <c:if test="${ cur.commission_type==1 }">消费金额的</c:if>
                        <c:if test="${ cur.commission_type==2 }">推荐商家营业额的</c:if>
                        ${ cur.commission_amount }%
                    </td>
                    <td>
                        <div class="btn-group-dis">
                            <a class="btn btn-xs btn-primary " href="${ctx }/console/config/commission/update/${ cur.id }"
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
