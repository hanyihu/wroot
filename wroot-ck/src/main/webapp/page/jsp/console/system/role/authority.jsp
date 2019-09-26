<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>后台</title>
<%@include file="/page/jspf/head.jspf"%>
<%@include file="/page/jspf/console-ztree.jspf"%>
<script type="text/javascript">
ztreeSetting = {
		view:{
			showLine :false,
			selectedMulti : true,//设置是否允许同时选中多个节点
			showIcon: true
		},
		data : {
			simpleData : {
				enable : true,
				idKey: "id",
				pIdKey: "parentId",
				rootPId: "0"
			}
		},
		check: {
			enable: true,
			chkboxType: { "Y": "p", "N": "s" }
		},
		callback : {
		}
	};
	
	$(function(){
		
		
		//点击提交按钮前把数据组装好
		$("form").on("submit", function(){
			var $tree = $(".ztree").data("ztree");
			if(!$tree){
				alert("不存在的菜单树");
				return false;
			}
			var selectedNodes = $tree.getCheckedNodes();
			var menuIds = [];
			var btnIds = [];
			$.each(selectedNodes,function(i, node){
				console.info(node.role + " " + node.id);
				if(node.id.length > 2){
					if(node.role == 1){//菜单
						menuIds.push(node.id.substring(2));
					}else if(node.role ==2){//按钮
						btnIds.push(node.id.substring(2));
					}
				}
				
				
			});
			$('<input type="hidden" name="menuIds" value="' + menuIds.join(",")+ '"/>').prependTo($(this));
			$('<input type="hidden" name="btnIds" value="' + btnIds.join(",")+ '"/>').prependTo($(this));
			return true;
		});
		
	});
	
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
		<div class="form-group">
			<label for="firstname" class="col-sm-2 control-label">菜单</label>
			<div class="col-sm-6">
				<ul id="treeDemo" class="ztree" style="width: 100%;margin-bottom:20px" data-json=${treeJson }></ul>
			</div>
			<div class="col-sm-4 help-inline form-control-static"></div>
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
