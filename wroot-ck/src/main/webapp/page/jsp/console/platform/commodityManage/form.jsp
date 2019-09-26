<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
<%@include file="/page/jspf/head.jspf"%>
<%@include file="/page/jspf/console-uj.jspf"%>
 <!-- 上传js需要引入的 -->
 <script type="text/javascript" src="${ctx }/static/js/jquery.fileupload.js"></script> 
 <script type="text/javascript" src="${ctx }/static/js/jquery.iframe-transport.js"></script>  
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
										
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">名称*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="name" name="name" value="${ entity.name }"  data-rule-required="true" 
						placeholder="请输入名称">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>


        <div class="form-group">
            <label for="category_id" class="col-sm-2 control-label">一级分类*</label>
			<div class="col-sm-6 ">
                <select class="form-control" id="category_id" name="category_id" data-value="${ entity.category_id }"
                        data-rule-required="true"
                        data-linkage="true" data-linkage-target="#subCategoryId"
                        data-linkage-url="${ctx }/console/platform/commodity/subCategoryList">
					<c:forEach items="${categoryList }" var="item">
						<option value="${item.id }">--${item.name }--</option>
					</c:forEach>
                </select>

            </div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>

        <%--
                        <div class="form-group">
                            <label for="parentId" class="col-sm-2 control-label">二级分类*</label>
                            <div class="col-sm-6 ">
                                <select	class="form-control" id="subCategoryId" name="subCategoryId"	data-value="${ entity.subCategoryId }" data-rule-required="true" >
                                    <option value="">--选择二级分类--</option>
                                </select>

                            </div>
                            <div class="col-sm-4  help-inline form-control-static"></div>
                        </div>--%>

        <div class="form-group">
            <label for="images" class="col-sm-2 control-label">图片*</label>
            <div class="col-sm-6 ">
                <input type="hidden" name="images" value="${entity.images}" data-rule-required="false"/>
                <input class="fileupload" id="images" type="file" multiple data-display-id="images" data-preview="true"
                       data-rule-accept="image/*"/>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>


        <div class="form-group">
            <label for="spec" class="col-sm-2 control-label">规格*</label>
			<div class="col-sm-6 ">
                <input type="text" class="form-control" id="spec" name="spec" value="${ entity.spec }"
                       data-rule-required="true"
                       placeholder="请输入规格">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
        </div>
		<div class="form-group">
            <label for="price" class="col-sm-2 control-label">单价*</label>
			<div class="col-sm-6 ">
                <input type="text" class="form-control" id="price" name="price" value="${ entity.price }"
                       data-rule-required="true" data-rule-number="true" placeholder="请输入价格">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>

        <div class="form-group">
            <label for="company" class="col-sm-2 control-label">单位*</label>
			<div class="col-sm-6 ">
                <input type="text" class="form-control" id="company" name="company" value="${ entity.company }"
                       data-rule-required="true" placeholder="请输入单位">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>


        <div class="form-group">
            <label for="stock" class="col-sm-2 control-label">库存*</label>
			<div class="col-sm-6 ">
                <input type="text" class="form-control" id="stock" name="stock" value="${ entity.stock }"
                       data-rule-required="true"
                       placeholder="请输入库存">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>

		
		<div class="form-group">
            <label for="attribute" class="col-sm-2 control-label">属性*</label>
			<div class="col-sm-6 ">
                <input type="text" class="form-control" id="attribute" name="attribute" value="${ entity.attribute }"
                       data-rule-required="true" placeholder="请输入属性">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>


        <div class="form-group">
            <label for="isgroupbuy" class="col-sm-2 control-label">是否团购*</label>
			<div class="col-sm-6 ">
                <select class="form-control" id="isgroupbuy" name="isgroupbuy" data-value="${entity.isgroupbuy }"
                        data-rule-required="true">
					<option value="0">--否--</option>
					<option value="1">--是--</option>
                </select>

            </div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>

        <%--	<div class="form-group">
                <label for="enabled" class="col-sm-2 control-label">状态*</label>
                <div class="col-sm-6 ">
                    <select class="form-control" id="enabled" name="enabled" data-value="${entity.enabled }" data-rule-required="true">
                        <option value="1">--上架--</option>
                        <option value="0">--下架--</option>
                    </select>
                </div>
                <div class="col-sm-4  help-inline form-control-static"></div>
            </div>--%>
		
		<div class="form-group">
            <label for="content" class="col-sm-2 control-label">描述详情*</label>
			<div class="col-sm-6 ">
				<script class="u-editor" id="content"  name="content"  type="text/plain">
        				${entity.content}
   				</script>	
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
</html>
