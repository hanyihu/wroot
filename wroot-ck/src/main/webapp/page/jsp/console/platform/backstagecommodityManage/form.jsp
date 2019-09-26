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
			<label for="parentId" class="col-sm-2 control-label">一级分类*</label>
			<div class="col-sm-6 ">
				<select	class="form-control" id="categoryId" name="categoryId"	data-value="${ entity.categoryId }" data-rule-required="true" 
					data-linkage="true" data-linkage-target="#subCategoryId" data-linkage-url="${ctx }/console/platform/commodity/subCategoryList">
					<c:forEach items="${categoryList }" var="item">
						<option value="${item.id }">--${item.name }--</option>
					</c:forEach>
				</select>		
						
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">二级分类*</label>
			<div class="col-sm-6 ">
				<select	class="form-control" id="subCategoryId" name="subCategoryId"	data-value="${ entity.subCategoryId }" data-rule-required="true" >
					<option value="">--选择二级分类--</option>
				</select>		
						
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">宝贝类型*</label>
			<div class="col-sm-6 ">
				<select	class="form-control" id="type" name="type"	data-value="${ entity.type }" data-rule-required="true" >
					<option value="">--宝贝类型--</option>
					<option value="1">--淘宝--</option>
					<option value="2">--天猫--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
				<label for="parentId" class="col-sm-2 control-label">模块</label>
			<div class="col-sm-6 ">	
				<select	class="form-control" id="lookup-module" name="module"	data-value="${ entity.module }"
					data-linkage="true" data-linkage-target="#lookup-moduleCategoryId" data-linkage-url="${ctx }/console/platform/commodity/moduleCategoryList">
					<option value="">--模块--</option>
					<option value="1">--精选专题--</option>
					<option value="2">--爱生活--</option>
				</select>
			</div>	
			<div class="col-sm-4  help-inline form-control-static"></div>	
		</div>
		
		
		<div class="form-group">
			<label for="itemId" class="col-sm-2 control-label">模块分类</label>
			<div class="col-sm-6 ">
				<select	class="form-control" id="lookup-moduleCategoryId" name="moduleCategoryId"	data-value="${ entity.moduleCategoryId }">
				<option value="">--模块下分类--</option>
			</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
			
		
		<div class="form-group">
			<label for="itemId" class="col-sm-2 control-label">特色分类</label>
			<div class="col-sm-6 ">
				<select	class="form-control" id="lookup-specialCategoryId" name="specialCategoryId"	data-value="${ entity.specialCategoryId }">
					<option value="">--特色分类-- </option>
					<c:forEach items="${specialCategoryList }" var="cur">
						<option value="${cur.id }">--${cur.name }--</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>


		<div class="form-group">
			<label for="itemId" class="col-sm-2 control-label">淘客商品id*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="itemId" name="itemId" 	value="${ entity.itemId }" 
				data-rule-required="true" data-rule-digits="true"
				placeholder="请输入淘客商品id">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="commision" class="col-sm-2 control-label">佣金*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="commision" name="commision" value="${ entity.commision }" data-rule-required="true"  data-rule-number="true"	placeholder="请输入佣金">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		<!-- 针对导入的商品 -->
		<c:if test="${entity.flag==1 or  entity.flag==2}">
			<div class="form-group">
				<label for="parentId" class="col-sm-2 control-label">主图URL*</label>
				<div class="col-sm-6 ">
					<input type="text" class="form-control" id="outIcon" name="outIcon" value="${ entity.outIcon }"  data-rule-required="true" 
							placeholder="请输入主图地址">
				</div>
				<div class="col-sm-4  help-inline form-control-static"></div>
			</div>
		
			<div class="form-group">
				<label for="parentId" class="col-sm-2 control-label">多图URL*(英文逗号分隔 勿换行)</label>
				<div class="col-sm-6 ">
					<textarea rows="5" style="width: 100%" data-rule-maxlength="1024" name="outerImages" id="outerImages" 
					placeholder="请输入主图地址(多张用英文逗号分隔勿换行")>${entity.outerImages }</textarea>		
				</div>
				<div class="col-sm-4  help-inline form-control-static"></div>
			</div>
		
			
		</c:if>
		
		<!-- 针对手动上传的的商品 -->
		<c:if test="${entity.flag==0 }">
								
			<div class="form-group">
				<label for="parentId" class="col-sm-2 control-label">图标*</label>
				<div class="col-sm-6 ">
					<input type="hidden" name="icon" value="${entity.icon}" data-rule-required="true" /> 
					<input class="fileupload"  id="icon" type="file" data-display-id="icon"	data-preview="true" data-rule-accept="image/*" />	
				</div>
				<div class="col-sm-4  help-inline form-control-static"></div>
			</div>
			
									
			<div class="form-group">
				<label for="parentId" class="col-sm-2 control-label">图片*</label>
				<div class="col-sm-6 ">
					<input type="hidden" name="images"  value="${entity.images}" data-rule-required="true" /> 
					<input class="fileupload" id="images"  type="file"  multiple data-display-id="images"	data-preview="true" data-rule-accept="image/*" />	
				</div>
				<div class="col-sm-4  help-inline form-control-static"></div>
			</div>
		</c:if>
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">价格*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="price" name="price" value="${ entity.price }" data-rule-required="true"  data-rule-number="true"	placeholder="请输入价格">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">折后价*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="discountPrice" name="discountPrice" data-rule-required="true"  data-rule-number="true" value="${ entity.discountPrice }" 
						placeholder="请输入折后价">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">销售数量*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="sellNum" name="sellNum" value="${ entity.sellNum }"  data-rule-required="true" data-rule-digits="true"
						placeholder="请输入销售数量">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">是否包邮*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="freeShip" name="freeShip" data-value="${entity.freeShip }" data-rule-required="true">
					<option value="0">--否--</option>
					<option value="1">--是--</option>
				</select>	
			
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="commision" class="col-sm-2 control-label">淘口令*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="taoToken" name="taoToken" value="${ entity.taoToken }" data-rule-required="true" placeholder="请输入淘口令">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">优惠券-开始时间*</label>
			<div class="col-sm-6 ">
				   <div class="input-group">
						<input  class="datetime"  id="couponStartDate" name="couponStartDate" type="text" value="<fmt:formatDate value="${entity.couponStartDate}" pattern="yyyy-MM-dd"/>" data-date-format="yyyy-mm-dd"  data-min-view="2"      
							data-end-date-target="couponEndDate"
						readonly>
					</div>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>        
	            
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">优惠券-截至时间*</label>
			<div class="col-sm-6 ">
				<input  class="datetime"  id="couponEndDate" name="couponEndDate" type="text"value="<fmt:formatDate value="${entity.couponEndDate}" pattern="yyyy-MM-dd"/>" data-date-format="yyyy-mm-dd"  data-min-view="2"    
					data-start-date-target="couponStartDate"	
					readonly  /> 
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">优惠券-金额*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="couponAmount" name="couponAmount" value="${ entity.couponAmount }"  data-rule-required="true"  data-rule-number="true" 
						placeholder="请输入优惠券-金额">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">优惠券-跳转URL*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="couponUrl" name="couponUrl" value="${ entity.couponUrl }" data-rule-required="true" 
						placeholder="请输入优惠券-跳转url">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
	<%-- 							
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">简介-推荐理由*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="summary" name="summary" value="${ entity.summary }"  data-rule-required="true" 
						placeholder="请输入简介-推荐理由">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div> --%>
		
								
				
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">是否进口优选*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="todayRecommended" name="todayRecommended" data-value="${entity.todayRecommended }" data-rule-required="true">
					<option value="0">--否--</option>
					<option value="1">--是--</option>
				</select>	
			
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">是否必买清单*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="needBuyed" name="needBuyed" data-value="${entity.needBuyed }" data-rule-required="true">
					<option value="0">--否--</option>
					<option value="1">--是--</option>
				</select>		
						
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">状态*</label>
			<div class="col-sm-6 ">
				<select class="form-control" id="enabled" name="enabled" data-value="${entity.enabled }" data-rule-required="true">
					<option value="1">--上架--</option>
					<option value="0">--下架--</option>
				</select>
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">图文详情*</label>
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
