<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><spring:message code="application.title" /></title>
<%@include file="/page/jspf/head.jspf"%>
<script type="text/javascript" src="${ctx }/static/js/echarts.min.js"></script> 
<script type="text/javascript">

	$(function(){
		var bar = ${barData};
		if(!bar) return;
		var myChart = echarts.init(document.getElementById('main'));
		var option = {
				title: {
					text: '流水统计'
				},
				tooltip: {
					trigger: 'axis',
					axisPointer: { // 坐标轴指示器，坐标轴触发有效
						type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				legend: {
			        data:bar.legend
			    },
				grid: {
					left: '3%',
					right: '4%',
					bottom: '3%',
					containLabel: true
				},
				xAxis: [{
					type: 'category',
					data: bar.xAxis
				}],
				yAxis: [{
					type: 'value'
				}],
				series: bar.series
			};
		
		myChart.setOption(option);
	});
	
</script>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<div class="row row-nocol">
		<form class="form-inline lookup" role="form" method="post">

			<div class="form-group">
				<label class="sr-only" for="lookup-startDate">开始时间</label> 
						<input  class="datetime form-control"  id="lookup-startDate" name="startDate" type="text" value="<fmt:formatDate value="${pager.lookup.startDate}" pattern="yyyy-MM-dd"/>" data-date-format="yyyy-mm-dd"  data-min-view="2"      
							data-end-date-target="lookup-endDate"	readonly placeholder="开始日期"  " />
			</div>
			<div class="form-group">
				<label class="sr-only" for="lookup-startDate">结束时间</label> 
				<div class="input-group">
						<input  class="datetime form-control"   id="lookup-endDate" name="endDate" type="text"value="<fmt:formatDate value="${pager.lookup.endDate}" pattern="yyyy-MM-dd"/>" data-date-format="yyyy-mm-dd"  data-min-view="2"    
					data-start-date-target="lookup-startDate"	readonly placeholder="结束日期"  /> 
					</div>
			</div>
			

			<div class="form-group form-btn-bar">
				<button type="submit" class="btn btn-primary btn-sm">
					<i class="glyphicon glyphicon-search"></i> 查询
				</button>
				<button type="button" class="btn btn-info btn-sm reset">
					<i class="glyphicon glyphicon-refresh"></i> 重置
				</button>
			</div>

		</form>
		<hr />
		<!--列表栏-->
		<form class="nocol">
			<div id="main" style="width: 800px;height:600px;"></div>
		</form>
	</div>
</body>
</html>
