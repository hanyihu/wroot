;
(function($, $nojs) {
	if(!$ || !$nojs) {
		try {
			console.warn("jQuery or $nojs is not define ");
		} catch(e) {
			console.warn("what happend ");
		}
	}
	
	$nojs.attachment = {//更换img源
	    enable: function () {
	        return !!fileprefix;
	    },
	    selector: "img[data-attachment]",
	    init: function (context) {
	        var module = this;
	        $(module.selector, context).each(function () {
	            var $img = $(this);
	            var src = $img.attr("data-attachment");
	            if (src) {
	                if (/^(http|https|ftp):\/\/.*/.test(src)) {
	                    $img.attr("src", src);
	                } else {
	                    $img.attr("src", fileprefix + "/attachment/ajax/visit/"+ src);
	                }
	            }
	        });
	    }
	};
	
	//单个文件上传
	/*
	 * 修改页面的回显
	 * add：显示出进度条-label   是否需要验证-->submit
	 * done:提示error||success  preview  bind-value hide progress and label
	 * 
	 */	
	$nojs.singleFileupload = {
        enable: function () {
            return !!$.fn.fileupload;
        },
        selector: ".fileupload:file:not([multiple])",
        progress: {
            $progress: null,
            $progressBar: null,
            $progressText: null,
            setValue: function (value) {
                this.$progressBar.css("width", value + "%");
                this.$progressText.text(value + "%");
            }
        },
        init: function (context) {
            var module = this;
            $(this.selector, context).each(function (i, fileInput) {
                var $progress = $('<div class="progress progress-striped"><div class="progress-bar" style="width:0%"><span class="sr-only">0%</span></div></div>');
                var $label = $('<p class="label"></p>');
                var $fileInput = $(fileInput);
                $fileInput.attr("name", "upfile");
                $fileInput.after($progress);
                $fileInput.after($label);
                $progress.hide();
                var progress = {
                    $progress: $progress,
                    $progressBar: $progress.children(),
                    $progressText: $progress.children().children()
                };
                $fileInput.data("nojs.fileupload.progress", $.extend({}, module.progress, progress));
                $fileInput.data("nojs.fileupload.$statusBar", $label);

                var $form = $fileInput.parents("form");
                
                var $displayId = $("[name=" + $fileInput.data("displayId") + "]", $form);
                $fileInput.data("nojs.fileupload.display.$id", $displayId);
                var id = $displayId.val();	
                //回显
                var needPreview = $fileInput.data("preview");
                if (needPreview === true) {
                    var $preview = $('<img class="preview"/>');
                    $fileInput.after($preview);
                    
                    $fileInput.data("nojs.fileupload.$preview", $preview);
                    
                    if (id) {
                        $preview.attr("src", fileprefix + "/attachment/ajax/visit/"+ id);
                    } else {
                        $preview.hide();
                    }
                }

                $fileInput.fileupload({
                    url: $fileInput.data("url") || (ctx + "/ajax/upfile"),
                    replaceFileInput: false,
                    dataType: 'json',
                    formData: null,
                    progressall: function (e, data) {
                        var progress = parseInt(data.loaded / data.total * 100, 10);
                        $(this).data("nojs.fileupload.progress").setValue(progress);
                    },
                    add: function (e, data) {
                        data.fileInput.data("nojs.fileupload.$statusBar").text('Uploading...');
                        data.fileInput.data("nojs.fileupload.progress").setValue(0);
                        data.fileInput.data("nojs.fileupload.progress").$progress.show();
                        var validator = data.fileInput.parents("form").data("validator");
                        if (validator) {
                            if (!validator.element(data.fileInput[0])) {
                                return false;
                            }
                        }
                        data.submit();
                    },
                    done: function (e, data) {
                        var result = data.result;
                        if (result.code != 0) {
                            if (result.message) {
                                window.alert("文件上传失败！" + result.message);
                            } else {
                                window.alert("文件上传出错了！");
                            }
                            return;
                        }
                        data.fileInput.trigger("uploadSuccess", result);
                        var $preview = data.fileInput.data("nojs.fileupload.$preview");
                        if ($preview) {
                            $preview.show();
                            $preview.attr("src", fileprefix + "/attachment/ajax/visit/"+ result.id);
                        }

                        var $displayId = data.fileInput.data("nojs.fileupload.display.$id");
                        
                        $displayId.val(result.id);
                        var validator = $displayId.parents("form").data("validator");
                        if (validator) {
                            validator.element($displayId[0]);
                        }

                        data.fileInput.data("nojs.fileupload.$statusBar").hide();
                        data.fileInput.data("nojs.fileupload.progress").$progress.hide();
                    }
                });
            });
        }
    };
	/**
	 * 多文件上传
	 */
    $nojs.fileuploadMultiple = {
        enable: function () {
            return !!$.fn.fileupload;
        },
        selector: ".fileupload:file[multiple]",
        options: {
            showProgress: true,
            showLabel: true,
            showPreview: true,
            schema: "string",
            onlyShowPreview: false,	
        },
        event: ["addfile.$nojs-fileupload", "removefile.$nojs-fileupload"],
        $template: {
            $progress: $('<div class="progress progress-striped"><div class="progress-bar" style="width:0%"><span class="sr-only">0%</span></div></div>'),
            $label: $('<p class="label"></p>'),
            $preview: $('<div class="clearfix preview"></div>'),
            $previewItem: $('<div class="clearfix preview-item"><button type="button" class="close">&times;</button><img class="img-responsive"/></div>'),
            $onlyPreviewItem: $('<div class="clearfix preview-item"><img class="img-responsive amplifier"/></div>')
            
        },
        progress: function ($progress) {
            this.$progress = $progress;
            this.setValue = function (value) {
                this.$progress.show();
                var $progressBar = this.$progress.find(".progress-bar");
                $progressBar.css("width", value + "%");
                $progressBar.children().text(value + "%");
            };
            this.show = function () {
                this.setValue(0);
                this.$progress.show();
            };
            this.hide = function () {
                this.$progress.hide();
            };
        },
        label: function ($label) {
            this.$label = $label;
            this.setText = function (txt) {
                this.$label.text(txt);
                this.$label.show();
            };
            this.show = function () {
                this.$label.show();
            };
            this.hide = function () {
                this.$label.hide();
            };
        },
        preview: function ($preview, options) {
            this.options = options;
            this.$preview = $preview;
            this.addImage = function (id) {
                var preview = this;
                var $previewItem = $nojs.fileuploadMultiple.$template.$previewItem.clone();
                
                /* 只是单纯的显示**/
                if(options.onlyShowPreview){
                	$previewItem = $nojs.fileuploadMultiple.$template.$onlyPreviewItem.clone();
                }
                
                
                $previewItem.data("value", id);
                $previewItem.attr("id", id);
                var $img = $previewItem.find("img");
                $img.attr("src", this.getPreviewUrl(id));
                $img.attr("title", "attachment-" + id);
                if (preview.options.previewWidth) {
                    $img.css("width", preview.options.width);
                }
                if (preview.options.previewHeight) {
                    $img.css("height", preview.options.height);
                }
                $previewItem.find("button.close").click(function () {
                    preview.options.$element.trigger("removefile", id);
                });
                this.$preview.append($previewItem);
                return $previewItem;
            };
            this.removeImage = function (id) {
                this.$preview.find("#" + id).remove();
            };

            this.getPreviewUrl = function (id) {
                return fileprefix + "/attachment/ajax/visit/"+ id;
            };
        },
        schema: {
            string: {
                init: function ($value, preview) {
                    var originValue = $value.val();
                    if (originValue) {
                        var values = originValue.split(",");
                        for (var i = 0; i < values.length; i++) {
                            preview.addImage(values[i]);
                        }
                    }
                },
                addValue: function ($value, value, preview) {
                    var originValue = $value.val();
                    if (originValue) {
                        $value.val(originValue + "," + value);
                    } else {
                        $value.val(value);
                    }
                    if ($value.parents("form").data("validator")) {
                        $value.parents("form").validate().element($value[0]);
                    }
                    preview.addImage(value);
                },
                removeValue: function ($value, value, preview) {
                    var originValue = $value.val();
                    if (originValue) {
                        var pieces = originValue.split(",");
                        var newValue = "";
                        for (var i = 0; i < pieces.length; i++) {
                            var cur = pieces[i];
                            var valueStr = value + "";

                            if (cur === valueStr) {
                                continue;
                            }
                            newValue += (pieces[i] + ",");
                        }
                        if (newValue.length) {
                            newValue = newValue.substr(0, newValue.length - 1);
                        }
                        $value.val(newValue);
                    }
                    preview.removeImage(value);
                }
            }
        },
        init: function (context) {
            var module = this;
            $(this.selector, context).each(function (i, fileInput) {
                var $fileInput = $(fileInput);
                $fileInput.attr("name", "upfile");

                var opt = $.extend({}, module.options, $fileInput.data());
                opt.$element = $fileInput;
                var progress = {
                    setValue: function () {
                    }, show: function () {
                    }, hide: function () {
                    }};
                if (opt.showProgress) {
                    var $progress = module.$template.$progress.clone();
                    $fileInput.after($progress);
                    $progress.hide();
                    progress = new module.progress($progress);
                }
                $fileInput.data("nojs.fileupload.progress", progress);

                var label = {setText: function () {
                    }, show: function () {
                    }, hide: function () {
                    }};
                if (opt.showLabel) {
                    var $label = module.$template.$label.clone();
                    $fileInput.after($label);
                    $label.hide();
                    label = new module.label($label);
                }
                $fileInput.data("nojs.fileupload.label", label);

                var preview = {addImage: function () {
                    }, removeImage: function () {
                    }};
                if (opt.showPreview) {
                    var $preview = module.$template.$preview.clone();
                    $fileInput.after($preview);
                    preview = new module.preview($preview, opt);
                }
                $fileInput.data("nojs.fileupload.preview", preview);

                var $form = $fileInput.parents("form");
                var $displayId = $("[name=" + $fileInput.data("displayId") + "]", $form)

                var schema = module.schema[opt.schema];
                if (!schema) {
                    console.warn("nojs.fileupload dosn't recognize the schema:" + opt.schema);
                    return;
                }
                schema.init($displayId, preview);
                $fileInput.on("addfile", function (event, value) {
                    schema.addValue($("[name=" + $fileInput.data("displayId") + "]", $form), value, preview);
                });
                $fileInput.on("removefile", function (event, value) {
                    schema.removeValue($("[name=" + $fileInput.data("displayId") + "]", $form), value, preview);
                });

                $fileInput.fileupload({
                    url: $fileInput.data("url") || (ctx + "/ajax/upfile"),
                    replaceFileInput: false,
                    dataType: 'json',
                    sequentialUploads: true,
                    formData: null,
                    progressall: function (e, data) {
                        var progressValue = parseInt(data.loaded / data.total * 100, 10);
                        progress.setValue(progressValue);
                    },
                    add: function (e, data) {
                        var validator = data.fileInput.parents("form").data("validator");
                        if (validator) {
                            if (!validator.element(data.fileInput[0])) {
                                return false;
                            }
                        }
                        label.setText('Uploading...');
                        progress.show();
                        data.submit();
                    },
                    done: function (e, data) {
                        var result = data.result;
                        if (result.code < 0) {
                            if (result.message) {
                                window.alert("文件上传失败！" + result.message);
                            } else {
                                window.alert("文件上传出错了！");
                            }
                            return;
                        }
                        data.fileInput.trigger("addfile", result.id);

                        label.hide();
                        progress.hide();
                    }
                });
            });
        }
    };

})(jQuery, $nojs);