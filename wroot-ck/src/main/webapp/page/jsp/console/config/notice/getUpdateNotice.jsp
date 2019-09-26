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
<form class="form-horizontal validate" role="form" method="post">

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">公告名称*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="title" name="title" value="${ notice.title }"
                   data-rule-required="true"
                   data-rule-maxlength="32"placeholder="请输入公告名称（32字以内）">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">公告内容*</label>
        <div class="col-sm-6 ">
            <textarea class="form-control" id="content" name="content" rows="10"
                      data-rule-required="true"
                      data-rule-maxlength="1000">${ notice.content }</textarea>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>
    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">公告类型*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="notice_type" name="notice_type"	data-value="${ notice.notice_type }">
                <option value="">--请选择分类--</option>
                <option value="1">--用户--</option>
                <option value="2">--商家--</option>
                <option value="3">--分站--</option>
                <option value="4">--个人--</option>
            </select>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group" id="setMobile" style="display: none;">
        <label for="parentId" class="col-sm-2 control-label">接收人的手机号*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="to_user_mobile" name="to_user_mobile" value="${ notice.to_user_mobile }"
                   data-rule-maxlength="11" placeholder="请输入接收人手机号">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group clearfix">
        <div class="col-sm-offset-3 col-sm-9">
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
    $(function () {
        var type=$("#notice_type").val();
        if(type == 4){
            $("#setMobile").css("display","block");
        }
    })
    
    $("#notice_type").on("click",function () {
        var type=$("#notice_type").val();
        if(type == 4){
            $("#setMobile").css("display","block");
        }else {
            $("#to_user_mobile").val("");
            $("#setMobile").css("display","none");
        }
    })
</script>
</html>

