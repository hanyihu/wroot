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
                <th>积分类型</th>
                <th>是否开启</th>
                <th>积分规则</th>
                <th class="text-info">操作</th>
            </tr>
            </thead>
            <tbody align="center">
            <c:forEach items="${managements}" var="cur" varStatus="status">
                <tr>
                    <td>
                        <c:if test="${ cur.integral_type==1 }">消费积分</c:if>
                        <c:if test="${ cur.integral_type==2 }">评价积分</c:if>
                        <c:if test="${ cur.integral_type==3 }">充值积分</c:if>
                        <c:if test="${ cur.integral_type==4 }">签到积分</c:if>
                        <c:if test="${ cur.integral_type==5 }">分享积分</c:if>
                    </td>
                    <td>
                        <c:if test="${ cur.integral_open==0 }">否</c:if>
                        <c:if test="${ cur.integral_open==1 }">是</c:if>
                    </td>
                    <td>
                        <c:if test="${ cur.integral_type==1 }">${ cur.integral_x }积分/元</c:if>
                        <c:if test="${ cur.integral_type==2 }">${ cur.integral_x }积分/无图评论 ${ cur.integral_y }积分/有图评论</c:if>
                        <c:if test="${ cur.integral_type==3 }">${ cur.integral_x }积分/元</c:if>
                        <c:if test="${ cur.integral_type==4 }">${ cur.integral_x }积分/元,累计${ cur.integral_y }天一次性奖励${ cur.integral_z }积分</c:if>
                        <c:if test="${ cur.integral_type==5 }">${ cur.integral_x }积分/天/转发(一天奖励一次/人)</c:if>
                    </td>
                    <td>
                        <div class="btn-group-dis">
                            <a class="btn btn-xs btn-primary " href="${ctx }/console/sysmanagement/integral/update/${ cur.id }"
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
