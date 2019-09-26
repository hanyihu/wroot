<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/page/jspf/prepare.jspf"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<%@include file="/page/jspf/head.jspf"%>
<%@include file="/page/jspf/console-timeline.jspf"%>
</head>
<body>
	<%@include file="/page/jspf/console-body-first.jspf"%>
	<form class="form-horizontal validate" role="form" method="post">
	<div class="form-group clearfix">
			<div class="col-sm-offset-5 col-sm-7">
				<button type="button" class="btn btn-info action-back">
					<i class="glyphicon glyphicon-repeat"></i> 返回
				</button>
			</div>
		</div>
		<div class="text-center">
			<h2>订单状态时间轴</h2>
		</div>
		
		 <div class="row">
            <div class="col-sm-offset-2 col-sm-6">
			
				<div class="VivaTimeline">
					<dl>
						<c:forEach items="${records }" var="record" varStatus="status">
							<dt>${record.date }</dt>
							<dd class="clearfix pos-${status.index%2==1?'left':'right' } ">
								<div class="circ"></div>
								<div class="time">${record.time }</div>
								<div class="events">
									<div class="events-header">${record.orderno }</div>
									<div class="events-body">
										<!-- row 可以多個 -->
										<div class="row">
											<div class="col-md-6 pull-left">
												<img class="events-object img-responsive img-rounded" src="" />
											</div>
											<div class="events-desc">${record.describe }</div>
										</div>
									</div>
									<div class="events-footer">
										<!-- footer -->
									</div>
								</div>
							</dd>
						</c:forEach>
					</dl>
					</div>
				</div>
			</div>


	</form>
	<%@include file="/page/jspf/console-body-last.jspf"%>
</body>
</html>
