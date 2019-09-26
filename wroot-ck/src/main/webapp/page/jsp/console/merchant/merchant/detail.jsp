<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<style type="text/css">
	.table th, .table td { 
		text-align: center;
		vertical-align: middle!important;
	}
</style>
<%@include file="/page/jspf/head.jspf"%>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">

		<div class="text-center">
			<h3 class="text-info">商家详情</h3>
			<br/>
		</div>
		<div class="col-sm-10 col-sm-offset-1">
			<table class="table table-striped table-condensed table-bordered">
				<thead>
					<tr>
						<th colspan="5">商家基本信息</th>
					</tr>
				</thead>
				<tbody>
					<tr class="success">
						<td>商家名称</td>
						<td>所属用户</td>
						<td>推荐人</td>
						<td>类型</td>
						<td>所属分类</td>
						
					</tr>
					<tr>
						<td>${entity.name }</td>
						<td>${entity.customerName}</td>
						<td>${entity.recommendName }</td>
						<td>${entity.typeDesc }</td>
						<td>${entity.categoryName }</td>
					</tr>
					

					<tr class="success">
						<td>联系电话</td>
						<td>地区-详细地址</td>
						<td>附近地标</td>
						<td>外景照</td>
						<td>内景照</td>
					</tr>
					<tr >
						<td>${entity.mobile }</td>
						<td>${entity.areaName }-${entity.address }</td>
						<td>${entity.landmarks }</td>
						<td><img  data-attachment="${ entity.outPic }" class="pop"/></td>
						<td>
							<c:if test="${not empty entity.inPic }">
								<c:forEach items="${ fn:split(entity.inPic, ',') }" var="pic">
									<img  data-attachment="${pic }" class="pop"/>	
								</c:forEach>
							</c:if> 
						</td>
					</tr>
					
						<tr class="success">
						<td>营业时间</td>
						<td>最低消费金额</td>
						<td>服务评分</td>
						<td>环境评星</td>
						<td>口味评星</td>
					</tr>
					<tr >
						<td>${entity.businessHours }</td>
						<td>${entity.price }</td>
						<td>${entity.serviceStar }</td>
						<td>${entity.environmentStar }</td>
						<td>${entity.flavorStar }</td>
					</tr>
					
					<tr class="success">
						<td>总评价</td>
						<td>查看人数</td>
						<td>评论数</td>
						<td>好评数</td>
						<td>销售数量</td>
					</tr>
					<tr >
						<td>${entity.star }</td>
						<td>${entity.viewNum }</td>
						<td>${entity.commentNum }</td>
						<td>${entity.niceCommentNum }</td>
						<td>${entity.sellNum }</td>
					</tr>
					
					<tr class="info">
						<td>账户名</td>
						<td>银行账号</td>
						<td>开户银行</td>
						<td colspan="2">商家设施</td>
					</tr>
					<tr>
						<td>${entity.accountName }</td>
						<td>${entity.bankAccount }</td>
						<td>${entity.bankName }</td>
						<td colspan="2">${entity.facility } </td>
					</tr>
					
					
					<tr>
						<th colspan="5">证照信息</th>
					</tr>
					
					<tr class="danger">
						<td>营业执照名称</td>
						<td>营业执照注册号</td>
						<td>营业执照</td>
						<td>营业执照是否长期有效</td>
						<td>营业执照有效期</td>
					</tr>
					<tr >
						<td>${entity.licenseName }</td>
						<td>${entity.licenseRegcode }</td>
						<td><img  data-attachment="${ entity.licensePic }" class="pop"/></td>
						<td>${entity.licensePersistent==1?'是':'否' }</td>
						<td>${entity.licenseDate }</td>
					</tr>
					
					<tr class="danger">
						<td>营业许可证</td>
						<td>许可证是否长期有效</td>
						<td>营业许可证有效期</td>
						<td colspan="2">其他证明图片</td>
					</tr>
					<tr >
						<td><img  data-attachment="${ entity.permitPic }" class="pop"/></td>
						<td>${entity.permitPersistent==1?'是':'否' }</td>
						<td>${entity.permitDate }</td>
						<td colspan="2">
							<c:if test="${not empty entity.otherProvePic }">
								<c:forEach items="${ fn:split(entity.otherProvePic, ',') }" var="pic">
									<img  data-attachment="${pic }" class="pop"/>	
								</c:forEach>
							</c:if> 
						</td>
					</tr>
					
					<!-- 如果是酒店预订的话  -->
					<c:if test="${entity.type == 2 }">
						<tr>
							<th colspan="5">酒店信息</th>
						</tr>
						<tr class="info">
							<td>入店时间</td>
							<td>离店时间</td>
							<td>可否加床</td>
							<td>加床价格</td>
							<td>是否接待外宾</td>
						</tr>
						<tr >
							<td><fmt:formatDate value="${entity.hotelInTime }" pattern="HH:mm"/></td>
							<td><fmt:formatDate value="${entity.hotelOutTime }" pattern="HH:mm"/></td>
							<td>${entity.hotelCanAddBed==1?'是':'否'  }</td>
							<td>${entity.hotelAddBedPrice }</td>
							<td>${entity.hotelForeigned ==1?'是':'否' }</td>
						</tr>	
						<tr class="info">
							<td>可否携带宠物</td>
							<td>是否需要押金</td>
							<td>酒店星级</td>
							<td>是否精选</td>
							<td>精选排序</td>
						</tr>
						<tr >
							<td>${entity.hotelPeted==1?'是':'否'  }</td>
							<td>${entity.hotelPledge ==1?'是':'否' }</td>
							<td>${entity.hotelLevel  }</td>
							<td>${entity.wellChosened==1?'是':'否' }</td>
							<td>${entity.wellChosenSort }</td>
						</tr>			
					</c:if>
					
					<tr>
						<th colspan="5"><h5 class="text-danger">实名认证信息</h5></th>
					</tr>
					
					<c:if test="${empty  auth}">
						<tr class="">
							<td colspan="5">未提交认证信息</td>
						</tr>
					</c:if>
					
					<c:if test="${not empty auth }">
						<tr class="danger">
							<td>身份证</td>
							<td>真实姓名</td>
							<td>性别</td>
							<td>名族</td>
							<td>出生年月</td>
						</tr>
						<tr >
							<td>${auth.idNumber }</td>
							<td>${auth.readName }</td>
							<td><span data-random-label="${auth.gender}">${auth.gender ==1?'男':(auth.gender==2?'女':'保密') }</span></td>
							<td>${auth.nation }</td>
							<td><fmt:formatDate value="${auth.birthday}" pattern="yyyy-MM-dd"/></td>
						</tr>
						
						<tr class="danger">
							<td>提交审核时间</td>
							<td>失效时间</td>
							<td colspan="3">身份证地址</td>
						</tr>
						
						<tr>
							<td><fmt:formatDate value="${auth.createTime}" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${auth.expiryDate}" pattern="yyyy-MM-dd"/></td>
							<td colspan="3">${auth.houseAddress }</td>
						</tr>
					
					</c:if>
					
					
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
	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
