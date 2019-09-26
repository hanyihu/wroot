<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/page/jspf/prepare.jspf" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="application.title"/></title>
    <%@include file="/page/jspf/head.jspf" %>
    <%@include file="/page/jspf/console-uj.jspf" %>
    <%--上传图片需要引入的--%>
    <script type="text/javascript" src="${ctx }/static/js/jquery.fileupload.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/jquery.iframe-transport.js"></script>
    <script type="text/javascript">

    </script>

</head>
<body>
<%@include file="/page/jspf/console-body-first.jspf" %>
<form class="form-horizontal validate" role="form" method="post">

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">推荐人ID*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="recommendId" name="recommendId" value="${ entity.recommendId }"
                   data-rule-required="false"
                   placeholder="请输入推荐人ID">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">商铺名称*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="name" name="name" value="${ entity.name }"
                   data-rule-required="false"
                   placeholder="请输入商铺名称">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <%--<div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">商铺图标*</label>
        <div class="col-sm-6 ">
            <input type="hidden" name="shopTrademark" value="${entity.shopTrademark}" data-rule-required="false"/>
            <input class="fileupload" id="shopTrademark" type="file" data-display-id="shopTrademark" data-preview="true"
                   data-rule-accept="image/*"/>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>--%>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">详细地址*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="address" name="address" value="${ entity.address }"
                   data-rule-required="false"
                   placeholder="请输入详细地址">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">附近地标*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="landmarks" name="landmarks" value="${ entity.landmarks }"
                   data-rule-required="false"
                   placeholder="请输入附近地标">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">类型*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="type" name="type" data-value="${ entity.type }"
                    data-rule-required="false"
                    data-linkage="true" data-linkage-target="#areaId" data-linkage-url="">
                <option value="">--选择分类--</option>
                <option value="1">美食</option>
                <option value="2">外卖</option>
            </select>

        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">商家分类*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="categoryId" name="categoryId" data-value="${ entity.categoryId }"
                    data-rule-required="false">
                <option value="">--选择分类--</option>
                <option value="1">美食</option>
                <option value="2">外卖</option>
            </select>

        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">联系电话*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="mobile" name="mobile" value="${ entity.mobile }"
                   data-rule-required="false"
                   placeholder="请输入联系电话">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">外景照*</label>
        <div class="col-sm-6 ">
            <input type="hidden" name="outPic" value="${entity.outPic}" data-rule-required="false"/>
            <input class="fileupload" id="outPic" type="file" data-display-id="outPic" data-preview="true"
                   data-rule-accept="image/*"/>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">营业执照*</label>
        <div class="col-sm-6 ">
            <input type="hidden" name="licensePic" value="${entity.licensePic}" data-rule-required="false"/>
            <input class="fileupload" id="licensePic" type="file" data-display-id="licensePic" data-preview="true"
                   data-rule-accept="image/*"/>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">营业执照是否长期有效*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="licensePersistent" name="licensePersistent"
                    data-value="${ entity.licensePersistent }"
                    data-rule-required="false">
                <option value="">--选择分类--</option>
                <option value="1">1</option>
                <option value="2">2</option>
            </select>

        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">营业执照有效期*</label>
        <div class="col-sm-6 ">
            <div class="input-group">
                <input class="datetime" id="licenseDate" name="licenseDate" type="text"
                       value="${entity.licenseDate}"  data-end-date-target="couponEndDate" data-min-view="2"   data-rule-required="true"/>
                      <%-- data-date-format="yyyy-mm-dd" data-min-view="2"
                       data-end-date-target="couponEndDate"
                       readonly   data-rule-required="true">--%>
            </div>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>


    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">营业执照名称*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="licenseName" name="licenseName" value="${ entity.licenseName }"
                   data-rule-required="false"
                   placeholder="请输入营业执照名称">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">营业执照注册号*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="licenseRegcode" name="licenseRegcode"
                   value="${ entity.licenseRegcode }"
                   data-rule-required="false"
                   placeholder="请输入营业执照注册号">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">营业许可证图片*</label>
        <div class="col-sm-6 ">
            <input type="hidden" name="permitPic" value="${entity.permitPic}" data-rule-required="false"/>
            <input class="fileupload" id="permitPic" type="file" data-display-id="permitPic" data-preview="true"
                   data-rule-accept="image/*"/>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">许可证是否长期有效*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="permitPersistent" name="permitPersistent"
                    data-value="${ entity.permitPersistent }" data-rule-required="false">
                <option value="1">1</option>
                <option value="2">2</option>
            </select>

        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">营业许可证有效期*</label>
        <div class="col-sm-6 ">
            <div class="input-group">
                <input class="datetime" id="permitDate" name="permitDate" type="text"
                       value="${entity.permitDate}" data-rule-required="true" />


            </div>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>


    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">账户名*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="accountName" name="accountName" value="${ entity.accountName }"
                   data-rule-required="false"
                   placeholder="请输入账户名">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">银行帐号*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="bankAccount" name="bankAccount" value="${ entity.bankAccount }"
                   data-rule-required="false"
                   placeholder="请输入银行卡号">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">开户银行*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="bankName" name="bankName" value="${ entity.bankName }"
                   data-rule-required="false"
                   placeholder="请输入开户银行">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">状态*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="status" name="status"
                    data-value="${ entity.status }" data-rule-required="false">
                <option value="1">审核中</option>
                <option value="2">通过</option>
                <option value="3">拒绝</option>
            </select>

        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">商家设施*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="facility" name="facility"
                   value="${ entity.facility }"
                   data-rule-required="false"
                   placeholder="请输入商家设施">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">店铺介绍*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="describe" name="describe" value="${ entity.describe }"
                   data-rule-required="false"
                   placeholder="请输入店铺介绍">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">营业时间*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="businessHours" name="businessHours"
                   value="${ entity.businessHours }"
                   data-rule-required="false"
                   placeholder="请输入营业时间">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">最低消费金额*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="price" name="price" value="${ entity.price }"
                   data-rule-required="false"
                   placeholder="请输入最低消费金额">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

   <%-- <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">酒店可否加床*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="hotelCanAddBed" name="hotelCanAddBed"
                    data-value="${ entity.hotelCanAddBed }" data-rule-required="false">
                <option value="1">可以</option>
                <option value="2">不可以</option>

            </select>

        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">酒店加床价格*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="hotelAddBedPrice" name="hotelAddBedPrice"
                   value="${ entity.hotelAddBedPrice }"
                   data-rule-required="false"
                   placeholder="请输入酒店加床价格">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">酒店是否接待外宾*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="hotelForeigned" name="hotelForeigned"
                    data-value="${ entity.hotelForeigned }" data-rule-required="false">
                <option value="1">是</option>
                <option value="2">否</option>

            </select>

        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">酒店是否可携带宠物*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="hotelPeted" name="hotelPeted"
                    data-value="${ entity.hotelPeted }" data-rule-required="false">
                <option value="1">是</option>
                <option value="2">否</option>

            </select>

        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">酒店是否需要押金*</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="hotelPledge" name="hotelPledge"
                    data-value="${ entity.hotelPeted }" data-rule-required="false">
                <option value="1">是</option>
                <option value="2">否</option>

            </select>

        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>--%>


    <div class="form-group">
        <label for="wellChosened" class="col-sm-2 control-label">镗镗锣精选</label>
        <div class="col-sm-6 ">
            <select class="form-control" id="wellChosened" name="wellChosened" data-value="${entity.wellChosened }"
                    data-rule-required="false">
                <option value="0">--否--</option>
                <option value="1">--是--</option>
            </select>
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>

    <div class="form-group">
        <label for="wellChosenSort" class="col-sm-2 control-label">精选排序</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="wellChosenSort" name="wellChosenSort"
                   value="${ entity.wellChosenSort }"
                   data-rule-digits="true" placeholder="请输入排序">
        </div>
        <div class="col-sm-4  help-inline form-control-static"></div>
    </div>


    <div class="form-group">
        <label for="parentId" class="col-sm-2 control-label">二维码*</label>
        <div class="col-sm-6 ">
            <input type="text" class="form-control" id="qrcode" name="qrcode"
                   value="${ entity.qrcode }"
                   data-rule-required="false"
                   placeholder="二维码">
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
<%@include file="/page/jspf/console-body-last.jspf" %>
</body>
</html>
