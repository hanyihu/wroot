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
            <label class="sr-only" for="startTime">日期</label>
            <input  class="datetime form-control"  id="startTime" name="startTime" type="text" value="" data-date-format="yyyy-mm-dd"  data-min-view="2"
                    style="background-color:#ffffff"	data-end-date-target="lookup-endDate"	readonly placeholder="开始日期"  />
        </div>
        <div class="form-group">
            <label class="sr-only" for="endTime">日期</label>
            <div class="input-group">
                <input  class="datetime form-control"   id="endTime" name="endTime" type="text"value="" data-date-format="yyyy-mm-dd"  data-min-view="2"
                        style="background-color:#ffffff"		data-start-date-target="lookup-startDate"	readonly placeholder="结束日期"  />
            </div>
        </div>


        <div class="form-group">
            <label class="sr-only" for="status">订单状态</label>
            <select class="form-control" id="status" name="status" data-value="">
                <option value="">--订单状态--</option>
                <option value="1">已下单</option>
                <option value="2">骑手已接单</option>
                <option value="3">骑手配送中</option>
                <option value="4">已完成</option>
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
        </div>

        <table class="table table-striped table-bordered table-hover ">
            <thead>
            <tr class="text-center">
                <th><input type="checkbox" data-member="ids"></th>
                <th>订单号</th>
                <th>骑手</th>
                <th>骑手电话</th>
                <th>订单状态</th>
                <th>收货地址</th>
                <th>店铺名称</th>
                <th>下单时间</th>
                <th class="text-info">操作</th>
            </tr>
            </thead>
            <tbody align="center">
            <c:forEach items="${orders.datas}" var="cur" varStatus="status">
                <tr>
                    <td class="center"><input type="checkbox" value="${ cur.id }" name="ids"></td>
                    <td>${cur.orderno }</td>
                    <td>${cur.name}</td>
                    <td>${cur.phone}</td>
                    <td><span
                            data-random-label="${ cur.status }">${cur.status ==1?'已下单':(cur.status==2?'骑手接单':(cur.status==3?'骑手配送中':(cur.status==4?'已完成':'无'))) }</span></td>
                    <td>${cur.shippingAddress}
                    </td>
                    <td>${ cur.merchantName }</td>
                    <td><fmt:formatDate value="${cur.time}" pattern="yyyy-MM-dd"/></td>
                    <td>
                        <div class="btn-group-dis">

                            <a class="btn btn-xs btn-success " href="${ cur.id}/query" data-toggle="tooltip" title="详情">
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
            <c:forEach begin="${ fn:length(orders.datas)}" end="${ orders.size-1}">
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
