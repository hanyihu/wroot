package com.vic.ck.api.rider.personal.service;


import com.alibaba.fastjson.JSON;
import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.rider.personal.mapper.SetupApiMapper;
import com.vic.ck.api.rider.personal.vo.InvitationVo;
import com.vic.ck.api.rider.personal.vo.SupplementVo;
import com.vic.ck.api.rider.personal.vo.WithdrawVo;
import com.vic.ck.api.system.mapper.CustomerBalanceMapper;
import com.vic.ck.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hanyihu
 * @date 2019/4/17 10:51
 */
@Service
public class SetupApiService extends BaseService {

   @Resource
   private SetupApiMapper setupApiMapper;

    @Resource
    private CustomerBalanceMapper customerBalanceMapper;

    private Logger logger= LoggerFactory.getLogger(SetupApiService.class);

    public Customer findById(int id) {
        Customer entity = setupApiMapper.findById(id);
        return entity;
    }

    //骑手余额信息
    public RiderBalanceSheet balance(int id) {
        return setupApiMapper.balance(id);
    }

    public void updateSetup(Customer entity) {
        setupApiMapper.updateSetup(entity);
    }

    //工作统计
    public WorkStatistics workStatistics(int id,Integer createTime) {
        Integer orderNum = setupApiMapper.orderNum(id,createTime);//接单数
        Integer completeOrder = setupApiMapper.completeOrder(id,createTime);//完成订单数
        Integer cancelOrders= setupApiMapper.cancelOrders(id,createTime);//取消订单数
        BigDecimal profit= setupApiMapper.profit(id,createTime);//收益
        WorkStatistics entity=new WorkStatistics();
        entity.setOrderNum(orderNum);
        entity.setCompleteOrder(completeOrder);
        entity.setCancelOrders(cancelOrders);
         if(profit==null){
             entity.setProfit(BigDecimal.ZERO);
         }
         else{
             entity.setProfit(profit);
         }

        return entity;
    }

    //接单明细
    public PageInfo<RiderTask> listDetail(int id,Date createTime,Lookup lookup) {
        startPage(lookup);
        List<RiderTask> datas = setupApiMapper.listDetail(id,createTime,lookup);

        return PageInfo.instance(datas, lookup);
    }

    //我的客服
    public PageInfo<CustomerService> customerService(Lookup lookup){
        startPage(lookup);
        List<CustomerService> datas=setupApiMapper.customerService(lookup);

        for (CustomerService customerService:datas) {
            customerService.setProblemList(setupApiMapper.findProblemList());
            customerService.getHeadImageUrl();
        }

        return PageInfo.instance(datas,lookup);
    }

    //修改骑手余额
    public boolean updateRiderBalance(RiderBalanceSheet balance) {
        return setupApiMapper.updateRiderBalance(balance) > 0;
    }


    //我的邀请
    public InvitationVo invitation(int id){

        InvitationVo invitationVo=new InvitationVo();
        List<Customer> datas= setupApiMapper.invitationList(id);//邀请人信息集合
        Integer allInvitation= setupApiMapper.getAllInvitation(id);//全部邀请数
        Integer successInvitation=setupApiMapper.getSuccessInvitation(id);//成功邀请数
        invitationVo.setCodePath(setupApiMapper.findById(id).getRecommendQcodePathUrl());//二维码路径
        logger.info("成功邀请数=={}", successInvitation);
        invitationVo.setInvitationList(datas);
        invitationVo.setAllInvitation(allInvitation);
        invitationVo.setSuccessInvitation(successInvitation);
        invitationVo.setInvatationFee(successInvitation * 20);
        invitationVo.setCodePath("https://waimai.meituan.com/mobile/download/default");
        return invitationVo;
    }

    //配送中心
    public WorkStatistics distribution(int id,int type, String createTime,Lookup lookup){
        startPage(lookup);
        WorkStatistics entity=  setupApiMapper.distribution(id,type,createTime,lookup);
        System.out.println(JSON.toJSON(entity));
        if(entity.getActivityAward()==null)
        {
            entity.setActivityAward(BigDecimal.ZERO);
        }
        if(entity.getProfit()==null){
            entity.setProfit(BigDecimal.ZERO);
        }
        if(entity.getOtherIncome()==null){
            entity.setOtherIncome(BigDecimal.ZERO);
        }
            entity.setTotalIncome(entity.getOtherIncome().add(entity.getActivityAward().add(entity.getProfit())));
        if(entity.getTotalIncome()==null){
            entity.setTotalIncome(BigDecimal.ZERO);
        }

        return entity;
    }

    /*管理细则*/
    public PageInfo<RiderManagement> ruleList(Lookup lookup) {
        startPage(lookup);
        List<RiderManagement> datas = setupApiMapper.ruleList(lookup);
        for (RiderManagement riderManagement : datas) {
            riderManagement.setContent(riderManagement.getContent().replaceAll("<[.[^<]]*>", ""));
            riderManagement.setContent(riderManagement.getContent().replaceAll("&nbsp;", ""));
            riderManagement.setContent(riderManagement.getContent().replaceAll("\t", ""));

        }
        return PageInfo.instance(datas,lookup);
    }



    /*补充个人中心*/
    public SupplementVo supplement(int id,Lookup lookup){
        SupplementVo supplementVo=new SupplementVo();

        Customer entity = setupApiMapper.findById(id);
        supplementVo.setHeadImage(entity.getRecommendQcodePathUrl());//头像放入到补充个人中心里
        supplementVo.setNickname(entity.getNickname());//昵称
        supplementVo.setMobile(entity.getMobile());//手机号

        RiderBalanceSheet riderBalanceSheet = setupApiMapper.balance(id);//余额信息
        BigDecimal balance = riderBalanceSheet.getBalance();//余额
        if(balance==null){
            supplementVo.setBalance(BigDecimal.ZERO);
        }else{
            supplementVo.setBalance(balance);
        }

        return supplementVo;
    }

    /*开通骑手提交申请*/
    public void submitRider(RiderExamine entity) {
      logger.info("开通骑手实体类====={}",JSON.toJSON(entity));
      setupApiMapper.submitRider(entity);

    }

    /*上传健康证*/
    public int uploadHealth(RiderExamine entity) {
        return setupApiMapper.uploadHealth(entity);
    }

    /*上传身份证*/
    public int uploadIdCard(RiderExamine entity) {
        return setupApiMapper.uploadIdCard(entity);
    }

    /*根据骑手ID查找信息*/
    RiderExamine findByRiderId(Integer id) {
        return setupApiMapper.findByRiderId(id);
    }


    /*提现*/
    public WithdrawVo withdraw(int id){
       WithdrawVo withdrawVo=new WithdrawVo();
        List<CustomerBankCard> bankCardList = setupApiMapper.bankCardList(id);//获取骑手银行卡
        //获取银行卡尾号
        if(bankCardList.size()!=0) {
            for (CustomerBankCard customerBankCard : bankCardList) {
                String bankCardno = customerBankCard.getBankCardno();
                int length = bankCardno.length();
                 if(length!=0){
                     String str = bankCardno.substring(length-4, length);
                     customerBankCard.setLastNumber(str);
                     logger.info("银行卡尾号为{}",str);
                 }


            }
        }
        RiderBalanceSheet riderBalance = setupApiMapper.balance(id);//获取骑手的余额

        BigDecimal balance = riderBalance.getBalance();
        withdrawVo.setBankCardList(bankCardList);
        withdrawVo.setCanWithdrawMoney(balance);
        return withdrawVo;
    }

    /*增加卡内余额*/
    public int addBalance(int id, BigDecimal withdrawMoney) {
        List<CustomerBankCard> customerBankCards = setupApiMapper.bankCardList(id);
        if (customerBankCards.size() > 0) {
            CustomerBankCard customerBankCard = customerBankCards.get(0);
            BigDecimal balance = customerBankCard.getBalance();
            logger.info("从银行卡取出的卡内余额{}", balance);
            withdrawMoney = withdrawMoney.add(balance);

        }
        logger.info("插入银行卡的卡内余额{}", withdrawMoney);
        return setupApiMapper.addBalance(id, withdrawMoney);
    }

    /*消息中心---系统消息*/
    public PageInfo<PlatformMsg>  getMsg(Lookup lookup){
      startPage(lookup);
      List<PlatformMsg> datas= setupApiMapper.getMsg(lookup);
      return PageInfo.instance(datas,lookup);
    }

    /*消息中心---快捷消息*/
    public PageInfo<RiderTask> quickMsg(int id, Lookup lookup){
        startPage(lookup);
        List<RiderTask> datas= setupApiMapper.quickMsg(id,lookup);
        return  PageInfo.instance(datas,lookup);
    }

    /*骑手邀请轮播*/
    public  PageInfo<PlatformBanner> InviteBanner(Lookup lookup){
        startPage(lookup);
        List<PlatformBanner> datas=setupApiMapper.InviteBanner(lookup);
        return PageInfo.instance(datas,lookup);
    }

}
