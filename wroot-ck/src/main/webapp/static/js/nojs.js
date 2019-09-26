(function(windom, $){
	var $application = function(context) {
		for(var name in $application) {
			var app = $application[name];
			if($.isFunction(app.enable) ? app.enable() : app.enable === true) {
				app.init(context);
			}
		}
	};
	
	var $nojs = $application;
	//1  测试
	$nojs.test = {
		enable : function(context){
			return true;
		},
		selector:".test",
		init: function(context){
			$(this.selector, context).each(function(i, t){
				$(t).append("<h1>这是一个测试</h1>")
			});
		}
	};
	
	//2 UI-tabs
	$nojs.tabs = {
		enable : function(){
			return !!$.ui;
		},
		selector : ".ui-tabs",
		init: function(context) {
			$(this.selector, context).tabs();
		}
	};
	
	//3 UI-button
	$nojs.button = {
		enable : function(){
			return !!$.ui;
		},
		selector : ".ui-button",
		init : function(context) {
			$(this.selector, context).button();
		}
	};
	
	
	$nojs.ueditor = {//初始化百度富文本编辑器  VIC  
		enable: function(){
			return !!windom.UE;
		},
		selector :"script.u-editor",
		options: {//初始化的一些参数 尚未想好用哪些
			initialFrameWidth : null
	    },
		init: function(context) {
			var module = this;
			$(this.selector, context).each(function(i, script){
				var $script = $(this);
				var id = $script.attr("id");
				if(!id) {
					id = i +"ueditor";
					$script.attr("id", id);
				}
				var dataOpt = $script.data();
				if(dataOpt && dataOpt.toolbars) {//toolbars 以逗号分隔
					dataOpt.toolbars = dataOpt.toolbars.split(",");
				}
				var opt = $.extend({}, module.options, dataOpt);//可自定义传入一些参数
				var ue = UE.getEditor(id, opt);
				$script.data("ueditor", ue);
			});
		}
    };

	//导航点击事件
	$nojs.navClick = {
		enable : true,
		selector: ".mine-nav li:not(:last)",
		init:function(context){
			$(this.selector, context).click(function(){
				$(this).addClass("active").siblings("li").removeClass("active");
			});
		}
	};
	
	//日历控件
	 $nojs.datetimepicker = {
        enable: function () {
            return !!$.fn.datetimepicker;
        },
        options: {
            autoclose: true,
            todayBtn: true,
            language: "zh-CN"
        },
        selector: ".datetime",
        initRange: function (option, $input, context) {
            function _bindDateTarget(opt) {
                var method = {
                    "startDateTarget": "setStartDate",
                    "endDateTarget": "setEndDate"
                };
                if (option[opt] === 'now') {
                    $input.datetimepicker(method[opt], new Date());
                    return;
                }
                var $target = $(option[opt], context);
                if (!$target.length) {
                    $target = $("[name=" + option[opt] + "]", context);
                }
                $target.on("changeDate", function (ev) {
                    $input.datetimepicker(method[opt], this.value);
                });
            }
            if (!option.startDateTarget && !option.endDateTarget) {
                return;
            }
            var $form = $input.closest("form");
            if ($form.length) {
                context = $form;
            }

            if (option.startDateTarget) {
                _bindDateTarget("startDateTarget");
            }
            if (option.endDateTarget) {
                _bindDateTarget("endDateTarget");
            }
        },
        init: function (context) {
            var module = this;
            $(this.selector, context).each(function (i, dt) {
                var $input = $(dt);
                var option = $.extend({}, module.options, $input.data());
                $input.datetimepicker(option);
                module.initRange(option, $input, context);

                $input.next("span").click(function () {
                    $input.focus();
                });
            });
        }
    };
	
	//日历
	$nojs.timeCalendar = $.extend(true, {}, $nojs.datetimepicker,{selector: "div.calendar"});
	
	
	//form 表单验证
	$nojs.validation = {
		enable :  function(){
			return !!$.fn.validate;
		},
		selector : "form.validate",
		init: function(context){
			var $forms = $(this.selector, context);
			var option = {//验证的一些设置
				ignore:".novalidate",//对某些元素不验证
				errorClass:"help-inline",//默认 "error"。指定错误提示的 css 类名
				errorElement:"label",//指定使用什么标签标记错误。
				errorPlacement: function(error, element){//跟一个函数，可以自定义错误放到哪里。
					 $(element).closest('.form-group').find(".form-control-static").append(error);
				},
				highlight: function (element) {//可以给未通过验证的元素加效果、闪烁等。
                    $(element).closest('.form-group').addClass('has-error');
                },
                unhighlight: function (element) {
                    $(element).closest('.form-group').removeClass('has-error');
                }
			};
			$forms.each(function(){
				var $form = $(this);
				var opts = $.extend(true,{}, option, $form.data())
				console.info(JSON.stringify(opts));
				$form.validate(opts);
			});
			
		}
	};
	
	//lookup 表单的重置
	$nojs.lookupReset = {
		enable : true,
		selector : {
			form:"form.lookup",//要重置的表单
			reset:"button.reset",//触发的按钮
			control:":input"//要重置的对象
		},
		init: function(context){
			var module = this;
			var $form = $(module.selector.form, context);
			$(module.selector.reset, $form).on("click", function(){
				$(module.selector.control, $form).val("");
				$form.submit();
			});
		}
	};
	

	//select值绑定
	$nojs.selectValueInitialization = {
        enable: true,
        selector: "select[data-value]",
        event: "$nojs-select-initialize",
        init: function (context) {
            $(this.selector, context).on(this.event, function (event) {
                var $select = $(this);
                var value = $select.data("value");
                if (value !== undefined ) {//把相应的值选中
                    $select.children("option:selected").prop("selected", false);
                    if(value !==''){
                    	$select.children("option[value=" + value + "]").prop("selected", true);
                    }
                }
                $select.trigger("change");
            }).trigger(this.event);
        }
    };
	
	//分页插件
	$nojs.pagination  = {
		enable: function(){
			return !!$.fn.pager;
		},
		selector: "form ul.pagination",
		init: function(context){
			$(this.selector, context).pager();
		}
			
	};
	
	//提交表单动作 包括get  post  back
	$nojs.actions = {
		enable : true,
		init : function(context){
			for(var name in this) {
				var module = this[name];
				if(module && module.init) {
					module.init(context);
				}
			}
		},
		get:{//get请求操作
			selector:".action-get",
			option:{
				param :".action-param"//请求参数
			},
			init: function(context){
				var module = this;
				$(this.selector, context).click(function(){
					var $btn = $(this);
					//提示信息
					var confirm = $btn.data("confirm");
					/*
					 *  换成jQuery UI的dialog JS代码参见common.js
					 */  
					if(confirm) {
						if(!!$.ui && !!window.$common) {//如果引入了jqueryUI 和common.js
							$common.tipDialog("确认操作", confirm, getOperation);
							return false;
						}else {
							if(!windom.confirm(confirm)){
								return false;
							}
						}
					}
					
					getOperation();
					
					function getOperation(){
						
						//获取href
						var href = $btn.data("href");
						if(!/^\//.test(href)) {//如果不是以斜杠开头的href 则加上前缀
							var prefix = windom.location.href;
							var index = prefix.indexOf("?");
							if(index != -1) {
								prefix = prefix.substring(0, index);
							} 
							href = prefix + href;
						}
						//附加参数 包含在这 标签内部的 标签：<i class="action-param" data-key="key" data-value="val" ></i>
						var $params = $(module.option.param, $btn);
						if($params.length) {
							href +="?1=1";
							$params.each(function(){
								href += "&" + $(this).data("key") + "=" + encodeURIComponent($(this).data("value"));
							});
						}
						
						//此get请求是否是提交表单
						var formSelector = $btn.data("form");
						if(!formSelector) {
							window.location = href;
						}else{
							var $form;
							
							if($.type(formSelector) == "string") {
								$form = $(formSelector, context);
							}else {
								$form = $btn.parents("form");
							}
							if(!$form.length) {
								console.error("找不到绑定的表单");
								return;
							}
							$form.attr("action", href).attr("method", "get").submit();
						}
						return false;
						
					}
					return false;
				});
			}
		},
		post:{//post 请求操作
			selector:".action-post",
			option:{
				param :".action-param"//请求参数
			},
			init: function(context){
				var module = this;
				$(this.selector, context).click(function(){
					var $btn = $(this);
					//提示信息
					var confirm = $btn.data("confirm");
					if(confirm) {
						if(!!$.ui && !!window.$common) {//如果引入了jqueryUI 和common.js
							$common.tipDialog("确认操作", confirm, postOperation);
							return false;
						}else {
							if(!windom.confirm(confirm)){
								return false;
							}
						}
					}
					
					postOperation();
					
					function postOperation(){
						//获取表单
						var $form = $btn.parents("form");
						if($btn.data("form")) {
							$form = $($btn.data("form"), context);
						}
						if(!$form.length) {
							$form = $('<form></form>')
						}
						$form.attr("method", "post");
						//获取href
						var href = $btn.data("href");
						if(href) {
							$form.attr("action", href);
						}
						//附加参数 包含在这 标签内部的 标签：<i class="action-param" data-key="key" data-value="val" ></i>
						var $params = $(module.option.param, $btn);
						$params.each(function () {
							var $param = $(this);
							var key = $param.data("key");
							var value = $param.data("value");
							if(key && value) {
								var $input = $("<input type=\"hidden\" class=\"help\"/>");
								$form.append($input);
								$input.attr("name", key);
								$input.val(value);
							}
	                    });
	                    $form.submit();
	                    $(".help:hidden", $form).remove();
						
					}
				
					
				});
			}
		},
		back: {//返回请求操作 只返回 不刷新
			selector:".action-back",
			init: function(context){
				$(this.selector, context).click(function(){
					console.info("back");
					windom.history.back();//返回
					//window.history.go(-1);//返回+刷新
				});
			}
		}
	};
	//CheckBox 和全选/取消 和相关批量操作按钮 的关系建立
	$nojs.checkboxBinding = {
		enable : true,	
		selector: "[data-member]",
		$member: function($leader, context) { //获取成员
			var memberName = $leader.data("member"); //成员name
			return $("input:checkbox[name=" + memberName + "]:not(:disabled)", context);
		},
		$relate: function($leader, $member, context) { //相关操作按钮是否可操作
			var memberName = $leader.data("member"); //成员name
			var $relate = $("[data-checkbox-required=" + memberName + "]", context)
			if($relate.length !== 0) {
				$relate.prop("disabled", $member.filter(":checked").length === 0);
			}
		},
		init: function(context) {
			var module = this;
			$(this.selector, context).each(function() {
				var $leader = $(this);
				var $member = module.$member($leader, context);
				$leader.change(function() { //点击全选或者取消
					$member.prop("checked", $leader.prop("checked"));
					module.$relate($leader, $member, context);
				});
				$member.change(function() { //点击成员 若全选则全选checkbox选中
					$leader.prop("checked", $member.length == $member.filter(":checked").length);
					module.$relate($leader, $member, context);
				});
				module.$relate($leader, $member, context);
			});
		}
	};
	
	//chosen  下拉框
	$nojs.chosen = {
		enable: function(){
			return !!$.fn.chosen;
		},
		selector: "select.chosen",
		option :{
			no_results_text: "没有找到",//找不到结果时候显示的内容
    		allow_single_deselect: true,//是否允许取消选择
    		max_selected_options:12//当select为多选时，最多选择个数
		},
		init: function(context){
			var module = this;
			$(module.selector, context).each(function(){
				var $select = $(this);
				var opts = $.extend(module.option, $select.data());
				$select.chosen(opts);
			});
		}
	};
	// table  父子结构
	$nojs.subTable = {
		enable : true,	
		selector: "table.sub tbody",
		opts: {
			canClosed: '<span class="glyphicon glyphicon-minus pull-right"></span>',
			canOpened: '<span class="glyphicon glyphicon-plus pull-right"></span>'
		},
		init: function(context) {
			var module = this;
			var $table = $(this.selector, context);
			//隐藏子类  data-parent!=0
			$("tr[data-parent!=0]", $table).hide();
			var $parent = $("tr[data-parent=0]", $table);
			$parent.each(function() {
				var $p = $(this);
				var $open = $(module.opts.canOpened);
				$(" td:first", $p).append($open);
				var $sub = $("tr[data-parent=" + $p.data("value") + "]", $table);
				$p.after($sub);
				$p.data("son", $sub);
				$open.on("click", function() {
					$p.data("son").toggle();
					$(this).toggleClass("glyphicon-minus","glyphicon-plus" );
				})
			});
		}	
	};
	
	// 多级联动
	$nojs.linkage = {
			enable : true,	
			selector:"[data-linkage=true]",
			init: function(context){
				$(this.selector, context).each(function(){
					var $parentSelect = $(this);
					var url = $parentSelect.data("linkageUrl");
					var target =  $parentSelect.data("linkageTarget")
					if(url && target &&  $(target, context).size() > 0){
						var $target = $(target, context);
						var $first = $("option", $target);
						$parentSelect.on("change", function(){
							
							var pId = $parentSelect.val();
							$target.html($first);
							if(!pId) return;
							
							$.ajax(url, {
								type: "post",
								dataType: "json",
								data:{id: pId},
							}).done(function(result){
								if(result && result.code == 0) {
									var data = result.data;
									var html;
									for(var i = 0; i< data.length; i++) {
										html +='<option value="' +data[i].id+ '">' + data[i].name + '</option>';
									}
									$target.append(html);
									$target.trigger("$nojs-select-initialize");//触发目标子类的选择
									$target.trigger("change")
								}
							});
						}).trigger("change");
						
						
					}
				});
				
			}
		};
	
	//发送短信验证码
	$nojs.smscode = {
	        enable: function () {
	            return !!$.timer;
	        },
	        selector: "button[data-smscode],a[data-smscode]",
	        init: function (context) {
	            var thisModule = this;
	            //短信验证码
				

	            $(document).on("click", thisModule.selector, function () {
	                var $this = $(this);
	                var originalCaption = $this.text();
	                var $caption = $("<span></span>");
	                var remain = 60;
	                var $remain = $("<span></span>").text(remain);
	                $caption.append($remain).append(" 秒后才可重新获取");
	                $this.html($caption);
	                var timer = $.timer(function () {
	                    if (remain > 0) {
	                        $remain.text(--remain);
	                    } else {
	                        timer.stop();
	                        $(thisModule.selector, context).prop("disabled", false);
	                        $this.text(originalCaption);
	                    }
	                }, 1000, true);
	                $(thisModule.selector, context).prop("disabled", true);
	            });
	        }
	    };
		
	
	windom.$nojs = windom.$application = $application;
	
})(window, jQuery);
$(document).ready(function(){
	window.$nojs(document);
});
