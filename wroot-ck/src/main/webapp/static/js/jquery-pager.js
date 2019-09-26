/** jQuery 同步分页插件* Version 1.0   by   vic
 * Example at: .... 
 * licenses:
 * Usage:$("").pager(opt); 当前元素应该在form中
 */
/**
 * start:
 * 首页 前一页 （页码list） 下一页 末页
 */
;(function($){
	var opt = {
		size:10,//每页默认数量
		total:100,//总数量
		page:1,//当前页码
		pageMax:7,//最多显示多少个页码
		onlyPage:false,//是否只计算显示页码
		disabledClass:"disabled",
		lookup:"form.lookup",
		totalDisplay:'span.total',//总页数显示
		names:{//要提交的表单属性
			currentPage:"page",
			currentPageSize:"pageSize"
			
		},
		template:{
			firstPage:'<li class="first-page"><span>首页</span></li>',
			lastPage:'<li class="last-page"><span>末页</span></li>',
			prevPage:'<li class="prev-page"><span>上一页</span></li>',
			nextPage:'<li class="next-page"><span>下一页</span></li>',
			page:'<li class="next-page"><span>下一页</span></li>',
			currentPage:'<li class="active"><span>当前页</span></li>',
			input:'<input type="hidden" />'
		}
	};
	
	$.fn.extend({
		"pager": function(option){
			var $this = this;
			var opts = $.extend(true, opt, $this.data(), option);
			var $form = $this.parents("form");
			var cachePageForward = "page_forward";
			
			var caclIndex =  function(){//计算当前页面的页码显示情况，应保持当前页码显示在中间位置
				var pageStart;//开始页码
				var pageEnd;//结束页码
				var pageTotal = Math.ceil(opts.total/opts.size);//总页数
				var halfPageMax = Math.ceil(opts.pageMax/2);//计算出页码的中间位置
				var curPage = opts.page;
				if(pageTotal < opts.pageMax){//总页码小于pageMax 则从第一页开始显示到全部
					pageStart = 1;
					pageEnd = pageTotal;
				}else if(pageTotal >  opts.pageMax && curPage > pageTotal - halfPageMax ){//总页码大于pageMax且当前页码加halfPageMax大于pageTotal则pageEnd 应该等于pageTotal
					pageEnd = pageTotal;
					pageStart = pageTotal - opts.pageMax+1;
				}else if(pageTotal >  opts.pageMax && curPage <halfPageMax){
					pageStart = 1;
					pageEnd = opts.pageMax;
				}else {
					pageStart = opts.page -halfPageMax +1;
					pageEnd = pageStart + opts.pageMax-1;
				}
				return {
					pageStart:pageStart,
					pageEnd:pageEnd,
					pageTotal:pageTotal
				};
			};////计算当前页面的页码显示情况完毕
			
			var clickPage =  function(){//点击页码的操作
				var $this = $(this);
				var pageNum = $this.data(cachePageForward);
				var $pageNumInput = $("[name=" + opts.names.currentPage + "]", $form);
				if($pageNumInput.length ===0) {
					$pageNumInput = $(opts.template.input).clone().attr("name", opts.names.currentPage);
					$form.append($pageNumInput);
				}
				$pageNumInput.val(pageNum);
				$form.submit();
			};
			
			//是否不需要绑定点击事件 
			if(opts.onlyPage === true) {
				clickPage = $.noop();
			}
			
			$form.submit(function(){//提交页码表单操作
				
                var $this = $(this);

				var $lookupForm = $(opts.lookup);
				if($lookupForm.length === 0) {//没有查询表单 则直接提交
					return true;
				}
				//此处clone需注意，jQuery的clone 对于SELECT和TEXTAREA 不复制其属性值，故需要重写下JUQERY的clone() 或者直接_cloneOtherInput
				var $lookups = $(":input", $lookupForm).clone().removeAttr("id");
				_cloneOtherInput($lookupForm, $this);
				$this.prepend($lookups);
				this.submit();
				$lookups.remove();
				_removeOtherInput($this);
				return false;
				
                function _cloneOtherInput($searchForm, $pagerForm) {
                    var $select = $("select", $searchForm);
                    $select.each(function(i, select) {
                        var value = $(select).val();
                        var $help = $(opts.template.input).clone();
                        $help.attr("name", select.name)
                                .addClass(opt.disabledClass)
                                .val(value);
                        $pagerForm.prepend($help);
                    });
                }
                function _removeOtherInput($pagerForm) {
                    $("." + opts.disabledClass, $pagerForm).remove();
                }

				
				
				
			});
			//总条数显示位置
			$(opts.totalDisplay, $form).text(opts.total);
			
			/****************开始构建页码dom*******************/
			var pageIndex = caclIndex();
			if(pageIndex.pageTotal == 0) {
				return this;
			}
			
			//首页
			var $first = $(opts.template.firstPage).clone();
			if(opts.page ==1) {//当前页码是第一页 则 不可点击
				$first.addClass(opts.disabledClass);
			}else{
				$first.data(cachePageForward, 1).click(clickPage);
			}
			$this.append($first);
			
			//上一页
			var $prev = $(opts.template.prevPage).clone();
			if(opts.page ==1) {//当前页码是第一页 则 不可点击
				$prev.addClass(opts.disabledClass);
			}else{
				$prev.data(cachePageForward, opt.page-1).click(clickPage);
			}
			$this.append($prev);
			//中间的页码
			for(var i= pageIndex.pageStart;i<=pageIndex.pageEnd; i++){
				var $page;
				if(i == opts.page) {//当前页码 无需点击事件
					$page = $(opts.template.currentPage).clone();
					$page.find("span").text(i);
                    $this.append($page);
				}else {
					$page = $(opts.template.page).clone();
					$page.find("span").text(i);
					$page.data(cachePageForward, i).click(clickPage);
                    $this.append($page);
				}
			}
			
			//下一页
			var $next = $(opts.template.nextPage).clone();
			if(opts.page == pageIndex.pageEnd) {//当前页码是最后一页则 不可点击
				$next.addClass(opts.disabledClass);
			}else{
				$next.data(cachePageForward, opts.page+1).click(clickPage);
			}
			$this.append($next);
			
			//末页
			var $last = $(opts.template.lastPage).clone();
			if(opts.page == pageIndex.pageEnd) {//当前页码是最后一页则 不可点击
				$last.addClass(opts.disabledClass);
			}else{
				$last.data(cachePageForward, pageIndex.pageTotal).click(clickPage);
			}
			$this.append($last);
			
			
			
			
			
			/****************结束构建页码dom*******************/
			return this;
		}
		
	
	});
				
})(jQuery)
