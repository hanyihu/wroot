package com.vic.ck.api.personal.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.merchant.service.MerchantActivityService;
import com.vic.ck.api.personal.mapper.CustomerDiscountTicketMapper;
import com.vic.ck.entity.CustomerDiscountTicket;
import com.vic.ck.entity.PlatformActivityDiscountcoupon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * 用户优惠券(折扣券)
 *
 */
@Service
public class CustomerDiscountTicketService extends BaseService {

	@Resource
	private CustomerDiscountTicketMapper customerDiscountTicketMapper;
	
	@Resource
	private MerchantActivityService merchantActivityService;

	/**
	 * 查询列表
	 */
	public PageInfo<CustomerDiscountTicket> list(Lookup lookup) {
		startPage(lookup);
		List<CustomerDiscountTicket> datas = customerDiscountTicketMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public CustomerDiscountTicket findById(int id) {
		return customerDiscountTicketMapper.findById(id);
	}

	/**
	 * 新增
	 */
	public int insert(CustomerDiscountTicket entity) {
		return customerDiscountTicketMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	public int update(CustomerDiscountTicket entity) {
		return customerDiscountTicketMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		return customerDiscountTicketMapper.delete(ids);
	}
	

	/**
	 * 领取折扣券
	 */
	@Transactional
	public synchronized CustomerDiscountTicket receiveTicket(PlatformActivityDiscountcoupon activity, int customerId) {

		String random = "随机金额";
		//类型：1满减券、2折扣券、3随即金额券
		if (activity.getType() != null && activity.getType() == 3) {
			String[] manjian = activity.getRulerandom().split(":");
			//得到满额使用金额数
			int xiaxian = Integer.parseInt(manjian[1]);
			int shangxian = Integer.parseInt(manjian[2]);
//          通过java.util包中的Random类的nextInt方法来得到1-10的int随机数
			Random ra = new Random();
			int nextInt = ra.nextInt(shangxian) + xiaxian;
			random = manjian[0] + ":" + nextInt;
		} else {
			random = activity.getRulerandom();
		}
		CustomerDiscountTicket t = new CustomerDiscountTicket(customerId, activity.getId(), activity.getType(),
				activity.getStartdate(), activity.getEnddate(), activity.getRulereduce(), activity.getRulediscount(), random);
		insert(t);

		//减少库存
		if (activity.getNum() != null && activity.getNum() > 0) {
			//
			merchantActivityService.minusStock(activity.getId());
		}
		return t;
	}

	public void updateStatus(int id, int status) {
		customerDiscountTicketMapper.updateStatus(id, status);
		
	}

	/**
	 * 优惠券是否过期
	 */
	public int overduedUpdate() {
		return customerDiscountTicketMapper.overduedUpdate();
	}

}
