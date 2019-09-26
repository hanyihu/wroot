package com.vic.wroot.third.pay;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.vic.wroot.common.util.CommonUtils;
import com.vic.wroot.common.util.MapUtils;
import com.vic.wroot.common.util.PropertiesUtil;
import com.vic.wroot.common.util.XmlUtils;

import org.apache.http.ssl.SSLContexts;


/**
 * 微信退款相关
 * @author VIC
 *
 */
public class WechatpayUtils {
	private static Log logger = LogFactory.getLog(WechatpayUtils.class);
	/**
	 * 充值客户端类型--微信公众号
	 */
	public static Integer CLIENTTYPE_WX = 2;
	/**
	 * 充值客户端类型--app
	 */
	public static Integer CLIENTTYPE_APP = 1;
	
	/**
	 * 退款地址
	 */
	public static final String refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	
	/**
	 * 退款查询地址
	 */
	public static final String refundqueryUrl = "https://api.mch.weixin.qq.com/pay/refundquery";
	
 	/**
 	 * 方法描述：微信退款逻辑
 	 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
 	 * @param  clientType 2 充值客户端类型--微信公众号 1-值客户端类型--app
 	 * @param out_trade_no 商户侧传给微信的订单号
 	 * 单位为元
 	 */
 	public static WechatRefundApiResult wxRefund(Integer clientType,String out_trade_no, Double orderMoney, Double refundMoney){
 		//初始化请求微信服务器的配置信息包括appid密钥等
 		WechatConfig config = new WechatConfig(clientType);
 		//转换金钱格式
		BigDecimal bdOrderMoney = new BigDecimal(orderMoney,MathContext.DECIMAL32);
		BigDecimal bdRefundMoney = new BigDecimal(refundMoney,MathContext.DECIMAL32);
		//构建请求参数
		Map<Object, Object> params = buildRequsetMapParam(config,out_trade_no,bdOrderMoney,bdRefundMoney);
		String mapToXml = MapUtils.convertMap2Xml(params);
		//请求微信
		String reponseXml = sendSSLPostToWx(mapToXml,config.sslcsf);
		WechatRefundApiResult result = (WechatRefundApiResult) XmlUtils.xmlStrToBean(reponseXml, WechatRefundApiResult.class);
		
		logger.info("***********************微信退款返回**************************\n" + CommonUtils.toJson(result));
		return result;
	}
 	
 	/**
 	 * 方法描述：得到请求微信退款请求的参数
 	 * @return
 	 */
 	private static Map<Object, Object> buildRequsetMapParam(WechatConfig config,String out_trade_no, BigDecimal bdOrderMoney, BigDecimal bdRefundMoney){
 		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("appid", config.appId );//微信分配的公众账号ID（企业号corpid即为此appId）
		params.put("mch_id", config.mchId);//微信支付分配的商户号
		params.put("nonce_str", RandomStringUtils.randomAlphabetic(16));//随机字符串，不长于32位。推荐随机数生成算法
		params.put("out_trade_no", out_trade_no);//商户侧传给微信的订单号
		params.put("out_refund_no", getBundleId());//商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
		params.put("total_fee", bdOrderMoney.multiply(new BigDecimal(100)).intValue());//订单总金额，单位为分，只能为整数
		params.put("refund_fee", bdRefundMoney.multiply(new BigDecimal(100)).intValue());//退款总金额，订单总金额，单位为分，只能为整数
		params.put("op_user_id", config.mchId);//操作员帐号, 默认为商户号
		//签名前必须要参数全部写在前面
		params.put("sign", arraySign(params,config.paySignKey));//签名
		return params;
 	}
 	
 	/**
 	 * 请求微信https
 	 *
 	 **/
 	public static String sendSSLPostToWx(String mapToXml,SSLConnectionSocketFactory sslcsf){
 		logger.info("*******人工退款（WX Request："+mapToXml);
 		HttpPost httPost = new HttpPost(refundUrl); 
        httPost.addHeader("Connection", "keep-alive");  
        httPost.addHeader("Accept", "*/*");  
        httPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");  
        httPost.addHeader("Host", "api.mch.weixin.qq.com");  
        httPost.addHeader("X-Requested-With", "XMLHttpRequest");  
        httPost.addHeader("Cache-Control", "max-age=0");
        httPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");  
        httPost.setEntity(new StringEntity(mapToXml, "UTF-8"));
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslcsf).build(); 
        CloseableHttpResponse response = null;
        try {
			response = httpClient.execute(httPost);
			HttpEntity entity = response.getEntity();  
			String xmlStr = EntityUtils.toString(entity, "UTF-8");  
			logger.info("*******人工退款（WX Response："+xmlStr);
			return xmlStr;
        }catch(Exception e){
        	logger.error("*******人工退款异常"+ExceptionUtils.getStackTrace(e));
        	return null;
        }finally {
            try {
            	if(response!=null){
            		response.close();
            	}
			} catch (IOException e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
        }
 	}
 	
 	/**
 	 * 
 	 * 方法描述：微信查询退款逻辑
 	 * @param  clientType 2 充值客户端类型--微信公众号 1-值客户端类型--app
 	 * @param out_trade_no 商户侧传给微信的订单号
 	 */
 	public Map<String,Object> wxRefundquery(Integer clientType,String out_trade_no, String out_refund_no){
 		WechatConfig config = new WechatConfig(clientType);
 		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("appid", config.appId );//微信分配的公众账号ID（企业号corpid即为此appId）
		params.put("mch_id", config.mchId);//微信支付分配的商户号
		params.put("nonce_str", RandomStringUtils.randomAlphabetic(16));//随机字符串，不长于32位。推荐随机数生成算法
		params.put("out_trade_no", out_trade_no);//商户侧传给微信的订单号
		//签名前必须要参数全部写在前面
		params.put("sign", arraySign(params,config.paySignKey));//签名
		String mapToXml = MapUtils.convertMap2Xml(params);
		HttpPost httPost = new HttpPost(refundqueryUrl); 
        httPost.addHeader("Connection", "keep-alive");  
        httPost.addHeader("Accept", "*/*");  
        httPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");  
        httPost.addHeader("Host", "api.mch.weixin.qq.com");  
        httPost.addHeader("X-Requested-With", "XMLHttpRequest");  
        httPost.addHeader("Cache-Control", "max-age=0");
        httPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");  
        httPost.setEntity(new StringEntity(mapToXml, "UTF-8"));
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(config.sslcsf).build(); 
        CloseableHttpResponse response = null;
        try {
			response = httpClient.execute(httPost);
			HttpEntity entity = response.getEntity();  
			String xmlStr = EntityUtils.toString(entity, "UTF-8");  
			System.out.println(xmlStr);
			Map<String,Object> result = XmlUtils.xmlStrToMap(xmlStr);//.xmlStrToBean(xmlStr, WechatRefundApiResult.class);
			return result;
			//将信息保存到数据库
        }catch(Exception e){
        	logger.error(ExceptionUtils.getStackTrace(e));
        	return null;
        }finally {
            try {
            	if(response!=null){
            		response.close();
            	}
			} catch (IOException e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
        }
	}
 	
	/**
	 * 支付交易ID
	 */
	public static  String getBundleId(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String tradeno = dateFormat.format(new Date());
		String str = "000000" + (int)(Math.random()*1000000);
		tradeno = tradeno+str.substring(str.length()-6);
		return tradeno;
	}
	
	/**
	 * 方法描述：根据签名加密请求参数
	 * 创建时间：2017年6月8日  上午11:28:52
	 * 作者： xubo
	 * @param 
	 * @return
	 */
	public static  String arraySign(Map<Object, Object> params,String paySignKey){
		boolean encode = false;
        Set<Object> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                try {
					temp.append(URLEncoder.encode(valueString, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					logger.error(ExceptionUtils.getStackTrace(e));
				}
            } else {
                temp.append(valueString);
            }
        }
        temp.append("&key=");
        temp.append(paySignKey);
		System.out.println(temp.toString());
		String packageSign = DigestUtils.md5Hex(temp.toString().getBytes()).toUpperCase();
        return packageSign;
	}
	public static void main(String[] args) {
		System.out.println(RandomStringUtils.randomAlphabetic(16));
	}
	
}


class WechatConfig {

	private static Log logger = LogFactory.getLog(WechatConfig.class);
	
	/**
	 * 商户id
	 */
	public String appId ;
	/**
	 * 机器码
	 */
	public String mchId ;
	/**
	 * 签名
	 */
	public String paySignKey;
	/**
	 * 证书名称，对应不同的商户号
	 */
	public String certName;
	
	public SSLConnectionSocketFactory sslcsf;
	
	public WechatConfig(Integer clientType){
		if(WechatpayUtils.CLIENTTYPE_WX.compareTo(clientType) == 0){ // 微信公眾號
			this.appId = PropertiesUtil.getStringByKey("wx.wx.AppId");
			this.paySignKey = PropertiesUtil.getStringByKey("wx.wx.paySignKey");
			this.certName = PropertiesUtil.getStringByKey("wx.wx.certName");
			this.mchId = PropertiesUtil.getStringByKey("wx.wx.mchId");
			setSsslcsf();
		}else{ //APP
			this.certName = PropertiesUtil.getStringByKey("wx.app.certName");
			this.mchId = PropertiesUtil.getStringByKey("wx.app.mchId");
			this.appId = PropertiesUtil.getStringByKey("wx.app.AppId");
			this.paySignKey = PropertiesUtil.getStringByKey("wx.app.paySignKey");
			setSsslcsf();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void setSsslcsf(){
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			Thread.currentThread().getContextClassLoader();
			InputStream instream = Thread.currentThread().getContextClassLoader().getResourceAsStream(certName);// P12文件目录
			try {
				keyStore.load(instream, mchId.toCharArray());
			} finally {
				instream.close();
			}
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
			sslcsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		} catch (Exception e) {
			logger.error("微信退款证书加载出错！"+ExceptionUtils.getStackTrace(e));
		}
	}
	
	
}
