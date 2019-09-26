<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/page/jspf/prepare.jspf" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="application.title"/></title>
    <%@include file="/page/jspf/head.jspf" %>
    <script type="text/javascript">

    </script>
</head>
<body>
<%@include file="/page/jspf/console-body-first.jspf" %>
<div class="row row-nocol">
    <form class="form-inline lookup" role="form" method="post">
        <div class="form-group">
            <label class="sr-only" for="nickname">名称</label>
            <input type="text" class="form-control" id="nickname" name="nickname" value="" placeholder="名称">
        </div>

        <div class="form-group">
            <label class="sr-only" for="mobile">手机号</label>
            <input type="text" class="form-control" id="mobile" name="mobile" value="" placeholder="手机号">
        </div>

        <div class="form-group">
            <label class="sr-only" for="gender">性别</label>
            <select class="form-control" id="gender" name="gender" data-value="">
                <option value="">--性别--</option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
        </div>


        <div class="form-group">
            <label class="sr-only" for="enabled">骑手状态</label>
            <select class="form-control" id="enabled" name="enabled" data-value="">
                <option value="">--骑手状态--</option>
                <option value="1">可用</option>
                <option value="0">不可用</option>
            </select>
        </div>

        <!-- 权限为全国的则显示选择城市 -->
        <%--<c:if test="${empty principal.cityId}">
            <div class="form-group">
                <label class="sr-only" for="lookup-cityId">请选择城市</label> <select
                    class="form-control" id="lookup-cityId" name="cityId"
                    data-value="${ pager.lookup.cityId }">
                <option value="">--请选择城市--</option>
                <c:forEach items="${cityList }" var="city">
                    <option value="${city.id }">${city.name }</option>
                </c:forEach>
            </select>
            </div>
        </c:if>--%>

        <div class="form-group form-btn-bar">
            <button type="submit" class="btn btn-primary btn-sm">
                <i class="glyphicon glyphicon-search"></i> 查询
            </button>
            <button type="button" class="btn btn-info btn-sm reset">
                <i class="glyphicon glyphicon-refresh"></i> 重置
            </button>
        </div>

    </form>
    <hr/>
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
            <%--<a class="btn btn-sm btn-success" href="0">
                <i class="glyphicon glyphicon-plus"></i> 添加
            </a>--%>

            <%--<button class="btn btn-sm btn-danger action-post" type="button"
                data-checkbox-required="ids" data-href="remove"
                data-confirm="确认删除吗">
                <span class="glyphicon glyphicon-trash"></span> 批量删除
            </button>--%>
        </div>

        <table class="table table-striped table-bordered table-hover ">
            <thead>
            <tr class="text-center">
                <th><input type="checkbox" data-member="ids"></th>
                <th>骑手名称</th>
                <th>电话</th>
                <th>性别</th>
                <th>积分</th>
                <th>VIP</th>
                <th>配送区域</th>
                <th>位置</th>
                <th class="text-info">操作</th>
            </tr>
            </thead>
            <tbody align="center">
            <c:forEach items="${personnel.datas}" var="cur" varStatus="status">
                <tr>
                    <td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
                    <td>${ cur.nickname }</td>
                    <td>${cur.mobile}</td>
                    <td><span
                            data-random-label="${ entity.gender }">${entity.gender ==1?'男':(entity.gender==2?'女':'保密') }</span></td>
                    <td>${ cur.score }</td>
                    <td><span data-random-label="${ cur.viped }">${cur.viped ==0?'非会员':(cur.viped==1?'会员':'无') }</span>
                    </td>
                    <td>${ cur.sendarea }</td>
                    <td></td>
                    <td>
                        <div class="btn-group-dis">

                            <a class="btn btn-xs btn-success " href="${ cur.id}" data-toggle="tooltip" title="详情">
                                <i class="glyphicon glyphicon-list-alt"></i>
                            </a>
                            <%--<a class="btn btn-xs btn-info " href="${ cur.id}/timeline" data-toggle="tooltip"
                               title="订单时间轴">
                                <i class="glyphicon glyphicon-time"></i>
                            </a>--%>

                        </div>
                    </td>
                </tr>
            </c:forEach>
            <c:forEach begin="${ fn:length(personnel.datas)}" end="${ personnel.size-1}">
                <tr>
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
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
<%@include file="/page/jspf/console-pager.jspf" %>
<%@include file="/page/jspf/console-body-last.jspf" %>
</body>
</html>
