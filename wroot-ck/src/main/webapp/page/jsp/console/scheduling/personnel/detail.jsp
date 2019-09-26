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
        <h3 class="text-info">骑手详情</h3>
        <br/>
    </div>
    <div class="col-sm-10 col-sm-offset-1">
        <table class="table table-striped table-condensed table-bordered">
            <thead>
            <tr>
                <th colspan="5">骑手基本信息</th>
            </tr>
            </thead>
            <tbody>
            <tr class="success">
                <td>骑手名称</td>
                <td>电话</td>
                <td>性别</td>
                <td>头像</td>
                <td>生日</td>

            </tr>
            <tr>
                <td>${entity.nickname }</td>
                <td>${entity.mobile}</td>
                <td><span
                        data-random-label="${ entity.gender }">${entity.gender ==1?'男':(entity.gender==2?'女':'保密') }</span>
                </td>
                <td>${entity.headpic }</td>
                <td><fmt:formatDate value="${entity.birthday}" pattern="yyyy-MM-dd"/></td>

            </tr>


            <tr class="success">
                <td>积分</td>
                <td>是否VIP</td>
                <td>配送区域</td>
                <td>邀请收益</td>
                <td>登录方式</td>

            </tr>
            <tr>
                <td>${entity.score }</td>
                <td><span
                        data-random-label="${ entity.viped }">${entity.viped ==0?'非会员':(entity.viped==1?'会员':'无') }</span>
                </td>
                <td>${entity.sendarea }</td>
                <td>${entity.invitationFee }</td>
                <td><span
                        data-random-label="${entity.thirdPartyType}">${entity.thirdPartyType ==1?'微信':(entity.thirdPartyType==2?'qq':(entity.thirdPartyType==3?'支付宝':"其它")) }</span>
                </td>

            </tr>

            <tr class="success">
                <td>支付宝账号</td>
                <td>微信账号</td>
                <td>淘宝账号</td>
                <td>是否登录</td>

            </tr>
            <tr>
                <td>${entity.aliAccount }</td>
                <td>${entity.wechatAccount }</td>
                <td>${entity.taobaoAccount }</td>
                <td><span
                        data-random-label="${entity.logged}">${entity.logged ==0?'否':(entity.viped==1?'是':'无') }</span>
                </td>

            </tr>

            <tr>
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
            </tr>
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
<%@include file="/page/jspf/console-pager.jspf"%>
<%--<%@include file="/page/jspf/console-body-last.jspf"%>--%>
</body>
</html>
