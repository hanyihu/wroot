package com.vic.ck.console.activity.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.PlatformActivityPulllimitn;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 平台活动表
 *
 * @author VIC
 */
@MybatisMapper
public interface FullLimitnMapper extends BaseMapper<PlatformActivityPulllimitn> {

    // 已领优惠券的所有用户查询
    List<PlatformActivityPulllimitn> findByIds(@Param("ids") Integer[] ids);

    //  根据ID查优惠券
    PlatformActivityPulllimitn discountCouponById(@Param("id") int id);

}
