package com.vic.ck.api.constant;

import com.vic.wroot.common.util.PropertiesUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量定义
 *
 * @author QiuDong
 */
public final class FinalFiledParams {

    /**
     * 是否开启 请求参数 和返回结果的加密  测试时可关闭
     */
    public static final boolean START_ENCRYPT = false;

    /**
     * 附件URL  = ATTACHMENT_PREFIX + id
     */
    public static final String ATTACHMENT_PREFIX = PropertiesUtil.getStringByKey("attachment.prefix");


    /**
     * AES加解密key
     */
    public static final String AES_KEY = "(@E20-*2+!#$bc95";

    /**
     * 所有请求的参数为PARAM
     */
    public static final String REQUEST_PARAM = "param";

    /**
     * 骑手完成订单
     */
    public static final Integer RIDER_ORDER_COMPLETE = 3;
    /**
     * 骑手待取订单
     */
    public static final Integer RIDER_ORDER_TAKE = 1;
    /**
     * 抢单
     */
    public static final Integer RIDER_ORDER_SINGLE = 2;
    /**
     * 订单状态
     */
    public static final Integer RIDER_ORDER_STATUS = 1;
    /**
     * 订单已完成
     */
    public static final Integer ORDER_STATUS = 4;
    /**
     * 判断
     */
    public static final Integer RIDER_ORDER = 0;

    /**
     * 积分类型
     */
    //类型 1-评论积分 2-消费获得积分 3-签到积分 4-分享积分 5-邀请好友积分 6 - 兑换商品
    public static final Map<Integer, String> SCORE_TYPE_MAP = new HashMap<Integer, String>();

    public static final Integer SCORE_COMMENT = 1;

    public static final Integer SCORE_BUY = 2;

    public static final Integer SCORE_SIGN = 3;

    public static final Integer SCORE_SHARE = 4;

    public static final Integer SCORE_RECOMMENT = 5;

    public static final Integer SCORE_SWAP = 6;


    /**
     * 积分兑换的订单状态 0-未发货 1-配送中 2-已完成
     */
    public static final Map<Integer, String> SWAP_STATUS_MAP = new HashMap<Integer, String>();

    /**
     * 不同动作送积分的数值
     * //类型 1-评论积分 2-消费积分 3-签到积分 4-分享积分 5-邀请好友积分
     */
    public static final Map<Integer, Integer> SCORE_TYPE_NUMBER_MAP = new HashMap<Integer, Integer>();

    /**
     * 评论送5分
     */
    public static final int SCORE_COMMENT_NUMBER = 5;
    //消费是根据消费的金额
    public static final int SCORE_BUY_NUMBER = 1;
    /**
     * 签到送10分
     */
    public static final int SCORE_SIGN_NUMBER = 5;
    /**
     * 分享送5分
     */
    public static final int SCORE_SHARE_NUMBER = 5;
    /**
     * 推荐送10分
     */
    public static final int SCORE_RECOMMENT_NUMBER = 10;

    /***
     * 用户收入明细类别  1-推荐消费收入 2-下单收入 3-红包收入 4-推荐商户收入 5-活动收入 6-注册送现金  7-消费支出 8-提现 9- 商户退款  10-话费余额转入 11 推荐用户成为商户 12-提现失败退款 13-推荐用户注册收入
     */
    public static final Map<Integer, String> BALANCE_TYPE_MAP = new HashMap<Integer, String>();

    /**
     * 1-推荐消费收入
     */
    public static final int BALANCE_RECOMMENT_BUY = 1;
    /**
     * 2-下单收入
     */
    public static final int BALANCE_BUY = 2;
    /**
     * 3-红包收入
     */
    public static final int BALANCE_REDPACKET = 3;
    /**
     * 4-推荐商户收入
     */
    public static final int BALANCE_RECOMMENT_SELL = 4;
    /**
     * 5-活动收入
     */
    public static final int BALANCE_ACTIVITY = 5;
    /**
     * 6-注册送现金
     */
    public static final int BALANCE_REGISTER = 6;
    /**
     * 7-消费支出
     */
    public static final int BALANCE_CONSUME = 7;
    /**
     * 8-提现
     */
    public static final int BALANCE_FETCH = 8;
    /**
     * 9- 商户退款
     */
    public static final int BALANCE_REFUND = 9;
    /**
     * 10-话费余额转入
     */
    public static final int BALANCE_TELEPHONE_REMAIN = 10;
    /**
     * 11 推荐用户成为商户
     */
    public static final int BALANCE_RECOMMENT_SUCCESS = 11;
    /**
     * 12-提现失败退款
     */
    public static final int BALANCE_FETCH_FAIL = 12;
    /**
     * 13-推荐用户注册收入
     */
    public static final int BALANCE_RECOMMENT_CUSTOMER = 13;

    /**
     * 商家明细类别1-用户付款 2-团购销售收入 3-给用户退款 4-提款 5 酒店预订收入
     */
    public static final Map<Integer, String> MERCHANT_BALANCE_TYPE_MAP = new HashMap<Integer, String>();

    public static final int MERCHAN_BALANCE_CONSUMER_PAY = 1;

    public static final int MERCHAN_BALANCE_GROUBUY_SALE = 2;

    public static final int MERCHAN_BALANCE_REFUND = 3;

    public static final int MERCHAN_BALANCE_FETCH = 4;

    public static final int MERCHAN_BALANCE_HOTEL = 5;


    /************** 系统配置 code*************************************************/
    /**
     * 系统配置 ：注册送现金code
     */
    public static final String SYS_CONFIG_REGISTER = "001";

    /**
     * 系统配置 ：关于我们-客户端code
     */
    public static final String SYS_CONFIG_ABOUTUS_CUSTOMER = "002";

    /**
     * 系统配置 ：注关于我们-商家端code
     */
    public static final String SYS_CONFIG_ABOUTUS_MERCHANT = "003";


    /**
     * 系统配置 ：一天分享获得积分次数code
     */
    public static final String SYS_CONFIG_SHARED_NUMBER = "004";

    /**
     * 系统配置 ：推荐注册获现金code
     */
    public static final String SYS_CONFIG_RECOMMENT_REGIST_MONEY = "005";

    /**
     * 系统配置 ：客服电话code
     */
    public static final String SYS_CONFIG_CONTACT = "006";
    /************** 系统配置 code*************************************************/


    /**订单状态*/
    /**订单状态  0-待支付 1-待消费 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
     * 直接购买：0-待支付 4-待评价 (已完成) 5-已评价
     * 团购：	 0-待支付 1-待消费 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
     * 预定：	 0-待支付 2-待确认 3- 待入住 4-待评价 (已完成) 5-已评价 6-退款中 7-已退款 8-已取消
     */
    /**
     * 订单状态对应的订单描述
     */
    public static final Map<Integer, String> ORDER_STATUS_MAP = new HashMap<Integer, String>();

    /**
     * 订单筛选状态
     */
    public static final Map<Integer, String> ORDER_SCREENING_MAP = new HashMap<Integer, String>();

    /**
     * 订单状态对应的订单记录表的描述
     */
    public static final Map<Integer, String> ORDER_STATUS_RECORD_MAP = new HashMap<Integer, String>();
    /**
     * 待支付
     */
    public static final int ORDER_STATUS_NOPAY = 0;

    /**
     * 待消费
     */
    public static final int ORDER_STATUS_NOCONSUME = 1;

    /**
     * 待确认
     */
    public static final int ORDER_STATUS_NOCONFIRM = 2;

    /**
     * 待入住
     */
    public static final int ORDER_STATUS_NOSTAY = 3;

    /**
     * 待评价
     */
    public static final int ORDER_STATUS_NOCOMMENT = 4;

    /**
     * 已评价
     */
    public static final int ORDER_STATUS_COMMENTED = 5;

    /**
     * 申请退款
     */
    public static final int ORDER_STATUS_REFUNDING = 6;

    /**
     * 已退款
     */
    public static final int ORDER_STATUS_REFUNDED = 7;
    // 71-拒绝退款
    public static final int ORDER_STATUS_NOTREFUNDED = 71;

    /**
     * 已取消
     */
    public static final int ORDER_STATUS_CANCELED = 8;

    /**
     * 退款理由
     */
    public static final Map<Integer, String> REFUND_REASON_MAP = new HashMap<Integer, String>();


    /**
     * 下单
     */
    public static final int ORDER_SCREENING_S = 0;
    /**
     * 商家接单
     */
    public static final int ORDER_SCREENING_A = 1;
    /**
     * 骑手接单
     */
    public static final int ORDER_SCREENING_B = 2;
    /**
     * 配送
     */
    public static final int ORDER_SCREENING_C = 3;
    /**
     * 签收
     */
    public static final int ORDER_SCREENING_D = 4;
    /**
     * 取消
     */
    public static final int ORDER_SCREENING_E = 5;
    /**
     * 退款
     */
    public static final int ORDER_SCREENING_F = 6;
    /**
     * 退货
     */
    public static final int ORDER_SCREENING_G = 7;
    /**
     * 已结算
     */
    public static final int ORDER_SCREENING_H = 8;
    /**
     * 未结算
     */
    public static final int ORDER_SCREENING_J = 9;


    static {
        //积分类型
        SCORE_TYPE_MAP.put(SCORE_COMMENT, "评论积分");
        SCORE_TYPE_MAP.put(SCORE_BUY, "消费得积分");
        SCORE_TYPE_MAP.put(SCORE_SIGN, "签到积分");
        SCORE_TYPE_MAP.put(SCORE_SHARE, "分享积分");
        SCORE_TYPE_MAP.put(SCORE_RECOMMENT, "邀请好友积分");
        SCORE_TYPE_MAP.put(SCORE_SWAP, "兑换商品");

        //积分类型分别送多少分
        SCORE_TYPE_NUMBER_MAP.put(SCORE_COMMENT, SCORE_COMMENT_NUMBER);
        SCORE_TYPE_NUMBER_MAP.put(SCORE_BUY, SCORE_BUY_NUMBER);
        SCORE_TYPE_NUMBER_MAP.put(SCORE_SIGN, SCORE_SIGN_NUMBER);
        SCORE_TYPE_NUMBER_MAP.put(SCORE_SHARE, SCORE_SHARE_NUMBER);
        SCORE_TYPE_NUMBER_MAP.put(SCORE_RECOMMENT, SCORE_RECOMMENT_NUMBER);

        //积分兑换的订单状态 0-未发货 1-配送中 2-已完成
        SWAP_STATUS_MAP.put(0, "未发货");
        SWAP_STATUS_MAP.put(1, "配送中");
        SWAP_STATUS_MAP.put(2, "已完成");


        //收入明细类别 1-推荐消费收入 2-下单收入 3-红包收入 4-推荐商户收入 5-活动收入 6-注册送现金  7-消费支出 8-提现
        BALANCE_TYPE_MAP.put(BALANCE_RECOMMENT_BUY, "推荐消费收入");
        BALANCE_TYPE_MAP.put(BALANCE_BUY, "下单收入");
        BALANCE_TYPE_MAP.put(BALANCE_REDPACKET, "红包收入");
        BALANCE_TYPE_MAP.put(BALANCE_RECOMMENT_SELL, "推荐商户收入");
        BALANCE_TYPE_MAP.put(BALANCE_ACTIVITY, "活动收入");
        BALANCE_TYPE_MAP.put(BALANCE_REGISTER, "注册送现金");
        BALANCE_TYPE_MAP.put(BALANCE_CONSUME, "消费支出");
        BALANCE_TYPE_MAP.put(BALANCE_FETCH, "提现");
        BALANCE_TYPE_MAP.put(BALANCE_REFUND, "商户退款");
        BALANCE_TYPE_MAP.put(BALANCE_TELEPHONE_REMAIN, "话费余额转入");
        BALANCE_TYPE_MAP.put(BALANCE_RECOMMENT_SUCCESS, "推荐用户成为商家");
        BALANCE_TYPE_MAP.put(BALANCE_FETCH_FAIL, "提现失败退款");
        BALANCE_TYPE_MAP.put(BALANCE_RECOMMENT_CUSTOMER, "推荐用户注册");


        //商家明细类别1-用户付款 2-团购销售收入 3-给用户退款 4-提款 5 酒店预订收入
        MERCHANT_BALANCE_TYPE_MAP.put(MERCHAN_BALANCE_CONSUMER_PAY, "用户付款");
        MERCHANT_BALANCE_TYPE_MAP.put(MERCHAN_BALANCE_GROUBUY_SALE, "团购销售收入");
        MERCHANT_BALANCE_TYPE_MAP.put(MERCHAN_BALANCE_REFUND, "给用户退款入");
        MERCHANT_BALANCE_TYPE_MAP.put(MERCHAN_BALANCE_FETCH, "提款");
        MERCHANT_BALANCE_TYPE_MAP.put(MERCHAN_BALANCE_HOTEL, "酒店预订收入");

        //订单状态 对应的描述
        ORDER_STATUS_MAP.put(ORDER_STATUS_NOPAY, "待支付");
        ORDER_STATUS_MAP.put(ORDER_STATUS_NOCONSUME, "待消费");
        ORDER_STATUS_MAP.put(ORDER_STATUS_NOCONFIRM, "待确认");
        ORDER_STATUS_MAP.put(ORDER_STATUS_NOSTAY, "待入住");
        ORDER_STATUS_MAP.put(ORDER_STATUS_NOCOMMENT, "待评价");
        ORDER_STATUS_MAP.put(ORDER_STATUS_COMMENTED, "已评价");
        ORDER_STATUS_MAP.put(ORDER_STATUS_REFUNDING, "申请退款");
        ORDER_STATUS_MAP.put(ORDER_STATUS_NOTREFUNDED, "拒绝退款");
        ORDER_STATUS_MAP.put(ORDER_STATUS_REFUNDED, "已退款");
        ORDER_STATUS_MAP.put(ORDER_STATUS_CANCELED, "已取消");


        //订单筛选状态
        ORDER_SCREENING_MAP.put(ORDER_SCREENING_S, "下单");
        ORDER_SCREENING_MAP.put(ORDER_SCREENING_A, "商家接单");
        ORDER_SCREENING_MAP.put(ORDER_SCREENING_B, "骑手接单");
        ORDER_SCREENING_MAP.put(ORDER_SCREENING_C, "配送");
        ORDER_SCREENING_MAP.put(ORDER_SCREENING_D, "签收");
        ORDER_SCREENING_MAP.put(ORDER_SCREENING_E, "取消");
        ORDER_SCREENING_MAP.put(ORDER_SCREENING_F, "退款");
        ORDER_SCREENING_MAP.put(ORDER_SCREENING_G, "退货");
        ORDER_SCREENING_MAP.put(ORDER_SCREENING_H, "已结算");
        ORDER_SCREENING_MAP.put(ORDER_SCREENING_J, "未结算");

        ORDER_STATUS_RECORD_MAP.put(ORDER_STATUS_NOPAY, "已下单，等待支付");
        ORDER_STATUS_RECORD_MAP.put(ORDER_STATUS_NOCONSUME, "已支付，等待接单");
        ORDER_STATUS_RECORD_MAP.put(ORDER_STATUS_NOCONFIRM, "已预约，等待商家待确认");
        ORDER_STATUS_RECORD_MAP.put(ORDER_STATUS_NOSTAY, "商家已确认");
        ORDER_STATUS_RECORD_MAP.put(ORDER_STATUS_NOCOMMENT, "消费已完成，等待评价");
        ORDER_STATUS_RECORD_MAP.put(ORDER_STATUS_COMMENTED, "订单已评价");
        ORDER_STATUS_RECORD_MAP.put(ORDER_STATUS_REFUNDING, "已申请退款，等待退款");
        ORDER_STATUS_RECORD_MAP.put(ORDER_STATUS_NOTREFUNDED, "拒绝退款");
        ORDER_STATUS_RECORD_MAP.put(ORDER_STATUS_REFUNDED, "已成功退款");
        ORDER_STATUS_RECORD_MAP.put(ORDER_STATUS_CANCELED, "订单已取消");

        //退款理由   后面改为 string
        /*
         * 退款理由 1.买错了， 2计划有变，没时间消费 3去过了，不满意 4预约不上 5商家停业维修转让 6商家营业，但不接待
         * 7商家说可以直接以团购价到店里消费 8网友评价不好 19-其他原因
         */
        REFUND_REASON_MAP.put(1, "买错了");
        REFUND_REASON_MAP.put(2, "计划有变，没时间消费");
        REFUND_REASON_MAP.put(3, "去过了，不满意");
        REFUND_REASON_MAP.put(4, "预约不上");
        REFUND_REASON_MAP.put(5, "商家停业维修转让");
        REFUND_REASON_MAP.put(6, "商家营业，但不接待");
        REFUND_REASON_MAP.put(7, "商家说可以直接以团购价到店里消费");
        REFUND_REASON_MAP.put(8, "网友评价不好");
        REFUND_REASON_MAP.put(19, "其他原因");

    }


}
	