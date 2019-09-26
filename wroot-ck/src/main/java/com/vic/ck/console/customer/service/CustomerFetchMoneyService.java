package com.vic.ck.console.customer.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.customer.mapper.CustomerFetchMoneyMapper;
import com.vic.ck.entity.CustomerFetchMoney;

/**
 * 用户提现申请表
 *
 */
@Service
public class CustomerFetchMoneyService extends BaseService {

	@Resource
	private CustomerFetchMoneyMapper customerFetchMoneyMapper;

	/**
	 * 查询列表
	 */
	public PageInfo<CustomerFetchMoney> list(Lookup lookup) {
		startPage(lookup);
		List<CustomerFetchMoney> datas = customerFetchMoneyMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public CustomerFetchMoney findById(int id) {
		CustomerFetchMoney entity = customerFetchMoneyMapper.findById(id);
		if (entity != null && entity.getCardId() != null) {
			entity.setBankCard(customerFetchMoneyMapper.bankCard(entity.getCardId()));
		}
		return entity;
	}

	/**
	 * 新增
	 */
	public int insert(CustomerFetchMoney entity) {
		return customerFetchMoneyMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	public int update(CustomerFetchMoney entity) {
		return customerFetchMoneyMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		return customerFetchMoneyMapper.delete(ids);
	}

}
