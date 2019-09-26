<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
<%@include file="/page/jspf/head.jspf"%>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
										
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">手机号*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="mobile" name="mobile" value="${ entity.mobile }" 
						placeholder="请输入手机号">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">密码*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="password" name="password" value="${ entity.password }" 
						placeholder="请输入密码">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">昵称*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="nickname" name="nickname" value="${ entity.nickname }" 
						placeholder="请输入昵称">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">1-男 2-女 0-保密*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="gender" name="gender" value="${ entity.gender }" 
						placeholder="请输入1-男 2-女 0-保密">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">生日*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="birthday" name="birthday" value="${ entity.birthday }" 
						placeholder="请输入生日">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">家乡-省id*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="homeProvince" name="homeProvince" value="${ entity.homeProvince }" 
						placeholder="请输入家乡-省id">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">家乡-市id*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="homeCity" name="homeCity" value="${ entity.homeCity }" 
						placeholder="请输入家乡-市id">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">常住地-省id*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="liveProvince" name="liveProvince" value="${ entity.liveProvince }" 
						placeholder="请输入常住地-省id">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">常住地-市id*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="liveCity" name="liveCity" value="${ entity.liveCity }" 
						placeholder="请输入常住地-市id">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">头像-对应附件表id*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="headpic" name="headpic" value="${ entity.headpic }" 
						placeholder="请输入头像-对应附件表id">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">第三方登陆方式 1-微信 2-qq 3-支付宝*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="thirdPartyType" name="thirdPartyType" value="${ entity.thirdPartyType }" 
						placeholder="请输入第三方登陆方式 1-微信 2-qq 3-支付宝">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">第三方登陆id*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="thirdPartyUid" name="thirdPartyUid" value="${ entity.thirdPartyUid }" 
						placeholder="请输入第三方登陆id">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">我的推荐二维码*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="recommendQcodePath" name="recommendQcodePath" value="${ entity.recommendQcodePath }" 
						placeholder="请输入我的推荐二维码">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">我的推荐地址*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="recommendUrl" name="recommendUrl" value="${ entity.recommendUrl }" 
						placeholder="请输入我的推荐地址">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">我是被谁推荐的*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="recommendId" name="recommendId" value="${ entity.recommendId }" 
						placeholder="请输入我是被谁推荐的">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">是否可用*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="enabled" name="enabled" value="${ entity.enabled }" 
						placeholder="请输入是否可用">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">创建时间*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="createTime" name="createTime" value="${ entity.createTime }" 
						placeholder="请输入创建时间">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">手机设备号*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="mobileCode" name="mobileCode" value="${ entity.mobileCode }" 
						placeholder="请输入手机设备号">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">1-用户 2-商家(商家必定是用户)*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="type" name="type" value="${ entity.type }" 
						placeholder="请输入1-用户 2-商家(商家必定是用户)">
			</div>
			<div class="col-sm-4  help-inline form-control-static"></div>
		</div>
		
								
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">用户积分*</label>
			<div class="col-sm-6 ">
				<input type="text" class="form-control" id="score" name="score" value="${ entity.score }" 
						placeholder="请输入用户积分">
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
