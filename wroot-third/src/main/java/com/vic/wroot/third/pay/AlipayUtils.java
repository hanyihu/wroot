package com.vic.wroot.third.pay;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.vic.wroot.common.util.PropertiesUtil;

/**
 *  支付宝的工具类
 * @author VIC
 *
 */
public class AlipayUtils {

	private static Logger logger = LoggerFactory.getLogger(AlipayUtils.class);

	private static final String ALIPAY_URL ="https://openapi.alipay.com/gateway.do";
	
	private static final String APP_ID = PropertiesUtil.getStringByKey("pay.alipay.appid");
	// 商户收款账号
	protected static final String SELLER = PropertiesUtil.getStringByKey("pay.alipay.seller");
	// 商户私钥，pkcs8格式
	private static final String RSA_PRIVATE = PropertiesUtil.getStringByKey("pay.alipay.rsa_private");
	// 支付宝公钥
	private static final String RSA_PUBLIC = PropertiesUtil.getStringByKey("pay.alipay.rsa_public");

	private static final String INPUT_CHARSET = "utf-8";
	
    /**
     * 
     * @方法名称:统一收单交易退款接口
     * @link https://docs.open.alipay.com/api_1/alipay.trade.refund
     * 
     * @param out_trade_no 订单支付时传入的商户订单号,不能和 trade_no同时为空。
     * @param trade_no 支付宝交易号，和商户订单号不能同时为空
     * @param refund_amount 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
     * @throws AlipayApiException 
     */
    public static AlipayTradeRefundResponse refundAlipay(String out_trade_no,String trade_no,double refund_amount,String refund_reason) throws AlipayApiException{
        // 发送请求
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID,
				 RSA_PRIVATE, "json", INPUT_CHARSET, RSA_PUBLIC, "RSA2");
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		Map<String, Object> alidata = new HashMap<String, Object>();
		alidata.put("out_trade_no", out_trade_no);
		alidata.put("out_request_no", getBundleId());
		alidata.put("trade_no", trade_no);
		alidata.put("refund_amount", refund_amount);
		alidata.put("refund_reason", refund_reason);
		logger.info("*******支付宝人工退款（alipay request:"+JSONObject.toJSONString(alidata));
		request.setBizContent(JSONObject.toJSONString(alidata));
		AlipayTradeRefundResponse response = alipayClient.execute(request);
		logger.info("*******支付宝人工退款（alipay:" + response.getBody());
		return response;
    }
    
    /**
     * 
     * @方法名称:统一收单交易退款查询
     * @link https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query/
     * 
     * @param out_trade_no 订单支付时传入的商户订单号,不能和 trade_no同时为空。
     * @param trade_no 支付宝交易号，和商户订单号不能同时为空
     * @param refund_amount 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
     */
    public static AlipayTradeFastpayRefundQueryResponse AlipayTradeRefundResponse(String out_trade_no,String trade_no,double refund_amount,String refund_reason) throws AlipayApiException{
        // 发送请求
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID,
				RSA_PRIVATE, "json", INPUT_CHARSET, RSA_PUBLIC, "RSA2");
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		Map<String, Object> alidata = new HashMap<String, Object>();
		alidata.put("out_trade_no", out_trade_no);
		alidata.put("trade_no", trade_no);
		alidata.put("out_request_no", out_trade_no);
		request.setBizContent(JSONObject.toJSONString(alidata));
		AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
		logger.info("*******人工退款状态检查（checkRefundAlipay:" + response.getBody());
		return response;
    }
    
    
    /**
     * 单笔转账到支付宝账户接口
     * @link https://docs.open.alipay.com/api_28/alipay.fund.trans.toaccount.transfer

     * @param out_biz_no 商户转账唯一订单号。发起转账来源方定义的转账单据ID，用于将转账回执通知给来源方。
     * @param payee_account 收款方账户。与payee_type配合使用。付款方和收款方不能是同一个账户。
     * @param amount 转账金额，单位：元。 只支持2位小数，小数点前最大支持13位，金额必须大于等于0.1元。最大转账金额以实际签约的限额为准。
     * @param payer_show_name 付款方姓名（最长支持100个英文/50个汉字）。 选填
     * @param payee_real_name 收款方真实姓名（最长支持100个英文/50个汉字）。  选填
     * @param remark 转账备注（支持200个英文/100个汉字）。当付款方为企业账户，且转账金额达到（大于等于）50000元，remark不能为空 
     * 
     * @throws AlipayApiException 
     */
    public static AlipayFundTransToaccountTransferResponse toaccountTransfer(String out_biz_no, String payee_account, double amount, String payer_show_name, String payee_real_name) throws AlipayApiException {
    	AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID,RSA_PRIVATE, "json", INPUT_CHARSET, RSA_PUBLIC,"RSA2");
    	AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("out_biz_no", out_biz_no);//商户转账唯一订单号。发起转账来源方定义的转账单据ID，用于将转账回执通知给来源方。 
    	params.put("payee_type", "ALIPAY_LOGONID");//收款方账户类型。
    	params.put("payee_account", "");//收款方账户。与payee_type配合使用。付款方和收款方不能是同一个账户。
    	params.put("amount", new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//转账金额，单位：元。   	只支持2位小数，小数点前最大支持13位，金额必须大于等于0.1元。 
    	params.put("payer_show_name", payer_show_name);//付款方姓名（最长支持100个英文/50个汉字）。显示在收款方的账单详情页。如果该字段不传，则默认显示付款方的支付宝认证姓名或单位名称。
    	params.put("payee_real_name", payee_real_name);//收款方真实姓名（最长支持100个英文/50个汉字）。    	如果本参数不为空，则会校验该账户在支付宝登记的实名是否与收款方真实姓名一致。
    	params.put("remark", "");//转账备注（支持200个英文/100个汉字）。  	当付款方为企业账户，且转账金额达到（大于等于）50000元，remark不能为空。
    	request.setBizContent(JSONObject.toJSONString(params));
    	AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
    	logger.info("单笔转账到支付宝返回结果:{}", response.getBody());
    	return response;
    }
    
    /**
     * 查询转账订单接口
     * @param out_biz_no 商户转账唯一订单号：发起转账来源方定义的转账单据ID。 
     * @param order_id 支付宝转账单据号：和商户转账唯一订单号不能同时为空。
     * 
     * @link https://docs.open.alipay.com/api_28/alipay.fund.trans.order.query
     * @throws AlipayApiException 
     */
    public static AlipayFundTransOrderQueryResponse transOrderQuery(final String out_biz_no, final String order_id) throws AlipayApiException {
    	AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, APP_ID, RSA_PRIVATE, "json", INPUT_CHARSET, RSA_PUBLIC, "RSA2");
    	AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
    	@SuppressWarnings("serial")
		Map<String, Object> params = new HashMap<String, Object>(){{
    		put("out_biz_no", out_biz_no);//	商户转账唯一订单号：发起转账来源方定义的转账单据ID。 
    		put("order_id", order_id);//支付宝转账单据号：和商户转账唯一订单号不能同时为空。
    	}};
    	
    	request.setBizContent(JSONObject.toJSONString(params));
    	AlipayFundTransOrderQueryResponse response = alipayClient.execute(request);
    	logger.info("查询转账订单接口返回结果:{}", response.getBody());
    	return response;
    }
    
    
    
	/**
	 * 支付交易ID 23位
	 */
	public static String getBundleId(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String tradeno = dateFormat.format(new Date());
		String str = "000000" + (int)(Math.random()*1000000);
		tradeno = tradeno+str.substring(str.length()-6);
		return tradeno;
	}
	
}
