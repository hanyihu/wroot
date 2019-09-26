package com.vic.ck.console.examine.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.Customer;
import com.vic.ck.entity.RiderExamine;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 开通骑手提交信息审核
 * @author hanyihu
 * @date 2019/4/23 11:02
 */
@MybatisMapper
public interface ExamineMapper extends BaseMapper<RiderExamine> {

  int update(Customer entity);

    RiderExamine findByRiderId(@Param("riderId") Integer riderId);

    int updateExamine(RiderExamine entity);

    int updateRiderExamine(RiderExamine entity);
}
