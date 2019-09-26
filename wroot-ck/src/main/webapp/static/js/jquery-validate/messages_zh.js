/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
(function($) {
    $.validator.addMethod("mobile", function(value, element) {
        return this.optional(element) || /^1[3|4|5|7|8][0-9]\d{8}$/.test(value);
    }, "Please specify a valid mobile number");
    
    $.validator.addMethod("longitudeString", function(value, element) {
    	return this.optional(element) || /^(([0-9]+\.[0-9]{6}))$/.test(value);
    }, "请输入正确的经纬度");

    $.validator.addMethod("tencentqq", function(value, element) {
        return this.optional(element) || /^[1-9]\d{4,8}$/.test(value);
    }, "Please specify a valid tencent-qq number");
    $.validator.addMethod("phone", function(value, element) {
        return this.optional(element) || /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test(value);
    }, "Please specify a valid phone number");
    
    $.extend($.validator.messages, {
        required: "必选字段",
        remote: "请修正该字段",
        email: "请输入正确格式的电子邮件",
        url: "请输入合法的网址",
        date: "请输入合法的日期",
        dateISO: "请输入合法的日期 (ISO).",
        number: "请输入合法的数字",
        digits: "只能输入整数",
        creditcard: "请输入合法的信用卡号",
        equalTo: "请再次输入相同的值",
        accept: "请上传合法的文件",
        extension: "请上传合法的文件",
        maxlength: $.validator.format("请输入一个长度最多是 {0} 的字符串"),
        minlength: $.validator.format("请输入一个长度最少是 {0} 的字符串"),
        rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
        range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
        max: $.validator.format("请输入一个最大为 {0} 的值"),
        min: $.validator.format("请输入一个最小为 {0} 的值"),
        mobile: "手机号码格式不正确",
        tencentqq: "QQ号格式不正确",
        phone:"电话号码格式不正确"
    });
}(jQuery));