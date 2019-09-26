package com.vic.ck.api.system.mapper;

import com.vic.ck.entity.Customer;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

@MybatisMapper
public interface AuthMapper {

    /**
     * 注册用户
     */
    void insertCustomer(Customer c);

    /**
     * 注册第三方账号
     */
    void insertThirdCustomer(Customer c);

    /**
     * 新建余额账户
     */
    void insertCustomerBalance(Customer c);

    //手机号是否重复
    boolean existedMobile(@Param("mobile") String mobile);

    Integer findCustomerIdByMobile(@Param("mobile") String mobile);

    /**
     * 获取第三方用户id
     */
    Integer findThirdPartyCustomerId(@Param("thirdPartyType") int thirdPartyType, @Param("thirdPartyUid") String thirdPartyUid);

    Customer findCustomerById(@Param("id") Integer id);

    //修改设备号
    void updateMobileCode(Customer customer);

    //修改设备号-商家端
    void updateMerchantMobileCode(Customer customer);

    void updatePassword(Customer customer);

    void updateCustomer(Customer customer);

    //修改手机号
    void updateMobile(@Param("mobile") String mobile, @Param("id") int id);

    /**
     * 修改用户积分
     */
    void updateScore(Customer customer);

    Integer findCustomerIdByMerchantId(@Param("merchantId") Integer merchantId);

    void userLogged(@Param("id") Integer customerId);

    Customer findCustomerByByRider(@Param("id") String id);


}