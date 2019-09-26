package com.vic.ck.console.statistics.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.BusinessStatistics;
import com.vic.ck.entity.MemberStatistics;
import com.vic.ck.entity.ReportOrder;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单的一些统计报表
 * 
 * @author VIC
 */
@MybatisMapper
public interface ReportOrderMapper extends BaseMapper<ReportOrder> {

	boolean existCurrentDay(@Param("day")String day);

	ReportOrder staticticsCurrentDayOrder(@Param("day")String day);

	ReportOrder findByday(@Param("day") String day);

	BusinessStatistics selectMerchantsByTime(@Param("day") String day, @Param("mid")Integer mid);

	List<Integer> merchantsWithOrders(@Param("day") String day);

	List<Integer> judgeState();

	MemberStatistics selectMembers(@Param("id") int id);

	MemberStatistics refundAmounts(@Param("id") int id,@Param("type") int type);

}
