package com.vic.ck.console.scheduling.mapper;

import com.vic.base.pager.Lookup;
import com.vic.ck.entity.RiderOrder;
import com.vic.ck.entity.RiderPersonnel;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MybatisMapper
public interface PersonnelMapper {
    /**所有骑手信息*/
    List<RiderPersonnel> selectRiderPersonnel(Lookup lookup);
    /**骑手详细信息*/
    RiderPersonnel selectById(@Param("id") int id);
    /**所有订单*/
    List<RiderOrder> selectAllOrder(Lookup lookup);
}
