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
            <label class="sr-only" for="lookup-prompt_type">请选择分类</label>
            <select class="form-control" id="lookup-prompt_type" name="prompt_type"	data-value="${ pager.lookup.prompt_type }">
                <option value="">--请选择分类--</option>
                <option value="1">--用户端语音--</option>
                <option value="2">--商家端语音--</option>
                <option value="3">--骑手端语音--</option>
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
            <a class="btn btn-sm btn-success" href="${ctx}/console/config/prompt/update/0"> <i
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
                <th>提示音名称</th>
                <th>提示音类型</th>
                <th class="text-info">操作</th>
            </tr>
            </thead>
            <tbody align="center">
            <c:forEach items="${ pager.datas}" var="cur" varStatus="status">
                <tr>
                    <td class="center"><input type="checkbox"
                                              value="${ cur.id }" name="ids"></td>
                    <td title="${cur.prompt_name }">${fn:substring(cur.prompt_name, 0, 20)}${fn:length(cur.prompt_name) gt 20 ? "..." : ""}</td>
                    <td>
                        <c:if test="${cur.prompt_type==1 }">用户端语音</c:if>
                        <c:if test="${cur.prompt_type==2 }">商家端语音</c:if>
                        <c:if test="${cur.prompt_type==3 }">骑手端语音</c:if>
                    </td>
                    <td>
                        <div class="btn-group-dis">

                            <a class="btn btn-xs btn-success " href="${ctx}/console/config/prompt/update/${ cur.id}"
                               data-toggle="tooltip" title="编辑"> <i
                                    class="glyphicon glyphicon-edit"></i>
                            </a>


                            <button class="btn btn-xs btn-danger action-get" type="button"
                                    data-href="${ctx }/console/config/prompt/remove/${ cur.id }" data-toggle="tooltip"
                                    title="删除" data-confirm="确认删除本条记录吗">
                                <i class="glyphicon glyphicon-trash"></i>
                            </button>
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
