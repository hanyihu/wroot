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
                <th>第三方接口名称</th>
                <th>第三方接口id</th>
                <th>第三方接口公钥</th>
                <th>第三方接口私钥</th>
                <th class="text-info">操作</th>
            </tr>
            </thead>
            <tbody align="center">
            <c:forEach items="${payInterfaces}" var="cur" varStatus="status">
                <tr>
                    <td>${ cur.interface_name }</td>
                    <td>${ cur.interface_id }</td>
                    <td>${ cur.public_key }</td>
                    <td>${ cur.private_key }</td>
                    <td>
                        <div class="btn-group-dis">
                            <a class="btn btn-xs btn-primary " href="${ctx }/console/config/thirdInterface/updatePay/${ cur.id }"
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
