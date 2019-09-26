package com.vic.ck.api.rider.personal.mapper;

import com.vic.base.pager.Lookup;
import com.vic.ck.entity.*;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 *
 * @author hanyihu
 * @date 2019/4/17 10:51
 */
@MybatisMapper
public interface SetupApiMapper {

    //查找骑手端的信息
    Customer findById(@Param("id") int id);

    //修改设置
    void updateSetup(Customer entity);

    //订单数
    Integer orderNum(@Param("id") int id, @Param("createTime") Integer createTime);

    //完成订单数
    Integer completeOrder(@Param("id") int id, @Param("createTime") Integer createTime);

    //取消订单数
    Integer cancelOrders(@Param("id") int id, @Param("createTime") Integer createTime);

    //获取骑手余额
    RiderBalanceSheet balance(@Param("id") int id);

    //收益
    BigDecimal profit(@Param("id") int id, @Param("createTime") Integer createTime);

    //管理细则
    List<RiderManagement> ruleList(Lookup lookup);

    //接单明细
    List<RiderTask> listDetail(@Param("id") int id, @Param("createTime") Date createTime, Lookup lookup);

    //邀请信息集合
    List<Customer> invitationList(@Param("id")int id);

    //全部邀请数
    Integer getAllInvitation(@Param("id") int id);

    //邀请成功的人数
    Integer getSuccessInvitation(@Param("id") int id);

    //我的客服
    List<CustomerService> customerService(Lookup lookup);

    //我的客服问题集合
    List<CustomerServiceProblem> findProblemList();

    //配送中心
    WorkStatistics distribution(@Param("id") int id,@Param("type")int type,@Param("createTime") String createTime,Lookup lookup);

     //开通骑手
     void submitRider(RiderExamine entity);

    //上传健康证
    int uploadHealth(RiderExamine entity);

    //上传身份证
    int uploadIdCard(RiderExamine entity);

    //查找骑手提交临时信息
    RiderExamine findByRiderId(@Param("id") Integer id);

    //修改骑手余额
    int updateRiderBalance(RiderBalanceSheet balance);

    //骑手 提现 获取骑手银行卡信息集合
    List<CustomerBankCard> bankCardList(@Param("customerId")Integer customerId);

    //增加卡内余额
    int addBalance(@Param("id") int id, @Param("withdrawMoney") BigDecimal withdrawMoney);

     //消息中心---系统消息
    List<PlatformMsg> getMsg(Lookup lookup);

    //消息中心---快捷消息
    List<RiderTask> quickMsg(@Param("id") int id,Lookup lookup);

    //骑手邀请轮播
    List<PlatformBanner> InviteBanner(Lookup lookup);


}
