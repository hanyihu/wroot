package com.vic.ck.console.statistics.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.statistics.mapper.ReportOrderMapper;
import com.vic.ck.entity.BusinessStatistics;
import com.vic.ck.entity.MemberStatistics;
import com.vic.ck.entity.ReportOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * 订单的一些统计报表
 *
 */
@Service
public class ReportOrderService extends BaseService{

	@Resource
	private ReportOrderMapper reportOrderMapper;
	
	/**
    * 查询列表
    */
	public PageInfo<ReportOrder> list(Lookup lookup){
		startPage(lookup);
		List<ReportOrder> datas = reportOrderMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}
	
	/**
	 * 根据主键查询
	 */
	public ReportOrder findById(int id) {
		return reportOrderMapper.findById(id);
	}
	
	public ReportOrder findByday(String day) {
		return reportOrderMapper.findByday(day); 
	}
	
	/**
	 * 新增
	 */
	 public int insert(ReportOrder entity) {
		return reportOrderMapper.insert(entity);
	}
	
	/**
	 * 修改
	 */
	  public int update(ReportOrder entity) {
		  return reportOrderMapper.update(entity);
	  }
	  
	  /***
	   * 删除
	   */
	  public int delete(int...ids) {
		  return reportOrderMapper.delete(ids);
	  }

	public boolean existCurrentDay(String day) {
		return reportOrderMapper.existCurrentDay(day);
	}

	/**
	 * 统计这一天的一些订单数据 
	 */
	public ReportOrder staticticsCurrentDayOrder(String day) {
		return reportOrderMapper.staticticsCurrentDayOrder(day);
	}

	/**
	 * 商家的一些统计
	 * @param day 订单支付时间
	 * @return
	 */
	public BusinessStatistics selectMerchantsByTime(String day, Integer mid){
                    return reportOrderMapper.selectMerchantsByTime(day,mid);
	}

	/**
	 * 根据日期查询所有有订单的商家
	 * @param day
	 * @return
	 */
	public List<Integer> merchantsWithOrders(String day){
		return reportOrderMapper.merchantsWithOrders(day);
	}

	/**
	 * 查询所有会员
	 * @return
	 */
	public List<Integer> judgeState(){

		return reportOrderMapper.judgeState();
	}

	/**
	 * 会员统计
	 * @param id
	 * @return
	 */
	public MemberStatistics selectMembers(int id){

		return reportOrderMapper.selectMembers(id);
	}

	/**
	 * 查询会员退款额
	 * @param id
	 * @param type
	 * @return
	 */
	public MemberStatistics refundAmounts(int id,int type){

		return reportOrderMapper.refundAmounts(id,type);
	}
    
}
