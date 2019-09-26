;
(function($, $nojs) {
	if(!$ || !$nojs) {
		try {
			console.warn("jQuery or $nojs is not define ");
		} catch(e) {
			console.warn("what happend ");
		}
	}
	//单个文件上传
	/*
	 * 修改页面的回显
	 * add：显示出进度条-label   是否需要验证-->submit
	 * done:提示error||success  preview  bind-value hide progress and label
	 * 
	 */
	$nojs.singleFileupload = {
		enable: true,
		selector: ".fileupload:file:not(multiple)",
		progress: {
			$progress: $('<div class="progress progress-striped"><div class="progress-bar" style="width:20%"><span class="sr-only">0%</span></div></div>'),
			setValue: function(value) {
				this.$progress.children().css("width", value + '%');
				this.$progress.children().children().text(value + '%');
			}
		},
		label: '<div><span class="label label-warning">上传ing....</span></div>',
		init: function(context) {
			var module = this;
			$(this.selector, context).each(function() {
				var $fileInput = $(this);
				$fileInput.attr("name", "upfile");
				var progress = $.extend(true, {}, module.progress);;
				$fileInput.after(progress.$progress.hide());
				var $label = $(module.label);
				$fileInput.after($label.hide());

				$fileInput.data("fileupload.progress", progress);
				$fileInput.data("fileupload.label", $label);
				
				//回显
				var needPreview = true === $fileInput.data("preview");
				var $preview = $('<img class="preview"/>');
				var $form = $fileInput.parents("form");
				var $displayId = $("[name=" + $fileInput.data("displayId") + "]", $form);
				var id = $displayId.val();
				if(needPreview) {
					$fileInput.data("uploadfile.preview", $preview);
					$fileInput.after($preview);
					if(id) {
						$preview.attr("src", ctx + "/console/ajax/upfile/" + id);
					}else{
						$preview.hide();
					}
				}
				

				$fileInput.fileupload({
					url: $fileInput.data("url") || ctx +"/console/ajax/upfile",
					dataType: 'json',
					replaceFileInput: false,
					forceIframeTransport: false,
					formData: null,
					progressall: function(e, data) {
						var progress = parseInt(data.loaded / data.total * 100, 10);
						$(this).data("fileupload.progress").setValue(progress);
					},
					add: function(e, data) {
						var $fileInput = data.fileInput;
						$fileInput.data("fileupload.progress").$progress.show();
						$fileInput.data("fileupload.label").show();

						var validation = $fileInput.parents("form").data("validate");
						
						console.info($fileInput.parents("form"));
						console.info(validation);
						console.info(validation.element($fileInput[0]));
						if(validation && !validation.element($fileInput[0])) {
							return false;
						}
						data.submit();

					},
					done: function(e, data) {
						var result = data.result;
						if(result.code != 0) {
							if(result.message) {
								window.alert("上传失败 ：" + result.message);
							} else {
								window.alert("上传失败");
							}
							return;
						}
						var $fileInput = data.fileInput;
						var $preview = $fileInput.data("uploadfile.preview");
						if($preview) {
							$preview.attr("src", ctx + "/console/ajax/upfile/" + result.id).show();
						}
						$fileInput.data("fileupload.progress").$progress.hide();
						$fileInput.data("fileupload.label").hide();
						
					}
				});

			});

		}
	};

})(jQuery, $nojs);