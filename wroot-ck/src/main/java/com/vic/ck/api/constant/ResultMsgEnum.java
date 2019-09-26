package com.vic.ck.api.constant;
/**
 * 状态码说明
 * @author VIC
 *
 */
public enum ResultMsgEnum {

    SUCCESS(0, "操作成功"),
    FAIL(-1, "操作失败"),

	UNOPEN_CITY(701, "当前城市未开放"),
	SMSSEND_FAIL(702, "发送短信验证码失败"),
	INVALID_SMSCODE(703, "无效的短信验证码"),
	ALIPAY_AUTH_FAIL(704, "获取用户支付宝信息失败"),
	INVALID_PICCODE(705, "无效的图形码"),
	INVALID_OUTTIME(706,"图形码已失效"),

    EXISTED_USER(801, "当前手机号已注册"),
    NOTEXISTED_USER(802, "不存在的用户"),
    DISABLED_USER(803, "用户被禁用"),
    ERROR_PASSWORD(804, "密码错误"),
    NOTEXISTED_MERCHANT(805, "不存在的商户"),
    NOTEXISTED_CARD(806, "不存在的银行卡"),
    NOTEXISTED_ACTIVITY(807, "不存在的活动"),
    NOTEXISTED_ACTIVITY_YHQ(817, "活动还未开启或者已结束"),
    NO_MORE_LIMINT_ACTIVITY(808, "打折券被领完了"),
    NOTEXISTED_GROUPBUY(809, "不存在的团购"),
    NOTEXISTED_TICKET(810, "不存在的折扣券"),
    NOTEXISTED_ORDER(811, "不存在的订单"),
    NOTEXISTED_MALL_GOODS(812, "不存在的积分商品"),
    NOTEXISTED_HOTEL(812, "不存在的酒店房间"),
    NOTEXISTED_GROUP_TICKET(813, "不存在的团购券"),
    NOTEXISTED_REDPACKET(814, "不存在的话费红包"),

    BALANCE_NOT_ENOUGH(815, "余额不足"),
    ENTERPRISE_EXIST(816, "企业名称已存在"),


    ALREADY_SIGN(901, "已经签到，请不要重复签到"),
    ALREADY_COLLECT(902, "已经收藏，请不要重复收藏"),
    ALREADY_PRAISE(903, "已经点赞，请不要重复点赞"),
    ALREADY_PAYED(905, "订单当前状态不可变更支付方式"),
    ALREADY_USED_GROUP_TICKET(906, "团购券已使用"),
    ALREADY_RECEIVE_REDPACKET(907, "已领取红包，不可重复领取"),
    ALREADY_COMMENT(908, "已评论,请勿重复评论"),

    ERROR_AMOUNT(1001, "金额错误"),
    EXPIRE_TICKET(1002, "折扣券已过期"),
    TICKET_MERCHANT_NOT_MATCH(1003, "折扣券和商家不匹配"),
    TICKET_AMOUNT_NOT_MATCH(1004, "折扣券没有达到最低使用标准"),
    ORDER_MERCHANT_NOT_MATCH(1005, "订单不属于该商家"),


    GROUP_TICKET_NOT_MATCH(1005, "团购券不属于本商户"),


    CANNOT_REFUND_STATUS(1010, "订单当前状态不可退款"),
    CANNOT_REFUND_HOTEL(1011, "酒店预定16点之后不可退款"),
    CANNOT_FIND_PAYNO(1012, "当前订单未找到第三方支付记录"),
    REFUND_FAIL(1013, "退款失败"),
    CANNOT_DELETE_ORDER(1014, "当前订单状态不可删除"),
    CANNOT_CANCEL_ORDER(1015, "当前订单状态不可取消"),
    CANNOT_CONFIRML_ORDER(1016, "当前订单状态不可确认"),
    CANNOT_STAY_ORDER(1017, "当前订单状态不可入住"),
    SCORE_NOT_ENOUGH(1101, "积分不足,不可兑换"),
    CANNOT_OPEN_REDPACKET(1102, "未达到开启红包的要求"),

    NOT_CONSUM_YESTODAY(1102, "昨日没有消费，不可领取红包"),
    NONE_REDPACKTE(1103, "没有可领取红包"),

    OVERSTEP(1201, "提现金额大于可提现金额，无法提现"),

    PARAM_ERROR(400, "请求参数错误!"),
    NOT_LOGIN(401, "当前操作需要先登陆！"),
    INTERNAL_SERVER_ERROR(500, "服务器出了点小问题");

    private int code;

    private String msg;


    private ResultMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
