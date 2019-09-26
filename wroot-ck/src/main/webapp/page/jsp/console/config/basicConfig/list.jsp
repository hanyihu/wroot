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
        <div class="action-bar">
            <a class="btn btn-sm btn-success" href="${ctx }/console/config/basicConfig/update/0"> <i
                    class="glyphicon glyphicon-plus"></i> 添加
            </a>

            <button class="btn btn-sm btn-danger action-post" type="button"
                    data-checkbox-required="ids" data-href="remove"
                    data-confirm="确认删除吗">
                <span class="glyphicon glyphicon-trash"></span> 批量删除
            </button>
        </div>
        <table class="table table-striped table-bordered table-hover ">
            <thead>
            <tr class="text-center">
                <th><input type="checkbox" data-member="ids"></th>
                <th>系统名称</th>
                <th>ICP备案</th>
                <th>网站关键字</th>
                <th class="text-info">操作</th>
            </tr>
            </thead>
            <tbody align="center">
            <c:forEach items="${configs}" var="cur" varStatus="status">
                <tr>
                    <td class="center"><input type="checkbox"	value="${ cur.id }" name="ids"></td>
                    <td>${ cur.name }</td>
                    <td><span>${ cur.icp_record }</span></td>
                    <td><span>${ cur.web_keyword }</span></td>
                    <td>
                        <div class="btn-group-dis">
                                <a class="btn btn-xs btn-primary " href="${ctx }/console/config/basicConfig/update/${ cur.id }"
                                   data-toggle="tooltip" title="编辑">
                                    <i class="glyphicon glyphicon-edit"></i>编辑
                                </a>
                                <button class="btn btn-xs btn-danger action-get" type="button"
                                        data-href="${ctx }/console/config/basicConfig/remove/${ cur.id }" data-toggle="tooltip"
                                        title="删除" data-confirm="确认删除本条记录吗">
                                    <i class="glyphicon glyphicon-trash"></i>
                                </button>
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
