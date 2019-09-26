(function($) {

	/** 1 取值 设置值 类似VAL（） */
	$.fn.vald = function() {
		if (arguments.length) {
			_setValue(this, arguments[0]);
			return this;
		} else {
			return _getValue(this);
		}
		function _getValue($j) {
			if ($j.is(":input")) {
				return $j.val();
			} else if ($j.is("img")) {
				return $j.attr("src");
			} else {
				return $j.html();
			}
		}
		function _setValue($j, value) {
			if ($j.is(":input")) {
				$j.val(value);
			} else if ($j.is("img")) {
				$j.attr("src", value);
			} else {
				$j.html(value);
			}
		}
	};

	/** 2 根据name把设置值 */
	$.fn.bindData = function(json) {
		var $this = $(this);

		var isJosn = typeof (json) == "object"
				&& Object.prototype.toString.call(json).toLowerCase() == "[object object]"
				&& !json.length
		if (!isJosn)
			return this;
		$("[name]", $this).each(function() {
			var $ele = $(this);
			var key = $ele.attr("name");
			$(this).vald(json[key]);

		});
		return this;

	};

})(jQuery);

/** jQuery 的clone 丢失textarea 和select的值的问题的解决* */
(function(original) {
	jQuery.fn.clone = function() {
		var result = original.apply(this, arguments), 
			my_textareas = this.find('textarea').add(this.filter('textarea')), 
			result_textareas = result.find('textarea').add(result.filter('textarea')), 
			my_selects = this.find('select').add(this.filter('select')), 
			result_selects = result.find('select').add(result.filter('select'));
		for (var i = 0, l = my_textareas.length; i < l; ++i)
			$(result_textareas[i]).val($(my_textareas[i]).val());
		for (var i = 0, l = my_selects.length; i < l; ++i) {
			for (var j = 0, m = my_selects[i].options.length; j < m; ++j) {
				if (my_selects[i].options[j].selected === true) {
					result_selects[i].options[j].selected = true;
				}
			}
		}
		return result;
	};
})(jQuery.fn.clone);
