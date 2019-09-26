package com.vic.ck.api.rider.personal.controller;

import com.alibaba.fastjson.JSON;
import com.vic.base.BaseApiController;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.personal.service.PersonalService;
import com.vic.ck.api.rider.personal.service.SetupApiService;
import com.vic.ck.api.rider.personal.vo.InvitationVo;
import com.vic.ck.api.rider.personal.vo.SupplementVo;
import com.vic.ck.api.rider.personal.vo.WithdrawVo;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.api.system.service.CustomerBalanceService;
import com.vic.ck.console.examine.service.ExamineService;
import com.vic.ck.console.platform.service.PlatformFeedbackService;
import com.vic.ck.console.sysmanagement.service.RiderService;
import com.vic.ck.entity.*;
import com.vic.ck.util.GetAge;
import com.vic.wroot.common.exception.SmsException;
import com.vic.wroot.common.po.BaseResponse;
import com.vic.wroot.common.util.QRCodeImageUtil;
import com.vic.wroot.third.sms.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 *骑手端 个人中心 设置接口
 * @author hanyihu
 * @date 2019/4/17 10:51
 */

@Api(tags = "个人中心")
@RestController
@RequestMapping("/api/rider/personal")
public class SetupApiController extends BaseApiController {

    @Resource
    private SetupApiService setupApiService;

    @Resource
    private PlatformFeedbackService platformFeedbackService;

    @Resource
    private AuthService authService;

    @Resource
    private SmsService smsService;

    @Resource
    private ExamineService examineService;

    @Resource
    private PersonalService personalService;

    @Resource
    private RiderService riderService;

    @Resource
    private CustomerBalanceService customerBalanceService;


    private Logger logger= LoggerFactory.getLogger(SetupApiController.class);

    /*生成骑手的带头像二维码*/
    /*
      @ApiOperation(value = "获取合成头像的二维码",notes = "获取合成头像的二维码 骑手id：id",produces = "application/josn")
    @RequestMapping(value = "/getQRImageCode",method = RequestMethod.GET)*/
    public static Object getQRImageCode(int id) throws Exception {
        //二维码内容
        String content = "https://waimai.meituan.com/mobile/download/default";
        // Customer customer = setupApiService.findById(id);
        //String headpicUrl = customer.getHeadpicUrl();
        //String recommendQcodePathUrl = customer.getRecommendQcodePathUrl();
        String headpicUrl = "D:/Pictures/1.jpg";
        // 生成的二维码的路径及名称
        String recommendQcodePathUrl = "D:/Pictures/2.jpg";
        QRCodeImageUtil.encode(content, headpicUrl, recommendQcodePathUrl, true);
        String decode = QRCodeImageUtil.decode(recommendQcodePathUrl);
        return decode;
    }

    //根据骑手id获取骑手的设置信息
    @ApiOperation(value = "查询骑手的设置信息", notes = "查询骑手的设置，{骑手id:id} {头像：headpicUrl}  {二维码：recommendQcodePathUrl}  {开通骑手时的手机号：ttlAccounts} " +
            "{健康证图片：healthImage} {健康证号：healthCardNo}  {健康有效期：healthValidity} {身份证图片：idCardImage} {工作城市：sendcity}  {本机号码（账号）：mobile}" +
            "{紧急联系人：emergency}  {紧急联系人电话：emergencyPhone}  {交通工具：vehicle(1电动车  2自行车  3摩托车  4汽车)}  {婚姻状况：marriage（1未婚 2已婚）} {学历：education（1博士  2硕士  3本科  4专科  5其他）} "
            ,produces = "application/josn")
    @RequestMapping(value = "/setup",method = RequestMethod.GET)
    public Object setup ( int id){
        Customer entity = setupApiService.findById(id);
        //头像
        logger.info("获取的二维码地址{}", entity.getRecommendQcodePathUrl());
        int age = GetAge.getAgeByBirth(entity.getBirthday());
        entity.getRecommendQcodePathUrl();
        entity.setAge(age);

        return resultSuccess(entity);
    }


   //骑手端个人中心设置管理规则
   @ApiOperation(value = "骑手的设置中管理规则", notes = "骑手的设置中管理规则， {标题：title}   {图标：iconUrl}  {内容：content}   {时间：createTime}  {是否显示：isShow} ", produces = "application/josn")
    @RequestMapping(value = "/riderRule",method = RequestMethod.GET)
   public BaseResponse<RiderManagement> riderRule(Lookup lookup) {
       PageInfo<RiderManagement> list = setupApiService.ruleList(lookup);
        return resultSuccess(list);
    }


  /*骑手端个人中心工作统计*/
  @ApiOperation(value="个人中心 工作统计", notes = "个人中心 工作统计 createTime=1：今日   createTime=2：历史统计 ，接单：orderNum  完成订单：completeOrder  取消订单：cancelOrders " +
          "收益：profit ",produces = "application/josn")
   @RequestMapping(value = "/statistics",method = RequestMethod.GET)
    public BaseResponse<WorkStatistics> statistics( int id, int createTime){
      //id为骑手id， createTime=1为今日统计，否则为历史统计
      WorkStatistics data = setupApiService.workStatistics(id, createTime);
      return resultSuccess(data);
  }

   
   /*骑手端个人中心 接单明细 id为骑手id， createTime日期*/
   @ApiOperation(value="个人中心 接单明细", notes = "个人中心 工作统计 id:骑手id createTime：传入的日期   订单号：orderno   订单状态：orderStatus( 0:已取消   3已完成)，",produces = "application/josn")
    @RequestMapping(value = "/orderDetail",method = RequestMethod.GET)
    public BaseResponse<RiderTask> orderDetail( int id, Date createTime, Lookup lookup){

       PageInfo<RiderTask> data=  setupApiService.listDetail(id,createTime,lookup);
      return resultSuccess(data);
    }

    /*我的客服*/
    @ApiOperation(value="个人中心 我的客服", notes = "个人中心 客服  ， 客服名：title 客服电话：phone  客服头像：headImage  问题：problem  解决方案：solution",produces = "application/josn")
    @RequestMapping(value = "/customerService",method = RequestMethod.GET)
    public BaseResponse<CustomerService> customerService( Lookup lookup){
       PageInfo<CustomerService> data= setupApiService.customerService(lookup);
        return resultSuccess(data);
    }

    @ApiOperation(value = "骑手服务标准", notes = "骑手服务标准  {骑手服务标准:rider_service_standard} ", produces = "application/josn")
    @RequestMapping(value = "/serviceStandard", method = RequestMethod.GET)
    public Object serviceStandard() {
        List<SysManagement> list = riderService.list();
        return resultSuccess(list);
    }

    /*骑手端 反馈*/
    @ApiOperation(value="个人中心设置 反馈", notes = "反馈  ， 骑手id：id, 骑手名：customerName ,  反馈内容：content  图片：images   提交时间：createTime ",produces = "application/josn")
    @RequestMapping(value = "/feedback",method =RequestMethod.GET)
    public void feedback( int id, PlatformFeedback entity){
        entity.setCustomerId(id);
        platformFeedbackService.insert(entity);
    }

    /*骑手端 配送中心*/
    @ApiOperation(value="配送中心", notes = "配送中心 type=1:日，type=2：月   createTime为查询日期（按日查询格式：2019-04-15，按年月查询格式：2019 1~12月）， 完成订单：completeOrder  订单收入：profit  活动奖励：activityAward  其他收入：otherIncome  总收入：totalIncome ",produces = "application/josn")
    @RequestMapping(value = "/distribution",method = RequestMethod.GET)
    public BaseResponse<WorkStatistics>  distribution( int id, int type, String  createTime,Lookup lookup){
        WorkStatistics data= setupApiService.distribution(id,type,createTime,lookup);

        return resultSuccess(data);

    }

    /*骑手端 个人中心补充接口*/
    @ApiOperation(value="个人中心 头像 余额 昵称 手机号", notes = "个人中心 骑手id：id 头像：headImage  手机号：mobile  余额：balance   昵称：nickname  ",produces = "application/josn")
    @RequestMapping(value = "/supplement",method = RequestMethod.GET)
    public BaseResponse<SupplementVo>  supplement(int id, Lookup lookup){
        SupplementVo data= setupApiService.supplement(id,lookup);

        return resultSuccess(data);

    }

    /*用户登录时验证是否是骑手*/
    @ApiOperation(value="登录验证是否是骑手 ", notes = "是否是骑手 用户id：id  true:骑手  false：非骑手",produces = "application/josn")
    @RequestMapping(value = "/isRider",method = RequestMethod.GET)
    public boolean isRider(Integer id) {
        Customer customer = authService.findCustomerById(id);
        if(customer!=null){
            logger.info("判断用户是否是骑手（1）{}",customer.getIsRider());
            return  customer.getIsRider()==1?true:false;
        }
        return false;

    }

    //修改骑手端 个人中心设置的信息
    @ApiOperation(value = "修改骑手的设置信息", notes = "修改骑手的设置，{骑手id:id} {头像：headpicUrl}  {二维码：recommendQcodePathUrl}  {开通骑手时的手机号：ttlAccounts} " +
            "{健康证：healthCard} {健康证号：healthCardNo}  {健康有效期：healthValidity} {身份证：idCard} {工作城市：sendcity}  {本机号码（账号）：mobile}" +
            "{紧急联系人：emergency}  {紧急联系人电话：emergencyPhone}  {交通工具：vehicle(1电动车  2自行车  3摩托车  4汽车)}  {婚姻状况：marriage（1未婚 2已婚）} {学历：education（1博士  2硕士  3本科  4专科  5其他）} "
            , produces = "application/josn")
    @RequestMapping(value = "/updateSetup", method = RequestMethod.GET)
    public void updateSetup(Customer entity) {
        logger.info("进入修改界面{}", entity);

        setupApiService.updateSetup(entity);

    }

    /*开通骑手*/
    @ApiOperation(value = "开通骑手 ", notes = "开通骑手 骑手id：riderId  配送城市：sendcity  配送详细地址：address  联系电话：phone  短息验证码：smsCode", produces = "application/josn")
    @RequestMapping(value = "/openRider",method = RequestMethod.GET)
    @Transactional
    public Object openRider(RiderExamine entity) {
        //1 判断验证码
        String code = cacheFromRedis(entity.getPhone()) + "";
       logger.info("从缓存中取出的短信验证码======{}",code);
       logger.info("提交时输入的短信验证码======{}",entity.getSmsCode());
        if (!StringUtils.equalsIgnoreCase(code, entity.getSmsCode())) {
           return resultError(ResultMsgEnum.INVALID_SMSCODE);
        }

        //根据骑手id查找骑手提交临时表信息
        RiderExamine riderExamine = examineService.findByRiderId(entity.getRiderId());
        if (riderExamine == null) {
            setupApiService.submitRider(entity);
        } else {
            //修改提交的信息
            int i = examineService.updateRiderExamine(entity);
            if (i > 0) {
                return true;
            }
            return false;
        }

        return true;
    }

    /*上传健康证*/
    @ApiOperation(value = "上传健康证 ", notes = "上传健康证 {骑手id：riderId} ,{健康证：healthCard} ，{健康证号：healthCardNo}， {健康证有效期：healthValidity}", produces = "application/josn")
    @RequestMapping(value = "uploadHealth", method = RequestMethod.GET)
    public boolean uploadHealth(RiderExamine entity) {
        //把健康证传入到骑手临时信息表中
        int i = setupApiService.uploadHealth(entity);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /*上传身份证*/
    @ApiOperation(value = "上传身份证 ", notes = "上传身份证 {骑手id：riderId} ,{身份证：idCard} ，", produces = "application/josn")
    @RequestMapping(value = "uploadIdCard", method = RequestMethod.GET)
    public boolean uploadIdCard(RiderExamine entity) {
        //把健康证传入到骑手临时信息表中

        entity.setStatus(1);//把审核状态变成 1待审核
        int i = setupApiService.uploadIdCard(entity);
        if (i > 0) {
            return true;
        }
        return false;
    }


    /*绑定银行卡*/
    @ApiOperation(value = "绑定银行卡", notes = "绑定银行卡 {骑手id：customerId}  {持卡人：accountName}  {卡号：bankCardno}  {手机号：mobile}  {smscode：验证码}", produces = "application/josn")
    @RequestMapping(value = "/bindCard", method = RequestMethod.GET)
    public Object bindCard(CustomerBankCard entity, String smsCode) {
        //先判断验证码是不是一致，
        String code = cacheFromRedis(entity.getMobile()) + "";//缓存的验证码
        if (!StringUtils.equalsIgnoreCase(code, smsCode)) {
            return resultError(ResultMsgEnum.INVALID_SMSCODE);
        }
        personalService.addBankCard(entity);
        return true;
    }

    /*获取短信验证码*/
    @ApiOperation(value = "获取短信验证码 ", notes = "开通骑手短信验证码 联系电话：phone  ", produces = "application/josn")
    @RequestMapping(value = "/smsCode",method = RequestMethod.GET)
    public Object smsCode(String mobile ){
        String code = RandomStringUtils.random(6, "0123456789");
        try {
            smsService.sendSmscode(mobile, code);

        } catch (SmsException e) {
            return resultError(ResultMsgEnum.SMSSEND_FAIL);
        }
        cache2Redis(mobile, code, 30);
        return resultSuccess(code);
    }


     /*获取骑手端消息列表*/
     @ApiOperation(value = "消息中心--系统消息",notes ="骑手端系统消息 {标题：title} {内容：content}  {时间：pushTime}",produces = "application/josn")
     @RequestMapping(value = "/systemMsg",method = RequestMethod.GET)
     public Object systemMsg(Lookup lookup){
          //系统消息
          PageInfo<PlatformMsg> msg = setupApiService.getMsg(lookup);
         return resultSuccess(msg);
      }

      /*获取骑手端消息列表*/
      @ApiOperation(value = "消息中心--快捷消息",notes ="骑手端快捷消息  订单号：orderno   订单状态：orderStatus( 1:待取货   2配送中 3已完成)  {时间：creationTime}",produces = "application/josn")
      @RequestMapping(value = "/quickMsg",method = RequestMethod.GET)
      public Object quickMsg(int id,Lookup lookup){
          //快捷消息
          PageInfo<RiderTask> msg = setupApiService.quickMsg(id,lookup);
         return resultSuccess(msg);

      }

      /*骑手邀请轮播图*/
      @ApiOperation(value = "骑手邀请轮播图",notes ="骑手邀请轮播图   轮播图地址：imageUrl  ",produces = "application/josn")
      @RequestMapping(value = "/InviteBanner",method = RequestMethod.GET)
      public Object InviteBanner(Lookup lookup){
          PageInfo<PlatformBanner> data= setupApiService.InviteBanner(lookup);

          return resultSuccess(data);

      }

    /*返回提交骑手信息时的状态*/
    @ApiOperation(value = "返回骑手信息审核状态", notes = "返回骑手信息审核状态 骑手id：id   " +
            "{审核状态(0未审核  1审核中 2审核成功  3审核失败):status}   {审核失败说明：examineRemark} ", produces = "application/josn")
    @RequestMapping(value = "/examine", method = RequestMethod.GET)
    public Object examine(int id) {
        RiderExamine riderExamine = examineService.findByRiderId(id);
        return riderExamine;
    }

    //骑手端 我的邀请
    @ApiOperation(value = "个人中心 我的邀请", notes = "个人中心 邀请  ， 邀请人信息：invitationList（名称：nickname 手机号：mobile  时间：createTime） 已邀请：allInvitation  成功邀请：successInvitation  邀请收益：invatationFee " +
            "邀请路径：codePath", produces = "application/josn")
    @RequestMapping(value = "/invitation", method = RequestMethod.GET)
    public BaseResponse<InvitationVo> invitation(int id) {
        InvitationVo data = setupApiService.invitation(id);

        return resultSuccess(data);
    }

    /*获取用户二维码*/
  /*  @ApiOperation(value = "生成二维码",notes = " 生成二维码",produces = "application/josn")
    @RequestMapping(value = "/getCode",method = RequestMethod.GET)*/
    public Object getCode(int id) {
        Customer c = new Customer();
        authService.generateQrcode(id);
        logger.info("二维码{}", c.getRecommendQcodePath());

        return c.getRecommendQcodePathUrl();
    }

    /*骑手 提现*/
    @Transactional
    @ApiOperation(value = "骑手 提现 ", notes = "骑手 提现 用户id：id  银行卡信息集合:bankCardList  银行卡名称：bankName 银行卡尾号：lastNumber  银行卡图标：bankIconUrl  银行卡类型：cardType（1储蓄卡  2信用卡  其它）  提现金额:withdrawMoney  可提现金额:canWithdrawMoney ", produces = "application/josn")
    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public Object withdraw(int id, BigDecimal withdrawMoney) {
        WithdrawVo entity = setupApiService.withdraw(id);
        if (withdrawMoney == null) {

            return entity;
        }
        if (withdrawMoney.compareTo(entity.getCanWithdrawMoney()) == 1) {
            return resultError(ResultMsgEnum.OVERSTEP);
        }

        int i = setupApiService.addBalance(id, withdrawMoney);
        if (i > 0) {
            //骑手余额减少相应的余额
            DecimalFormat df = new DecimalFormat("0.00");
            String percent = "2%";
            percent = percent.replace("%", "");
            Float f = Float.valueOf(percent) / 100;
            BigDecimal decimal = new BigDecimal(df.format(f));
            logger.info("转为BigDecimal类型的数据==={}" + decimal);
            RiderBalanceSheet riderBalanceSheet = setupApiService.balance(id);
            logger.info("从数据库查出的用户余额{}", JSON.toJSON(riderBalanceSheet));
            BigDecimal money = riderBalanceSheet.getBalance().subtract(withdrawMoney.add(withdrawMoney.multiply(decimal)));
            logger.info("变更后的用户余额=={}", money);
            if (money.signum() == -1) {
                riderBalanceSheet.setBalance(BigDecimal.ZERO);
            } else {
                riderBalanceSheet.setBalance(money);
            }
            boolean b = setupApiService.updateRiderBalance(riderBalanceSheet);
            if (b) {
                return true;
            }
            return false;
        }

        return false;
    }

    public static void main(String[] args) {

        try {
            Object qrImageCode = getQRImageCode(2);
            System.out.println("获取的带头像的二维码=="+qrImageCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
