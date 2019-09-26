package com.vic.ck.api.merchant.controller;

import com.vic.base.BaseApiController;
import com.vic.base.BaseApiLookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.merchant.service.BusinessCategoryService;
import com.vic.ck.api.merchant.service.MerchantService;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.api.system.service.SystemService;
import com.vic.ck.entity.*;
import com.vic.ck.util.PicCodeUtils;
import com.vic.wroot.common.exception.SmsException;
import com.vic.wroot.common.po.BaseResponse;
import com.vic.wroot.third.sms.AliyunSmsService;
import io.swagger.annotations.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 五 商家端 1 登陆认证相关
 *
 * @author VIC
 *
 */
@Api(tags = "商家端登陆认证")
@RestController
@RequestMapping("/api/merchant/auth")
public class MerchantAuthController extends BaseApiController {

    @Resource
    private AuthService authService;

    @Resource
    private MerchantService merchantService;

    @Resource
    private SystemService systemService;

    @Resource
    private BusinessCategoryService businessCategoryService;

    @Resource(name = "aliyunSmsService")
    private AliyunSmsService smsService;

    /**
     * 5.01 商家登陆
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "商家登陆", notes = "处理商家登陆, 需要判断用户的状态以及密码, 如果没有找到相关商家,需要提示用户 。", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 802, message = "不存在的用户", response = BaseResponse.class),
            @ApiResponse(code = 803, message = "用户被禁用", response = BaseResponse.class),
            @ApiResponse(code = 804, message = "密码错误", response = BaseResponse.class)
    })
    public Object login(@RequestParam @ApiParam(value = "手机号", required = true) String mobile,
                        @RequestParam @ApiParam(value = "密码", required = true) String password,
                        @RequestParam(defaultValue = "") @ApiParam(value = "设备号") String mobileCode) {

        Customer customer = authService.findCustomerByMobile(mobile);

        if (customer == null) {// 用户不存在
            return resultError(ResultMsgEnum.NOTEXISTED_USER);
        }
        if (!customer.isEnabled()) {// 用户被禁用
            return resultError(ResultMsgEnum.DISABLED_USER);
        }

        if (!StringUtils.equals(DigestUtils.md5Hex(password), customer.getPassword())) {// 密码错误
            return resultError(ResultMsgEnum.ERROR_PASSWORD);
        }

        Merchant merchant = merchantService.findMerchantByCustomerId(customer.getId());

        // 如果没有找到这个商家的信息 则告诉前端当前商家尚未提交审核资料
        if (merchant == null) {
            merchant = new Merchant().setCustomer(customer).setStatus(4);
            return resultSuccess(merchant);
        }
        merchant.setCustomer(customer);
        if (StringUtils.isNoneBlank(mobileCode)) {
            customer.setMerchantMobileCode(mobileCode);
            // 修改设备号
            authService.updateMerchantMobileCode(customer);
        }
        return resultSuccess(merchant);
    }

    /**
     * 生成图形码
     *
     * @author zy
     * @param response
     * @return 返回正确对象
     * @throws IOException
     */
    @RequestMapping(value = "/picCode", method = RequestMethod.GET)
    @ApiOperation(value = "生成图形码", notes = "生成图形验证码进行验证", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object picCode(HttpServletResponse response, @RequestParam @ApiParam(value = "用户ID", required = true) int userId
            ,@RequestParam @ApiParam(value = "uuid", required = true) String uuid
    ) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        Map<String, Object> map = PicCodeUtils.generateCodeAndPic();
        ImageIO.write((RenderedImage) map.get("buffImg"), "jpg", outputStream);
        cache2Redis("picCode_" + userId, map.get("randomCode").toString(), 5);
        return resultSuccess();
    }

    /**
     * 发送短信验证码并验证图形码
     *
     * @author zy
     * @param mobile 手机号
     * @param picCode 图形码
     * @return 返回包含code(手机验证码)的正确对象
     * @throws SmsException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/smsCode", method = RequestMethod.GET)
    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码并验证图形码的正确性,如果图形码不正确则不发送短信验证码", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 702, message = "发送短信验证码失败", response = BaseResponse.class),
            @ApiResponse(code = 705, message = "无效的图形码", response = BaseResponse.class),
            @ApiResponse(code = 706, message = "图形码已失效", response = BaseResponse.class)
    })
    public Object smsCode(@RequestParam(defaultValue = "") @ApiParam(value = "手机号", required = true) String mobile,
                          @RequestParam @ApiParam(value = "图形码", required = true) String picCode,
                          @RequestParam @ApiParam(value = "用户ID", required = true) int userId) {
        String picValue = "";
        Object picCodeObject = cacheFromRedis("picCode_" + userId);

        if(picCodeObject != null) {
            picValue = picCodeObject.toString();
        } else {
            return resultError(ResultMsgEnum.INVALID_OUTTIME);
        }

        if(!merchantService.picCodeValidate(picCode, picValue)) {
            return resultError(ResultMsgEnum.INVALID_PICCODE);
        }
        String code = RandomStringUtils.random(6, "0123456789");
        try {
            smsService.sendLocateSmsCode(mobile, code);
        } catch (SmsException e) {
            return resultError(ResultMsgEnum.SMSSEND_FAIL);
        }
        cache2Redis(mobile, code, 30);
        return resultSuccess(code);
    }

    /**
     * 提交并验证手机验证码，企业名称是否重复，电话是否重复
     *
     * @author zy
     * @param name 企业名称
     * @param mobile 电话
     * @param smscode 手机验证码
     * @return 返回正确对象
     */
    @RequestMapping(value = "/smsCodeValidate", method = RequestMethod.GET)
    @ApiOperation(value = "提交并验证手机验证码", notes = "提交并验证手机验证码的正确性,企业名称是否重复,电话是否重复", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 703, message = "无效的短信验证码", response = BaseResponse.class),
            @ApiResponse(code = 816, message = "企业名称已存在", response = BaseResponse.class),
            @ApiResponse(code = 801, message = "当前手机号已注册", response = BaseResponse.class)
    })
    public Object smsCodeValidate(@RequestParam @ApiParam(value = "企业名称", required = true) String name,
                                           @RequestParam(defaultValue = "") @ApiParam(value = "手机号码", required = true) String mobile,
                                           @RequestParam @ApiParam(value = "短信验证码", required = true) String smscode) {
        String smsValue = cacheFromRedis(mobile) + "";
        if (!merchantService.smsCodeValidate(smsValue, smscode)) {
            return resultError(ResultMsgEnum.INVALID_SMSCODE);
        }
        if (merchantService.merchantByName(name)) {
            return resultError(ResultMsgEnum.ENTERPRISE_EXIST);
        }
        if (merchantService.merchantByMobile(mobile)) {
            return resultError(ResultMsgEnum.EXISTED_USER);
        }
        delRedisCache(mobile);
        return resultSuccess();
    }

    /**
     * 查询省市区
     *
     * @author zy
     * @return 返回包含areaList(进行三级联动后的城市集合)的正确对象
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    @ApiOperation(value = "查询省市区", notes = "查询省市区并返回给前台,用于商户入驻时选择地址", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Area> queryCity() {

        List<Area> cityList = systemService.selectAllCities();

        List<Area> areaList = merchantService.selectAllCities(cityList);

        return resultSuccess(areaList);
    }

    /**
     * 查询经营品类
     *
     * @author zy
     * @return 返回包含categories(进行三级联动后的经营品类集合)的正确对象
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    @ApiOperation(value = "查询经营品类", notes = "查询经营品类并返回给前台,用于商户入驻时选择经营品类", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<BusinessCategory> queryCategory() {

        List<BusinessCategory> categoryList = businessCategoryService.selectAllCategory();

        List<BusinessCategory> categorys = merchantService.selectAllCategory(categoryList);

        return resultSuccess(categorys);
    }

    /**
     * 提交保存资料
     *
     * @author zy
     * @param merchant 商家审核资料
     * @param userId 用户ID
     * @return 返回包含商户信息的正确对象
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/submitAudit", method = RequestMethod.POST)
    @ApiOperation(value = "提交保存资料", notes = "提交用户审核资料", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Merchant> submitAudit(@RequestBody @ApiParam(value = "商家信息", required = true) Merchant merchant,
                                              @RequestParam @ApiParam(value = "手机号码", required = true) int userId) {
        Customer customer = authService.findCustomerById(userId);
        if(customer != null) {
            merchant.setCustomerId(userId);
        }
        merchantService.insertMerchant(merchant.setStatus(1));
        return resultSuccess(merchantService.getMerchantById(merchant.getId()));
    }

    /**
     * 5.03 修改基本信息-需重新审核. 包含商家详情外的的全部图片
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/updateBaseInfo", method = RequestMethod.POST)
    @ApiOperation(value = "修改基本信息", notes = " 修改基本信息-需重新审核. 包含商家详情外的的全部图片", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Merchant> updateBaseInfo(@RequestBody @ApiParam(value = "商家基本信息", required = true) Merchant merchant) {
        merchantService.updateMerchant(merchant.setStatus(1));
        return resultSuccess(merchantService.getMerchantById(merchant.getId()));
    }

    /**
     * 5.04修改商家信息. 只有商家详情里包含图片信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/updateBusiness", method = RequestMethod.POST)
    @ApiOperation(value = "修改商家信息", notes = "修改商家信息. 只有商家详情里包含图片信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Merchant> updateBusiness(@RequestBody @ApiParam(value = "商家信息", required = true) Merchant merchant) {
        merchantService.updateMerchant(merchant.setStatus(null));// 虽然交给前端修改 但是状态就别想修改了
        return resultSuccess(merchantService.getMerchantById(merchant.getId()));
    }

    /**
     * 5.05 提交实名认证信息
     */
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    @ApiOperation(value = "提交实名认证信息", notes = "提交实名认证信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object authentication(@RequestBody @ApiParam(value = "实名认证信息", required = true) MerchantAuthentication authentication) {
        merchantService.insertMerchantAuthentication(authentication);
        return resultSuccess();
    }

    /**
     * 5.06 获得商家信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获得商家信息", notes = "获得商家信息并返回到前端", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Merchant> merchantInfo(@RequestParam @ApiParam(value = "商家ID", required = true) int id) {
        Merchant merchant = merchantService.getMerchantById(id);
        return resultSuccess(merchant);
    }

    /**
     * 5.07 交通信息列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/traffics", method = RequestMethod.GET)
    @ApiOperation(value = "交通信息列表", notes = "获得商家的交通信息列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<MerchantTraffic> traffics(BaseApiLookup lookup) {
        PageInfo<MerchantTraffic> data = merchantService.traffics(lookup);
        return resultSuccess(data);
    }

    /**
     * 5.08 新增交通信息
     */
    @RequestMapping(value = "/traffic", method = RequestMethod.POST)
    @ApiOperation(value = "新增交通信息", notes = "新增交通信息", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object addTraffic(@RequestBody @ApiParam(value = "交通信息", required = true) MerchantTraffic traffic) {
        merchantService.insertTraffic(traffic);
        return resultSuccess();
    }

    /**
     * 5.09 删除交通信息
     */
    @RequestMapping(value = "/traffic/{id}/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除交通信息", notes = "删除交通信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object deleteTraffic(@PathVariable @ApiParam(value = "商家ID", required = true) int id) {
        merchantService.deleteTraffic(id);
        return resultSuccess();
    }

}
