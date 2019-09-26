package com.vic.ck.console.activity.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.PlatformActivityDiscountcoupon;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 平台优惠券活动
 *
 * @author VIC
 */
@MybatisMapper
public interface PlatformActivityDiscountCouponMapper extends BaseMapper<PlatformActivityDiscountcoupon> {


    //插入优惠券 适用商家表
    int inserts(@Param("id") int id, @Param("merchantid") int idmerchantid);

    //已领优惠券的所有用户查询
    List<PlatformActivityDiscountcoupon> listuse(@Param("id") int id);

    List<PlatformActivityDiscountcoupon> findByIds(@Param("ids") Integer[] ids);

    //根据ID查优惠券
    PlatformActivityDiscountcoupon discountCouponById(@Param("id") int id);

    //查优惠券 是否适用于当前商家
    int discountCouponByMerchantId(@Param("id") int id, @Param("merchantid") int merchantid);

    //删除对应商家
    int deletemerchantId(@Param("ids") int[] ids);


}
