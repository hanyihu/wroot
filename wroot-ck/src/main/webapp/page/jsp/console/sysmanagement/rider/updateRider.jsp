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
<form class="form-horizontal validate" role="form" method="post" onsubmit="return toVaild()">
    <input id="isCheck" type="hidden" value="">
    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">邀请是否开启*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="rider_invite" name="rider_invite"	data-value="${ management.rider_invite }">
                <option value="0">--否--</option>
                <option value="1">--是--</option>
            </select>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">骑手服务标准*</label>
        <div class="col-sm-6 ">
            <textarea class="form-control" id="rider_service_standard" name="rider_service_standard" rows="10"
                      data-rule-required="true"
                      data-rule-maxlength="500">${ management.rider_service_standard }</textarea>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>


    <div class="form-group clearfix">
        <div class="col-sm-offset-3 col-sm-9">
            <button type="button" class="btn btn-primary" id="checkText">
                <i class="glyphicon glyphicon-check"></i> 验证
            </button>
            <button type="submit" class="btn btn-primary">
                <i class="glyphicon glyphicon-ok"></i> 提交
            </button>
            <button type="button" class="btn btn-info action-back">
                <i class="glyphicon glyphicon-repeat"></i> 返回
            </button>
        </div>
    </div>

</form>
<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
<script type="text/javascript">
    $("#checkText").on("click", function () {
        var content = $("#rider_service_standard").val();
        $.ajax({
            url: "/wroot_ck_war/api/aitextcheck/check",
            dataType: "text",
            data: {"content": content},
            type: "POST",
            success: function (data) {
                if (data == 1) {
                    $("#isCheck").val(1);
                    alert("验证通过!");
                } else if (data == 0) {
                    $("#isCheck").val(0);
                    alert("验证未通过!内容包含有违禁词汇");
                }
            }
        });
    })

    $("#rider_service_standard").on("focus", function () {
        $("#isCheck").val("");
    })

    function toVaild() {
        var isCheck = $("#isCheck").val();
        if (isCheck == "" || isCheck == null) {
            alert("请先完成验证!");
            return false;
        } else if (isCheck == 0) {
            alert("请先核对内容再提交!");
            return false;
        } else if (isCheck == 1) {
            return true;
        }
    }
</script>
</html>

