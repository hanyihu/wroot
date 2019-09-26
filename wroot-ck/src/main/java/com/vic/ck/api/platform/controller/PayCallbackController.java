package com.vic.ck.api.platform.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vic.ck.api.platform.service.OrderService;
import com.vic.ck.entity.Order;
import com.vic.wroot.common.util.XmlUtils;

/**
 * 支付回调接口
 * 
 * 相关日志入单独日志
 * @author VIC
 *
 */
@RestController
@RequestMapping("/api/callback")
public class PayCallbackController {
	
	private Logger logger = LoggerFactory.getLogger(PayCallbackController.class);
	
	@Resource
	private OrderService orderService;

	/**
	 * 支付宝回调接口
	 * @link https://docs.open.alipay.com/204/105301/
	 * @param orderno 本系统订单号
	 * @param alipayno 支付宝订单号
	 * @param status 支付状态
	 * @param fee 订单金额  单位元
	 * @param payAccount 买家支付宝账号
	 * @return
	 * 
	 */
	@RequestMapping("/alipay")
	@Transactional
	public String alipayCallback(@RequestParam("out_trade_no") String orderno,
			@RequestParam("trade_no") String alipayno,
			@RequestParam("trade_status") String status,
			@RequestParam("total_amount") double fee,
			@RequestParam("buyer_logon_id") String payAccount) {
		logger.debug("支付宝返回结果...\n订单号：{}\n支付宝交易号：{}\n状态：{}", new Object[] {
				orderno, alipayno, status });
		if ("WAIT_BUYER_PAY".equals(status)) {//交易创建，等待买家付款
			return "success";
		}
		
		// 根据订单号验证订单是否存在  t_pay_order
		Order order = orderService.findOrderByOrderno(orderno);
		if (order == null) { // 判断订单是否存在  可加一些其他的字段辅助判断
			logger.warn("订单不存在！ orderno={}", orderno);
			return "unknow";
		}
		if(order.getPayOutNo() != null && order.getPayOutNo().equals(alipayno)){
			logger.warn("订单支付回调已处理过！ orderno={}", orderno);
			return "success";
		}
		order.setPayOutNo(alipayno);
		orderService.alterOrderPayOutNo(order);
		orderService.afterPaySuccess(order);

		return "success";
	}
	
	/**
	 * 微信支付成功回调接口
	 * @link https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_7&index=3
	 */
	@RequestMapping(value = "/wxpay", method = RequestMethod.POST)
	public String wxCallBack(HttpServletRequest request) throws IOException, DocumentException{
		Map<String, String> map = XmlUtils.getXmlRequestParamsMap(request);
		String resultCode = map.get("result_code");
		if("SUCCESS".equals(resultCode)) {
			String orderno = map.get("out_trade_no");//订单号
			String transactionId = map.get("transaction_id");//微信的订单号
			Integer  fee =Integer.parseInt(map.get("total_fee"));//单位是分
			logger.debug("微信回调\n订单号：{}\n支付宝交易号：{}\n金额：{}分", new Object[] {
					orderno, transactionId, fee });
			// 根据订单号验证订单是否存在  t_pay_order
			Order order = orderService.findOrderByOrderno(orderno);
			if (order == null) { // 判断订单是否存在  可加一些其他的字段辅助判断
				logger.warn("订单不存在！ orderno={}", orderno);
				return wechatResult(WechatResultEnum.FAIL);
			}
			
			if(order.getPayOutNo() != null && order.getPayOutNo().equals(transactionId)){
				logger.warn("订单支付回调已处理过！ orderno={}", orderno);
				return "success";
			}
			order.setPayOutNo(transactionId);
			orderService.alterOrderPayOutNo(order);
			orderService.afterPaySuccess(order);
			return wechatResult(WechatResultEnum.SUCCESS);
		}
		return wechatResult(WechatResultEnum.FAIL);
	}
	
	/**
	 *构建微信回调返回对象 
	 * @param resultEnum
	 * @return
	 */
	protected String wechatResult(WechatResultEnum resultEnum) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("<xml>");
		sbf.append("<return_code>").append("<![CDATA[" + resultEnum.getCode() + "]]>").append("</return_code>");
		sbf.append("<return_msg>").append("<![CDATA[" + resultEnum.getMsg() + "]]>").append("</return_msg>");
		sbf.append("</xml>");
		return sbf.toString();
	}
	

	public enum WechatResultEnum {
		FAIL("FAIL", "FAIL"), SUCCESS("SUCCESS", "OK");
		private String code;
		private String msg;

		private WechatResultEnum(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
	
	
}