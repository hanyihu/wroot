package com.vic.ck.api.system.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.vic.base.BaseApiController;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.entity.Customer;
import com.vic.ck.util.PicCodeUtils;
import com.vic.wroot.common.exception.SmsException;
import com.vic.wroot.common.web.interceptor.PhoneToken;
import com.vic.wroot.third.login.AlipayLogin;
import com.vic.wroot.third.sms.SmsService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.naming.AuthenticationException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录注册相关
 *
 * @author VIC
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseApiController {

    @Resource
    private SmsService smsService;

    @Resource
    private AuthService authService;

    /**
     * 2.00 获得用户信息信息  后续加的接口
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Object userInfo(int userId) throws AuthenticationException {
        checkLoggin(userId);
        Customer c = authService.findCustomerById(userId);
        return resultSuccess(c);
    }

    
//    /**
//     *  修改用户手机号   
//     */
//    @RequestMapping(value = "/upUserMobile", method = RequestMethod.GET)
//    public Object upUserMobile(int userId) throws AuthenticationException {
//        checkLoggin(userId);
//        Customer c = authService.upUserMobile(userId);
//        return resultSuccess(c);
//    }
    
    /**
     * 2.00 修改用户推送设备号
     * @param type
     * @param userId
     * @param mobileCode
     * @return
     */
    @RequestMapping(value = "/updateMobileCode", method = RequestMethod.POST)
    public Object updateMobileCode(@RequestParam(defaultValue = "1") int type, int userId, String mobileCode) {
        Customer c = new Customer(userId);
        if (type == 1) {
            c.setMobile(mobileCode);
            authService.updateMobileCode(c);
        } else if (type == 2) {
            c.setMerchantMobileCode(mobileCode);
            authService.updateMerchantMobileCode(c);
        }
        return resultSuccess();
    }

    
    /**
     * 生成图形码
     * 
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/picCode", method = {RequestMethod.POST, RequestMethod.GET})
    public Object picCode(HttpServletResponse response, HttpSession session,@RequestParam String uuid) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        Map<String, Object> map = PicCodeUtils.generateCodeAndPic();
        ImageIO.write((RenderedImage)map.get("buffImg"), "jpg", outputStream);
        cache2Redis(uuid,  map.get("randomCode").toString(), 1);
        return resultSuccess();
    }
    

    /**
     * 2.01 发送短信验证码  验证图形验证码
     *
     * @throws SmsException
     */
    @RequestMapping(value = "/smscode", method = RequestMethod.GET)
    public Object smscode(@RequestParam(defaultValue = "") String mobile, @RequestParam String picCode, @RequestParam String uuid,
            HttpSession session) {
    		String picValue = (String) cacheFromRedis(uuid);
//            String picValue = session.getAttribute("code").toString();
            if (!authService.picCodeValidate(picCode, picValue)) {
                return resultError(ResultMsgEnum.INVALID_PICCODE);
            }
    	
        String code = RandomStringUtils.random(6, "0123456789");
        try {
            smsService.sendSmscode(mobile, code);

        } catch (SmsException e) {
            return resultError(ResultMsgEnum.SMSSEND_FAIL);
        }
        cache2Redis(mobile, code, 30);
        //4刪除验证码
        delRedisCache(uuid);
        return resultSuccess(code);
    }
    
    
    
    
    /**
     * 2.01 发送短信验证码 
     * @throws SmsException
     */
    @RequestMapping(value = "/smscode1", method = RequestMethod.GET)
    public Object smscode(@RequestParam(defaultValue = "") String mobile) {
 
        String code = RandomStringUtils.random(6, "0123456789");
        try {
            smsService.sendSmscode(mobile, code);

        } catch (SmsException e) {
            return resultError(ResultMsgEnum.SMSSEND_FAIL);
        }
        cache2Redis(mobile, code, 30);
        return resultSuccess(code);
    }
    

    /**
     * 2.02 用户注册  
     *
     * @throws SmsException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestParam String mobile, @RequestParam String smscode, @RequestParam String password) {
        //1 判断验证码
        String code = cacheFromRedis(mobile) + "";
        if (!StringUtils.equalsIgnoreCase(code, smscode)) {
            return resultError(ResultMsgEnum.INVALID_SMSCODE);
        }
        //2判断手机号是否重复
        if (authService.existedMobile(mobile)) {
            return resultError(ResultMsgEnum.EXISTED_USER);
        }
        //3 注册的一些操作
        Customer c = authService.register(mobile, password);
        //4刪除验证码
        delRedisCache(mobile);

        return resultSuccess(c);
    }


/**
 * 设置手机号码-- 第三方首次登陆后 设置手机号   AND  修改手机号
 * @param mobile
 * @param smscode
 * @param id
 * @return
 */
    @RequestMapping(value = "/registersj", method = RequestMethod.POST)
    public Object registersj(@RequestParam String mobile, @RequestParam String smscode, @RequestParam int id) {
        //1 判断验证码
        String code = cacheFromRedis(mobile) + "";
        if (!StringUtils.equalsIgnoreCase(code, smscode)) {
            return resultError(ResultMsgEnum.INVALID_SMSCODE);
        }
        //2判断手机号是否重复
        if (authService.existedMobile(mobile)) {
            return resultError(ResultMsgEnum.EXISTED_USER);
        }
        //3 设置手机号的一些操作
        Customer c = authService.registersj(mobile,id);
        //4刪除验证码
        delRedisCache(mobile);

        return resultSuccess(c);
    }

    
    /**
     * 2.03 账号密码登录
     * @param mobile
     * @param password
     * @param mobileCode
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, params = {"mobile", "password", "!smscode"})
    public Object login(@RequestParam String mobile, @RequestParam String password, @RequestParam(defaultValue = "") String mobileCode) {
        Customer customer = authService.findCustomerByMobile(mobile);

        if (customer == null) {//用户不存在
//            return resultError(ResultMsgEnum.NOTEXISTED_USER);
            return  "用户还未注册，请使用手机验证码方式登陆！";
        }
        if (!customer.isEnabled()) {//用户被禁用
            return resultError(ResultMsgEnum.DISABLED_USER);
        }

        if (!StringUtils.equals(DigestUtils.md5Hex(password), customer.getPassword())) {//密码错误
            return resultError(ResultMsgEnum.ERROR_PASSWORD);
        }
        if (StringUtils.isNoneBlank(mobileCode)) {
            customer.setMobileCode(mobileCode);
            ;
            //修改设备号
            authService.updateMobileCode(customer);
        }
        // 登陆成功 生成token
        String token = PhoneToken.cachePhoneToken(mobile);
        PhoneToken.cachePhoneUid(mobile, customer.getId());
        customer.setToken(token);
        cacheCustomer(customer);
        userLogged(customer.getId());
        return resultSuccess(customer);

    }

    /**
     * 2.04 手机验证码登录
     * @param mobile
     * @param smscode
     * @param mobileCode
     * @return
     */
    @RequestMapping(value = "/smscodeLogin", method = RequestMethod.POST, params = {"mobile", "!password", "smscode"})
    public Object loginBySmscode(@RequestParam String mobile, @RequestParam String smscode, @RequestParam(defaultValue = "") String mobileCode) {
        //1 判断验证码
        String code = cacheFromRedis(mobile) + "";
        if (!StringUtils.equalsIgnoreCase(code, smscode)) {
            return resultError(ResultMsgEnum.INVALID_SMSCODE);
        }
        Customer customer = authService.findCustomerByMobile(mobile);

        if (customer == null) {//用户不存在
//            return resultError(ResultMsgEnum.NOTEXISTED_USER);
            // 	注册的一些操作： 按照需求逻辑， 免除注册流程，直接手机验证码无感知注册。
        	customer = authService.register(mobile,"123456");//默认初始密码123456
        	
        }
        if (!customer.isEnabled()) {//用户被禁用
            return resultError(ResultMsgEnum.DISABLED_USER);
        }

        if (StringUtils.isNoneBlank(mobileCode)) {
            customer.setMobileCode(mobileCode);
            //修改设备号
            authService.updateMobileCode(customer);
        }
        // 登陆成功 生成token
        String token = PhoneToken.cachePhoneToken(mobile);
        PhoneToken.cachePhoneUid(mobile, customer.getId());
        customer.setToken(token);
        cacheCustomer(customer);
        userLogged(customer.getId());
        return resultSuccess(customer);

    }

    /**
     * 2.05第三方登录
     * @param thirdPartyType
     * @param thirdPartyUid
     * @param thirdPartyHeadpic
     * @param nickname
     * @return
     */
    @RequestMapping(value = "/thirdLogin", method = RequestMethod.POST)
    public Object thirdLogin(int thirdPartyType, String thirdPartyUid, @RequestParam(defaultValue = "") String thirdPartyHeadpic, @RequestParam(defaultValue = "") String nickname) {
        Customer customer = authService.findThirdPartyCustomer(thirdPartyType, thirdPartyUid);
        if (customer == null) { //注册用户
            customer = authService.registerThird(nickname, thirdPartyType, thirdPartyUid, thirdPartyHeadpic);
        }
        return resultSuccess(authService.findCustomerById(customer.getId()));
    }

    /**
     *  2.05-01 第三方登陆-支付宝
     * @param code
     * @return
     */
    @RequestMapping(value = "/alipayLogin", method = RequestMethod.GET)
    public Object thirdLogin(String code) {
        try {
            AlipaySystemOauthTokenResponse toekenResponse = AlipayLogin.getToken(code);
            String accessToken = toekenResponse.getAccessToken();
            AlipayUserInfoShareResponse userInfo = AlipayLogin.getUserInfoShare(accessToken);
            //第三方登陆方式 1-微信 2-qq 3-支付宝
            Customer customer = authService.findThirdPartyCustomer(3, userInfo.getUserId());
            if (customer == null) { //注册用户
                customer = authService.registerThird(userInfo.getNickName(), 3, userInfo.getUserId(), userInfo.getAvatar());
            }
            return resultSuccess(authService.findCustomerById(customer.getId()));
        } catch (AlipayApiException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return resultError(ResultMsgEnum.ALIPAY_AUTH_FAIL);
        }

    }


    /**
     *  2.06  忘记密码
     * @param mobile
     * @param smscode
     * @param password
     * @return
     */
    @RequestMapping(value = "/forget", method = RequestMethod.POST)
    public Object forget(@RequestParam String mobile, @RequestParam String smscode, @RequestParam(defaultValue = "") String password) {
        //1 判断验证码
        String code = cacheFromRedis(mobile) + "";
        if (!StringUtils.equalsIgnoreCase(code, smscode)) {
            return resultError(ResultMsgEnum.INVALID_SMSCODE);
        }
        Customer customer = authService.findCustomerByMobile(mobile);

        if (customer == null) {//用户不存在
            return resultError(ResultMsgEnum.NOTEXISTED_USER);
        }
        customer.setPassword(DigestUtils.md5Hex(password));

        authService.updatePassword(customer);

        return resultSuccess(customer);
    }


    /**
     * 2.07 修改密码
     * @param mobile
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public Object updatePassword(@RequestParam String mobile, @RequestParam String oldPassword, @RequestParam(defaultValue = "") String newPassword) {
        Customer customer = authService.findCustomerByMobile(mobile);
        if (customer == null) {//用户不存在
            return resultError(ResultMsgEnum.NOTEXISTED_USER);
        }
        if (!StringUtils.equals(DigestUtils.md5Hex(oldPassword), customer.getPassword())) {//密码错误
            return resultError(ResultMsgEnum.ERROR_PASSWORD);
        }
        customer.setPassword(DigestUtils.md5Hex(newPassword));

        authService.updatePassword(customer);
        return resultSuccess(customer);
    }


    //修改用户是否登录过
    private void userLogged(Integer customerId) {
        authService.userLogged(customerId);
    }
}
