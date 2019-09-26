<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/page/jspf/prepare.jspf" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <style type="text/css">
        .table th, .table td {
            text-align: center;
            vertical-align: middle !important;
        }
    </style>
    <%@include file="/page/jspf/head.jspf" %>
</head>
<body>
<%@include file="/page/jspf/console-body-first.jspf" %>

<div class="row row-nocol">
    <form class="form-horizontal validate" role="form" method="post">

        <div class="text-center">
            <h3 class="text-info">骑手订单详情</h3>
            <br/>
        </div>
        <div class="col-sm-10 col-sm-offset-1">
            <table class="table table-striped table-condensed table-bordered">
                <thead>
                <tr>
                    <th colspan="5">订单基本信息</th>
                </tr>
                </thead>
                <tbody>
                <tr class="success">
                    <td>订单号</td>
                    <td>所属商家</td>
                    <td>订单状态</td>
                    <td>支付方式</td>

                </tr>
                <tr>
                    <td>${entity.orderno }</td>
                    <td>${entity.merchantName}</td>
                    <td><span
                            data-random-label="${ entity.status }">${entity.status ==1?'已下单':(entity.status==2?'骑手接单':(entity.status==3?'骑手配送中':(entity.status==4?'已完成':'无'))) }</span></td>
                    <td><span
                            data-random-label="${ entity.payType }">${entity.payType ==1?'余额支付':(entity.payType==2?'支付宝支付':(entity.payType==3?'微信支付':"其它方式")) }</span></td>

                </tr>

                <%--<tr>
                    <th colspan="5">证照信息</th>
                </tr>

                <tr class="danger">
                    <td>健康证</td>
                    <td>健康证有效期</td>
                    <td>身份证</td>
                    <td>身份证有效期</td>
                    <td>推荐二维码</td>
                </tr>
                <tr>
                    <td><img data-attachment="${ entity.healthCard }" class="pop"/></td>
                    <td>${entity.healthValidity }</td>
                    <td><img data-attachment="${ entity.idCard }" class="pop"/></td>
                    <td>${entity.idValidity }</td>
                    <td>${entity.recommendQcodePath}</td>
                </tr>

                <tr class="danger">
                    <td>骑手紧急联系人</td>
                    <td>骑手紧急联系人电话</td>
                    <td>婚姻状况</td>
                    <td>学历</td>
                    <td>邀请码</td>

                </tr>
                <tr>
                    <td>${entity.emergency }</td>
                    <td>${entity.emergencyPhone }</td>
                    <td>${entity.marriage }</td>
                    <td>${entity.education }</td>
                    <td>${entity.recommendCode}</td>
                </tr>--%>
            </table>

        </div>

        <div class="form-group clearfix">
            <div class="col-sm-offset-4 col-sm-8">
                <button type="button" class="btn btn-info action-back">
                    <i class="glyphicon glyphicon-repeat"></i> 返回
                </button>
            </div>
        </div>

    </form>
</div>
</body>
</html>
