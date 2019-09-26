package com.vic.ck.api.system.service;

import com.vic.ck.api.constant.AttachmentModuleEnum;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.personal.service.PersonalService;
import com.vic.ck.api.platform.service.JpushMsgService;
import com.vic.ck.api.system.mapper.AuthMapper;
import com.vic.ck.entity.Customer;
import com.vic.ck.entity.SysConfig;
import com.vic.wroot.common.attachment.service.AttachmentService;
import com.vic.wroot.common.po.AjaxResponse;
import com.vic.wroot.common.tool.Tools;
import com.vic.wroot.common.util.AESUtil;
import com.vic.wroot.common.util.QRCodeUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.MessageFormat;

@Service
public class AuthService {
	
	Logger logger = LoggerFactory.getLogger(AuthService.class);

	@Resource
	private AuthMapper authMapper;

	@Resource
	private SystemService systemService;

	@Resource
	private CustomerBalanceService customerBalanceService;
	
	@Resource
	private PersonalService personalService;
	
	@Resource
	private JpushMsgService jpushMsgService;

	
	
	/**
	 *  用户设置手机号
	 */
	@Transactional
	public Customer registersj(String mobile,int id) {
		authMapper.updateMobile(mobile,id);
		Customer c = findCustomerById(id);
		
//		// 判断注册是否送现金
//		SysConfig s = systemService.getSysConfig(FinalFiledParams.SYS_CONFIG_REGISTER);
//		if (s != null && s.getBigDecimalContent().compareTo(BigDecimal.ZERO) > 0) {
//			customerBalanceService.addBalance(s.getBigDecimalContent(), FinalFiledParams.BALANCE_REGISTER, c.getId());
//		}
		//生成用户的邀请二维码
		generateQrcode(c.getId());
		return c;

	}
	
	/**
	 * 注册用户
	 */
	@Transactional
	public Customer register(String mobile, String password) {
		Customer c = new Customer(mobile, DigestUtils.md5Hex(password), StringUtils.overlay(mobile, "****", 3, 7));
		authMapper.insertCustomer(c);
		authMapper.insertCustomerBalance(c);

		// 判断注册是否送现金
		SysConfig s = systemService.getSysConfig(FinalFiledParams.SYS_CONFIG_REGISTER);
		if (s != null && s.getBigDecimalContent().compareTo(BigDecimal.ZERO) > 0) {
			customerBalanceService.addBalance(s.getBigDecimalContent(), FinalFiledParams.BALANCE_REGISTER, c.getId());
		}
		//生成用户的邀请二维码
		generateQrcode(c.getId());
		return c;

	}
	
	/**
	 * 注册第三方
	 */
	@Transactional
	public Customer registerThird(String nickname,int thirdPartyType,String thirdPartyUid, String thirdPartyHeadpic) {
		Customer c = new Customer(nickname, thirdPartyType, thirdPartyUid, thirdPartyHeadpic);
		authMapper.insertThirdCustomer(c);;
		authMapper.insertCustomerBalance(c);

		// 判断注册是否送现金
		SysConfig s = systemService.getSysConfig(FinalFiledParams.SYS_CONFIG_REGISTER);
		if (s != null && s.getBigDecimalContent().compareTo(BigDecimal.ZERO) > 0) {
			customerBalanceService.addBalance(s.getBigDecimalContent(), FinalFiledParams.BALANCE_REGISTER, c.getId());
		}
		//生成用户的邀请二维码
		generateQrcode(c.getId());
		return c;
	}
	
	/**
	 * 推荐用户注册
	 * @param recommendCode  推荐码
	 */
	@Transactional
	public Customer register(String mobile, String password, String recommendCode) {
		Customer c = register(mobile, password);
		if(StringUtils.isNotEmpty(recommendCode)) {
			try {
				//推荐人id
				int recommendId = Integer.parseInt(AESUtil.decrypt(recommendCode, FinalFiledParams.AES_KEY));
				
				Customer recommend = findCustomerById(recommendId);
				if(recommend != null) {
					// 1 推荐送积分给推荐用户
					personalService.addCustomerScoreRecord(FinalFiledParams.SCORE_RECOMMENT_NUMBER, FinalFiledParams.SCORE_RECOMMENT, recommendId);
					// 2 更新当前注册人的推荐人
					authMapper.updateCustomer(new Customer().setId(c.getId()).setRecommendId(recommendId));
					
					//3 推荐别人注册成功 送现金 
					BigDecimal money = systemService.getSysConfigBigDecimal(FinalFiledParams.SYS_CONFIG_RECOMMENT_REGIST_MONEY);
					if(money.compareTo(BigDecimal.ZERO) > 0){
						customerBalanceService.addBalance(money, FinalFiledParams.BALANCE_RECOMMENT_CUSTOMER, recommendId, MessageFormat.format("推荐用户{0}注册送现金{1}元", c.getNickname(), money));
					}
					
					// 推荐好友，好友成功注册通知
					jpushMsgService.pushToCustomer(recommendId, MessageFormat.format("推荐好友，好友{0}注册成功", c.getNickname()), recommend.getMobileCode());
				}
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}

		return c;

	}
	

	/** 手机号是否重复 */
	public boolean existedMobile(String mobile) {
		return authMapper.existedMobile(mobile);
	}

	/** 根据手机号查询用户 */
	public Customer findCustomerByMobile(String mobile) {
		Integer id = authMapper.findCustomerIdByMobile(mobile);
		return id == null ? null : findCustomerById(id);
	}
	
	/**根据商户id获得所属用于信息*/
	public Customer findMerchantCustomer(Integer merchantId) {
		Integer id = authMapper.findCustomerIdByMerchantId(merchantId);
		return id == null ? null : findCustomerById(id);
	}
	/**
	 * 获取第三方用户信息
	 */
	public Customer findThirdPartyCustomer(int thirdPartyType, String thirdPartyUid) {
		Integer id = authMapper.findThirdPartyCustomerId(thirdPartyType, thirdPartyUid);
		return id == null ? null : findCustomerById(id);
	}

	public Customer findCustomerById(Integer id) {
		return authMapper.findCustomerById(id);
	}

    //根据订单号 查骑手id+用户信息
    public Customer findCustomerByRider(String id) {
        return authMapper.findCustomerByByRider(id);
    }
	
	
	/**获得用户的积分*/
	public int getScore(int id) {
		Customer c = findCustomerById(id);
		if(c != null) {
			return c.getScore() == null ? 0 : c.getScore();
		}
		return 0;
	}

	// 修改设备号-客户端
	public void updateMobileCode(Customer customer) {
		authMapper.updateMobileCode(customer);

	}
	// 修改设备号-商家端
	public void updateMerchantMobileCode(Customer customer) {
		authMapper.updateMerchantMobileCode(customer);

	}

	// 修改密码
	public void updatePassword(Customer customer) {
		authMapper.updatePassword(customer);

	}

	// 编辑用户资料
	public void updateCustomer(Customer customer) {
		Customer old = findCustomerById(customer.getId());
		if(customer.getHeadpic() != null && old != null){
			attachmentService.HandleOldAndNowAttachment(old, customer);
		}
		authMapper.updateCustomer(customer);
	}

	@Resource
	private AttachmentService attachmentService;

	@Value("${recommend.qcode.url.prefix}")
	private String recommendQcodeUrlPrefix;

	/**
	 * 生成用户二维码
	 */
	public void generateQrcode(final int customerId) {

		logger.info("start generate qrcode for {}", customerId);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String recommend = AESUtil.encrypt(String.valueOf(customerId), FinalFiledParams.AES_KEY);
					recommend = URLEncoder.encode(recommend,"utf-8");
					String recommendUrl = recommendQcodeUrlPrefix + recommend;
					
					String qrcodePath = "/qrcode";
					
					if(StringUtils.isNotBlank(Tools.QRCODE_PATH)) {
						///           www/wwwroot/ttl
						qrcodePath = Tools.QRCODE_PATH + qrcodePath;
					}
					
					File f = QRCodeUtil.getQrCode(qrcodePath, recommendUrl);
					// 上传到附件项目
					AjaxResponse response = attachmentService.upload(f.getName(), new FileInputStream(f),
							AttachmentModuleEnum.QRCODE.getModule() + "");
					if (response != null && response.getCode() == 0) {
						int recommendQcodePath = response.getId();
						logger.info("recommendQcodePath= {}", recommendQcodePath);
						attachmentService.updateTemporary(false, recommendQcodePath);
						// 修改用户的二维码
						authMapper.updateCustomer(new Customer(customerId, recommendQcodePath, recommendUrl));
						f.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();

	}
	
	/**
	 * 验证图形码
	 * 
	 * @param picCode
	 * @param picValue
	 * @return
	 */
	public boolean picCodeValidate(String picCode, String picValue) {
		if (!StringUtils.equalsIgnoreCase(picValue, picCode)) {
			return false;
		}
		return true;
	}
	
	

	public static void main(String[] args) throws NumberFormatException, Exception {
		String recommendCode = "wKZZQgYV43nE/Wns1willw==";
		
		String b = URLEncoder.encode(recommendCode,"utf-8");
		int a = Integer.parseInt(AESUtil.decrypt(recommendCode, FinalFiledParams.AES_KEY));
		System.out.println(a);
		System.out.println(b);
		
	}

    //修改用户是否登录过
    public void userLogged(Integer customerId) {
        authMapper.userLogged(customerId);

    }
}
