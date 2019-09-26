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
    <form class="form-inline lookup" role="form" method="post">
        <div class="form-group">
            <label class="sr-only" for="lookup-merchant_name">商家名称</label> <input
                type="text" class="form-control" id="lookup-merchant_name" name="merchant_name"
                value="${ pager.lookup.merchant_name }" placeholder="请输入商家名称">
        </div>


        <div class="form-group">
            <label class="sr-only" for="lookup-status">处理状态</label> <select
                class="form-control" id="lookup-status" name="status"
                data-value="${ pager.lookup.status }">
            <option value="">--请选择处理状态--</option>
            <option value="0">--未读--</option>
            <option value="1">--已读--</option>
            <option value="2">--已处理--</option>
            <option value="3">--不处理--</option>
        </select>
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
        <div class="action-bar">

        </div>

        <table class="table table-striped table-bordered table-hover ">
            <thead>
            <tr class="text-center">
                <th>商家名称</th>
                <th>留言人姓名</th>
                <th>留言内容</th>
                <th>留言时间</th>
                <th>是否包含违禁词汇</th>
                <th>处理状态</th>
                <th class="text-info">操作</th>
            </tr>
            </thead>
            <tbody align="center">
            <c:forEach items="${ pager.datas}" var="cur" varStatus="status">
                <tr>
                    <td>${cur.merchant_name }	</td>
                    <td>${cur.customer_name }	</td>
                    <td title="${cur.content }">${fn:substring(cur.content, 0, 20)}${fn:length(cur.content) gt 20 ? "..." : ""}</td>
                    <td>${ cur.msgTimeDesc }</td>
                    <td>
                        <c:if test="${cur.is_illegal_word==0 }">不包含</c:if>
                        <c:if test="${cur.is_illegal_word==1 }">包含</c:if>
                    </td>
                    <td>
                        <c:if test="${cur.status==0 }">未读</c:if>
                        <c:if test="${cur.status==1 }">已读</c:if>
                        <c:if test="${cur.status==2 }">已处理</c:if>
                        <c:if test="${cur.status==3 }">不处理</c:if>
                    </td>

                    <td>
                        <div class="btn-group-dis">

                            <a class="btn btn-xs btn-success " href="${ctx }/console/sysmanagement/merchantMsg/update/${ cur.id}"
                               data-toggle="tooltip" title="编辑"> <i
                                    class="glyphicon glyphicon-edit"></i>
                            </a>

                        </div>
                    </td>
                </tr>
            </c:forEach>
            <c:forEach begin="${ fn:length(pager.datas)}"
                       end="${ pager.size-1}">
                <tr>
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
<%@include file="/page/jspf/console-pager.jspf"%>
<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
